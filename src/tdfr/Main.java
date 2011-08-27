package tdfr;

import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import processing.core.*;

public class Main extends PApplet {
	
	AbstractGraph<Node, DefaultEdge> graph;
	
	private static final long serialVersionUID = 1L;
	
	public void setupGraph() {
		graph = new SimpleGraph<Node, DefaultEdge>(DefaultEdge.class);
		Node node1 = new Node(10, 10);
		Node node2 = new Node(30, 10);
		Node node3 = new Node(10, 30);
		graph.addVertex(node1);
		graph.addVertex(node2);
		graph.addVertex(node3);
		graph.addEdge(node1, node2);
		graph.addEdge(node1, node3);
		
	}

	public void setup() {
		
		size(400,400, OPENGL);
		background(0);
		setupGraph();
	}

	public void draw() {
		background(0);
		
		stroke(255);
		strokeWeight(2);
		
		for(DefaultEdge edge : graph.edgeSet()) {
			Node src = graph.getEdgeSource(edge);
			Node tgt = graph.getEdgeTarget(edge);
			
			line(src.getX(), src.getY(), 0, tgt.getX(), tgt.getY(), 0);
			
		}
	}

}
