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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private JPanel panelUsername, panelPassword, panelEmail, panelAddress, panelType, panelButton;
    private JLabel labelTitle, labelUsername, labelPassword, labelEmail, labelAddress, labelType;
    private JTextField username, email, address;
    private JComboBox type;
    private JPasswordField password;
    private JButton btnRegister, btnBack;

    public RegisterScreen() {
        showRegisterScreen();
    }
    
    private void showRegisterScreen(){
        menu = new JFrame("Warehouse Register");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(7, 1));
        
        labelTitle = new JLabel("Register", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        menu.add(labelTitle);
        
        panelUsername = new JPanel(new GridLayout(1,2));
        labelPassword = new JLabel("Username : ");
        username = new JTextField();
        panelUsername.add(labelPassword);
        panelUsername.add(username);
        menu.add(panelUsername);
        
        panelPassword = new JPanel(new GridLayout(1,2));
        labelPassword = new JLabel("Password : ");
        password = new JPasswordField("");
        panelPassword.add(labelPassword);
        panelPassword.add(password);
        menu.add(panelPassword);
        
        panelEmail = new JPanel(new GridLayout(1,2));
        labelEmail = new JLabel("E-mail : ");
        email = new JTextField();
        panelEmail.add(labelEmail);
        panelEmail.add(email);
        menu.add(panelEmail);
        
        panelAddress = new JPanel(new GridLayout(1,2));
        labelAddress = new JLabel("Address : ");
        address = new JTextField();
        panelAddress.add(labelAddress);
        panelAddress.add(address);
        menu.add(panelAddress);
        
        String listType[] = {"Supplier", "Distributor"};
        panelType = new JPanel(new GridLayout(1,2));
        labelType = new JLabel("Type : ");
        type = new JComboBox(listType);
        panelType.add(labelType);
        panelType.add(type);
        menu.add(panelType);
        
        panelButton = new JPanel(new GridLayout(1,2));
        btnRegister = new JButton("Register");
        btnRegister.setActionCommand("register");
        btnRegister.addActionListener(this);
        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        panelButton.add(btnBack);
        panelButton.add(btnRegister);
        menu.add(panelButton);
        
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "register":
                // validate data + payment
                boolean validationResult = true;
                if(validationResult){
                    menu.dispose();
                   JOptionPane.showMessageDialog(null,"Please immediately do the initiation payment to use your account!","Notification",JOptionPane.INFORMATION_MESSAGE);
                   new PaymentScreen();
                }else{
                   JOptionPane.showMessageDialog(null,"Please fill all field correctly!","Alert",JOptionPane.WARNING_MESSAGE);
                }
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
