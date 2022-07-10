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
		monsterGP.loadImage("res/textures/img/tauvutru.png");
		monsterGP.getImageDimension();
	}

	public void move() {
		if (monsterGP.getDirect() == 1) {
			monsterGP.x += speed;
			monsterGP.loadImage("res/textures/img/monkeyright.png");
		} else if (monsterGP.getDirect() == -1) {
			monsterGP.x -= speed;
			monsterGP.loadImage("res/textures/img/monkeyleft.png");
		} else if (monsterGP.getDirect() == 2) {
			monsterGP.y -= speed;
			monsterGP.loadImage("res/textures/img/monkeyup.png");
		} else if (monsterGP.getDirect() == -2) {
			monsterGP.y += speed;
			monsterGP.loadImage("res/textures/img/monkeydown.png");
		}
		timez += 1;
		if (timez == 100) { // cứ sau 100 chu kỳ timer.DELAY lại chuyển hướng di chuyển
			monsterGP.setDirect(rd.nextInt(5) - 2); // random hướng di chuyển (0..5 -2 --> -2 ..2 hướng di chuyển đã quy
																										// định 0
			// tương ứng với đứng yên)
			timez = 0;
		}
		if (monsterGP.x < 1) {
			monsterGP.x = 1;
		} // ko cho di chuyển tràn khung
		if (monsterGP.y < 1) {
			monsterGP.y = 1;
		}
		monsterGP.getImageDimension();
		if (monsterGP.x > Board.getSizeX() - monsterGP.width) {
			monsterGP.x = Board.getSizeX() - monsterGP.width;
		}
		if (monsterGP.y > Board.getSizeY() - monsterGP.height) {
			monsterGP.y = Board.getSizeY() - monsterGP.height;
		}

//		int left = Board.m.checkMapLeft(monsterGP.x, monsterGP.y, monsterGP.width, monsterGP.height);
//		int right = Board.m.checkMapRight(monsterGP.x, monsterGP.y, monsterGP.width, monsterGP.height);
//		int up = Board.m.checkMapUp(monsterGP.x, monsterGP.y, monsterGP.width, monsterGP.height);
//		int down = Board.m.checkMapDown(monsterGP.x, monsterGP.y, monsterGP.width, monsterGP.height);
//
//		GameObject objectLeft = new GameObject(0, 0, 0, down, null);
//		switch (left) {
//			case 0:
//				objectLeft.collision = new Earth(0, 0, 0, down, null).getCollision();
//				break;
//			case 1:
//				objectLeft.collision = new Tree(0, 0, 0, down, null).getCollision();
//				break;
//			case 2:
//				objectLeft.collision = new Water(0, 0, 0, down, null).getCollision();
//				break;
//			case 3:
//				objectLeft.collision = new Rock(0, 0, 0, down, null).getCollision();
//				break;
//			case 9:
//				objectLeft.collision = new NewLandDoor(0, 0, 0, down, null).getCollision();
//				break;
//		}
//		GameObject objectRight = new GameObject(0, 0, 0, down, null);
//		switch (right) {
//			case 0:
//				objectRight.collision = new Earth(0, 0, 0, down, null).getCollision();
//				break;
//			case 1:
//				objectRight.collision = new Tree(0, 0, 0, down, null).getCollision();
//				break;
//			case 2:
//				objectRight.collision = new Water(0, 0, 0, down, null).getCollision();
//				break;
//			case 3:
//				objectRight.collision = new Rock(0, 0, 0, down, null).getCollision();
//				break;
//			case 9:
//				objectRight.collision = new NewLandDoor(0, 0, 0, down, null).getCollision();
//				break;
//		}
//		GameObject objectUp = new GameObject(0, 0, 0, down, null);
//		switch (up) {
//			case 0:
//				objectUp.collision = new Earth(0, 0, 0, down, null).getCollision();
//				break;
//			case 1:
//				objectUp.collision = new Tree(0, 0, 0, down, null).getCollision();
//				break;
//			case 2:
//				objectUp.collision = new Water(0, 0, 0, down, null).getCollision();
//				break;
//			case 3:
//				objectUp.collision = new Rock(0, 0, 0, down, null).getCollision();
//				break;
//			case 9:
//				objectUp.collision = new NewLandDoor(0, 0, 0, down, null).getCollision();
//				break;
//		}
//		GameObject objectDown = new GameObject(0, 0, 0, down, null);
//		switch (down) {
//			case 0:
//				objectDown.collision = new Earth(0, 0, 0, down, null).getCollision();
//				break;
//			case 1:
//				objectDown.collision = new Tree(0, 0, 0, down, null).getCollision();
//				break;
//			case 2:
//				objectDown.collision = new Water(0, 0, 0, down, null).getCollision();
//				break;
//			case 3:
//				objectDown.collision = new Rock(0, 0, 0, down, null).getCollision();
//				break;
//			case 9:
//				objectDown.collision = new NewLandDoor(0, 0, 0, down, null).getCollision();
//				break;
//		}

		if (checkMapLeft(monsterGP.x, monsterGP.y, monsterGP.width, monsterGP.height)) {
			monsterGP.x = monsterGP.x + speed;
		}
		if (checkMapRight(monsterGP.x, monsterGP.y, monsterGP.width, monsterGP.height)) {
			monsterGP.x = monsterGP.x - speed;
		}
		if (checkMapUp(monsterGP.x, monsterGP.y, monsterGP.width, monsterGP.height)) {
			monsterGP.y = monsterGP.y + speed;
		}
		if (checkMapDown(monsterGP.x, monsterGP.y, monsterGP.width, monsterGP.height)) {
			monsterGP.y = monsterGP.y - speed;
		}
	}


	public boolean checkMapRight(int x, int y, int width, int height) {
		x = x + width + 1;
		for (int i = 0; i < height; i++) {
			if (Board.map.getEntityMap(x, y).isCollision())
				return true;
			else {
				y = y + 1;
			}
		}
		return false;
	}

	public boolean checkMapUp(int x, int y, int width, int height) {
		y = y - 1;
		for (int i = 0; i < width; i++) {
			if (Board.map.getEntityMap(x, y).isCollision())
				return true;
			else {
				x = x + 1;
			}
		}
		return false;
	}

	public boolean checkMapDown(int x, int y, int width, int height) {
		y = y + height + 1;
		for (int i = 0; i < width; i++) {
			if (Board.map.getEntityMap(x, y).isCollision())
				return true;
			else {
				x = x + 1;
			}
		}
		return false;
	}

	public boolean checkMapLeft(int x, int y, int width, int height) {
		x = x - 1;
		for (int i = 0; i < height; i++) {
			if (Board.map.getEntityMap(x, y).isCollision())
				return true;
			else {
				y = y + 1;
			}
		}
		return false;
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
