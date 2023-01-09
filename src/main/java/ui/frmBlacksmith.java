package ui;

import data.DataStorage;
import engine.Blacksmith;
import engine.Inventory;
import items.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Objects;

public class frmBlacksmith extends JDialog implements ActionListener {
    private final frmGameMenu fgm;
    private boolean material1 = false;
    private boolean material2 = false;
    private boolean material3 = false;
    private JPanel pBlacksmith;
    private JScrollPane pScrollPane;
    private JPanel pCraftItem;
    private JButton btnCraft;
    private JLabel lblItemName;
    private JLabel lblMaterial1;
    private JLabel lblMaterial2;
    private JLabel lblMaterial3;

    public frmBlacksmith(frmGameMenu fgm){
        super(fgm, "Blacksmith", ModalityType.APPLICATION_MODAL);
        this.fgm = fgm;
        initialize();
    }

    private void initialize(){
        loadCraftItem();
        btnCraftItem();

        setContentPane(pBlacksmith);
        setMinimumSize(pBlacksmith.getMinimumSize());
        setMaximumSize(pBlacksmith.getMaximumSize());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    private void loadCraftItem(){
        try{
            pCraftItem.removeAll();
            ArrayList<Blacksmith> blacksmithData = DataStorage.LBS;
            for (int i = 0; i<blacksmithData.size(); i++) {
                JPanel pItem = new JPanel();
                pItem.setLayout(new GridLayout(0, 3));
                pItem.setBackground(new Color(189, 189, 189));
                pItem.setMaximumSize(new Dimension(500, 20));
                pItem.setVisible(true);

                JLabel lblCraftItem = new JLabel();
                lblCraftItem.setText(Blacksmith.getCraftItemName(blacksmithData.get(i).getItemID()));
                lblCraftItem.setForeground(new Color(43, 43, 43));
                lblCraftItem.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
                lblCraftItem.setHorizontalAlignment(JLabel.CENTER);
                lblCraftItem.setVisible(true);

                JLabel lblCost = new JLabel();
                lblCost.setText("$"+blacksmithData.get(i).getCost());
                lblCost.setForeground(new Color(43, 43, 43));
                lblCost.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
                lblCost.setHorizontalAlignment(JLabel.CENTER);
                lblCost.setVisible(true);

                JButton btnUseItem = new JButton();
                btnUseItem.setName(blacksmithData.get(i).getItemID());
                btnUseItem.setText("SELECT");
                btnUseItem.setBackground(new Color(55, 55, 55));
                btnUseItem.setForeground(new Color(232, 232, 232));
                btnUseItem.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
                btnUseItem.setHorizontalAlignment(JButton.CENTER);
                btnUseItem.setVisible(true);
                btnUseItem.addActionListener(this);

                pItem.add(btnUseItem);
                pItem.add(lblCraftItem);
                pItem.add(lblCost);
                pCraftItem.setLayout(new BoxLayout(pCraftItem, BoxLayout.Y_AXIS));
                pCraftItem.add(Box.createRigidArea(new Dimension(5, 2)));
                pCraftItem.add(pItem);
            }
            pScrollPane.getViewport().add(pCraftItem);
        }
        catch (Exception ex){
            System.err.println("Something went wrong in loadCraftItem: " + ex);
        }
    }

    private void btnCraftItem(){
        try{
            btnCraft.addActionListener(e -> {
                if(material1){
                    if(material2){
                        if(material3){
                            int confirmation = JOptionPane.showConfirmDialog(null, "Do you want to craft this?", "Select an option...", JOptionPane.YES_NO_OPTION);
                            if (confirmation == 0) {
                                Blacksmith.craftUIValidation(this, btnCraft.getName());
                                fgm.reload();
                                this.dispatchEvent(new WindowEvent(frmBlacksmith.this, WindowEvent.WINDOW_CLOSING));
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(frmBlacksmith.this, "Material isn't enough to craft");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(frmBlacksmith.this, "Material isn't enough to craft");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frmBlacksmith.this, "Material isn't enough to craft");
                }
            });
        }
        catch (Exception ex){
            System.err.println("Something went wrong in btnCraftItem : " + ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            material1 = false;
            material2 = false;
            material3 = false;
            JButton button = (JButton) e.getSource();
            btnCraft.setName(button.getName());
            Blacksmith bs = DataStorage.getBlacksmithItem(button.getName());
            if(bs != null){
                HuntingItem h1 = DataStorage.getHuntingItem(bs.getMaterial1ID());
                HuntingItem h2 = DataStorage.getHuntingItem(bs.getMaterial2ID());
                HuntingItem h3 = DataStorage.getHuntingItem(bs.getMaterial3ID());
                Inventory inventItem1 = DataStorage.getInventory(Objects.requireNonNull(h1).getItemID());
                Inventory inventItem2 = DataStorage.getInventory(Objects.requireNonNull(h2).getItemID());
                Inventory inventItem3 = DataStorage.getInventory(Objects.requireNonNull(h3).getItemID());

                lblItemName.setText(Blacksmith.getCraftItemName(bs.getItemID()));

                if(inventItem1 != null){
                    lblMaterial1.setText(inventItem1.getQuantity() +
                            "/" + bs.getMaterial1Quantity() + " " +
                            Objects.requireNonNull(h1).getName());
                    if(inventItem1.getQuantity() >= bs.getMaterial1Quantity()){
                        material1 = true;
                    }

                }
                else{
                    lblMaterial1.setText(0 +
                            "/" + bs.getMaterial1Quantity() + " " +
                            Objects.requireNonNull(h1).getName());
                }

                if(inventItem2 != null){
                    lblMaterial2.setText(inventItem2.getQuantity() +
                            "/" + bs.getMaterial2Quantity() + " " +
                            Objects.requireNonNull(h2).getName());
                    if(inventItem2.getQuantity() >= bs.getMaterial2Quantity()){
                        material2 = true;
                    }
                }
                else{
                    lblMaterial2.setText(0 +
                            "/" + bs.getMaterial2Quantity() + " " +
                            Objects.requireNonNull(h2).getName());
                }

                if(inventItem3 != null){
                    lblMaterial3.setText(inventItem3.getQuantity() +
                            "/" + bs.getMaterial3Quantity() + " " +
                            Objects.requireNonNull(h3).getName());
                    if(inventItem3.getQuantity() >= bs.getMaterial3Quantity()){
                        material3 = true;
                    }
                }
                else{
                    lblMaterial3.setText(0 +
                            "/" + bs.getMaterial3Quantity() + " " +
                            Objects.requireNonNull(h3).getName());
                }
            }
        }
        catch (Exception ex){
            System.err.println("Something went wrong in frmBlacksmithActionPerformed: " + ex);
        }
    }
}
