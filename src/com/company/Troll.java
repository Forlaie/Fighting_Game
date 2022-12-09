package com.company;

import java.util.ArrayList;
import java.util.List;

// steals an item
public class Troll extends Enemy{

    public Troll(String name, int health, int attack, String description) {
        super(name, health, attack, description);
    }

    public String steal(Player player){
        int index = (int) (Math.random()*player.getInventory().size());
        List<Potion> keyList = new ArrayList<>(player.getInventory().keySet());
        Potion potion = keyList.get(index);
        switch (potion.getName()){
            case "Health potion" -> potion = Item.potions[0];
            case "Attack potion" -> potion = Item.potions[1];
            case "Defence potion" -> potion = Item.potions[2];
        }
        player.removeItem(potion);
        return potion.getName();
    }
    public void died(Player player, Floor floor){
        if (!player.getInventory().isEmpty()){
            String itemName = steal(player);
            System.out.println(name + " steals " + itemName + " before dying");
        }
        else{
            System.out.println(name + " has died");
        }
        floor.addDeadEnemy(this);
    }
}
