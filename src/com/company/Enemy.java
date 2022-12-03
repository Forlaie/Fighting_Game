package com.company;

public class Enemy {
    protected String name;
    protected int health;
    protected int attack;
    protected String description;
    public static final String bold = "\u001B[1m";
    public static final String italic = "\033[3m";
    public static final String reset = "\u001B[0m";
    private static Enemy[] possibleEnemies = {
            new Enemy("Enemy", 10, 1, """
            Enemies are people who have been corrupted by the pollution
            """),
            new Vampire("Vampire", 15, 3, """
                    Vampires are creatures that suck your blood
                    They steal your hp and heal themselves
                    (scaled according to how much hp you have)
                    """),
            new Golem("Golem", 20, 2, 5, """
                    Golems are creatures made of rock and stone that have become sentient due to the pollution
                    They have strong defence, so attacks will deal less damage than usual
                    (scaled according to how much defence the golem has)
                    """),
            new Troll("Troll", 5, 2, """
                    Trolls are mischevious mythical creatures that love to play tricks
                    Trolls will steal an item from your inventory when they die, so equip any items you want to keep safe
                    """),
            new Dragon("Dragon", 50, 10, 10, """
                    Dragons are extremely powerful creatures
                    Dragons have lots of health, attack, and defence, so they're difficult to defeat
                    However, once defeated, they drop special dragon armour that can't be found anywhere else
                    These items have higher stats than all other items
                    """)
    };

    public static Enemy generateRandomEnemy(){
        int index = (int) (Math.random()*possibleEnemies.length);
        return possibleEnemies[index];
    }

    public Enemy(String name, int health, int attack, String description){
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.description = description;
    }

    public static void enemyInfo(){
        System.out.println();
        for (Enemy enemy : possibleEnemies){
            System.out.println(bold + enemy.name + reset);
            System.out.println(italic + enemy.description + reset);
        }
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }
    public String getName(){
        return name;
    }

    public int getAttack(){
        return attack;
    }

    public void battle(Player player, Floor floor){
        health -= player.getAttack();
        System.out.println("You have dealt " + player.getAttack() + " damage");
        if (health <= 0){
            died(player, floor);
        }
    }

    public void died(Player player, Floor floor){
        System.out.println(name + " has died");
        Item item = Item.generateRandomDrop();
        player.defeatedMonster(item);
        System.out.println(name + " dropped " + item.getName());
        floor.addDeadEnemy(this);
    }

    public String toString(){
        return this.name + " has " + this.health + " hp";
    }
}
