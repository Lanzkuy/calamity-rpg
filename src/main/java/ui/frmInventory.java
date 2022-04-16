package ui;

import data.DataSaver;
import data.DataStorage;
import engine.Inventory;
import entity.Player;
import items.Consumable;
import items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class frmInventory extends JDialog implements ActionListener {
    private final frmGameMenu fgm;
    private JLabel lblWeapon;
    private JLabel lblArmor;
    private JLabel lblPendant;
    private JButton btnTypeConsumable;
    private JButton btnTypeHuntingItem;
    private JScrollPane pScrollPane;
    private JPanel pInventory;
    private JPanel pInventoryItem;

    public frmInventory(frmGameMenu fgm){
        super(fgm, "Inventory", ModalityType.APPLICATION_MODAL);
        this.fgm = fgm;
        initialize();
    }

    private void initialize(){
        loadEquipment();
        loadInventory("Consumable");
        btnTypeConsumableOnClick();
        btnTypeHuntingItemOnClick();
        btnExitOnClick();

        setTitle("Calamity RPG");
        setContentPane(pInventory);
        setMinimumSize(pInventory.getMinimumSize());
        setMaximumSize(pInventory.getMaximumSize());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    private void btnTypeConsumableOnClick(){
        btnTypeConsumable.addActionListener(e -> {
            loadInventory("Consumable");
        });
    }

    private void btnTypeHuntingItemOnClick(){
        btnTypeHuntingItem.addActionListener(e -> {
            loadInventory("Hunting Item");
        });
    }

    private void btnExitOnClick(){
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                fgm.setEnabled(true);
            }
        });
    }

    private void loadEquipment(){
        if(Player.weapon != null){
            lblWeapon.setText(Player.weapon.getName());
        }
        else{
            lblWeapon.setText("-");
        }

        if(Player.armor != null){
            lblArmor.setText(Player.armor.getName());
        }
        else{
            lblArmor.setText("-");
        }

        if(Player.pendant != null){
            lblPendant.setText(Player.pendant.getName());
        }
        else{
            lblPendant.setText("-");
        }
    }

    private void loadInventory(String type){
        pInventoryItem.removeAll();
        ArrayList<Inventory> inventData = DataStorage.LI;
        for (int i = 0; i<inventData.size(); i++){
            JPanel pItem = new JPanel();
            pItem.setLayout(new GridLayout(0, 3));
            pItem.setBackground(new Color(189, 189, 189));
            pItem.setMaximumSize(new Dimension(500,20));
            pItem.setVisible(true);

            JLabel lblItemName = new JLabel();
            lblItemName.setText(inventData.get(i).getName());
            lblItemName.setForeground(new Color(43, 43, 43));
            lblItemName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            lblItemName.setVisible(true);
            lblItemName.setHorizontalAlignment(JLabel.CENTER);
            lblItemName.setMaximumSize(new Dimension(100,20));

            JLabel lblQuantity = new JLabel();
            lblQuantity.setText(String.valueOf(inventData.get(i).getQuantity()));
            lblQuantity.setForeground(new Color(43, 43, 43));
            lblQuantity.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            lblQuantity.setVisible(true);
            lblQuantity.setHorizontalAlignment(JLabel.RIGHT);
            lblQuantity.setMaximumSize(new Dimension(100,20));

            if(inventData.get(i).getType().equals("Consumable") && type.equals("Consumable")){
                JButton btnUseItem = new JButton();
                btnUseItem.setName(inventData.get(i).getItemID());
                btnUseItem.setText("Use");
                btnUseItem.setForeground(new Color(232, 232, 232));
                btnUseItem.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
                btnUseItem.setVisible(true);
                btnUseItem.setHorizontalAlignment(JButton.LEFT);
                btnUseItem.setMaximumSize(new Dimension(100,20));
                btnUseItem.setBackground(new Color(55, 55 , 55));
                btnUseItem.addActionListener(this);

                pItem.add(btnUseItem);
                pItem.add(lblItemName);
                pItem.add(lblQuantity);
                pInventoryItem.setLayout(new BoxLayout(pInventoryItem, BoxLayout.Y_AXIS));
                pInventoryItem.add(pItem);
            }
            else if(inventData.get(i).getType().equals("Hunting Item") && type.equals("Hunting Item")){
                pItem.add(lblItemName);
                pItem.add(lblQuantity);
                pInventoryItem.setLayout(new BoxLayout(pInventoryItem, BoxLayout.Y_AXIS));
                pInventoryItem.add(pItem);

            }
        }
        pScrollPane.getViewport().add(pInventoryItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Consumable consumable = DataStorage.getConsumable(button.getName());
        if(Objects.requireNonNull(consumable).getItemID().equals(button.getName())){
            if(consumable.getName().equals("Offensive Potion")){

            }
            else if(consumable.getName().equals("Defensive Potion")){

            }
            else{
                Player.heal(consumable.getHealValue());
            }
            Inventory.updateItem(consumable.getItemID(), consumable.getName(), consumable.getType(), -1);
            DataSaver.saveInventoryData();
            loadInventory("Consumable");
            fgm.reload();
        }
    }
}
