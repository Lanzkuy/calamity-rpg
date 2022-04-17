package ui;

import data.DataLoader;
import data.DataSaver;
import data.DataStorage;
import engine.Dungeon;
import engine.Hunt;
import entity.HuntEnemy;
import entity.Player;

import javax.swing.*;

public class frmGameMenu extends JFrame{
    private JPanel pGameMenu;
    private JScrollPane pScrollPane;
    private JTextPane pLogText;
    private JProgressBar healthBar;
    private JProgressBar expBar;
    private JButton btnHunt;
    private JButton btnShop;
    private JButton btnInventory;
    private JButton btnBlacksmith;
    private JButton btnMap;
    private JButton btnDungeon;
    private JLabel lblPlayerName;
    private JLabel lblLevel;
    private JLabel lblMoneyValue;
    private JLabel lblAttackValue;
    private JLabel lblDefenseValue;
    private JLabel lblCriticalChanceValue;
    private JLabel lblLifestealValue;

    public frmGameMenu(){
        initialize();
    }

    private void initialize(){
        setTitle("Calamity RPG");
        setContentPane(pGameMenu);
        setMinimumSize(pGameMenu.getMinimumSize());
        setMaximumSize(pGameMenu.getMaximumSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();

        DataLoader.clearData();
        DataLoader.loadData();
        reload();

        btnHuntOnClick();
        btnMapOnClick();
        btnInventoryOnClick();
        btnDungeonOnClick();
        btnShopOnClick();
        btnBlacksmithOnClick();
    }

    public void reload(){
        try{
            DataSaver.savePlayerData();
            Player.initializeEquipment();
            if(Player.name.equals("")){
                String name = JOptionPane.showInputDialog(null, "Player Name");
                JOptionPane.showMessageDialog(null, "Welcome to Calamity RPG. Have funn >_<");
                Player.name = name;
                DataSaver.savePlayerData();
            }
            lblPlayerName.setText(Player.name);
            lblLevel.setText("Level " + Player.level);
            lblMoneyValue.setText("$"+Player.money);
            lblAttackValue.setText(String.valueOf(Player.totalAttack));
            lblDefenseValue.setText(String.valueOf(Player.totalDefense));
            lblCriticalChanceValue.setText(Player.totalCriticalChance+"%");
            lblLifestealValue.setText(Player.totalLifesteal+"%");

            healthBar.setStringPainted(true);
            healthBar.setMaximum(Player.totalMaxHealth);
            healthBar.setValue(Player.health);
            healthBar.setString(Player.health+"/"+Player.totalMaxHealth);
            expBar.setStringPainted(true);
            expBar.setMaximum(Player.maxExp);
            expBar.setValue(Player.exp);
            expBar.setString(Player.exp+"/"+Player.maxExp);
        }
        catch (Exception ex){
            System.err.println("Something went wrong in reloadGameMenu : " + ex);
        }
    }

    private void btnHuntOnClick(){
        try{
            btnHunt.addActionListener(e -> {
                //Randomize the enemy
                HuntEnemy ce = DataStorage.getRandomHuntEnemy();
                if(ce != null){
                    //Execute hunt battle system
                    if(Player.health != 0){
                        pLogText.setText(pLogText.getText() + "\n" + Hunt.huntBattle(ce));
                    }
                    else{
                        pLogText.setText(pLogText.getText() + "\nYour health is 0. Please take some medicine!\n");
                    }
                    pScrollPane.getViewport().add(pLogText);

                    DataLoader.clearData();
                    DataLoader.loadData();
                    reload();
                }
            });
        }
        catch (Exception ex){
            System.err.println("Something went wrong in btnHuntOnClick : " + ex);
        }
    }

    private void btnMapOnClick(){
        try{
            btnMap.addActionListener(e -> new frmChangeMap(this));
        }
        catch (Exception ex){
            System.err.println("Something went wrong in btnMapOnClick: " + ex);
        }
    }

    private void btnInventoryOnClick(){
        try{
            btnInventory.addActionListener(e -> new frmInventory(this));
        }
        catch (Exception ex){
            System.err.println("Something went wrong in btnInventoryOnClick : " + ex);
        }
    }

    private void btnShopOnClick(){
        try{
            btnShop.addActionListener(e -> new frmShop(this));
        }
        catch (Exception ex){
            System.err.println("Something went wrong in btnShopOnClick : " + ex);
        }
    }

    private void btnBlacksmithOnClick(){
        try{
            btnBlacksmith.addActionListener(e -> new frmBlacksmith(this));
        }
        catch (Exception ex){
            System.err.println("Something went wrong in btnBlacksmithOnClick : " + ex);
        }
    }

    private void btnDungeonOnClick(){
        try{
            btnDungeon.addActionListener(e ->{
                if(Player.health == Player.totalMaxHealth) {
                    Dungeon dungeon = DataStorage.getAvailableDungeon(Player.mapID);
                    if(dungeon != null){
                        int confirmation = JOptionPane.showConfirmDialog(null, "Do you want to dungeon battle?", "Select an option...", JOptionPane.YES_NO_OPTION);
                        if (confirmation == 0) {
                            new frmDungeon(this, dungeon);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frmGameMenu.this, "You already beat all dungeon in this map");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frmGameMenu.this, "Your health must be full to enter dungeon");
                }
            });
        }
        catch (Exception ex){
            System.err.println("Something went wrong in btnDungeonOnClick : " + ex);
        }
    }

    public static void main(String[] args) {
        new frmGameMenu();
    }
}
