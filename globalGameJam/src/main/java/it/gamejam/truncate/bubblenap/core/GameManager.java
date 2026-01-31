package it.gamejam.truncate.bubblenap.core;

import it.gamejam.truncate.bubblenap.core.enemies.AbstractEnemy;
import it.gamejam.truncate.bubblenap.core.enemies.EnemyFactory;
import it.gamejam.truncate.bubblenap.core.enemies.EnemySpawner;
import it.gamejam.truncate.bubblenap.core.enums.MaskType;
import it.gamejam.truncate.bubblenap.core.objects.StaticObjectFactory;
import it.gamejam.truncate.bubblenap.core.objects.StaticObjectSpawner;
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
	private EnemyFactory enemyFactory;
	private EnemySpawner enemySpawner;
	private StaticObjectSpawner staticObjectSpawner;
	private StaticObjectFactory staticObjectFactory;
	private MaskType currentMaskName;
	private int firstBackgroundX = 0;
	private int secondBackgroundX = 2690;

	public GameManager() {
		setMaskedPlayer(new MaskedPlayer( 100, 450));
		try {
			enemyFactory = new EnemyFactory();
			staticObjectFactory = new StaticObjectFactory();
			staticObjectSpawner = new StaticObjectSpawner(staticObjectFactory, 7000);
			enemySpawner = new EnemySpawner(enemyFactory);
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
				long start = System.currentTimeMillis();
				while (running.get()) {
					if (last == -1) {
						last = System.currentTimeMillis();
						continue;
					}
					long elapsed = System.currentTimeMillis() - last;
					staticObjectSpawner.update(GameManager.this);
					enemySpawner.update(GameManager.this);
					if(last-start > 5000) {
						start = last;
						player.accelerate();
					}
					int d = (int) (player.getSpeed()*elapsed);
					firstBackgroundX-=d;
					secondBackgroundX-=d;

					if (firstBackgroundX <= -2690) {
						firstBackgroundX = secondBackgroundX + 2690;
					}
					if (secondBackgroundX <= -2690 ) {
						secondBackgroundX = firstBackgroundX + 2690;
					}

					getObjects().forEach(t -> t.updatePosition(elapsed,player.getSpeed()));
					getObjects().forEach(o -> {
						if (o.collide(player)) {
							o.applyEffect(GameManager.this);
						}
						if(o.getX()<-o.getWidth()) {
							toRemove.add(o);
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
	public DJLWebcamClassifier getClassifier() {
		return classifier;
	}
	
	public void setMask(MaskType maskName) {
		System.out.println(maskName);
		this.currentMaskName = maskName;
	}

	public MaskType getCurrentMask() { return currentMaskName; }

	public int getActiveEnemyCount() {
		return (int) getObjects().stream()
				.filter(AbstractEnemy.class::isInstance)
				.count();
	}

	public int getFirstBackgroundX() {
		return firstBackgroundX;
	}
	public int getSecondBackgroundX() {
		return secondBackgroundX;
	}
}
