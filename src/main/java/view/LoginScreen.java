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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.Person;
import controller.Controller;
import controller.UserManager;

/**
 *
 * @author luckysetiawan
 */
public class LoginScreen implements ActionListener {
    private JFrame menu;
    private JPanel panelUsername, panelPassword, panelButton;
    private JLabel labelTitle, labelUsername, labelPassword;
    private JTextField username;
    private JPasswordField password;
    private JButton btnLogin, btnBack;

    public LoginScreen() {
        showLoginScreen();
    }
    
    private void showLoginScreen(){
        menu = new JFrame("Warehouse Login");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(4, 1));
        
        labelTitle = new JLabel("Login", SwingConstants.CENTER);
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
        
        panelButton = new JPanel(new GridLayout(1,2));
        btnLogin = new JButton("Login");
        btnLogin.setActionCommand("login");
        btnLogin.addActionListener(this);
        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        panelButton.add(btnBack);
        panelButton.add(btnLogin);
        menu.add(panelButton);
        
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "login":
                // validate data
                boolean validateData = false;
                if(!this.username.getText().isEmpty() && !this.password.getText().isEmpty()){
                    Person user = Controller.getPerson(0, this.username.getText());
                    if(user != null && this.password.getText().equals(user.getPassword())){
                        if(user.isMembership_status()){
                            validateData = true;
                            //set user to user manager
                            UserManager.getInstance().setUser(user);
                        }
                    }
                    if(!validateData)
                        JOptionPane.showMessageDialog(null,"Login Failed! Username or Password Doesn't Match!","Alert",JOptionPane.WARNING_MESSAGE);
                    
                }else if(this.username.getText().isEmpty())
                    JOptionPane.showMessageDialog(null,"Login Failed! Empty Username Detected!","Alert",JOptionPane.WARNING_MESSAGE);
                else if(this.password.getText().isEmpty())
                    JOptionPane.showMessageDialog(null,"Login Failed! Empty Password Detected!","Alert",JOptionPane.WARNING_MESSAGE);
                
                if(validateData){
                    JOptionPane.showMessageDialog(null,"Login Success! Welcome To WAREHOUSE!","Notification",JOptionPane.INFORMATION_MESSAGE);
                    menu.dispose();
                    new MainMenuScreen();
                }else
                    password.setText("");
                break;
            case "back":
                menu.dispose();
                new StartingScreen();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    
}
