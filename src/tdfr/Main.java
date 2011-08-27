package tdfr;

import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import processing.core.*;
import processing.opengl.*;
import java.util.Vector;
import java.util.Set;
import java.util.Random;
import java.util.ListIterator;

public class Main extends PApplet {

	AbstractGraph<Node, DefaultEdge> graph;
	
	private static final long serialVersionUID = 1L;
	
	public void setupGraph() {
		graph = new SimpleGraph<Node, DefaultEdge>(DefaultEdge.class);
		Random rand = new Random();
		for (int i =0; i <10; i++) {
		Node node1 = new Node(rand.nextFloat()*400, rand.nextFloat()*400, rand.nextFloat()*400);
		graph.addVertex(node1);
		}

		Set<Node> nodes = graph.vertexSet();
		Object[] nodesA = nodes.toArray();
		for (int i = 0; i< 10; i++) {
			graph.addEdge((Node) nodesA[rand.nextInt(10)], (Node) nodesA[rand.nextInt(10)]);
		}

		
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
