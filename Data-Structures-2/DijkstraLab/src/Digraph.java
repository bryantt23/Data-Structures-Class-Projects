import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Digraph {
	private static List<Vertex> nodes;
	private static List<Edge> edges;

	private Set<Vertex> visitedNodes;
	private Set<Vertex> notVisitedNodes;

	private Map<Vertex, Vertex> predecessors;
	private Map<Vertex, Integer> distance;

	// array copy to use for functions
	public Digraph(Graph graph) {
		this.nodes = new ArrayList<Vertex>(graph.getVertexes());
		this.edges = new ArrayList<Edge>(graph.getEdges());
	}

	public void dijkstra(Vertex source) {
		// track nodes visited
		visitedNodes = new HashSet<Vertex>();
		notVisitedNodes = new HashSet<Vertex>();

		// track distance
		distance = new HashMap<Vertex, Integer>();
		predecessors = new HashMap<Vertex, Vertex>();

		// start distance at 0
		distance.put(source, 0);

		notVisitedNodes.add(source);

		// loop while all nodes have not been visited
		while (notVisitedNodes.size() > 0) {

			// get minimum node and add
			Vertex node = getMinimum(notVisitedNodes);
			visitedNodes.add(node);
			notVisitedNodes.remove(node);

			// call method
			findSmallestDistances(node);
		}
	}

	private void findSmallestDistances(Vertex node) {

		// compare distances of neighbor nodes
		List<Vertex> adjacentNodes = getNeighbors(node);
		for (Vertex destination : adjacentNodes) {

			// loop through & save shortest distance
			if (getShortestDistance(destination) > getShortestDistance(node)
					+ getDistance(node, destination)) {
				distance.put(destination, getShortestDistance(node)
						+ getDistance(node, destination));
				predecessors.put(destination, node);
				notVisitedNodes.add(destination);
			}
		}
	}

	// get distance between nodes
	private int getDistance(Vertex node, Vertex destination) {

		// use edge information
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
					&& edge.getDestination().equals(destination)) {
				return edge.getWeight();
			}
		}
		// to help debug
		throw new RuntimeException("Error");
	}

	// use list to store neighbor information
	private List<Vertex> getNeighbors(Vertex node) {
		List<Vertex> neighbors = new ArrayList<Vertex>();

		// get neighbor information from edges
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
					&& !isVisited(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	// get minimum of vertices
	private Vertex getMinimum(Set<Vertex> vertices) {
		Vertex minimum = null;
		for (Vertex vertex : vertices) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	// mark nodes as visited
	private boolean isVisited(Vertex vertex) {
		return visitedNodes.contains(vertex);
	}

	// create large value if node cannot be visited
	private int getShortestDistance(Vertex destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	// path from source to destination
	public LinkedList<Vertex> getPath(Vertex destination) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		Vertex step = destination;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// put it into the correct order
		Collections.reverse(path);
		return path;
	}

	public static void main(String[] args) {

		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();

		String[] airports = new String[20];
		String[] airportsC = new String[20];

		for (int i = 0; i < 20; i++) {
			airports[i] = "Node_" + i;
		}

		// airport names
		String[] airports2 = { "Los Angeles", "San Francisco", "Denver",
				"Chicago", "Boston", "New York", "Miami", "New Orlean",
				"Seatle" };

		// airport codes
		String[] airportCode = { "LAX", "SFO", "DFW", "ORD", "BOS", "JFK",
				"MIA", "MSY", "SEA" };

		// loops to store information
		for (int i = 0; i < airports2.length; i++) {
			airports[i] = airports2[i];
		}

		for (int i = 0; i < airports2.length; i++) {
			airportsC[i] = airportCode[i];
		}

		for (int i = 0; i < 15; i++) {
			Vertex location = new Vertex("Node_" + i, airports[i], airportsC[i]);
			nodes.add(location);
		}

		// add edge information
		addEdges("Edge_0", 0, 8, 300.00);
		addEdges("Edge_1", 0, 2, 159.00);
		addEdges("Edge_2", 1, 0, 79.00);
		addEdges("Edge_3", 2, 0, 199.00);
		addEdges("Edge_4", 2, 1, 99.99);
		addEdges("Edge_5", 3, 2, 50.00);
		addEdges("Edge_6", 3, 4, 179.00);
		addEdges("Edge_7", 4, 3, 149.00);
		addEdges("Edge_8", 4, 5, 99.00);
		addEdges("Edge_9", 5, 3, 99.00);
		addEdges("Edge_10", 5, 6, 49.00);
		addEdges("Edge_11", 5, 7, 220.00);
		addEdges("Edge_12", 6, 7, 50.00);
		addEdges("Edge_13", 7, 0, 190.00);
		addEdges("Edge_14", 7, 2, 109.00);
		addEdges("Edge_15", 8, 3, 79.50);

		// Cheapest flight from LAX to JFK
		System.out.println();
		System.out.println("Cheapest roundtrip flight from LAX to JFK");
		System.out.println("Cheapest flight from LAX to JFK");
		Graph graph = new Graph(nodes, edges);
		Digraph dijkstra = new Digraph(graph);
		dijkstra.dijkstra(nodes.get(0));
		LinkedList<Vertex> path = dijkstra.getPath(nodes.get(5));

		for (Vertex vertex : path) {
			System.out.println(vertex);
		}

		// Cheapest flight from JFK to LAX
		System.out.println();
		System.out.println("Cheapest flight from JFK to LAX");
		dijkstra.dijkstra(nodes.get(5));
		LinkedList<Vertex> path2 = dijkstra.getPath(nodes.get(0));

		for (Vertex vertex : path2) {
			System.out.println(vertex);
		}
	}

	private static void addEdges(String edgesId, int sourceLocNo,
			int destLocNo, double duration) {
		Edge edgesl = new Edge(edgesId, nodes.get(sourceLocNo),
				nodes.get(destLocNo), (int) duration);
		edges.add(edgesl);
	}
}