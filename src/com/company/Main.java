package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static final String reset = "\u001B[0m";
    public static final String green = "\u001B[32m";
    public static final String cyan = "\u001B[36m";
    public static final String bold = "\u001B[1m";
    public static final String italic = "\033[3m";

    // THE LOREEE OF THE GAME
    public static void lore(){
        System.out.println(italic + """
                8,035,999 years ago...
                Wake up warrior, you must help *** **** **** by saving Wen Ymar Elad.
                
                Your head is pounding as you slowly open your eyes.
                
                All around you is a wasteland. The fields are all dead, the grass long since wilted, the water's been polluted into a pure black colour, and there's not a single living thing in sight.
                The only thing that catches your eye is a dark, menacing tower on top of a hill.
                Your head pounds in pain. Who are you? Where are you? How did you get here? Why are you here?
                There's so many questions running through your head, but only one of them has an answer.
                You feel it deep in your soul, you were sent here with a purpose. What it was, you can't quite remember.
                However, beside you is a sword, shield, and armour. You don't know why, but you feel a strong sense to protect this place, to save this place.
                With nothing else to do, you pick up the items beside you and start walking to the tower.
                
                As you walk closer and closer, you start to hear the screeches and screams of monsters inside.
                The sense of urgency inside you grows stronger, and you realize what you must have been sent here for: to defeat all the monsters and save this place.
                Why? Why do you feel this way? Perhaps you will find the answers you seek as you climb the tower.
                Good luck brave warrior, and please help us.
                """ + reset);
    }

    // how to play the game + how the game works
    public static void gameInfo(){
        System.out.println("""
                Your character is equipped with a sword, shield, and armour.
                To increase your stats, you can fight monsters by either entering dungeons or floors.
                You gain a certain amount of XP when you defeat a monster based on the monster's difficulty
                You can also increase your stats by leveling up your equipped items!
                
                As you defeat monsters, not only do they drop XP, they also drop coins and random materials.
                Monsters usually drop materials, but they have a 10% chance of dropping an item (sword, shield, armour).
                You use these coins and materials/items to increase the stats of your equipped items.
                Each item needs a certain type of material to be upgraded, which is where the dungeons come in handy.
                You can choose to enter specific dungeons (and choose the level of difficulty) to farm the materials you need!
                
                When upgrading items, you can choose to use materials or other items of the same kind.
                The difference between the two is that using materials to upgrade will only upgrade the main stat of that item,
                but using other items of the same kind will upgrade all stats!
                
                The shop sells potions that you can use to buff your character in order to defeat difficult floors.
                
                The goal of the game is to beat as many floors as you can.
                Each floor, the difficulty and number of monsters is ramped higher, so make sure you prepare well beforehand!""");
    }

    // main menu options
    public static void mainMenu(){
        System.out.println();
        System.out.println(bold + "What would you like to do?" + reset);
        System.out.println("========================= ©MM");
        System.out.println(italic + cyan + "1:" + reset + italic + " open info menu");
        System.out.println(cyan + "2:" + reset + italic + " change username");
        System.out.println(cyan + "3:" + reset + italic + " check profile");
        System.out.println(cyan + "4:" + reset + italic + " check inventory");
        System.out.println(cyan + "5:" + reset + italic + " drink health potion");
        System.out.println(cyan + "6:" + reset + italic + " level up items" + reset);
        System.out.println(cyan + "7:" + reset + italic + " access shop");
        System.out.println(cyan + "8:" + reset + italic + " enter a dungeon" + reset);
        System.out.println(cyan + "9:" + reset + italic + " enter floor " + Floor.floorLevel);
        System.out.println(cyan + "10:" + reset + italic + " save and exit" + reset);
        System.out.println("========================= ©MM");
    }

    // menu options when in a dungeon or floor
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

    // info menu
    public static void infoMenu(){
        System.out.println();
        System.out.println(bold + "Info Menu" + reset);
        System.out.println("=============== ©MM");
        System.out.println(italic + cyan + "1:" + reset + italic + " game info");
        System.out.println(cyan + "2:" + reset + italic + " enemy info" + reset);
        System.out.println(cyan + "3:" + reset + italic + " item info" + reset);
        System.out.println("=============== ©MM");
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Player player = null;
        try {
            // if this isn't a new player (has info in files), get all that info back and put it into their player
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
                    System.out.println(bold + "Wen Ymar Elad welcomes you back " + green + player.getName() + reset + bold + "!" + reset);
                }
                fileInput.close();
            }
            // if it's a new player, show them the LOREEE and prompt them to create a username
            else{
                lore();
                System.out.println(bold + "Welcome to Wen Ymar Elad! What is your name?" + reset);
                String name = userInput.nextLine();
                player = new Player(name);
            }

            // if this isn't a new user, they'll have a floor saved from the last time they played
            // recreate that floor with the enemies it had previously
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
                // if this is the first time they're entering a floor, create a new floor
                else{
                    Floor.floorLevel = 0;
                    floor = new Floor();
                }

                // setup for the game
                // show main menu and prompt a choice, write their info into files, then set variables that will of use later
                assert player != null;
                mainMenu();
                putInfoIntoFiles(player, floor);
                int choice = Integer.parseInt(userInput.nextLine());
                boolean exitGame = false;
                boolean exitCurrentPlace = false;

                // while the user doesn't want to exit, keep running the game
                while (!exitGame){
                    switch (choice) {
                        // show info menu for game, enemies, items
                        case 1 -> {
                            infoMenu();
                            int action = Integer.parseInt(userInput.nextLine());
                            switch (action){
                                case 1 -> gameInfo();
                                case 2 -> Enemy.enemyInfo();
                                case 3 -> Item.itemInfo();
                            }
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        // change username and update it into the files
                        case 2 -> {
                            System.out.println(bold + "What would you like your new username to be?" + reset);
                            String name = userInput.nextLine();
                            player.changeName(name);
                            putInfoIntoFiles(player, floor);
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        // show player profile
                        case 3 -> {
                            player.profile();
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        // show inventory
                        case 4 -> {
                            player.inventoryMenu();
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        // drink health potion
                        case 5 -> {
                            player.drinkHealthPotion();
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        // level up items
                        case 6 -> {
                            player.upgradeItem();
                            mainMenu();
                            choice = Integer.parseInt(userInput.nextLine());
                        }
                        // access shop
                        case 7 -> {
                            // prompt player to buy or sell
                            System.out.println(bold + "Would you like to buy or sell? " + cyan + "(B/S)" + reset);
                            String action = userInput.nextLine();
                            switch(action) {
                                // if player wants to buy something, call the method for it
                                case "B" -> Shop.purchaseItem(player);
                                // find out what user wants to sell
                                // update coins and inventory, or give warning messages accordingly
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
                        // enter a dungeon
                        case 8 -> {
                            // ask player to specify what dungeon they want to enter
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
                            // create the dungeon
                            Dungeon dungeon = new Dungeon(enemyType, difficulty);
                            // keep prompting the player to fight as long as all enemies aren't dead, they aren't dead, and they didn't save and exit
                            exitCurrentPlace = false;
                            while (!dungeon.getAllEnemiesDead() && !exitGame && !exitCurrentPlace){
                                // show the fight menu instead of the main menu
                                fightMenu();
                                choice = Integer.parseInt(userInput.nextLine());
                                switch (choice) {
                                    // info about enemies and items
                                    case 1 -> {
                                        infoMenu();
                                        int action = Integer.parseInt(userInput.nextLine());
                                        switch (action){
                                            case 1 -> gameInfo();
                                            case 2 -> Enemy.enemyInfo();
                                            case 3 -> Item.itemInfo();
                                        }
                                    }
                                    // check stats
                                    case 2 -> player.stats();
                                    // check inventory
                                    case 3 -> player.inventoryMenu();
                                    // use potions
                                    case 4 -> {
                                        if (player.getInventory().size() != 0) {
                                            player.usePotion();
                                        }
                                        else {
                                            System.out.println("Sorry, you have no potions to use");
                                        }
                                    }
                                    // fight
                                    case 5 -> {
                                        player.battle(dungeon, floor);
                                        // while the player isn't dead, show updates in the battle result
                                        // otherwise, exit the dungeon
                                        if (!player.getIsDead()){
                                            dungeon.fightUpdate(player);
                                        }
                                        else{
                                            exitCurrentPlace = true;
                                        }
                                    }
                                    // save and exit
                                    case 6 -> {
                                        System.out.println(bold + "The game will save your progress up to the last floor or dungeon you completed. Do you wish to proceed? " + cyan + "(Y/N)" + reset);
                                        String action = userInput.nextLine();
                                        switch (action) {
                                            case "Y" -> exitGame = true;
                                            case "N" -> System.out.println(italic + "Resuming game..." + reset);
                                            default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                                        }
                                    }
                                    // warning message for unknown command
                                    default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                                }
                            }
                            // if game has exited the dungeon and the player doesn't want to exit the game and the player isn't dead,
                            // that means the dungeon was successfully cleared
                            // put the player's info into files (save after every floor/dungeon clear)
                            if (!exitGame){
                                if (!exitCurrentPlace){
                                    dungeon.dungeonCleared(player);
                                    putInfoIntoFiles(player, floor);
                                }
                                mainMenu();
                                choice = Integer.parseInt(userInput.nextLine());
                            }
                        }
                        // enter floors
                        case 9 -> {
                            floor.enterLevel();
                            // keep fighting while there's still enemies, the player hasn't exited the game, and the player hasn't died
                            exitCurrentPlace = false;
                            while (!floor.getAllEnemiesDead() && !exitGame && !exitCurrentPlace) {
                                fightMenu();
                                choice = Integer.parseInt(userInput.nextLine());
                                switch (choice) {
                                    // info about enemies and items
                                    case 1 -> {
                                        infoMenu();
                                        int action = Integer.parseInt(userInput.nextLine());
                                        switch (action) {
                                            case 1 -> gameInfo();
                                            case 2 -> Enemy.enemyInfo();
                                            case 3 -> Item.itemInfo();
                                        }
                                    }
                                    // check stats
                                    case 2 -> player.stats();
                                    // check inventory
                                    case 3 -> player.inventoryMenu();
                                    // use potions
                                    case 4 -> {
                                        if (player.getInventory().size() != 0) {
                                            player.usePotion();
                                        } else {
                                            System.out.println("Sorry, you have no potions to use");
                                        }
                                    }
                                    // fight
                                    case 5 -> {
                                        player.battle(floor);
                                        if (!player.getIsDead()){
                                            floor.fightUpdate(player);
                                        }
                                        else{
                                            exitCurrentPlace = true;
                                        }
                                    }
                                    // save and exit
                                    case 6 -> {
                                        System.out.println(bold + "The game will save your progress up to the last floor or dungeon you completed. Do you wish to proceed? " + cyan + "(Y/N)" + reset);
                                        String action = userInput.nextLine();
                                        switch (action) {
                                            case "Y" -> exitGame = true;
                                            case "N" -> System.out.println(italic + "Resuming game..." + reset);
                                            default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                                        }
                                    }
                                    // warning message for unknown command
                                    default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                                }
                            }
                            // if game has exited the floor and the player doesn't want to exit the game and the player isn't dead,
                            // that means the floor was successfully cleared
                            // put the player's info into files (save after every floor/dungeon clear)
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
                        // save and exit
                        case 10 -> {
                            System.out.println(bold + "The game will save your progress up to the last floor or dungeon you completed. Do you wish to proceed? " + cyan + "(Y/N)" + reset);
                            String action = userInput.nextLine();
                            switch (action) {
                                case "Y" -> exitGame = true;
                                case "N" -> System.out.println(italic + "Resuming game..." + reset);
                                default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                            }
                        }
                        // warning message for unknown command
                        default -> System.out.println("Sorry, that is not a recognized command. Please try again.");
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("can't open floor file");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't open player file");
        }
    }

    // method to write player and floor information into files
    public static void putInfoIntoFiles(Player player, Floor floor) {
        PrintWriter floorOutput;
        PrintWriter playerOutput;
        try {
            // if a floor exists, put it into the floor info file
            floorOutput = new PrintWriter("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\FloorInfo");
            floorOutput.println(Floor.floorLevel);
            if (floor != null){
                for (Enemy enemy : floor.getEnemies()){
                    floorOutput.println(enemy.getName());
                }
            }
            floorOutput.close();
            // put player's information into the player info file
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
