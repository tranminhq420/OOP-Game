package Entities;

import Main.*;
import Main.Map.EntityList;

import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Entities.GameObjectDynamic.Direction;

public class MagicCat {

	private List<MagicBall> magicballs;
	private List<Skillshot> skillshots;

	private int life = 8; // health
	private int maxLife = 8;
	private int speed = 8;
	private int maxMana = 12;

	private int mana = 12;
	private int attack = 4;
	private int defense = 1;
	private int skillAttack = 10;
	private boolean invincible = false;
	private int invincibleCounter = 0;
	private int collidedCounter = 0;
	private int shotAvailable = 0;
	private int skillAvailable = 0;
	private boolean isCollided;
	private GameObjectDynamic magicCatGP;

	public MagicCat(int x, int y) {
		setMagicCatGP(new GameObjectDynamic(x, y));
		magicballs = new ArrayList<>();
		skillshots = new ArrayList<>();
		magicCatGP.loadImage("res/textures/img/down.png");
		magicCatGP.getImageDimension();
		magicCatGP.setObjectDricetion(Direction.DOWN);
	}

	public void move() {
		if (magicCatGP.x - speed < 1) {
			magicCatGP.x = 1;
		}
		if (magicCatGP.y - speed < 1) {
			magicCatGP.y = 1;
		}

		magicCatGP.getImageDimension();
		if (magicCatGP.x + speed > Board.getSizeX() - magicCatGP.width) {
			magicCatGP.x = Board.getSizeX() - magicCatGP.width;
		}
		if (magicCatGP.y + speed > Board.getSizeY() - magicCatGP.height) {
			magicCatGP.y = Board.getSizeY() - magicCatGP.height;
		}

		if (collisionLeft(magicCatGP.x, magicCatGP.y, magicCatGP.width, magicCatGP.height)) {
			magicCatGP.x = magicCatGP.x + speed;
		}
		if (collisionRight(magicCatGP.x, magicCatGP.y, magicCatGP.width, magicCatGP.height)) {
			magicCatGP.x = magicCatGP.x - speed;
		}
		if (collisionUp(magicCatGP.x, magicCatGP.y, magicCatGP.width, magicCatGP.height)) {
			magicCatGP.y = magicCatGP.y + speed;
		}
		if (collisionDown(magicCatGP.x, magicCatGP.y, magicCatGP.width, magicCatGP.height)) {
			magicCatGP.y = magicCatGP.y - speed;
		}


//		Check 4 canh cua nhan vat neu nhu vao trong cua thi xuat hien
		if (Board.map.getEntityMap(magicCatGP.x, magicCatGP.y).getName().equals("New Land Door")
				|| Board.map.getEntityMap(magicCatGP.x + magicCatGP.width, magicCatGP.y).getName().equals("New Land Door")
				|| Board.map.getEntityMap(magicCatGP.x, magicCatGP.y + magicCatGP.height).getName().equals("New Land Door")
				|| Board.map.getEntityMap(magicCatGP.x + magicCatGP.width, magicCatGP.y + magicCatGP.height).getName().equals("New Land Door")
						) {
			Board.setDoor_appared();
		}

	}

