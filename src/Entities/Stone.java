package Entities;

public class Stone extends Bullet{
	private int bullet_speed = 3;
	public Stone(int x, int y) {
		super(x, y);
		loadImage("res/textures/img/dan2.png");
		getImageDimension();
	}
	@Override
	
    public void move() {
    	if (getDirect() == 1) x += bullet_speed; // viên đạn bay ngang
        if (getDirect() == 2) y -= bullet_speed; // viên đạn bay d�?c 
        if (getDirect() ==-1) x -= bullet_speed;
        if (getDirect() ==-2) y += bullet_speed;
        fly += bullet_speed; // so sánh độ dài đã bay với độ dài đạn
        if (fly>bullet_length) {
        	setTontai(false);
        }
        if(x<0||x>550||y<0||y>550) {
        	setTontai(false);
        }
    }

}
