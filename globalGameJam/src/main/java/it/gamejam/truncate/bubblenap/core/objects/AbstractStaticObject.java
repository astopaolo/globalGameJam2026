package it.gamejam.truncate.bubblenap.core.objects;

import it.gamejam.truncate.bubblenap.core.GameManager;
import it.gamejam.truncate.bubblenap.core.MovingObject;
import java.awt.Image;

public class AbstractStaticObject extends MovingObject {

  protected StaticObjectType type;
  protected Image image;
  protected Image scaledInstance;

  public AbstractStaticObject(int x, int y, int width, int height, double dx, double dy, StaticObjectType type, Image image) {
    super(x, y, width, height, dx, dy);
    this.type = type;
    this.image = image;
    resizeImage();
  }

  @Override
  protected void applyEffect(GameManager gameManager) { /* No effect */ }

  @Override
  public Image getTransformedImage(int index) {
    return scaledInstance;
  }

  private void resizeImage() {
    scaledInstance = image.getScaledInstance(this.width, this.height, Image.SCALE_SMOOTH);
  }
}
