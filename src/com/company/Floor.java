package com.company;
import java.util.ArrayList;

public class Floor {
    public static final String red = "\u001B[31m";
    public static final String bold = "\u001B[1m";
    public static final String reset = "\u001B[0m";
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();
    public static int floorLevel;

    public Floor(){
        floorLevel += 1;
        if (floorLevel != 10){
            generateEnemies();
        }
        else{
            enemies.add(new Reaper("**********", 100, 10, "*** **** ****"));
        }
    }

    public Floor(ArrayList<String> enemyNames){
        setFloorEnemies(enemyNames);
    }

    public void setFloorEnemies(ArrayList<String> enemyNames){
        for (String name : enemyNames){
            switch(name) {
                case "Enemy" -> enemies.add(new Enemy("Enemy", 10+Floor.floorLevel, 1+Floor.floorLevel, """
            Enemies are people who have been corrupted by the pollution"""));
                case "Vampire" -> enemies.add(new Vampire("Vampire", 15+Floor.floorLevel, 3+Floor.floorLevel, """
                    Vampires are creatures that suck your blood
                    They steal your hp and heal themselves
                    (scaled according to how much hp you have)"""));
                case "Golem" -> enemies.add(new Golem("Golem", 20+Floor.floorLevel, 2+Floor.floorLevel, 5+Floor.floorLevel, """
                    Golems are creatures made of rock and stone that have become sentient due to the pollution
                    They have strong defence, so attacks will deal less damage than usual
                    (scaled according to how much defence the golem has)"""));
                case "Troll" -> enemies.add(new Troll("Troll", 5+Floor.floorLevel, 2+Floor.floorLevel, """
                    Trolls are mischievous mythical creatures that love to play tricks
                    Trolls will steal an item from your inventory when they die, so equip any items you want to keep safe"""));
                case "Dragon" -> enemies.add(new Dragon("Dragon", 50+Floor.floorLevel, 10+Floor.floorLevel, 10+Floor.floorLevel, """
                    Dragons are extremely powerful creatures
                    Dragons have lots of health, attack, and defence, so they're difficult to defeat
                    However, once defeated, they drop special dragon armour that can't be found anywhere else
                    These items have higher stats than all other items"""));
                // add reaper later?
            }
        }
    }

    public boolean getAllEnemiesDead(){
        return (enemies.size() == 0);
    }

    public void generateEnemies(){
        for (int i = 0; i < floorLevel; i++){
            enemies.add(Enemy.generateRandomEnemy());
        }
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public void enterLevel(){
        System.out.println();
        System.out.println(bold + "Floor " + floorLevel + reset);
        for (Enemy enemy : enemies) {
            System.out.println(enemy);
        }
    }

    public void addDeadEnemy(Enemy enemy){
        deadEnemies.add(enemy);
    }

    public void updateEnemies(){
        enemies.removeAll(deadEnemies);
    }

    public void floorCleared(Player player){
        System.out.println();
        System.out.println(bold + "Floor " + floorLevel + " cleared!" + reset);
        for (Potion potion : player.getPotionsInUse()){
            potion.endOfEffect(player);
        }
        player.clearPotionsInUse();
        player.profile();
    }

    public void fightUpdate(Player player){
        updateEnemies();
        System.out.println(bold + "Result" + reset);
        System.out.println("You have " + red + player.getHealth() + " hp" + reset);
        for (Enemy enemy : enemies){
            System.out.println(enemy);
        }
    }
}
