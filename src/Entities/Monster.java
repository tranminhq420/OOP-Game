package Entities;

import java.util.Random;

import Entities.GameObjectDynamic.Direction;
import Main.Board;

public class Monster {

	private final int speed = 1;
	private int maxLife = 4;
	private int life = maxLife;
	private int attack = 2;
	private int defense = 2;
//	private boolean collision = true;
	private boolean invincible=false;
	private int invincibleCounter=0;
	private GameObjectDynamic monsterGP;
	private int timez = 0;
	public Monster(int x, int y) {
		setMonsterGP(new GameObjectDynamic(x, y));
		initMonster();
	}

	private void initMonster() {
		monsterGP.loadImage("res/textures/img/tauvutru.png");
		monsterGP.getImageDimension();
		monsterGP.setObjectDricetion(Direction.DOWN);
	}

	public void move() {
		if (monsterGP.getObjectDricetion() == Direction.RIGHT) {
			monsterGP.x += speed;
			monsterGP.loadImage("res/textures/img/monkeyright.png");
		} else if (monsterGP.getObjectDricetion() == Direction.LEFT) {
			monsterGP.x -= speed;
			monsterGP.loadImage("res/textures/img/monkeyleft.png");
		} else if (monsterGP.getObjectDricetion() == Direction.UP) {
			monsterGP.y -= speed;
			monsterGP.loadImage("res/textures/img/monkeyup.png");
		} else if (monsterGP.getObjectDricetion() == Direction.DOWN) {
			monsterGP.y += speed;
			monsterGP.loadImage("res/textures/img/monkeydown.png");
		}
		
		Random rd = new Random();

		timez += 1;
		if (timez == 100) { // cứ sau 100 chu kỳ timer.DELAY lại chuyển hướng di chuyển
		 // random hướng di chuyển 
			int key = rd.nextInt(4);// định 0
			switch (key) {
			case 4:	monsterGP.setObjectDricetion(Direction.DOWN);break;
			case 1:	monsterGP.setObjectDricetion(Direction.LEFT);break;
			case 2:	monsterGP.setObjectDricetion(Direction.UP);break;
			case 3:	monsterGP.setObjectDricetion(Direction.RIGHT);break;

			}
			System.out.println(key);
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
	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public int getInvincibleCounter() {
		return invincibleCounter;
	}

	public void setInvincibleCounter(int invincibleCounter) {
		this.invincibleCounter = invincibleCounter;
	}
}
