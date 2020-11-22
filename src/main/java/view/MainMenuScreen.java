/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author luckysetiawan
 */
public class MainMenuScreen implements ActionListener {
    private JFrame menu;
    private JLabel labelTitle;
    private JPanel panelButton1, panelButton2;
    private JButton btnReadRequest, btnCreateRequest, btnTakeItem, btnReturnItem;
    private JButton btnMembership, btnReadList, btnCreateItem, btnUpdateItem;
    private JButton btnLogout;

    public MainMenuScreen() {
        showMainMenu();
    }
    
    private void showMainMenu(){
        menu = new JFrame("Warehouse Main Menu");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        
        labelTitle = new JLabel("Main Menu", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        
        if(true){// if admin
            btnMembership = new JButton("Membership");
            btnMembership.setActionCommand("membership");
            btnMembership.addActionListener(this);
        }
        
        // All user can read list item
        btnReadList = new JButton("Read List Item");
        btnReadList.setActionCommand("readList");
        btnReadList.addActionListener(this);
        
        if(true){// if supplier
            btnCreateItem = new JButton("Add Item");
            btnCreateItem.setActionCommand("addItem");
            btnCreateItem.addActionListener(this);
            
            btnUpdateItem = new JButton("Update Item");
            btnUpdateItem.setActionCommand("updateItem");
            btnUpdateItem.addActionListener(this);
        }
        
        if(true){// if supplier and distributor
            btnReadRequest = new JButton("View Requested Item");
            btnReadRequest.setActionCommand("readRequest");
            btnReadRequest.addActionListener(this);
        }
        
        if(true){// if distributor
            btnCreateRequest = new JButton("Request Item");
            btnCreateRequest.setActionCommand("addRequest");
            btnCreateRequest.addActionListener(this);
            
            btnTakeItem = new JButton("Take Item");
            btnTakeItem.setActionCommand("takeItem");
            btnTakeItem.addActionListener(this);
            
            btnReturnItem = new JButton("Return Item");
            btnReturnItem.setActionCommand("returnItem");
            btnReturnItem.addActionListener(this);
        }
        
        // All User can logout
        btnLogout = new JButton("Logout");
        btnLogout.setActionCommand("logout");
        btnLogout.addActionListener(this);
        
        menu.add(labelTitle);
        
        if(true){ // admin
            menu.setLayout(new GridLayout(2, 1));
            panelButton1 = new JPanel(new GridLayout(1,3));
            panelButton1.add(btnMembership);
            panelButton1.add(btnReadList);
            panelButton1.add(btnLogout);
            menu.add(panelButton1);
        }else if(true){ // supplier
            menu.setLayout(new GridLayout(3, 1));
            panelButton1 = new JPanel(new GridLayout(1,3));
            panelButton2 = new JPanel(new GridLayout(1,3));
            panelButton1.add(btnReadList);
            panelButton1.add(btnCreateItem);
            panelButton1.add(btnUpdateItem);
            panelButton2.add(btnReadRequest);
            panelButton2.add(new JLabel(""));
            panelButton2.add(btnLogout);
            menu.add(panelButton1);
            menu.add(panelButton2);
        }else if(true) { // distributor
            menu.setLayout(new GridLayout(3, 1));
            panelButton1 = new JPanel(new GridLayout(1,6));
            panelButton2 = new JPanel(new GridLayout(1,3));
            panelButton1.add(btnReadList);
            panelButton1.add(btnReadRequest);
            panelButton1.add(btnCreateRequest);
            panelButton2.add(btnTakeItem);
            panelButton2.add(btnReturnItem);
            panelButton2.add(btnLogout);
            menu.add(panelButton1);
            menu.add(panelButton2);
        }
        
        menu.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "membership":
                menu.dispose();
                new MembershipScreen();
                break;
            case "readList":
                menu.dispose();
                new ListItemScreen();
                break;
            case "addItem":
                menu.dispose();
                new StartingScreen();
                break;
            case "updateItem":
                menu.dispose();
                new StartingScreen();
                break;
            case "readRequest":
                menu.dispose();
                new StartingScreen();
                break;
            case "addRequest":
                menu.dispose();
                new StartingScreen();
                break;
            case "takeItem":
                menu.dispose();
                new StartingScreen();
                break;
            case "returnItem":
                menu.dispose();
                new StartingScreen();
                break;
            case "logout":
                // validate data
                boolean validationResult = true;
                if(validationResult){
                    menu.dispose();
                   JOptionPane.showMessageDialog(null,"Logout Successful!","Notification",JOptionPane.INFORMATION_MESSAGE);
                   new StartingScreen();
                }else{
                   JOptionPane.showMessageDialog(null,"Logout Failed!","Alert",JOptionPane.WARNING_MESSAGE);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    
}
