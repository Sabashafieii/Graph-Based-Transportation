import java.util.*;

class UndirectedGraph
{
    int V;
    LinkedList<Integer> graph[];

    UndirectedGraph(int v)
    {
        V = v;
        graph = new LinkedList[v];
        for(int i=0; i<v; ++i)
            graph[i] = new LinkedList();
    }

    void addEdge(int p,int q)
    {
        graph[p].add(q);
        graph[q].add(p);
    }

    Boolean isCyclicUtil(int v, int parent, Boolean visited[])
    {
        Iterator<Integer> it = graph[v].iterator();
        int index;
        visited[v] = true;
        while (it.hasNext())
        {
            index = it.next();
            if (!visited[index])
            {
                if (isCyclicUtil(index, v, visited))
                    return true;
            }
            else if (index != parent)
                return true;
        }
        return false;
    }
    Boolean isCyclic()
    {
        Boolean visited[] = new Boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;
        for (int i = 0; i < V; i++)
            if (!visited[i])
                if (isCyclicUtil(i, -1, visited))
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
        UndirectedGraph g = new UndirectedGraph(n);
        for (int i = 0; i < e; i++) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            g.addEdge(p, q);
        }
        String result = (g.isCyclic() == true) ? "YES" : "NO";
        System.out.println(result);
    }
}
