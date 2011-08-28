package tdfr;

import java.util.Vector;
import javax.vecmath.Tuple3d;
import javax.vecmath.Point3d;

public class Node {
	private float x = 0;
	private Point3d velocity = new Point3d(0,0,0);
	
	private Vector<Edge> edges;
	
	public void addToVelocity(Point3d hook) {
		velocity.add((Tuple3d)hook);
	}

	
	public Point3d getVelocity() {
		return velocity;
	}

	public void adaptPositions() {
		x += velocity.x;
		y += velocity.y;
		z += velocity.z;
	}
	
	public Point3d getCoordinates() {
		return new Point3d(x,y,z);
	}
	public void slowDown(float damping)
	{
		velocity.scale(damping);
	}
	
	public void setVelocity(Point3d velocity) {
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
	
	public Node() {
	
	}
	
	public Node(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		}
	
	
}
