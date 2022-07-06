package Entities;
import Main.*;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hero extends GameObjectDynamic {
	private int dx; 
	private int dy;
    private List<Fire> fires ;  
    private List<Skillshot> skillshots;
//    private int live = 3;
    private int moving ;   
    private int life = 6; // health
    private int maxLife = 6;
    private int speed = 3;
    private int maxMana = 10;
    private int mana = 10;
    private int attack = 2;
    private int defense = 1;
    private int skillAttack = 4;
    
    public Hero(int x,int y) { 
         super(x,y);
         fires = new ArrayList<>(); 
         skillshots = new ArrayList<>();
         loadImage("res/textures/img/down.png");
         getImageDimension();
    }
    public void move() {
    	x +=dx;
    	y +=dy;
    	if (x<1) {x=1;} 
    	if (y<1) {y=1;}
    	this.getImageDimension();
    	if (x>Board.getSizeX()-this.width) {x=Board.getSizeX()-this.width;}
    	if (y>Board.getSizeY()-this.height) {y=Board.getSizeY()-this.height;}
    	// ko cho đi xuyên tư�?ng
    	if(!Board.m.checkMapLeft(x,y,this.width,this.height)) {
    		x=x+speed;
    	}
    	if(!Board.m.checkMapRight(x,y,this.width,this.height)) {
    		x=x-speed;
    	}
    	if(!Board.m.checkMapUp(x,y,this.width,this.height)) {
    		y=y+speed;
    	}
    	if(!Board.m.checkMapDown(x,y,this.width,this.height)) {
    		y=y-speed;
    	}        
    }
    public List<Fire> getFires(){ 
    	return fires;
    }
    public void tofire() {
    	int xz = 0 ,yz=0;  
    	if (getDirect()==1) { xz=x+width ; yz=y+height/2 ;}
    	   else if (getDirect()==-1) { xz=x ; yz=y+height/2;}
    	      else if (getDirect()==-2) {xz=x+width/2 ; yz=y+height;}
    	         else if (getDirect()==2) { xz=x+width/3 ; yz=y;}    	
    	Fire fire_new=new Fire(xz,yz);
    	fire_new.setDirect(getDirect());
    	fires.add(fire_new);
    }
    public List<Skillshot> getSkillshots(){
    	return skillshots;
    }
    public void toSkillshot() {
    	if( mana >0 ) {
    		int xz = 0 ,yz=0;  
    	if (getDirect()==1) { xz=x+width ; yz=y+height/2 ;}
    	   else if (getDirect()==-1) { xz=x ; yz=y+height/2;}
    	      else if (getDirect()==-2) {xz=x+width/2 ; yz=y+height;}
    	         else if (getDirect()==2) { xz=x+width/3 ; yz=y;}    	
    	Skillshot skillshot_new=new Skillshot(xz,yz);
    	skillshot_new.setDirect(getDirect());
    	skillshots.add(skillshot_new);
    	mana -= skillshot_new.getUseCost();
    	}
    	
    	
    }
    public void keyPressed(KeyEvent e) {
	   int key = e.getKeyCode();
	   if (key == KeyEvent.VK_SPACE) { tofire();} 
	   if (key == KeyEvent.VK_A) { toSkillshot();} 
	   if (key == KeyEvent.VK_LEFT)  { dx = -speed; loadImage("res/textures/img/left.png") ; setDirect(-1);}
	   if (key == KeyEvent.VK_RIGHT) { dx =  speed; loadImage("res/textures/img/right.png") ; setDirect(1) ;}
	   if (key == KeyEvent.VK_UP)    { dy = -speed; loadImage("res/textures/img/up.png") ; setDirect(2) ;} 
	   if (key == KeyEvent.VK_DOWN)  { dy =  speed; loadImage("res/textures/img/down.png") ; setDirect(-2);}    	
    }
   
	public void keyReleased(KeyEvent e) {
	   int key = e.getKeyCode(); 
	   if (key == KeyEvent.VK_SPACE) { }
	   if (key == KeyEvent.VK_A) { }
	   if (key == KeyEvent.VK_LEFT)  { dx = 0; }
	   if (key == KeyEvent.VK_RIGHT) { dx = 0; }
	   if (key == KeyEvent.VK_UP)    { dy = 0; }
	   if (key == KeyEvent.VK_DOWN)  { dy = 0; }
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

}
//hello chua te Aram xin chao.