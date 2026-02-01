package it.gamejam.truncate.bubblenap.core.objects;

import it.gamejam.truncate.bubblenap.core.GameManager;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StaticObjectSpawner {
  private final StaticObjectFactory factory;

  private final List<StaticObjectType> staticObjectTypes;
  private final Random random = new Random();
  private long lastSpawn;
  private long lastHydrantSpawn;
  private long spawnIntervalMs;
  private long hydrantSpawnIntervalMs;

  private int lowerBoundMs = 3;
  private int upperBoundMs = 6;
  private int hydrantLowerBoundMs = 7;
  private int hydrantUpperBoundMs = 11;

  public StaticObjectSpawner(StaticObjectFactory factory) {
    this.factory = factory;
    this.spawnIntervalMs = random.nextInt(lowerBoundMs,upperBoundMs) * 1000L;
    this.hydrantSpawnIntervalMs = random.nextInt(hydrantLowerBoundMs,hydrantUpperBoundMs) * 1000L;
    this.staticObjectTypes = Arrays.asList(StaticObjectType.values());
  }

  public void update(GameManager gameManager) {
    long now = System.currentTimeMillis();
    if (now - lastSpawn < spawnIntervalMs) return;

    for (StaticObjectType type : staticObjectTypes) {
      if(type.equals(StaticObjectType.HYDRANT)) continue;
      factory.spawn(type, gameManager::addMovingObject);
    }

    spawnIntervalMs = random.nextInt(lowerBoundMs,upperBoundMs) * 1000L;
    lastSpawn = now;
  }

  public void updateForHydrant(GameManager gameManager) {
    long now = System.currentTimeMillis();
    if (now - lastHydrantSpawn < hydrantSpawnIntervalMs) return;
    factory.spawn(StaticObjectType.HYDRANT, gameManager::addMovingObject);
    hydrantSpawnIntervalMs = random.nextInt(hydrantLowerBoundMs,hydrantUpperBoundMs) * 1000L;
    lastHydrantSpawn = now;
  }
}
