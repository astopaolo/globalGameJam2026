package it.gamejam.truncate.bubblenap.ui.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundProvider {
	private static byte[] menuSound;
	private static byte[] gameOver;
	private static byte[] videoIntro;
	private static byte[] femaleScream;
	private static byte[] bubleExplosion;


	static {
		try {

			try (FileInputStream fis = new FileInputStream("resources/audio/female-scream.wav")) {
				femaleScream = new byte[fis.available()];
				fis.read(femaleScream);
			}

			try (FileInputStream fis = new FileInputStream("resources/audio/game_over.wav")) {
				gameOver = new byte[fis.available()];
				fis.read(gameOver);
			}

			try (FileInputStream fis = new FileInputStream("resources/audio/menu-sound.wav")) {
				menuSound = new byte[fis.available()];
				fis.read(menuSound);
			}


		} catch (final Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}


	public static byte[] getBubleExplosion() {
		return bubleExplosion;
	}

	public static byte[] getFemaleScream() {
		return femaleScream;
	}

	public static byte[] getGameOver() {
		return gameOver;
	}


	public static byte[] getMenuSound() {
		return menuSound;
	}

	public static Map<String, byte[]> getSamples(final File dir) throws IOException {
		final Map<String, byte[]> samples = new HashMap<>();
		final File[] sampleFiles = dir.listFiles();
		for (final File file : sampleFiles) {
			try (FileInputStream fis = new FileInputStream(file)) {
				final byte[] tmp = new byte[fis.available()];
				fis.read(tmp);
				samples.put(file.getName(), tmp);
			}
		}
		return samples;
	}

	public static byte[] getVideoIntro() {
		return videoIntro;
	}

}
