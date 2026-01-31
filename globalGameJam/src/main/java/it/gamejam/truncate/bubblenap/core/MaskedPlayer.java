package it.gamejam.truncate.bubblenap.core;

import it.gamejam.truncate.bubblenap.core.enums.MaskType;
import java.awt.Image;

import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;
import java.util.EnumMap;
import java.util.Map;

public class MaskedPlayer {

	private int x;
	private int y;
	private int anxiety;
	private double speed;
	private final Map<MaskType, Image[]> imagesMap;
	private Image[] currentMaskScaledImage;
	private MaskType currentMask = MaskType.BASE;

	public MaskedPlayer( int x, int y) {
		super();
		this.x = x;
		this.y = y;
		speed=0.2;
		imagesMap = new EnumMap<>(MaskType.class);
		imagesMap.put(MaskType.MUMMY, ImageLoader.getPlayerMummy());
		imagesMap.put(MaskType.VAMPIRE, ImageLoader.getPlayerVampire());
		imagesMap.put(MaskType.WEREWOLF, ImageLoader.getPlayerWerewolf());
		imagesMap.put(MaskType.ZOMBIE, ImageLoader.getPlayerZombie());
		imagesMap.put(MaskType.BASE, ImageLoader.getPlayerBase());
		setMask(currentMask);
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

	public void setMask(MaskType mask) {
		this.currentMask = mask;
		this.currentMaskScaledImage = imagesMap.get(mask);
	}

	public Image getScaledImage(int index) {
		return currentMaskScaledImage[index].getScaledInstance(120, 220, Image.SCALE_SMOOTH);
	}

	public int getImageCount() {
		return currentMaskScaledImage.length - 1;
	}

	public Image getMaskScaledImage(int index) {
		return currentMaskScaledImage[index];
	}

	public MaskType getMask() { return currentMask; }
}
