import java.util.*;

class SubGraph
{
    static int MAX = 100;
    int n;
    int []store;

    public SubGraph(int n) {
        this.n = n;
        store = new int[MAX];
    }

    boolean isClique(int b, int[][] graph)
    {
        for (int i = 1; i < b; i++)
            for (int j = i + 1; j < b; j++)
                if (graph[store[i]][store[j]] == 0)
                    return false;
        return true;
    }

    int maxSubGraph(int i, int l, int[][] graph)
    {
        int max = 0;
        for (int j = i + 1; j <= n; j++)
        {
            store[l] = j;
            if (isClique(l + 1, graph))
            {
                max = Math.max(max, l);
                max = Math.max(max, maxSubGraph(j, l + 1, graph));
            }
        }
        return max;
    }

    int maxSubGraph(int[][] graph){
        return maxSubGraph(0, 1, graph);
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        //num of nodes
        System.out.println("enter num of nodes: ");
        int n = scanner.nextInt();
        n++;
        //num of edges
        System.out.println("enter num of edges: ");
        int e = scanner.nextInt();
        System.out.println("enter edges: ");
        int graph[][] = new int[MAX][];
        for (int i = 0; i < MAX; i++) {
            graph[i] = new int[MAX];
            for (int j = 0; j < MAX; j++) {
                graph[i][j] = 0;
            }
        }
        for (int i = 0; i < e; i++) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            graph[p][q] = 1;
            graph[q][p] = 1;
        }
        SubGraph g = new SubGraph(n);
        System.out.println("length of maximum sub graph: " + g.maxSubGraph(graph));
    }
}
