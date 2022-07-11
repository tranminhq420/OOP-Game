package Entities;

import Main.*;
import Main.Map.EntityList;

import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Entities.GameObjectDynamic.Direction;

public class Hero {

	private List<Fire> fires;
	private List<Skillshot> skillshots;

	private int life = 8; // health
	private int maxLife = 8;
	private int speed = 4;
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
	private GameObjectDynamic heroGP;

	public Hero(int x, int y) {
		setHeroGP(new GameObjectDynamic(x, y));
		fires = new ArrayList<>();
		skillshots = new ArrayList<>();
		heroGP.loadImage("res/textures/img/down.png");
		heroGP.getImageDimension();
		heroGP.setObjectDricetion(Direction.DOWN);
	}

	public void move() {
		if (heroGP.x - speed < 1) {
			heroGP.x = 1;
		}
		if (heroGP.y - speed < 1) {
			heroGP.y = 1;
		}

		heroGP.getImageDimension();
		if (heroGP.x + speed > Board.getSizeX() - heroGP.width) {
			heroGP.x = Board.getSizeX() - heroGP.width;
		}
		if (heroGP.y + speed > Board.getSizeY() - heroGP.height) {
			heroGP.y = Board.getSizeY() - heroGP.height;
		}

		if (checkMapLeft(heroGP.x, heroGP.y, heroGP.width, heroGP.height)) {
			heroGP.x = heroGP.x + speed;
		}
		if (checkMapRight(heroGP.x, heroGP.y, heroGP.width, heroGP.height)) {
			heroGP.x = heroGP.x - speed;
		}
		if (checkMapUp(heroGP.x, heroGP.y, heroGP.width, heroGP.height)) {
			heroGP.y = heroGP.y + speed;
		}
		if (checkMapDown(heroGP.x, heroGP.y, heroGP.width, heroGP.height)) {
			heroGP.y = heroGP.y - speed;
		}


//		Check 4 canh cua nhan vat neu nhu vao trong cua thi xuat hien
		if (Board.map.getEntityMap(heroGP.x, heroGP.y).getName().equals("New Land Door")
				|| Board.map.getEntityMap(heroGP.x + heroGP.width, heroGP.y).getName().equals("New Land Door")
				|| Board.map.getEntityMap(heroGP.x, heroGP.y + heroGP.height).getName().equals("New Land Door")
				|| Board.map.getEntityMap(heroGP.x + heroGP.width, heroGP.y + heroGP.height).getName().equals("New Land Door")
						) {
			Board.setDoor_appared();
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

	public void tofire() {
		int xz = 0, yz = 0;
		if (heroGP.getObjectDricetion() == Direction.RIGHT) {
			xz = heroGP.x + heroGP.width;
			yz = heroGP.y + heroGP.height / 2;
		} else if (heroGP.getObjectDricetion() == Direction.LEFT) {
			xz = heroGP.x;
			yz = heroGP.y + heroGP.height / 2;
		} else if (heroGP.getObjectDricetion() == Direction.DOWN) {
			xz = heroGP.x + heroGP.width / 2;
			yz = heroGP.y + heroGP.height;
		} else if (heroGP.getObjectDricetion() == Direction.UP) {
			xz = heroGP.x + heroGP.width / 2;
			yz = heroGP.y;
		}
		Fire fire_new = new Fire(xz, yz);
		fire_new.setObjectDricetion(heroGP.getObjectDricetion());
		fires.add(fire_new);
	}

	public void toSkillshot() {
		if (mana > 0) {

			int xz = 0, yz = 0;
			if (heroGP.getObjectDricetion() == Direction.RIGHT) {
				xz = getHeroGP().x + getHeroGP().width;
				yz = getHeroGP().y + getHeroGP().height / 2;
				for (int i = 0; i < Board.getSizeX() - xz -140; i++) {
					Skillshot skillshot_new = new Skillshot(xz + i, yz);
					skillshot_new.setObjectDricetion(heroGP.getObjectDricetion());
					skillshots.add(skillshot_new);
				}
				Skillshot skillshot_new = new Skillshot(0, 0);
				mana -= skillshot_new.getUseCost();
			} else if (heroGP.getObjectDricetion() == Direction.LEFT) {
				xz = getHeroGP().x;
				yz = getHeroGP().y + getHeroGP().height / 2;
				for (int i = 0; i < xz; i++) {
					Skillshot skillshot_new = new Skillshot(xz - i, yz);
					skillshot_new.setObjectDricetion(heroGP.getObjectDricetion());
					skillshots.add(skillshot_new);
				}
				Skillshot skillshot_new = new Skillshot(0, 0);
				mana -= skillshot_new.getUseCost();
			} else if (heroGP.getObjectDricetion() == Direction.DOWN) {
				xz = getHeroGP().x + getHeroGP().width / 2;
				yz = getHeroGP().y + getHeroGP().height;
				for (int i = 0; i < Board.getSizeY() - yz; i++) {
					Skillshot skillshot_new = new Skillshot(xz, yz + i);
					skillshot_new.setObjectDricetion(heroGP.getObjectDricetion());
					skillshots.add(skillshot_new);
				}
				Skillshot skillshot_new = new Skillshot(0, 0);
				mana -= skillshot_new.getUseCost();
			} else if (heroGP.getObjectDricetion() == Direction.UP) {
				xz = getHeroGP().x + getHeroGP().width / 3;
				yz = getHeroGP().y;
				for (int i = 0; i < yz; i++) {
					Skillshot skillshot_new = new Skillshot(xz, yz - i);
					skillshot_new.setObjectDricetion(heroGP.getObjectDricetion());
					skillshots.add(skillshot_new);
				}
				Skillshot skillshot_new = new Skillshot(0, 0);
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

	public GameObjectDynamic getHeroGP() {
		return heroGP;
	}

	public void setHeroGP(GameObjectDynamic heroGP) {
		this.heroGP = heroGP;
	}

	public List<Fire> getFires() {
		return fires;
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
