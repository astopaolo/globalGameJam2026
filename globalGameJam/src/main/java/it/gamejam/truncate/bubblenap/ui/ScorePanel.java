package it.gamejam.truncate.bubblenap.ui;

import it.gamejam.truncate.bubblenap.core.GameManager;
import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;

public class ScorePanel extends JPanel {
	private static final long serialVersionUID = -2020644767984510521L;

	private static Image scoreBackground = ImageLoader.getImageScoreBackground();
	private static Image back = ImageLoader.getImageBack();
	private static final int BACK_X = (1280 / 2) - 150;
	private static final int BACK_Y = 643;
	MainFrame frame;
	private GameManager gameManager;
	private Font titleFont;

	public ScorePanel(final GameManager gameManager, final MainFrame frame) {
		this.gameManager = gameManager;
		this.frame = frame;

		this.setLayout(null);
		setPreferredSize(new Dimension(1280, 768));

		requestFocus();
		loadFont();

		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(final MouseEvent e) {
				// Back Start
				if ((e.getX() >= BACK_X) && (e.getX() <= (BACK_X + ImageLoader.getImageBack().getWidth(null)))
						&& (e.getY() >= BACK_Y)
						&& (e.getY() <= (BACK_Y + ImageLoader.getImageBack().getHeight(null)))) {
					back = ImageLoader.getImageBackPressed();
				} else {
					back = ImageLoader.getImageBack();
					// Back End
				}

				repaint();
			}

		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(final MouseEvent e) {
				// pulsante back
				if ((e.getX() >= BACK_X) && (e.getX() <= (BACK_X + ImageLoader.getImageBack().getWidth(null)))
						&& (e.getY() >= BACK_Y)
						&& (e.getY() <= (BACK_Y + ImageLoader.getImageBack().getHeight(null)))) {

					frame.drawPanel(EnumPanel.MENU_PANEL, true);
				}
			}

		});

	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		// Enable anti-aliasing for text
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g.drawImage(scoreBackground, 0, 0, this.getWidth(), this.getHeight(), null);
		g.setColor(new Color(0, 0, 0, 0.6f));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(back, BACK_X, BACK_Y, 330, 83, null);

		g.setColor(Color.WHITE);
		g.setFont(titleFont);

		g.drawString("Points: " + gameManager.getPoints(), 200, 384);

	}

	private void loadFont() {
		if (titleFont == null) {
			try {
				final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Creepster-Regular.otf")));
				titleFont = new Font("Creepster", Font.BOLD, 160);
			} catch (IOException | FontFormatException e) {
				// IGNORE
			}
		}

	}

}
