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
        List<String> keyList = new ArrayList<>(player.getInventory().keySet());
        String itemName = keyList.get(index);
        player.removeItem(itemName);
        return itemName;
    }
    public void died(Player player, Floor floor){
        if (player.getMaterials().size() != 0){
            String itemName = steal(player);
            System.out.println(name + " steals " + itemName + " before dying");
        }
        else{
            System.out.println(name + " has died");
        }
        floor.addDeadEnemy(this);
    }
}
