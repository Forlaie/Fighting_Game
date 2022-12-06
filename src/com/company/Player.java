package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.HashMap;

public class Player {
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
                    +10 atk
                    """),
            new Item("Shield", 5, 10, 0, 10, """
                    The shield is an essential for any warrior to keep themselves safe and protect what they need to protect
                    +5 hp, +10 def
                    """),
            new Item("Armour", 20, 5, 0, 30, """
                    Proper armour keeps your vitals safe
                    +10 hp, +20 def, +5 atk
                    """)
    };
    HashMap<String, Integer> materials = new HashMap<String, Integer>();
    HashMap<String, Integer> potions = new HashMap<String, Integer>();

    private boolean isFighting;

    public Player(String name){
        this.name = name;
        health = 100;
        defence = 0;
        attack = 10;
        level = 1;
        xp = 0;
        isFighting = false;
        coins = 0;
        materials.put("Enemy material", 0);
        materials.put("Vampire material", 0);
        materials.put("Golem material", 0);
        materials.put("Sword", 0);
        materials.put("Shield", 0);
        materials.put("Armour", 0);
    }

    public Player(String name, int health, int defence, int attack, int level, int xp, int coins){
        this.name = name;
        this.health = health;
        this.defence = defence;
        this.attack = attack;
        this.level = level;
        this.xp = xp;
        this.isFighting = false;
        this.coins = coins;

        //temp
        materials.put("Enemy material", 0);
        materials.put("Vampire material", 0);
        materials.put("Golem material", 0);
        materials.put("Sword", 0);
        materials.put("Shield", 0);
        materials.put("Armour", 0);
    }

    public int getEnemyMaterials(){
        return materials.get("Enemy material");
    }

    public int getVampireMaterials(){
        return materials.get("Vampire material");
    }

    public int getGolemMaterials(){
        return materials.get("Golem material");
    }

    public int getSwords(){
        return materials.get("Sword");
    }

    public int getShields(){
        return materials.get("Shield");
    }

    public int getArmours(){
        return materials.get("Armour");
    }

    public void changeName(String name){
        this.name = name;
    }
    public int getAttack(){
        return attack;
    }
    public void setIsFighting(boolean isFighting){
        this.isFighting = isFighting;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getHealth(){
        return health;
    }
    public HashMap<String, Integer> getMaterials(){
        return materials;
    }

    public void levelUp(){
        System.out.println("Level up!");
        level += 1;
        xp = 0;
        health += 10;
        attack += 5;
        defence += 5;
        coins += 20;
    }

    public void defeatedMonster(Item item){
        System.out.println("Gained " + (10+Floor.floorLevel) + " xp!");
        xp += 10+Floor.floorLevel;
        if (xp >= level*10){
            levelUp();
        }
        materials.put(item.getName(), materials.get(item.getName())+1);
        coins += 10+Floor.floorLevel;
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

    public void purchaseItem(Item item){
        if (coins < item.getCost()){
            System.out.println("Unfortunately, you don't have enough coins");
        }
        else{
            System.out.println("Successfully purchased " + item.getName() + "!");
            coins -= item.getCost();
            materials.put(item.getName(), materials.get(item.getName())+1);
        }
    }

    public void sellItem(Item item){
        System.out.println("Successfully sold " + item.getName() + "!");
        coins += item.getCost();
        materials.remove(item);
    }

    public void removeItem(Item item){
        materials.remove(item);
    }

    public void battle(Floor floor) throws FileNotFoundException {
        System.out.println();
        System.out.println(bold + "Enemy turn" + reset);
        ArrayList<Enemy> enemies = floor.getEnemies();
        for (Enemy enemy : enemies){
            health -= enemy.getAttack();
            System.out.println(enemy.getName() + " has dealt " + enemy.getAttack() + " damage");
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
            System.out.println(enemy.getName() + " has dealt " + enemy.getAttack() + " damage");
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
        System.out.println(bold + "You have died! Restart from previous floor? (Y/N)" + reset);
        String choice = input.nextLine();
        if (choice.equals("Y")) {
            try {
                Scanner fileInput = new Scanner(new File("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\PlayerInfo"));
                //BufferedReader bf=new BufferedReader(new FileReader("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\PlayerInfo"));
                if (fileInput.hasNextLine()) {
                    while (fileInput.hasNextLine()) {
                        String name = fileInput.nextLine();
                        int health = Integer.parseInt(fileInput.nextLine());
                        int defence = Integer.parseInt(fileInput.nextLine());
                        int attack = Integer.parseInt(fileInput.nextLine());
                        int level = Integer.parseInt(fileInput.nextLine());
                        int xp = Integer.parseInt(fileInput.nextLine());
                        int coins = Integer.parseInt(fileInput.nextLine());
                        // figure out how to get the items back...
                        // ArrayList<Item> materials = new ArrayList<Item>();
                        // ArrayList<Item> equipped = new ArrayList<Item>();

                        player = new Player(name, health, defence, attack, level, xp, coins);
                    }
                }

                try {
                    fileInput = new Scanner(new File("C:\\Users\\jessi\\Desktop\\CS Project Base\\src\\FloorInfo"));
                    if (fileInput.hasNextLine()) {
                        int floorLevel = Integer.parseInt(fileInput.nextLine());
                        // figure out how to get enemies back...
                        // Enemy[] enemies =
                        Floor.floorLevel = floorLevel - 1;
                        floor = new Floor(player);
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
        boolean hasSomething = false;
        for (Map.Entry<String, Integer> entry : materials.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value != 0){
                System.out.println(key + ": " + value);
                hasSomething = true;
            }
        }
        if (!hasSomething){
            System.out.println(italic + "You have nothing yet..." + reset);
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
        System.out.println("What do you want to level up?");
        System.out.println("1: sword");
        System.out.println("2: shield");
        System.out.println("3: armour");
        int itemChoice = Integer.parseInt(input.nextLine());
        switch (itemChoice){
            case 1 -> equipped[0].upgradeItem(this);
            case 2 -> equipped[1].upgradeItem(this);
            case 3 -> equipped[2].upgradeItem(this);
        }
    }

    public void useVampireMaterial(int used) {
        materials.put("Vampire material", materials.get("Vampire material")-used);
    }

    public void useSword(int used) {
        materials.put("Sword", materials.get("Sword")-used);
    }

    public void useGolemMaterial(int used) {
        materials.put("Golem material", materials.get("Vampire material")-used);
    }

    public void useShield(int used) {
        materials.put("Shield", materials.get("Shield")-used);
    }

    public void useEnemyMaterial(int used) {
        materials.put("Enemy material", materials.get("Enemy material")-used);
    }

    public void useArmour(int used) {
        materials.put("Armour", materials.get("Armour")-used);
    }
}
