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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Lucky Setiawan
 */
public class StartingScreen implements ActionListener {
    private JFrame menu;
    private JLabel labelTitle;
    private JButton btnLogin, btnRegister, btnExit;
    
    public StartingScreen() {
        showStartingMenu();
    }
    
    private void showStartingMenu(){
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../iconWH.jpg"));
        Image logo = icon.getScaledInstance(75, 75, Image.SCALE_DEFAULT);

        menu = new JFrame("Warehouse System");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(4, 1));
        menu.setIconImage(icon);
        
        labelTitle = new JLabel(new ImageIcon(logo), SwingConstants.CENTER);
        menu.add(labelTitle);
        
        btnLogin = new JButton("Login");
        btnLogin.setActionCommand("login");
        btnLogin.addActionListener(this);
        menu.add(btnLogin);
        
        btnRegister = new JButton("Register");
        btnRegister.setActionCommand("register");
        btnRegister.addActionListener(this);
        menu.add(btnRegister);
        
        btnExit = new JButton("Exit");
        btnExit.setActionCommand("exit");
        btnExit.addActionListener(this);
        menu.add(btnExit);
        
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "login":
                menu.dispose();
                new LoginScreen();
                break;
            case "register":
                menu.dispose();
                new RegisterScreen();
                break;
            case "exit":
                menu.dispose();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
}
