package data;

import engine.Dungeon;
import engine.Inventory;
import entity.Boss;
import entity.HuntEnemy;
import entity.Map;
import entity.Player;
import items.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DataStorage {
    //To keep data while the program is running
    public static ArrayList<Weapon> LW = new ArrayList<>();
    public static ArrayList<Armor> LA = new ArrayList<>();
    public static ArrayList<Pendant> LP = new ArrayList<>();
    public static ArrayList<Consumable> LC = new ArrayList<>();
    public static ArrayList<HuntingItem> LHI = new ArrayList<>();
    public static ArrayList<Inventory> LI = new ArrayList<>();
    public static ArrayList<HuntEnemy> LHE = new ArrayList<>();
    public static ArrayList<Boss> LB = new ArrayList<>();
    public static ArrayList<Map> LM = new ArrayList<>();
    public static ArrayList<Dungeon> LD = new ArrayList<>();

    //To get weapon by itemID
    public static Weapon getWeapon(String itemID){
        try{
            if(!itemID.equals("")){
                for (Weapon w : LW) {
                    if(itemID.equals(w.getItemID())){
                        return w;
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in getWeapon : " + ex.getMessage());
        }
        return null;
    }

    //To get armor by itemID
    public static Armor getArmor(String itemID){
        try{
            if(!itemID.equals("")){
                for (Armor a : LA) {
                    if(itemID.equals(a.getItemID())){
                        return a;
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in getArmor : " + ex.getMessage());
        }
        return null;
    }

    //To get pendant by itemID
    public static Pendant getPendant(String itemID){
        try{
            if(!itemID.equals("")){
                for (Pendant p : LP) {
                    if(itemID.equals(p.getItemID())){
                        return p;
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in getPendant: " + ex.getMessage());
        }
        return null;
    }

    //To get consumable item by itemID
    public static Consumable getConsumable(String itemID){
        try{
            if(!itemID.equals("")){
                for (Consumable c : LC) {
                    if(itemID.equals(c.getItemID())){
                        return c;
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in getConsumable : " + ex.getMessage());
        }
        return null;
    }

    //To get hunting item by itemID
    public static HuntingItem getHuntingItem(String itemID){
        try{
            if(!itemID.equals("")){
                for (HuntingItem hi : LHI) {
                    if(itemID.equals(hi.getItemID())){
                        return hi;
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in getHuntingItem : " + ex.getMessage());
        }
        return null;
    }

    //To get map by mapID
    public static Map getMap(String mapID){
        try{
            if(!mapID.equals("")){
                for (Map hi : LM) {
                    if(mapID.equals(hi.getMapID())){
                        return hi;
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in getMap : " + ex.getMessage());
        }
        return null;
    }

    //To get random hunt enemy and random level by player current map
    public static HuntEnemy getRandomHuntEnemy(){
        Random rand = new Random();
        try{
            Collections.shuffle(LHE);
            for (HuntEnemy he : LHE) {
                int i = 0;
                while (i < LHE.size()) {
                    if(he.getMapID().equals(Player.mapID) && he.getType().equals("Crook")){
                        if(he.getEnemyID().equals(LHE.get(i).getEnemyID())){
                            he.setLevel(rand.nextInt(Player.level-5-1)+1);
                            if(he.getLevel()>1){
                                he.setBaseAttack((he.getBaseAttack() + (2 * he.getLevel())));
                                he.setBaseDefense((he.getBaseDefense() + (2 * he.getLevel())));
                                he.setBaseMaxHealth((he.getBaseMaxHealth() + (3 * he.getLevel())));
                                he.setBaseCriticalChance((int) (he.getBaseCriticalChance() + (0.5 * he.getLevel())));
                                he.setExpDrop((int) (he.getExpDrop() + (0.5 * he.getLevel())));
                            }
                            he.setHealth(he.getBaseMaxHealth());
                            return he;
                        }
                    }
                    i++;
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in getRandomHuntEnemy : " + ex.getMessage());
        }
        return null;
    }

    //To get boss enemy by enemyID
    public static Boss getBossEnemy(String enemyID){
        try{
            if(!enemyID.equals("")){
                for (Boss b : LB) {
                    if(enemyID.equals(b.getEnemyID())){
                        b.setHealth(b.getBaseMaxHealth());
                        return b;
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in getBossEnemy : " + ex.getMessage());
        }
        return null;
    }

    //To get available dungeon in current map
    public static Dungeon getAvailableDungeon(String mapID){
        try{
            if(!mapID.equals("")){
                for (Dungeon d : LD) {
                    if(mapID.equals(d.getMapID())){
                        if(d.getStatus().equals("Available")){
                            return d;
                        }
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in getAvailableDungeon : " + ex.getMessage());
        }
        return null;
    }

    //To get total dungeon in current map
    public static int getTotalDungeonByMap(String mapID){
        try{
            int total = 0;
            if(!mapID.equals("")){
                for (Dungeon d : LD) {
                    if(mapID.equals(d.getMapID())){
                        total += 1;
                        return total;
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in getAvailableDungeon : " + ex.getMessage());
        }
        return 0;
    }

    //To get total available dungeon in current map
    public static int getTotalAvailableDungeonByMap(String mapID){
        try{
            int total = 0;
            if(!mapID.equals("")){
                for (Dungeon d : LD) {
                    if(mapID.equals(d.getMapID())){
                        if(d.getStatus().equals("Available")){
                            total += 1;
                            return total;
                        }
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in getAvailableDungeon : " + ex.getMessage());
        }
        return 0;
    }
}
