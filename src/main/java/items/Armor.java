package items;

public class Armor extends Item{
    private int additionalDefense;
    //Constructor
    public Armor(String itemID, String name, String type, int price, boolean sellable, int additionalDefense){
        setItemID(itemID);
        setName(name);
        setType(type);
        setPrice(price);
        setSellable(sellable);
        setAdditionalDefense(additionalDefense);
    }

    //Getter setter
    public int getAdditionalDefense() {
        return additionalDefense;
    }

    public void setAdditionalDefense(int additionalDefense) {
        this.additionalDefense = additionalDefense;
    }
}
