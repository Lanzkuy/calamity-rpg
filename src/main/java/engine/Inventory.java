package engine;

import data.*;
import items.*;


public class Inventory {
    private String itemID;
    private String name;
    private String type;
    private int quantity;

    public Inventory(String itemID, String name, String type, int quantity){
        setItemID(itemID);
        setName(name);
        setType(type);
        setQuantity(quantity);
    }

    //To put item into inventory
    public static void insertItem(String itemID, String type, int quantity){
        try{
            if(type.equals("Consumable")){
                Consumable c = DataStorage.getConsumable(itemID);
                if(c != null){
                    if(isItemExist(c.getItemID())){
                        DataStorage.LI.add(new Inventory(c.getItemID(), c.getName(), c.getType(), quantity));
                    }
                    else{
                        updateItem(c.getItemID(), c.getName(), c.getType(), quantity);
                    }
                }
            }
            else if(type.equals("Hunting Item")){
                HuntingItem hi = DataStorage.getHuntingItem(itemID);
                if(hi != null){
                    if(isItemExist(hi.getItemID())){
                        DataStorage.LI.add(new Inventory(hi.getItemID(), hi.getName(), hi.getType(), quantity));
                    }
                    else{
                        updateItem(hi.getItemID(), hi.getName(), hi.getType(), quantity);
                    }
                }
            }
            else{
                System.out.println("This type of item is illegal");
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in insertItem: " + ex);
        }
    }

    //To update exist item in inventory
    public static void updateItem(String itemID, String name, String type, int quantity){
        try{
            for (int i = 0; i<DataStorage.LI.size(); i++){
                if(DataStorage.LI.get(i).getItemID().equals(itemID)){
                    DataStorage.LI.set(i, new Inventory(itemID, name, type, DataStorage.LI.get(i).getQuantity() + quantity));
                    if(DataStorage.LI.get(i).getQuantity() <= 0){
                        deleteItem(DataStorage.LI.get(i).getItemID());
                    }
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in updateItem: " + ex);
        }
    }

    //To delete item when quantity 0
    public static void deleteItem(String itemID){
        try{
            for (int i = 0; i<DataStorage.LI.size(); i++){
                if(DataStorage.LI.get(i).getItemID().equals(itemID)){
                    DataStorage.LI.remove(i);
                    break;
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in deleteItem: " + ex);
        }
    }

    //To check if the item is existed in inventory
    public static boolean isItemExist(String itemID){
        try{
            for (int i = 0; i<DataStorage.LI.size(); i++){
                if(DataStorage.LI.get(i).getItemID().equals(itemID)){
                    return false;
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in isItemExist: " + ex);
        }
        return true;
    }

    //Getter Setter
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
