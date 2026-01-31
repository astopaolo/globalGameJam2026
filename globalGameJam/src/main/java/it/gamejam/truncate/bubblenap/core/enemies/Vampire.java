package it.gamejam.truncate.bubblenap.core.enemies;

import it.gamejam.truncate.bubblenap.core.GameManager;
import it.gamejam.truncate.bubblenap.core.enums.MaskType;
import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;
import java.awt.Image;

public class Vampire extends AbstractEnemy{

  public Vampire(int x, int y, int width, int height, double dx, double dy) {
    super(x, y, width, height, dx, dy, MaskType.VAMPIRE, ImageLoader.getVampire());
  }

}
