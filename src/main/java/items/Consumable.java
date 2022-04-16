package items;

public class Consumable extends Item{
    private int healValue;

    public Consumable(String itemID, String name, String type, int price, boolean sellable, int healValue){
        setItemID(itemID);
        setName(name);
        setType(type);
        setPrice(price);
        setSellable(sellable);
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
