package ui;

import data.DataStorage;
import entity.Map;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class frmChangeMap extends JDialog implements ActionListener{
    private final frmGameMenu fgm;
    private JPanel pChangeMap;
    private JLabel lblCurrentMap;
    private JScrollPane pScrollPane;
    private JPanel pMaps;

    public frmChangeMap(frmGameMenu fgm){
        super(fgm, "Change Map", ModalityType.APPLICATION_MODAL);
        this.fgm = fgm;
        initialize();
    }

    private void initialize(){
        lblCurrentMap.setText("Current Map : " + Objects.requireNonNull(DataStorage.getMap(Player.mapID)).getMapName());
        loadButton();
        btnExit();

        setContentPane(pChangeMap);
        setMinimumSize(pChangeMap.getMinimumSize());
        setMaximumSize(pChangeMap.getMaximumSize());
        setResizable(false);
        setLocationRelativeTo(fgm);
        setVisible(true);
        pack();
    }

    private void btnExit(){
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                fgm.setEnabled(true);
            }
        });
    }

    private void loadButton(){
        pMaps.removeAll();
        ArrayList<Map> mapData = DataStorage.LM;
        for (int i = 0; i<mapData.size(); i++){
            JButton button = new JButton();
            button.setName(mapData.get(i).getMapID());
            button.setText(mapData.get(i).getMapName());
            button.setSize(new Dimension(258, 54));
            button.setBackground(new Color(232, 232, 232));
            button.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            button.setVisible(true);
            if(mapData.get(i).getStatus().equals("Lock")){
                button.setEnabled(false);
            }
            button.addActionListener(this);

            pMaps.setLayout(new GridLayout(i+1, 0));
            pMaps.add(button);
        }
        pScrollPane.getViewport().add(pMaps);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if(button.getName().equals(Player.mapID)){
            Player.mapID = button.getName();
            lblCurrentMap.setText("Current Map : " + Objects.requireNonNull(DataStorage.getMap(Player.mapID)).getMapName());
        }
    }
}