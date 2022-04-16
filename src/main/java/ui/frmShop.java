package ui;

import data.DataSaver;
import data.DataStorage;
import engine.Inventory;
import entity.Player;
import items.ShopItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class frmShop extends JDialog implements ActionListener {
    private final frmGameMenu fgm;
    private JPanel pShop;
    private JLabel lblMoney;
    private JPanel pShopItem;
    private JScrollPane pScrollPane;
    private JButton btnBuy;
    private JButton btnSell;
    private JPanel buyHeader;
    private JPanel sellHeader;

    public frmShop(frmGameMenu fgm){
        super(fgm, "Shop", ModalityType.APPLICATION_MODAL);
        this.fgm = fgm;
        initialize();
    }

    private void initialize(){
        lblMoney.setText("Money : $" + Player.money);
        loadBuyItem();
        btnBuyOnClick();
        btnSellOnClick();

        setContentPane(pShop);
        setMinimumSize(pShop.getMinimumSize());
        setMaximumSize(pShop.getMaximumSize());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    private void btnBuyOnClick(){
        btnBuy.addActionListener(e -> {
            loadBuyItem();
        });

        btnBuy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            btnBuy.setBackground(new Color(43, 43 ,43));
            btnSell.setBackground(new Color(103, 103 ,103));
            }
        });
    }

    private void btnSellOnClick(){
        btnSell.addActionListener(e -> {
            buyHeader.setVisible(false);
            sellHeader.setVisible(true);
            loadSellItem();
        });

        btnSell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            btnSell.setBackground(new Color(43, 43 ,43));
            btnBuy.setBackground(new Color(103, 103 ,103));
            }
        });
    }

    private void loadBuyItem(){
        sellHeader.setVisible(false);
        buyHeader.setVisible(true);
        pShopItem.removeAll();
        ArrayList<ShopItem> shopData = DataStorage.LSI;
        for (int i = 0; i<shopData.size(); i++) {
            if(shopData.get(i).isBuyable()){
                JPanel pItem = new JPanel();
                pItem.setLayout(new GridLayout(0, 3));
                pItem.setBackground(new Color(189, 189, 189));
                pItem.setMaximumSize(new Dimension(500, 20));
                pItem.setVisible(true);

                JLabel lblItemName = new JLabel();
                lblItemName.setText(shopData.get(i).getName());
                lblItemName.setForeground(new Color(43, 43, 43));
                lblItemName.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
                lblItemName.setHorizontalAlignment(JLabel.CENTER);
                lblItemName.setMaximumSize(new Dimension(100, 20));
                lblItemName.setVisible(true);

                JLabel lblPrice = new JLabel();
                lblPrice.setText("$"+shopData.get(i).getPrice());
                lblPrice.setForeground(new Color(43, 43, 43));
                lblPrice.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
                lblPrice.setHorizontalAlignment(JLabel.CENTER);
                lblPrice.setMaximumSize(new Dimension(100, 20));
                lblPrice.setVisible(true);

                JButton btnUseItem = new JButton();
                btnUseItem.setName(shopData.get(i).getItemID());
                btnUseItem.setText("BUY");
                btnUseItem.setBackground(new Color(55, 55, 55));
                btnUseItem.setForeground(new Color(232, 232, 232));
                btnUseItem.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
                btnUseItem.setHorizontalAlignment(JButton.CENTER);
                btnUseItem.setMaximumSize(new Dimension(100, 20));
                btnUseItem.setVisible(true);
                btnUseItem.addActionListener(this);

                pItem.add(btnUseItem);
                pItem.add(lblItemName);
                pItem.add(lblPrice);
                pShopItem.setLayout(new BoxLayout(pShopItem, BoxLayout.Y_AXIS));
                pShopItem.add(Box.createRigidArea(new Dimension(5, 2)));
                pShopItem.add(pItem);
            }
        }
        pScrollPane.getViewport().add(pShopItem);
    }

    private void loadSellItem(){
        pShopItem.removeAll();
        ArrayList<Inventory> inventData = DataStorage.LI;
        for (int i = 0; i<inventData.size(); i++) {
            ShopItem si = DataStorage.getShopItem(inventData.get(i).getItemID());
            if(si != null){
                if(si.isSellable()){
                    JPanel pItem = new JPanel();
                    pItem.setLayout(new GridLayout(0, 4));
                    pItem.setBackground(new Color(189, 189, 189));
                    pItem.setMaximumSize(new Dimension(500, 20));
                    pItem.setVisible(true);

                    JLabel lblItemName = new JLabel();
                    lblItemName.setText(si.getName());
                    lblItemName.setForeground(new Color(43, 43, 43));
                    lblItemName.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
                    lblItemName.setHorizontalAlignment(JLabel.CENTER);
                    lblItemName.setMaximumSize(new Dimension(100, 20));
                    lblItemName.setVisible(true);

                    JLabel lblPrice = new JLabel();
                    lblPrice.setText("$"+si.getPrice());
                    lblPrice.setForeground(new Color(43, 43, 43));
                    lblPrice.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
                    lblPrice.setHorizontalAlignment(JLabel.CENTER);
                    lblPrice.setMaximumSize(new Dimension(100, 20));
                    lblPrice.setVisible(true);

                    JLabel lblQuantity = new JLabel();
                    lblQuantity.setText(String.valueOf(inventData.get(i).getQuantity()));
                    lblQuantity.setForeground(new Color(43, 43, 43));
                    lblQuantity.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
                    lblQuantity.setHorizontalAlignment(JLabel.CENTER);
                    lblQuantity.setMaximumSize(new Dimension(100, 20));
                    lblQuantity.setVisible(true);

                    JButton btnUseItem = new JButton();
                    btnUseItem.setName(si.getItemID());
                    btnUseItem.setText("SELL");
                    btnUseItem.setBackground(new Color(55, 55, 55));
                    btnUseItem.setForeground(new Color(232, 232, 232));
                    btnUseItem.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
                    btnUseItem.setHorizontalAlignment(JButton.CENTER);
                    btnUseItem.setMaximumSize(new Dimension(100, 20));
                    btnUseItem.setVisible(true);
                    btnUseItem.addActionListener(this);

                    pItem.add(btnUseItem);
                    pItem.add(lblItemName);
                    pItem.add(lblPrice);
                    pItem.add(lblQuantity);
                    pShopItem.setLayout(new BoxLayout(pShopItem, BoxLayout.Y_AXIS));
                    pShopItem.add(Box.createRigidArea(new Dimension(5, 2)));
                    pShopItem.add(pItem);
                }
            }
        }
        pScrollPane.getViewport().add(pShopItem);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        ShopItem si = DataStorage.getShopItem(button.getName());
        if(si != null){
            if(button.getText().equals("BUY")){
                if(si.getType().equals("Weapon")){
                    if(Player.weapon == null){
                        Player.money -= si.getPrice();
                        Player.weapon = DataStorage.getWeapon(si.getItemID());
                    }
                    else{
                        JOptionPane.showMessageDialog(frmShop.this, "You already have a weapon");
                    }
                }
                else if(si.getType().equals("Armor")){
                    if(Player.armor == null){
                        Player.money -= si.getPrice();
                        Player.armor = DataStorage.getArmor(si.getItemID());
                    }
                    else{
                        JOptionPane.showMessageDialog(frmShop.this, "You already have a armor");
                    }
                }
                else if(si.getType().equals("Consumable")){
                    Player.money -= si.getPrice();
                    Inventory.insertItem(si.getItemID(), si.getType(), 1);
                }
            }
            else{
                Player.money += si.getPrice();
                Inventory.updateItem(si.getItemID(), si.getName(), si.getType(), -1);
                loadSellItem();
            }
            DataSaver.saveInventoryData();
            DataSaver.savePlayerData();
            lblMoney.setText("Money : $" + Player.money);
            fgm.reload();
        }
    }
}
