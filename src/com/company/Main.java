package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// do this over the weekend
// do save file stuff (especially after you die + save and exit)
// have final floor (10?) with only reaper? (give lore)

public class Main {

    public static final String reset = "\u001B[0m";
    public static final String black = "\u001B[30m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    public static final String white = "\u001B[37m";
    public static final String bold = "\u001B[1m";
    public static final String italic = "\033[3m";

    public static void mainMenu(){
        System.out.println();
        System.out.println(bold + "What would you like to do?" + reset);
        System.out.println("========================= ©MM");
        System.out.println(italic + cyan + "1:" + reset + italic + " open info menu");
        System.out.println(cyan + "2:" + reset + italic + " check profile");
        System.out.println(cyan + "3:" + reset + italic + " change username");
        System.out.println(cyan + "4:" + reset + italic + " access shop");
        System.out.println(cyan + "5:" + reset + italic + " check inventory");
        System.out.println(cyan + "6:" + reset + italic + " enter floor " + (Floor.floorLevel+1));
        System.out.println(cyan + "7:" + reset + italic + " enter a dungeon" + reset);
        System.out.println(cyan + "8:" + reset + italic + " level up items" + reset);
        System.out.println(cyan + "9:" + reset + italic + " save and exit" + reset);
        System.out.println("========================= ©MM");
    }

    public static void fightMenu(){
        System.out.println();
        System.out.println(bold + "What would you like to do?" + reset);
        System.out.println("========================= ©MM");
        System.out.println(italic + cyan + "1:" + reset + italic + " open info menu");
        System.out.println(cyan + "2:" + reset + italic + " check stats");
        System.out.println(cyan + "3:" + reset + italic + " check inventory");
        System.out.println(cyan + "4:" + reset + italic + " fight");
        System.out.println(cyan + "5:" + reset + italic + " use potion");
        System.out.println(cyan + "6:" + reset + italic + " save and exit" + reset);
        System.out.println("========================= ©MM");
    }

    public static void infoMenu(){
        System.out.println();
        System.out.println(bold + "Info Menu" + reset);
        System.out.println("=============== ©MM");
        System.out.println(italic + cyan + "1:" + reset + italic + " enemy info");
        System.out.println(cyan + "2:" + reset + italic + " item info" + reset);
        System.out.println("=============== ©MM");
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Player player = null;
        try {
            Scanner fileInput = new Scanner(new File("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\PlayerInfo"));
            //BufferedReader bf=new BufferedReader(new FileReader("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\PlayerInfo"));
            if (fileInput.hasNextLine()){
                while (fileInput.hasNextLine()){
                    String name = fileInput.nextLine();
                    int health = Integer.parseInt(fileInput.nextLine());
                    int defence = Integer.parseInt(fileInput.nextLine());
                    int attack = Integer.parseInt(fileInput.nextLine());
                    int level = Integer.parseInt(fileInput.nextLine());
                    int xp = Integer.parseInt(fileInput.nextLine());
                    int coins = Integer.parseInt(fileInput.nextLine());
                    // figure out how to get the items back...
                    // ArrayList<Item> inventory = new ArrayList<Item>();
                    // ArrayList<Item> equipped = new ArrayList<Item>();

                    player = new Player(name, health, defence, attack, level, xp, coins);
                    System.out.println(bold + "Welcome back " + green + player.getName() + reset + bold + "!" + reset);
                }
                fileInput.close();
            }
            else{
                System.out.println("Welcome to Wen Ymar Elad! What is your name?");
                String name = userInput.nextLine();
                player = new Player(name);
            }

            try {
                fileInput = new Scanner(new File("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\FloorInfo"));
                if (fileInput.hasNextLine()){
                    int floor = Integer.parseInt(fileInput.nextLine());
                    Floor.floorLevel = floor-1;
                }
                fileInput.close();

                assert player != null;
                mainMenu();
                int choice = Integer.parseInt(userInput.nextLine());
                Floor currentFloor = null;

                while (choice != 9){
                    switch (choice) {
                        case 1 -> {
                            // info about enemies and items
                            infoMenu();
                            int action = Integer.parseInt(userInput.nextLine());
                            switch (action){
                                case 1 ->
                                        Enemy.enemyInfo();
                                case 2 ->
                                        Item.itemInfo();
                            }
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 2 -> {
                            // profile stuff
                            player.profile();
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 3 -> {
                            // username stuff
                            System.out.println("What would you like your new username to be?");
                            String name = userInput.nextLine();
                            player.changeName(name);
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 4 -> {
                            // access shop
                            System.out.println(bold + "Would you like to buy or sell? " + cyan + "(B/S)" + reset);
                            String action = userInput.nextLine();
                            if (action.equals("B")){
                                Shop.shopMenu(player);
                                int index = Integer.parseInt(userInput.nextLine());
                                player.purchaseItem(Item.potions[index-1]);
                            }
                            else if (action.equals("S")){
                                System.out.println("Do you want to sell materials or potions? (M/P)");
                                String sellChoice = userInput.nextLine();
                                if (sellChoice.equals("M")){
                                    player.sellMaterial();
                                }
                                else if (sellChoice.equals("P")){
                                    player.sellPotion();
                                }
                            }
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 5 ->{
                            // check inventory stuff
                            player.inventoryMenu();
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 6 -> {
                            // fight stuff
                            currentFloor = new Floor(player);
                            while (!currentFloor.getAllEnemiesDead()) {
                                fightMenu();
                                choice = Integer.parseInt(userInput.nextLine());
                                switch (choice) {
                                    case 1 ->
                                        // info about enemies and items
                                            infoMenu();
                                    case 2 ->
                                        // check stats
                                            player.stats();
                                    case 3 ->
                                            // check inventory
                                        player.inventoryMenu();
                                    case 4 -> {
                                        //fight stuff
                                        player.battle(currentFloor);
                                        currentFloor.fightUpdate(player);
                                    }
                                    case 5 -> {
                                        if (player.getInventory().size() != 0){
                                            player.usePotion();
                                        }
                                        else{
                                            System.out.println("Sorry, you have no potions");
                                        }
                                    }
                                    default ->
                                            System.out.println("Sorry, that is not a recognized command. Please try again");
                                }
                            }
                            currentFloor.floorCleared(player);
                            fightMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 7 -> {
                            //Floor currentFloor = new Floor(player);
                            System.out.println("What dungeon do you want to enter?");
                            System.out.println("1: Enemy dungeon");
                            System.out.println("2: Vampire dungeon");
                            System.out.println("3: Golem dungeon");
                            int enemyType = Integer.parseInt(userInput.nextLine());
                            System.out.println("What difficulty do you want to enter?");
                            System.out.println("1, 2, 3");
                            int difficulty = Integer.parseInt(userInput.nextLine());
                            Dungeon dungeon = new Dungeon(player, enemyType, difficulty);
                            while (!dungeon.getAllEnemiesDead()){
                                fightMenu();
                                choice = Integer.parseInt(userInput.nextLine());
                                switch (choice) {
                                    case 1 -> {
                                        // info about enemies and items
                                        infoMenu();
                                        int action = Integer.parseInt(userInput.nextLine());
                                        switch (action){
                                            case 1 ->
                                                    Enemy.enemyInfo();
                                            case 2 ->
                                                    Item.itemInfo();
                                        }
                                    }
                                    case 2 ->
                                        // check stats
                                            player.stats();
                                    case 3 ->
                                        // item stuff
                                            player.inventoryMenu();
                                    case 4 -> {
                                        //fight stuff
                                        player.battle(dungeon, currentFloor);
                                        dungeon.fightUpdate(player);
                                    }
                                    default ->
                                            System.out.println("Sorry, that is not a recognized command. Please try again");
                                }
                            }
                            dungeon.dungeonCleared(player);
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 8 -> {
                            // do leveling up stuff
                            player.upgradeItem();
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        default ->
                                System.out.println("Sorry, that is not a recognized command. Please try again");
                    }
                }
                // save info into files and stuff

            } catch (FileNotFoundException e) {
                System.out.println("can't open floor file");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't open player file");
        }
    }
}
