package Entities;

import java.util.Random;

import Main.Board;

public class Monster {
	Random rd = new Random();
	private int timez = 0;
	private final int speed = 1;
	private int maxLife = 4;
	private int life = maxLife;
	private int attack = 2;
	private int defense = 0;
	private boolean collision = true;
	private GameObjectDynamic monsterGP;

	public Monster(int x, int y) {
		setMonsterGP(new GameObjectDynamic(x, y));
		initMonster();
	}

	private void initMonster() {
		getMonsterGP().loadImage("res/textures/img/tauvutru.png");
		getMonsterGP().getImageDimension();
	}

	public void move() {
		if (getMonsterGP().getDirect() == 1) {
			getMonsterGP().x += speed;
			getMonsterGP().loadImage("res/textures/img/monkeyright.png");
		} else if (getMonsterGP().getDirect() == -1) {
			getMonsterGP().x -= speed;
			getMonsterGP().loadImage("res/textures/img/monkeyleft.png");
		} else if (getMonsterGP().getDirect() == 2) {
			getMonsterGP().y -= speed;
			getMonsterGP().loadImage("res/textures/img/monkeyup.png");
		} else if (getMonsterGP().getDirect() == -2) {
			getMonsterGP().y += speed;
			getMonsterGP().loadImage("res/textures/img/monkeydown.png");
		}
		timez += 1;
		if (timez == 100) { // cứ sau 100 chu kỳ timer.DELAY lại chuyển hướng di chuyển
			getMonsterGP().setDirect(rd.nextInt(5) - 2); // random hướng di chuyển (0..5 -2 --> -2 ..2 hướng di chuyển đã quy
																										// định 0
			// tương ứng với đứng yên)
			timez = 0;
		}
		if (getMonsterGP().x < 1) {
			getMonsterGP().x = 1;
		} // ko cho di chuyển tràn khung
		if (getMonsterGP().y < 1) {
			getMonsterGP().y = 1;
		}
		getMonsterGP().getImageDimension();
		if (getMonsterGP().x > Board.getSizeX() - getMonsterGP().width) {
			getMonsterGP().x = Board.getSizeX() - getMonsterGP().width;
		}
		if (getMonsterGP().y > Board.getSizeY() - getMonsterGP().height) {
			getMonsterGP().y = Board.getSizeY() - getMonsterGP().height;
		}

		int left = Board.m.checkMapLeft(getMonsterGP().x, getMonsterGP().y, getMonsterGP().width, getMonsterGP().height);
		int right = Board.m.checkMapRight(getMonsterGP().x, getMonsterGP().y, getMonsterGP().width, getMonsterGP().height);
		int up = Board.m.checkMapUp(getMonsterGP().x, getMonsterGP().y, getMonsterGP().width, getMonsterGP().height);
		int down = Board.m.checkMapDown(getMonsterGP().x, getMonsterGP().y, getMonsterGP().width, getMonsterGP().height);

		GameObject objectLeft = new GameObject(0, 0, 0, down, null);
		switch (left) {
			case 0:
				objectLeft.collision = new Earth(0, 0, 0, down, null).getCollision();
				break;
			case 1:
				objectLeft.collision = new Tree(0, 0, 0, down, null).getCollision();
				break;
			case 2:
				objectLeft.collision = new Water(0, 0, 0, down, null).getCollision();
				break;
			case 3:
				objectLeft.collision = new Rock(0, 0, 0, down, null).getCollision();
				break;
			case 9:
				objectLeft.collision = new NewLandDoor(0, 0, 0, down, null).getCollision();
				break;
		}
		GameObject objectRight = new GameObject(0, 0, 0, down, null);
		switch (right) {
			case 0:
				objectRight.collision = new Earth(0, 0, 0, down, null).getCollision();
				break;
			case 1:
				objectRight.collision = new Tree(0, 0, 0, down, null).getCollision();
				break;
			case 2:
				objectRight.collision = new Water(0, 0, 0, down, null).getCollision();
				break;
			case 3:
				objectRight.collision = new Rock(0, 0, 0, down, null).getCollision();
				break;
			case 9:
				objectRight.collision = new NewLandDoor(0, 0, 0, down, null).getCollision();
				break;
		}
		GameObject objectUp = new GameObject(0, 0, 0, down, null);
		switch (up) {
			case 0:
				objectUp.collision = new Earth(0, 0, 0, down, null).getCollision();
				break;
			case 1:
				objectUp.collision = new Tree(0, 0, 0, down, null).getCollision();
				break;
			case 2:
				objectUp.collision = new Water(0, 0, 0, down, null).getCollision();
				break;
			case 3:
				objectUp.collision = new Rock(0, 0, 0, down, null).getCollision();
				break;
			case 9:
				objectUp.collision = new NewLandDoor(0, 0, 0, down, null).getCollision();
				break;
		}
		GameObject objectDown = new GameObject(0, 0, 0, down, null);
		switch (down) {
			case 0:
				objectDown.collision = new Earth(0, 0, 0, down, null).getCollision();
				break;
			case 1:
				objectDown.collision = new Tree(0, 0, 0, down, null).getCollision();
				break;
			case 2:
				objectDown.collision = new Water(0, 0, 0, down, null).getCollision();
				break;
			case 3:
				objectDown.collision = new Rock(0, 0, 0, down, null).getCollision();
				break;
			case 9:
				objectDown.collision = new NewLandDoor(0, 0, 0, down, null).getCollision();
				break;
		}

		if (objectLeft.collision == true) {
			getMonsterGP().x = getMonsterGP().x + speed;
		}
		if (objectRight.collision == true) {
			getMonsterGP().x = getMonsterGP().x - speed;
		}
		if (objectUp.collision == true) {
			getMonsterGP().y = getMonsterGP().y + speed;
		}
		if (objectDown.collision == true) {
			getMonsterGP().y = getMonsterGP().y - speed;
		}
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getSpeed() {
		return speed;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public GameObjectDynamic getMonsterGP() {
		return monsterGP;
	}

	public void setMonsterGP(GameObjectDynamic monsterGP) {
		this.monsterGP = monsterGP;
	}
}
