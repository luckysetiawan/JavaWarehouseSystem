/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import controller.UserManager;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.Item;

/**
 *
 * @author luckysetiawan
 */
public class AddItemScreen implements ActionListener {
    private JFrame menu;
    private JPanel panelName, panelQuantity, panelSize, panelWeight, panelPrice, panelButton;
    private JLabel labelTitle ,labelName,labelQuantity,labelSize;
    private JLabel labelWeight, labelPrice;
    private JTextField textName, textQuantity, textSize, textWeight, textPrice;
    private JButton btnBack,btnSubmit;
    
    public AddItemScreen() {
        showAddItem();
    }
    
    private void showAddItem(){
        menu = new JFrame("Add Item");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../iconWH.jpg"));
        menu.setIconImage(icon);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(7, 1));
        
        labelTitle = new JLabel("Add Item", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        
        panelName = new JPanel(new GridLayout(1, 2));
        labelName = new JLabel("Name : ");
        textName = new JTextField();
        panelName.add(labelName);
        panelName.add(textName);
        
        panelQuantity = new JPanel(new GridLayout(1, 2));
        labelQuantity = new JLabel("Quantity : ");
        textQuantity = new JTextField();
        panelQuantity.add(labelQuantity);
        panelQuantity.add(textQuantity);
        
        panelSize = new JPanel(new GridLayout(1, 2));
        labelSize = new JLabel("Size : ");
        textSize = new JTextField();
        panelSize.add(labelSize);
        panelSize.add(textSize);
        
        panelWeight = new JPanel(new GridLayout(1, 2));
        labelWeight = new JLabel("Weight : ");
        textWeight = new JTextField();
        panelWeight.add(labelWeight);
        panelWeight.add(textWeight);
        
        panelPrice = new JPanel(new GridLayout(1, 2));
        labelPrice = new JLabel("Price : ");
        textPrice = new JTextField();
        panelPrice.add(labelPrice);
        panelPrice.add(textPrice);
        
        panelButton = new JPanel(new GridLayout(1, 2));
        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        btnSubmit = new JButton("Submit");
        btnSubmit.setActionCommand("submit");
        btnSubmit.addActionListener(this);
        panelButton.add(btnBack);
        panelButton.add(btnSubmit);
        
        menu.add(labelTitle);
        menu.add(panelName);
        menu.add(panelQuantity);
        menu.add(panelSize);
        menu.add(panelWeight);
        menu.add(panelPrice);
        menu.add(panelButton);
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
            case "submit":
                String name = textName.getText();
                String quantity = textQuantity.getText();
                String size = textSize.getText();
                String weight = textWeight.getText();
                String price = textPrice.getText();
                
                if(name.equals("") || quantity.equals("") || size.equals("") || 
                        weight.equals("") || price.equals("")){
                    JOptionPane.showMessageDialog(null,"Data Item Tidak Lengkap!","Alert",JOptionPane.WARNING_MESSAGE);
                }else{
                    int quantityNum = Integer.parseInt(textQuantity.getText());
                    int sizeNum = Integer.parseInt(textSize.getText());
                    int weightNum = Integer.parseInt(textWeight.getText());
                    int priceNum = Integer.parseInt(textPrice.getText());
                    
                    Controller.insertItem(UserManager.getInstance().getUser().getUid(), name, quantityNum, sizeNum, weightNum, priceNum);
                    JOptionPane.showMessageDialog(null,"Item succesfully added!","Notification",JOptionPane.INFORMATION_MESSAGE);
                }
                menu.dispose();
                new MainMenuScreen();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    
}
