package Entities;

abstract class Bullet extends GameObjectDynamic {
	protected final int bullet_length = 750;
	protected final int bullet_speed =2;
	protected int fly =0;
    public Bullet(int x, int y) {
		super(x, y);
	}
	public abstract void move();
}
