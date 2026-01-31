package it.gamejam.truncate.bubblenap.core.enemies;

import it.gamejam.truncate.bubblenap.core.MovingObject;
import it.gamejam.truncate.bubblenap.core.enums.MaskType;
import it.gamejam.truncate.bubblenap.ui.img.ImageLoader;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class EnemyFactory {

  public interface EnemyCreator {
    AbstractEnemy create(int x, int y);
  }

  private final Map<EnemyType, EnemyCreator> registry = new EnumMap<>(EnemyType.class);

  public EnemyFactory() {
    registerDefaults();
  }

  private void registerDefaults() {
    register(EnemyType.WEREWOLF, (x, y) ->
        new Werewolf(x, y, 100, 100, 1.0,1.0, MaskType.saverio)
    );

    register(EnemyType.VAMPIRE, (x, y) ->
        new Vampire(x, y, 100, 100, 1.0,1.0, MaskType.saverio)
    );

    register(EnemyType.ZOMBIE, (x, y) ->
        new Zombie(x, y, 20, 20, -10.0,-1.0, MaskType.saverio)
    );

    register(EnemyType.MUMMY, (x, y) ->
        new Mummy(x, y, 100, 100, 1.0,1.0, MaskType.saverio)
    );
  }

  public void register(EnemyType type, EnemyCreator creator) {
    registry.put(type, creator);
  }

  private AbstractEnemy create(EnemyType type, int x, int y) {
    EnemyCreator creator = registry.get(type);
    return creator.create(x, y);
  }

  public void spawn(EnemyType type, Consumer<MovingObject> consumer) {
    int x = ThreadLocalRandom.current().nextInt(-300, -200);
    int y = ThreadLocalRandom.current().nextInt(1, 2);
    AbstractEnemy enemy = create(type, x, y);
    consumer.accept(enemy);
  }
}
