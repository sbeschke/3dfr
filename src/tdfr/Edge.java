package tdfr;

import org.jgrapht.graph.DefaultEdge;
import javax.vecmath.Point3d;

public class Edge extends DefaultEdge {

	public float getLength() {
		float length= 0;
		Point3d n1 = ((Node)getSource()).getCoordinates();
		Point3d n2 = ((Node)getTarget()).getCoordinates();
		length = (float) n1.distance(n2);
		return length;
	}
	
}
