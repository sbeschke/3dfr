package tdfr.base.graph;


public class Graph extends org.jgrapht.graph.DirectedMultigraph<tdfr.base.graph.Node, tdfr.base.graph.Edge> {

	private static final long serialVersionUID = 1L;

	public Graph() {
		super(tdfr.base.graph.Edge.class);
	}
	
}
