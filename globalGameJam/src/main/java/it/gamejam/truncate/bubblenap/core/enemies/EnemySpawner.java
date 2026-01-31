package it.gamejam.truncate.bubblenap.core.enemies;

import it.gamejam.truncate.bubblenap.core.GameManager;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnemySpawner {
  private final EnemyFactory factory;
  private  long spawnIntervalMs;
  private final int maxEnemies;
  private final List<EnemyType> enemyTypes;
  private final Random random = new Random();
  private long lastSpawn;

  public EnemySpawner(EnemyFactory factory, long spawnIntervalMs, int maxEnemies) {
    this.factory = factory;
    this.spawnIntervalMs = spawnIntervalMs;
    this.maxEnemies = maxEnemies;
    this.enemyTypes = Arrays.asList(EnemyType.values());
  }

  public void update(GameManager gameManager) {
    long now = System.currentTimeMillis();

    if (now - lastSpawn < spawnIntervalMs) return;

    if (gameManager.getActiveEnemyCount() >= maxEnemies){
      System.out.println("Max enemies reached: " + gameManager.getActiveEnemyCount());
      return;
    }

    EnemyType type = enemyTypes.get(random.nextInt(enemyTypes.size()));
    //EnemyType type = EnemyType.ZOMBIE;
    factory.spawn(type, gameManager::addMovingObject);
    lastSpawn = now;
    spawnIntervalMs*=0.95;
  }

  public void reduceSpawnInterval(double factor) {
    long newInterval = (long)(spawnIntervalMs * factor);
    System.out.println("Reducing spawn interval from " + spawnIntervalMs + " to " + newInterval);
  }

  public void increaseMaxEnemies(int increment) {
    int newMax = maxEnemies + increment;
    System.out.println("Increasing max enemies from " + maxEnemies + " to " + newMax);
  }
}
