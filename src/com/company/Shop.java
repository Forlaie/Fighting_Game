package com.company;
import java.awt.*;
import java.util.Scanner;

public class Shop {
    public static final String cyan = "\u001B[36m";
    public static final String reset = "\u001B[0m";
    public static final String bold = "\u001B[1m";
    public static final String italic = "\033[3m";

    public static Potion[] inventory = {
            new Potion("Health potion", 50, 0, 0, 50, """
                    Health potion heals you
                    """),
            new Potion("Attack potion", 0, 0, 5, 50, """
                    Attack potion increases your attack
                    """),
            new Potion("Defence potion", 0, 5, 0, 50, """
                    Defence potion increases your defence
                    """)
            // get rid of from inventory when used
            // other buff potions last for one floor
    };

    public static void shopMenu(){
        System.out.println();
        System.out.println(bold + "What would you like to purchase?" + reset);
        for (int i = 0; i < inventory.length; i++){
            System.out.println(bold + cyan + (i+1) + ": " + reset + inventory[i]);
        }
    }
}
