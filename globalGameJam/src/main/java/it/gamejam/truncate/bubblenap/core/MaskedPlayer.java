package it.gamejam.truncate.bubblenap.core;

import java.awt.Image;

import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;

public class MaskedPlayer {

	private int x;
	private int y;
	private int anxiety;
	private double speed;
	private Image[] image;  
	private Image[] scaledImage;  

	public MaskedPlayer( int x, int y) {
		super();
		this.x = x;
		this.y = y;
		speed=0.2;
		image=ImageLoader.getMummy();
		scaledImage=new Image[image.length];
		for(int i=0;i<image.length;i++) {
			scaledImage[i] = image[i].getScaledInstance(120, 220, Image.SCALE_SMOOTH);
		}
	}

	public double getSpeed() {
		return speed;
	}
	
	public int getAnxiety() {
		return anxiety;
	}
	
	public void increaseAnxiety() {
		anxiety+=10;
	}	

	public void decreaseAnxiety() {
		anxiety-=5;
	}	
	
	public void accelerate() {
		speed*=1.1;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getScaledImage(int index) {
		return scaledImage[index];
	}

	public int getImageCount() {
		return scaledImage.length;
	}
}
