package it.gamejam.truncate.maskordie.core.enemies;

import it.gamejam.truncate.maskordie.core.MovingObject;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

public class EnemyFactory {

  private static final double DX = 30.0;

public interface EnemyCreator {
    AbstractEnemy create(int x, int y);
  }

  private final Map<EnemyType, EnemyCreator> registry = new EnumMap<>(EnemyType.class);

  public EnemyFactory() {
    registerDefaults();
  }

  private void registerDefaults() {
    register(EnemyType.WEREWOLF, (x, y) ->
        new Werewolf(x, y+20, 140, 220, DX,1.0)
    );

    register(EnemyType.VAMPIRE, (x, y) ->
        new Vampire(x, y-5, 140, 220, DX,1.0)
    );

    register(EnemyType.ZOMBIE, (x, y) ->
        new Zombie(x, y+3, 120, 220, DX,1.0)
    );

    register(EnemyType.MUMMY, (x, y) ->
        new Mummy(x, y, 140, 220, DX,1.0)
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
    int x = 1300;
    int y = 320;
    AbstractEnemy enemy = create(type, x, y+(int)(Math.random()*20-10));
    consumer.accept(enemy);
  }
}
