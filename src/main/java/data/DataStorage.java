package data;

import entity.HuntEnemy;
import entity.Map;
import entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DataStorage {
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
