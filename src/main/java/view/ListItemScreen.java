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
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import controller.Controller;
import controller.UserManager;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Item;

/**
 *
 * @author luckysetiawan
 */
public class ListItemScreen implements ActionListener {
    private JFrame menu;
    private JLabel labelTitle;
    private JButton btnBack;
    private JTable itemTable;
    private JScrollPane scrollPane;
    private ArrayList<Item> items;

    public ListItemScreen() {
        this.items = Controller.getAllItems(UserManager.getInstance().getUser());
        showListItem();
    }
    
    private void showListItem(){
        menu = new JFrame("List Item");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../iconWH.jpg"));
        menu.setIconImage(icon);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(3, 1));
        
        labelTitle = new JLabel("List Item", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        
        String column[] = {"Item ID", "Supplier ID", "Name", "Quantity", "Size", "Weight", "Price"};
        String data[][] = new String[items.size()][column.length];
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            data[i][0] = "" + item.getItem_id();
            data[i][1] = "" + item.getUid();
            data[i][2] = item.getItem_name();
            data[i][3] = "" + item.getItem_quantity();
            data[i][4] = "" + item.getItem_size() + " cm3";
            data[i][5] = "" + item.getItem_weight() + " gr";
            data[i][6] = "Rp " + item.getItem_price();
        }
        itemTable = new JTable(data, column);
        
        itemTable.setCellSelectionEnabled(true);
        ListSelectionModel select= itemTable.getSelectionModel();  
            select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
            select.addListSelectionListener(new ListSelectionListener() {  
                @Override
                public void valueChanged(ListSelectionEvent e) {  
                    String Data = null;
                    int itemId = 0;
                    int[] row = itemTable.getSelectedRows();

                    for (int i = 0; i < row.length; i++) { 
                        Data = (String) itemTable.getValueAt(row[i], 0); 
                        itemId = Integer.parseInt(Data);
                    }
                    
                    menu.dispose();
                    new UpdateItemScreen(itemId);
                }       
        });
        
        scrollPane = new JScrollPane(itemTable);
        
        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        
        menu.add(labelTitle);
        menu.add(scrollPane);
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
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    
}
