package data;

import entity.HuntEnemy;
import entity.Map;
import entity.Player;
import items.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DataStorage {
    public static ArrayList<Weapon> LW = new ArrayList<>();
    public static ArrayList<Armor> LA = new ArrayList<>();
    public static ArrayList<Pendant> LP = new ArrayList<>();
    public static ArrayList<Consumable> LC = new ArrayList<>();
    public static ArrayList<HuntingItem> LHI = new ArrayList<>();
    public static ArrayList<HuntEnemy> LHE = new ArrayList<>();
    public static ArrayList<Map> LM = new ArrayList<>();

    public static HuntEnemy getRandomHuntEnemy(){
        Random rand = new Random();
        try{
            Collections.shuffle(LHE);
            for (HuntEnemy he : LHE) {
                int i = 0;
                while (i < LHE.size()) {
                    if(he.getMapID().equals(Player.mapID) && he.getType().equals("Crook")){
                        if(he.getEnemyID().equals(LHE.get(i).getEnemyID())){
                            he.setLevel(rand.nextInt(4-1)+1);
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
}
