package Main;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;

import Entities.Hero;

public class Map {
	private Scanner m;
	private String Map[] = new String[20];

	public String getMap(int x, int y) {
		String index = Map[y].substring(x, x + 1);
		// String index=Map[y].substring(x, x);
		return index;
	}

	public int checkMapLeft(int x, int y, int width, int height) {
		x = x - 1;
		for (int i = 0; i < height; i++) {
			if (getMap(x / 32, y / 32).equals("1"))
				return 1;
			else if (getMap(x / 32, y / 32).equals("2"))
				return 2;
			else if (getMap(x / 32, y / 32).equals("3"))
				return 3;
			else if (getMap(x / 32, y / 32).equals("6"))
				return 6;
			else if (getMap(x / 32, y / 32).equals("9"))
				return 9;
			else {
				y = y + 1;
			}
		}
		return 0;
	}

	public int checkMapRight(int x, int y, int width, int height) {
		x = x + width + 1;
		for (int i = 0; i < height; i++) {
			if (getMap(x / 32, y / 32).equals("1"))
				return 1;
			else if (getMap(x / 32, y / 32).equals("3"))
				return 3;
			else if (getMap(x / 32, y / 32).equals("6"))
				return 6;
			else if (getMap(x / 32, y / 32).equals("9"))
				return 9;
			else {
				y = y + 1;
			}
		}
		return 0;
	}

	public int checkMapUp(int x, int y, int width, int height) {
		y = y - 1;
		for (int i = 0; i < width; i++) {
			if (getMap(x / 32, y / 32).equals("1"))
				return 1;
			else if (getMap(x / 32, y / 32).equals("3"))
				return 3;
			else if (getMap(x / 32, y / 32).equals("6"))
				return 6;
			else if (getMap(x / 32, y / 32).equals("9"))
				return 9;
			else {
				x = x + 1;
			}
		}
		return 0;
	}

	public int checkMapDown(int x, int y, int width, int height) {
		y = y + height + 1;
		for (int i = 0; i < width; i++) {
			if (getMap(x / 32, y / 32).equals("1"))
				return 1;
			else if (getMap(x / 32, y / 32).equals("3"))
				return 3;
			else if (getMap(x / 32, y / 32).equals("6"))
				return 6;
			else if (getMap(x / 32, y / 32).equals("9"))
				return 9;
			else {
				x = x + 1;
			}
		}
		return 0;
	}

	public int checkMapLeft(int x, int y, int width, int height, int event) { // tao ra 1 ham moi, tuong tu nhu
																																						// checkMapLeft/Right... nhung khi co them
																																						// tham so event thi se tu biet tra ve so
																																						// hieu object
		x = x - 1;
		for (int i = 0; i < height; i++) {
			if (getMap(x / 32, y / 32).equals("1"))
				return 1;
			else if (getMap(x / 32, y / 32).equals("3"))
				return 3;
			else if (getMap(x / 32, y / 32).equals("6"))
				return 6;
			else if (getMap(x / 32, y / 32).equals("9"))
				return 9;
			else {
				y = y + 1;
			}
		}
		return 0;
	}

	public int checkMapRight(int x, int y, int width, int height, int event) { // tao ra 1 ham moi, tuong tu nhu
																																							// checkMapLeft/Right... nhung khi co them
																																							// tham so event thi se tu biet tra ve so
																																							// hieu object
		x = x + width + 1;
		for (int i = 0; i < height; i++) {
			if (getMap(x / 32, y / 32).equals("1"))
				return 1;
			else if (getMap(x / 32, y / 32).equals("3"))
				return 3;
			else if (getMap(x / 32, y / 32).equals("6"))
				return 6;
			else if (getMap(x / 32, y / 32).equals("9"))
				return 9;
			else {
				y = y + 1;
			}
		}
		return 0;
	}

	public int checkMapUp(int x, int y, int width, int height, int event) { // tao ra 1 ham moi, tuong tu nhu
																																					// checkMapLeft/Right... nhung khi co them
																																					// tham so event thi se tu biet tra ve so hieu
																																					// object
		y = y - 1;
		for (int i = 0; i < width; i++) {

			if (getMap(x / 32, y / 32).equals("6"))
				return 6;
			else if (getMap(x / 32, y / 32).equals("1"))
				return 1;
			else if (getMap(x / 32, y / 32).equals("3"))
				return 3;
			else if (getMap(x / 32, y / 32).equals("9"))
				return 9;
			else {
				x = x + 1;
			}
		}
		return 0;
	}

	public int checkMapDown(int x, int y, int width, int height, int event) { // tao ra 1 ham moi, tuong tu nhu
																																						// checkMapLeft/Right... nhung khi co them
																																						// tham so event thi se tu biet tra ve so
																																						// hieu object
		y = y + height + 1;
		for (int i = 0; i < width; i++) {
			if (getMap(x / 32, y / 32).equals("6"))
				return 6;
			else if (getMap(x / 32, y / 32).equals("1"))
				return 1;
			else if (getMap(x / 32, y / 32).equals("3"))
				return 3;
			else if (getMap(x / 32, y / 32).equals("9"))
				return 9;
			else {
				x = x + 1;
			}
		}
		return 0;
	}

	String pathname = "res/worlds/map1.txt";

	public Map() {
		openFile(pathname);
		readFile();
		closeFile();
	}

	public void openFile() {
		try {
			// m= new Scanner(new File("src/data/map.txt"));
			m = new Scanner(new File("res/worlds/map1.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		}
	}

	public void openFile(String pathname) { // tao them 1 ham moi, giong ten ham cu nhung neu co path cu the se doc path
																					// nay
		try {
			// m= new Scanner(new File("src/data/map.txt"));
			m = new Scanner(new File(pathname));
		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		}
	}

	public void readFile() {
		while (m.hasNext()) {
			for (int i = 0; i < 20; i++) {
				Map[i] = m.next();
			}
		}
		System.out.println("Chuyen map"); // Test xem co nhan hay khong
	}

	public void closeFile() {
		m.close();
	}
}
