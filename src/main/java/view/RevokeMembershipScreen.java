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
import javax.swing.SwingConstants;

/**
 *
 * @author luckysetiawan
 */
public class RevokeMembershipScreen implements ActionListener {
    private JFrame menu;
    private JLabel labelTitle;
    private JButton btnBack;

    public RevokeMembershipScreen() {
        showRevokeMembership();
    }
    
    private void showRevokeMembership(){
        menu = new JFrame("Revoke Membership");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(2, 1));
        
        labelTitle = new JLabel("Revoke Membership", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        
        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        
        menu.add(labelTitle);
        menu.add(btnBack);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "back":
                menu.dispose();
                new MembershipScreen();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    
}
