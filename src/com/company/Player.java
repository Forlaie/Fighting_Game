package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private ArrayList<Item> equipped = new ArrayList<Item>();
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
    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public void printInventory(){
        System.out.println("Inventory: " + inventory);
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
        inventory.add(item);
        coins += 10+Floor.floorLevel;
    }

    public void equipItem(Item item){
        equipped.add(item);
        health += item.getHealth();
        defence += item.getDefence();
        attack += item.getAttack();
        inventory.remove(item);
    }

    public void unequipItem(Item item){
        equipped.remove(item);
        health -= item.getHealth();
        defence -= item.getDefence();
        attack -= item.getAttack();
        inventory.add(item);
    }

    public void purchaseItem(Item item){
        if (coins < item.getCost()){
            System.out.println("Unfortunately, you don't have enough coins");
        }
        else{
            System.out.println("Successfully purchased " + item.getName() + "!");
            coins -= item.getCost();
            inventory.add(item);
        }
    }

    public void sellItem(Item item){
        System.out.println("Successfully sold " + item.getName() + "!");
        coins += item.getCost();
        inventory.remove(item);
    }

    public void removeItem(Item item){
        inventory.remove(item);
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
                        // ArrayList<Item> inventory = new ArrayList<Item>();
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
        System.out.println("Equipped: " + equipped);
        System.out.println("Inventory: " + inventory);
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to equip or unequip? (E/U)");
        String choice = input.nextLine();
        if (choice.equals("E")){
            if (inventory.size() == 0){
                System.out.println("You don't have any items to equip");
            }
            else{
                System.out.println("Input the index of the item you want to equip (1-" + inventory.size() + ")");
                int index = Integer.parseInt(input.nextLine());
                equipItem(inventory.get(index-1));
            }
        }
        else{
            if (equipped.size() == 0){
                System.out.println("You don't have any items to unequip");
            }
            else{
                System.out.println("Input the index of the item you want to unequip (1-" + equipped.size() + ")");
                int index = Integer.parseInt(input.nextLine());
                unequipItem(equipped.get(index-1));
            }
        }
    }

    public String getName() {
        return name;
    }
}
