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
    private JPanel panelButtonA, panelButtonB;
    private JLabel labelTitle;
    private JButton btnMembership, btnReadList, btnLogout;

    public MainMenuScreen() {
        showMainMenu();
    }
    
    private void showMainMenu(){
        menu = new JFrame("Warehouse Main Menu");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(2, 1));
        
        labelTitle = new JLabel("Main Menu", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        menu.add(labelTitle);
        
        panelButtonA = new JPanel(new GridLayout(1,3));
        
        btnMembership = new JButton("Membership");
        btnMembership.setActionCommand("membership");
        btnMembership.addActionListener(this);
        
        btnReadList = new JButton("Read List Item");
        btnReadList.setActionCommand("readList");
        btnReadList.addActionListener(this);
        
        btnLogout = new JButton("Logout");
        btnLogout.setActionCommand("logout");
        btnLogout.addActionListener(this);
        
        panelButtonA.add(btnMembership);
        panelButtonA.add(btnReadList);
        panelButtonA.add(btnLogout);
        
        menu.add(panelButtonA);
        
        menu.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
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
            case "membership":
                menu.dispose();
                // new MembershipScreen();
                new StartingScreen();
                break;
            case "readList":
                // new ReadListItemScreen();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    
}
