package Entities;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

public class GameObjectDynamic extends GameObject {
	
	protected boolean tontai; // còn sống 2
	public enum Direction{
		UP,DOWN,RIGHT,LEFT
	}
	private Direction ObjectDricetion; // đánh dấu hướng di chuyển -1 1

	public GameObjectDynamic(int x, int y) {
		super(x, y);
		setTontai(true);
		// TODO Auto-generated constructor stub
	}



	public boolean getTontai() {
		return tontai;
	}

	public void setTontai(boolean x) {
		this.tontai = x;
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
