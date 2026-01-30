package it.gamejam.truncate.bubblenap.core;

import it.gamejam.truncate.bubblenap.core.enums.MaskType;
import java.awt.Image;

public abstract class AbstractEnemy extends MovingObject {

  protected MaskType maskType;

  public AbstractEnemy(int x, int y, int width, int height, double dx, double dy, MaskType maskType, Image image) {
    super(x, y, width, height, dx, dy, image);
    this.maskType = maskType;
    this.image = image;
  }
}
