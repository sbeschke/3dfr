package tdfr;

import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import processing.core.*;
import processing.opengl.*;
import java.util.Vector;
import java.util.Random;
import java.util.ListIterator;

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
	
	public void draw() 
	{
		/*background(0);
		fill(255);
		lights();
		//noStroke();
		strokeWeight(0);
		ListIterator iter = nodes.listIterator();
		while (iter.hasNext()) {
			Node node = (Node) iter.next();
			translate(node.x, node.y, node.z);
			sphere(50+value);
			translate(-node.x,-node.y,-node.z);
		}
	  //rect(25, 25, 50+value, 50+value);
		stroke(255);
		iter = edges.listIterator();
		strokeWeight(20);
		while (iter.hasNext()) 
		{
			Edge edge = (Edge) iter.next();
			line(edge.start.x, edge.start.y, edge.start.z, edge.end.x, edge.end.y, edge.end.z);*/

			
		background(0);
		
		stroke(255);
		strokeWeight(2);
		
		for(DefaultEdge edge : graph.edgeSet()) {
			Node src = graph.getEdgeSource(edge);
			Node tgt = graph.getEdgeTarget(edge);
			
			line(src.getX(), src.getY(), 0, tgt.getX(), tgt.getY(), 0);
			
		}
		//strokeWeight(0);
	}

	/*public void mouseDragged() 
	{
	  value = value + 5;
	  if (value > 200) {
	    value = 0;
	  }
	}*/
	
	
	
}