	public boolean collisionRight(int x, int y, int width, int height) {
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

	public boolean collisionUp(int x, int y, int width, int height) {
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

	public boolean collisionDown(int x, int y, int width, int height) {
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

	public boolean collisionLeft(int x, int y, int width, int height) {
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

	public void castMagicBall() {
		int xz = 0, yz = 0;
		GameObjectDynamic bulletGP;
		if (magicCatGP.getObjectDricetion() == Direction.RIGHT) {
			xz = magicCatGP.x + magicCatGP.width;
			yz = magicCatGP.y + magicCatGP.height / 2;
		} else if (magicCatGP.getObjectDricetion() == Direction.LEFT) {
			xz = magicCatGP.x;
			yz = magicCatGP.y + magicCatGP.height / 2;
		} else if (magicCatGP.getObjectDricetion() == Direction.DOWN) {
			xz = magicCatGP.x + magicCatGP.width / 2;
			yz = magicCatGP.y + magicCatGP.height;
		} else if (magicCatGP.getObjectDricetion() == Direction.UP) {
			xz = magicCatGP.x + magicCatGP.width / 2;
			yz = magicCatGP.y;
		}
		bulletGP = new GameObjectDynamic(xz, yz);
		MagicBall MagicBall_new = new MagicBall(bulletGP);
		MagicBall_new.bulletGP.setObjectDricetion(magicCatGP.getObjectDricetion());
		magicballs.add(MagicBall_new);
	}

	public void castSkillshot() {
		if (mana > 0) {
			GameObjectDynamic bulletGP;
			int xz = 0, yz = 0;
			if (magicCatGP.getObjectDricetion() == Direction.RIGHT) {
				xz = getMagicCatGP().x + getMagicCatGP().width/2;
				yz = getMagicCatGP().y + getMagicCatGP().height / 2;
				for (int i = 0; i < Board.getSizeX() - xz -140; i++) {
					bulletGP = new GameObjectDynamic(xz + i, yz);
					Skillshot skillshot_new = new Skillshot(bulletGP);
					skillshot_new.bulletGP.setObjectDricetion(magicCatGP.getObjectDricetion());
					skillshots.add(skillshot_new);
				}
				bulletGP = new GameObjectDynamic(0, 0);
				Skillshot skillshot_new = new Skillshot(bulletGP);
				mana -= skillshot_new.getUseCost();
			} else if (magicCatGP.getObjectDricetion() == Direction.LEFT) {
				xz = getMagicCatGP().x;
				yz = getMagicCatGP().y + getMagicCatGP().height / 2;
				for (int i = 0; i < xz; i++) {
					bulletGP = new GameObjectDynamic(xz - i, yz);
					Skillshot skillshot_new = new Skillshot(bulletGP);
					skillshot_new.bulletGP.setObjectDricetion(magicCatGP.getObjectDricetion());
					skillshots.add(skillshot_new);
				}
				bulletGP = new GameObjectDynamic(0, 0);
				Skillshot skillshot_new = new Skillshot(bulletGP);
				mana -= skillshot_new.getUseCost();
			} else if (magicCatGP.getObjectDricetion() == Direction.DOWN) {
				xz = getMagicCatGP().x + getMagicCatGP().width / 2;
				yz = getMagicCatGP().y + getMagicCatGP().height/2;
				for (int i = 0; i < Board.getSizeY() - yz; i++) {
					bulletGP = new GameObjectDynamic(xz, yz + i);	 
					Skillshot skillshot_new = new Skillshot(bulletGP);
					skillshot_new.bulletGP.setObjectDricetion(magicCatGP.getObjectDricetion());
					skillshots.add(skillshot_new);
				}
				bulletGP = new GameObjectDynamic(0, 0);	 
				Skillshot skillshot_new = new Skillshot(bulletGP);
				mana -= skillshot_new.getUseCost();
			} else if (magicCatGP.getObjectDricetion() == Direction.UP) {
				xz = getMagicCatGP().x + getMagicCatGP().width / 3;
				yz = getMagicCatGP().y;
				for (int i = 0; i < yz; i++) {
					bulletGP = new GameObjectDynamic(xz, yz - i);
					Skillshot skillshot_new = new Skillshot(bulletGP);
					skillshot_new.bulletGP.setObjectDricetion(magicCatGP.getObjectDricetion());
					skillshots.add(skillshot_new);
				}
				bulletGP = new GameObjectDynamic(0, 0);
				Skillshot skillshot_new = new Skillshot(bulletGP);
				mana -= skillshot_new.getUseCost();
			
			}
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

	public int getMana() {
		return mana;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getSkillAttack() {
		return skillAttack;
	}

	public GameObjectDynamic getMagicCatGP() {
		return magicCatGP;
	}

	public void setMagicCatGP(GameObjectDynamic magicCatGP) {
		this.magicCatGP = magicCatGP;
	}

	public List<MagicBall> getMagicBalls() {
		return magicballs;
	}

	public List<Skillshot> getSkillshots() {
		return skillshots;
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

	public int getCollidedCounter() {
		return collidedCounter;
	}

	public void setCollidedCounter(int collidedCounter) {
		this.collidedCounter = collidedCounter;
	}

	public boolean getCollided() {
		return isCollided;
	}

	public void setCollided(boolean isCollided) {
		this.isCollided = isCollided;
	}

	public boolean isCollided() {
		return isCollided;
	}

	public void setSpeed(int i) {
		this.speed = i;
	}

	public int getShotAvailable() {
		return shotAvailable;
	}

	public void setShotAvailable(int i) {
		this.shotAvailable = i;
	}
	public int getSkillAvailable() {
		return skillAvailable;
	}

	public void setSkillAvailable(int i) {
		this.skillAvailable = i;
	}
}
