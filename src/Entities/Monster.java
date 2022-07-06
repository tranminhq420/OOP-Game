package Entities;

import java.util.Random;

import Main.Board;

public class Monster {
	Random rd = new Random();
	private int timez = 0;

//	protected static final int speed =1;
	private final int speed = 1;
	private int maxLife = 4;
	private int life = maxLife;
	private int attack = 2;
	private int defense = 0;
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
			getMonsterGP().loadImage("res/textures/img/tauvutruright.png");
		} else if (getMonsterGP().getDirect() == -1) {
			getMonsterGP().x -= speed;
			getMonsterGP().loadImage("res/textures/img/tauvutruleft.png");
		} else if (getMonsterGP().getDirect() == 2) {
			getMonsterGP().y -= speed;
			getMonsterGP().loadImage("res/textures/img/tauvutrutop.png");
		} else if (getMonsterGP().getDirect() == -2) {
			getMonsterGP().y += speed;
			getMonsterGP().loadImage("res/textures/img/tauvutrudown.png");
		}
		timez += 1;
		if (timez == 100) { // cứ sau 100 chu kỳ timer.DELAY lại chuyển hướng di chuyển
			getMonsterGP().setDirect(rd.nextInt(5) - 2); // random hướng di chuyển (0..5 -2 --> -2 ..2 hướng di chuyển đã quy định 0
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
		if (!Board.m.checkMapLeft(getMonsterGP().x, getMonsterGP().y, getMonsterGP().width, getMonsterGP().height)) {
			getMonsterGP().x = getMonsterGP().x + 1;
		}
		if (!Board.m.checkMapRight(getMonsterGP().x, getMonsterGP().y, getMonsterGP().width, getMonsterGP().height)) {
			getMonsterGP().x = getMonsterGP().x - 1;
		}
		if (!Board.m.checkMapUp(getMonsterGP().x, getMonsterGP().y, getMonsterGP().width, getMonsterGP().height)) {
			getMonsterGP().y = getMonsterGP().y + 1;
		}
		if (!Board.m.checkMapDown(getMonsterGP().x, getMonsterGP().y, getMonsterGP().width, getMonsterGP().height)) {
			getMonsterGP().y = getMonsterGP().y - 1;
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
