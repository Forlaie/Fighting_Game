package com.company;
import java.util.ArrayList;

public class Dungeon {
    public static final String red = "\u001B[31m";
    public static final String bold = "\u001B[1m";
    public static final String reset = "\u001B[0m";
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();

    private int difficulty;

    // constructor to create a dungeon
    public Dungeon(int enemyType, int difficulty){
        this.difficulty = difficulty;
        switch (enemyType) {
            case 1 -> enemyDungeon();
            case 2 -> vampireDungeon();
            case 3 -> golemDungeon();
        }
    }

    // creates the correct enemy dungeon based on difficulty chosen
    public void enemyDungeon(){
        for (int i = 0; i < 4+difficulty; i++){
            enemies.add(new Enemy("Enemy", 10*difficulty, difficulty, """
                    Enemies are people who have been corrupted by the pollution
            """));
        }
        enterDungeon("Enemy");
    }

    // creates the correct vampire dungeon based on difficulty chosen
    public void vampireDungeon(){
        for (int i = 0; i < 4+difficulty; i++){
            enemies.add(new Vampire("Vampire", 15*difficulty, 3*difficulty, """
                    Vampires are creatures that suck your blood
                    They steal your hp and heal themselves
                    (scaled according to how much hp you have)
                    """));
        }
        enterDungeon("Vampire");
    }

    // creates the correct golem dungeon based on difficulty chosen
    public void golemDungeon(){
        for (int i = 0; i < 4+difficulty; i++){
            enemies.add(new Golem("Golem", 20*difficulty, 2*difficulty, 5*difficulty, """
                    Golems are creatures made of rock and stone that have become sentient due to the pollution
                    They have strong defence, so attacks will deal less damage than usual
                    (scaled according to how much defence the golem has)
                    """));
        }
        enterDungeon("Golem");
    }

    // prints out what dungeon player entered as well as all the enemies' info
    public void enterDungeon(String enemyType){
        System.out.println();
        System.out.println(bold + "Dungeon " + enemyType + ": Difficulty " + difficulty + reset);
        for (Enemy enemy : enemies) {
            System.out.println(enemy);
        }
    }

    // get all the enemies inside the dungeon
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    // checks it all enemies in the dungeon are dead
    public boolean getAllEnemiesDead(){
        return (enemies.size() == 0);
    }

    // add a dead enemy to the deadEnemies list
    public void addDeadEnemy(Enemy enemy) {
        deadEnemies.add(enemy);
    }

    // removes all dead enemies from the dungeon
    public void updateEnemies(){
        enemies.removeAll(deadEnemies);
    }

    // updates the results after one turn
    public void fightUpdate(Player player) {
        System.out.println(bold + "Result" + reset);
        System.out.println("You have " + red + player.getHealth() + " hp" + reset);
        for (Enemy enemy : enemies){
            System.out.println(enemy);
        }
    }

    // runs when dungeon is successfully cleared
    // gets rid of the potions in effect (ends buff)
    public void dungeonCleared(Player player){
        System.out.println();
        System.out.println(bold + "Dungeon cleared!" + reset);
        for (Potion potion : player.getPotionsInUse()){
            potion.endOfEffect(player);
        }
        player.clearPotionsInUse();
        player.profile();
    }
}
