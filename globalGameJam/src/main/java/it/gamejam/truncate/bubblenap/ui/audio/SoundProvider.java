package it.gamejam.truncate.bubblenap.ui.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundProvider {
	private static byte[] menuSound;
	private static byte[] gameSound;
	private static byte[] gameOverBackground;
	private static byte[] gameOverBreath;
	private static byte[] introSound;
	private static byte[] hitSound;

	static {

		try {

			try (FileInputStream fis = new FileInputStream("resources/audio/intro-sound.wav")) {
				introSound = new byte[fis.available()];
				fis.read(introSound);
			}
			try (FileInputStream fis = new FileInputStream("resources/audio/game-sound.wav")) {
				gameSound = new byte[fis.available()];
				fis.read(gameSound);
			}

			try (FileInputStream fis = new FileInputStream("resources/audio/game-over-background.wav")) {
				gameOverBackground = new byte[fis.available()];
				fis.read(gameOverBackground);
			}

			try (FileInputStream fis = new FileInputStream("resources/audio/game-over-breath.wav")) {
				gameOverBreath = new byte[fis.available()];
				fis.read(gameOverBreath);
			}

			try (FileInputStream fis = new FileInputStream("resources/audio/menu-sound.wav")) {
				menuSound = new byte[fis.available()];
				fis.read(menuSound);
			}

			try (FileInputStream fis = new FileInputStream("resources/audio/scream-male.wav")) {
				hitSound = new byte[fis.available()];
				fis.read(hitSound);
			}
		} catch (final Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static byte[] getHitSound() {
		return hitSound;
	}


	public static byte[] getGameOverBreath() {
		return gameOverBreath;
	}

	public static byte[] getGameOverBackground() {
		return gameOverBackground;
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


	public static byte[] getGameSound() {
		return gameSound;
	}

	public static byte[] getIntroSound() {
		return introSound;
	}
}
