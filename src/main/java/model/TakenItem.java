/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Lucky Setiawan
 */
public class TakenItem {
    private int taken_id;
    private ArrayList <Item> listItem;
    private int uid, item_quantity;
    private String date;

    public TakenItem(int taken_id, ArrayList<Item> listItem, int uid, int item_quantity, String date) {
        this.taken_id = taken_id;
        this.listItem = listItem;
        this.uid = uid;
        this.item_quantity = item_quantity;
        this.date = date;
    }

    public int getTaken_id() {
        return taken_id;
    }

    public void setTaken_id(int taken_id) {
        this.taken_id = taken_id;
    }

    public ArrayList<Item> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<Item> listItem) {
        this.listItem = listItem;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
