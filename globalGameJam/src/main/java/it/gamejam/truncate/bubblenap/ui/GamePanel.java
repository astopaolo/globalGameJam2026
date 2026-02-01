package it.gamejam.truncate.bubblenap.ui;

import it.gamejam.truncate.bubblenap.core.GameManager;
import it.gamejam.truncate.bubblenap.core.MaskedPlayer;
import it.gamejam.truncate.bubblenap.core.enemies.AbstractEnemy;
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
//		g.setClip(new Polygon(new int[] {0,getWidth()-325,getWidth()-325,getWidth(),getWidth(),0}, new int[] {0,0,245,245,getHeight(),getHeight()},6 ));

        // Enable anti-aliasing for text
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
//		g.drawImage(ImageLoader.getGameScreen(), gameManager.getFirstBackgroundX(), 0, 2690, getHeight(), null);
//		g.drawImage(ImageLoader.getGameScreen(), gameManager.getSecondBackgroundX(), 0, 2690, getHeight(), null);


		if (gameManager.isGameOver()) {

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

			g.drawImage(ImageLoader.getGameScreen1(), gameManager.getFirstBackgroundX(), 0, null);
			g.drawImage(ImageLoader.getGameScreen2(), gameManager.getSecondBackgroundX(), 0, null);
//			g.drawImage(ImageLoader.getGameScreen(), gameManager.getFirstBackgroundX(), 0, 2690, getHeight(), null);
//			g.drawImage(ImageLoader.getGameScreen(), gameManager.getSecondBackgroundX(), 0, 2690, getHeight(), null);

		}

		gameManager.getObjects().forEach(o -> {
			if(o instanceof AbstractEnemy){
				g.drawImage(o.getTransformedImage((int)((System.currentTimeMillis() / 150) % ((AbstractEnemy) o).getImageCount())), o.getX(), o.getY(), null);
			}else{
				g.drawImage(o.getTransformedImage(0), o.getX(), o.getY(), null);
			}
		});

		g.drawImage(player.getScaledImage((int)((System.currentTimeMillis() / 200) % player.getImageCount())), player.getX(), player.getY(), null);


		//ansia che aumenta
		Composite composite = g2d.getComposite();
		int rule = AlphaComposite.SRC_OVER;
        Composite comp = AlphaComposite.getInstance(rule , Math.min(1, player.getAnxiety()/100f) );
        g2d.setComposite(comp );
//        g2d.setColor(Color.green);
//        g2d.fillRect(0, 0, getWidth(),getHeight());
		g2d.drawImage(ImageLoader.getBloodCurtain(),  0, 0,null);
		
		g2d.setComposite(composite);
		
		g.drawImage(player.getCurrentHead(), 30, 10,95,120, null);
		
		g.setColor(player.getCurrentColor());
		g.setFont(font);
		g.drawString("Points: " + gameManager.getPoints(), 200, 70);

		g.setColor(Color.BLACK);
		int anxX = 210;
		int anxY = 85;
		int anxW = 200;
		int anxH = 15;
		g.fillRect(anxX -2, anxY -2 , anxW + 4, anxH + 4);
		
		g.setColor(Color.WHITE);
		g.fillRect(anxX, anxY, anxW, anxH);
		g.setColor(new Color(192,41,255));
//		g.setColor(Color.GREEN);
		g.fillRect(anxX, anxY, player.getAnxiety()*(anxW/100)+(int)(Math.random()*2), anxH);
		g.drawImage(ImageLoader.getAnxietyFrame(), anxX-14, anxY-5,anxW+30,anxH+10, null);
		
		
	}

	public void startGame() {
		startedGameOverThread.set(false);
		gameManager.startGame();
		if (font == null) {
			try {
				final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Creepster-Regular.otf")));
				font = new Font("Creepster", Font.BOLD, 45);
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
