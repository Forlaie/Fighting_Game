package com.company;
import java.awt.*;
import java.util.Scanner;

public class Shop {
    public static void shopMenu(){
        System.out.println();
        System.out.println("Enter the number of the item you would like to purchase");
        for (int i = 0; i < Item.drops.length; i++){
            System.out.println((i+1) + ": " + Item.drops[i]);
        }
    }
}
