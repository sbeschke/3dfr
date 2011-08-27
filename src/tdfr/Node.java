package tdfr;

public class Node {
	private float x = 0;
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
	}
	
	
}
