package com.company;
// child of Enemy class (inheritance)
public class Golem extends Enemy{
    private int defence;

    // inherits constructor and instance variables of Enemy class, plus its own instance variable of defence
    public Golem(String name, int health, int attack, int defence, String description) {
        super(name, health, attack, description);
        this.defence = defence;
    }

    // override floor battle function from parent class (Enemy)
    // takes into account golem defence when calculating player damage
    public void battle(Player player, Floor floor){
        int playerDamage = (int) (player.getAttack() - player.getAttack()*(defence/100.0));
        System.out.println("You have dealt " + purple + playerDamage + " damage " + reset + "to " + name);
        health -= playerDamage;
        if (health <= 0){
            died(player, floor);
        }
    }

    // override dungeon battle function from parent class (Enemy)
    // takes into account golem defence when calculating player damage
    public void battle(Player player, Dungeon dungeon){
        int playerDamage = (int) (player.getAttack() - player.getAttack()*(defence/100.0));
        System.out.println("You have dealt " + purple + playerDamage + " damage " + reset + "to " + name);
        health -= playerDamage;
        if (health <= 0){
            died(player, dungeon);
        }
    }

    // when golem dies in a dungeon, only drop golem materials
    public void died(Player player, Dungeon dungeon){
        System.out.println(name + " has died");
        player.defeatedMonster(Item.materialDrops[2]);
        System.out.println(name + " dropped " + Item.materialDrops[2].getName());
        dungeon.addDeadEnemy(this);
    }
}
