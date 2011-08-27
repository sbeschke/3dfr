package tdfr;

import processing.core.*;
import processing.opengl.*;
import java.util.Vector;
import java.util.Random;
import java.util.ListIterator;

public class Main extends PApplet {
	
	class Node {
		public float x,y,z;
		
		public Node(float x, float y,float z)
		{
		this.x = x;
		this.y = y;
		this.z = z;
		Print();
		}
		
		public void Print()
		{
			System.out.println("("+x +","+ y+"," + z+")");
		}
	}
	
	class Edge {
		public Node start, end;
		
		public Edge(Node start, Node end)
		{
			this.start = start;
			this.end = end;
		}
		
		
	}
	
	Vector<Node> nodes;
	Vector<Edge> edges;
	
	public void setup() {
		size(400,400, OPENGL);
		background(0);
		SetupGraph();
	}
	
	int value = 0;

	public void draw() 
	{
		background(0);
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
			line(edge.start.x, edge.start.y, edge.start.z, edge.end.x, edge.end.y, edge.end.z);
		}
		strokeWeight(0);
	}

	public void mouseDragged() 
	{
	  value = value + 5;
	  if (value > 200) {
	    value = 0;
	  }
	}
	
	private void SetupGraph()
	{
		Random rand = new Random();
		nodes = new Vector<Node>();
		for (int i=0; i < 10; i++ )
		{
			nodes.add(new Node(rand.nextFloat()*400, rand.nextFloat()*400, -1*rand.nextFloat()*400));
		}
		
		edges = new Vector<Edge>();
		for (int i =0; i < 10; i++)
		{
			edges. add(new Edge(nodes.elementAt(rand.nextInt(10)), nodes.elementAt(rand.nextInt(10))));
		}
	}
	
}
