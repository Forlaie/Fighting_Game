package com.company;

import java.io.*;
import java.util.*;

// have final floor (10?) with only reaper? (give lore)

public class Main {

    public static final String reset = "\u001B[0m";
    public static final String green = "\u001B[32m";
    public static final String cyan = "\u001B[36m";
    public static final String bold = "\u001B[1m";
    public static final String italic = "\033[3m";

    public static void mainMenu(){
        System.out.println();
        System.out.println(bold + "What would you like to do?" + reset);
        System.out.println("========================= ©MM");
        System.out.println(italic + cyan + "1:" + reset + italic + " open info menu");
        System.out.println(cyan + "2:" + reset + italic + " change username");
        System.out.println(cyan + "3:" + reset + italic + " check profile");
        System.out.println(cyan + "4:" + reset + italic + " check inventory");
        System.out.println(cyan + "5:" + reset + italic + " level up items" + reset);
        System.out.println(cyan + "6:" + reset + italic + " access shop");
        System.out.println(cyan + "7:" + reset + italic + " enter a dungeon" + reset);
        System.out.println(cyan + "8:" + reset + italic + " enter floor " + Floor.floorLevel);
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
        System.out.println(cyan + "4:" + reset + italic + " use potion");
        System.out.println(cyan + "5:" + reset + italic + " fight");
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
            if (fileInput.hasNextLine()){
                while (fileInput.hasNextLine()){
                    String name = fileInput.nextLine();
                    int health = Integer.parseInt(fileInput.nextLine());
                    int defence = Integer.parseInt(fileInput.nextLine());
                    int attack = Integer.parseInt(fileInput.nextLine());
                    int level = Integer.parseInt(fileInput.nextLine());
                    int xp = Integer.parseInt(fileInput.nextLine());
                    int coins = Integer.parseInt(fileInput.nextLine());
                    int[] materialQuantities = new int[6];
                    for (int i = 0; i < 6; i++){
                        int quantity = Integer.parseInt(fileInput.nextLine());
                        materialQuantities[i] = quantity;
                    }
                    int[] potionQuantities = new int[3];
                    for (int i = 0; i < 3; i++){
                        int quantity = Integer.parseInt(fileInput.nextLine());
                        potionQuantities[i] = quantity;
                    }
                    int[] swordInfo = new int[3];
                    for (int i = 0; i < 3; i++){
                        int stat = Integer.parseInt(fileInput.nextLine());
                        swordInfo[i] = stat;
                    }
                    int[] shieldInfo = new int[3];
                    for (int i = 0; i < 3; i++){
                        int stat = Integer.parseInt(fileInput.nextLine());
                        shieldInfo[i] = stat;
                    }
                    int[] armourInfo = new int[3];
                    for (int i = 0; i < 3; i++){
                        int stat = Integer.parseInt(fileInput.nextLine());
                        armourInfo[i] = stat;
                    }
                    player = new Player(name, health, defence, attack, level, xp, coins, materialQuantities, potionQuantities, swordInfo, shieldInfo, armourInfo);
                    System.out.println(bold + "Welcome back " + green + player.getName() + reset + bold + " to Wen Ymar Elad!" + reset);
                }
                fileInput.close();
            }
            else{
                System.out.println(bold + "Welcome to Wen Ymar Elad! What is your name?" + reset);
                String name = userInput.nextLine();
                player = new Player(name);
            }

            try {
                Floor floor;
                fileInput = new Scanner(new File("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\FloorInfo"));
                if (fileInput.hasNextLine()){
                    Floor.floorLevel = Integer.parseInt(fileInput.nextLine());
                    ArrayList<String> enemyNames = new ArrayList<String>();
                    while (fileInput.hasNextLine()){
                        enemyNames.add(fileInput.nextLine());
                    }
                    floor = new Floor(enemyNames);
                    fileInput.close();
                }
                else{
                    Floor.floorLevel = 0;
                    floor = new Floor();
                }
                assert player != null;
                mainMenu();
                putInfoIntoFiles(player, floor);
                int choice = Integer.parseInt(userInput.nextLine());
                boolean exitGame = false;
                boolean exitCurrentPlace = false;

                while (!exitGame){
                    switch (choice) {
                        case 1 -> {
                            // info about enemies and items
                            infoMenu();
                            int action = Integer.parseInt(userInput.nextLine());
                            switch (action){
                                case 1 -> Enemy.enemyInfo();
                                case 2 -> Item.itemInfo();
                            }
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 2 -> {
                            // username stuff
                            System.out.println(bold + "What would you like your new username to be?" + reset);
                            String name = userInput.nextLine();
                            player.changeName(name);
                            putInfoIntoFiles(player, floor);
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 3 -> {
                            // profile stuff
                            player.profile();
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 4 ->{
                            // check inventory stuff
                            player.inventoryMenu();
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 5 -> {
                            // do leveling up stuff
                            player.upgradeItem();
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 6 -> {
                            // access shop
                            System.out.println(bold + "Would you like to buy or sell? " + cyan + "(B/S)" + reset);
                            String action = userInput.nextLine();
                            switch(action) {
                                case "B" -> Shop.purchaseItem(player);
                                case "S" -> {
                                    System.out.println(bold + "Do you want to sell materials or potions? " + cyan + "(M/P)" + reset);
                                    String sellChoice = userInput.nextLine();
                                    switch(sellChoice) {
                                        case "M" -> {
                                            if (player.getMaterials().isEmpty()){
                                                System.out.println("Sorry, you don't have any materials to sell");
                                            }
                                            else{
                                                Shop.sellMaterial(player);
                                            }
                                        }
                                        case "P" -> {
                                            if (player.getInventory().isEmpty()){
                                                System.out.println("Sorry, you don't have any potions to sell");
                                            }
                                            else{
                                                Shop.sellPotion(player);
                                            }
                                        }
                                        default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                                    }
                                }
                                default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                            }
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        case 7 -> {
                            System.out.println(bold + "What dungeon do you want to enter?" + reset);
                            System.out.println(bold + cyan + "1:" + reset + " Enemy dungeon" + reset);
                            System.out.println(bold + cyan + "2:" + reset + " Vampire dungeon" + reset);
                            System.out.println(bold + cyan + "3:" + reset + " Golem dungeon" + reset);
                            int enemyType = Integer.parseInt(userInput.nextLine());
                            System.out.println(bold + "What difficulty do you want to enter?" + reset);
                            System.out.println(bold + cyan + "1: " + reset + bold + "Easy: " + reset + italic + "5 monsters, normal stats" + reset);
                            System.out.println(bold + cyan + "2: " + reset + bold + "Medium: " + reset + italic + "6 monsters, stats are doubled" + reset);
                            System.out.println(bold + cyan + "3: " + reset + bold + "Hard: " + reset + italic + "7 monsters, stats are tripled" + reset);
                            int difficulty = Integer.parseInt(userInput.nextLine());
                            Dungeon dungeon = new Dungeon(enemyType, difficulty);
                            while (!dungeon.getAllEnemiesDead() && !exitGame && !exitCurrentPlace){
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
                                    // check stats
                                    case 2 -> player.stats();
                                    // item stuff
                                    case 3 -> player.inventoryMenu();
                                    case 4 -> {
                                        if (player.getInventory().size() != 0) {
                                            player.usePotion();
                                        }
                                        else {
                                            System.out.println("Sorry, you have no potions to use");
                                        }
                                    }
                                    case 5 -> {
                                        //fight stuff
                                        player.battle(dungeon, floor);
                                        if (!player.getIsDead()){
                                            dungeon.fightUpdate(player);
                                        }
                                        else{
                                            exitCurrentPlace = true;
                                        }
                                    }
                                    case 6 -> {
                                        // save stuff
                                        System.out.println(bold + "The game will save your progress up to the last floor or dungeon you completed. Do you wish to proceed? " + cyan + "(Y/N)" + reset);
                                        String action = userInput.nextLine();
                                        switch (action) {
                                            case "Y" -> exitGame = true;
                                            case "N" -> System.out.println(italic + "Resuming game..." + reset);
                                            default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                                        }
                                    }
                                    default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                                }
                            }
                            if (!exitGame){
                                if (!exitCurrentPlace){
                                    dungeon.dungeonCleared(player);
                                    putInfoIntoFiles(player, floor);
                                }
                                mainMenu();
                                choice = Integer.parseInt(userInput.nextLine());
                            }
                        }
                        case 8 -> {
                            // fight stuff
                            floor.enterLevel();
                            while (!floor.getAllEnemiesDead() && !exitGame && !exitCurrentPlace) {
                                fightMenu();
                                choice = Integer.parseInt(userInput.nextLine());
                                switch (choice) {
                                    case 1 -> {
                                        // info about enemies and items
                                        infoMenu();
                                        int action = Integer.parseInt(userInput.nextLine());
                                        switch (action) {
                                            case 1 -> Enemy.enemyInfo();
                                            case 2 -> Item.itemInfo();
                                        }
                                    }
                                    // check stats
                                    case 2 -> player.stats();
                                    // check inventory
                                    case 3 -> player.inventoryMenu();
                                    case 4 -> {
                                        if (player.getInventory().size() != 0) {
                                            player.usePotion();
                                        } else {
                                            System.out.println("Sorry, you have no potions to use");
                                        }
                                    }
                                    case 5 -> {
                                        // fight stuff
                                        player.battle(floor);
                                        if (!player.getIsDead()){
                                            floor.fightUpdate(player);
                                        }
                                        else{
                                            exitCurrentPlace = true;
                                        }
                                    }
                                    case 6 -> {
                                        // save stuff
                                        System.out.println(bold + "The game will save your progress up to the last floor or dungeon you completed. Do you wish to proceed? " + cyan + "(Y/N)" + reset);
                                        String action = userInput.nextLine();
                                        switch (action) {
                                            case "Y" -> exitGame = true;
                                            case "N" -> System.out.println(italic + "Resuming game..." + reset);
                                            default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                                        }
                                    }
                                    default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                                }
                            }
                            if (!exitGame){
                                if (!exitCurrentPlace){
                                    floor.floorCleared(player);
                                    floor = new Floor();
                                    putInfoIntoFiles(player, floor);
                                }
                                mainMenu();
                                choice = Integer.parseInt(userInput.nextLine());
                            }
                        }
                        case 9 -> exitGame = true;
                        default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                    }
                }
                // save info into files and stuff
                //putInfoIntoFiles(player, floor);

            } catch (FileNotFoundException e) {
                System.out.println("can't open floor file");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't open player file");
        }
    }

    public static void putInfoIntoFiles(Player player, Floor floor) {
        PrintWriter floorOutput;
        PrintWriter playerOutput;
        try {
            floorOutput = new PrintWriter("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\FloorInfo");
            floorOutput.println(Floor.floorLevel);
            if (floor != null){
                for (Enemy enemy : floor.getEnemies()){
                    floorOutput.println(enemy.getName());
                }
            }
            floorOutput.close();
            playerOutput = new PrintWriter("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\PlayerInfo");
            playerOutput.println(player.getName());
            playerOutput.println(player.getHealth());
            playerOutput.println(player.getDefence());
            playerOutput.println(player.getAttack());
            playerOutput.println(player.getLevel());
            playerOutput.println(player.getXP());
            playerOutput.println(player.getCoins());
            if (player.getMaterials().containsKey(Item.materialDrops[0])){
                playerOutput.println(player.getMaterials().get(Item.materialDrops[0]));
            }
            else{
                playerOutput.println(0);
            }
            if (player.getMaterials().containsKey(Item.materialDrops[1])){
                playerOutput.println(player.getMaterials().get(Item.materialDrops[1]));
            }
            else{
                playerOutput.println(0);
            }
            if (player.getMaterials().containsKey(Item.materialDrops[2])){
                playerOutput.println(player.getMaterials().get(Item.materialDrops[2]));
            }
            else{
                playerOutput.println(0);
            }
            if (player.getMaterials().containsKey(Item.weaponDrops[0])){
                playerOutput.println(player.getMaterials().get(Item.weaponDrops[0]));
            }
            else{
                playerOutput.println(0);
            }
            if (player.getMaterials().containsKey(Item.weaponDrops[1])){
                playerOutput.println(player.getMaterials().get(Item.weaponDrops[1]));
            }
            else{
                playerOutput.println(0);
            }
            if (player.getMaterials().containsKey(Item.weaponDrops[2])){
                playerOutput.println(player.getMaterials().get(Item.weaponDrops[2]));
            }
            else{
                playerOutput.println(0);
            }
            if (player.getInventory().containsKey(Item.potions[0])){
                playerOutput.println(player.getInventory().get(Item.potions[0]));
            }
            else{
                playerOutput.println(0);
            }
            if (player.getInventory().containsKey(Item.potions[1])){
                playerOutput.println(player.getInventory().get(Item.potions[1]));
            }
            else{
                playerOutput.println(0);
            }
            if (player.getInventory().containsKey(Item.potions[2])){
                playerOutput.println(player.getInventory().get(Item.potions[2]));
            }
            else{
                playerOutput.println(0);
            }
            playerOutput.println(player.getEquipped()[0].getHealth());
            playerOutput.println(player.getEquipped()[0].getDefence());
            playerOutput.println(player.getEquipped()[0].getAttack());
            playerOutput.println(player.getEquipped()[1].getHealth());
            playerOutput.println(player.getEquipped()[1].getDefence());
            playerOutput.println(player.getEquipped()[1].getAttack());
            playerOutput.println(player.getEquipped()[2].getHealth());
            playerOutput.println(player.getEquipped()[2].getDefence());
            playerOutput.println(player.getEquipped()[2].getAttack());
            playerOutput.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't write to floor level");
        }
    }
}
