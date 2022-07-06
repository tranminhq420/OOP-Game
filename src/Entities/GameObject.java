package Entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

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
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		//Tao ra mot anh rong co chieu dai va chieu rong kieu la png
		BufferedImage scaledImage = new BufferedImage(width,height,original.getType());
//		g2 tro den scaledImage co do hoa 
		Graphics2D g2 = scaledImage.createGraphics();
//		Ve len ScaledImage voi ti le da keo dai ra
//		Tile image luu 16x16 px con day la 48x48 px
		g2.drawImage(original, 0, 0,width, height, null);
		g2.dispose();
		return scaledImage;
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
