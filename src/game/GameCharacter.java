package game;
public abstract class GameCharacter {
    // --- Encapsulated Attributes ---
    private String name;
    protected int health;
    protected int maxHealth;
    protected int attackPower;
    protected int defense;
    private IAttackStrategy attackStrategy; // Composition: Character *has-a* strategy

    public GameCharacter(String name, int maxHealth, int attackPower, int defense, IAttackStrategy attackStrategy) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
        this.defense = defense;
        this.attackStrategy = attackStrategy; // Set the initial strategy
    }

    // --- Method now delegates to the strategy object ---
    public void attack(GameCharacter target) {
        // The character no longer knows HOW to attack. It tells its strategy object to execute.
        this.attackStrategy.execute(this, target);
    }

    // Abstract method for subclasses to implement
    public abstract void takeDamage(int damage);

    // --- Concrete Methods (Shared by all subclasses) ---
    public boolean isAlive() {
        return this.health > 0;
    }

    public String getStatus() {
        return name + " - HP: " + health + "/" + maxHealth;
    }
    
    // Setter to change strategy at runtime!
    public void setAttackStrategy(IAttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
        System.out.println(this.name + " is preparing a new move!");
    }

    // --- Getters (Encapsulation) ---
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
}


