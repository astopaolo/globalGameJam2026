package it.gamejam.truncate.bubblenap.core;

public class MaskedPlayer {

	private int x;
	private int y;
	private double speed;

	public MaskedPlayer( int x, int y) {
		super();
		this.x = x;
		this.y = y;
		speed=0.2;
	}

	public double getSpeed() {
		return speed;
	}
	public void accelerate() {
		speed+=0.1;
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

}
