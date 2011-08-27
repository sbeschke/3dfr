package tdfr;

import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import processing.core.*;
import processing.opengl.*;
import tdfr.graph.Graph;
import tdfr.importer.JsonImporter;

import javax.vecmath.Point3d;
import javax.vecmath.Tuple3d;
import java.util.Vector;
import java.util.Set;
import java.util.Random;
import java.util.ListIterator;
//http://mirrors.ibiblio.org/pub/mirrors/maven/java3d/jars/vecmath-1.3.1.jar

public class Main extends PApplet {

	Graph graph;
		
	public void setupGraph() {
		graph = (new JsonImporter()).loadFile("../data/sotu_small.json");		
	}
	
	private float COLUMBFORCECONSTANT=1;
	private float FEDERCONSTANT=1;
	
	float coulumb_force(Node i, Node j)
	{
		float distance = (float) i.getCoordinates().distance(j.getCoordinates());
		return this.COLUMBFORCECONSTANT*distance;
	}
	
	float hook_force(Edge edge)
	{
		return FEDERCONSTANT*edge.getLength();
	}
	
	void updateForces() {
		Set<Node> nodes; 
		Object[] nodesA;
		//Set<Edge> edges = graph.edgeSet();
		Point3d totalkineticenergy = new Point3d( 0, 0, 0);
		
		//damping \in 0;1
		float damping = 1;
		//TODO set better value 
		while (totalkineticenergy.x < 100) {
			nodes = graph.vertexSet();
			nodesA = nodes.toArray();
			
			for (Node n: nodes) {
				for (Edge e: graph.edgesOf(n)) 
				{
					float hook = hook_force(e);
					n.addToVelocity(new Point3d(hook, hook, hook));
				}
			}
			
			for (int i = 0; i< nodesA.length; i++) {
				Node node = (Node) nodesA[i];
				//nodes.remove(nodesA[i]);
				float netforce= 0;
				for (Node onode: nodes) {
					netforce = coulumb_force(onode, node);
				}			
				
				netforce *= damping;
				
				node.addToVelocity(new Point3d(netforce, netforce, netforce));
			
				node.adaptPositions();
				
				//FIXME
				totalkineticenergy.scaleAdd((double) 0.000001, node.getCoordinates());
				System.out.println("working: " + totalkineticenergy);
				//nodes.add((Node)nodesA[i]);
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
		for(Edge edge : graph.edgeSet()) {
			Node src = graph.getEdgeSource(edge);
			Node tgt = graph.getEdgeTarget(edge);
			
			line(src.getX(), src.getY(), 0, tgt.getX(), tgt.getY(), 0);
			
		}
		updateForces();

	}

	
		
}
