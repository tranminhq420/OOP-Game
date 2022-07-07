package Entities;

import java.awt.Image;

public class Earth extends GameObject {
	private boolean collision = false;
	public Earth(int x, int y, int width,int height, Image image) {
		super(x,y,width, height, image);
	}
	public boolean getCollision() {
		return collision;
	}
}
