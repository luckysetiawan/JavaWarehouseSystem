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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import controller.UserManager;
import controller.Controller;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Person;
import model.Admin;
import model.Supplier;
import model.Distributor;

/**
 *
 * @author luckysetiawan
 */
public class AcceptMembershipScreen implements ActionListener {
    private ArrayList<Person> persons, nonMembers;
    private JTable personTable;
    private JScrollPane scrollPaneTable;
    private JFrame menu;
    private JLabel labelTitle;
    private JButton btnBack;

    public AcceptMembershipScreen() {
        persons = Controller.getAllPersons();
        nonMembers = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) {
          if(!(persons.get(i) instanceof Admin) && !persons.get(i).isMembership_status())
             nonMembers.add(persons.get(i));
       }
        showAcceptMembership();
    }
    
    private void showAcceptMembership(){
        menu = new JFrame("Accept Membership");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("../iconWH.jpg"));
        menu.setIconImage(icon);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(3, 1));
        
        labelTitle = new JLabel("Accept Membership", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        
        String column[] = {"User ID", "User Type", "Username", "Email", "Address"};
        String data[][] = new String[nonMembers.size()][column.length];
        for (int i = 0; i < nonMembers.size(); i++) {
            Person person = nonMembers.get(i);
            data[i][0] = "" + person.getUid();
            if(person instanceof Supplier)
               data[i][1] = "Supplier";
            else if(person instanceof Distributor)
               data[i][1] = "Distributor";
            data[i][2] = person.getUsername();
            data[i][3] = person.getEmail();
            data[i][4] = person.getAddress();
        }
        personTable = new JTable(data, column);
        
        personTable.setCellSelectionEnabled(true);
        ListSelectionModel select= personTable.getSelectionModel();  
            select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
            select.addListSelectionListener(new ListSelectionListener() {  
                @Override
                public void valueChanged(ListSelectionEvent e) {  
                    String Data = null;
                    int userId = 0;
                    int[] row = personTable.getSelectedRows();

                    for (int i = 0; i < row.length; i++) { 
                        Data = (String) personTable.getValueAt(row[i], 0);
                        userId = Integer.parseInt(Data);
                    }
                    
                    Person person = persons.get(userId);
                    person.setMembership_status(true);
                    Controller.updatePerson(person);
                    JOptionPane.showMessageDialog(null,"Membership Activated!","Notification",JOptionPane.INFORMATION_MESSAGE);
                    menu.dispose();
                    new MembershipScreen();
                }       
        });
            
        scrollPaneTable = new JScrollPane(personTable);

        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        
        menu.add(labelTitle);
        menu.add(scrollPaneTable);
        menu.add(btnBack);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "back":
                menu.dispose();
                new MembershipScreen();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
    
}
