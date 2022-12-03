package com.company;

public class Vampire extends Enemy{
    public Vampire(String name, int health, int attack, String description) {
        super(name, health, attack, description);
    }

    public void battle(Player player, Floor floor){
        health -= player.getAttack();
        System.out.println("You have dealt " + player.getAttack() + " damage");
        int suckedBlood = (int) (player.getHealth()*0.05);
        health += suckedBlood;
        System.out.println("Vampire sucked " + suckedBlood + " hp from you");
        player.setHealth(player.getHealth() - suckedBlood);
        if (health <= 0){
            died(player, floor);
        }
    }
}
