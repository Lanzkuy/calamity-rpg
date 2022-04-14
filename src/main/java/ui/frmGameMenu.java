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

public class frmGameMenu {
    private JPanel pGameMenu;
    private JPanel pContentBottom;
    private JPanel pContentTop;
    private JPanel pStat;
    private JPanel pLog;
    private JScrollPane pScrollLog;
    private JPanel pTitle;
    private JLabel lblTitle;
    private JPanel pHealthBar;
    private JPanel pStatValue;
    private JProgressBar healthBar;
    private JProgressBar expBar;
    private JPanel pExpBar;
    private JButton btnHunt;
    private JButton btnShop;
    private JButton btnInventory;
    private JButton btnBlacksmith;
    private JButton btnMap;
    private JButton btnDungeon;
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("frmGameMenu");
        frmGameMenu fgm = new frmGameMenu();
        frame.setContentPane(fgm.pGameMenu);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(fgm.pGameMenu.getMinimumSize());
        frame.setMaximumSize(fgm.pGameMenu.getMaximumSize());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
