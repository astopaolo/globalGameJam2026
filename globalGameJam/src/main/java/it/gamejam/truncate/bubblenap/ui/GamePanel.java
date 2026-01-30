package it.gamejam.truncate.bubblenap.ui;

import it.gamejam.truncate.bubblenap.core.GameManager;
import it.gamejam.truncate.bubblenap.core.MaskedPlayer;
import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;



public class GamePanel extends JPanel implements Repaintable {
	private static final long serialVersionUID = 1L;
	private final MaskedPlayer player;
	private final GameManager gameManager;

	private int firstBackgroundX = 0;
	private int secondBackgroundX = 2690;

	private Font font;
	private MainFrame mainFrame;

	private AtomicBoolean startedGameOverThread = new AtomicBoolean(false);

	public GamePanel(final GameManager gameManager, final MainFrame frame) {
		this.gameManager = gameManager;
		this.mainFrame = frame;
		player=gameManager.getMaskedPlayer();
		setPreferredSize(new Dimension(1280, 768));
		setBackground(Color.DARK_GRAY);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE: {
					System.exit(0);
				}
				}
				repaint();
			}
		});

	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for text
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g.drawImage(ImageLoader.getGameScreen(), firstBackgroundX, 0, 2690, getHeight(), null);
		g.drawImage(ImageLoader.getGameScreen(), secondBackgroundX, 0, 2690, getHeight(), null);


		if (gameManager.isGameOver()) {
//
//			g.drawImage(ImageLoader.getBollaMucoScoppiata(), bubble.getX() - (int) bubble.getRadius(),
//					bubble.getY() - (int) bubble.getRadius(), (int) bubble.getRadius() * 2,
//					(int) bubble.getRadius() * 2, null);
			if (!startedGameOverThread.get()) {
				startedGameOverThread.set(true);
				new Thread() {
					@Override
					public void run() {
						try {
							Thread.sleep(700);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						mainFrame.drawPanel(EnumPanel.GAME_OVER_VIDEO_PANEL);
					};
				}.start();
			}
		} else {
			firstBackgroundX-=gameManager.getMaskedPlayer().getSpeed();
			secondBackgroundX-=gameManager.getMaskedPlayer().getSpeed();

			if (firstBackgroundX <= -2690) {
				firstBackgroundX = 2690;
			}
			if (secondBackgroundX <= -2690 ) {
				secondBackgroundX = 2690;
			}

			g.drawImage(ImageLoader.getGameScreen(), firstBackgroundX, 0, 2690, getHeight(), null);
			g.drawImage(ImageLoader.getGameScreen(), secondBackgroundX, 0, 2690, getHeight(), null);




//			g.drawImage(ImageLoader.getBollaMuco(), bubble.getX() - (int) bubble.getRadius(),
//					bubble.getY() - (int) bubble.getRadius(), (int) bubble.getRadius() * 2,
//					(int) bubble.getRadius() * 2, null);
		}
		gameManager.getObjects().forEach(o -> {
			g.drawImage(o.getTransformedImage(), o.getX(), o.getY(), null);
		});
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Points: " + gameManager.getPoints(), 45, 70);
	}

	public void startGame() {
		startedGameOverThread.set(false);
		gameManager.startGame();
		if (font == null) {
			try {
				final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Creepster-Regular.otf")));
				font = new Font("Creepster", Font.BOLD, 55);
			} catch (IOException | FontFormatException e) {
				// IGNORE
			}
		}

	}

	@Override
	public void update() {
		repaint();
	}

}
