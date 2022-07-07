package Entities;

import java.awt.Image;

public class Wall extends GameObject {
	private boolean collision = true;
	public Wall(int x, int y, int width,int height, Image image) {
		super(x,y,width, height, image);
	}
	public boolean getCollision() {
		return collision;
	}
}
