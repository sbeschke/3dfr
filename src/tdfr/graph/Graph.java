package tdfr.graph;

import org.jgrapht.graph.DefaultEdge;

public class Graph extends org.jgrapht.graph.DirectedMultigraph<tdfr.Node, tdfr.Edge> {

	private static final long serialVersionUID = 1L;

	public Graph() {
		super(tdfr.Edge.class);
	}
	
}
