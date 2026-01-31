package it.gamejam.truncate.bubblenap.ui.img;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageLoader {

	private static Image creditsBed;

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

	private static Image playBackPressed;
	private static Image playBackRed;

	private static Image werewolf;
	private static Image zombie;
	private static Image vampire;
	private static Image mummy;

	private static Image gameover;
	private static Image gameScreen;
	private static Image bollaMuco;
	private static Image bollaMucoScoppiata;
	private static Image hourGlass;
	private static Image scoreBackground;

	private static List<Image> introVideoFrames;

	private static List<Image> gameOverVideoFrames;

	static {
		try {

			zombie = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/zombie.png"));

			werewolf = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/werewolf.png"));

			vampire = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/vampire.png"));

			mummy = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/game/enemies/mummy.png"));
//
//			creditsBed = ImageIO
//					.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/CreditsBed.png"));
//
			background = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Background.png"));
			background_blur = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Background_Blur.png"));

			credits = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Credits.png"));

			creditsPressed = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Credits_Pressed.png"));

			exit = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Exit.png"));

			exitPressed = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Exit_Pressed.png"));

			play = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Play.png"));

			playPressed = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Play_Pressed.png"));

			back = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Back.png"));

			backPressed = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/Back_Pressed.png"));
//			gameover = ImageIO
//					.read(Thread.currentThread().getContextClassLoader().getResource("img/game/gameover.png"));
//			bollaMuco = ImageIO
//					.read(Thread.currentThread().getContextClassLoader().getResource("img/game/bolla_muco.png"));
//			bollaMucoScoppiata = ImageIO.read(
//					Thread.currentThread().getContextClassLoader().getResource("img/game/bolla_muco_scoppiata.png"));
			gameScreen = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/game/GameScreen.png"));
//			hourGlass = ImageIO
//					.read(Thread.currentThread().getContextClassLoader().getResource("img/game/hourglass.png"));
//
			scoreBackground = ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource("img/menu/score_background.png"));
//
//			introVideoFrames = getVideoFrames("img/video/intro/", 100);
//			gameOverVideoFrames = getVideoFrames("img/video/gameover/", 85);

		} catch (final IOException e) {

			e.printStackTrace();
		}
	}


	public static Image getCreditsBed() {
		return creditsBed;
	}


	public static List<Image> getGameOverVideoFrames() {
		return gameOverVideoFrames;
	}

	public static Image getGameScreen() {
		return gameScreen;
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

	public static Image getWerewolf() { return werewolf; }

	public static Image getZombie() { return zombie; }

	public static Image getVampire() { return vampire; }

  public static Image getMummy() { return mummy; }

	public static Image getPlayBackPressed() {
		return playBackPressed;
	}

	public static Image getPlayBackWhite() {
		return playBackRed;
	}

	private static List<Image> getVideoFrames(final String dir, final int numberFrames) throws IOException {
		List<Image> frames = new ArrayList<>();

		for (int frameNumber = 1; frameNumber <= numberFrames; frameNumber++) {
			frames.add(ImageIO
					.read(Thread.currentThread().getContextClassLoader().getResource(dir + frameNumber + ".png")));

		}
		return frames;
	}

}
