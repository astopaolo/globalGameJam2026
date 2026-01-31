package it.gamejam.truncate.bubblenap.core;

import java.awt.Image;

public abstract class MovingObject {

	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected double dx;
	protected double dy;
	protected Image image;
	protected Image scaledInstance;


	public MovingObject(final int x, final int y, final int width, final int height, final double dx, final double dy, final Image image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.dx = dx;
		this.dy = dy;
		this.image = image;
	}

	protected abstract void applyEffect(GameManager gameManager);


	public boolean collide(final MaskedPlayer player) {
		return player.getX()>=x-dx && player.getX()<=x+dx;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public int getHeight() {
		return height;
	}

	public Image getTransformedImage(int index) {
		return scaledInstance;
	}

	public int getWidth() {
		return width;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public void setDx(final double dx) {
		this.dx = dx;
	}

	public void setDy(final double dy) {
		this.dy = dy;
	}

	public void setHeight(final int height) {
		this.height = height;
	}

	public void setImage(final Image image) {
		this.image = image;
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public void setY(final int y) {
		this.y = y;
	}

	public void updatePosition(final long elapsed, double speed) {
		x -= (int)(speed * elapsed);
	}
}
