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

    public Player(String name){
        this.name = name;
        health = 100;
        defence = 0;
        attack = 10;
        level = 1;
        xp = 0;
        coins = 0;
    }

    public Player(String name, int health, int defence, int attack, int level, int xp, int coins, int[] materialQuantities, int[] potionQuantities){
        this.name = name;
        this.health = health;
        this.defence = defence;
        this.attack = attack;
        this.level = level;
        this.xp = xp;
        this.coins = coins;
        loadMaterialInfo(materialQuantities);
        loadPotionInfo(potionQuantities);
    }

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

    public LinkedHashMap<Potion, Integer> getInventory(){
        return inventory;
    }

    public int getEnemyMaterials(){
        if (materials.get(Item.materialDrops[0]) != null){
            return materials.get(Item.materialDrops[0]);
        }
        else{
            return 0;
        }
    }

    public int getVampireMaterials(){
        if (materials.get(Item.materialDrops[1]) != null){
            return materials.get(Item.materialDrops[1]);
        }
        else{
            return 0;
        }
    }

    public int getGolemMaterials(){
        if (materials.get(Item.materialDrops[2]) != null){
            return materials.get(Item.materialDrops[2]);
        }
        else{
            return 0;
        }
    }

    public int getSwords(){
        if (materials.get(Item.weaponDrops[0]) != null){
            return materials.get(Item.weaponDrops[0]);
        }
        else{
            return 0;
        }
    }

    public int getShields(){
        if (materials.get(Item.weaponDrops[1]) != null){
            return materials.get(Item.weaponDrops[1]);
        }
        else{
            return 0;
        }
    }

    public int getArmours(){
        if (materials.get(Item.weaponDrops[2]) != null){
            return materials.get(Item.weaponDrops[2]);
        }
        else{
            return 0;
        }
    }

    public void changeName(String name){
        this.name = name;
    }
    public int getAttack(){
        return attack;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth(){
        return health;
    }
    public LinkedHashMap<Item, Integer> getMaterials(){
        return materials;
    }

    public void levelUp(){
        System.out.println(yellow + "Level up!" + reset);
        level += 1;
        xp = 0;
        health += 10;
        attack += 5;
        defence += 5;
        coins += 20;
    }

    public void defeatedMonster(Item item){
        System.out.println(yellow + "Gained " + (10+Floor.floorLevel) + " xp!" + reset);
        xp += 10+Floor.floorLevel;
        if (xp >= level*10){
            levelUp();
        }
        materials.merge(item, 1, Integer::sum);
        coins += 10+Floor.floorLevel;
    }

    public void usePotion(){
        Scanner input = new Scanner(System.in);
        System.out.println(bold + "What potion would you like to use?" + reset);
        int i = 1;
        for (Potion key : inventory.keySet()) {
            System.out.println(bold + cyan + i + ": " + reset + key.getName() + ": " + reset + italic + inventory.get(key) + reset);
            i++;
        }
        Set<Potion> keySet = inventory.keySet();
        Potion[] keyArray = keySet.toArray(new Potion[keySet.size()]);
        int index = Integer.parseInt(input.nextLine());
        Potion potion = keyArray[index-1];
        switch (potion.getName()){
            case "Health potion" -> {
                health += potion.getHealth();
                inventory.replace(Item.potions[0], inventory.get(Item.potions[0])-1);
                if (inventory.get(Item.potions[0]) == 0){
                    inventory.remove(Item.potions[0]);
                }
            }
            case "Defence potion" -> {
                defence *= potion.getDefence();
                potionsInUse.add(potion);
                inventory.replace(Item.potions[1], inventory.get(Item.potions[1])-1);
                if (inventory.get(Item.potions[1]) == 0){
                    inventory.remove(Item.potions[1]);
                }
            }
            case "Attack potion" -> {
                attack *= potion.getAttack();
                potionsInUse.add(potion);
                inventory.replace(Item.potions[2], inventory.get(Item.potions[2])-1);
                if (inventory.get(Item.potions[2]) == 0){
                    inventory.remove(Item.potions[2]);
                }
            }
        }
    }

    public ArrayList<Potion> getPotionsInUse(){
        return potionsInUse;
    }

    public void clearPotionsInUse(){
        potionsInUse.clear();
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

    public void removeItem(Potion potion){
        materials.replace(potion, materials.get(potion)-1);
        if (materials.get(potion) == 0){
            materials.remove(potion);
        }
    }

    public void battle(Floor floor) throws FileNotFoundException {
        floor.enterLevel();
        System.out.println();
        System.out.println(bold + "Enemy turn" + reset);
        ArrayList<Enemy> enemies = floor.getEnemies();
        for (Enemy enemy : enemies){
            health -= enemy.getAttack();
            System.out.println(enemy.getName() + " has dealt " + purple + enemy.getAttack() + " damage" + reset);
        }
        if (health <= 0){
            died(this, floor);
        }
        else{
            System.out.println(bold + "Your turn" + reset);
            for (Enemy enemy : enemies){
                enemy.battle(this, floor);
            }
            floor.updateEnemies();
        }
    }

    public void battle(Dungeon dungeon, Floor floor) throws FileNotFoundException {
        System.out.println();
        System.out.println(bold + "Enemy turn" + reset);
        ArrayList<Enemy> enemies = dungeon.getEnemies();
        for (Enemy enemy : enemies){
            health -= enemy.getAttack();
            System.out.println(enemy.getName() + " has dealt " + purple + enemy.getAttack() + " damage" + reset);
        }
        if (health <= 0){
            died(this, floor);
        }
        else{
            System.out.println(bold + "Your turn" + reset);
            for (Enemy enemy : enemies){
                enemy.battle(this, dungeon);
            }
            dungeon.updateEnemies();
        }
    }

    // fix this checkpoint thing
    public void died(Player player, Floor floor) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println(bold + "You have died! Restart from previous floor or the beginning? (P/B)" + reset);
        String choice = input.nextLine();
        // fix this
        while (!(choice.equals("P") || choice.equals("B"))){
            System.out.println("Sorry, that is not a recognized command. Please try again.");
            choice = input.nextLine();
        }
        if (choice.equals("P")) {
            try {
                Scanner fileInput = new Scanner(new File("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\PlayerInfo"));
                if (fileInput.hasNextLine()) {
                    while (fileInput.hasNextLine()) {
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
                        // is this a problem?
                        player = new Player(name, health, defence, attack, level, xp, coins, materialQuantities, potionQuantities);
                    }
                }

                try {
                    fileInput = new Scanner(new File("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\FloorInfo"));
                    if (fileInput.hasNextLine()) {
                        int floorLevel = Integer.parseInt(fileInput.nextLine());
                        Floor.floorLevel = floorLevel-1;
                        ArrayList<String> enemyNames = new ArrayList<String>();
                        while (fileInput.hasNextLine()){
                            enemyNames.add(fileInput.nextLine());
                        }
                        floor = new Floor(enemyNames);
                        fileInput.close();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Couldn't open floor file");
                }

            } catch (FileNotFoundException e) {
                System.out.println("Couldn't open player file");
            }
        }
    }

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

    public void stats(){
        System.out.println();
        System.out.println(red + bold + "Health: " + reset + red + health + reset);
        System.out.println(blue + bold + "Defence: " + reset + blue + defence + reset);
        System.out.println(purple + bold + "Attack: " + reset + purple + attack + reset);
    }

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

    public String getName() {
        return name;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence){
        this.defence = defence;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }

    public int getCoins(){
        return coins;
    }

    public void useCoins(int used){
        coins -= used;
    }

    public void upgradeItem(){
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println(bold + "What do you want to level up?" + reset);
        System.out.println(bold + cyan + "1: " + reset + bold + "Sword " + reset + italic + "(uses vampire materials)" + reset);
        System.out.println(bold + cyan + "2: " + reset + bold + "Shield " + reset + italic + "(uses enemy materials)" + reset);
        System.out.println(bold + cyan + "3: " + reset + bold + "Armour " + reset + italic + "(uses golem materials)" + reset);
        int itemChoice = Integer.parseInt(input.nextLine());
        switch (itemChoice){
            case 1 -> equipped[0].upgradeItem(this);
            case 2 -> equipped[1].upgradeItem(this);
            case 3 -> equipped[2].upgradeItem(this);
        }
    }

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

    public int getLevel() {
        return level;
    }

    public int getXP() {
        return xp;
    }

    public void soldItem(int profit) {
        coins += profit;
    }
}