package items;

public class Pendant extends Item{
    private int additionalCriticalChance;
    private int additionalLifeSteal;
    private int additionalMaxHealth;

    public Pendant(String itemID, String name, String type, int price, boolean sellable,
                   int additionalCriticalChance, int additionalLifeSteal, int additionalMaxHealth){
        setItemID(itemID);
        setName(name);
        setType(type);
        setPrice(price);
        setSellable(sellable);
        setAdditionalCriticalChance(additionalCriticalChance);
        setAdditionalLifeSteal(additionalLifeSteal);
        setAdditionalMaxHealth(additionalMaxHealth);
    }

    //Getter Setter
    public int getAdditionalCriticalChance() {
        return additionalCriticalChance;
    }

    public void setAdditionalCriticalChance(int additionalCriticalChance) {
        this.additionalCriticalChance = additionalCriticalChance;
    }

    public int getAdditionalLifeSteal() {
        return additionalLifeSteal;
    }

    public void setAdditionalLifeSteal(int additionalLifeSteal) {
        this.additionalLifeSteal = additionalLifeSteal;
    }

    public int getAdditionalMaxHealth() {
        return additionalMaxHealth;
    }

    public void setAdditionalMaxHealth(int additionalMaxHealth) {
        this.additionalMaxHealth = additionalMaxHealth;
    }
}
