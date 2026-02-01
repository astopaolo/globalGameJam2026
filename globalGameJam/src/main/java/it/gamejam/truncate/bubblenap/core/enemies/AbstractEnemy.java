package it.gamejam.truncate.bubblenap.core.enemies;

import it.gamejam.truncate.bubblenap.core.GameManager;
import it.gamejam.truncate.bubblenap.core.MovingObject;
import it.gamejam.truncate.bubblenap.core.enums.MaskType;
import it.gamejam.truncate.bubblenap.ui.audio.SimpleAudioPlayer;
import it.gamejam.truncate.bubblenap.ui.audio.SoundProvider;
import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;

import java.awt.Image;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractEnemy extends MovingObject {

  protected MaskType maskType;
  protected Image[] image;
  protected Image[] scaledInstance;
  protected boolean active = true;
  private long startTaunting=0;


  public AbstractEnemy(int x, int y, int width, int height, double dx, double dy, MaskType maskType, Image[] image) {
    super(x, y, width, height, dx, dy);
    this.maskType = maskType;
    this.image = image;
    resizeImage();
  }

  private void resizeImage() {
	  scaledInstance=new Image[image.length];
	  for(int i=0;i<image.length;i++) {
		  scaledInstance[i] = image[i];//.getScaledInstance(120, 220, Image.SCALE_SMOOTH);
	  }
  }

  @Override
  protected void applyEffect(GameManager gameManager) {
	  if(active) {
		  active = false;
		if (!Objects.equals(gameManager.getCurrentMask(), this.maskType)) {
			  gameManager.getMaskedPlayer().increaseAnxiety();
			  startTaunting = System.currentTimeMillis();
        new Thread(() -> SimpleAudioPlayer.playSyncSoundOnce(SoundProvider.getHitSound()[(ThreadLocalRandom.current()
            .nextInt(0, SoundProvider.getHitSound().length))], 5f)).start();
			  if(gameManager.getMaskedPlayer().getAnxiety()>=100) {
				  gameManager.gameOver();
			  }
		} else {
			  gameManager.getMaskedPlayer().decreaseAnxiety();
		}
	} 
  }
  @Override
  public Image getTransformedImage(int index) {
    return scaledInstance[index];
  }
  protected abstract Image _getTauntingImage(); 
  
  public Image getTauntingImage() {
	  if(startTaunting>0 && System.currentTimeMillis()-startTaunting<1000) {
		  return _getTauntingImage();//.getScaledInstance(200, 200, Image.SCALE_SMOOTH);		  
	  }else {
		  startTaunting=0;
		  return null;
	  }
  }

  public int getImageCount() {
    return scaledInstance.length;
  }
  
}
