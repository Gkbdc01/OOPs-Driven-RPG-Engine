package game;

/**
 * Concrete implementations of the Item interface.
 *
 * OOP Concepts Showcased:
 * 1. Interface Implementation: Both classes `implement` the Item interface, meaning
 * they fulfill the "contract" by providing implementations for `getName`,
 * `getDescription`, and `use`.
 * 2. Polymorphism: The `use` method has completely different logic in each class.
 * For a Weapon, `use` means equipping it. For a HealthPotion, `use` means drinking it to heal.
 * The Player's code that calls `item.use()` doesn't need to know these details; it just
 * works, demonstrating polymorphism.
 */
class Weapon implements Item {
    private String name;
    private String description;
    private int damageBonus;

    public Weapon(String name, String description, int damageBonus) {
        this.name = name;
        this.description = description;
        this.damageBonus = damageBonus;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "Weapon. Damage Bonus: +" + damageBonus;
    }

    @Override
    public void use(GameCharacter user, GameCharacter target) {
        if (user instanceof Player) {
            ((Player) user).equipWeapon(this);
        }
    }
    
    public int getDamageBonus() {
        return damageBonus;
    }
}

class HealthPotion implements Item {
    private String name;
    private int healAmount;

    public HealthPotion(String name, int healAmount) {
        this.name = name;
        this.healAmount = healAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "Potion. Heals for " + healAmount + " HP.";
    }

    @Override
    public void use(GameCharacter user, GameCharacter target) {
        System.out.println(user.getName() + " uses a " + this.name + ".");
        if (user instanceof Player) {
           ((Player) user).heal(healAmount);
        }
    }
}


