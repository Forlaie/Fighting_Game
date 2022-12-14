package com.company;
// child of Item class (inheritance)
public class Potion extends Item{
    // inherits constructor and instance variables of Item class
    public Potion(String name, int health, int defence, int attack, int cost, String description) {
        super(name, health, defence, attack, cost, description);
    }

    // resets player stats back to normal after buff is over
    public void endOfEffect(Player player){
        switch (name){
            case "Defence potion" -> player.setDefence(player.getDefence()-defence);
            case "Attack potion" -> player.setAttack(player.getAttack()-attack);
        }
    }

    // overload toString() method to return the necessary info about potions
    public String toString(){
        return bold + name + reset + "\n" + italic + description + reset;
    }
}
