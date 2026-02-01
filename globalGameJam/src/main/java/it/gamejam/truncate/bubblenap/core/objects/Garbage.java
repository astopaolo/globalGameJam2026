package it.gamejam.truncate.bubblenap.core.objects;

import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;
import java.util.concurrent.ThreadLocalRandom;

public class Garbage extends AbstractStaticObject {

  public Garbage(int x, int y, int width, int height, double dx, double dy) {
    super(x, y, width, height, dx, dy, StaticObjectType.GARBAGE, ImageLoader.getGarbage()[ThreadLocalRandom.current().nextInt(0, ImageLoader.getGarbage().length)]);
  }

}
