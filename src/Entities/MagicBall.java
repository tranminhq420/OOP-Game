package Entities;

import Entities.GameObjectDynamic.Direction;

public class MagicBall extends Bullet {

//	private final int bullet_speed = 5;
//	private final int bullet_length = 250;
//	private int useCost = 1;
	
	public MagicBall(GameObjectDynamic bulletGP) {
        super(bulletGP);
        bulletGP.loadImage("res/textures/img/magic_ball.png");
        bulletGP.getImageDimension();
		setBullet_speed(2);
		setBullet_length(200);
		setUseCost(0);
		setFly(0);
    }

    public void move() {
    	if (bulletGP.getObjectDricetion() == Direction.RIGHT ) bulletGP.x += getBullet_speed(); // \
        if (bulletGP.getObjectDricetion() == Direction.UP) bulletGP.y -= getBullet_speed(); // \
        if (bulletGP.getObjectDricetion() == Direction.LEFT ) bulletGP.x -= getBullet_speed();
        if (bulletGP.getObjectDricetion() == Direction.DOWN) bulletGP.y += getBullet_speed();
        setFly(getFly() + getBullet_speed()  ); 
        if (getFly() > getBullet_length()) {
        	bulletGP.setExist(false);
        }
        if(bulletGP.x<0||bulletGP.x>550||bulletGP.y<0||bulletGP.y>550) {
        	bulletGP.setExist(false);
        }
    }
}