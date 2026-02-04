package it.gamejam.truncate.maskordie.ui;

import it.gamejam.truncate.maskordie.ui.img.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CreditsMenuPanel extends JPanel {

	private static final Image BACKGROUND_BLUR = ImageLoader.getImageBackground_Blur();
	private static Image back = ImageLoader.getImageBack();


	private static Image luigi = ImageLoader.getLuigiMask();
	private static Image stefano = ImageLoader.getStefanoMask();
	private static Image paolo = ImageLoader.getPaoloMask();
	private static Image domenico = ImageLoader.getDomenicoMask();
	private static Image saverio = ImageLoader.getSaverioMask();
	private static Image ernani = ImageLoader.getErnaniMask();

	private static final int IMAGE_WIDTH_PIXEL = 300;
	private static final int IMAGE_HEIGHT_PIXEL = 300;

	private static final int PAOLO_X = 80;
	private static final int PAOLO_Y = 20;

	private static final int STEFANO_X = PAOLO_X + 400;
	private static final int STEFANO_Y = PAOLO_Y;

	private static final int DOMENICO_X = STEFANO_X + 400;
	private static final int DOMENICO_Y = PAOLO_Y + 310;

	private static final int ERNANI_X = PAOLO_X;
	private static final int ERNANI_Y = PAOLO_Y + 310;

	private static final int SAVERIO_X = STEFANO_X;
	private static final int SAVERIO_Y = PAOLO_Y + 310;

	private static final int LUIGI_X = DOMENICO_X;
	private static final int LUIGI_Y = PAOLO_Y;


	private static final int BACK_X = (1280 / 2) - 150;
	private static final int BACK_Y = 643;

	MainFrame frame;

	public CreditsMenuPanel(final MainFrame frame) {

		this.frame = frame;

		this.setLayout(null);
		setPreferredSize(new Dimension(1280, 768));

		requestFocus();

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

				// Luigi Start
				if ((e.getX() >= LUIGI_X) && (e.getX() <= (LUIGI_X + ImageLoader.getLuigiMask().getWidth(null)))
						&& (e.getY() >= LUIGI_Y)
						&& (e.getY() <= (LUIGI_Y + ImageLoader.getLuigiMask().getHeight(null)))) {
					luigi = ImageLoader.getLuigiNoMask();
				} else {
					luigi = ImageLoader.getLuigiMask();
				}
				// Luigi End

				// Stefano Start
				if ((e.getX() >= STEFANO_X) && (e.getX() <= (STEFANO_X + ImageLoader.getStefanoMask().getWidth(null)))
						&& (e.getY() >= STEFANO_Y)
						&& (e.getY() <= (STEFANO_Y + ImageLoader.getStefanoMask().getHeight(null)))) {
					stefano = ImageLoader.getStefanoNoMask();
				} else {
					stefano = ImageLoader.getStefanoMask();
				}
				// Stefano End

				// Domenico Start
				if ((e.getX() >= DOMENICO_X) && (e.getX() <= (DOMENICO_X + ImageLoader.getDomenicoMask().getWidth(null)))
						&& (e.getY() >= DOMENICO_Y)
						&& (e.getY() <= (DOMENICO_Y + ImageLoader.getDomenicoMask().getHeight(null)))) {
					domenico = ImageLoader.getDomenicoNoMask();
				} else {
					domenico = ImageLoader.getDomenicoMask();
				}
				// Domenico End


				// Saverio Start
				if ((e.getX() >= SAVERIO_X) && (e.getX() <= (SAVERIO_X + ImageLoader.getSaverioMask().getWidth(null)))
						&& (e.getY() >= SAVERIO_Y)
						&& (e.getY() <= (SAVERIO_Y + ImageLoader.getSaverioMask().getHeight(null)))) {
					saverio = ImageLoader.getSaverioNoMask();
				} else {
					saverio = ImageLoader.getSaverioMask();
				}
				// Saverio End

				// Ernani Start
				if ((e.getX() >= ERNANI_X) && (e.getX() <= (ERNANI_X + ImageLoader.getErnaniMask().getWidth(null)))
						&& (e.getY() >= ERNANI_Y)
						&& (e.getY() <= (ERNANI_Y + ImageLoader.getErnaniMask().getHeight(null)))) {
					ernani = ImageLoader.getErnaniNoMask();
				} else {
					ernani = ImageLoader.getErnaniMask();
				}
				// Ernani End

				// Paolo Start
				if ((e.getX() >= PAOLO_X) && (e.getX() <= (PAOLO_X + ImageLoader.getPaoloMask().getWidth(null)))
						&& (e.getY() >= PAOLO_Y)
						&& (e.getY() <= (PAOLO_Y + ImageLoader.getPaoloMask().getHeight(null)))) {
					paolo = ImageLoader.getPaoloNoMask();
				} else {
					paolo = ImageLoader.getPaoloMask();
				}
				// Paolo End


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

					frame.drawPanel(EnumPanel.MENU_PANEL);
				}
			}

		});
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


		g.drawImage(BACKGROUND_BLUR, 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(back, BACK_X, BACK_Y, 330, 83, null);
		g.drawImage(paolo, PAOLO_X, PAOLO_Y, IMAGE_WIDTH_PIXEL, IMAGE_HEIGHT_PIXEL, null);
		g.drawImage(stefano, STEFANO_X, STEFANO_Y, IMAGE_WIDTH_PIXEL, IMAGE_HEIGHT_PIXEL, null);
		g.drawImage(domenico, DOMENICO_X, DOMENICO_Y, IMAGE_WIDTH_PIXEL, IMAGE_HEIGHT_PIXEL, null);
		g.drawImage(ernani, ERNANI_X, ERNANI_Y, IMAGE_WIDTH_PIXEL, IMAGE_HEIGHT_PIXEL, null);
		g.drawImage(saverio, SAVERIO_X, SAVERIO_Y, IMAGE_WIDTH_PIXEL, IMAGE_HEIGHT_PIXEL, null);
		g.drawImage(luigi, LUIGI_X, LUIGI_Y, IMAGE_WIDTH_PIXEL, IMAGE_HEIGHT_PIXEL, null);


	}
}
