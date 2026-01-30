package it.gamejam.truncate.bubblenap.core;

import it.gamejam.truncate.bubblenap.ui.Repaintable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameManager {
	private Repaintable repaintable;
	private List<MovingObject> objects = new CopyOnWriteArrayList<>();


	private AtomicBoolean running = new AtomicBoolean(false);
	private AtomicBoolean gameOver = new AtomicBoolean(false);

	private int level = 1;
	private long points = 0;


	private List<MovingObject> toRemove = new ArrayList<>();

	public GameManager() {
	}

	public void addMovingObject(MovingObject mo) {
		getObjects().add(mo);
	}



	public void gameOver() {
		System.out.println("GameManager.gameOver()");
		running.set(false);
		gameOver.set(true);
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


	public void setRepaintable(final Repaintable repaintable) {
		this.repaintable = repaintable;
	}

	public void startGame() {
		running.set(true);
		gameOver.set(false);
		points = 0;
		objects.clear();
		long rate = 1000 / 100;
		Runnable updater = () -> {
            long last = -1;
            while (running.get()) {
                if (last == -1) {
                    last = System.currentTimeMillis();
                    continue;
                }
                long elapsed = System.currentTimeMillis() - last;
                getObjects().forEach(t -> t.updatePosition(elapsed));
                getObjects().forEach(o -> {
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
        };
		new Thread(updater).start();

	}


}
