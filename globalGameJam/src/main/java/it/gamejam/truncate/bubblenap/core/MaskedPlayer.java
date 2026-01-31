package it.gamejam.truncate.bubblenap.core;

import java.awt.Image;

import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;

public class MaskedPlayer {

	private int x;
	private int y;
	private double speed;
	private Image[] image;  
	private Image[] scaledImage;  

	public MaskedPlayer( int x, int y) {
		super();
		this.x = x;
		this.y = y;
		speed=0.2;
		image=ImageLoader.getMummy();
		for(int i=0;i<image.length;i++) {
			scaledImage[i] = image[i].getScaledInstance(120, 220, Image.SCALE_SMOOTH);
		}
	}

	public double getSpeed() {
		return speed;
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
}
