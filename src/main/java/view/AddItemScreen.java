/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.SwingConstants;

/**
 *
 * @author luckysetiawan
 */
public class AddItemScreen implements ActionListener {
    private JFrame menu;
    private JPanel panelId, panelName, panelQuantity, panelSize, panelWeight, panelPrice;
    private JLabel labelTitle ,labelItemId ,labelItemName,labelItemQuantity,labelItemSize;
    private JLabel labelItemWeight, labelItemPrice, labelItemIsDeleted;
    private JTextField textItemId, textItemName, textItemQuantity, textItemSize;
    private JTextField textItemWeight, textItemPrice;
    private JButton btnBack,btnSubmit;
    Controller control = new Controller();
    
    public AddItemScreen() {
        showAddItem();
    }
    
    private void showAddItem(){
        menu = new JFrame("Add Item");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(2, 1));
        
        labelTitle = new JLabel("Add Item" /*, SwingConstants.CENTER*/);
        labelTitle.setBounds(200,0,100,100);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));

        labelItemId = new JLabel();
        labelItemId.setText("Item Id : ");
        labelItemId.setBounds(10, 30, 100, 100);
        menu.add(labelItemId);
        textItemId = new JTextField();
        textItemId.setBounds(130, 70, 300, 20);
        menu.add(textItemId);
            
        //item name
        labelItemName = new JLabel();
        labelItemName.setText("Item Name : ");
        labelItemName.setBounds(10, 50, 100, 100);
        menu.add(labelItemName);
        textItemName = new JTextField();
        textItemName.setBounds(130, 90, 300, 20);
        menu.add(textItemName);
            
        //item quantity
        labelItemQuantity = new JLabel();
        labelItemQuantity.setText("Item Quantity : ");
        labelItemQuantity.setBounds(10, 70, 100, 100);
        menu.add(labelItemQuantity);
        textItemQuantity = new JTextField();
        textItemQuantity.setBounds(130, 110, 300, 20);
        menu.add(textItemQuantity);
        
        //item size
        labelItemSize = new JLabel();
        labelItemSize.setText("Item Size : ");
        labelItemSize.setBounds(10, 90, 100, 100);
        menu.add(labelItemSize);
        textItemSize = new JTextField();
        textItemSize.setBounds(130, 130, 300, 20);
        menu.add(textItemSize);
        
        //item weight
        labelItemWeight = new JLabel();
        labelItemWeight.setText("Item Weight : ");
        labelItemWeight.setBounds(10, 110, 100, 100);
        menu.add(labelItemWeight);
        textItemWeight = new JTextField();
        textItemWeight.setBounds(130, 150, 300, 20);
        menu.add(textItemWeight);
        
        //item price
        labelItemPrice = new JLabel();
        labelItemPrice.setText("Item Price : ");
        labelItemPrice.setBounds(10, 130, 100, 100);
        menu.add(labelItemPrice);
        textItemPrice = new JTextField();
        textItemPrice.setBounds(130, 170, 300, 20);
        menu.add(textItemPrice);

        btnSubmit = new JButton("Submit");
        btnSubmit.setActionCommand("submit");
        btnSubmit.addActionListener(this);
        btnSubmit.setBounds(20,220,100,20);
        menu.add(btnSubmit);
        
        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.setBounds(130,220,100,20);
        btnBack.addActionListener(this);
        
//        //*red alert
//        JLabel labelItemPrice = new JLabel();
//        labelItemPrice.setText("Data Item Tidak Lengkap");
//        labelItemPrice.setBounds(50, 190, 100, 100);
//        menu.add(labelItemPrice);
//        labelItemPrice.setVisible(false);
                
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
                new MainMenuScreen();
                break;
            case "submit":
                if(textItemId.equals("")||textItemName.equals("")||textItemQuantity.equals("")||
                    textItemSize.equals("")||textItemWeight.equals("")||
                    textItemPrice.equals("")){
//                   strItemIdlabelItemPrice.setVisible(true);
                    JOptionPane.showMessageDialog(null,"Data Item Tidak Lengkap!","Alert",JOptionPane.WARNING_MESSAGE);
                }else{
                    String strItemId = textItemId.getText();
                    String strItemName = textItemName.getText();
                    String intItemQuantity = textItemQuantity.getText();
                    String intItemSize = textItemSize.getText();
                    String intItemWeight = textItemWeight.getText();
                    String intItemPrice = textItemPrice.getText();
                   
//                    Controller.insertItem(strItemId, strItemName, intItemQuantity, intItemSize, intItemWeight, intItemPrice);
                    
                    
                }
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    
}
