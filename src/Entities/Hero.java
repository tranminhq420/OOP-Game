package Entities;

import Main.*;
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
	private int life = 6; // health
	private int maxLife = 6;
	private int speed = 2;
	private int maxMana = 10;

	private int mana = 10;
	private int attack = 2;
	private int defense = 1;
	private int skillAttack = 4;
	private boolean invincible = false;
	private int invincibleCounter = 0;
	private int collidedCounter = 0;

	private boolean isCollided;
	private GameObjectDynamic heroGP;

	public Hero(int x, int y) {
		setHeroGP(new GameObjectDynamic(x, y));
		fires = new ArrayList<>();
		skillshots = new ArrayList<>();
		getHeroGP().loadImage("res/textures/img/down.png");
		getHeroGP().getImageDimension();
	}

	public void move() {
		getHeroGP().x += getDx();
		getHeroGP().y += getDy();
		if (getHeroGP().x < 1) {
			getHeroGP().x = 1;
		}
		if (getHeroGP().y < 1) {
			getHeroGP().y = 1;
		}
		getHeroGP().getImageDimension();
		if (getHeroGP().x > Board.getSizeX() - getHeroGP().width) {
			getHeroGP().x = Board.getSizeX() - getHeroGP().width;
		}
		if (getHeroGP().y > Board.getSizeY() - getHeroGP().height) {
			getHeroGP().y = Board.getSizeY() - getHeroGP().height;
		}
		int left = Board.m.checkMapLeft(getHeroGP().x, getHeroGP().y, getHeroGP().width, getHeroGP().height);
		int right = Board.m.checkMapRight(getHeroGP().x, getHeroGP().y, getHeroGP().width, getHeroGP().height);
		int up = Board.m.checkMapUp(getHeroGP().x, getHeroGP().y, getHeroGP().width, getHeroGP().height);
		int down = Board.m.checkMapDown(getHeroGP().x, getHeroGP().y, getHeroGP().width, getHeroGP().height);

		GameObject objectLeft = new GameObject(0, 0, 32, 32, null);
		switch (left) {
			case 0:
				objectLeft.collision = new Earth(0, 0, 32, 32, null).getCollision();
				break;
			case 1:
				objectLeft.collision = new Tree(0, 0, 32, 32, null).getCollision();
				break;
			case 3:
				objectLeft.collision = new Rock(0, 0, 32, 32, null).getCollision();
				break;
			case 6:
				objectLeft.collision = new WaterBorder(0, 0, 32, 32, null).getCollision();
				break;
			case 9:
				objectLeft.collision = new NewLandDoor(0, 0, 32, 32, null).getCollision();
				break;
		}
		GameObject objectRight = new GameObject(0, 0, 32, 32, null);
		switch (right) {
			case 0:
				objectRight.collision = new Earth(0, 0, 32, 32, null).getCollision();
				break;
			case 1:
				objectRight.collision = new Tree(0, 0, 32, 32, null).getCollision();
				break;
			case 3:
				objectRight.collision = new Rock(0, 0, 32, 32, null).getCollision();
				break;
			case 6:
				objectRight.collision = new WaterBorder(0, 0, 32, 32, null).getCollision();
				break;
			case 9:
				objectRight.collision = new NewLandDoor(0, 0, 32, 32, null).getCollision();
				break;
		}
		GameObject objectUp = new GameObject(0, 0, 32, 32, null);
		switch (up) {
			case 0:
				objectUp.collision = new Earth(0, 0, 32, 32, null).getCollision();
				break;
			case 1:
				objectUp.collision = new Tree(0, 0, 32, 32, null).getCollision();
				break;
			case 3:
				objectUp.collision = new Rock(0, 0, 32, 32, null).getCollision();
				break;
			case 6:
				objectUp.collision = new WaterBorder(0, 0, 32, 32, null).getCollision();
				break;
			case 9:
				objectUp.collision = new NewLandDoor(0, 0, 32, 32, null).getCollision();
				break;
		}
		GameObject objectDown = new GameObject(0, 0, 32, 32, null);
		switch (down) {
			case 0:
				objectDown.collision = new Earth(0, 0, 32, 32, null).getCollision();
				break;
			case 1:
				objectDown.collision = new Tree(0, 0, 32, 32, null).getCollision();
				break;
			case 3:
				objectDown.collision = new Rock(0, 0, 32, 32, null).getCollision();
				break;
			case 6:
				objectDown.collision = new WaterBorder(0, 0, 32, 32, null).getCollision();
				break;
			case 9:
				objectDown.collision = new NewLandDoor(0, 0, 32, 32, null).getCollision();
				break;
		}

		// ko cho đi xuyen qua dia hinh: neu check collision cua object do = true thi se
		// cong tru toa do ve vi tri cu (+-dx,dy)

		if (objectLeft.collision == true) {

			getHeroGP().x = getHeroGP().x + speed;
		}
		if (objectRight.collision == true) {
			getHeroGP().x = getHeroGP().x - speed;
		}
		if (objectUp.collision == true) {
			getHeroGP().y = getHeroGP().y + speed;
		}
		if (objectDown.collision == true) {
			getHeroGP().y = getHeroGP().y - speed;
		}

		if (Board.m.checkMapLeft(getHeroGP().x, getHeroGP().y, getHeroGP().width, getHeroGP().height, 1) == 9) {
			Board.setDoor_appared();
		}
		if (Board.m.checkMapRight(getHeroGP().x, getHeroGP().y, getHeroGP().width, getHeroGP().height, 1) == 9) {
			Board.setDoor_appared();
		}
		if (Board.m.checkMapUp(getHeroGP().x, getHeroGP().y, getHeroGP().width, getHeroGP().height, 1) == 9) {
			Board.setDoor_appared();
		}
		if (Board.m.checkMapDown(getHeroGP().x, getHeroGP().y, getHeroGP().width, getHeroGP().height, 1) == 9) {
			Board.setDoor_appared();
		}
	}

	public void tofire() {
		int xz = 0, yz = 0;
		if (getHeroGP().getDirect() == 1) {
			xz = getHeroGP().x + getHeroGP().width;
			yz = getHeroGP().y + getHeroGP().height / 2;
		} else if (getHeroGP().getDirect() == -1) {
			xz = getHeroGP().x;
			yz = getHeroGP().y + getHeroGP().height / 2;
		} else if (getHeroGP().getDirect() == -2) {
			xz = getHeroGP().x + getHeroGP().width / 2;
			yz = getHeroGP().y + getHeroGP().height;
		} else if (getHeroGP().getDirect() == 2) {
			xz = getHeroGP().x + getHeroGP().width / 3;
			yz = getHeroGP().y;
		}
		Fire fire_new = new Fire(xz, yz);
		fire_new.setDirect(getHeroGP().getDirect());
		fires.add(fire_new);
	}

	public void toSkillshot() {
		if (mana > 0) {
			int xz = 0, yz = 0;
			if (getHeroGP().getDirect() == 1) {
				xz = getHeroGP().x + getHeroGP().width;
				yz = getHeroGP().y + getHeroGP().height / 2;
			} else if (getHeroGP().getDirect() == -1) {
				xz = getHeroGP().x;
				yz = getHeroGP().y + getHeroGP().height / 2;
			} else if (getHeroGP().getDirect() == -2) {
				xz = getHeroGP().x + getHeroGP().width / 2;
				yz = getHeroGP().y + getHeroGP().height;
			} else if (getHeroGP().getDirect() == 2) {
				xz = getHeroGP().x + getHeroGP().width / 3;
				yz = getHeroGP().y;
			}
			Skillshot skillshot_new = new Skillshot(xz, yz);
			skillshot_new.setDirect(getHeroGP().getDirect());
			skillshots.add(skillshot_new);
			mana -= skillshot_new.getUseCost();
		}

	}

	// public void keyPressed(KeyEvent e) {
	// int key = e.getKeyCode();
	// if (key == KeyEvent.VK_SPACE) {
	// tofire();
	// }
	// if (key == KeyEvent.VK_A) {
	// toSkillshot();
	// }
	// if (key == KeyEvent.VK_LEFT) {
	// setDx(-speed);
	// getHeroGP().loadImage("res/textures/img/left.png");
	// getHeroGP().setDirect(-1);
	// }
	// if (key == KeyEvent.VK_RIGHT) {
	// setDx(speed);
	// getHeroGP().loadImage("res/textures/img/right.png");
	// getHeroGP().setDirect(1);
	// }
	// if (key == KeyEvent.VK_UP) {
	// dy = -speed;
	// getHeroGP().loadImage("res/textures/img/up.png");
	// getHeroGP().setDirect(2);
	// }
	// if (key == KeyEvent.VK_DOWN) {
	// dy = speed;
	// getHeroGP().loadImage("res/textures/img/down.png");
	// getHeroGP().setDirect(-2);
	// }
	// }
	//
	// public void keyReleased(KeyEvent e) {
	// int key = e.getKeyCode();
	// if (key == KeyEvent.VK_SPACE) {
	// }
	// if (key == KeyEvent.VK_A) {
	// }
	// if (key == KeyEvent.VK_LEFT) {
	// setDx(0);
	// }
	// if (key == KeyEvent.VK_RIGHT) {
	// setDx(0);
	// }
	// if (key == KeyEvent.VK_UP) {
	// dy = 0;
	// }
	// if (key == KeyEvent.VK_DOWN) {
	// dy = 0;
	// }
	// }

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
}
// hello chua te Aram xin chao.
// co may cai branch ma` dau ca dau @@