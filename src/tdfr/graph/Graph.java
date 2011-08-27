package tdfr.graph;

import tdfr.Edge;

public class Graph extends org.jgrapht.graph.DirectedMultigraph<tdfr.Node, tdfr.Edge> {

	private static final long serialVersionUID = 1L;

	public Graph() {
		super(Edge.class);
	}
	
}
