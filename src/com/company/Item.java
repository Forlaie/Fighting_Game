package com.company;
// have different types of armour (e.g. fireproof, rubber)
// e.g. fireproof good for fighting dragons
// can merge armours to make them stronger/better
// MAKE POTIONS

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
            new Item("Sword", Floor.floorLevel, Floor.floorLevel, 10+Floor.floorLevel, 10+Floor.floorLevel, """
                    The sword is a sturdy and reliable weapon for any warrior
                    +10 atk
                    """),
            new Item("Shield", Floor.floorLevel, 10+Floor.floorLevel, Floor.floorLevel, 10+Floor.floorLevel, """
                    The shield is an essential for any warrior to keep themselves safe and protect what they need to protect
                    +10 def
                    """),
            new Item("Shoes", Floor.floorLevel, 3+Floor.floorLevel, 3+Floor.floorLevel, 5+Floor.floorLevel, """
                    Proper footwear can help you dodge and attack faster
                    +10 def, +3 atk
                    """),
            new Item("Gloves", Floor.floorLevel, 3+Floor.floorLevel, 3+Floor.floorLevel, 5+Floor.floorLevel, """
                    Having a good grip on your weapon can be the deciding factor between life and death
                    +3 def, +3 atk
                    """),
            // new Item("health potion", 5, 0, 0)
            // get rid of from inventory when used
            // other buff potions last for one floor
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
            System.out.println(bold + item.name + reset);
            System.out.println(italic + item.description + reset);
        }
    }

    public static Item generateRandomDrop(){
        int index = (int) (Math.random()*(drops.length-4));
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

    public String getName(){
        return name;
    }

//    public String getDescription(){
//        return description;
//    }

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
        return bold + name + ":" + reset + " +" + health + " hp, +" + defence + " def, +" + attack + " atk, " + cost + " coins";
    }
}
