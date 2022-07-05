package Entities;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Main.Board;
public class Boss extends Monster implements Boss_interface{
	Random rd = new Random();
	private int timez=0;

	private final int find_hero_speed=1; // cứ sau 20s tìm hero 1 lần
	private List<Stone> stones;
	private int hp;
	private final int HP_MAX=1000;
	int t=0,k=0;
	public Boss(int x, int y) {
		super(x, y);
		initBoss();
	}
	private void initBoss() {
		stones = new ArrayList<>();
		loadImage("res/textures/img/bossdown.png");
		getImageDimension(); // lấy kích thước ảnh
		this.hp=HP_MAX;
	}
	
	
	   
	
	@Override
	public void toStone() {  // quái ném đá
		int xz = 0 ,yz=0;  
    	if (getDirect()==1) { xz=x+width ; yz=y+height/2 ;}
    	   else if (getDirect()==-1) { xz=x ; yz=y+height/2;}
    	      else if (getDirect()==-2) {xz=x+width/2 ; yz=y+height;}
    	         else if (getDirect()==2) { xz=x+width/3 ; yz=y;}    	
    	Stone stone_new=new Stone(xz, yz);
    	stone_new.setDirect(getDirect());
    	stones.add(stone_new);
	}
	public List<Stone> getStones(){
		return stones;
	}
	public void move(int heroY) { // hàm move() có tham số >< move() kế thừa từ Monster
		if (getDirect() == 1 ) { x+=MONSTER_SPEED ;loadImage("res/textures/img/bossright.png");}
        else if (getDirect() == -1 ) { x-=MONSTER_SPEED; loadImage("res/textures/img/bossleft.png");}
        else if (getDirect() == 2 )  { y-=MONSTER_SPEED; loadImage("res/textures/img/bossup.png");}
        else if (getDirect() == -2 ) { y+=MONSTER_SPEED; loadImage("res/textures/img/bossdown.png");}
        timez+=1; 
        if (timez==100) { //cứ sau 100 chu kỳ timer.DELAY lại chuyển hướng di chuyển
           setDirect(rd.nextInt(5)-2); // random hướng di chuyển  (0..5  -2 --> -2 ..2 hướng di chuyển đã quy định 0 tương ứng với đứng yên)
           if (getDirect()==0) setDirect(-1);
           timez=0;
        }
    	if (x<1) {x=1;} // ko cho di chuyển tràn khung
    	if (y<1) {y=1;}
    	
    	this.getImageDimension();
    	if (x>Board.getSizeX()-150-this.width*2) {x=Board.getSizeX()-150-this.width*2;}
    	if (y>Board.getSizeY()-this.height*2) {y=Board.getSizeY()-this.height*2;}
    	
    	
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
		
		
		
		
        if (t<find_hero_speed) t++; // số chu kì tìm hero
        if (t==find_hero_speed) {
           if (getY()-heroY>MONSTER_SPEED)  { y-= MONSTER_SPEED ; }
           if (getY()-heroY<-MONSTER_SPEED) { y+= MONSTER_SPEED ; }
           t=0;
        }
        k++;
        if (getY()-heroY<height && k>=200) {
        	toStone();
            k=0;
        } ;
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
