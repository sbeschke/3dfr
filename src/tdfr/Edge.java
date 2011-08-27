package tdfr;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge {

	public float getLength() {
		float length= 0;
		float x = ((Node)getSource()).getX()- ((Node)getTarget()).getX();
		float y = ((Node)getSource()).getY()- ((Node)getTarget()).getY();
		float z = ((Node)getSource()).getZ()- ((Node)getTarget()).getZ();
		length = (float) Math.sqrt(  Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
		return length;
	}
	
}
