/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ojan
 */
public class Admin extends Person{
  
    //deklarasi
    public Admin(int uid, String username, String password, String email, String address, boolean membership_status){
        super(uid, username, password, email, address, membership_status);
    }
    
    
    //Getter Setter  
    
    //kerjaan
    public void removeMember(int uid){
        
    }
    public void acceptMember(int uid){
        
    }
  
}
