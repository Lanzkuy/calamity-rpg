package ui;

import data.DataStorage;
import engine.Dungeon;
import engine.Inventory;
import entity.Boss;
import entity.Map;
import entity.Player;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class frmDungeon extends  JDialog{
    private final frmGameMenu fgm;
    private Boss boss;
    private Dungeon dungeon;
    private JPanel pDungeon;
    private JButton btnAttack;
    private JButton btnBlock;
    private JButton btnHeal;
    private JProgressBar playerHealthBar;
    private JScrollPane pScrollPane;
    private JTextPane pLogText;
    private JProgressBar bossHealthBar;

    public frmDungeon(frmGameMenu fgm, Dungeon dungeon){
        super(fgm, "DUNGEON", ModalityType.APPLICATION_MODAL);
        this.fgm = fgm;
        this.dungeon = dungeon;
        loadEntity();
        initialize();
    }

    private void initialize(){
        btnAttackOnClick();
        btnBlockOnClick();
        btnHealOnClick();
        btnExitOnClick();

        setContentPane(pDungeon);
        setMinimumSize(pDungeon.getMinimumSize());
        setMaximumSize(pDungeon.getMaximumSize());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    private void loadEntity(){
        boss = DataStorage.getBossEnemy(dungeon.getBossID());
        if(boss != null){
            pLogText.setText(String.format("""
                    You challenge boss %s
                    Attack : %s
                    Defense : %s
                    Critical Chance : %s
                    """, boss.getName(), boss.getBaseAttack(),
                    boss.getBaseDefense(), boss.getBaseCriticalChance()));
            pScrollPane.getViewport().add(pLogText);

            loadHealthBar();
        }
    }

    private void loadHealthBar(){
        playerHealthBar.setStringPainted(true);
        playerHealthBar.setMaximum(Player.totalMaxHealth);
        playerHealthBar.setValue(Player.health);
        playerHealthBar.setString(Player.health+"/"+Player.totalMaxHealth);
        bossHealthBar.setStringPainted(true);
        bossHealthBar.setMaximum(boss.getBaseMaxHealth());
        bossHealthBar.setValue(boss.getHealth());
        bossHealthBar.setString(boss.getHealth()+"/"+boss.getBaseMaxHealth());
    }

    private void btnAttackOnClick(){
        btnAttack.addActionListener(e -> {
            String output = Dungeon.dungeonBattle(boss, "Attack", boss.bossRandomMove());
            pLogText.setText(pLogText.getText() + output);
            loadHealthBar();
            if(output.contains("You lose")){
                JOptionPane.showMessageDialog(frmDungeon.this, "Aww, you dead good luck next time..");
                setVisible(false);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                fgm.reload();
                this.dispatchEvent(new WindowEvent(frmDungeon.this, WindowEvent.WINDOW_CLOSING));
            }
            else if(output.contains("You win")){
                Dungeon.updateDungeon(dungeon.getDungeonID(), dungeon.getMapID(), dungeon.getBossID(), "Defeated");
                int totalAvailableDungeon = DataStorage.getTotalAvailableDungeonByMap(dungeon.getMapID());
                if(totalAvailableDungeon == 0){
                    Map currentMap = DataStorage.getMap(dungeon.getMapID());
                    Map.openNewMap(Objects.requireNonNull(currentMap).getMapID());
                }
                JOptionPane.showMessageDialog(frmDungeon.this, "Congratulations, you win this dungeon!");
                setVisible(false);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                fgm.reload();
                this.dispatchEvent(new WindowEvent(frmDungeon.this, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    private void btnBlockOnClick(){
        btnBlock.addActionListener(e -> {
            String output = Dungeon.dungeonBattle(boss, "Block", boss.bossRandomMove());
            pLogText.setText(pLogText.getText() + output);
            loadHealthBar();
            if(output.contains("You lose")){
                JOptionPane.showMessageDialog(frmDungeon.this, "Aww, you dead good luck next time..");
                setVisible(false);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                fgm.reload();
                this.dispatchEvent(new WindowEvent(frmDungeon.this, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    private void btnHealOnClick(){
        btnHeal.addActionListener(e -> {
            String output = Dungeon.dungeonBattle(boss, "Heal", boss.bossRandomMove());
            pLogText.setText(pLogText.getText() + output);
            loadHealthBar();
            if(output.contains("You lose")){
                JOptionPane.showMessageDialog(frmDungeon.this, "Aww, you dead good luck next time..");
                setVisible(false);
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                fgm.reload();
                this.dispatchEvent(new WindowEvent(frmDungeon.this, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    private void btnExitOnClick(){
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            if(getDefaultCloseOperation() == DO_NOTHING_ON_CLOSE){
                JOptionPane.showMessageDialog(frmDungeon.this, "You must defeat the boss");
            }
            }
        });
    }
}
