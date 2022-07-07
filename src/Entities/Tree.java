package Entities;

import java.awt.Image;

public class Tree extends GameObject {
	private boolean collision = true;
	public Tree(int x, int y, int width, int height, Image image) {
		super(x, y, width, height, image);
	}
	public boolean getCollision() {
		return collision;
	}
}
