package data;

import engine.Inventory;
import entity.HuntEnemy;
import entity.Map;
import entity.Player;
import items.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DataLoader {
    //Clear all data in data storage
    public static void clearData(){
        DataStorage.LW.clear();
        DataStorage.LA.clear();
        DataStorage.LP.clear();
        DataStorage.LC.clear();
        DataStorage.LHI.clear();
        DataStorage.LI.clear();
        DataStorage.LM.clear();
        DataStorage.LHE.clear();
    }

    //Load all data
    public static void loadData(){
        loadItemData();
        loadInventoryData();
        loadMapData();
        loadEnemyData();
        loadPlayerData();
    }

    //Load item data to various type
    public static void loadItemData(){
        String line;
        Weapon w = null;
        Armor a = null;
        Pendant p = null;
        Consumable c = null;
        HuntingItem hi = null;
        try{
            File file = new File("src/main/resources/ItemData.csv");
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] values = line.split(";");
                switch (values[2]) {
                    case "Weapon" -> {
                        w = new Weapon(values[0], values[1], values[2],
                                Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]),
                                Integer.parseInt(values[5]));
                        DataStorage.LW.add(w);
                    }
                    case "Armor" -> {
                        a = new Armor(values[0], values[1], values[2],
                                Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]),
                                Integer.parseInt(values[5]));
                        DataStorage.LA.add(a);
                    }
                    case "Pendant" -> {
                        p = new Pendant(values[0], values[1], values[2],
                                Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]),
                                Integer.parseInt(values[5]), Integer.parseInt(values[6]),
                                Integer.parseInt(values[7]));
                        DataStorage.LP.add(p);
                    }
                    case "Consumable" -> {
                        c = new Consumable(values[0], values[1], values[2],
                                Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]),
                                Integer.parseInt(values[10]));
                        DataStorage.LC.add(c);
                    }
                    case "Hunting Item" -> {
                        hi = new HuntingItem(values[0], values[1], values[2],
                                Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]));
                        DataStorage.LHI.add(hi);
                    }
                    default -> System.out.println("This type of item is illegal : " + values[2]);
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in loadItemData : " + ex);
        }
    }

    //Load inventory data
    public static void loadInventoryData(){
        String line;
        Inventory i;
        try{
            File file = new File("src/main/resources/InventoryData.csv");
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] values = line.split(";");
                i = new Inventory(values[0], values[1], values[2], Integer.parseInt(values[3]));
                DataStorage.LI.add(i);
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in loadInventoryData : " + ex);
        }
    }

    //Load map data
    public static void loadMapData(){
        String line;
        Map m;
        try{
            File file = new File("src/main/resources/MapData.csv");
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] values = line.split(";");
                m = new Map(values[0], values[1], values[2]);
                DataStorage.LM.add(m);
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in loadMapData : " + ex);
        }
    }

    //Load enemy data
    public static void loadEnemyData(){
        String line;
        HuntEnemy ce;
        try{
            File file = new File("src/main/resources/EnemyData.csv");
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            br.readLine();
            while ((line = br.readLine()) != null){
                String[] values = line.split(";");
                ce = new HuntEnemy(values[0], values[1], values[2], values[3], values[4],
                        Integer.parseInt(values[5]), Integer.parseInt(values[6]), Integer.parseInt(values[7]),
                        Integer.parseInt(values[8]), (int) Float.parseFloat(values[9]));
                DataStorage.LHE.add(ce);
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in loadEnemyData : " + ex);
        }
    }

    //Load player data
    public static void loadPlayerData(){
        try{
            if(Player.name == null){
                File file = new File("src/main/resources/PlayerData.csv");
                BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                br.readLine();

                String[] values = br.readLine().split(";");
                Player.mapID = values[0];
                Player.weapon = DataStorage.getWeapon(values[1]);
                Player.armor = DataStorage.getArmor(values[2]);
                Player.pendant = DataStorage.getPendant(values[3]);
                Player.name = values[4];
                Player.level = Integer.parseInt(values[5]);
                Player.exp = Integer.parseInt(values[6]);
                Player.maxExp = Integer.parseInt(values[7]);
                Player.health = Integer.parseInt(values[8]);
                Player.baseMaxHealth = Integer.parseInt(values[9]);
                Player.baseAttack = Integer.parseInt(values[10]);
                Player.baseDefense = Integer.parseInt(values[11]);
                Player.baseCriticalChance = Integer.parseInt(values[12]);
                Player.baseLifeSteal = Integer.parseInt(values[13]);
                Player.money = Integer.parseInt(values[14]);
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in loadPlayerData : " + ex);
        }
    }
}
