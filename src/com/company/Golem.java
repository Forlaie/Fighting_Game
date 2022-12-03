package com.company;

public class Golem extends Enemy{
    private int defence;

    public Golem(String name, int health, int attack, int defence, String description) {
        super(name, health, attack, description);
        this.defence = defence;
    }

    public void battle(Player player, Floor floor){
        int playerDamage = (int) (player.getAttack() - player.getAttack()*(defence/100.0));
        System.out.println("You have dealt " + playerDamage + " damage");
        health -= playerDamage;
        if (health <= 0){
            died(player, floor);
        }
    }
}
