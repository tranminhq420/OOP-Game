package Entities;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class GameObject {
	protected int x,y,width,height;
	protected Image image;
	public GameObject(int x,int y,int width,int height,Image image){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
	}
	public GameObject(int x,int y){
		this.x = x;
		this.y = y;
	}
	public void draw(Graphics2D g2d){
			g2d.drawImage(image,x,y,width,height,null);	
	}
	protected void loadImage(String imageName) { 
	      ImageIcon img = new ImageIcon(imageName);
	      image = img.getImage();
	}
	protected void getImageDimension() { 
		  width  = image.getWidth(null);
		  height = image.getHeight(null);
	}  
	public void setImage(Image image) {
		this.image = image;
	}
	public Image getImage() {return image;}
    public int getX() {return x;}
    public int getY() {return y;}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
    
}
