package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import data.DataLoader;
import entity.Player;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

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
        DataLoader.loadData();
        initialize();
    }

    public void initialize(){
        //Frame
        setContentPane(pGameMenu);
        setMinimumSize(pGameMenu.getMinimumSize());
        setMaximumSize(pGameMenu.getMaximumSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    
        //Label
        lblPlayerName.setText(Player.name);
        lblLevel.setText("Level " + Player.level);
        lblMoneyValue.setText("$"+Player.money);
        lblAttackValue.setText(String.valueOf(Player.baseAttack));
        lblDefenseValue.setText(String.valueOf(Player.baseDefense));
        lblCriticalChanceValue.setText(Player.baseCriticalChance+"%");
        lblLifestealValue.setText(Player.baseLifeSteal+"%");

        //ProgressBar
        healthBar.setStringPainted(true);
        healthBar.setMaximum(Player.baseMaxHealth);
        healthBar.setValue(Player.health);
        healthBar.setString(Player.health+"/"+Player.baseMaxHealth);
        expBar.setStringPainted(true);
        expBar.setMaximum(Player.maxExp);
        expBar.setValue(Player.exp);
        expBar.setString(Player.exp+"/"+Player.maxExp);
    }

    public static void main(String[] args) {
        frmGameMenu fgm = new frmGameMenu();
    }
}
