package com.company;
public class Dragon extends Enemy{
    private int defence;

    public Dragon(String name, int health, int attack, int defence, String description) {
        super(name, health, attack, description);
        this.defence = defence;
    }

    public void battle(Player player, Floor floor){
        int playerDamage = (int) (player.getAttack() - player.getAttack()*(defence/100.0));
        System.out.println("You have dealt " + playerDamage + " damage to " + name);
        health -= playerDamage;
        if (health <= 0){
            died(player, floor);
        }
    }

    public void died(Player player, Floor floor){
        System.out.println(name + " has died");
        int stat = (int) (Math.random()*3);
        int value = (int) (Math.random()*10)+1;
        switch (stat){
            case 0 -> {
                System.out.println("Health stat increased by " + value + "!");
                player.setHealth(player.getHealth()+value);
            }
            case 1 -> {
                System.out.println("Defence stat increased by " + value + "!");
                player.setDefence(player.getDefence()+value);
            }
            case 2 -> {
                System.out.println("Attack stat increased by " + value + "!");
                player.setAttack(player.getAttack()+value);
            }
        }
        floor.addDeadEnemy(this);
    }
}
