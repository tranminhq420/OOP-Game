package Entities;

abstract class Bullet {
	protected final int bullet_length = 250;
	protected final int bullet_speed =2;
	protected int fly =0;
	protected GameObjectDynamic bulletGP;
    public Bullet(GameObjectDynamic bulletGP) {
		this.bulletGP = bulletGP;
	}
	public abstract void move();
	
	public int getBullet_length() {
		return bullet_length;
	}
	public int getBullet_speed() {
		return bullet_speed;
	}
	public int getFly() {
		return fly;
	}
	public void setFly(int fly) {
		this.fly = fly;
	}
	public GameObjectDynamic getBulletGP() {
		return bulletGP;
	}
	public void setBulletGP(GameObjectDynamic bulletGP) {
		this.bulletGP = bulletGP;
	}

	

}
