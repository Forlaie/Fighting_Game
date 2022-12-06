package com.company;
// lives again
// mystery drop (lore?)
// MYSTERY DROP IS ANCIENT SEED
// EAT SEED GO TO MARYS

public class Reaper extends Enemy{
    public Reaper(String name, int health, int attack, String description) {
        super(name, health, attack, description);
    }

    public void died(Player player, Floor floor){
        System.out.println(name + " has died");
        Item item = Item.generateRandomDrop();
        player.defeatedMonster(item);
        System.out.println(name + " dropped " + item.getName());
        floor.addDeadEnemy(this);
    }
}
