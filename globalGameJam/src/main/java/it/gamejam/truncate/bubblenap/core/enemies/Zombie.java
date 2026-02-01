package it.gamejam.truncate.bubblenap.core.enemies;

import java.awt.Image;

import it.gamejam.truncate.bubblenap.core.enums.MaskType;
import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;

public class Zombie extends AbstractEnemy{

  public Zombie(int x, int y, int width, int height, double dx, double dy) {
    super(x, y, width, height, dx, dy, MaskType.ZOMBIE, ImageLoader.getZombie());
  }

@Override
protected Image _getTauntingImage() {
	return ImageLoader.getZombieTaunting();
}

}
