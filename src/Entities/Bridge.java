package Entities;

import java.awt.Image;

public class Bridge extends GameObject{
	private boolean collision = false;
	public Bridge(int x, int y, int width,int height, Image image) {
		super(x,y,width, height, image);
	}
	public boolean getCollision() {
		return collision;
	}
}
