package tdfr;

import processing.core.*;

public class Main extends PApplet {
	
	private static final long serialVersionUID = 1L;

	public void setup() {
		size(200,200, OPENGL);
		background(0);
	}

	public void draw() {
		stroke(255);
		if (mousePressed) {
			line(mouseX,mouseY,pmouseX,pmouseY);
		}
	}

}
