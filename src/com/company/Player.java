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
        materials.put(new Item("Enemy material", 1, """
                    Enemies drop this"""), 0);
        materials.put(new Item("Vampire material", 1, """
                    Vampires drop this"""), 0);
        materials.put(new Item("Golem material", 1, """
                    Golems drop this"""), 0);
        materials.put(new Item("Sword", 0, 0, 10, 10, """
                    The sword is a sturdy and reliable weapon for any warrior
                    +10 atk"""), 0);
        materials.put(new Item("Shield", 5, 10, 0, 10, """
                    The shield is an essential for any warrior to protect themselves and others
                    +5 hp, +10 def"""), 0);
        materials.put(new Item("Armour", 20, 5, 0, 30, """
                    Proper armour keeps your vitals safe
                    +10 hp, +20 def, +5 atk"""), 0);
    }

    public Player(String name, int health, int defence, int attack, int level, int xp, int coins){
        this.name = name;
        this.health = health;
        this.defence = defence;
        this.attack = attack;
        this.level = level;
        this.xp = xp;
        this.coins = coins;

        //temp
//        materials.put("Enemy material", 0);
//        materials.put("Vampire material", 0);
//        materials.put("Golem material", 0);
//        materials.put("Sword", 0);
//        materials.put("Shield", 0);
//        materials.put("Armour", 0);
    }

    public LinkedHashMap<Potion, Integer> getInventory(){
        return inventory;
    }

    public int getEnemyMaterials(){
        if (materials.get(new Item("Enemy material", 1, """
                    Enemies drop this""")) != null){
            return materials.get(new Item("Enemy material", 1, """
                    Enemies drop this"""));
        }
        else{
            return 0;
        }
    }

    public int getVampireMaterials(){
        if (materials.get(new Item("Vampire material", 1, """
                    Vampires drop this""")) != null){
            return materials.get(new Item("Vampire material", 1, """
                    Vampires drop this"""));
        }
        else{
            return 0;
        }
    }

    public int getGolemMaterials(){
        if (materials.get(new Item("Golem material", 1, """
                    Golems drop this""")) != null){
            return materials.get(new Item("Golem material", 1, """
                    Golems drop this"""));
        }
        else{
            return 0;
        }
    }

    public int getSwords(){
        if (materials.get(new Item("Sword", 0, 0, 10, 10, """
                    The sword is a sturdy and reliable weapon for any warrior
                    +10 atk""")) != null){
            return materials.get(new Item("Sword", 0, 0, 10, 10, """
                    The sword is a sturdy and reliable weapon for any warrior
                    +10 atk"""));
        }
        else{
            return 0;
        }
    }

    public int getShields(){
        if (materials.get(new Item("Shield", 5, 10, 0, 10, """
                    The shield is an essential for any warrior to protect themselves and others
                    +5 hp, +10 def""")) != null){
            return materials.get(new Item("Shield", 5, 10, 0, 10, """
                    The shield is an essential for any warrior to protect themselves and others
                    +5 hp, +10 def"""));
        }
        else{
            return 0;
        }
    }

    public int getArmours(){
        if (materials.get(new Item("Armour", 20, 5, 0, 30, """
                    Proper armour keeps your vitals safe
                    +10 hp, +20 def, +5 atk""")) != null){
            return materials.get(new Item("Armour", 20, 5, 0, 30, """
                    Proper armour keeps your vitals safe
                    +10 hp, +20 def, +5 atk"""));
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
    public void sellMaterial(){
        Scanner input = new Scanner(System.in);
        int i = 1;
        System.out.println("What would you like to sell?");
        for (Item key : materials.keySet()) {
            System.out.println(i + ": " + key.getName() + ": " + materials.get(key) + ", +" + key.getCost() + " coins");
            i++;
        }

        Set<Item> keySet = materials.keySet();
        Item[] keyArray = keySet.toArray(new Item[keySet.size()]);
        int index = Integer.parseInt(input.nextLine());
        Item item = keyArray[index-1];
        System.out.println("How many " + item.getName() + " would you like to sell?");
        int amount = Integer.parseInt(input.nextLine());
        int profit = (int) (item.getCost()*0.8);
        if (amount > materials.get(item)){
            System.out.println("Sorry, you don't have that many " + item.getName());
        }
        else{
            System.out.println("Successfully sold " + amount + " " + item.getName());
            materials.put(item, materials.get(item)-amount);
            if (materials.get(item) == 0){
                materials.remove(item);
            }
            coins += profit;
        }
    }

    public void sellPotion(){
        Scanner input = new Scanner(System.in);
        int i = 1;
        System.out.println("What would you like to sell?");
        for (Potion key : inventory.keySet()) {
            System.out.println(i + ": " + key.getName() + ": " + inventory.get(key) + ", +" + key.getCost() + " coins");
            i++;
        }

        Set<Potion> keySet = inventory.keySet();
        Potion[] keyArray = keySet.toArray(new Potion[keySet.size()]);
        int index = Integer.parseInt(input.nextLine());
        Potion potion = keyArray[index-1];
        System.out.println("How many " + potion.getName() + " would you like to sell?");
        int amount = Integer.parseInt(input.nextLine());
        int profit = (int) (potion.getCost()*0.8);
        if (amount > inventory.get(potion)){
            System.out.println("Sorry, you don't have that many " + potion.getName());
        }
        else{
            System.out.println("Successfully sold " + amount + " " + potion.getName());
            inventory.put(potion, inventory.get(potion)-amount);
            if (inventory.get(potion) == 0){
                inventory.remove(potion);
            }
            coins += profit;
        }
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
        materials.merge(item, 1, Integer::sum);
        coins += 10+Floor.floorLevel;
    }

    public void usePotion(){
        Scanner input = new Scanner(System.in);
        System.out.println("What potion would you like to use?");
        int i = 1;
        for (Potion key : inventory.keySet()) {
            System.out.println(i + ": " + key.getName() + ": " + inventory.get(key));
            i++;
        }
        Set<Potion> keySet = inventory.keySet();
        Potion[] keyArray = keySet.toArray(new Potion[keySet.size()]);
        int index = Integer.parseInt(input.nextLine());
        Potion potion = keyArray[index-1];
        switch (potion.getName()){
            case "Health potion" -> {
                health += potion.getHealth();
                inventory.put(new Potion("Health potion", 50, 0, 0, 50, """
                    Health potion heals you by 50hp points
                    Costs 50 coins"""), inventory.get(new Potion("Health potion", 50, 0, 0, 50, """
                    Health potion heals you by 50hp points
                    Costs 50 coins"""))-1);
            }
            case "Defence potion" -> {
                defence *= potion.getDefence();
                potionsInUse.add(potion);
                inventory.put(new Potion("Defence potion", 0, 2, 0, 50, """
                    Defence potion doubles your defence
                    Lasts for one floor/dungeon
                    Costs 50 coins"""), inventory.get(new Potion("Defence potion", 0, 2, 0, 50, """
                    Defence potion doubles your defence
                    Lasts for one floor/dungeon
                    Costs 50 coins"""))-1);
            }
            case "Attack potion" -> {
                attack *= potion.getAttack();
                potionsInUse.add(potion);
                inventory.put(new Potion("Attack potion", 0, 0, 2, 50, """
                    Attack potion doubles your attack
                    Lasts for one floor/dungeon
                    Costs 50 coins"""), inventory.get(new Potion("Attack potion", 0, 0, 2, 50, """
                    Attack potion doubles your attack
                    Lasts for one floor/dungeon
                    Costs 50 coins"""))-1);
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

    public void purchaseItem(Potion potion){
        if (coins < potion.getCost()){
            System.out.println("Unfortunately, you don't have enough coins");
        }
        else{
            System.out.println("Successfully purchased " + potion.getName() + "!");
            coins -= potion.getCost();
            inventory.merge(potion, 1, Integer::sum);
        }
    }

//    public void sellItem(Item item){
//        System.out.println("Successfully sold " + item.getName() + "!");
//        coins += item.getCost();
//        materials.remove(item);
//    }

    public void removeItem(Potion potion){
        materials.put(potion, materials.get(potion)-1);
        if (materials.get(potion) == 0){
            materials.remove(potion);
        }
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
        if (materials.size() == 0){
            System.out.println(italic + "You have nothing yet..." + reset);
        }
        else{
            for (Map.Entry<Item, Integer> entry : materials.entrySet()) {
                Item key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(key.getName() + ": " + value);
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
                System.out.println(key.getName() + ": " + value);
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
        System.out.println("What do you want to level up?");
        System.out.println("1: sword (uses vampire materials)");
        System.out.println("2: shield (uses golem materials)");
        System.out.println("3: armour (uses enemy materials)");
        int itemChoice = Integer.parseInt(input.nextLine());
        switch (itemChoice){
            case 1 -> equipped[0].upgradeItem(this);
            case 2 -> equipped[1].upgradeItem(this);
            case 3 -> equipped[2].upgradeItem(this);
        }
    }

    public void useVampireMaterial(int used) {
        materials.put(new Item("Vampire material", 1, """
                    Vampires drop this"""), materials.get(new Item("Vampire material", 1, """
                    Vampires drop this"""))-used);
    }

    public void useSword(int used) {
        materials.put(new Item("Sword", 0, 0, 10, 10, """
                    The sword is a sturdy and reliable weapon for any warrior
                    +10 atk"""), materials.get(new Item("Sword", 0, 0, 10, 10, """
                    The sword is a sturdy and reliable weapon for any warrior
                    +10 atk"""))-used);
    }

    public void useGolemMaterial(int used) {
        materials.put(new Item("Golem material", 1, """
                    Golems drop this"""), materials.get(new Item("Golem material", 1, """
                    Golems drop this"""))-used);
    }

    public void useShield(int used) {
        materials.put(new Item("Shield", 5, 10, 0, 10, """
                    The shield is an essential for any warrior to protect themselves and others
                    +5 hp, +10 def"""), materials.get(new Item("Shield", 5, 10, 0, 10, """
                    The shield is an essential for any warrior to protect themselves and others
                    +5 hp, +10 def"""))-used);
    }

    public void useEnemyMaterial(int used) {
        materials.put(new Item("Enemy material", 1, """
                    Enemies drop this"""), materials.get(new Item("Enemy material", 1, """
                    Enemies drop this"""))-used);
    }

    public void useArmour(int used) {
        materials.put(new Item("Armour", 20, 5, 0, 30, """
                    Proper armour keeps your vitals safe
                    +10 hp, +20 def, +5 atk"""), materials.get(new Item("Armour", 20, 5, 0, 30, """
                    Proper armour keeps your vitals safe
                    +10 hp, +20 def, +5 atk"""))-used);
    }
}