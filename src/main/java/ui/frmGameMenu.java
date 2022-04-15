package ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

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
    private JLabel lblTitle;
    private JLabel lblPlayerName;
    private JLabel lblLevel;
    private JLabel lblMoney;
    private JLabel lblMoneyValue;
    private JLabel lblAttack;
    private JLabel lblAttackValue;
    private JLabel lvlDefense;
    private JLabel lblDefenseValue;
    private JLabel lblCriticalChance;
    private JLabel lblCriticalChanceValue;
    private JLabel lblLifesteal;
    private JLabel lblLifestealValue;

    public frmGameMenu(){
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

        //ProgressBar
        healthBar.setValue(70);
        expBar.setValue(50);
    }

    public static void main(String[] args) {
        frmGameMenu fgm = new frmGameMenu();
    }

}
