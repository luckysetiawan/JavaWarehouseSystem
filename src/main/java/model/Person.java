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
public abstract class Person {
    private int uid;
    private String username, password, email, address;
    private boolean membership_status;

    public Person(int uid, String username, String password, String email, String address, boolean membership_status) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.membership_status = membership_status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isMembership_status() {
        return membership_status;
    }

    public void setMembership_status(boolean membership_status) {
        this.membership_status = membership_status;
    }
    
    
}
