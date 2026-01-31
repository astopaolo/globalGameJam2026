package it.gamejam.truncate.bubblenap.core.enemies;

import it.gamejam.truncate.bubblenap.core.GameManager;
import it.gamejam.truncate.bubblenap.core.MovingObject;
import it.gamejam.truncate.bubblenap.core.enums.MaskType;
import java.awt.Image;
import java.util.Objects;

public abstract class AbstractEnemy extends MovingObject {

  protected MaskType maskType;


  public AbstractEnemy(int x, int y, int width, int height, double dx, double dy, MaskType maskType, Image image) {
    super(x, y, width, height, dx, dy, image);
    this.maskType = maskType;
    this.image = image;
    resizeImage();
  }

  public void resizeImage() {
	  scaledInstance = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
  }

  @Override
  protected void applyEffect(GameManager gameManager) {
	  if(!Objects.equals(gameManager.getCurrentMask(), this.maskType)) {
//      gameManager.gameOver();
		  System.err.println("gameover");
    }else{
      System.out.println("Enemy tricked!");
    }
  }
}
