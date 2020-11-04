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
public class Distributor extends Person{
    //object
    private ArrayList <Request> listRequest;
    private ArrayList <TakenItem> listTakenItems;
   
    
    //deklarasi
    public Distributor(int uid, String username, String password, String email, String address, boolean membership_status){
        super(uid, username, password, email, address, membership_status);
        this.listRequest = listRequest;
        this.listTakenItems = listTakenItems;
    }
    
    //Getter Setter
    public ArrayList<Request> getListRequest() {
        return listRequest;
    }

    public void setListRequest(ArrayList<Request> listRequest) {
        this.listRequest = listRequest;
    }

    public ArrayList<TakenItem> getListTakenItems() {
        return listTakenItems;
    }

    public void setListTakenItems(ArrayList<TakenItem> listTakenItems) {
        this.listTakenItems = listTakenItems;
    }
    
    
    //kerjaan
    public void addRequest(int id, int quantity){
        
    }
}
