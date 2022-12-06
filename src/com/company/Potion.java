package com.company;

public class Potion extends Item{
    private boolean inEffect;
    public Potion(String name, int health, int defence, int attack, int cost, String description) {
        super(name, health, defence, attack, cost, description);
        inEffect = false;
    }

    public void setInEffect(boolean bool){
        inEffect = bool;
    }

    public void endOfEffect(Player player){
        inEffect = false;
        switch (name){
            case "Defence potion" -> player.setDefence(player.getDefence()/defence);
            case "Attack potion" -> player.setAttack(player.getAttack()/attack);
        }
    }
}
