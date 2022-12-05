package com.company;
// have different types of armour (e.g. fireproof, rubber)
// e.g. fireproof good for fighting dragons
// can merge armours to make them stronger/better
// MAKE POTIONS

import java.util.Scanner;

public class Item {
    public static final String reset = "\u001B[0m";
    public static final String italic = "\033[3m";
    public static final String bold = "\u001B[1m";
    private String name;
    private int health;
    private int defence;
    private int attack;
    private int cost;
    private String description;
    public static Item[] drops = {
            new Item("Sword", 0, 0, 10, 10, """
                    The sword is a sturdy and reliable weapon for any warrior
                    +10 atk
                    """),
            new Item("Shield", 5, 10, 0, 10, """
                    The shield is an essential for any warrior to keep themselves safe and protect what they need to protect
                    +5 hp, +10 def
                    """),
            new Item("Armour", 20, 5, 0, 30, """
                    Proper armour keeps your vitals safe
                    +10 hp, +20 def, +5 atk
                    """),
            new Item("Enemy material", "enemies drop this"),
            new Item("Vampire material", "vampires drop this"),
            new Item("Golem material", "golems drop this"),
            new Item("Dragon Sword", 10, 10, 30, 50, """
                    A sword that holds the power of a dragon, it gives off an oppressive aura that affects all other enemies below it
                    +10 hp, +10 def, +30 atk
                    """),
            new Item("Dragon Shield", 20, 30, 10, 50, """
                    A shield made from the indestructible scales of a dragon, it stays strong in the face of any attack
                    +20 hp, +30 def, +10 atk
                    """),
            new Item("Dragon Shoes", 10, 15, 10, 40, """
                    Shoes infused with the swiftness of a dragon's flight, it makes those who wear it feel as light and nimble as a feather
                    +10 hp, +15 def, +10 atk
                    """),
            new Item("Dragon Gloves", 10, 15, 10, 45, """
                    Gloves made from the claws of a dragon, it gives those who wear it a grip strength that rivals a dragon
                    +10 hp, +15 def, +10 atk
                    """)
    };

    public static void itemInfo(){
        System.out.println();
        for (Item item : drops){
            System.out.println(item.name);
            System.out.println(italic + item.description + reset);
        }
    }

    public static Item generateRandomDrop(){
        int getRandomDrop = (int) (Math.random()*101)+1;
        int index;
        if (getRandomDrop >= 90){
            index = (int) (Math.random() * 3);
        }
        else{
            index = (int) (Math.random() * (3)) + 3;
        }
        return drops[index];
    }

    public Item(String name, int health, int defence, int attack, int cost, String description){
        this.name = name;
        this.health = health;
        this.defence = defence;
        this.attack = attack;
        this.cost = cost;
        this.description = description;
    }

    public Item(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

//    public String getDescription(){
//        return description;
//    }

    public void upgradeItem(Player player){
        Scanner input = new Scanner(System.in);
        switch (this.name){
            case "Sword" -> {
                System.out.println("Do you want to use vampire materials or other swords? (V/S)");
                String choice = input.nextLine();
                switch (choice){
                    case "V" -> {
                        System.out.println("How many vampire materials will you use? (Avaliable: #)");
                        int use = Integer.parseInt(input.nextLine());
                        upgradeAttack(use, player);
                    }
                    case "S" -> {
                        System.out.println("How many swords will you use? (Avaliable: #)");
                        int use = Integer.parseInt(input.nextLine());
                        upgradeAttack(10*use, player);
                        upgradeDefence(use, player);
                        upgradeHealth(use, player);
                    }
                }
            }
            case "Shield" -> {
                System.out.println("Do you want to use golem materials or other shields? (G/S)");
                String choice = input.nextLine();
                switch (choice){
                    case "G" -> {
                        System.out.println("How many golem materials will you use? (Avaliable: #)");
                        int use = Integer.parseInt(input.nextLine());
                        upgradeDefence(use, player);
                    }
                    case "S" -> {
                        System.out.println("How many shields will you use? (Avaliable: #)");
                        int use = Integer.parseInt(input.nextLine());
                        upgradeAttack(use, player);
                        upgradeDefence(10*use, player);
                        upgradeHealth(5*use, player);
                    }
                }
            }
            case "Armour" -> {
                System.out.println("Do you want to use enemy materials or other armours? (E/A)");
                String choice = input.nextLine();
                switch (choice) {
                    case "E" -> {
                        System.out.println("How many enemy materials will you use? (Avaliable: #)");
                        int use = Integer.parseInt(input.nextLine());
                        upgradeHealth(use, player);
                    }
                    case "A" -> {
                        System.out.println("How many armours will you use? (Avaliable: #)");
                        int use = Integer.parseInt(input.nextLine());
                        upgradeAttack(use, player);
                        upgradeDefence(5*use, player);
                        upgradeHealth(20*use, player);
                    }
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

    public void upgradeHealth(int increase, Player player){
        health += increase;
        player.setHealth(player.getHealth() + increase);
    }

    public void upgradeDefence(int increase, Player player){
        defence += increase;
        player.setDefence(player.getDefence() + increase);
    }

    public void upgradeAttack(int increase, Player player){
        attack += increase;
        player.setAttack(player.getAttack() + increase);
    }

    public String toString(){
        return name + ":" + " +" + health + " hp, +" + defence + " def, +" + attack + " atk, " + cost + " coins";
    }
}
