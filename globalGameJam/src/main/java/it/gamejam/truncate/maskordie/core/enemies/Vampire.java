package it.gamejam.truncate.maskordie.core.enemies;

import it.gamejam.truncate.maskordie.core.enums.MaskType;
import it.gamejam.truncate.maskordie.ui.img.ImageLoader;

import java.awt.*;

public class Vampire extends AbstractEnemy{

  public Vampire(int x, int y, int width, int height, double dx, double dy) {
    super(x, y, width, height, dx, dy, MaskType.VAMPIRE, ImageLoader.getVampire());
  }

@Override
protected Image _getTauntingImage() {
	return ImageLoader.getVampireTaunting();
}

}
