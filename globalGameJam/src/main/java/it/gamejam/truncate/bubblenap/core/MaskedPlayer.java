package it.gamejam.truncate.bubblenap.core;

import it.gamejam.truncate.bubblenap.core.enums.MaskType;

import java.awt.Color;
import java.awt.Image;

import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;
import java.util.EnumMap;
import java.util.Map;

public class MaskedPlayer {

	private int x;
	private int y;
	private int anxiety;
	private double speed;
	private final double initialSpeed=0.2;
	private final Map<MaskType, Image[]> imagesMap;
	private final Map<MaskType, Image> iconsMap;
	private final Map<MaskType, Color> colorsMap;
	private Image[] currentMaskScaledImage;
	private MaskType currentMask = MaskType.BASE;

	public MaskedPlayer( int x, int y) {
		super();
		this.x = x;
		this.y = y;
		speed=initialSpeed;
		imagesMap = new EnumMap<>(MaskType.class);
		iconsMap = new EnumMap<>(MaskType.class);
		colorsMap = new EnumMap<>(MaskType.class);
		imagesMap.put(MaskType.MUMMY, scale(ImageLoader.getPlayerMummy()));
		imagesMap.put(MaskType.VAMPIRE, scale(ImageLoader.getPlayerVampire()));
		imagesMap.put(MaskType.WEREWOLF, scale(ImageLoader.getPlayerWerewolf()));
		imagesMap.put(MaskType.ZOMBIE, scale(ImageLoader.getPlayerZombie()));
		imagesMap.put(MaskType.BASE, ImageLoader.getPlayerBase());
	
		iconsMap.put(MaskType.MUMMY, ImageLoader.getMummyHead());
		iconsMap.put(MaskType.VAMPIRE, ImageLoader.getVampireHead());
		iconsMap.put(MaskType.WEREWOLF, ImageLoader.getWerewolfHead());
		iconsMap.put(MaskType.ZOMBIE, ImageLoader.getZombieHead());
		iconsMap.put(MaskType.BASE, ImageLoader.getBaseHead());
		colorsMap.put(MaskType.MUMMY, new Color(242, 230, 208));
		colorsMap.put(MaskType.VAMPIRE, new Color(167, 132, 183));
		colorsMap.put(MaskType.WEREWOLF, new Color(164, 117, 83));
		colorsMap.put(MaskType.ZOMBIE, new Color(125, 168, 98));
		colorsMap.put(MaskType.BASE, new Color(233, 181, 147));
		
		setMask(currentMask);
	}

	private Image[] scale(Image[] source) {
		Image[] toReturn=new Image[source.length];
		for (int i = 0; i < toReturn.length; i++) {
			toReturn[i]=source[i].getScaledInstance(120, 220, Image.SCALE_SMOOTH);	
		}
		return toReturn;
	}

	public double getSpeed() {
		return speed;
	}

	public int getAnxiety() {
		return anxiety;
	}

	public void increaseAnxiety() {
		anxiety+=15;
	}

	public void decreaseAnxiety() {
		anxiety-=2;
		if(anxiety<0) {
			anxiety = 0;
		}
	}

	public void accelerate() {
		if(speed<3*initialSpeed) {
			speed*=1.1;
		}
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
	public Image getCurrentHead() {
		return iconsMap.get(currentMask);
	}

	public Image getScaledImage(int index) {
		return currentMaskScaledImage[index];
	}

	public int getImageCount() {
		return currentMaskScaledImage.length - 1;
	}

	public Image getMaskScaledImage(int index) {
		return currentMaskScaledImage[index];
	}

	public MaskType getMask() { return currentMask; }

	public void reset() {
		anxiety=0;
		speed=initialSpeed;
	}

	public Color getCurrentColor() {
		return colorsMap.get(currentMask);
	}
}
