package Entities;

import Entities.GameObjectDynamic.Direction;

public class Monkey extends Monster{

   private static final int SPEED = 1;
   private static final int MAX_LIFE = 8;
   private static final int ATTACK = 200;
	private static final int DEFENSE = 1;


	public Monkey(int x, int y) {
		super(x, y);
	}


}