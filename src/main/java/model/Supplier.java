/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ojan
 */
public class Supplier extends Person{
    //object
    private ArrayList <Item> listItems;
    
    //deklarasi
    public Supplier(int uid, String username, String password, String email, String address, boolean membership_status){
        super(uid, username, password, email, address, membership_status);
        this.listItems = listItems;
    }
    
    //Getter Setter
    public ArrayList<Item> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<Item> listItems) {
        this.listItems = listItems;
    }
    
    
    //kerjaan
    public void createItem(String nama, int quantity, int price, int size, int weight){
        
    }
    
    public void updateItem(int id, int quantity){
        
    }
    
}
