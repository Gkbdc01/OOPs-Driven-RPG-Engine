package game;
public class EnemyFactory {

    public static Enemy create(String enemyType) {
        if (enemyType == null) {
            return null;
        }
        if (enemyType.equalsIgnoreCase("GOBLIN")) {
            return new Goblin();
        } else if (enemyType.equalsIgnoreCase("DRAGON")) {
            return new Dragon();
        }

        return null;
    }
}
    class Goblin extends Enemy {
        public Goblin() {
            // A goblin is created with a basic attack strategy.
            super("Goblin Grunt", 30, 8, 3, new BasicAttack());
        }
    }

    class Dragon extends Enemy {
        public Dragon() {
            // A dragon is created with a powerful fire-breathing strategy.
            super("Mighty Dragon", 150, 25, 15, new FireBreathAttack());
        }
    }


