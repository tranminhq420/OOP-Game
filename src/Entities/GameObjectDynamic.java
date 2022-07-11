package Entities;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

public class GameObjectDynamic extends GameObject {
	
	protected boolean exist; // còn sống 2
	public enum Direction{
		UP,DOWN,RIGHT,LEFT
	}
	private Direction ObjectDricetion; // đánh dấu hướng di chuyển -1 1

	public GameObjectDynamic(int x, int y) {
		super(x, y);
		setExist(true);
		// TODO Auto-generated constructor stub
	}



	public boolean getExist() {
		return exist;
	}

	public void setExist(boolean x) {
		this.exist = x;
	}

	public Rectangle getBounds() { // khung bao quanh hình ảnh --> va chạm
		return new Rectangle(x, y, width, height);
	}

	public Direction getObjectDricetion() {
		return ObjectDricetion;
	}



	public void setObjectDricetion(Direction objectDricetion) {
		ObjectDricetion = objectDricetion;
	};
}
