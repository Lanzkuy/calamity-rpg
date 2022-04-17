package engine;

import data.DataSaver;
import data.DataStorage;
import entity.Boss;
import entity.Player;
import items.HuntingItem;

import java.util.Objects;
import java.util.Random;

public class Dungeon {
    private String dungeonID;
    private String mapID;
    private String bossID;
    private String status;

    public Dungeon(String dungeonID, String mapID, String bossID, String status){
        setDungeonID(dungeonID);
        setMapID(mapID);
        setBossID(bossID);
        setStatus(status);
    }

    //Dungeon battle system
    public static String dungeonBattle(Boss boss, String playerMove, String bossMove){
        Random rand = new Random();
        StringBuilder result = new StringBuilder();
        try{
            int moneyDrop = rand.nextInt(500 - 1) + 1;
            int expDrop = boss.getExpDrop();

            //Damage validation
            int playerDamage = Player.damage(boss.getBaseDefense());
            if(playerDamage < 0){
                playerDamage = 0;
            }

            int bossDamage = boss.damage(Player.totalDefense);
            if(bossDamage < 0){
                bossDamage = 0;
            }

            int bossSpecialAttackDamage = boss.specialAttack(Player.totalDefense);
            if(bossSpecialAttackDamage < 0){
                bossSpecialAttackDamage = 0;
            }

            if(playerMove.equals("Attack")){
                if(bossMove.equals("Attack")){
                    Player.getDamage(bossDamage);
                    boss.getDamage(playerDamage);
                    result.append("You deal ").append(playerDamage);
                    result.append(" damage to ").append(boss.getName());
                    result.append(", and get hurt ").append(bossDamage);
                    result.append(" damage\n");
                }
                else if(bossMove.equals("Block")){
                    if (rand.nextInt(100 - 1) + 1 <= 40) {
                        result.append(boss.getName()).append(" blocked the attack\n");
                    } else {
                        boss.getDamage(playerDamage);
                        result.append(boss.getName()).append(" failed block the attack\n");
                        result.append("You attack with ").append(playerDamage);
                        result.append(" damage\n");
                    }
                }
                else if(bossMove.equals("Special Attack")){
                    Player.getDamage(bossSpecialAttackDamage);
                    boss.getDamage(playerDamage);
                    result.append(boss.getName()).append(" use special attack\n");
                    result.append("You deal ").append(playerDamage);
                    result.append(" damage, and get hurt ").append(bossSpecialAttackDamage);
                    result.append(" damage\n");
                }
                else{
                    System.out.println("The move is not exist");
                }
            }
            else if(playerMove.equals("Block")){
                if(bossMove.equals("Attack")){
                    if (rand.nextInt(100 - 1) + 1 <= 50) {
                        result.append("You blocked the attack\n");
                    }
                    else {
                        Player.getDamage(bossDamage);
                        result.append("You failed block the attack\n");
                        result.append("You got hurt ").append(bossDamage);
                        result.append(" damage\n");
                    }
                }
                else if(bossMove.equals("Block")){
                    result.append("You both make a stand, no one get hurt\n");
                }
                else if(bossMove.equals("Special Attack")){
                    if (rand.nextInt(100 - 1) + 1 <= 50) {
                        result.append("You blocked the attack\n");
                    } else {
                        Player.getDamage(bossSpecialAttackDamage);
                        result.append(boss.getName()).append(" use special attack\n");
                        result.append("You failed block the attack\n").append("You got hurt ").append(bossSpecialAttackDamage);
                        result.append(" damage\n");
                    }
                }
                else{
                    System.out.println("The move is not exist");
                }
            }
            else if(playerMove.equals("Heal")){
                if(bossMove.equals("Attack")){
                    if (rand.nextInt(100 - 1) + 1 <= 30) {
                        int healTotal = Player.totalMaxHealth - (Player.totalMaxHealth * 25 / 100);
                        Player.heal(healTotal);
                        Player.getDamage(bossDamage);
                        result.append("You cast heal spell you get ").append(healTotal).append(" heal\n");
                    } else {
                        Player.getDamage(bossDamage);
                        result.append("You heal spell is failed\n");
                    }
                    result.append("You got hurt ").append(bossDamage);
                    result.append(" damage\n");
                }
                else if(bossMove.equals("Block")){
                    if (rand.nextInt(100 - 1) + 1 <= 30) {
                        int healTotal = Player.totalMaxHealth - (Player.totalMaxHealth * 25 / 100);
                        Player.heal(healTotal);
                        result.append(boss.getName()).append(" make a stand\n");
                        result.append("You cast heal spell you get ").append(healTotal).append(" heal\n");
                    } else {
                        Player.getDamage(bossDamage);
                        result.append(boss.getName()).append(" make a stand\n");
                        result.append("You heal spell is failed\n");
                    }
                }
                else if(bossMove.equals("Special Attack")){
                    if (rand.nextInt(100 - 1) + 1 <= 30) {
                        int healTotal = Player.totalMaxHealth - (Player.totalMaxHealth * 25 / 100);
                        Player.heal(healTotal);
                        Player.getDamage(bossSpecialAttackDamage);
                        result.append("You cast heal spell you get ").append(healTotal).append(" heal\n");
                    } else {
                        Player.getDamage(bossSpecialAttackDamage);
                        result.append("You heal spell is failed\n");
                    }
                    result.append(boss.getName()).append(" use special attack\n");
                    result.append("You got hurt ").append(bossSpecialAttackDamage);
                    result.append(" damage\n");
                }
                else{
                    System.out.println("The move is not exist");
                }
            }
            else{
                System.out.println("The move is not exist");
            }

            if(Player.health == 0){
                int totalMoney = Player.money - moneyDrop;
                Player.money = Math.max(totalMoney, 0);
                DataSaver.savePlayerData();
                result.append("You lose agains ").append(boss.getName()).append("\n");
                result.append("You dropped $").append(moneyDrop);
                result.append(" in the panic\n");
                DataSaver.saveInventoryData();
                return result.toString();
            }
            else if(boss.getHealth() == 0){
                Player.exp += expDrop;
                Player.money += moneyDrop;
                Player.levelUp();
                HuntingItem hi = DataStorage.getHuntingItem(boss.getItemDropID());
                Inventory.insertItem(Objects.requireNonNull(hi).getItemID(), hi.getType(), 3);
                DataSaver.saveInventoryData();
                DataSaver.savePlayerData();
                result.append("You win agains ").append(boss.getName()).append("\n");
                result.append("You get ").append(expDrop);
                result.append(" exp, $").append(moneyDrop);
                result.append(" money\n").append("and ");
                result.append(hi.getName()).append("\n");
                return result.toString();
            }
            else{
                return result.toString();
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in dungeonBattle : " + ex);
        }
        return result.toString();
    }

    //To update dungeon status
    public static void updateDungeon(String dungeonID, String mapID, String bossID, String status){
        try{
            for (int i = 0; i<DataStorage.LD.size(); i++){
                if(DataStorage.LD.get(i).getDungeonID().equals(dungeonID)){
                    DataStorage.LD.set(i, new Dungeon(dungeonID, mapID, bossID, status));
                    DataSaver.saveDungeonData();
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in updateDungeon: " + ex);
        }
    }

    //Getter Setter
    public String getDungeonID() {
        return dungeonID;
    }

    public void setDungeonID(String dungeonID) {
        this.dungeonID = dungeonID;
    }

    public String getMapID() {
        return mapID;
    }

    public void setMapID(String mapID) {
        this.mapID = mapID;
    }

    public String getBossID() {
        return bossID;
    }

    public void setBossID(String bossID) {
        this.bossID = bossID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
