package Entities;

import Main.*;
import Main.Map.EntityList;

import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hero {
	private int dx;
	private int dy;
	private List<Fire> fires;
	private List<Skillshot> skillshots;
	// private int live = 3;
	private int moving;
	private int life = 8; // health
	private int maxLife = 6;
	private int speed = 2;
	private int maxMana = 20;

	private int mana = 12;
	private int attack = 4;
	private int defense = 1;
	private int skillAttack = 10;
	private boolean invincible = false;
	private int invincibleCounter = 0;
	private int shotAvailable = 0;
	private int skillAvailable = 0;
	// private int collidedCounter=0;
	// private boolean isCollided;


	private GameObjectDynamic heroGP;

	public Hero(int x, int y) {
		setHeroGP(new GameObjectDynamic(x, y));
		fires = new ArrayList<>();
		skillshots = new ArrayList<>();
		heroGP.loadImage("res/textures/img/down.png");
		heroGP.getImageDimension();
	}

	public void move() {
		heroGP.x += getDx();
		heroGP.y += getDy();
		if (heroGP.x < 1) {
			heroGP.x = 1;
		}
		if (heroGP.y < 1) {
			heroGP.y = 1;
		}
		//??? getImaeDimension o day u???
		heroGP.getImageDimension();
		if (heroGP.x > Board.getSizeX() - heroGP.width) {
			heroGP.x = Board.getSizeX() - heroGP.width;
		}
		if (heroGP.y > Board.getSizeY() - heroGP.height) {
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
		if (Board.map.getEntityMap(heroGP.x, heroGP.y).getName() == EntityList.NEWLANDDOOR.name()
				|| Board.map.getEntityMap(heroGP.x + heroGP.width, heroGP.y).getName() == EntityList.NEWLANDDOOR.name()
				|| Board.map.getEntityMap(heroGP.x, heroGP.y + heroGP.height).getName() == EntityList.NEWLANDDOOR.name()
				|| Board.map.getEntityMap(heroGP.x + heroGP.width, heroGP.y + heroGP.height)
						.getName() == EntityList.NEWLANDDOOR.name()) {
			Board.setDoor_appared();
		}

//		if (checkMapLeft(heroGP.x, heroGP.y, heroGP.width, heroGP.height) == 9) {
//			Board.setDoor_appared();
//		}
//		if (checkMapRight(heroGP.x, heroGP.y, heroGP.width, heroGP.height) == 9) {
//			Board.setDoor_appared();
//		}
//		if (checkMapUp(heroGP.x, heroGP.y, heroGP.width, heroGP.height) == 9) {
//			Board.setDoor_appared();
//		}
//		if (checkMapDown(heroGP.x, heroGP.y, heroGP.width, heroGP.height) == 9) {
//			Board.setDoor_appared();
//		}
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
		if (heroGP.getDirect() == 1) {
			xz = heroGP.x + heroGP.width;
			yz = heroGP.y + heroGP.height / 2;

		} else if (heroGP.getDirect() == -1) {
			xz = heroGP.x;
			yz = heroGP.y + heroGP.height / 2;
		} else if (heroGP.getDirect() == -2) {
			xz = heroGP.x + heroGP.width / 2;
			yz = heroGP.y + heroGP.height;
		} else if (heroGP.getDirect() == 2) {
			xz = heroGP.x + heroGP.width / 3;
			yz = heroGP.y;
		}
		Fire fire_new = new Fire(xz, yz);
		fire_new.setDirect(heroGP.getDirect());
		fires.add(fire_new);
	}

	public void toSkillshot() {
		if (mana > 0) {
			int xz = 0, yz = 0, direct;
			if (getHeroGP().getDirect() == 1) {
				xz = getHeroGP().x + getHeroGP().width;
				yz = getHeroGP().y + getHeroGP().height / 2;
				for (int i = 0; i < Board.getSizeX() - xz - 120; i++) {
					Skillshot skillshot_new = new Skillshot(xz + i, yz);
					skillshot_new.setDirect(getHeroGP().getDirect());
					skillshots.add(skillshot_new);
				}
				Skillshot skillshot_new = new Skillshot(0, 0);
				mana -= skillshot_new.getUseCost();
			} else if (getHeroGP().getDirect() == -1) {
				xz = getHeroGP().x;
				yz = getHeroGP().y + getHeroGP().height / 2;
				for (int i = 0; i < xz; i++) {
					Skillshot skillshot_new = new Skillshot(xz - i, yz);
					skillshot_new.setDirect(getHeroGP().getDirect());
					skillshots.add(skillshot_new);
				}
				Skillshot skillshot_new = new Skillshot(0, 0);
				mana -= skillshot_new.getUseCost();
			} else if (getHeroGP().getDirect() == -2) {
				xz = getHeroGP().x + getHeroGP().width / 2;
				yz = getHeroGP().y + getHeroGP().height;
				for (int i = 0; i < Board.getSizeY() - yz; i++) {
					Skillshot skillshot_new = new Skillshot(xz, yz + i);
					skillshot_new.setDirect(getHeroGP().getDirect());
					skillshots.add(skillshot_new);
				}
				Skillshot skillshot_new = new Skillshot(0, 0);
				mana -= skillshot_new.getUseCost();
			} else if (getHeroGP().getDirect() == 2) {
				xz = getHeroGP().x + getHeroGP().width / 3;
				yz = getHeroGP().y;
				for (int i = 0; i < yz; i++) {
					Skillshot skillshot_new = new Skillshot(xz, yz - i);
					skillshot_new.setDirect(getHeroGP().getDirect());
					skillshots.add(skillshot_new);
				}
				Skillshot skillshot_new = new Skillshot(0, 0);
				mana -= skillshot_new.getUseCost();
			}

			// for( int i=0; i < Board.getSizeX()/32-xz ; i++) {
			// Skillshot skillshot_new = new Skillshot(xz, yz);
			// skillshot_new.setDirect(getHeroGP().getDirect());
			// skillshots.add(skillshot_new);
			// }
			// mana -= skillshot_new.getUseCost();

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

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
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

	public void setSpeed(int i) {
		this.speed = i;
	}

}
// hello chua te Aram xin chao.
// co may cai branch ma` dau ca dau @@