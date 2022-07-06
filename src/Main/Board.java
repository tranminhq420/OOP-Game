package Main;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

import Entities.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener {
	private static final int SIZE_X = 750;
	private static final int SIZE_Y = 600;
	private static final int tileSize = 32;
	private final int TOADO_X = 10;
	private final int TOADO_Y = 10;
	private final int DELAY = 10; // toc do quet su kien
	// protected Image logo;
	private Hero hero;
	private Boss boss;
	private Timer timer;
	private boolean ingame; // danh dau chuyen canh
	private boolean started; // danh dau bat dau -> chuyen sang image->game over
	private boolean boss_died = false; // boss chet ->gameover
	private boolean boss_appared = false;
	private List<Monster> monsters; // mang quai
	private boolean win = false;
	private final int[][] position = { // vi tri quai->thay = random
			{ 250, 250 }, { 230, 230 }, { 200, 100 }, { 140, 120 }, { 80, 150 }, { 130, 140 }, { 100, 200 },
			{ 300, 200 }, { 310, 300 }, { 400, 310 }, { 420, 420 }, { 350, 500 }, { 230, 460 }, { 370, 280 },
			{ 30, 40 }, { 60, 60 } };
	public static Map m;
	Color bgcolor = new Color(207, 207, 207);

	public Board() {
		initBoard();
	}

	private void initBoard() {
		addKeyListener(new TAdapter()); // Nhan su kien tu ban phim
		setFocusable(true); // xu li su kien
		setBackground(Color.BLACK); // mau nen
		setDoubleBuffered(true);
		setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		m = new Map();
		ingame = true;
		started = false;
		hero = new Hero(TOADO_X, TOADO_Y);
		initMonsters();
		boss = new Boss(SIZE_X - 250, SIZE_Y / 2); // khoi tao boss ben phai man hinh
		timer = new Timer(DELAY, this); // nhan su kien sau 10dvth
		timer.start(); // gui toi actionPerformed
	}

	public void initMonsters() {
		monsters = new ArrayList<>();
		for (int[] p : position) { // them monster
			monsters.add(new Monster(p[0], p[1]));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!ingame) {
			timer.stop();
		} // game over ( ko nhan su kien nhan nua )
		updateFires();
		updateHero();
		updateMonster();
		updateBoss();
		if (boss_appared)
			updateStone();
		checkCollisions(); // kiem tra va chạm
		repaint(); // goi ra paintComponent
//	       System.out.println(hero.getLive());
	}

	private void updateFires() {
		List<Fire> fires = hero.getFires();
		for (int i = 0; i < fires.size(); i++) {
			Fire fire = fires.get(i); // doi tg 1viendan = mangdan.thui
			if (fire.getTontai()) {
				fire.move();
			} // vien dan di chuyen
			else {
				fires.remove(i);
			} // xoa vien dan khi ham move
		}

	}

	private void updateStone() {
		List<Stone> stones = boss.getStones();
		for (int i = 0; i < stones.size(); i++) {
			Stone stone = stones.get(i);
			if (stone.getTontai()) {
				stone.move();
			} else
				stones.remove(i);
		}
	}

	private void updateHero() {
		if (hero.getTontai()) {
			hero.move();
		}
		if (!hero.getTontai())
			ingame = false; // --> neu co mang -> tru mang
	}

	private void updateBoss() {
		if (boss_appared) {
			boss.move(hero.getY());
			if (boss.getHp() <= 0) {
				ingame = false;
				win = true;
			}

		}
	}

	private void updateMonster() {
		if (monsters.isEmpty() && boss_died) { // mang quai rong va boss chet
			ingame = false;
			return;
		}
		for (int i = 0; i < monsters.size(); i++) {
			Monster m = monsters.get(i);
			if (m.getTontai()) {
				m.move();
			} else
				monsters.remove(i);
		}
	}

	private void checkCollisions() {
		Rectangle hr = hero.getBounds(); // tao khung bao quanh nv
		for (Monster monster : monsters) { // kiem tra quai dam vao ng choi
			Rectangle ms = monster.getBounds();
			if (hr.intersects(ms)) { // 2 khung cham vao nhau
				if (hero.getLive() == 1)
					hero.setTontai(false);
				else
					hero.setLive(hero.getLive() - 1);
				monster.setTontai(false);
			}
		}
		List<Fire> frs = hero.getFires(); // fr : mang cac fire cua hhero
		for (Fire fr : frs) {
			Rectangle khung_fr = fr.getBounds(); // lay khung hinh dan ban ra
			for (Monster monster : monsters) {
				Rectangle ms = monster.getBounds(); // lay hinh tung con quai
				if (ms.intersects(khung_fr)) { // va cham dan va quai
					fr.setTontai(false);
					monster.setTontai(false);
				}
			}
			Rectangle bs = boss.getBounds(); // va cham dan va boss
			if (khung_fr.intersects(bs)) {
				boss.setHp(boss.getHp() - hero.getShoot_force());
				fr.setTontai(false);
			}
			;
		}
		if (boss_appared) {
			List<Stone> stones = boss.getStones(); // xu li va cham stones vs nhan vat
			for (Stone st : stones) {
				Rectangle st_rec = st.getBounds();
				if (hr.intersects(st_rec)) {
					if (hero.getLive() == 1)
						hero.setTontai(false);
					else
						hero.setLive(hero.getLive() - 1);
					st.setTontai(false);
				}
			}
			Rectangle bs = boss.getBounds();
			if (bs.intersects(hr)) {
				if (hero.getLive() == 1)
					hero.setTontai(false);
				else
					hero.setLive(hero.getLive() - 1);
				hero.setX(10);
				hero.setY(10);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// ve map
		ImageIcon treeImage = new ImageIcon("res/textures/img/tree_grass_1.png");
		// ImageIcon wallImage=new ImageIcon("res/textures/wall.png");
		// ImageIcon groundImage=new ImageIcon("res/textures/ground.png");
		ImageIcon grassImage = new ImageIcon("res/textures/img/grass.png");
		ImageIcon datImage = new ImageIcon("res/textures/img/dirt.png");
		ImageIcon thungImage = new ImageIcon("res/textures/img/rock_dirt.png");
		ImageIcon nuocImage = new ImageIcon("res/textures/img/water.png");
		ImageIcon cauImage = new ImageIcon("res/textures/img/dirt.png");
		ImageIcon monsterImage = new ImageIcon("res/textures/img/tauvutru.png");
		for (int y = 0; y < 20; y++)
			for (int x = 0; x < 20; x++) {
				if (m.getMap(x, y).equals("1")) {
					g.drawImage(new Tree(x, y, getTilesize(), getTilesize(), treeImage.getImage()).getImage(),
							x * getTilesize(), y * getTilesize(), null);
				} else if (m.getMap(x, y).equals("2")) {
					g.drawImage(new Water(x, y, getTilesize(), getTilesize(), nuocImage.getImage()).getImage(),
							x * getTilesize(), y * getTilesize(), null);
				} else if (m.getMap(x, y).equals("3")) {
					g.drawImage(new Rock(x, y, getTilesize(), getTilesize(), thungImage.getImage()).getImage(),
							x * getTilesize(), y * getTilesize(), null);
				} else if (m.getMap(x, y).equals("4")) {
					g.drawImage(new Bridge(x, y, getTilesize(), getTilesize(), cauImage.getImage()).getImage(),
							x * getTilesize(), y * getTilesize(), null);
				} else if (m.getMap(x, y).equals("5")) {
					g.drawImage(new Earth(x, y, getTilesize(), getTilesize(), datImage.getImage()).getImage(),
							x * getTilesize(), y * getTilesize(), null);
				} else if (m.getMap(x, y).equals("0")) {
					g.drawImage(new Grass(x, y, getTilesize(), getTilesize(), grassImage.getImage()).getImage(),
							x * getTilesize(), y * getTilesize(), null);
				}

			}
		if (started == false) {
//				IntroState intro = new IntroState();
//				intro.init();
			// private Image backgroundImage = ImageIO.read(new
			// File("res/textuers/img/thanos.png"));

			String msg = "Press S to start";
			Font small = new Font("Helvetica", Font.BOLD, 20);
			FontMetrics fm = getFontMetrics(small);

			g.setColor(Color.white);
			g.setFont(small);
			g.drawString(msg, (SIZE_X - fm.stringWidth(msg)) / 2, SIZE_Y / 2);
		} else if (win) {
			String msg = "You Win!";
			Font small = new Font("Helvetica", Font.BOLD, 20);
			FontMetrics fm = getFontMetrics(small);
			g.setColor(Color.white);
			g.setFont(small);
			g.drawString(msg, (SIZE_X - fm.stringWidth(msg)) / 2, SIZE_Y / 2);
		} else if (ingame) {
			g.drawImage(hero.getImage(), hero.getX(), hero.getY(), this); // ve hero
			List<Fire> fires = hero.getFires();
			for (Fire fire : fires) { // ve dan
				g.drawImage(fire.getImage(), fire.getX(), fire.getY(), this);
			}
			for (Monster monster : monsters) { // ve quai
				if (monster.getTontai()) {
					g.drawImage(monster.getImage(), monster.getX(), monster.getY(), this);
				}
			}
			List<Stone> stones = boss.getStones();
			for (Stone stone : stones) {
				g.drawImage(stone.getImage(), stone.getX(), stone.getY(), this);
			}
			if (boss_appared)
				g.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);
			g.setColor(Color.white);
			if (monsters.isEmpty()) {
				g.drawString("THANOS XUẤT HIỆN! ", SIZE_X - 120, SIZE_Y / 4);
				g.drawString("HP : " + boss.getHp(), SIZE_X - 120, SIZE_Y / 4 + 20);
				if (boss_appared == false)
					boss_appared = true;
			} else

				g.drawString("Còn: " + monsters.size() + " quái ", SIZE_X - 100, SIZE_Y / 4); // 10,10 : k/c tinh tu goc
																								// trai man hinh
			g.drawString("IronMan: " + hero.getLive() + " mạng", SIZE_X - 120, SIZE_Y / 4 + 50);
		} else {
			String msg = "Game Over";
			Font small = new Font("Helvetica", Font.BOLD, 20);
			FontMetrics fm = getFontMetrics(small);
			g.setColor(Color.white);
			g.setFont(small);
			g.drawString(msg, (SIZE_X - fm.stringWidth(msg)) / 2, SIZE_Y / 2);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	private class TAdapter extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			hero.keyReleased(e);
			int key = e.getKeyCode(); // danh dau vao game
			if ((key == 's' || key == 'S') && started == false)
				started = true;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			hero.keyPressed(e);
		}
	}

	public static int getSizeX() {
		return SIZE_X;
	}

	public static int getSizeY() {
		return SIZE_Y;
	}

	public static int getTilesize() {
		return tileSize;
	}

}
