package Entities;

import Entities.GameObjectDynamic.Direction;

public class Fireball extends Bullet {
	private final int bullet_speed = 5;
	private final int bullet_length = 600;
	private int useCost = 1;
	
	public Fireball(GameObjectDynamic bulletGP) {
        super(bulletGP);
    	bulletGP.loadImage("res/textures/img/dan2.p"
    			+ "ng");
        bulletGP.getImageDimension();
    }
    public void move() {
    	if (bulletGP.getObjectDricetion() == Direction.RIGHT ) bulletGP.x += bullet_speed; // viên đạn bay ngang
        if (bulletGP.getObjectDricetion() == Direction.UP) bulletGP.y -= bullet_speed; // viên đạn bay d�?c 
        if (bulletGP.getObjectDricetion() == Direction.LEFT ) bulletGP.x -= bullet_speed;
        if (bulletGP.getObjectDricetion() == Direction.DOWN) bulletGP.y += bullet_speed;
        fly += bullet_speed; // so sánh độ dài đã bay với độ dài đạn
        if (fly>bullet_length) {
        	bulletGP.setExist(false);
        }
        if(bulletGP.x<0||bulletGP.x>550||bulletGP.y<0||bulletGP.y>550) {
        	bulletGP.setExist(false);
        }
    }
    public int getUseCost() {
    	return useCost;
    }
}