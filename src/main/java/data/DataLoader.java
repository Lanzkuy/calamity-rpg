package data;

import entity.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DataLoader {
    public static void loadData(){
        loadPlayerData();
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
