package engine;

import data.DataSaver;
import data.DataStorage;
import entity.Player;
import items.Armor;
import items.Pendant;
import items.Weapon;
import ui.frmBlacksmith;
import ui.frmShop;

import javax.swing.*;
import java.rmi.ServerError;

public class Blacksmith {
    private String blacksmithID;
    private String itemID;
    private String material1ID;
    private String material2ID;
    private String material3ID;
    private int material1Quantity;
    private int material2Quantity;
    private int material3Quantity;
    private int cost;

    public Blacksmith(String blacksmithID, String itemID, String material1ID, String material2ID,
                      String material3ID, int material1Quantity, int material2Quantity, int material3Quantity,
                      int cost){
        setBlacksmithID(blacksmithID);
        setItemID(itemID);
        setMaterial1ID(material1ID);
        setMaterial2ID(material2ID);
        setMaterial3ID(material3ID);
        setMaterial1Quantity(material1Quantity);
        setMaterial2Quantity(material2Quantity);
        setMaterial3Quantity(material3Quantity);
        setCost(cost);
    }

    public static void craftUIValidation(frmBlacksmith fb, String blacksmithID) {
        String craftResponse = craft(blacksmithID);
        JOptionPane.showMessageDialog(fb ,craftResponse);
        DataSaver.savePlayerData();
        DataSaver.saveInventoryData();
    }

    //To craft a equip using materials in inventory
    public static String craft(String blacksmithID){
        try{
            Blacksmith bs = DataStorage.getBlacksmithItem(blacksmithID);
            if(bs != null){
                if((Player.money - bs.getCost()) >= 0) {
                    Weapon w = DataStorage.getWeapon(bs.getItemID());
                    Armor a = DataStorage.getArmor(bs.getItemID());
                    Pendant p = DataStorage.getPendant(bs.getItemID());

                    if(w != null){
                        if(Player.weapon == null){
                            Player.weapon = w;
                            Player.money -= bs.getCost();
                            Inventory.updateItem(bs.getMaterial1ID(), -bs.getMaterial1Quantity());
                            Inventory.updateItem(bs.getMaterial2ID(), -bs.getMaterial2Quantity());
                            Inventory.updateItem(bs.getMaterial3ID(), -bs.getMaterial3Quantity());
                            return "Crafting " + w.getName() + "success";
                        }
                        else if(w.getAdditionalAttack() > Player.weapon.getAdditionalAttack()){
                            Player.weapon = w;
                            Player.money -= bs.getCost();
                            Inventory.updateItem(bs.getMaterial1ID(), -bs.getMaterial1Quantity());
                            Inventory.updateItem(bs.getMaterial2ID(), -bs.getMaterial2Quantity());
                            Inventory.updateItem(bs.getMaterial3ID(), -bs.getMaterial3Quantity());
                            return "Crafting " + w.getName() + "success";
                        }
                        else{
                            return "You current weapon is more powerful than this";
                        }
                    }
                    else if(a != null){
                        if(Player.armor == null){
                            Player.armor = a;
                            Player.money -= bs.getCost();
                            Inventory.updateItem(bs.getMaterial1ID(), -bs.getMaterial1Quantity());
                            Inventory.updateItem(bs.getMaterial2ID(), -bs.getMaterial2Quantity());
                            Inventory.updateItem(bs.getMaterial3ID(), -bs.getMaterial3Quantity());
                            return "Crafting " + a.getName() + "success";
                        }
                        else if(a.getAdditionalDefense() > Player.armor.getAdditionalDefense()){
                            Player.armor = a;
                            Player.money -= bs.getCost();
                            Inventory.updateItem(bs.getMaterial1ID(), -bs.getMaterial1Quantity());
                            Inventory.updateItem(bs.getMaterial2ID(), -bs.getMaterial2Quantity());
                            Inventory.updateItem(bs.getMaterial3ID(), -bs.getMaterial3Quantity());
                            return "Crafting " + a.getName() + "success";
                        }
                        else{
                            return "You current armor is more powerful than this";
                        }
                    }
                    else if(p != null){
                        if(Player.pendant == null){
                            Player.pendant = p;
                            Player.money -= bs.getCost();
                            Inventory.updateItem(bs.getMaterial1ID(), -bs.getMaterial1Quantity());
                            Inventory.updateItem(bs.getMaterial2ID(), -bs.getMaterial2Quantity());
                            Inventory.updateItem(bs.getMaterial3ID(), -bs.getMaterial3Quantity());
                            return "Crafting " + p.getName() + "success";
                        }
                        else if(p.getAdditionalMaxHealth() >= Player.pendant.getAdditionalMaxHealth()){
                            Player.pendant = p;
                            Player.money -= bs.getCost();
                            Inventory.updateItem(bs.getMaterial1ID(), -bs.getMaterial1Quantity());
                            Inventory.updateItem(bs.getMaterial2ID(), -bs.getMaterial2Quantity());
                            Inventory.updateItem(bs.getMaterial3ID(), -bs.getMaterial3Quantity());
                            return "Crafting " + p.getName() + "success";
                        }
                        else{
                            return "You current pendant is more powerful than this";
                        }
                    }
                }
                else{
                    return "You dont have enough money";
                }
                return "";
            }
            throw new Exception();
        }
        catch (Exception ex){
            System.err.println("Something went wrong in craft: " + ex);
            throw new RuntimeException();
        }
    }

    //Get craft item name
    public static String getCraftItemName(String itemID){
        try{
            Weapon w = DataStorage.getWeapon(itemID);
            Armor a = DataStorage.getArmor(itemID);
            Pendant p = DataStorage.getPendant(itemID);

            if(w != null){
                return w.getName();
            }
            else if (a != null){
                return a.getName();
            }
            else if(p != null){
                return p.getName();
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in getCraftItemName: " + ex);
            throw new RuntimeException();
        }
        return "";
    }

    //Getter Setter
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getMaterial1ID() {
        return material1ID;
    }

    public void setMaterial1ID(String material1ID) {
        this.material1ID = material1ID;
    }

    public String getMaterial2ID() {
        return material2ID;
    }

    public void setMaterial2ID(String material2ID) {
        this.material2ID = material2ID;
    }

    public String getMaterial3ID() {
        return material3ID;
    }

    public void setMaterial3ID(String material3ID) {
        this.material3ID = material3ID;
    }

    public int getMaterial1Quantity() {
        return material1Quantity;
    }

    public void setMaterial1Quantity(int material1Quantity) {
        this.material1Quantity = material1Quantity;
    }

    public int getMaterial2Quantity() {
        return material2Quantity;
    }

    public void setMaterial2Quantity(int material2Quantity) {
        this.material2Quantity = material2Quantity;
    }

    public int getMaterial3Quantity() {
        return material3Quantity;
    }

    public void setMaterial3Quantity(int material3Quantity) {
        this.material3Quantity = material3Quantity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getBlacksmithID() {
        return blacksmithID;
    }

    public void setBlacksmithID(String blacksmithID) {
        this.blacksmithID = blacksmithID;
    }
}
