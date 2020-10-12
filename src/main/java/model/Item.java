/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lucky Setiawan
 */
public class Item {
    private int item_id;
    private String item_name;
    private int item_quantity, item_size, item_weight, item_price;
    private boolean is_deleted;

    public Item(int item_id, String item_name, int item_quantity, int item_size, int item_weight, int item_price, boolean is_deleted) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_quantity = item_quantity;
        this.item_size = item_size;
        this.item_weight = item_weight;
        this.item_price = item_price;
        this.is_deleted = is_deleted;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public int getItem_size() {
        return item_size;
    }

    public void setItem_size(int item_size) {
        this.item_size = item_size;
    }

    public int getItem_weight() {
        return item_weight;
    }

    public void setItem_weight(int item_weight) {
        this.item_weight = item_weight;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
    
}
