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
	// private static final int tileSize = 32; thay cho so 32 hien tai
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
		updateSkillshots();
		updateHero();
		updateMonster();
		updateBoss();
		if (boss_appared)
			updateStone();
		checkCollisions(); // kiem tra va chạm
		repaint(); // goi ra paintComponent
//	       System.out.println(hero.getLife());
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

	private void updateSkillshots() {
		List<Skillshot> skillshots = hero.getSkillshots();
		for (int j = 0; j < skillshots.size(); j++) {
			Skillshot skillshot = skillshots.get(j); // doi tg 1viendan = mangdan.thui
			if (skillshot.getTontai()) {
				skillshot.move();
			} // vien dan di chuyen
			else {
				skillshots.remove(j);
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
		if (hero.getHeroGP().getTontai()) {
			hero.move();
		}
		if (!hero.getHeroGP().getTontai()) {
			ingame = false; // --> neu co mang -> tru mang
		}
	}

	private void updateBoss() {
		if (boss_appared) {
			boss.move(hero.getHeroGP().getY());
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
			if (m.getMonsterGP().getTontai()) {
				m.move();
			} else
				monsters.remove(i);
		}
	}

	private void checkCollisions() {
		Rectangle hr = hero.getHeroGP().getBounds(); // tao khung bao quanh nv
		for (Monster monster : monsters) { // kiem tra quai dam vao ng choi
			Rectangle ms = monster.getMonsterGP().getBounds();
			if (hr.intersects(ms)) { // 2 khung cham vao nhau
				hero.setLife(hero.getLife() - (monster.getAttack() - hero.getDefense()));
				if (hero.getLife() == 0)
					hero.getHeroGP().setTontai(false);

				monster.setLife(monster.getLife() - 1);
				if (monster.getLife() == 0)
					monster.getMonsterGP().setTontai(false);
			}
		}
		List<Fire> frs = hero.getFires(); // fr : mang cac fire cua hhero
		for (Fire fr : frs) {
			Rectangle khung_fr = fr.getBounds(); // lay khung hinh dan ban ra
			for (Monster monster : monsters) {
				Rectangle ms = monster.getMonsterGP().getBounds(); // lay hinh tung con quai
				if (ms.intersects(khung_fr)) { // va cham dan va quai
					fr.setTontai(false);
					monster.setLife(monster.getLife() - hero.getAttack());
					if (monster.getLife() == 0)
						monster.getMonsterGP().setTontai(false);
				}
			}
			Rectangle bs = boss.getMonsterGP().getBounds(); // va cham dan va boss
			if (khung_fr.intersects(bs)) {
				boss.setHp(boss.getHp() - hero.getAttack());
				fr.setTontai(false);
			}
			;
		}
		List<Skillshot> sks = hero.getSkillshots(); // fr : mang cac fire cua hero
		for (Skillshot sk : sks) {
			Rectangle khung_fr2 = sk.getBounds(); // lay khung hinh dan ban ra
			for (Monster monster : monsters) {
				Rectangle ms = monster.getMonsterGP().getBounds(); // lay hinh tung con quai
				if (ms.intersects(khung_fr2)) { // va cham dan va quai
					sk.setTontai(false);
					monster.setLife(monster.getLife() - hero.getSkillAttack());
					if (monster.getLife() == 0)
						monster.getMonsterGP().setTontai(false);
				}
			}
			Rectangle bs = boss.getMonsterGP().getBounds(); // va cham dan va boss
			if (khung_fr2.intersects(bs)) {
				boss.setHp(boss.getHp() - hero.getSkillAttack());
				sk.setTontai(false);
			}
			;
		}

		if (boss_appared) {
			List<Stone> stones = boss.getStones(); // xu li va cham stones vs nhan vat
			for (Stone st : stones) {
				Rectangle st_rec = st.getBounds();
				if (hr.intersects(st_rec)) {
					if (hero.getLife() == 1)
						hero.getHeroGP().setTontai(false);
					else
						hero.setLife(hero.getLife() - 1);
					st.setTontai(false);
				}
			}
			Rectangle bs = boss.getMonsterGP().getBounds();
			if (bs.intersects(hr)) {
				if (hero.getLife() == 1)
					hero.getHeroGP().setTontai(false);
				else
					hero.setLife(hero.getLife() - 1);
				hero.getHeroGP().setX(10);
				hero.getHeroGP().setY(10);
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
					g.drawImage(new Tree(x, y, 32, 32, treeImage.getImage()).getImage(), x * 32, y * 32, null);
				} else if (m.getMap(x, y).equals("2")) {
					g.drawImage(new Water(x, y, 32, 32, nuocImage.getImage()).getImage(), x * 32, y * 32, null);
				} else if (m.getMap(x, y).equals("3")) {
					g.drawImage(new Rock(x, y, 32, 32, thungImage.getImage()).getImage(), x * 32, y * 32, null);
				} else if (m.getMap(x, y).equals("4")) {
					g.drawImage(new Bridge(x, y, 32, 32, cauImage.getImage()).getImage(), x * 32, y * 32, null);
				} else if (m.getMap(x, y).equals("5")) {
					g.drawImage(new Earth(x, y, 32, 32, datImage.getImage()).getImage(), x * 32, y * 32, null);
				} else if (m.getMap(x, y).equals("0")) {
					g.drawImage(new Grass(x, y, 32, 32, grassImage.getImage()).getImage(), x * 32, y * 32, null);
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
			g.drawImage(hero.getHeroGP().getImage(), hero.getHeroGP().getX(), hero.getHeroGP().getY(), this); // ve hero

			List<Fire> fires = hero.getFires();
			for (Fire fire : fires) { // ve dan
				g.drawImage(fire.getImage(), fire.getX(), fire.getY(), this);
			}
			// ve skillshot
			List<Skillshot> skillshots = hero.getSkillshots();
			for (Skillshot skillshot : skillshots) { // ve dan
				g.drawImage(skillshot.getImage(), skillshot.getX(), skillshot.getY(), this);
			}

			for (Monster monster : monsters) { // ve quai
				if (monster.getMonsterGP().getTontai()) {
					g.drawImage(monster.getMonsterGP().getImage(), monster.getMonsterGP().getX(), monster.getMonsterGP().getY(), this);
				}
			}
			List<Stone> stones = boss.getStones();
			for (Stone stone : stones) {
				g.drawImage(stone.getImage(), stone.getX(), stone.getY(), this);
			}
			if (boss_appared)
				g.drawImage(boss.getMonsterGP().getImage(), boss.getMonsterGP().getX(), boss.getMonsterGP().getY(), this);
			g.setColor(Color.white);
			if (monsters.isEmpty()) {
				g.drawString("THANOS XUẤT HIỆN! ", SIZE_X - 120, SIZE_Y / 4);
				g.drawString("HP : " + boss.getHp(), SIZE_X - 120, SIZE_Y / 4 + 20);
				if (boss_appared == false)
					boss_appared = true;
			} else

				g.drawString("Monsters: " + monsters.size(), SIZE_X - 100, SIZE_Y / 4); // 10,10 : k/c tinh tu goc trai
																						// man hinh
			g.drawString("Health: " + hero.getLife(), SIZE_X - 100, SIZE_Y / 4 + 50);
			g.drawString("Speed : " + hero.getSpeed(), SIZE_X - 100, SIZE_Y / 4 + 70);
			g.drawString("Mana : " + hero.getMana(), SIZE_X - 100, SIZE_Y / 4 + 90);
			g.drawString("Attack : " + hero.getAttack(), SIZE_X - 100, SIZE_Y / 4 + 110);
			g.drawString("Defense : " + hero.getDefense(), SIZE_X - 100, SIZE_Y / 4 + 130);

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

}
