package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Entities.GameObjectDynamic.Direction;
import Main.Board;

public class Boss extends Monster implements Boss_interface {


	private List<Stone> stones;
	private int hp;
	private final int HP_MAX = 100;
	private int speed = 1;
	int t = 0, k = 0;
	int timez = 0;

	public Boss(int x, int y) {
		super(x, y);
		initBoss();
	}

	private void initBoss() {
		stones = new ArrayList<>();
		getMonsterGP().loadImage("res/textures/img/bossdown.png");
		getMonsterGP().getImageDimension(); // lấy kích thước ảnh
		this.hp = HP_MAX;
	}

	@Override
	public void toStone() { // quái ném đá
		int xz = 0, yz = 0;
		if (getMonsterGP().getObjectDricetion() == Direction.RIGHT) {
			xz = getMonsterGP().x + getMonsterGP().width;
			yz = getMonsterGP().y + getMonsterGP().height / 2;
		} else if (getMonsterGP().getObjectDricetion() == Direction.LEFT) {
			xz = getMonsterGP().x;
			yz = getMonsterGP().y + getMonsterGP().height / 2;
		} else if (getMonsterGP().getObjectDricetion() == Direction.DOWN) {
			xz = getMonsterGP().x + getMonsterGP().width / 2;
			yz = getMonsterGP().y + getMonsterGP().height;
		} else if (getMonsterGP().getObjectDricetion() == Direction.UP) {
			xz = getMonsterGP().x + getMonsterGP().width / 2;
			yz = getMonsterGP().y;
		}
		Stone stone_new = new Stone(xz, yz);
		stone_new.setObjectDricetion(getMonsterGP().getObjectDricetion());;

		stones.add(stone_new);
	}

	public List<Stone> getStones() {
		return stones;
	}

	public void move(int heroY) { // hàm move() có tham số >< move() kế thừa từ Monster

		if (getMonsterGP().getObjectDricetion() == Direction.RIGHT) {
			getMonsterGP().x += speed;
			getMonsterGP().loadImage("res/textures/img/eyeright.png");
		} else if (getMonsterGP().getObjectDricetion() == Direction.LEFT) {
			getMonsterGP().x -= speed;
			getMonsterGP().loadImage("res/textures/img/eyeleft.png");
		} else if (getMonsterGP().getObjectDricetion() == Direction.UP) {
			getMonsterGP().y -= speed;
			getMonsterGP().loadImage("res/textures/img/eyeup.png");
		} else if (getMonsterGP().getObjectDricetion() == Direction.DOWN) {
			getMonsterGP().y += speed;
			getMonsterGP().loadImage("res/textures/img/eyedown.png");
		}
		Random rd = new Random();
		int find_hero_speed = 1; // cứ sau 20s tìm hero 1 lần

		timez += 1;
		if (timez == 20) { // cứ sau 100 chu kỳ timer.DELAY lại chuyển hướng di chuyển
		 // random hướng di chuyển 
																										// định 0
			switch (rd.nextInt(4) ) {
			case 4:	getMonsterGP().setObjectDricetion(Direction.DOWN);break;
			case 1:	getMonsterGP().setObjectDricetion(Direction.LEFT);break;
			case 2:	getMonsterGP().setObjectDricetion(Direction.UP);break;
			case 3:	getMonsterGP().setObjectDricetion(Direction.RIGHT);break;

			}
			// tương ứng với đứng yên)
			timez = 0;
		}
//		timez += 1;
//		if (timez == 50) { // cứ sau 100 chu kỳ timer.DELAY lại chuyển hướng di chuyển
//			getMonsterGP().setDirect(rd.nextInt(5) - 2); // random hướng di chuyển (0..5 -2 --> -2 ..2 hướng di chuyển đã quy
//																										// định 0
//			// tương ứng với đứng yên)
//			if (getMonsterGP().getDirect() == 0)
//				getMonsterGP().setDirect(-1);
//			timez = 0;
//		}
		if (getMonsterGP().x < 1) {
			getMonsterGP().x = 1;
		} // ko cho di chuyển tràn khung
		if (getMonsterGP().y < 1) {
			getMonsterGP().y = 1;
		}

		getMonsterGP().getImageDimension();
		if (getMonsterGP().x > Board.getSizeX() - 150 - getMonsterGP().width * 2) {
			getMonsterGP().x = Board.getSizeX() - 150 - getMonsterGP().width * 2;
		}
		if (getMonsterGP().y > Board.getSizeY() - getMonsterGP().height * 2) {
			getMonsterGP().y = Board.getSizeY() - getMonsterGP().height * 2;
		}


		if (t < find_hero_speed)
			t++; // số chu kì tìm hero
		if (t == find_hero_speed) {
			if (getMonsterGP().getY() - heroY > speed) {
				getMonsterGP().y -= speed;
			}
			if (getMonsterGP().getY() - heroY < -speed) {
				getMonsterGP().y += speed;
			}
			t = 0;
		}
		k++;
		if (getMonsterGP().getY() - heroY < getMonsterGP().height && k >= 200) {
			toStone();
			k = 0;
		}
		;
	}

	@Override
	public void dequai() {
		
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHpMax() {
		return HP_MAX;
	}
}
