
package it.gamejam.truncate.bubblenap.ui;

import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPanel.DrawMode;
import it.gamejam.truncate.bubblenap.core.GameManager;
import it.gamejam.truncate.bubblenap.ui.audio.SimpleAudioPlayer;
import it.gamejam.truncate.bubblenap.ui.audio.SoundProvider;
import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Optional;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -6974599828262854447L;

	private static SimpleAudioPlayer backgroundMusic;
	private static SimpleAudioPlayer gameMusic;

	public static SimpleAudioPlayer getBackgroundMusic() {
		return backgroundMusic;
	}

	ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("img/game/enemies/heads/werewolf.png"));

	public static void main(final String[] args) throws Exception {



		final JFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
		backgroundMusic = new SimpleAudioPlayer(SoundProvider.getMenuSound(), 1f);
		backgroundMusic.playLoop();


		gameMusic = new SimpleAudioPlayer(SoundProvider.getGameSound(), 1f);
		gameMusic.playLoop();
		gameMusic.stop();

	}


	private final MenuPanel menuPanel;

	private final CreditsMenuPanel creditsMenu;
	private final GamePanel gamePanel;
	private final VideoPanel introVideoPanel;
	private final VideoPanel gameOverVideoPanel;
	private final ScorePanel scorePanel;

	private GameManager gameManager;

	private JDialog dialog;

	public MainFrame() {
		this.setIconImage(logo.getImage());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		gameManager = new GameManager();
		setTitle("Mask or Die");
		menuPanel = new MenuPanel(this);
		creditsMenu = new CreditsMenuPanel(this);
		introVideoPanel = new VideoPanel(this, ImageLoader.getIntroVideoFrames(), EnumPanel.GAME_PANEL,
				List.of(SoundProvider.getIntroSound()), Optional.empty());

		gamePanel = new GamePanel(gameManager, this);
		scorePanel = new ScorePanel(gameManager, this);
		gameOverVideoPanel = new VideoPanel(this, ImageLoader.getGameOverVideoFrames(), EnumPanel.SCORE_PANEL,
				List.of(SoundProvider.getGameOverBackground(), SoundProvider.getGameOverBreath()), Optional.of("Am I dreaming...?"));

		gameManager.setRepaintable(gamePanel);
		setUndecorated(true);
		this.setContentPane(menuPanel);
		pack();
		setLocationRelativeTo(null);
		WebcamPanel webcam = new WebcamPanel(gameManager.getClassifier().getWebcam()) ;
		webcam.setPreferredSize(new Dimension(240,180));
		webcam.setDrawMode(DrawMode.FIT);
		dialog = new JDialog(this);
		dialog.setUndecorated(true);
//        dialog.setLocationRelativeTo(button);
        dialog.setModal(true);
        dialog.add(webcam);
        dialog.pack();
        gameManager.setWebcamDialog(dialog);
	}

	public void drawPanel(final EnumPanel panel) {
		drawPanel(panel, false);
	}

	public void drawPanel(final EnumPanel panel, final boolean restartMusic) {

		switch (panel) {
		case MENU_PANEL:
			this.setContentPane(menuPanel);
			if (restartMusic) {
				try {
					gameMusic.stop();
					backgroundMusic.restart();
				} catch (final Exception e1) {
					e1.printStackTrace();
				}
			}
			break;

		case CREDITS_MENU_PANEL:
			this.setContentPane(creditsMenu);
			break;
		case GAME_PANEL:
			this.setContentPane(gamePanel);
			if (restartMusic) {
				try {
					backgroundMusic.stop();
					gameMusic.restart();
				} catch (final Exception e1) {
					e1.printStackTrace();
				}
			}

//			gamePanel.requestFocus();
			SwingUtilities.invokeLater(()->{showWebcamDialog();});
			try {
				gamePanel.startGame();
			} catch (final Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
			break;
		case INTRO_VIDEO_PANEL:
			this.setContentPane(introVideoPanel);
			introVideoPanel.playVideoAndDrawNextPanel(true);
			break;
		case GAME_OVER_VIDEO_PANEL:
			gameMusic.stop();
			this.setContentPane(gameOverVideoPanel);
			gameOverVideoPanel.playVideoAndDrawNextPanel(true);
			break;
		case SCORE_PANEL:
			this.setContentPane(scorePanel);
			break;
		default:
			break;
		}

		pack();
		setLocationRelativeTo(null);
	}

	private void showWebcamDialog() {
		Point frameLocation = this.getLocation();
        int x = frameLocation.x + this.getWidth()-dialog.getWidth()-30;
        int y = frameLocation.y+25;

        dialog.setLocation(x, y);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (SwingUtilities.isDescendingFrom(e.getComponent(), dialog)) {
                if (e.getID() == KeyEvent.KEY_RELEASED) {
                	switch (e.getKeyCode()) {
	    				case KeyEvent.VK_ESCAPE: {
	    					System.exit(0);
	    				}
    				}
                }
            }
            return false;
        });
		dialog.setVisible(true);
	}

}
