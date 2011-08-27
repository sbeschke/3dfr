package tdfr;

import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import controlP5.ControlP5;

import processing.core.*;
import processing.opengl.*;
import tdfr.graph.Graph;
import tdfr.importer.JsonImporter;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import javax.vecmath.Point3d;
import javax.vecmath.Tuple3d;
import java.util.Vector;
import java.util.Set;
import java.util.Random;
import java.util.ListIterator;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.vecmath.Point3d;
//http://mirrors.ibiblio.org/pub/mirrors/maven/java3d/jars/vecmath-1.3.1.jar

public class Main extends PApplet {

	private Graph graph;
	private ControlP5 controlP5;
		
	public static void main(String[] args) {
		PApplet.main(new String[] { "--present", "tdfr.Main" });
	}
	
	public void setupGraph() {
		//graph = (new JsonImporter()).loadFile("data/sotu_small.json");
		graph = new Graph();
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
		
		try { 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		} catch (Exception e) { 
			e.printStackTrace();  
		}
		
		controlP5 = new ControlP5(this);
		controlP5.addButton("loadButton");
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
		//updateForces();

	}

	/*public void mouseDragged() 
	{
	  value = value + 5;
	  if (value > 200) {
	    value = 0;
	  }
	}*/
	
	public void loadButton(int theValue) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				noLoop();
				
				FileDialog fc = new FileDialog((Frame)null);
				fc.setVisible(true);
				File file = new File(fc.getDirectory(), fc.getFile());
				(new JsonImporter()).loadFile(file, graph);
				
				loop();
			}
		});
	}
	
		
}
