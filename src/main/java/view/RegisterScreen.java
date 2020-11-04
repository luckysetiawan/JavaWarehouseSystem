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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author luckysetiawan
 */
public class RegisterScreen implements ActionListener {
    private JFrame menu;
    private JPanel panelUsername, panelPassword;
    private JLabel labelTitle, labelUsername, labelPassword;
    private JTextField username;
    private JPasswordField password;
    private JButton btnRegister;

    public RegisterScreen() {
        showRegisterScreen();
    }
    
    private void showRegisterScreen(){
        menu = new JFrame("Warehouse Register");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(4, 1));
        
        labelTitle = new JLabel("Register", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        menu.add(labelTitle);
        
        panelUsername = new JPanel(new GridLayout(1,2));
        labelPassword = new JLabel("Username: ");
        username = new JTextField();
        panelUsername.add(labelPassword);
        panelUsername.add(username);
        menu.add(panelUsername);
        
        panelPassword = new JPanel(new GridLayout(1,2));
        labelPassword = new JLabel("Password: ");
        password = new JPasswordField("");
        panelPassword.add(labelPassword);
        panelPassword.add(password);
        menu.add(panelPassword);
        
        btnRegister = new JButton("Register");
        btnRegister.setActionCommand("register");
        btnRegister.addActionListener(this);
        menu.add(btnRegister);
        
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "register":
                menu.dispose();
                // validate data
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    
}
