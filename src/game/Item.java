package game;

/**
 * Defines a contract for any object that can be considered an "Item".
 * An INTERFACE is a pure abstraction. It only declares what methods a class
 * implementing it must have, but provides no implementation at all.
 * This allows us to treat different kinds of items (Weapons, Potions) uniformly.
 *
 * OOP Concepts Showcased:
 * 1. Abstraction / Interfaces: This is the ultimate form of abstraction. We can create
 * a list of `Item` objects and call `use()` on any of them, regardless of what
 * concrete type of item it is. This is key to making the player's inventory work.
 * 2. Polymorphism: The `use` method will have different behaviors for a Weapon vs. a Potion.
 */
public interface Item {
    String getName();
    String getDescription();
    void use(GameCharacter user, GameCharacter target);
}


