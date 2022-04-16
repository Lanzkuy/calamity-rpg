package items;

public class Consumable extends Item{
    private int healValue;

    public Consumable(String itemID, String name, String type, int healValue){
        setItemID(itemID);
        setName(name);
        setType(type);
        setHealValue(healValue);
    }

    //Getter setter
    public int getHealValue() {
        return healValue;
    }

    public void setHealValue(int healValue) {
        this.healValue = healValue;
    }
}
