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
        Item item = Item.generateRandomDrop(true);
        player.defeatedMonster(item);
        System.out.println(name + " dropped " + item.getName());
        floor.addDeadEnemy(this);
    }
}
