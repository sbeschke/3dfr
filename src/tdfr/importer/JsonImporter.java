package tdfr.importer;

import java.util.Random;
import java.util.Set;

import tdfr.Node;
import tdfr.graph.Graph;

public class JsonImporter {
	public Graph loadFile(String fileName) {
		Graph graph = new Graph();
		
		Random rand = new Random();
		for (int i =0; i <10; i++) {
		Node node1 = new Node(rand.nextFloat()*400, rand.nextFloat()*400, rand.nextFloat()*400);
		graph.addVertex(node1);
		}

		Set<Node> nodes = graph.vertexSet();
		Object[] nodesA = nodes.toArray();
		for (int i = 0; i< 10; i++) {
			try {
				graph.addEdge((Node) nodesA[rand.nextInt(10)], (Node) nodesA[rand.nextInt(10)]);
			}
			catch(IllegalArgumentException x) {
				
			}
		}
		
		return graph;
	}
}
