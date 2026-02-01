package it.gamejam.truncate.bubblenap.core.objects;

import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;

public class Hydrant extends AbstractStaticObject{

  public Hydrant(int x, int y, int width, int height, double dx, double dy) {
    super(x, y, width, height, dx, dy, StaticObjectType.HYDRANT, ImageLoader.getHydrant());
  }

}
