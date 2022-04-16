package items;

public class Armor extends Item{
    private int additionalDefense;

    public Armor(String itemID, String name, String type, int additionalDefense){
        setItemID(itemID);
        setName(name);
        setType(type);
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
