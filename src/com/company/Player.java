package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Player {
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    public static final String bold = "\u001B[1m";
    public static final String italic = "\033[3m";
    public static final String reset = "\u001B[0m";
    private String name;
    private int health;
    private int defence;
    private int attack;
    private int level;
    private int xp;
    private int coins;
    private boolean isDead;
    public Item[] equipped = {
            new Item("Sword", 0, 0, 10, 10, """
                    The sword is a sturdy and reliable weapon for any warrior
                    +10 atk"""),
            new Item("Shield", 5, 0, 0, 10, """
                    The shield is an essential for any warrior to keep themselves safe and protect what they need to protect
                    +5 hp, +10 def"""),
            new Item("Armour", 0, 20, 0, 30, """
                    Proper armour keeps your vitals safe
                    +10 hp, +20 def, +5 atk""")
    };
    LinkedHashMap<Item, Integer> materials = new LinkedHashMap<Item, Integer>();
    LinkedHashMap<Potion, Integer> inventory = new LinkedHashMap<Potion, Integer>();
    ArrayList<Potion> potionsInUse = new ArrayList<Potion>();

    // constructor for creating a new player
    public Player(String name){
        this.name = name;
        health = 95;
        health += equipped[1].getHealth();
        defence = 0;
        defence += equipped[2].getDefence();
        attack = 10;
        attack += equipped[0].getAttack();
        level = 1;
        xp = 0;
        coins = 0;
        isDead = false;
    }

    // constructor for recreating player from save file
    public Player(String name, int health, int defence, int attack, int level, int xp, int coins, int[] materialQuantities, int[] potionQuantities, int[] swordInfo, int[] shieldInfo, int[] armourInfo){
        this.name = name;
        this.health = health;
        this.defence = defence;
        this.attack = attack;
        this.level = level;
        this.xp = xp;
        this.coins = coins;
        loadMaterialInfo(materialQuantities);
        loadPotionInfo(potionQuantities);
        loadWeaponInfo(swordInfo, shieldInfo, armourInfo);
        isDead = false;
    }

    // updates equipped weapon stats from save file information
    private void loadWeaponInfo(int[] swordInfo, int[] shieldInfo, int[] armourInfo) {
        equipped[0].setHealth(swordInfo[0]);
        equipped[0].setDefence(swordInfo[1]);
        equipped[0].setAttack(swordInfo[2]);
        equipped[1].setHealth(shieldInfo[0]);
        equipped[1].setDefence(shieldInfo[1]);
        equipped[1].setAttack(shieldInfo[2]);
        equipped[2].setHealth(armourInfo[0]);
        equipped[2].setDefence(armourInfo[1]);
        equipped[2].setAttack(armourInfo[2]);
    }

    // updates inventory with potions that player had according to save file information
    private void loadPotionInfo(int[] potionQuantities) {
        if (potionQuantities[0] != 0){
            inventory.put(new Potion("Health potion", 50, 0, 0, 50, """
                    Health potion heals you by 50 hp points
                    Costs 50 coins"""), potionQuantities[0]);
        }
        if (potionQuantities[1] != 0){
            inventory.put(new Potion("Attack potion", 0, 0, 2, 50, """
                    Attack potion doubles your attack
                    Lasts for one floor/dungeon
                    Costs 50 coins"""), potionQuantities[1]);
        }
        if (potionQuantities[2] != 0){
            inventory.put(new Potion("Defence potion", 0, 2, 0, 50, """
                    Defence potion doubles your defence
                    Lasts for one floor/dungeon
                    Costs 50 coins"""), potionQuantities[2]);
        }
    }

    // updates inventory with materials that player had according to save file information
    private void loadMaterialInfo(int[] materialQuantities) {
        if (materialQuantities[0] != 0){
            materials.put(Item.materialDrops[0], materialQuantities[0]);
        }
        if (materialQuantities[1] != 0){
            materials.put(Item.materialDrops[1], materialQuantities[1]);
        }
        if (materialQuantities[2] != 0){
            materials.put(Item.materialDrops[2], materialQuantities[2]);
        }
        if (materialQuantities[3] != 0){
            materials.put(Item.weaponDrops[0], materialQuantities[3]);
        }
        if (materialQuantities[4] != 0){
            materials.put(Item.weaponDrops[1], materialQuantities[4]);
        }
        if (materialQuantities[5] != 0){
            materials.put(Item.weaponDrops[2], materialQuantities[5]);
        }
    }

    // get player username
    public String getName() {
        return name;
    }

    // change player username
    public void changeName(String name){
        this.name = name;
        System.out.println("Successfully changed username to " + green + name + reset + "!");
    }

    // get player health
    public int getHealth(){
        return health;
    }

    // set player health
    public void setHealth(int health){
        this.health = health;
    }

    // get player defence
    public int getDefence() {
        return defence;
    }

    // get player attack
    public int getAttack(){
        return attack;
    }

    // get player level
    public int getLevel() {
        return level;
    }

    // get player xp
    public int getXP() {
        return xp;
    }

    public int getCoins(){
        return coins;
    }

    public void useCoins(int used){
        coins -= used;
    }

    // get whether player is dead or not
    public boolean getIsDead(){
        return isDead;
    }

    // get player equipped items
    public Item[] getEquipped() {
        return equipped;
    }

    // get player materials inventory
    public LinkedHashMap<Item, Integer> getMaterials(){
        return materials;
    }

    // get player inventory
    public LinkedHashMap<Potion, Integer> getInventory(){
        return inventory;
    }

    // get the amount of enemy material player has
    public int getEnemyMaterials(){
        if (materials.get(Item.materialDrops[0]) != null){
            return materials.get(Item.materialDrops[0]);
        }
        else{
            return 0;
        }
    }

    // get the amount of vampire material player has
    public int getVampireMaterials(){
        if (materials.get(Item.materialDrops[1]) != null){
            return materials.get(Item.materialDrops[1]);
        }
        else{
            return 0;
        }
    }

    // get the amount of golem material player has
    public int getGolemMaterials(){
        if (materials.get(Item.materialDrops[2]) != null){
            return materials.get(Item.materialDrops[2]);
        }
        else{
            return 0;
        }
    }

    // get the amount of swords player has
    public int getSwords(){
        if (materials.get(Item.weaponDrops[0]) != null){
            return materials.get(Item.weaponDrops[0]);
        }
        else{
            return 0;
        }
    }

    // get the amount of shields player has
    public int getShields(){
        if (materials.get(Item.weaponDrops[1]) != null){
            return materials.get(Item.weaponDrops[1]);
        }
        else{
            return 0;
        }
    }

    // get the amount of armours player has
    public int getArmours(){
        if (materials.get(Item.weaponDrops[2]) != null){
            return materials.get(Item.weaponDrops[2]);
        }
        else{
            return 0;
        }
    }

    // show player profile
    public void profile(){
        System.out.println();
        System.out.println(green + bold + "Username: " + reset + green + name + reset);
        System.out.println(yellow + bold + "Level: " + reset + yellow + level + reset);
        System.out.println(yellow + bold + "XP: " + reset + yellow + xp + "/" + level*10 + reset);
        System.out.println(yellow + bold + "Coins: " + reset + yellow + coins + reset);
        System.out.println(red + bold + "Health: " + reset + red + health + reset);
        System.out.println(blue + bold + "Defence: " + reset + blue + defence + reset);
        System.out.println(purple + bold + "Attack: " + reset + purple + attack + reset);
    }

    // show player stats
    public void stats(){
        System.out.println();
        System.out.println(red + bold + "Health: " + reset + red + health + reset);
        System.out.println(blue + bold + "Defence: " + reset + blue + defence + reset);
        System.out.println(purple + bold + "Attack: " + reset + purple + attack + reset);
    }

    // show player inventory
    public void inventoryMenu(){
        System.out.println();
        System.out.println(bold + "Equipped" + reset);
        for (Item item : equipped){
            System.out.println(item);
        }
        System.out.println();
        System.out.println(bold + "Materials" + reset);
        if (materials.size() == 0){
            System.out.println(italic + "You have nothing yet..." + reset);
        }
        else{
            for (Map.Entry<Item, Integer> entry : materials.entrySet()) {
                Item key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(bold + key.getName() + ": " + reset + value);
            }
        }
        System.out.println();
        System.out.println(bold + "Inventory" + reset);
        if (inventory.size() == 0){
            System.out.println(italic + "You have nothing yet..." + reset);
        }
        else{
            for (Map.Entry<Potion, Integer> entry : inventory.entrySet()) {
                Potion key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(bold + key.getName() + ": " + reset + italic + value + reset);
            }
        }
    }

//    public void inventoryMenu(){
//        System.out.println();
//        System.out.println("Equipped: " + equipped);
//        System.out.println("Inventory: " + materials);
//        Scanner input = new Scanner(System.in);
//        System.out.println("Do you want to equip or unequip? (E/U)");
//        String choice = input.nextLine();
//        if (choice.equals("E")){
//            if (materials.size() == 0){
//                System.out.println("You don't have any items to equip");
//            }
//            else{
//                System.out.println("Input the index of the item you want to equip (1-" + materials.size() + ")");
//                int index = Integer.parseInt(input.nextLine());
//                equipItem(materials.get(index-1));
//            }
//        }
//        else{
//            if (equipped.size() == 0){
//                System.out.println("You don't have any items to unequip");
//            }
//            else{
//                System.out.println("Input the index of the item you want to unequip (1-" + equipped.size() + ")");
//                int index = Integer.parseInt(input.nextLine());
//                unequipItem(equipped.get(index-1));
//            }
//        }
//    }

    // these functions are all used for resetting player if player chooses to restart from the very beginning after dying
    private void setName(String name) {
        this.name = name;
    }
    public void setDefence(int defence){
        this.defence = defence;
    }
    public void setAttack(int attack){
        this.attack = attack;
    }
    private void setLevel(int level) {
        this.level = level;
    }
    private void setXP(int xp) {
        this.xp = xp;
    }
    private void setCoins(int coins) {
        this.coins = coins;
    }
    private void clearInventory() {
        inventory.clear();
    }
    private void clearMaterials() {
        materials.clear();
    }

    // update coins after selling an item
    public void soldItem(int profit) {
        coins += profit;
    }

    // upgrade an item
    public void upgradeItem(){
        Scanner input = new Scanner(System.in);
        System.out.println();
        // upgrade item according to player selection
        System.out.println(bold + "What do you want to level up?" + reset);
        System.out.println(bold + cyan + "1: " + reset + bold + "Sword " + reset + italic + "(uses vampire materials or swords)" + reset);
        System.out.println(bold + cyan + "2: " + reset + bold + "Shield " + reset + italic + "(uses enemy materials or shields)" + reset);
        System.out.println(bold + cyan + "3: " + reset + bold + "Armour " + reset + italic + "(uses golem materials or armours)" + reset);
        int itemChoice = Integer.parseInt(input.nextLine());
        while (itemChoice != 1 && itemChoice != 2 && itemChoice != 3){
            System.out.println("Sorry, that is not a recognized item. Please try again.");
            itemChoice = Integer.parseInt(input.nextLine());
        }
        switch (itemChoice){
            case 1 -> equipped[0].upgradeItem(this);
            case 2 -> equipped[1].upgradeItem(this);
            case 3 -> equipped[2].upgradeItem(this);
        }
    }

    // update materials inventory accordingly after upgrading item
    public void useVampireMaterial(int used) {
        materials.replace(Item.materialDrops[1], materials.get(Item.materialDrops[1])-used);
        if (materials.get(Item.materialDrops[1]) == 0){
            materials.remove(Item.materialDrops[1]);
        }
    }
    public void useSword(int used) {
        materials.replace(Item.weaponDrops[0], materials.get(Item.weaponDrops[0])-used);
        if (materials.get(Item.weaponDrops[0]) == 0){
            materials.remove(Item.weaponDrops[0]);
        }
    }
    public void useEnemyMaterial(int used) {
        materials.replace(Item.materialDrops[0], materials.get(Item.materialDrops[0])-used);
        if (materials.get(Item.materialDrops[0]) == 0){
            materials.remove(Item.materialDrops[0]);
        }
    }
    public void useArmour(int used) {
        materials.replace(Item.weaponDrops[2], materials.get(Item.weaponDrops[2])-used);
        if (materials.get(Item.weaponDrops[2]) == 0){
            materials.remove(Item.weaponDrops[2]);
        }
    }
    public void useGolemMaterial(int used) {
        materials.replace(Item.materialDrops[2], materials.get(Item.materialDrops[2])-used);
        if (materials.get(Item.materialDrops[2]) == 0){
            materials.remove(Item.materialDrops[2]);
        }
    }
    public void useShield(int used) {
        materials.replace(Item.weaponDrops[1], materials.get(Item.weaponDrops[1])-used);
        if (materials.get(Item.weaponDrops[1]) == 0){
            materials.remove(Item.weaponDrops[1]);
        }
    }

    // updates player's stats after leveling up items
    public void setEquipped(Player player, int index, int health, int defence, int attack){
        equipped[index].setHealth(equipped[index].getHealth()+health);
        equipped[index].setDefence(equipped[index].getDefence()+defence);
        equipped[index].setAttack(equipped[index].getAttack()+attack);
        player.setHealth(player.getHealth()+health);
        player.setDefence(player.getDefence()+defence);
        player.setAttack(player.getAttack()+attack);
    }

    // drink a health potion
    // increases health by the potion amount
    public void drinkHealthPotion(){
        if (inventory.get(Item.potions[0]) == null){
            System.out.println("Sorry, you don't own any health potions.");
        }
        else{
            System.out.println("Drank health potion!");
            health += Item.potions[0].getHealth();
            inventory.replace(Item.potions[0], inventory.get(Item.potions[0])-1);
            if (inventory.get(Item.potions[0]) == 0){
                inventory.remove(Item.potions[0]);
            }
            profile();
        }
    }

    // use a potion during floor/dungeon
    public void usePotion(){
        Scanner input = new Scanner(System.in);
        // print out all avaliable potions to use
        System.out.println(bold + "What potion would you like to use?" + reset);
        int i = 1;
        for (Potion key : inventory.keySet()) {
            System.out.println(bold + cyan + i + ": " + reset + bold + key.getName() + ": " + reset + italic + inventory.get(key) + reset);
            i++;
        }
        // get what potion player selected
        Set<Potion> keySet = inventory.keySet();
        Potion[] keyArray = keySet.toArray(new Potion[keySet.size()]);
        int index = Integer.parseInt(input.nextLine());
        Potion potion = keyArray[index-1];
        // update inventory accordingly
        switch (potion.getName()){
            case "Health potion" -> {
                health += potion.getHealth();
                System.out.println(inventory.get(potion));
                inventory.replace(potion, inventory.get(potion)-1);
                if (inventory.get(potion) == 0){
                    inventory.remove(potion);
                }
                System.out.println("Used health potion");
            }
            case "Attack potion" -> {
                attack += potion.getAttack();
                potionsInUse.add(potion);
                inventory.replace(potion, inventory.get(potion)-1);
                if (inventory.get(potion) == 0){
                    inventory.remove(potion);
                }
                System.out.println("Used attack potion");
            }
            case "Defence potion" -> {
                defence += potion.getDefence();
                potionsInUse.add(potion);
                inventory.replace(potion, inventory.get(potion)-1);
                if (inventory.get(potion) == 0){
                    inventory.remove(potion);
                }
                System.out.println("Used defence potion");
            }
        }
    }

    // get all the potions that are in use (all buffs that are currently being applied)
    public ArrayList<Potion> getPotionsInUse(){
        return potionsInUse;
    }

    // clear all buffs from potions (after completing the floor/dungeon)
    public void clearPotionsInUse(){
        potionsInUse.clear();
    }

    // remove an item from inventory (troll uses it to steal a potion from player)
    public void removeItem(Potion potion){
        inventory.replace(potion, inventory.get(potion)-1);
        if (inventory.get(potion) == 0){
            inventory.remove(potion);
        }
    }

//    public void equipItem(Item item){
//        equipped.add(item);
//        health += item.getHealth();
//        defence += item.getDefence();
//        attack += item.getAttack();
//        materials.remove(item);
//    }

//    public void unequipItem(Item item){
//        equipped.remove(item);
//        health -= item.getHealth();
//        defence -= item.getDefence();
//        attack -= item.getAttack();
//        materials.add(item);
//    }

//    public void sellItem(Item item){
//        System.out.println("Successfully sold " + item.getName() + "!");
//        coins += item.getCost();
//        materials.remove(item);
//    }

    // called when xp reaches the required amount to level uo
    // increases stats
    public void levelUp(){
        System.out.println(yellow + "Level up!" + reset);
        xp = xp - level*10;
        level += 1;
        health += 10;
        attack += 5;
        defence += 5;
        coins += 20;
    }

    // defeat a monster that drops an item
    public void defeatedMonster(Item item){
        System.out.println(yellow + "Gained " + (10+Floor.floorLevel) + " xp!" + reset);
        xp += 10+Floor.floorLevel;
        if (xp >= level*10){
            levelUp();
        }
        materials.merge(item, 1, Integer::sum);
        coins += 10+Floor.floorLevel;
    }

    // defeat a monster that doesn't drop an item
    public void defeatedMonster(){
        System.out.println(yellow + "Gained " + (10+Floor.floorLevel) + " xp!" + reset);
        xp += 10+Floor.floorLevel;
        if (xp >= level*10){
            levelUp();
        }
        coins += 10+Floor.floorLevel;
    }

    // floor battle function
    public void battle(Floor floor) {
        // print out what enemies did on their turn
        System.out.println();
        System.out.println(bold + "Enemy turn" + reset);
        ArrayList<Enemy> enemies = floor.getEnemies();
        for (Enemy enemy : enemies){
            // takes player defence into account when calculating damage taken
            int damage = enemy.getAttack()*(100/defence);
            health -= damage;
            System.out.println(enemy.getName() + " has dealt " + purple + damage + " damage" + reset);
        }
        // check if player has died
        if (health <= 0){
            died(this, floor);
        }
        // if player is still alive, then do player's turn
        else{
            System.out.println();
            System.out.println(bold + "Your turn" + reset);
            // battle each enemy
            for (Enemy enemy : enemies){
                enemy.battle(this, floor);
            }
            System.out.println();
            // update the enemies on the floor (remove dead enemies)
            floor.updateEnemies();
        }
    }

    // dungeon battle function
    public void battle(Dungeon dungeon, Floor floor) {
        // print out what enemies did on their turn
        System.out.println();
        System.out.println(bold + "Enemy turn" + reset);
        ArrayList<Enemy> enemies = dungeon.getEnemies();
        for (Enemy enemy : enemies){
            // takes player defence into account when calculating damage taken
            int damage = enemy.getAttack()*(100/defence);
            health -= damage;
            System.out.println(enemy.getName() + " has dealt " + purple + damage + " damage" + reset);
        }
        // check if player has died
        if (health <= 0){
            died(this, floor);
        }
        // if player is still alive, then do player's turn
        else{
            System.out.println();
            System.out.println(bold + "Your turn" + reset);
            // battle each enemy
            for (Enemy enemy : enemies){
                enemy.battle(this, dungeon);
            }
            System.out.println();
            // update the enemies on the dungeon (remove dead enemies)
            dungeon.updateEnemies();
        }
    }

    // function for when player dies
    public void died(Player player, Floor floor) {
        isDead = true;
        Scanner input = new Scanner(System.in);
        System.out.println();
        // ask if player wants to restart from previous save, or restart from the beginning
        System.out.println(bold + "You have died! Restart from previous save or reset everything from the beginning? " + cyan + "(S/R)" + reset);
        String choice = input.nextLine();
        // while the player inputs an invalid choice, keep prompting
        while (!(choice.equals("S") || choice.equals("R"))){
            System.out.println("Sorry, that is not a recognized command. Please try again.");
            choice = input.nextLine();
        }
        // restart from previous save
        if (choice.equals("S")) {
            try {
                Scanner fileInput = new Scanner(new File("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\PlayerInfo"));
                if (fileInput.hasNextLine()) {
                    while (fileInput.hasNextLine()) {
                        // reset player information to previous save (e.g. stats, inventory, etc)
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
                        player.setName(name);
                        player.setHealth(health);
                        player.setDefence(defence);
                        player.setAttack(attack);
                        player.setLevel(level);
                        player.setXP(xp);
                        player.setCoins(coins);
                        player.loadMaterialInfo(materialQuantities);
                        player.loadPotionInfo(potionQuantities);
                        player.loadWeaponInfo(swordInfo, shieldInfo, armourInfo);
                    }
                }
                try {
                    // reset floor to previous floor (floor level, floor enemies)
                    fileInput = new Scanner(new File("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\FloorInfo"));
                    if (fileInput.hasNextLine()) {
                        Floor.floorLevel = Integer.parseInt(fileInput.nextLine());
                        ArrayList<String> enemyNames = new ArrayList<String>();
                        while (fileInput.hasNextLine()){
                            enemyNames.add(fileInput.nextLine());
                        }
                        floor.setFloorEnemies(enemyNames);
                        fileInput.close();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Couldn't open floor file");
                }

            } catch (FileNotFoundException e) {
                System.out.println("Couldn't open player file");
            }
        }
        // reset from very beginning
        else{
            // reset player to base stats, empty inventory, start from the first floor again
            // also SHOW LORE AGAIN
            Floor.floorLevel = 0;
            floor = new Floor();
            Main.lore();
            System.out.println(bold + "Welcome to Wen Ymar Elad! What is your name?" + reset);
            String name = input.nextLine();
            player.setName(name);
            player.setHealth(100);
            player.setDefence(20);
            player.setAttack(20);
            player.setLevel(1);
            player.setXP(0);
            player.setCoins(0);
            int[] materialQuantities = {0, 0, 0, 0, 0, 0};
            player.loadMaterialInfo(materialQuantities);
            int[] potionQuantities = {0, 0, 0};
            player.loadPotionInfo(potionQuantities);
            int[] swordInfo = {0, 0, 10};
            int[] shieldInfo = {5, 0, 0};
            int[] armourInfo = {0, 20, 0};
            player.loadWeaponInfo(swordInfo, shieldInfo, armourInfo);
            player.clearMaterials();
            player.clearInventory();
            Main.putInfoIntoFiles(player, floor);
        }
    }
}