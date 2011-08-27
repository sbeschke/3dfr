package tdfr;

import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import processing.core.*;
import processing.opengl.*;
import tdfr.importer.JsonImporter;

import java.util.Vector;
import java.util.Set;
import java.util.Random;
import java.util.ListIterator;
import javax.vecmath.Point3d;
//http://mirrors.ibiblio.org/pub/mirrors/maven/java3d/jars/vecmath-1.3.1.jar

public class Main extends PApplet {

	AbstractGraph<Node, DefaultEdge> graph;
	
	private static final long serialVersionUID = 1L;
	
	public void setupGraph() {
		graph = (new JsonImporter()).loadFile("data/jsonex.json");		

		/*graph = new SimpleGraph<Node, DefaultEdge>(DefaultEdge.class);
		Random rand = new Random();
		for (int i =0; i <10; i++) {
		Node node1 = new Node(rand.nextFloat()*400, rand.nextFloat()*400, rand.nextFloat()*400);
		graph.addVertex(node1);
		}

		Set<Node> nodes = graph.vertexSet();
		Object[] nodesA = nodes.toArray();
		for (int i = 0; i< 10; i++) {
			graph.addEdge((Node) nodesA[rand.nextInt(10)], (Node) nodesA[rand.nextInt(10)]);
		}*/

	}
	
	private float COLUMBFORCECONSTANT=1;
	
	float coulumb_force(Node i, Node j)
	{
		float dx = i.getX()-j.getX();
		float dy = i.getY()-j.getY();
		//float dz = i.getZ()-j.getZ();
		return this.COLUMBFORCECONSTANT*sqrt(dx*dx+dy*dy);//+dz*dz
	}
	
	Point3d hook_force(Node i, Edge edge)
	{
		float attraction = edge.getLength();
		Point3d direction = new Point3d (0,0,0);
		//Node direction = (edge.getTarget()).getCoordinates();
		//return direction*attraction;
		return direction;
	}
	
	void updateForces() {
		Set<Node> nodes; 
		Object[] nodesA;
		float totalkineticenergy = 0;
		float damping = 0;
		//TODO set better value 
		while (totalkineticenergy > 1) {
			nodes = graph.vertexSet();
			nodesA = nodes.toArray();
			for (int i = 0; i< nodesA.length; i++) {
				Node node = (Node) nodesA[i];
				nodes.remove(nodesA[i]);
				float netforce=0;
				for (Node onode: nodes) {
					netforce = coulumb_force(onode, node);
				}
				
				//hoook-power!!
				
				
				node.setVelocity( (node.getVelocity() + netforce)*damping);
				
				node.adaptPositions();
				
				totalkineticenergy += Math.pow(node.getVelocity(),2);
				
				nodes.add((Node)nodesA[i]);
			}
			
			
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
		updateForces();
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
