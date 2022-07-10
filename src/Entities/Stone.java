package Entities;

public class Stone extends Bullet{
	public Stone(int x, int y) {
		super(x, y);
		loadImage("res/textures/img/dan2.png");
		getImageDimension();
	}
	@Override
	
    public void move() {
    	if (getObjectDricetion() == Direction.RIGHT ) x += bullet_speed; // viên đạn bay ngang
        if (getObjectDricetion() == Direction.UP) y -= bullet_speed; // viên đạn bay d�?c 
        if (getObjectDricetion() == Direction.LEFT ) x -= bullet_speed;
        if (getObjectDricetion() == Direction.DOWN) y += bullet_speed;
        fly += bullet_speed; // so sánh độ dài đã bay với độ dài đạn
        if (fly>bullet_length) {
        	setTontai(false);
        }
        if(x<0||x>550||y<0||y>550) {
        	setTontai(false);
        }
    }

}
