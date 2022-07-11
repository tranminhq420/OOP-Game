package Entities;

import Entities.GameObjectDynamic.Direction;

public class MagicBall extends Bullet {

	// public MagicBall(int x, int y) {
    // 	super(x,y);
    // 	loadImage("res/textures/img/magic_ball.png");
    // 	getImageDimension();
	private final int bullet_speed = 5;
	private final int bullet_length = 250;
	private int useCost = 1;
	
	public MagicBall(GameObjectDynamic bulletGP) {
        super(bulletGP);
        bulletGP.loadImage("res/textures/img/magic_ball.png");
        bulletGP.getImageDimension();

    }
    public void move() {
    	if (bulletGP.getObjectDricetion() == Direction.RIGHT ) bulletGP.x += bullet_speed; // \
        if (bulletGP.getObjectDricetion() == Direction.UP) bulletGP.y -= bullet_speed; // \
        if (bulletGP.getObjectDricetion() == Direction.LEFT ) bulletGP.x -= bullet_speed;
        if (bulletGP.getObjectDricetion() == Direction.DOWN) bulletGP.y += bullet_speed;
        fly += bullet_speed; // \
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