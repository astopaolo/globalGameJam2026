package it.gamejam.truncate.bubblenap.core.enemies;

import it.gamejam.truncate.bubblenap.core.GameManager;
import it.gamejam.truncate.bubblenap.core.MovingObject;
import it.gamejam.truncate.bubblenap.core.enums.MaskType;
import java.awt.Image;
import java.util.Objects;

public abstract class AbstractEnemy extends MovingObject {

  protected MaskType maskType;
  protected Image[] image;
  protected Image[] scaledInstance;


  public AbstractEnemy(int x, int y, int width, int height, double dx, double dy, MaskType maskType, Image[] image) {
    super(x, y, width, height, dx, dy);
    this.maskType = maskType;
    this.image = image;
    resizeImage();
  }

  private void resizeImage() {
	  for(int i=0;i<image.length;i++) {
		  scaledInstance[i] = image[i].getScaledInstance(120, 220, Image.SCALE_SMOOTH);
	  }
  }

  @Override
  protected void applyEffect(GameManager gameManager) {
	  if(!Objects.equals(gameManager.getCurrentMask(), this.maskType)) {
//      gameManager.gameOver();
		  System.err.println("gameover");
    }else{
      //System.out.println("Enemy tricked!");
    }
  }
  @Override
  public Image getTransformedImage(int index) {
	  return scaledInstance[index];
  }
}
