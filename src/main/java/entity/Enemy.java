package entity;

import java.util.*;

public abstract class Enemy {
    //Enemy properties
    protected Random rand = new Random();
    private String enemyID;
    private String mapID;
    private String itemDropID;
    private String name;
    private String type;
    private int health;
    private int baseMaxHealth;
    private int level;
    private int baseAttack;
    private int baseDefense;
    private int baseCriticalChance;
    private int expDrop;

    //To calculate the total damage to the player
    public abstract int damage(int playerDefense);

    //To calculate the damage the enemy takes
    public abstract void getDamage(int damage);

    //Getter Setter
    public String getEnemyID() {
        return enemyID;
    }

    public void setEnemyID(String enemyID) {
        this.enemyID = enemyID;
    }

    public String getMapID() {
        return mapID;
    }

    public void setMapID(String mapID) {
        this.mapID = mapID;
    }

    public String getItemDropID() {
        return itemDropID;
    }

    public void setItemDropID(String itemDropID) {
        this.itemDropID = itemDropID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBaseMaxHealth() {
        return baseMaxHealth;
    }

    public void setBaseMaxHealth(int baseMaxHealth) {
        this.baseMaxHealth = baseMaxHealth;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    public int getBaseCriticalChance() {
        return baseCriticalChance;
    }

    public void setBaseCriticalChance(int baseCriticalChance) {
        this.baseCriticalChance = baseCriticalChance;
    }

    public int getExpDrop() {
        return expDrop;
    }

    public void setExpDrop(int expDrop) {
        this.expDrop = expDrop;
    }
}
