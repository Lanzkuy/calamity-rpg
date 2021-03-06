package engine;

import data.DataSaver;
import data.DataStorage;
import entity.HuntEnemy;
import entity.Player;
import items.HuntingItem;

import java.util.Objects;
import java.util.Random;

public class Hunt {
    public static String huntBattle(HuntEnemy enemy) {
        Random rand = new Random();
        int moneyDrop = rand.nextInt(50 - 1) + 1;
        StringBuilder logOutput = new StringBuilder();

        try{
            //Enemy encounter
            logOutput.append(String.format("""
                    You encountered %s
                    Level : %s
                    Attack : %s
                    Defense : %s
                    Health : %s/%s
                    """, enemy.getName(), enemy.getLevel(), enemy.getBaseAttack(),
                    enemy.getBaseDefense(), enemy.getHealth(), enemy.getBaseMaxHealth()));

            //Damage validation
            int playerDamage = Player.damage(enemy.getBaseDefense());
            if(playerDamage < 0){
                playerDamage = 0;
            }

            int enemyDamage = enemy.damage(Player.totalDefense);
            if(enemyDamage < 0){
                enemyDamage = 0;
            }

            //Loop until player/enemy health = 0
            for (;;) {
                Player.getDamage(enemyDamage);
                enemy.getDamage(playerDamage);

                //If player/enemy death
                if (Player.health == 0) {
                    logOutput.append("You lose agains ").
                            append(enemy.getName()).append("\n").
                            append("you dropped $").append(moneyDrop).
                            append(" in the panic\n");

                    Player.money -= moneyDrop;
                    DataSaver.savePlayerData();
                    return logOutput.toString();
                }
                else if (enemy.getHealth() == 0) {
                    int exp = enemy.getExpDrop();

                    //Item drop RNG
                    if (rand.nextInt(100 - 1) + 1 <= 50) {
                        HuntingItem hi = DataStorage.getHuntingItem(enemy.getItemDropID());
                        Inventory.insertItem(Objects.requireNonNull(hi).getItemID(), hi.getType(), 1);
                        DataSaver.saveInventoryData();
                        logOutput.append("You win agains ").
                                append(enemy.getName()).append("\n").
                                append("You get ").append(exp).
                                append(" exp, $").append(moneyDrop).
                                append(" money\n").append("and ").
                                append(hi.getName()).append("\n");
                    }
                    else{
                        logOutput.append("You win agains ").
                                append(enemy.getName()).
                                append("\n").append("You get ").
                                append(exp).append(" exp and $").
                                append(moneyDrop).append(" money\n");
                    }

                    Player.exp += exp;
                    Player.money += moneyDrop;
                    Player.levelUp();
                    DataSaver.savePlayerData();
                    return logOutput.toString();
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in huntBattle: " + ex);
        }
        return null;
    }
}
