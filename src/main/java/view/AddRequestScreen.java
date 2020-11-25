/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import controller.Controller;
import controller.UserManager;
import model.Request;
import model.TakenItem;
import model.Supplier;
import model.Distributor;
import model.Item;

/**
 *
 * @author luckysetiawan
 */
public class AddRequestScreen implements ActionListener {
    private JFrame menu;
    private JPanel takeRequest;
    private JTable itemTable;
    private JScrollPane scrollPane;
    private JLabel labelTitle, labelSuppId, labelTakenId, labelItemId, labelQuantity;
    private JButton btnRequest, btnBack;
    private JTextField suppId, takenId, itemId, quantity;
    private ArrayList<TakenItem> takenItems;
    private ArrayList<Item> items;
    private int reqType = 0;

    public AddRequestScreen() {
        takenItems = Controller.getAllTakenItems((Distributor)UserManager.getInstance().getUser());
        items = new ArrayList<>();
        for (int i = 0; i < takenItems.size(); i++) {
           for (int j = 0; j < takenItems.get(i).getListItem().size(); j++) {
              items.add(takenItems.get(i).getListItem().get(j));
           }
       }
        showAddRequest();
    }
    
    private void showAddRequest(){
        menu = new JFrame("Add Request");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(3, 1));
        
        labelTitle = new JLabel("Add a Request", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        
        String column[] = {"Taken ID", "Item ID", "Supplier ID", "Taken Quantity"};
        String data[][] = new String[items.size()][column.length];
        for (int i = 0, k = 0; i < takenItems.size(); i++) {
            for (int j = 0; j < takenItems.get(i).getListItem().size(); j++, k++) {
                Item item = takenItems.get(i).getListItem().get(j);
                data[k][0] = "" + takenItems.get(i).getTaken_id();
                data[k][1] = "" + item.getItem_id();
                data[k][2] = "" + item.getUid();
                data[k][3] = "" + item.getItem_quantity();
            }
        }
        itemTable = new JTable(data, column);
        
        scrollPane = new JScrollPane(itemTable);
        scrollPane.setVisible(false);
        
        takeRequest = new JPanel(new GridLayout(6,2));
        
        JButton btnTake = new JButton("Take Request");
        btnTake.setActionCommand("takeRequest");
        btnTake.addActionListener(this);
        
        JButton btnReturn = new JButton("Return Request");
        btnReturn.setActionCommand("returnRequest");
        btnReturn.addActionListener(this);
        
        labelSuppId = new JLabel("Supplier ID :");
        suppId = new JTextField();
        
        labelTakenId = new JLabel("Taken ID :");
        takenId = new JTextField();
        labelTakenId.setVisible(false);
        takenId.setVisible(false);
        
        labelItemId = new JLabel("Item ID :");
        itemId = new JTextField();
        
        labelQuantity = new JLabel("Item Quantity :");
        quantity = new JTextField();
        
        btnRequest = new JButton("Submit Request");
        btnRequest.setActionCommand("request");
        btnRequest.addActionListener(this);
        
        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        
        takeRequest.add(labelTakenId);
        takeRequest.add(takenId);
        takeRequest.add(labelSuppId);
        takeRequest.add(suppId);
        takeRequest.add(labelItemId);
        takeRequest.add(itemId);
        takeRequest.add(labelQuantity);
        takeRequest.add(quantity);
        takeRequest.add(btnTake);
        takeRequest.add(btnReturn);
        takeRequest.add(btnRequest);
        takeRequest.add(btnBack);
        
        menu.add(labelTitle);
        menu.add(scrollPane);
        menu.add(takeRequest);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "takeRequest" :
                reqType = 0;
                scrollPane.setVisible(false);
                labelTakenId.setVisible(false);
                takenId.setVisible(false);
                break;
            case "returnRequest" :
                reqType = 1;
                scrollPane.setVisible(true);
                labelTakenId.setVisible(true);
                takenId.setVisible(true);
                break;
            case "request":
               int suppId, itemId, takenId = 0, quantity;
               if(reqType == 0){
                  if(!this.suppId.getText().isEmpty() && !this.itemId.getText().isEmpty() && !this.quantity.getText().isEmpty()){
                     boolean validData = true;

                     try {
                         suppId = Integer.parseInt(this.suppId.getText());
                         itemId = Integer.parseInt(this.itemId.getText());
                         quantity = Integer.parseInt(this.quantity.getText());

                         Item item = Controller.getItem(itemId);
                         if(!(Controller.getPerson(suppId, "") instanceof Supplier) || item == null){
                             validData = false;
                             JOptionPane.showMessageDialog(null,"Invalid Data!","Alert",JOptionPane.WARNING_MESSAGE);
                         }else if(quantity < 1 || quantity > item.getItem_quantity()){
                             validData = false;
                             JOptionPane.showMessageDialog(null,"Invalid Quantity!","Alert",JOptionPane.WARNING_MESSAGE);
                         }
                     } catch (Exception ex) {
                         suppId = itemId = quantity = 0;
                         validData = false;
                         JOptionPane.showMessageDialog(null,"Invalid Input Type!","Alert",JOptionPane.WARNING_MESSAGE);
                     }

                     if(validData && Controller.insertRequest(UserManager.getInstance().getUser().getUid(), suppId, quantity, reqType, itemId, takenId)){
                         JOptionPane.showMessageDialog(null,"Request Has Been Sent!","Notification",JOptionPane.INFORMATION_MESSAGE);
                         menu.dispose();
                         new MainMenuScreen();
                     }
                   }else
                       JOptionPane.showMessageDialog(null,"Empty Field Detected!","Alert",JOptionPane.WARNING_MESSAGE);
               }else if(reqType == 1){
                  if(!this.takenId.getText().isEmpty() && !this.suppId.getText().isEmpty() && !this.itemId.getText().isEmpty() && !this.quantity.getText().isEmpty()){
                     boolean validData = true;

                     try {
                         suppId = Integer.parseInt(this.suppId.getText());
                         itemId = Integer.parseInt(this.itemId.getText());
                         quantity = Integer.parseInt(this.quantity.getText());
                         takenId = Integer.parseInt(this.takenId.getText());

                         Item item = Controller.getItem(itemId);
                         if(!(Controller.getPerson(suppId, "") instanceof Supplier) || item == null || Controller.getTakenItem(takenId) == null){
                             validData = false;
                             JOptionPane.showMessageDialog(null,"Invalid Data!","Alert",JOptionPane.WARNING_MESSAGE);
                         }else if(quantity < 1 || quantity > item.getItem_quantity()){
                             validData = false;
                             JOptionPane.showMessageDialog(null,"Invalid Quantity!","Alert",JOptionPane.WARNING_MESSAGE);
                         }
                     } catch (Exception ex) {
                         suppId = itemId = quantity = 0;
                         validData = false;
                         JOptionPane.showMessageDialog(null,"Invalid Input Type!","Alert",JOptionPane.WARNING_MESSAGE);
                     }

                     if(validData && Controller.insertRequest(UserManager.getInstance().getUser().getUid(), suppId, quantity, reqType, itemId, takenId)){
                         JOptionPane.showMessageDialog(null,"Request Has Been Sent!","Notification",JOptionPane.INFORMATION_MESSAGE);
                         menu.dispose();
                         new MainMenuScreen();
                     }
                  }else
                      JOptionPane.showMessageDialog(null,"Empty Field Detected!","Alert",JOptionPane.WARNING_MESSAGE);
               }
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
