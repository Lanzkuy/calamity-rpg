package data;

import entity.Player;

import java.io.File;
import java.io.PrintWriter;

public class DataSaver {
    //To save dungeon data to csv file
    public static void saveDungeonData(){
        try{
            File file = new File("src/main/resources/DungeonData.csv");
            PrintWriter pw = new PrintWriter(file);
            StringBuilder sb = new StringBuilder();
            sb.append("DungeonID;MapID;EnemyID;Status");
            sb.append("\n");
            for(int i = 0; i<DataStorage.LD.size(); i++){
                sb.append(DataStorage.LD.get(i).getDungeonID()).append(";");
                sb.append(DataStorage.LD.get(i).getMapID()).append(";");
                sb.append(DataStorage.LD.get(i).getBossID()).append(";");
                sb.append(DataStorage.LD.get(i).getStatus()).append(";");
                sb.append("\n");
            }
            pw.write(sb.toString());
            pw.flush();
            pw.close();
        }
        catch (Exception ex){
            System.err.println("Something went wrong in saveDungeonData : " + ex);
        }
    }

    //To save inventory data to csv file
    public static void saveInventoryData(){
        try{
            File file = new File("src/main/resources/InventoryData.csv");
            PrintWriter pw = new PrintWriter(file);
            StringBuilder sb = new StringBuilder();
            sb.append("ItemID;Name;Type;Quantity");
            sb.append("\n");
            for(int i = 0; i<DataStorage.LI.size(); i++){
                sb.append(DataStorage.LI.get(i).getItemID()).append(";");
                sb.append(DataStorage.LI.get(i).getName()).append(";");
                sb.append(DataStorage.LI.get(i).getType()).append(";");
                sb.append(DataStorage.LI.get(i).getQuantity()).append(";");
                sb.append("\n");
            }
            pw.write(sb.toString());
            pw.flush();
            pw.close();
        }
        catch (Exception ex){
            System.err.println("Something went wrong in saveInventoryData : " + ex);
        }
    }

    //To save map data to csv file
    public static void saveMapData(){
        try{
            File file = new File("src/main/resources/MapData.csv");
            PrintWriter pw = new PrintWriter(file);
            StringBuilder sb = new StringBuilder();
            sb.append("MapID;MapName;Status");
            sb.append("\n");
            for(int i = 0; i<DataStorage.LM.size(); i++){
                sb.append(DataStorage.LM.get(i).getMapID()).append(";");
                sb.append(DataStorage.LM.get(i).getMapName()).append(";");
                sb.append(DataStorage.LM.get(i).getStatus()).append(";");
                sb.append("\n");
            }
            pw.write(sb.toString());
            pw.flush();
            pw.close();
        }
        catch (Exception ex){
            System.err.println("Something went wrong in saveMapData : " + ex);
        }
    }

    //To save player data to csv file
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
