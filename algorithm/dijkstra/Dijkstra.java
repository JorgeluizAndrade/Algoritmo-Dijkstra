package algorithm.dijkstra;

public class Dijkstra {
	private int V;
	private int[][] graph;

	public Dijkstra(int vet) {
		// TODO Auto-generated constructor stub
		V = vet;
		graph = new int[V][V];
	}

	private int minimumDistance(int[] distances, boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int minIdex = -1;

		for (int i = 0; i < V; i++) {
			if (!visited[i] && distances[i] <= min) {
				min = distances[i];
				minIdex = i;
			}
		}
		return minIdex;
	}

	private void printPath(int distances[], int parent[], int source, int destination) {
		System.out.println("Caminho mínimo entre " + source + " e " + destination + ": ");
		int crawl = destination;

		System.out.println(crawl);

		while (parent[crawl] != -1) {
			System.out.println(" <- " + parent[crawl]);
			crawl = parent[crawl];
		}
		System.out.println("\nCusto total: " + distances[destination]);
	}

	public void dijkstra(int source, int destination) {
		int[] distances = new int[V];

		boolean[] visited = new boolean[V];

		int[] parent = new int[V];

		for (int i = 0; i < V; i++) {
			distances[i] = Integer.MAX_VALUE;
			visited[i] = false;
			parent[i] = -1;
		}

		distances[source] = 0;

		for (int count = 0; count < V - 1; count++) {
			int u = minimumDistance(distances, visited);
			visited[u] = true;

			for (int v = 0; v < V; v++) {
				if (!visited[v] && graph[u][v] != 0 && distances[u] != Integer.MAX_VALUE
						&& distances[u] + graph[u][v] < distances[v]) {
					distances[v] = distances[u] + graph[u][v];
					parent[v] = u;
				}
			}
		}
		printPath(distances, parent, source, destination);
	}

	public static void main(String[] args) {
		int vertices = 4;
		int[][] graph = { { 0, 50, 75, 0 }, // Custos entre Facebook (A) e outros canais
				{ 50, 0, 30, 60 }, // Custos entre Google Ads (B) e outros canais
				{ 75, 30, 0, 80 }, // Custos entre Instagram (C) e outros canais
				{ 0, 60, 80, 0 } }; // Custos entre X(Twitter) (D) e outros canais

		int source = 0; // (A) FACEBOOK
		int destination = 3; // (D) X(Twitter)

		Dijkstra dijkstra = new Dijkstra(vertices);
		dijkstra.graph = graph;

		dijkstra.dijkstra(source, destination);
	}
}
