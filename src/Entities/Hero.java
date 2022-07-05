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
    private int moving ;   
    private final int shoot_force =50; // lực bắn
    private int live =3;
    public Hero(int x,int y) { 
         super(x,y);
         fires = new ArrayList<>(); 
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
    public void keyPressed(KeyEvent e) {
	   int key = e.getKeyCode();
	   if (key == KeyEvent.VK_SPACE) { tofire();} 
	   if (key == KeyEvent.VK_LEFT)  { dx = -1; loadImage("res/textures/img/left.png") ; setDirect(-1);}
	   if (key == KeyEvent.VK_RIGHT) { dx =  1; loadImage("res/textures/img/right.png") ; setDirect(1) ;}
	   if (key == KeyEvent.VK_UP)    { dy = -1; loadImage("res/textures/img/up.png") ; setDirect(2) ;} 
	   if (key == KeyEvent.VK_DOWN)  { dy =  1; loadImage("res/textures/img/down.png") ; setDirect(-2);}    	
    }
   
	public void keyReleased(KeyEvent e) {
	   int key = e.getKeyCode(); 
	   if (key == KeyEvent.VK_SPACE) { }
	   if (key == KeyEvent.VK_LEFT)  { dx = 0; }
	   if (key == KeyEvent.VK_RIGHT) { dx = 0; }
	   if (key == KeyEvent.VK_UP)    { dy = 0; }
	   if (key == KeyEvent.VK_DOWN)  { dy = 0; }
    }
	public int getShoot_force() {
		return shoot_force;
	}
	public int getLive() {
		return live;
	}
	public void setLive(int live) {
		this.live = live;
	}
	
	

}
