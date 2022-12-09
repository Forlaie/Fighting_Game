package com.company;

public class Golem extends Enemy{
    private int defence;

    public Golem(String name, int health, int attack, int defence, String description) {
        super(name, health, attack, description);
        this.defence = defence;
    }

    public void battle(Player player, Floor floor){
        int playerDamage = (int) (player.getAttack() - player.getAttack()*(defence/100.0));
        System.out.println("You have dealt " + purple + playerDamage + " damage " + reset + "to " + name);
        health -= playerDamage;
        if (health <= 0){
            died(player, floor);
        }
    }

    public void battle(Player player, Dungeon dungeon){
        int playerDamage = (int) (player.getAttack() - player.getAttack()*(defence/100.0));
        System.out.println("You have dealt " + purple + playerDamage + " damage " + reset + "to " + name);
        health -= playerDamage;
        if (health <= 0){
            died(player, dungeon);
        }
    }

    public void died(Player player, Dungeon dungeon){
        System.out.println(name + " has died");
        player.defeatedMonster(Item.materialDrops[2]);
        System.out.println(name + " dropped " + Item.materialDrops[2].getName());
        dungeon.addDeadEnemy(this);
    }
}
