package Entities;

import Entities.GameObjectDynamic.Direction;

public class Skillshot extends Bullet {
	private final int bullet_speed = 10;
	private final int bullet_length = 600;
	private int useCost = 1;
	
	public Skillshot(int x, int y) {
    	super(x,y);
    	loadImage("res/textures/img/laser_attack.png");
    	getImageDimension();
    }
    public void move() {
    	if (getObjectDricetion() == Direction.RIGHT ) x += bullet_speed; // viên đạn bay ngang
        if (getObjectDricetion() == Direction.UP) y -= bullet_speed; // viên đạn bay d�?c 
        if (getObjectDricetion() == Direction.LEFT ) x -= bullet_speed;
        if (getObjectDricetion() == Direction.DOWN) y += bullet_speed;
        fly += bullet_speed; // so sánh độ dài đã bay với độ dài đạn
        if (fly>bullet_length) {
        	setExist(false);
        }
        if(x<0||x>600||y<0||y>600) {
        	setExist(false);
        }
    }
    public int getUseCost() {
    	return useCost;
    }
}