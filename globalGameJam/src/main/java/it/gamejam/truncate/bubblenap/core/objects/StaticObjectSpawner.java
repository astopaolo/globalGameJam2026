package it.gamejam.truncate.bubblenap.core.objects;

import it.gamejam.truncate.bubblenap.core.GameManager;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StaticObjectSpawner {
  private final StaticObjectFactory factory;
  private long spawnIntervalMs;
  private final List<StaticObjectType> staticObjectTypes;
  private final Random random = new Random();
  private long lastSpawn;

  public StaticObjectSpawner(StaticObjectFactory factory, long spawnIntervalMs) {
    this.factory = factory;
    this.spawnIntervalMs = spawnIntervalMs;
    this.staticObjectTypes = Arrays.asList(StaticObjectType.values());
  }

  public void update(GameManager gameManager) {
    long now = System.currentTimeMillis();
    if (now - lastSpawn < spawnIntervalMs) return;
    StaticObjectType type = staticObjectTypes.get(random.nextInt(staticObjectTypes.size()));
    factory.spawn(type, gameManager::addMovingObject);
    lastSpawn = now;
  }
}
