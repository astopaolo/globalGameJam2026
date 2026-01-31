package it.gamejam.truncate.bubblenap.core.enemies;

import it.gamejam.truncate.bubblenap.core.GameManager;
import it.gamejam.truncate.bubblenap.core.enums.MaskType;
import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;
import java.awt.Image;

public class Werewolf extends AbstractEnemy {

  public Werewolf(int x, int y, int width, int height, double dx, double dy, MaskType maskType) {
    super(x, y, width, height, dx, dy, maskType, ImageLoader.getWerewolf());
  }

  @Override
  protected void applyEffect(GameManager gameManager) {
    gameManager.gameOver();
  }
}
