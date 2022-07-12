package Entities;

import Entities.GameObjectDynamic.Direction;

public class Skillshot extends Bullet {
//	private final int bullet_speed = 10;
//	private final int bullet_length = 600;
//	private int useCost = 1;
	
	public Skillshot(GameObjectDynamic bulletGP) {
        super(bulletGP);
    	bulletGP.loadImage("res/textures/img/laser_attack.png");
        bulletGP.getImageDimension();
		setBullet_speed(10);
		setBullet_length(600);
		setUseCost(1);
		setFly(0);
    }

    public void move() {
    	if (bulletGP.getObjectDricetion() == Direction.RIGHT ) bulletGP.x += getBullet_speed(); // viên đạn bay ngang
        if (bulletGP.getObjectDricetion() == Direction.UP) bulletGP.y -= getBullet_speed(); // viên đạn bay d�?c 
        if (bulletGP.getObjectDricetion() == Direction.LEFT ) bulletGP.x -= getBullet_speed();
        if (bulletGP.getObjectDricetion() == Direction.DOWN) bulletGP.y += getBullet_speed();
        setFly(getFly() + getBullet_speed()  ); // so sánh độ dài đã bay với độ dài đạn
        if (getFly() > getBullet_length() ) {
        	bulletGP.setExist(false);
        }
        if(bulletGP.x<0||bulletGP.x>600||bulletGP.y<0||bulletGP.y>600) {
        	bulletGP.setExist(false);
        }
    }
}