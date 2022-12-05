package com.company;

import java.util.ArrayList;

public class Dungeon {
    public static final String bold = "\u001B[1m";
    public static final String reset = "\u001B[0m";
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();
//    private ArrayList<Vampire> vampires = new ArrayList<Vampire>();
//    private ArrayList<Vampire> deadVampires = new ArrayList<Vampire>();
//    private ArrayList<Golem> golems = new ArrayList<Golem>();
//    private ArrayList<Golem> deadGolems = new ArrayList<Golem>();
    private int difficulty;

    public Dungeon(Player player, int enemyType, int difficulty){
        switch (enemyType) {
            case 1 -> enemyDungeon(player, difficulty);
            case 2 -> vampireDungeon(player, difficulty);
            case 3 -> golemDungeon(player, difficulty);
        }
    }

    public void enemyDungeon(Player player, int difficulty){
        for (int i = 0; i < 5; i++){
            Enemy enemy = new Enemy("Enemy", 10*difficulty, difficulty, """
                    Enemies are people who have been corrupted by the pollution
            """);
            enemies.add(enemy);
        }
        enterDungeon(player, "Enemy", difficulty);
    }

    public void vampireDungeon(Player player, int difficulty){
        for (int i = 0; i < 5; i++){
            Vampire vampire = new Vampire("Vampire", 15*difficulty, 3*difficulty, """
                    Vampires are creatures that suck your blood
                    They steal your hp and heal themselves
                    (scaled according to how much hp you have)
                    """);
            enemies.add(vampire);
        }
        enterDungeon(player, "Vampire", difficulty);
    }

    public void golemDungeon(Player player, int difficulty){
        for (int i = 0; i < 5; i++){
            Golem golem = new Golem("Golem", 20*difficulty, 2*difficulty, 5*difficulty, """
                    Golems are creatures made of rock and stone that have become sentient due to the pollution
                    They have strong defence, so attacks will deal less damage than usual
                    (scaled according to how much defence the golem has)
                    """);
            enemies.add(golem);
        }
        enterDungeon(player, "Golem", difficulty);
    }

    public void enterDungeon(Player player, String enemyType, int difficulty){
        System.out.println();
        System.out.println(bold + "Dungeon " + enemyType + ": Difficulty " + difficulty + reset);
        for (Enemy enemy : enemies) {
            System.out.println(enemy);
        }
        player.setIsFighting(true);
    }

    public boolean getAllEnemiesDead(){
        return (enemies.size() == 0);
    }

    public void updateEnemies(){
        enemies.removeAll(deadEnemies);
    }

    public void dungeonCleared(Player player){
        System.out.println();
        System.out.println(bold + "Dungeon cleared!" + reset);
        player.profile();
        player.setIsFighting(false);
    }

    public void addDeadEnemy(Enemy enemy) {
        deadEnemies.add(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void fightUpdate(Player player) {
        System.out.println(bold + "Result" + reset);
        System.out.println("You have " + player.getHealth() + " hp");
        for (Enemy enemy : enemies){
            System.out.println(enemy);
        }
    }
}
