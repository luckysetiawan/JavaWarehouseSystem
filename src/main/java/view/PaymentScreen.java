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
import javax.swing.SwingConstants;

/**
 *
 * @author luckysetiawan
 */
public class PaymentScreen implements ActionListener {
    private JFrame menu;
    private JPanel panelPaymentMethod, panelButton;
    private JLabel labelTitle, labelPaymentMethod;
    private JComboBox paymentMethod;
    private JButton btnPay, btnBack;
    
    public PaymentScreen() {
        showPaymentScreen();
    }
    
    private void showPaymentScreen(){
        menu = new JFrame("Initiation Payment");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(512, 256);
        menu.setLayout(new GridLayout(3, 1));
        
        labelTitle = new JLabel("Payment", SwingConstants.CENTER);
        labelTitle.setFont(new Font("Georia", Font.BOLD, 20));
        menu.add(labelTitle);
        
        String listPayment[] = {"M-Banking", "Transfer", "GoPay", "OVO", "Alfamart", "Indomart"};
        panelPaymentMethod = new JPanel(new GridLayout(1,2));
        labelPaymentMethod = new JLabel("Choose payment method : ");
        paymentMethod = new JComboBox(listPayment);
        panelPaymentMethod.add(labelPaymentMethod);
        panelPaymentMethod.add(paymentMethod);
        menu.add(panelPaymentMethod);
        
        panelButton = new JPanel(new GridLayout(1,2));
        btnPay = new JButton("Pay");
        btnPay.setActionCommand("pay");
        btnPay.addActionListener(this);
        btnBack = new JButton("Back");
        btnBack.setActionCommand("back");
        btnBack.addActionListener(this);
        panelButton.add(btnBack);
        panelButton.add(btnPay);
        menu.add(panelButton);
        
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "pay":
                // validate data
                boolean validationResult = true;
                if(validationResult){
                    menu.dispose();
                   JOptionPane.showMessageDialog(null,"Register Successful!\n Please Login!","Notification",JOptionPane.INFORMATION_MESSAGE);
                   new LoginScreen();
                }else{
                   JOptionPane.showMessageDialog(null,"Register Failed!","Alert",JOptionPane.WARNING_MESSAGE);
                }
                break;
            case "back":
                menu.dispose();
                new RegisterScreen();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
        }
    }
}
