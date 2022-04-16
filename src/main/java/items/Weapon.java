package items;

public class Weapon extends Item{
    private int additionalAttack;

    public Weapon(String itemID, String name, String type, int price, boolean sellable, int additionalAttack){
        setItemID(itemID);
        setName(name);
        setType(type);
        setPrice(price);
        setSellable(sellable);
        setAdditionalAttack(additionalAttack);
    }

    //Getter Setter
    public int getAdditionalAttack() {
        return additionalAttack;
    }

    public void setAdditionalAttack(int additionalAttack) {
        this.additionalAttack = additionalAttack;
    }
}
