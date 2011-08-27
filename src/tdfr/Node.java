package tdfr;

import java.util.Vector;
import javax.vecmath.Point3d;

public class Node {
	private float x = 0;
	private float velocity = 0;
	
	private Vector<Edge> edges;
	
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	public Vector<Edge> Edges() {
		return edges;
	}
	
	public float getVelocity() {
		return velocity;
	}
	
	public void adaptPositions() {
		x += velocity;
		y += velocity;
		z += velocity;
	}
	
	public Point3d getCoordinates() {
		return new Point3d(x,y,z);
	}
	
	public void setVelocity(float velocity) {
		this.velocity= velocity;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	private float y = 0;
	
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	private float z = 0;
	public void setZ(float z) {
		this.z = z;
	}

	public float getZ() {
		return z;
	}
	public Node(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.velocity = 0;
		edges = new Vector<Edge>();
	}
	
	
}
