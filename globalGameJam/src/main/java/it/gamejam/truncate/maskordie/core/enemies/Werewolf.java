package it.gamejam.truncate.maskordie.core.enemies;

import it.gamejam.truncate.maskordie.core.enums.MaskType;
import it.gamejam.truncate.maskordie.ui.img.ImageLoader;

import java.awt.*;

public class Werewolf extends AbstractEnemy {

  public Werewolf(int x, int y, int width, int height, double dx, double dy) {
    super(x, y, width, height, dx, dy, MaskType.WEREWOLF, ImageLoader.getWerewolf());
  }

@Override
protected Image _getTauntingImage() {
	return ImageLoader.getWerewolfTaunting();
}

}
