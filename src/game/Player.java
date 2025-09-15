package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the player. Updated to use the Strategy pattern.
 * The overridden `attack` method is gone, as the logic is now in `PlayerWeaponAttack`.
 */
public class Player extends GameCharacter {
    private List<Item> inventory;
    private Weapon equippedWeapon;

    public Player(String name, int maxHealth, int attackPower, int defense) {
        // The Player starts with a strategy that knows how to use weapons.
        super(name, maxHealth, attackPower, defense, new PlayerWeaponAttack());
        this.inventory = new ArrayList<>();
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
        System.out.println(item.getName() + " was added to your inventory.");
    }

    public void viewInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }
        System.out.println("\n--- INVENTORY ---");
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - " + item.getDescription());
        }
        System.out.println("-----------------");
    }
    
    public Item getItemFromInventory(int index) {
        if (index >= 0 && index < inventory.size()) {
            return inventory.get(index);
        }
        return null;
    }

    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
        System.out.println(weapon.getName() + " has been equipped.");
    }

    @Override
    public void takeDamage(int damage) {
        int damageToTake = Math.max(0, damage - this.defense);
        this.health -= damageToTake;
        System.out.println(this.getName() + " takes " + damageToTake + " damage.");
        if (!isAlive()) {
            System.out.println(this.getName() + " has been defeated!");
        }
    }

    public void heal(int amount) {
        this.health = Math.min(this.maxHealth, this.health + amount);
        System.out.println(this.getName() + " heals for " + amount + " HP. Current health: " + this.health);
    }
    
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }
}

