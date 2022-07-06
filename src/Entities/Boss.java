package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Main.Board;

public class Boss extends Monster implements Boss_interface {
	Random rd = new Random();
	private int timez = 0;

	private final int find_hero_speed = 1; // cứ sau 20s tìm hero 1 lần
	private List<Stone> stones;
	private int hp;
	private final int HP_MAX = 1000;
	private int speed = 1;

	int t = 0, k = 0;

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
		if (getMonsterGP().getDirect() == 1) {
			xz = getMonsterGP().x + getMonsterGP().width;
			yz = getMonsterGP().y + getMonsterGP().height / 2;
		} else if (getMonsterGP().getDirect() == -1) {
			xz = getMonsterGP().x;
			yz = getMonsterGP().y + getMonsterGP().height / 2;
		} else if (getMonsterGP().getDirect() == -2) {
			xz = getMonsterGP().x + getMonsterGP().width / 2;
			yz = getMonsterGP().y + getMonsterGP().height;
		} else if (getMonsterGP().getDirect() == 2) {
			xz = getMonsterGP().x + getMonsterGP().width / 3;
			yz = getMonsterGP().y;
		}
		Stone stone_new = new Stone(xz, yz);
		stone_new.setDirect(getMonsterGP().getDirect());
		stones.add(stone_new);
	}

	public List<Stone> getStones() {
		return stones;
	}

	public void move(int heroY) { // hàm move() có tham số >< move() kế thừa từ Monster
		if (getMonsterGP().getDirect() == 1) {
			getMonsterGP().x += speed;
			getMonsterGP().loadImage("res/textures/img/bossright.png");
		} else if (getMonsterGP().getDirect() == -1) {
			getMonsterGP().x -= speed;
			getMonsterGP().loadImage("res/textures/img/bossleft.png");
		} else if (getMonsterGP().getDirect() == 2) {
			getMonsterGP().y -= speed;
			getMonsterGP().loadImage("res/textures/img/bossup.png");
		} else if (getMonsterGP().getDirect() == -2) {
			getMonsterGP().y += speed;
			getMonsterGP().loadImage("res/textures/img/bossdown.png");
		}
		timez += 1;
		if (timez == 100) { // cứ sau 100 chu kỳ timer.DELAY lại chuyển hướng di chuyển
			getMonsterGP().setDirect(rd.nextInt(5) - 2); // random hướng di chuyển (0..5 -2 --> -2 ..2 hướng di chuyển đã quy định 0
											// tương ứng với đứng yên)
			if (getMonsterGP().getDirect() == 0)
				getMonsterGP().setDirect(-1);
			timez = 0;
		}
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

//    	if(!Board.m.checkMapLeft(x,y,this.width,this.height)) {
//    		x=x+1;
//    	}
//    	if(!Board.m.checkMapRight(x,y,this.width,this.height)) {
//    		x=x-1;
//    	}
//    	if(!Board.m.checkMapUp(x,y,this.width,this.height)) {
//    		y=y+1;
//    	}
//    	if(!Board.m.checkMapDown(x,y,this.width,this.height)) {
//    		y=y-1;
//    	} 

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

}
