/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import controller.Controller;
import controller.UserManager;
import java.awt.Image;
import java.awt.Toolkit;
import model.Request;
import model.RequestStatus;
import model.Supplier;
import model.Item;

/**
 *
 * @author luckysetiawan
 */
public class ReturnItemScreen implements ActionListener {
    private JFrame menu;
    private JLabel labelTitle;
    private JButton btnBack;
    private JPanel panelMenu, panelChooser, panelChooserRow;
    private JScrollPane scrollPane;
    private ArrayList<Request> requests;
    private ArrayList<Item> items;

    public ReturnItemScreen() {
        requests = Controller.getAllRequest(UserManager.getInstance().getUser());
        items = new ArrayList<Item>();
        for (int i = 0; i < requests.size(); i++) {
            if(requests.get(i).getReqType() == 1 && requests.get(i).isIsAccepted() == RequestStatus.ACCEPTED)
                items.add(requests.get(i).getItem());
        }
        showReturnItem();
    }
    
    private void showReturnItem(){
        menu = new JFrame("Return Item");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../iconWH.jpg"));
        menu.setIconImage(icon);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(2, 1));
        
        labelTitle = new JLabel("Return Item", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        
        panelMenu = new JPanel(new GridLayout(2,1));
        panelChooser = new JPanel(new GridLayout(items.size() + 1, 1));
        
        panelChooserRow = new JPanel(new GridLayout(1,4));
        panelChooserRow.add(new JLabel("Item ID"));
        panelChooserRow.add(new JLabel("Supplier ID"));
        panelChooserRow.add(new JLabel("Return Quantity"));
        panelChooserRow.add(new JLabel());
        
        panelChooser.add(panelChooserRow);
        
        for (int i = 0; i < items.size(); i++) {
            Request request = requests.get(i);
            Item item = items.get(i);
            panelChooserRow = new JPanel(new GridLayout(1, 4));
            
            JButton btnReturn = new JButton("Return");
            btnReturn.addActionListener((ActionEvent e) -> {
               if(Controller.updateReturnQuantityTakenItem(request.getTakenId(), item.getItem_id(), item.getItem_quantity())){
                  if(Controller.updateDoneRequestStatus(request)){
                     JOptionPane.showMessageDialog(null, "Item Returned!","Notification",JOptionPane.INFORMATION_MESSAGE);
                     
                     btnReturn.setEnabled(false);
                     btnReturn.setText("Returned");
                  }else
                     JOptionPane.showMessageDialog(null, "Request Can't Be Done!","Alert",JOptionPane.WARNING_MESSAGE);
                  
               }else
                  JOptionPane.showMessageDialog(null, "Failed To Return!","Alert",JOptionPane.WARNING_MESSAGE);
            });
            
            JLabel itemId = new JLabel("" + item.getItem_id());
            JLabel suppId = new JLabel("" + item.getUid());
            JLabel quantity = new JLabel("" + item.getItem_quantity());
            
            panelChooserRow.add(itemId);
            panelChooserRow.add(suppId);
            panelChooserRow.add(quantity);
            panelChooserRow.add(btnReturn);
            
            panelChooser.add(panelChooserRow);
        }
        
        scrollPane = new JScrollPane(panelChooser);
        
        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        
        panelMenu.add(scrollPane);
        panelMenu.add(btnBack);
        
        menu.add(labelTitle);
        menu.add(panelMenu);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "back":
                menu.dispose();
                new MainMenuScreen();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    
}
