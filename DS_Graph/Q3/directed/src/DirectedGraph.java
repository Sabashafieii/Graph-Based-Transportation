import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class DirectedGraph {
    int V;
    List<List<Integer>> graph;

    public DirectedGraph(int n)
    {
        V = n;
        graph = new ArrayList<>(V);

        for (int i = 0; i < V; i++)
            graph.add(new LinkedList<>());
    }

    private boolean isCyclicUtil(int i, boolean[] visited, boolean[] recStack)
    {
        if (recStack[i])
            return true;
        if (visited[i])
            return false;
        visited[i] = true;
        recStack[i] = true;
        List<Integer> children = graph.get(i);
        for (Integer c: children)
            if (isCyclicUtil(c, visited, recStack))
                return true;
        recStack[i] = false;
        return false;
    }

    private void addEdge(int p, int q) {
        graph.get(p).add(q);
    }

    private boolean isCyclic()
    {
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        for (int i = 0; i < V; i++)
            if (isCyclicUtil(i, visited, recStack))
                return true;
        return false;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        //num of nodes
        System.out.print("n = ");
        int n = scanner.nextInt();
        //num of edges
        System.out.print("e = ");
        int e = scanner.nextInt();
        DirectedGraph g = new DirectedGraph(n);
        for (int i = 0; i < e; i++) {
            int p = scanner.nextInt();
            scanner.next("->");
            int q = scanner.nextInt();
            g.addEdge(p, q);
        }
        String result = (g.isCyclic() == true) ? "YES" : "NO";
        System.out.println(result);
    }
}