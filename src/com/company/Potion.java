package com.company;
// FIX POTIONS CAUSE IDK WHY ITS NOT WORKING

public class Potion extends Item{
    public Potion(String name, int health, int defence, int attack, int cost, String description) {
        super(name, health, defence, attack, cost, description);
    }

    public void endOfEffect(Player player){
        switch (name){
            case "Defence potion" -> player.setDefence(player.getDefence()/defence);
            case "Attack potion" -> player.setAttack(player.getAttack()/attack);
        }
    }

    public String toString(){
        return bold + name + reset + "\n" + italic + description + reset;
    }
}
