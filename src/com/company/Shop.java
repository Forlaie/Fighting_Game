package com.company;
import java.awt.*;
import java.util.Scanner;

public class Shop {
    public static final String cyan = "\u001B[36m";
    public static final String reset = "\u001B[0m";
    public static final String bold = "\u001B[1m";
    public static final String italic = "\033[3m";
    public static void shopMenu(){
        System.out.println();
        System.out.println(bold + "What would you like to purchase?" + reset);
        for (int i = 0; i < Item.drops.length-4; i++){
            System.out.println(bold + cyan + (i+1) + ": " + reset + Item.drops[i]);
        }
    }
}
