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
    register(StaticObjectType.MANHOLE, (x, y) ->
        new Manhole(1500 + ((int) Math.random() * 200 - 100),
            615 + ((int)(Math.random() * 100 - 50)), 80, 80, DX,1.0)
    );

    register(StaticObjectType.GARBAGE, (x, y) ->
        new Garbage(1700 + ((int) Math.random() * 200 - 100),
            470 + ((int)(Math.random() * 14 - 7)), 80, 80, DX,1.0)
    );

    register(StaticObjectType.HYDRANT, (x, y) ->
        new Hydrant(2200 + ((int) Math.random() * 300 - 150),
            520 + ((int)(Math.random() * 10 - 5)), 80, 80, DX,1.0)
    );
  }

  public void register(StaticObjectType type, ObjectCreator creator) {
    registry.put(type, creator);
  }

  private MovingObject create(StaticObjectType type, int x, int y) {
    ObjectCreator creator = registry.get(type);
    return creator.create(x, y);
  }

  public void spawn(StaticObjectType type, Consumer<MovingObject> consumer) {
    MovingObject obj = create(type, 0,0);
    consumer.accept(obj);
  }
}
