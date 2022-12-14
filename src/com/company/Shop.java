package com.company;
import java.util.Scanner;
import java.util.Set;
public class Shop {
    public static final String cyan = "\u001B[36m";
    public static final String yellow = "\u001B[33m";
    public static final String bold = "\u001B[1m";
    public static final String reset = "\u001B[0m";

    // show items available for sale in shop
    public static void shopMenu(Player player){
        System.out.println();
        System.out.println(bold + "What would you like to purchase? " + reset);
        System.out.println(yellow + "Coins: " + player.getCoins() + reset);
        for (int i = 0; i < Item.potions.length; i++){
            System.out.println();
            System.out.println(bold + cyan + (i+1) + ": " + reset + Item.potions[i]);
        }
    }

    // player wants to purchase item
    public static void purchaseItem(Player player){
        Scanner input = new Scanner(System.in);
        shopMenu(player);
        int index = Integer.parseInt(input.nextLine());
        Potion potion = Item.potions[index-1];
        // check if player has enough coins
        if (player.getCoins() < potion.getCost()){
            System.out.println("Unfortunately, you don't have enough coins");
        }
        // update player coins and inventory to reflect purchase
        else{
            System.out.println("Successfully purchased " + potion.getName() + "!");
            player.useCoins(potion.getCost());
            player.getInventory().merge(potion, 1, Integer::sum);
        }
    }

    // player wants to sell material
    public static void sellMaterial(Player player){
        // check if player has anything to sell
        if (player.getMaterials().isEmpty()){
            System.out.println("Sorry, you don't have any materials to sell");
        }
        else{
            Scanner input = new Scanner(System.in);
            int i = 1;
            // ask what player wants to sell and how many they want to sell
            // update coins and materials, or give warning messages accordingly
            System.out.println(bold + "What would you like to sell?" + reset);
            for (Item key : player.getMaterials().keySet()) {
                System.out.println(bold + cyan + i + ": " + reset + bold + key.getName() + ": " + reset + player.getMaterials().get(key) + ", " + yellow + "+" + key.getCost() + " coins" + reset);
                i++;
            }
            Set<Item> keySet = player.getMaterials().keySet();
            Item[] keyArray = keySet.toArray(new Item[keySet.size()]);
            int index = Integer.parseInt(input.nextLine());
            Item item = keyArray[index-1];
            switch(item.name){
                case "Enemy material" -> item = Item.materialDrops[0];
                case "Vampire material" -> item = Item.materialDrops[1];
                case "Golem material" -> item = Item.materialDrops[2];
            }
            System.out.println(bold + "How many " + item.getName() + " would you like to sell?" + reset);
            int amount = Integer.parseInt(input.nextLine());
            int profit = (int) Math.ceil((item.getCost()*0.8));
            if (amount > player.getMaterials().get(item)){
                System.out.println("Sorry, you don't have that many " + item.getName());
            }
            else{
                System.out.println("Successfully sold " + amount + " " + item.getName());
                player.getMaterials().put(item, player.getMaterials().get(item)-amount);
                if (player.getMaterials().get(item) == 0){
                    player.getMaterials().remove(item);
                }
                player.soldItem(profit);
            }
        }
    }

    // player wants to sell potion
    public static void sellPotion(Player player){
        Scanner input = new Scanner(System.in);
        int i = 1;
        // ask what player wants to sell and how many they want to sell
        // update coins and inventory, or give warning messages accordingly
        System.out.println(bold + "What would you like to sell?" + reset);
        for (Potion key : player.getInventory().keySet()) {
            System.out.println(i + ": " + key.getName() + ": " + player.getInventory().get(key) + ", +" + (key.getCost()*0.8) + " coins");
            i++;
        }
        Set<Potion> keySet = player.getInventory().keySet();
        Potion[] keyArray = keySet.toArray(new Potion[keySet.size()]);
        int index = Integer.parseInt(input.nextLine());
        Potion potion = keyArray[index-1];
        switch(potion.name){
            case "Health potion" -> potion = Item.potions[0];
            case "Attack potion" -> potion = Item.potions[1];
            case "Defence potion" -> potion = Item.potions[2];
        }
        System.out.println(bold + "How many " + potion.getName() + " would you like to sell?" + reset);
        int amount = Integer.parseInt(input.nextLine());
        int profit = (int) (potion.getCost()*0.8);
        if (amount > player.getInventory().get(potion)){
            System.out.println("Sorry, you don't have that many " + potion.getName());
        }
        else{
            System.out.println("Successfully sold " + amount + " " + potion.getName());
            player.getInventory().replace(potion, player.getInventory().get(potion)-amount);
            if (player.getInventory().get(potion) == 0){
                player.getInventory().remove(potion);
            }
            player.soldItem(profit);
        }
    }
}
