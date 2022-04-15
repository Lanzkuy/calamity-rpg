package engine;

import data.DataStorage;
import entity.HuntEnemy;
import entity.Player;
import items.HuntingItem;

import java.util.Objects;
import java.util.Random;

public class Hunt {
    public static String huntBattle(HuntEnemy enemy) {
        Random rand = new Random();
        StringBuilder logOutput = new StringBuilder();
        int moneyDrop = rand.nextInt(50 - 1) + 1;

        try{
            //Battle system
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

            int enemyDamage = enemy.damage(Player.baseDefense);
            if(enemyDamage < 0){
                enemyDamage = 0;
            }

            for (;;) {
                Player.getDamage(enemyDamage);
                enemy.getDamage(playerDamage);

                //If player or enemy death
                if (Player.health == 0) {
                    logOutput.append("You lose agains ").
                            append(enemy.getName()).append("\n").
                            append("you dropped $").append(moneyDrop).
                            append(" in the panic\n");

                    Player.money -= moneyDrop;
                    return logOutput.toString();
                }
                else if (enemy.getHealth() == 0) {
                    int exp = enemy.getExpDrop();

                    if (rand.nextInt(100 - 1) + 1 <= 50) {
                        logOutput.append("You win agains ").
                                append(enemy.getName()).append("\n").
                                append("You get ").append(exp).
                                append(" exp, $").append(moneyDrop).
                                append(" money\n").
                                append("and \n");
                    }
                    else{
                        logOutput.append("You win agains " + enemy.getName() + "\n" +
                                "You get " + exp + " exp and $" + moneyDrop + " money\n");
                    }

                    Player.exp += exp;
                    Player.money += moneyDrop;
                    Player.levelUp();
                    return logOutput.toString();
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in Hunt: " + ex);
        }
        return null;
    }
}
