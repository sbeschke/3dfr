package tdfr.importer;

import tdfr.Node;
import tdfr.graph.Graph;

public class JsonImporter {
	public Graph loadFile(String fileName) {
		Graph graph = new Graph();
		
		Node node1 = new Node(10, 10);
		Node node2 = new Node(30, 10);
		Node node3 = new Node(10, 30);
		graph.addVertex(node1);
		graph.addVertex(node2);
		graph.addVertex(node3);
		graph.addEdge(node1, node2);
		graph.addEdge(node1, node3);

		
		return graph;
	}
}
