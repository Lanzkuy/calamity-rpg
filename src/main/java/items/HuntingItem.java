package items;

public class HuntingItem extends Item{
    //Constructor
    public HuntingItem(String itemID, String name, String type, int price, boolean sellable){
        setItemID(itemID);
        setName(name);
        setType(type);
        setPrice(price);
        setSellable(sellable);
    }
}