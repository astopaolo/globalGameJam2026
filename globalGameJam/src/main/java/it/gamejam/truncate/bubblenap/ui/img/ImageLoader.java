package it.gamejam.truncate.bubblenap.ui.img;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageLoader {


	private static Image credits;
	private static Image creditsPressed;

	private static Image exit;
	private static Image exitPressed;

	private static Image background;
	private static Image background_blur;

	private static Image play;
	private static Image playPressed;

	private static Image back;
	private static Image backPressed;

	private static Image[] werewolf;
	private static Image[] zombie;
	private static Image[] vampire;
	private static Image[] mummy;
	private static Image werewolfTaunting;
	private static Image zombieTaunting;
	private static Image vampireTaunting;
	private static Image mummyTaunting;
	private static Image werewolfHead;
	private static Image zombieHead;
	private static Image vampireHead;
	private static Image mummyHead;
	private static Image baseHead;

	private static BufferedImage[] player;
	private static BufferedImage[] playerMummy;
	private static BufferedImage[] playerVampire;
	private static BufferedImage[] playerWerewolf;
	private static BufferedImage[] playerZombie;
	private static BufferedImage[] playerBase;

	private static Image[] garbage;
	private static Image[] manhole;
	private static Image hydrant;

	private static BufferedImage bloodCurtain;
	private static Image gameScreen;
	private static Image gameScreen1;
	private static Image gameScreen2;
	private static Image scoreBackground;

	private static Image luigiMask;
	private static Image luigiNoMask;

	private static Image stefanoMask;
	private static Image stefanoNoMask;

	private static Image domenicoMask;
	private static Image domenicoNoMask;

	private static Image paoloMask;
	private static Image paoloNoMask;

	private static Image ernaniMask;
	private static Image ernaniNoMask;

	private static Image saverioMask;
	private static Image saverioNoMask;

	private static Image anxietyFrame;
	private static Image webcamFrame;

	private static List<Image> introVideoFrames;

	private static List<Image> gameOverVideoFrames;

	static {
		try {
			final int FRAMES = 8;
			zombie=new Image[8];
			werewolf=new Image[8];
			vampire=new Image[8];
			mummy=new Image[8];
			for(int i=1;i<=8;i++) {
				zombie[i-1]= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/zombie/"+i+".png"));
				werewolf[i-1]= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/werewolf/"+i+".png"));
				vampire[i-1]= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/vampire/"+i+".png"));
				mummy[i-1]= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/mummy/"+i+".png"));
			}
			baseHead = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/heads/base.png"));
			zombieHead= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/heads/zombie.png"));
			werewolfHead= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/heads/werewolf.png"));
			vampireHead= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/heads/vampire.png"));
			mummyHead= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/heads/mummy.png"));

			zombieTaunting= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/taunting/zombie.png"));
			werewolfTaunting= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/taunting/werewolf.png"));
			vampireTaunting= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/taunting/vampire.png"));
			mummyTaunting= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/taunting/mummy.png"));

			playerMummy = new BufferedImage[FRAMES];
			playerVampire = new BufferedImage[FRAMES];
			playerWerewolf = new BufferedImage[FRAMES];
			playerZombie = new BufferedImage[FRAMES];
			playerBase = new BufferedImage[FRAMES];

			for (int i = 1; i <= FRAMES; i++) {
				playerMummy[i-1] = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/Ragazzo/RAGAZZO MUMMIA/"+i+".png"));
				playerVampire[i-1] = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/Ragazzo/RAGAZZO VAMPIRO/"+i+".png"));
				playerWerewolf[i-1] = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/Ragazzo/RAGAZZO LUPO/"+i+".png"));
				playerZombie[i-1] = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/Ragazzo/RAGAZZO ZOMBIE/"+i+".png"));
				playerBase[i-1] = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/Ragazzo/BASE/"+i+".png"));
			}

			garbage = new Image[3];
			manhole = new Image[3];
			for (int i=0;i<3;i++) {
				garbage[i]= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/environment/garbage/"+(i+1)+".png"));
				manhole[i]= ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/environment/manhole/"+(i+1)+".png"));
			}
			hydrant = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/environment/hydrant/1.png"));

			bloodCurtain = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/blood_curtain.png"));
			background = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Background.png"));
			background_blur = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Background_Blur.png"));

			anxietyFrame = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/anxietyFrame.png"));
			webcamFrame = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/webcamFrame.png"));

			credits = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Credits.png"));

			creditsPressed = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Credits_Pressed.png"));

			exit = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Exit.png"));

			exitPressed = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Exit_Pressed.png"));

			play = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Play.png"));

			playPressed = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Play_Pressed.png"));

			back = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Back.png"));

			backPressed = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Back_Pressed.png"));

			gameScreen = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/GameScreen.png"));
			gameScreen1 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/GameScreen-1.png"));
			gameScreen2 = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/game/GameScreen-2.png"));

			scoreBackground = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/score_background.png"));


			luigiMask = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/credits/luigi_mask.png"));
			luigiNoMask = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/credits/luigi_no_mask.png"));

			stefanoMask = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/credits/stefano_mask.png"));
			stefanoNoMask = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/credits/stefano_no_mask.png"));

			domenicoMask = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/credits/domenico_mask.png"));
			domenicoNoMask = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/credits/domenico_no_mask.png"));

			paoloMask = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/credits/paolo_mask.png"));
			paoloNoMask = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/credits/paolo_no_mask.png"));

			ernaniMask = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/credits/ernani_mask.png"));
			ernaniNoMask = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/credits/ernani_no_mask.png"));

			saverioMask = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/credits/saverio_mask.png"));
			saverioNoMask = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/credits/saverio_no_mask.png"));
			introVideoFrames = getVideoFrames("img/video/intro/", 110);
			gameOverVideoFrames = getVideoFrames("img/video/gameover/", 70);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}


	public static List<Image> getGameOverVideoFrames() {
		return gameOverVideoFrames;
	}

	public static Image getGameScreen() {
		return gameScreen;
	}
	
	public static Image getGameScreen1() {
		return gameScreen1;
	}
	
	public static Image getGameScreen2() {
		return gameScreen2;
	}

	public static Image getImageBack() {
		return back;
	}

	public static Image getImageBackground() {
		return background;
	}

	public static Image getImageBackground_Blur() {
		return background_blur;
	}

	public static Image getImageBackPressed() {
		return backPressed;
	}

	public static Image getImageCredits() {
		return credits;
	}

	public static Image getImageCreditsPressed() {
		return creditsPressed;
	}

	public static BufferedImage getBloodCurtain() {
		return bloodCurtain;
	}

	public static Image getImageExit() {
		return exit;
	}

	public static Image getImageExit_Pressed() {
		return exitPressed;
	}

	public static Image getImagePlay() {
		return play;
	}

	public static Image getImagePlayPressed() {

		return playPressed;
	}

	public static Image getImageScoreBackground() {
		return scoreBackground;
	}

	public static List<Image> getIntroVideoFrames() {
		return introVideoFrames;
	}

	public static Image[] getWerewolf() {
		return werewolf;
	}

	public static Image[] getZombie() {
		return zombie;
	}

	public static Image[] getVampire() {
		return vampire;
	}

	public static Image[] getMummy() {
		return mummy;
	}

	public static Image[] getPlayer() {
		return player;
	}

	public static Image[] getPlayerMummy() { return playerMummy; }
	public static Image[] getPlayerVampire() { return playerVampire; }
	public static Image[] getPlayerWerewolf() { return playerWerewolf; }
	public static Image[] getPlayerZombie() { return playerZombie; }
	public static Image[] getPlayerBase() { return playerBase; }


	private static List<Image> getVideoFrames(final String dir, final int numberFrames) throws IOException {
		List<Image> frames = new ArrayList<>();

		for (int frameNumber = 1; frameNumber <= numberFrames; frameNumber++) {
			frames.add(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource(dir + frameNumber + ".png")));

		}
		return frames;
	}

    public static Image getLuigiMask() {
        return luigiMask;
    }

    public static Image getLuigiNoMask() {
        return luigiNoMask;
    }

    public static Image getStefanoMask() {
        return stefanoMask;
    }

    public static Image getStefanoNoMask() {
        return stefanoNoMask;
    }

    public static Image getDomenicoMask() {
        return domenicoMask;
    }

    public static Image getDomenicoNoMask() {
        return domenicoNoMask;
    }

    public static Image getPaoloMask() {
        return paoloMask;
    }

	public static Image getPaoloNoMask() {
        return paoloNoMask;
    }

    public static Image getErnaniMask() {
        return ernaniMask;
    }

    public static Image getErnaniNoMask() {
        return ernaniNoMask;
    }

    public static Image getSaverioMask() {
        return saverioMask;
    }

    public static Image getSaverioNoMask() {
        return saverioNoMask;
    }

	public static Image getWerewolfHead() {
		return werewolfHead;
	}

	public static Image getZombieHead() {
		return zombieHead;
	}

	public static Image getVampireHead() {
		return vampireHead;
	}

	public static Image getMummyHead() {
		return mummyHead;
	}

	public static Image getBaseHead() { return baseHead; }

	public static Image getAnxietyFrame() {
		return anxietyFrame;
	}
	
	public static Image getWebcamFrame() {
		return webcamFrame;
	}

	public static Image[] getGarbage() { return garbage; }

	public static Image[] getManhole() { return manhole; }

	public static Image getHydrant() { return hydrant; }
	public static Image getMummyTaunting() {
		return mummyTaunting;
	}
	public static Image getWerewolfTaunting() {
		return werewolfTaunting;
	}
	public static Image getVampireTaunting() {
		return vampireTaunting;
	}
	public static Image getZombieTaunting() {
		return zombieTaunting;
	}
}
