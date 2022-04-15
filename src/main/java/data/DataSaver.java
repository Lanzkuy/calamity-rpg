package data;

import entity.Player;

import java.io.File;
import java.io.PrintWriter;

public class DataSaver {
    public static void savePlayerData(){
        try{
            File file = new File("src/main/resources/PlayerData.csv");
            PrintWriter pw = new PrintWriter(file);
            String weapon = Player.weapon != null ? Player.weapon.getItemID() : "";
            String armor = Player.armor != null ? Player.armor.getItemID() : "";
            String pendant = Player.pendant != null ? Player.pendant.getItemID() : "";
            String sb = "MapID;WeaponID;ArmorID;PendantID;Name;" +
                    "Level;Exp;BaseMaxExp;Health;BaseMaxHealth;" +
                    "BaseAttack;BaseDefense;BaseCriticalChance;BaseLifeSteal;Money" +
                    "\n" +
                    Player.mapID + ";" + weapon + ";" + armor + ";" +
                    pendant + ";" + Player.name + ";" + Player.level + ";" +
                    Player.exp + ";" + Player.maxExp + ";" + Player.health + ";" +
                    Player.baseMaxHealth + ";" + Player.baseAttack + ";" + Player.baseDefense + ";" +
                    Player.baseCriticalChance + ";" + Player.baseLifeSteal + ";" +Player.money;
            pw.write(sb);
            pw.flush();
            pw.close();
        }
        catch (Exception ex){
            System.err.println("Something went wrong in savePlayerData : " + ex);
        }
    }
}
