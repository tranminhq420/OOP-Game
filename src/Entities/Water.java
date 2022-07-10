package Entities;
import java.awt.Image;

public class Water extends StaticEntity{
	private boolean collision = false;
	public Water(GameObject graphics,String name, boolean collision) {
		super(graphics,name,collision);

	}
	public boolean getCollision() {
		return collision;
	}
}
