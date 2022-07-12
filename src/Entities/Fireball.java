package Entities;

import Entities.GameObjectDynamic.Direction;


// public class Fireball extends Bullet{
// 	public Fireball(int x, int y) {
// 		super(x, y);
// 		loadImage("res/textures/img/fireball.png");
// 		getImageDimension();
// 	}

public class Fireball extends Bullet {
	public Fireball(GameObjectDynamic bulletGP) {
        super(bulletGP);
    	bulletGP.loadImage("res/textures/img/fireball.png");
        bulletGP.getImageDimension();
		setBullet_speed(2);
		setBullet_length(300);
		setUseCost(0);
		setFly(0);
    }

    public void move() {
    	if (bulletGP.getObjectDricetion() == Direction.RIGHT ) bulletGP.x += getBullet_speed(); // viên đạn bay ngang
        if (bulletGP.getObjectDricetion() == Direction.UP) bulletGP.y -= getBullet_speed(); // viên đạn bay d�?c 
        if (bulletGP.getObjectDricetion() == Direction.LEFT ) bulletGP.x -= getBullet_speed();
        if (bulletGP.getObjectDricetion() == Direction.DOWN) bulletGP.y += getBullet_speed();
        setFly(getFly()+ getBullet_speed()); // so sánh độ dài đã bay với độ dài đạn
        if (getFly() > getBullet_length()) {
        	bulletGP.setExist(false);
        }
        if(bulletGP.x<0||bulletGP.x>550||bulletGP.y<0||bulletGP.y>550) {
        	bulletGP.setExist(false);
        }
    }
}