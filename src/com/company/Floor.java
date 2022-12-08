package com.company;
import java.util.ArrayList;

public class Floor {
    public static final String reset = "\u001B[0m";
    public static final String cyan = "\u001B[36m";
    public static final String bold = "\u001B[1m";
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Enemy> deadEnemies = new ArrayList<Enemy>();
    public static int floorLevel;

    public Floor(Player player){
        floorLevel += 1;
        generateEnemies(player);
    }

    public boolean getAllEnemiesDead(){
        return (enemies.size() == 0);
    }

    public void generateEnemies(Player player){
        for (int i = 0; i < floorLevel; i++){
            enemies.add(Enemy.generateRandomEnemy());
        }
        enterLevel(player);
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public void enterLevel(Player player){
        System.out.println();
        System.out.println(bold + "Floor " + floorLevel + reset);
        for (int i = 0; i < floorLevel; i++){
            System.out.println(enemies.get(i));
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
        player.profile();
        for (Potion potion : player.getPotionsInUse()){
            potion.endOfEffect(player);
        }
        player.clearPotionsInUse();
    }

    public void fightUpdate(Player player){
        System.out.println(bold + "Result" + reset);
        System.out.println("You have " + player.getHealth() + " hp");
        for (Enemy enemy : enemies){
            System.out.println(enemy);
        }
    }
}
