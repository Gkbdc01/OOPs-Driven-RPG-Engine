package game;

import java.util.Scanner;
import java.util.Random;

/**
 * The main driver for the game, updated to use the Factory and Strategy patterns.
 */
public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Player player = new Player("Hero", 100, 15, 10);
        System.out.println("Welcome, " + player.getName() + "!");

        player.addItemToInventory(new Weapon("Iron Sword", "A trusty blade.", 12));
        player.addItemToInventory(new HealthPotion("Minor Health Potion", 25));
        
        // --- Using the Factory ---
        System.out.println("\nA menacing figure approaches...");
        Enemy currentEnemy = EnemyFactory.create("GOBLIN");
        
        runCombat(player, currentEnemy, scanner);

        // If player survives, they face the next challenge
        if (player.isAlive()) {
            System.out.println("\nAs the first foe falls, a great shadow descends!");
            System.out.println("A mighty beast lands before you!");
            currentEnemy = EnemyFactory.create("DRAGON");
            runCombat(player, currentEnemy, scanner);
        }

        System.out.println("\n--- GAME OVER ---");
        if (player.isAlive()) {
            System.out.println("You have triumphed over all your foes! You are a true champion!");
        } else {
            System.out.println("Your adventure ends here.");
        }

        scanner.close();
    }
    
    /**
     * A reusable method to handle a combat encounter.
     */
    public static void runCombat(Player player, Enemy currentEnemy, Scanner scanner) {
        if (currentEnemy == null) return;
        System.out.println("You are now fighting a " + currentEnemy.getName() + "!");

        while (player.isAlive() && currentEnemy.isAlive()) {
            System.out.println("\n" + player.getStatus());
            System.out.println(currentEnemy.getStatus());
            
            System.out.println("\nChoose your action:");
            System.out.println("1. Attack");
            System.out.println("2. View Inventory");
            System.out.print("> ");
            
            String choice = scanner.nextLine();

            if ("1".equals(choice)) {
                // The correct attack logic is executed polymorphically via the strategy.
                player.attack(currentEnemy);
            } else if ("2".equals(choice)) {
                player.viewInventory();
                System.out.print("Enter item number to use, or 'c' to cancel: ");
                String itemChoice = scanner.nextLine();
                if (!itemChoice.equalsIgnoreCase("c")) {
                    try {
                        int itemIndex = Integer.parseInt(itemChoice) - 1;
                        Item itemToUse = player.getItemFromInventory(itemIndex);
                        if (itemToUse != null) {
                            itemToUse.use(player, player);
                            continue; // Skip enemy attack after using item
                        } else {
                            System.out.println("Invalid item number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                    }
                }
            } else {
                System.out.println("Invalid choice. You hesitate and do nothing.");
            }

            if (currentEnemy.isAlive()) {
                System.out.println("---");
                currentEnemy.attack(player);
            }
        }
        
        if (player.isAlive()) {
            System.out.println("\nYou have defeated the " + currentEnemy.getName() + "!");
        } else {
            System.out.println("\nYou have been slain by the " + currentEnemy.getName() + "...");
        }
    }
}


