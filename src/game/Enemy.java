package game;

/**
 * Base class for enemies, updated for the strategy pattern.
 */
public class Enemy extends GameCharacter {

    public Enemy(String name, int maxHealth, int attackPower, int defense, IAttackStrategy attackStrategy) {
        super(name, maxHealth, attackPower, defense, attackStrategy);
    }

    @Override
    public void takeDamage(int damage) {
        int damageToTake = Math.max(0, damage - this.defense);
        this.health -= damageToTake;
        System.out.println(this.getName() + " takes " + damageToTake + " damage.");
        if (!isAlive()) {
            System.out.println(this.getName() + " has been slain!");
        }
    }
}


