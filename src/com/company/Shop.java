package com.company;

public class Shop {
    public static final String cyan = "\u001B[36m";
    public static final String yellow = "\u001B[33m";
    public static final String reset = "\u001B[0m";
    public static final String bold = "\u001B[1m";
    public static final String italic = "\033[3m";

    public static void shopMenu(Player player){
        System.out.println();
        System.out.println(bold + "What would you like to purchase? " + reset + italic + "(Enter the number of the item)" + reset);
        System.out.println(yellow + "Coins: " + player.getCoins() + reset);
        for (int i = 0; i < Item.potions.length; i++){
            System.out.println();
            System.out.println(bold + cyan + (i+1) + ": " + reset + Item.potions[i]);
        }
    }
}
