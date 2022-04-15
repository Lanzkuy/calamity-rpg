package data;

import entity.HuntEnemy;
import entity.Map;
import entity.Player;
import items.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DataLoader {
    public static void clearData(){
        DataStorage.LW.clear();
        DataStorage.LA.clear();
        DataStorage.LP.clear();
        DataStorage.LC.clear();
        DataStorage.LHI.clear();
        DataStorage.LHE.clear();
    }

    public static void loadData(){
        loadItemData();
        loadMapData();
        loadEnemyData();
        loadPlayerData();
    }

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
                    case "Weapon" -> w = new Weapon(values[0], values[1], values[2],
                            Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]),
                            Integer.parseInt(values[5]));
                    case "Armor" -> a = new Armor(values[0], values[1], values[2],
                            Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]),
                            Integer.parseInt(values[5]));
                    case "Pendant" -> p = new Pendant(values[0], values[1], values[2],
                            Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]),
                            Integer.parseInt(values[5]), Integer.parseInt(values[6]),
                            Integer.parseInt(values[7]));
                    case "Consumable" -> c = new Consumable(values[0], values[1], values[2],
                            Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]),
                            Integer.parseInt(values[5]));
                    case "Hunting Item" -> hi = new HuntingItem(values[0], values[1], values[2],
                            Integer.parseInt(values[3]), Boolean.parseBoolean(values[4]));
                    default -> System.out.println("This type of item is illegal : " + values[2]);
                }
                DataStorage.LW.add(w);
                DataStorage.LA.add(a);
                DataStorage.LP.add(p);
                DataStorage.LC.add(c);
                DataStorage.LHI.add(hi);
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in loadItemData : " + ex);
        }
    }

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

    public static void loadPlayerData(){
        try{
            if(Player.name == null){
                File file = new File("src/main/resources/PlayerData.csv");
                BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                br.readLine();

                String[] values = br.readLine().split(";");
                Player.mapID = values[0];
                Player.weapon = null;
                Player.armor = null;
                Player.pendant = null;
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
