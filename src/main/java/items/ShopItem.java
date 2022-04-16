package items;

public class ShopItem extends Item{
    public ShopItem(String itemID, String name, String type, int price, boolean buyable, boolean sellable){
        setItemID(itemID);
        setName(name);
        setType(type);
        setPrice(price);
        setSellable(sellable);
        setBuyable(buyable);
    }
}
