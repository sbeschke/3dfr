package tdfr.graph;

import org.jgrapht.graph.DefaultEdge;

public class Graph extends org.jgrapht.graph.SimpleGraph<tdfr.Node, DefaultEdge> {

	private static final long serialVersionUID = 1L;

	public Graph() {
		super(DefaultEdge.class);
	}
	
}
