package game;

/**
 * Defines the contract for all attack algorithms. This is the core of the Strategy Pattern.
 * It allows us to define a family of algorithms (different ways to attack),
 * encapsulate each one, and make them interchangeable.
 *
 * OOP Concepts Showcased:
 * 1. Strategy Pattern: A behavioral design pattern that enables selecting an algorithm at runtime.
 * Instead of implementing a single attack algorithm directly in the GameCharacter,
 * we can equip a character with different strategies.
 * 2. Abstraction (Interface): Defines a common method `execute` that all concrete strategies
 * must implement, hiding the specific details of each algorithm.
 */
public interface IAttackStrategy {
    void execute(GameCharacter attacker, GameCharacter target);
}

/**
 * A concrete strategy for a standard physical attack.
 */
class BasicAttack implements IAttackStrategy {
    @Override
    public void execute(GameCharacter attacker, GameCharacter target) {
        System.out.println(attacker.getName() + " attacks " + target.getName() + " with a standard blow!");
        target.takeDamage(attacker.attackPower);
    }
}

/**
 * A concrete strategy for a player's physical attack, which also considers the equipped weapon.
 * This demonstrates how strategies can encapsulate more complex, specific logic.
 */
class PlayerWeaponAttack implements IAttackStrategy {
    @Override
    public void execute(GameCharacter attacker, GameCharacter target) {
        if (!(attacker instanceof Player)) {
            new BasicAttack().execute(attacker, target);
            return;
        }

        Player player = (Player) attacker;
        int totalAttackPower = player.attackPower;
        
        if (player.getEquippedWeapon() != null) {
            Weapon weapon = player.getEquippedWeapon();
            totalAttackPower += weapon.getDamageBonus();
            System.out.println(player.getName() + " attacks with the " + weapon.getName() + "!");
        } else {
            System.out.println(player.getName() + " attacks with their bare hands!");
        }
        target.takeDamage(totalAttackPower);
    }
}


/**
 * A unique, powerful strategy for a Dragon's fire attack.
 */
class FireBreathAttack implements IAttackStrategy {
    @Override
    public void execute(GameCharacter attacker, GameCharacter target) {
        System.out.println(attacker.getName() + " unleashes a torrent of fire on " + target.getName() + "!");
        // Fire attack is powerful and harder to defend against
        int fireDamage = attacker.attackPower + 10;
        target.takeDamage(fireDamage);
    }
}


