package entity;

import items.*;
import data.*;

import java.util.*;

public class Player{
    //Player properties
    public static final Random rand = new Random();
    public static String mapID;
    public static String name;
    public static int health;
    public static int baseMaxHealth;
    public static int level;
    public static int exp;
    public static int maxExp;
    public static int baseAttack;
    public static int baseDefense;
    public static int baseCriticalChance;
    public static int baseLifeSteal;
    public static int money;
    public static Weapon weapon;
    public static Armor armor;
    public static Pendant pendant;

    //Initialize the equipment
    public Player(){
        baseAttack = weapon != null ? baseAttack + weapon.getAdditionalAttack() : baseAttack;
        baseDefense = armor != null ? baseDefense + armor.getAdditionalDefense() : baseDefense;
        baseCriticalChance = pendant != null ? baseCriticalChance + pendant.getAdditionalCriticalChance() : baseCriticalChance;
        baseLifeSteal = pendant != null ? baseLifeSteal + pendant.getAdditionalLifeSteal() : baseLifeSteal;
        baseMaxHealth = pendant != null ? baseMaxHealth + pendant.getAdditionalMaxHealth() : baseMaxHealth;
    }

    //To heal player health
    public static void heal(int healValue){
        health += healValue;
        if(health >= baseMaxHealth){
            health = baseMaxHealth;
        }
    }

    //To give lifeSteal when attacking
    public static void lifeSteal(int damage){
        if(baseLifeSteal > 0) {
            int life = (baseLifeSteal/100) * damage;
            heal(life);
        }
    }

    //To calculate the total damage to the enemy
    public static int damage(int enemyDefense){
        int totalDamage = baseAttack - (enemyDefense / 2);
        if(rand.nextInt(100-1)+1 <= baseCriticalChance){
            totalDamage = totalDamage * 2;
        }
        lifeSteal(totalDamage);
        return totalDamage;
    }

    //To calculate the damage the player takes
    public static void getDamage(int damage){
        health -= damage;
        if(health <= 0){
            health = 0;
        }
    }

    //Levelup player when exp equals max exp
    public static void levelUp(){
        int extraExp = exp - maxExp;
        if(extraExp >= 0){
            System.out.println("Congratulations you leveled up");
            exp = extraExp;
            level += 1;
            maxExp += maxExp + (maxExp * 2) / 4;
            baseAttack += 2;
            baseDefense += 2;
            baseMaxHealth += 3;
            baseCriticalChance += 0.4 * level;
        }
    }
}
