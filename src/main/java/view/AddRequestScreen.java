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
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import controller.Controller;
import controller.UserManager;
import model.Request;
import model.Supplier;
import model.Item;

/**
 *
 * @author luckysetiawan
 */
public class AddRequestScreen implements ActionListener {
    private JFrame menu;
    private JPanel takeRequest;
    private JLabel labelTitle, labelSuppId, labelItemId, labelQuantity;
    private JButton btnRequest, btnBack;
    private JTextField suppId, itemId, quantity;

    public AddRequestScreen() {
        showAddRequest();
    }
    
    private void showAddRequest(){
        menu = new JFrame("Add Request");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(2, 1));
        
        labelTitle = new JLabel("Add a Request", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        
        takeRequest = new JPanel(new GridLayout(4,2));
        
        labelSuppId = new JLabel("Supplier ID :");
        suppId = new JTextField();
        
        labelItemId = new JLabel("Item ID :");
        itemId = new JTextField();
        
        labelQuantity = new JLabel("Item Quantity :");
        quantity = new JTextField();
        
        btnRequest = new JButton("Request Item");
        btnRequest.setActionCommand("request");
        btnRequest.addActionListener(this);
        
        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        
        takeRequest.add(labelSuppId);
        takeRequest.add(suppId);
        takeRequest.add(labelItemId);
        takeRequest.add(itemId);
        takeRequest.add(labelQuantity);
        takeRequest.add(quantity);
        takeRequest.add(btnRequest);
        takeRequest.add(btnBack);
        
        menu.add(labelTitle);
        menu.add(takeRequest);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "request":
                if(!this.suppId.getText().isEmpty() && !this.itemId.getText().isEmpty() && !this.quantity.getText().isEmpty()){
                    boolean validData = true;
                    int suppId, itemId, quantity;
                    
                    try {
                        suppId = Integer.parseInt(this.suppId.getText());
                        itemId = Integer.parseInt(this.itemId.getText());
                        quantity = Integer.parseInt(this.quantity.getText());
                        
                        Item item = Controller.getItem(itemId);
                        if(!(Controller.getPerson(suppId, "") instanceof Supplier) || item == null){
                            validData = false;
                            JOptionPane.showMessageDialog(null,"Invalid Data!","Alert",JOptionPane.WARNING_MESSAGE);
                        }else if(quantity > item.getItem_quantity()){
                            validData = false;
                            JOptionPane.showMessageDialog(null,"Invalid Quantity!","Alert",JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (Exception ex) {
                        suppId = itemId = quantity = 0;
                        validData = false;
                        JOptionPane.showMessageDialog(null,"Invalid Input Type!","Alert",JOptionPane.WARNING_MESSAGE);
                    }
                    
                    if(validData && Controller.insertRequest(UserManager.getInstance().getUser().getUid(), suppId, quantity, 0, itemId, 0)){
                        JOptionPane.showMessageDialog(null,"Request Has Been Sent!","Notification",JOptionPane.INFORMATION_MESSAGE);
                        menu.dispose();
                        new MainMenuScreen();
                    }
                }else
                    JOptionPane.showMessageDialog(null,"Empty Field Detected!","Alert",JOptionPane.WARNING_MESSAGE);
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
