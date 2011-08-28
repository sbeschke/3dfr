package tdfr;

import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import controlP5.ControlP5;

import processing.core.*;
import processing.opengl.*;
import remixlab.proscene.Scene;
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
	private Scene scene;
		
	public static void main(String[] args) {
		PApplet.main(new String[] { "tdfr.Main" });
	}
	

	private double COLUMBFORCECONSTANT=1000d;
	private float FEDERCONSTANT=0.02f;
	
	Point3d coulumb_force(Node i, Node j)
	{
		if(i == j) {
			return new Point3d();
		}
		Point3d distanceVec = new Point3d(i.getCoordinates());
		distanceVec.sub(j.getCoordinates());
		distanceVec.negate();
		//Point3d distance = new Point3d(i.getCoordinates());
		double dist = distanceVec.distance(new Point3d());
		if(dist < 1) {
			dist = 1;
		}
		//distance.distance(j.getCoordinates());
		//System.out.println(dist+" "+distanceVec.toString());
		distanceVec.scale(1/(dist*dist*dist));
		//System.out.println(i.getCoordinates().toString()+j.getCoordinates().toString()+" "+dist*dist*dist+" "+distanceVec.toString());
		distanceVec.scale(COLUMBFORCECONSTANT);

		return distanceVec;
		//return new Point3d();
	}
	
	Point3d hook_force(Edge edge)
	{
		Point3d direction = edge.getVecLength();
		
		direction.scale(FEDERCONSTANT);
		return direction;
	}

	
	void updateForces() {
		Set<Node> nodes; 
		Object[] nodesA;
		Set<Edge> edges = graph.edgeSet();
		Point3d totalkineticenergy = new Point3d( 0, 0, 0);
		//damping \in 0;1
		float damping = 0.1f;

		//TODO set better value 
		while (totalkineticenergy.x < 100) {

			nodes = graph.vertexSet();
			nodesA = nodes.toArray();

			
			for (Edge edge : graph.edgeSet()) {
				Point3d hook = hook_force(edge);
				graph.getEdgeTarget(edge).addToVelocity(hook);
				hook.negate();
				graph.getEdgeSource(edge).addToVelocity(hook);
				//System.out.println(graph.getEdgeTarget(edge).getCoordinates());

			}
			
			totalkineticenergy.x =1001;
			for (int i = 0; i< nodesA.length; i++) {
				Node node = (Node) nodesA[i];
				
				Point3d netforce= new Point3d(0,0,0);
				for (Node onode: nodes) {
					netforce.add(coulumb_force(onode, node));
				}			
				node.addToVelocity(netforce);
				
				node.slowDown(damping);
				node.adaptPositions();
//				
//				//FIXME
//				totalkineticenergy.scaleAdd((double) 0.000001, node.getCoordinates());
//				System.out.println("working: " + totalkineticenergy);
//				//nodes.add((Node)nodesA[i]);

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
		graph = new Graph();
		scene = new GraphScene(this, graph);
		
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

		
		updateForces();

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
