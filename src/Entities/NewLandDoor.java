package Entities;

import java.awt.Image;

public class NewLandDoor extends GameObject {
	private boolean collision = false;
	public NewLandDoor(int x, int y, int width,int height, Image image) {
		super(x,y,width, height, image);
	}
	public void anotherLand() {
		
	}
	public boolean getCollision() {
		return collision;
	}
}
