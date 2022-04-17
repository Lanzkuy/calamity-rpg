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
        DataSaver.savePlayerData();
        Player.initializeEquipment();
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

    private void btnHuntOnClick(){
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

    private void btnMapOnClick(){
        btnMap.addActionListener(e -> new frmChangeMap(this));
    }

    private void btnInventoryOnClick(){
        btnInventory.addActionListener(e -> new frmInventory(this));
    }

    private void btnShopOnClick(){
        btnShop.addActionListener(e -> new frmShop(this));
    }

    private void btnBlacksmithOnClick(){
        btnBlacksmith.addActionListener(e -> new frmBlacksmith(this));
    }

    private void btnDungeonOnClick(){
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

    public static void main(String[] args) {
        new frmGameMenu();
    }
}
