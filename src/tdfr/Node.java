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

	public Node(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
}
