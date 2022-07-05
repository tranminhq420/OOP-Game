package Entities;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

public class GameObjectDynamic extends GameObject {
	 public GameObjectDynamic(int x, int y) {
			super(x, y);
			setTontai(true);
			// TODO Auto-generated constructor stub
		}
	   protected boolean tontai; // còn sống                    2
	   protected int direct=-2; // đánh dấu hướng di chuyển  -1   1 
	   public boolean getTontai() { return tontai ;}
	   public void setTontai(boolean x) {this.tontai = x;}  
	   
	   public Rectangle getBounds() { // khung bao quanh hình ảnh --> va chạm
		   return new Rectangle(x,y,width,height);
	   }
	   public int getDirect() { return direct;} // get + set
	   public void setDirect(int direct) { this.direct = direct;}
	   public void move() {} ;
}
