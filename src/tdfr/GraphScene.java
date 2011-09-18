package tdfr;

import processing.core.PApplet;
import remixlab.proscene.Scene;
import tdfr.base.graph.Edge;
import tdfr.base.graph.Graph;
import tdfr.base.graph.Node;

public class GraphScene extends Scene {

	private Graph graph;
	
	public GraphScene(PApplet arg0, Graph g) {
		super(arg0);
		graph = g;
	}
	
	public void init() {
		setGridIsDrawn(false);
	}
	
	public void proscenium() {
		parent.background(0);		
		parent.stroke(255);
		parent.strokeWeight(2);
		for(Edge edge : graph.edgeSet()) {
			Node src = graph.getEdgeSource(edge);
			Node tgt = graph.getEdgeTarget(edge);
			
			parent.line(src.getX(), src.getY(), src.getZ(), tgt.getX(), tgt.getY(), tgt.getZ());
			
		}
	}

}
