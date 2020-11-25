/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author luckysetiawan
 */
public class MembershipScreen implements ActionListener {
    private JFrame menu;
    private JLabel labelTitle;
    private JPanel panelButton;
    private JButton btnAccept, btnRevoke, btnBack;

    public MembershipScreen() {
        showMembership();
    }
    
    private void showMembership(){
        menu = new JFrame("Warehouse Membership");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../iconWH.jpg"));
        menu.setIconImage(icon);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(4, 1));
        
        labelTitle = new JLabel("Warehouse Membership", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        
        btnAccept = new JButton("Accept Membership");
        btnAccept.setActionCommand("accMem");
        btnAccept.addActionListener(this);
        
        btnRevoke = new JButton("Revoke Membership");
        btnRevoke.setActionCommand("revMem");
        btnRevoke.addActionListener(this);
        
        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        
        menu.add(labelTitle);
        menu.add(btnAccept);
        menu.add(btnRevoke);
        menu.add(btnBack);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "accMem":
                menu.dispose();
                new AcceptMembershipScreen();
                break;
            case "revMem":
                menu.dispose();
                new RevokeMembershipScreen();
                break;
            case "back":
                menu.dispose();
                new MainMenuScreen();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
        
    }
}
