package com.company;

import java.util.ArrayList;

public class Dungeon {
    public static final String red = "\u001B[31m";
    public static final String bold = "\u001B[1m";
    public static final String reset = "\u001B[0m";
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();

    private int difficulty;

    public Dungeon(int enemyType, int difficulty){
        this.difficulty = difficulty;
        switch (enemyType) {
            case 1 -> enemyDungeon();
            case 2 -> vampireDungeon();
            case 3 -> golemDungeon();
        }
    }

    public void enemyDungeon(){
        for (int i = 0; i < 4+difficulty; i++){
            enemies.add(new Enemy("Enemy", 10*difficulty, difficulty, """
                    Enemies are people who have been corrupted by the pollution
            """));
        }
        enterDungeon("Enemy");
    }

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

    public void enterDungeon(String enemyType){
        System.out.println();
        System.out.println(bold + "Dungeon " + enemyType + ": Difficulty " + difficulty + reset);
        for (Enemy enemy : enemies) {
            System.out.println(enemy);
        }
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
    }

    public void addDeadEnemy(Enemy enemy) {
        deadEnemies.add(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void fightUpdate(Player player) {
        System.out.println(bold + "Result" + reset);
        System.out.println(red + "You have " + player.getHealth() + " hp" + reset);
        for (Enemy enemy : enemies){
            System.out.println(enemy);
        }
    }
}
