package Entities;

import java.awt.Image;

public class Water extends GameObject {
	
	private boolean collision = true;
	public Water(int x, int y, int width,int height, Image image) {
		super(x,y,width, height, image);
	}
	public boolean getCollision() {
		return collision;
	}
}
