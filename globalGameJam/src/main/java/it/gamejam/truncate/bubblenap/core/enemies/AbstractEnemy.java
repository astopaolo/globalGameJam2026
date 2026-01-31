package it.gamejam.truncate.bubblenap.core.enemies;

import it.gamejam.truncate.bubblenap.core.MovingObject;
import it.gamejam.truncate.bubblenap.core.enums.MaskType;
import java.awt.Image;

public abstract class AbstractEnemy extends MovingObject {

  protected MaskType maskType;

  public AbstractEnemy(int x, int y, int width, int height, double dx, double dy, MaskType maskType, Image image) {
    super(x, y, width, height, dx, dy, image);
    this.maskType = maskType;
    this.image = image;
  }

  public void downsizeImage() {
    int newWidth = (int) (this.width * 0.4);
    int newHeight = (int) (this.height * 0.4);
    this.width = newWidth;
    this.height = newHeight;
  }
}
