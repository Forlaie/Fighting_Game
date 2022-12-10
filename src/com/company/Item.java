package com.company;

import java.util.Scanner;

public class Item {
    public static final String purple = "\u001B[35m";
    public static final String red = "\u001B[31m";
    public static final String blue = "\u001B[34m";
    public static final String cyan = "\u001B[36m";
    public static final String yellow = "\u001B[33m";
    public static final String bold = "\u001B[1m";
    public static final String italic = "\033[3m";
    public static final String reset = "\u001B[0m";
    protected String name;
    protected int health;
    protected int defence;
    protected int attack;
    protected int cost;
    protected String description;
    public static final Item[] weaponDrops = {
            new Item("Sword", 0, 0, 10, 10, """
                    The sword is a sturdy and reliable weapon for any warrior
                    +10 atk"""),
            new Item("Shield", 5, 10, 0, 10, """
                    The shield is an essential for any warrior to protect themselves and others
                    +5 hp, +10 def"""),
            new Item("Armour", 20, 5, 0, 30, """
                    Proper armour keeps your vitals safe
                    +10 hp, +20 def, +5 atk""")
    };
    public static final Item[] materialDrops = {
            new Item("Enemy material", 1, """
                    Enemies drop this"""),
            new Item("Vampire material", 1, """
                    Vampires drop this"""),
            new Item("Golem material", 1, """
                    Golems drop this""")
    };
    public static final Potion[] potions = {
            new Potion("Health potion", 50, 0, 0, 50, """
                    Health potion heals you by 50hp points
                    Costs 50 coins"""),
            new Potion("Attack potion", 0, 0, 2, 50, """
                    Attack potion doubles your attack
                    Lasts for one floor/dungeon
                    Costs 50 coins"""),
            new Potion("Defence potion", 0, 2, 0, 50, """
                    Defence potion doubles your defence
                    Lasts for one floor/dungeon
                    Costs 50 coins""")
    };
//    public static Item[] dragonDrops = {
//            new Item("Dragon Sword", 10, 10, 30, 50, """
//                    A sword that holds the power of a dragon, it gives off an oppressive aura that affects all other enemies below it
//                    +10 hp, +10 def, +30 atk
//                    """),
//            new Item("Dragon Shield", 20, 30, 10, 50, """
//                    A shield made from the indestructible scales of a dragon, it stays strong in the face of any attack
//                    +20 hp, +30 def, +10 atk
//                    """),
//            new Item("Dragon Shoes", 10, 15, 10, 40, """
//                    Shoes infused with the swiftness of a dragon's flight, it makes those who wear it feel as light and nimble as a feather
//                    +10 hp, +15 def, +10 atk
//                    """),
//            new Item("Dragon Gloves", 10, 15, 10, 45, """
//                    Gloves made from the claws of a dragon, it gives those who wear it a grip strength that rivals a dragon
//                    +10 hp, +15 def, +10 atk
//                    """)
//    };

    public static void itemInfo(){
        for (Item item : materialDrops){
            System.out.println();
            System.out.println(bold + item.name + reset);
            System.out.println(italic + item.description + reset);
        }
        for (Potion potion : potions){
            System.out.println();
            System.out.println(bold + potion.name + reset);
            System.out.println(italic + potion.description + reset);
        }
        for (Item item : weaponDrops){
            System.out.println();
            System.out.println(bold + item.name + reset);
            System.out.println(italic + item.description + reset);
        }
    }

    public static Item generateRandomDrop(){
        int index;
        int getRandomDrop = (int) (Math.random()*101)+1;
        index = (int) (Math.random() * 3);
        if (getRandomDrop >= 90){
            return weaponDrops[index];
        }
        else{
            return materialDrops[index];
        }
    }

    public Item(String name, int health, int defence, int attack, int cost, String description){
        this.name = name;
        this.health = health;
        this.defence = defence;
        this.attack = attack;
        this.cost = cost;
        this.description = description;
    }

