package it.gamejam.truncate.bubblenap.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;

import it.gamejam.truncate.bubblenap.ui.Repaintable;

public class GameManager {
	private MaskedPlayer player;
	private Repaintable repaintable;
	private List<MovingObject> objects = new CopyOnWriteArrayList<>();


	private AtomicBoolean running = new AtomicBoolean(false);
	private AtomicBoolean gameOver = new AtomicBoolean(false);

	private long points = 0;


	private List<MovingObject> toRemove = new ArrayList<>();
	private DJLWebcamClassifier classifier;
	private String currentMaskName;

	public GameManager() {
		setMaskedPlayer(new MaskedPlayer( 612, 365));
		try {
			classifier= new DJLWebcamClassifier(this);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Errore durante l'avvio: " + e.getMessage());
		}
	}

	public void addMovingObject(MovingObject mo) {
		getObjects().add(mo);
	}

	public void gameOver() {
		System.out.println("GameManager.gameOver()");
		running.set(false);
		gameOver.set(true);
	}

	public MaskedPlayer getMaskedPlayer() {
		return player;
	}

	public List<MovingObject> getObjects() {
		return objects;
	}

	public long getPoints() {
		return points;
	}

	public Repaintable getRepaintable() {
		return repaintable;
	}

	public boolean isGameOver() {
		return gameOver.get();
	}

	public void markToRemove(MovingObject object) {
		toRemove.add(object);
	}

	public void setMaskedPlayer(final MaskedPlayer player) {
		this.player = player;
	}

	

	public void setRepaintable(final Repaintable repaintable) {
		this.repaintable = repaintable;
	}

	public void startGame() {
		running.set(true);
		gameOver.set(false);
		points = 0;
		objects.clear();
		long rate = 1000 / 100;
		Runnable updater = new Runnable() {

			@Override
			public void run() {
				long last = -1;
				while (running.get()) {
					if (last == -1) {
						last = System.currentTimeMillis();
						continue;
					}
					long elapsed = System.currentTimeMillis() - last;
					getObjects().forEach(t -> t.updatePosition(elapsed,player.getSpeed()));
					getObjects().forEach(o -> {
						if (o.collide(player)) {
							o.applyEffect(GameManager.this);
						}
					});
					objects.removeAll(toRemove);
					toRemove.clear();
					repaintable.update();
					last += elapsed;
					points += elapsed;
					try {
						Thread.sleep(rate);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(updater).start();
//		SamplePlayer player = new SamplePlayer();
//		player.setGameManager(this);
//		player.loadLevel(level);
//		player.start();

	}

	
	public void setMask(String maskName) {
		this.currentMaskName = maskName;
	}
}
