package ui;

import data.DataLoader;
import data.DataStorage;
import engine.Hunt;
import entity.HuntEnemy;
import entity.Player;

import javax.swing.*;
import java.awt.*;

public class frmGameMenu extends JFrame{
    private JPanel pGameMenu;
    private JPanel pContentBottom;
    private JPanel pContentTop;
    private JPanel pStat;
    private JPanel pLog;
    private JPanel pTitle;
    private JPanel pHealthBar;
    private JPanel pStatValue;
    private JPanel pExpBar;
    private JScrollPane pScrollLog;
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
        reload();
        initialize();
    }

    public void initialize(){
        setContentPane(pGameMenu);
        setMinimumSize(pGameMenu.getMinimumSize());
        setMaximumSize(pGameMenu.getMaximumSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();

        btnHuntOnClick();
    }

    public void reload(){
        DataLoader.loadData();

        lblPlayerName.setText(Player.name);
        lblLevel.setText("Level " + Player.level);
        lblMoneyValue.setText("$"+Player.money);
        lblAttackValue.setText(String.valueOf(Player.baseAttack));
        lblDefenseValue.setText(String.valueOf(Player.baseDefense));
        lblCriticalChanceValue.setText(Player.baseCriticalChance+"%");
        lblLifestealValue.setText(Player.baseLifeSteal+"%");

        healthBar.setStringPainted(true);
        healthBar.setMaximum(Player.baseMaxHealth);
        healthBar.setValue(Player.health);
        healthBar.setString(Player.health+"/"+Player.baseMaxHealth);
        expBar.setStringPainted(true);
        expBar.setMaximum(Player.maxExp);
        expBar.setValue(Player.exp);
        expBar.setString(Player.exp+"/"+Player.maxExp);
    }

    public void btnHuntOnClick(){
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
                pScrollLog.getViewport().add(pLogText);
                pScrollLog.updateUI();
                reload();
            }
        });
    }

    public static void main(String[] args) {
        frmGameMenu fgm = new frmGameMenu();
    }
}
