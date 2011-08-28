package tdfr;

import org.jgrapht.graph.DefaultEdge;
import javax.vecmath.Point3d;
import javax.vecmath.Tuple3d;
import java.lang.Math;

public class Edge extends DefaultEdge {

	public float getLength() {
		float length= 0;
		Point3d n1 = ((Node)getSource()).getCoordinates();
		Point3d n2 = ((Node)getTarget()).getCoordinates();
		length = (float) n1.distance(n2);
		return length;
	}
	public Point3d getVecLength()
	{
		Point3d vecLength = new Point3d(((Node)getSource()).getCoordinates());
		vecLength.sub(((Node)getTarget()).getCoordinates());
		return vecLength;
	}
}