    public Item(String name, int cost, String description){
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public void upgradeItem(Player player){
        Scanner input = new Scanner(System.in);
        switch (this.name){
            case "Sword" -> {
                System.out.println(bold + "Do you want to use vampire materials or swords? " + cyan + "(V/S)" + reset);
                String choice = input.nextLine();
                switch (choice){
                    case "V" -> {
                        System.out.println(bold + "How many vampire materials will you use? " + reset + italic + "(Vampire materials: " + player.getVampireMaterials() + ") " + yellow + "(Coins: " + player.getCoins() + ")" + reset);
                        System.out.println(italic + "Note: Using one vampire material costs 5 coins" + reset);
                        int use = Integer.parseInt(input.nextLine());
                        int cost = use * 5;
                        if (use != 0){
                            if (player.getVampireMaterials() < use){
                                System.out.println("Sorry, you don't have enough vampire materials");
                            }
                            else if (player.getCoins() < cost){
                                System.out.println("Sorry, you don't have enough coins.");
                            }
                            else{
                                player.useVampireMaterial(use);
                                player.useCoins(cost);
                                player.setEquipped(0, 0, 0, use);
                                System.out.println("Successfully upgraded sword!");
                            }
                        }
                    }
                    case "S" -> {
                        System.out.println(bold + "How many swords will you use? " + reset + italic + "(Swords: " + player.getSwords() + ") " + yellow + "(Coins: " + player.getCoins() + ")" + reset);
                        System.out.println(italic + "Note: Using one sword costs 20 coins" + reset);
                        int use = Integer.parseInt(input.nextLine());
                        int cost = use * 5;
                        if (use != 0){
                            if (player.getSwords() < use){
                                System.out.println("Sorry, you don't have enough swords");
                            }
                            else if (player.getCoins() < cost){
                                System.out.println("Sorry, you don't have enough coins.");
                            }
                            else{
                                player.useSword(use);
                                player.useCoins(cost);
                                player.setEquipped(0, use, use, 10*use);
                                System.out.println("Successfully upgraded sword!");
                            }
                        }
                    }
                    default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                }
            }
            case "Shield" -> {
                System.out.println(bold + "Do you want to use enemy materials or shields? " + cyan + "(E/S)" + reset);
                String choice = input.nextLine();
                switch (choice){
                    case "E" -> {
                        System.out.println(bold + "How many enemy materials will you use? " + reset + italic + "(Enemy materials: " + player.getEnemyMaterials() + ") " + yellow + "(Coins: " + player.getCoins() + ")" + reset);
                        System.out.println(italic + "Note: Using one enemy material costs 5 coins" + reset);
                        int use = Integer.parseInt(input.nextLine());
                        int cost = use * 5;
                        if (use != 0){
                            if (player.getEnemyMaterials() < use){
                                System.out.println("Sorry, you don't have enough enemy materials");
                            }
                            else if (player.getCoins() < cost){
                                System.out.println("Sorry, you don't have enough coins.");
                            }
                            else{
                                player.useEnemyMaterial(use);
                                player.useCoins(cost);
                                player.setEquipped(1, 0, use, 0);
                                System.out.println("Successfully upgraded shield!");
                            }
                        }
                    }
                    case "S" -> {
                        System.out.println(bold + "How many shields will you use? " + reset + italic + "(Shields: " + player.getShields() + ") " + yellow + "(Coins: " + player.getCoins() + ")" + reset);
                        System.out.println(italic + "Note: Using one shield costs 20 coins" + reset);
                        int use = Integer.parseInt(input.nextLine());
                        int cost = use * 5;
                        if (use != 0){
                            if (player.getShields() < use){
                                System.out.println("Sorry, you don't have enough shields");
                            }
                            else if (player.getCoins() < cost){
                                System.out.println("Sorry, you don't have enough coins.");
                            }
                            else{
                                player.useShield(use);
                                player.useCoins(cost);
                                player.setEquipped(1, 5*use, use, use);
                                System.out.println("Successfully upgraded shield");
                            }
                        }
                    }
                    default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                }
            }
            case "Armour" -> {
                System.out.println(bold + "Do you want to use golem materials or armours? " + cyan + "(G/A)" + reset);
                String choice = input.nextLine();
                switch (choice) {
                    case "G" -> {
                        System.out.println(bold + "How many golem materials will you use? " + reset + italic + "(Golem materials: " + player.getGolemMaterials() + ") " + yellow + "(Coins: " + player.getCoins() + ")" + reset);
                        System.out.println(italic + "Note: Using one golem material costs 5 coins" + reset);
                        int use = Integer.parseInt(input.nextLine());
                        int cost = use * 5;
                        if (use != 0){
                            if (player.getGolemMaterials() < use){
                                System.out.println("Sorry, you don't have enough golem materials");
                            }
                            else if (player.getCoins() < cost){
                                System.out.println("Sorry, you don't have enough coins.");
                            }
                            else{
                                player.useGolemMaterial(use);
                                player.useCoins(cost);
                                player.setEquipped(2, 0, use, 0);
                                System.out.println("Successfully upgraded armour!");
                            }
                        }
                    }
                    case "A" -> {
                        System.out.println(bold + "How many armours will you use? " + reset + italic + "(Armours: " + player.getArmours() + ") " + yellow + "(Coins: " + player.getCoins() + ")" + reset);
                        System.out.println(italic + "Note: Using one armour costs 50 coins" + reset);
                        int use = Integer.parseInt(input.nextLine());
                        int cost = use * 5;
                        if (use != 0){
                            if (player.getArmours() < use){
                                System.out.println("Sorry, you don't have enough armours");
                            }
                            else if (player.getCoins() < cost){
                                System.out.println("Sorry, you don't have enough coins.");
                            }
                            else{
                                player.useArmour(use);
                                player.useCoins(cost);
                                player.setEquipped(2, use, 20*use, use);
                                System.out.println("Successfully upgraded armour!");
                            }
                        }
                    }
                    default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                }
            }
        }
    }

    public int getHealth(){
        return health;
    }

    public int getDefence(){
        return defence;
    }

    public int getAttack(){
        return attack;
    }
    public int getCost(){
        return cost;
    }

    public String toString(){
        return bold + name + ": " + reset + italic + red + "+" + health + " hp" + reset + italic + ", " + blue + "+" + defence + " def" + reset + italic + ", " + purple + "+" + attack + " atk " + reset + italic + ", " + yellow + cost + " coins" + reset;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDefence(int defence){
        this.defence = defence;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }
}
