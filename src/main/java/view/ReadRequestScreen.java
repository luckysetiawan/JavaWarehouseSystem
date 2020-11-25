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
import model.Request;
import model.Supplier;
import model.RequestStatus;


/**
 *
 * @author luckysetiawan
 */
public class ReadRequestScreen implements ActionListener {
    private JFrame menu;
    private JLabel labelTitle;
    private JButton btnBack;
    private JTable requestTable;
    private JScrollPane scrollPane;
    private ArrayList<Request> requests;

    public ReadRequestScreen() {
        this.requests = Controller.getAllRequest(UserManager.getInstance().getUser());
        showRequest();
    }
    
    private void showRequest(){
        menu = new JFrame("Requests");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../iconWH.jpg"));
        menu.setIconImage(icon);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(3, 1));
        
        labelTitle = new JLabel("Requests", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        
        String column[] = {"Type", "Supplier ID", "Item ID", "Item Name", "Quantity", "Status"};
        if(UserManager.getInstance().getUser() instanceof Supplier)
            column[1] = "Distributor ID";
        String data[][] = new String[requests.size()][column.length];
        for (int i = 0; i < requests.size(); i++) {
            Request request = requests.get(i);
            data[i][0] = "Take";
            if(request.getReqType() == 1)
                data[i][0] = "Return";
            data[i][1] = "" + request.getSuppId();
            if(UserManager.getInstance().getUser() instanceof Supplier)
                data[i][1] = "" + request.getDistId();
            data[i][2] = "" + request.getItem().getItem_id();
            data[i][3] = request.getItem().getItem_name();
            data[i][4] = "" + request.getItem().getItem_quantity();
            data[i][5] = "Waiting";
            if(request.isIsAccepted() == RequestStatus.ACCEPTED)
                data[i][5] = "Accepted";
            else if(request.isIsAccepted() == RequestStatus.DENIED)
                data[i][5] = "Denied";

        }
        requestTable = new JTable(data, column);
        
        scrollPane = new JScrollPane(requestTable);
        
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
