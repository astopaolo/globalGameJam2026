package it.gamejam.truncate.maskordie.core.objects;

import it.gamejam.truncate.maskordie.ui.img.ImageLoader;

import java.util.concurrent.ThreadLocalRandom;

public class Manhole extends AbstractStaticObject{

  public Manhole(int x, int y, int width, int height, double dx, double dy) {
    super(x, y, width, height, dx, dy, StaticObjectType.MANHOLE, ImageLoader.getManhole()[ThreadLocalRandom.current().nextInt(0, ImageLoader.getManhole().length)]);
  }
}
