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
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import controller.Controller;
import controller.UserManager;
import java.awt.Image;
import java.awt.Toolkit;
import model.Request;
import model.RequestStatus;
import model.Supplier;
import model.Item;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 *
 * @author luckysetiawan
 */
public class TakeItemScreen implements ActionListener {
    private JFrame menu;
    private JLabel labelTitle;
    private JButton btnTake, btnBack;
    private JPanel panelMenu, panelChooser, panelChooserRow;
    private JScrollPane scrollPane;
    private ArrayList<Request> requests, selectedRequests;
    private ArrayList<Item> items, selectedItems;

    public TakeItemScreen() {
        requests = Controller.getAllRequest(UserManager.getInstance().getUser());
        selectedRequests = new ArrayList<Request>();
        items = new ArrayList<Item>();
        selectedItems = new ArrayList<Item>();
        for (int i = 0; i < requests.size(); i++) {
            if(requests.get(i).getReqType() == 0 && requests.get(i).isIsAccepted() == RequestStatus.ACCEPTED)
                items.add(requests.get(i).getItem());
        }
        showTakeItem();
    }
    
    private void showTakeItem(){
        menu = new JFrame("Take Item");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../iconWH.jpg"));
        menu.setIconImage(icon);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(2, 1));
        
        labelTitle = new JLabel("Take Item", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        
        panelMenu = new JPanel(new GridLayout(3,1));
        panelChooser = new JPanel(new GridLayout(items.size() + 1, 1));
        
        panelChooserRow = new JPanel(new GridLayout(1,4));
        panelChooserRow.add(new JLabel("Check List"));
        panelChooserRow.add(new JLabel("Item ID"));
        panelChooserRow.add(new JLabel("Supplier ID"));
        panelChooserRow.add(new JLabel("Take Quantity"));
        
        panelChooser.add(panelChooserRow);
        
        for (int i = 0; i < items.size(); i++) {
            Request request = requests.get(i);
            Item item = items.get(i);
            panelChooserRow = new JPanel(new GridLayout(1, 4));
            
            JCheckBox checkbox = new JCheckBox();
            checkbox.addItemListener((ItemEvent e) -> {
               if(e.getStateChange() == ItemEvent.SELECTED){
                   selectedItems.add(item);
                   selectedRequests.add(request);
               }else{
                   selectedItems.remove(item);
                   selectedRequests.remove(request);
               }
            });
            
            JLabel itemId = new JLabel("" + item.getItem_id());
            JLabel suppId = new JLabel("" + item.getUid());
            JLabel quantity = new JLabel("" + item.getItem_quantity());
            
            panelChooserRow.add(checkbox);
            panelChooserRow.add(itemId);
            panelChooserRow.add(suppId);
            panelChooserRow.add(quantity);
            
            panelChooser.add(panelChooserRow);
        }
        
        scrollPane = new JScrollPane(panelChooser);
        
        btnTake = new JButton("Take");
        btnTake.setActionCommand("take");
        btnTake.addActionListener(this);
        
        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        
        panelMenu.add(scrollPane);
        panelMenu.add(btnTake);
        panelMenu.add(btnBack);
        
        menu.add(labelTitle);
        menu.add(panelMenu);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "take":
                if(selectedItems.size() != 0){
                    Date dateNow = Date.valueOf(LocalDate.now(ZoneId.systemDefault()));
                    if(Controller.insertTakenItem(UserManager.getInstance().getUser().getUid(), dateNow, selectedItems)){
                        for (int i = 0; i < selectedRequests.size(); i++) {
                            Item item = Controller.getItem(selectedItems.get(i).getItem_id());
                            item.setItem_quantity(item.getItem_quantity() - selectedItems.get(i).getItem_quantity());
                            
                            if(!Controller.updateItem(item))
                               JOptionPane.showMessageDialog(null, "Item Can't Be Updated!","Alert",JOptionPane.WARNING_MESSAGE);
                            
                            if(!Controller.updateDoneRequestStatus(selectedRequests.get(i)))
                               JOptionPane.showMessageDialog(null, "Request Can't Be Done!","Alert",JOptionPane.WARNING_MESSAGE);
                        }
                        JOptionPane.showMessageDialog(null, "Item(s) Has Been Taken!","Notification",JOptionPane.INFORMATION_MESSAGE);
                        menu.dispose();
                        new MainMenuScreen();
                    }
                }else
                   JOptionPane.showMessageDialog(null, "No Item Is Selected!","Alert",JOptionPane.WARNING_MESSAGE);

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
