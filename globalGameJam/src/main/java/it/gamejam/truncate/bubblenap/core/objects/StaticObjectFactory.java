package it.gamejam.truncate.bubblenap.core.objects;

import it.gamejam.truncate.bubblenap.core.MovingObject;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class StaticObjectFactory {

  private static final double DX = 30.0;

  public interface ObjectCreator {
    MovingObject create(int x, int y);
  }

  private final Map<StaticObjectType, ObjectCreator> registry = new EnumMap<>(StaticObjectType.class);

  public StaticObjectFactory() {
    registerDefaults();
  }

  private void registerDefaults() {
    register(StaticObjectType.GARBAGE, (x, y) ->{
          y += (int)(Math.random() * 120 - 60);
          return new Garbage(x, y, 70, 70, DX,1.0);
    });
  }

  public void register(StaticObjectType type, ObjectCreator creator) {
    registry.put(type, creator);
  }

  private MovingObject create(StaticObjectType type, int x, int y) {
    ObjectCreator creator = registry.get(type);
    return creator.create(x, y);
  }

  public void spawn(StaticObjectType type, Consumer<MovingObject> consumer) {
    int x = 1500;
    int y = 600;
    MovingObject obj = create(type, x,y);
    consumer.accept(obj);
  }
}
