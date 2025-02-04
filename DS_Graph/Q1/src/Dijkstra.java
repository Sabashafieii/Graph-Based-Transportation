import java.lang.*;
import java.util.Scanner;

class Dijkstra {
    int N;

    public Dijkstra(int n) {
        N = n;
    }

    int minDistance(int dist[], Boolean sptSet[])
    {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < N; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }

    void dijkstra(int graph[][], int src, int des)
    {
        int dist[] = new int[N];
        Boolean sptSet[] = new Boolean[N];
        int path[] = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
            path[i] = 0;
        }
        dist[src] = 0;
        for (int count = 0; count < N - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < N; v++)
                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]){
                    dist[v] = dist[u] + graph[u][v];
                    path[v] = u;
                }
        }
        //print
        System.out.println((src + 1) + " to " + (des + 1) + " = " + dist[des]);
        System.out.print("path: ");
        int temp = des;
        while (temp != path[temp] && path[temp] != 0){
            System.out.print((temp + 1) + " <- ");
            temp = path[temp];
        }
        System.out.println((src + 1));
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        //num of nodes
        System.out.println("enter num of nodes: ");
        int n = scanner.nextInt();
        //num of edges
        System.out.println("enter num of edges: ");
        int m = scanner.nextInt();
        System.out.println("enter edges: (example: 1 2 3 that 1 and 2 are nodes and 3 is weight)");
        int graph[][] = new int[n][];
        for (int i = 0; i < n; i++) {
            graph[i] = new int[n];
            for (int j = 0; j < n; j++) {
                graph[i][j] = 0;
            }
        }
        for (int i = 0; i < m; i++) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            int w = scanner.nextInt();
            graph[p - 1][q - 1] = w;
            graph[q - 1][p - 1] = w;
        }
        Dijkstra d = new Dijkstra(n);
        System.out.println("enter source and destination: ");
        while (true) {
            int src = scanner.nextInt();
            int des = scanner.nextInt();
            d.dijkstra(graph, src - 1, des - 1);
        }
    }
}