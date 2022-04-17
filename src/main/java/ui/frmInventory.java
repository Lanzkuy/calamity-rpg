package ui;

import data.DataSaver;
import data.DataStorage;
import engine.Inventory;
import entity.Player;
import items.Consumable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JPanel consumableHeader;
    private JPanel huntingItemHeader;

    public frmInventory(frmGameMenu fgm){
        super(fgm, "Inventory", ModalityType.APPLICATION_MODAL);
        this.fgm = fgm;
        initialize();
    }

    private void initialize(){
        loadEquipment();
        huntingItemHeader.setVisible(false);
        consumableHeader.setVisible(true);
        loadInventory("Consumable");
        btnTypeConsumableOnClick();
        btnTypeHuntingItemOnClick();

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
            huntingItemHeader.setVisible(false);
            consumableHeader.setVisible(true);
            loadInventory("Consumable");
        });

        btnTypeConsumable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnTypeConsumable.setBackground(new Color(43, 43 ,43));
                btnTypeHuntingItem.setBackground(new Color(103, 103 ,103));
            }
        });
    }

    private void btnTypeHuntingItemOnClick(){
        btnTypeHuntingItem.addActionListener(e -> {
            consumableHeader.setVisible(false);
            huntingItemHeader.setVisible(true);
            loadInventory("Hunting Item");
        });

        btnTypeHuntingItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnTypeHuntingItem.setBackground(new Color(43, 43 ,43));
                btnTypeConsumable.setBackground(new Color(103, 103 ,103));
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
            pItem.setBackground(new Color(189, 189, 189));
            pItem.setMaximumSize(new Dimension(500,20));
            pItem.setVisible(true);

            JLabel lblItemName = new JLabel();
            lblItemName.setText(inventData.get(i).getName());
            lblItemName.setForeground(new Color(43, 43, 43));
            lblItemName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            lblItemName.setHorizontalAlignment(JLabel.CENTER);
            lblItemName.setVisible(true);

            JLabel lblQuantity = new JLabel();
            lblQuantity.setText(String.valueOf(inventData.get(i).getQuantity()));
            lblQuantity.setForeground(new Color(43, 43, 43));
            lblQuantity.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            lblQuantity.setHorizontalAlignment(JLabel.CENTER);
            lblQuantity.setVisible(true);

            if(inventData.get(i).getType().equals("Consumable") && type.equals("Consumable")){
                pItem.setLayout(new GridLayout(0, 3));
                JButton btnUseItem = new JButton();
                btnUseItem.setName(inventData.get(i).getItemID());
                btnUseItem.setText("USE");
                btnUseItem.setBackground(new Color(55, 55 , 55));
                btnUseItem.setForeground(new Color(232, 232, 232));
                btnUseItem.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
                btnUseItem.setHorizontalTextPosition(JButton.CENTER);
                btnUseItem.setHorizontalAlignment(JButton.CENTER);
                btnUseItem.setVisible(true);
                btnUseItem.addActionListener(this);

                pItem.add(btnUseItem);
                pItem.add(lblItemName);
                pItem.add(lblQuantity);
                pInventoryItem.setLayout(new BoxLayout(pInventoryItem, BoxLayout.Y_AXIS));
                pInventoryItem.add(Box.createRigidArea(new Dimension(5, 2)));
                pInventoryItem.add(pItem);
            }
            else if(inventData.get(i).getType().equals("Hunting Item") && type.equals("Hunting Item")){
                pItem.setLayout(new GridLayout(0, 2));
                pItem.add(lblItemName);
                pItem.add(lblQuantity);
                pInventoryItem.setLayout(new BoxLayout(pInventoryItem, BoxLayout.Y_AXIS));
                pInventoryItem.add(Box.createRigidArea(new Dimension(5, 2)));
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
                if(Player.health != Player.totalMaxHealth){
                    Player.heal(consumable.getHealValue());
                    Inventory.updateItem(consumable.getItemID(), -1);
                }
            }
            DataSaver.saveInventoryData();
            loadInventory("Consumable");
            fgm.reload();
        }
    }
}
