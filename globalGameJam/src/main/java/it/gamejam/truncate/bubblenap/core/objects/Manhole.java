package it.gamejam.truncate.bubblenap.core.objects;

import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;
import java.awt.Image;
import java.util.concurrent.ThreadLocalRandom;

public class Manhole extends AbstractStaticObject{

  public Manhole(int x, int y, int width, int height, double dx, double dy) {
    super(x, y, width, height, dx, dy, StaticObjectType.MANHOLE, ImageLoader.getManhole()[ThreadLocalRandom.current().nextInt(0, ImageLoader.getManhole().length)]);
  }
}
