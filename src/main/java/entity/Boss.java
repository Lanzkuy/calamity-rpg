package entity;

import java.util.Random;

public class Boss extends Enemy{
    public Boss(String enemyID, String mapID, String name, String type,
                     String itemDropID, int baseMaxHealth, int baseAttack,
                     int baseDefense, int baseCriticalChance, int dropExp){
        setEnemyID(enemyID);
        setMapID(mapID);
        setName(name);
        setType(type);
        setItemDropID(itemDropID);
        setBaseMaxHealth(baseMaxHealth);
        setBaseAttack(baseAttack);
        setBaseDefense(baseDefense);
        setBaseCriticalChance(baseCriticalChance);
        setExpDrop(dropExp);
    }

    @Override
    public int damage(int playerDefense) {
        int totalDamage = getBaseAttack() - (playerDefense / 2);
        if(rand.nextInt(100-1)+1 <= getBaseCriticalChance()){
            totalDamage = totalDamage * 2;
        }
        return totalDamage;
    }

    @Override
    public void getDamage(int damage){
        setHealth(getHealth() - damage);
        if(getHealth() <= 0){
            setHealth(0);
        }
    }

    public int specialAttack(int playerDefense){
        int totalDamage = getBaseAttack() - (playerDefense / 3);
        if(rand.nextInt(100-1)+1 <= getBaseCriticalChance()){
            totalDamage = totalDamage * 2;
        }
        return totalDamage;
    }

    public String bossRandomMove(){
        if(rand.nextInt(100 - 1) + 1 <= 60){
            return "Attack";
        }
        else if (rand.nextInt(100 - 1) + 1 <= 30){
            return "Block";
        }
        else{
            return "Special Attack";
        }
    }
}
