package Entities;
import java.util.Random;

import Main.Board;

public class Monster extends GameObjectDynamic {
	Random rd = new Random();
	private int timez=0;
	protected static final int MONSTER_SPEED =1;
	public Monster(int x, int y) {
		super(x, y);
        initMonster();
	}
    private void initMonster() {
    	loadImage("res/textures/img/tauvutru.png");
    	getImageDimension();
    }
	public void move() {
        if (getDirect() == 1 ) { x+=MONSTER_SPEED ;loadImage("res/textures/img/tauvutruright.png");}
        else if (getDirect() == -1 ) { x-=MONSTER_SPEED; loadImage("res/textures/img/tauvutruleft.png");}
        else if (getDirect() == 2 )  { y-=MONSTER_SPEED; loadImage("res/textures/img/tauvutrutop.png");}
        else if (getDirect() == -2 ) { y+=MONSTER_SPEED; loadImage("res/textures/img/tauvutrudown.png");}
        timez+=1; 
        if (timez==100) { //cứ sau 100 chu kỳ timer.DELAY lại chuyển hướng di chuyển
           setDirect(rd.nextInt(5)-2); // random hướng di chuyển  (0..5  -2 --> -2 ..2 hướng di chuyển đã quy định 0 tương ứng với đứng yên)
           timez=0;
        }
    	if (x<1) {x=1;} // ko cho di chuyển tràn khung
    	if (y<1) {y=1;}
    	this.getImageDimension();
    	if (x>Board.getSizeX()-this.width) {x=Board.getSizeX()-this.width;}
    	if (y>Board.getSizeY()-this.height) {y=Board.getSizeY()-this.height;}
    	if(!Board.m.checkMapLeft(x,y,this.width,this.height)) {
    		x=x+1;
    	}
    	if(!Board.m.checkMapRight(x,y,this.width,this.height)) {
    		x=x-1;
    	}
    	if(!Board.m.checkMapUp(x,y,this.width,this.height)) {
    		y=y+1;
    	}
    	if(!Board.m.checkMapDown(x,y,this.width,this.height)) {
    		y=y-1;
    	}   
	}
}
