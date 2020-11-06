/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author Lucky Setiawan
 */
public class Controller {
   static DatabaseHandler conn = new DatabaseHandler();

   public static ArrayList<Person> getAllPersons(){
      ArrayList<Person> persons = new ArrayList<Person>();
      String query = "select * from user";
      conn.connect();

      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while(rs.next()){
            int uid = rs.getInt("uid");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String address = rs.getString("address");
            int userType = rs.getInt("user_type");
            boolean membershipStatus = rs.getBoolean("membership_status");

            switch (userType) {
               //User Admin
               case 0:
                  persons.add(new Admin(uid, username, password, email, address, membershipStatus));
                  break;
               //User Supplier
               case 1:
                  persons.add(new Supplier(uid, username, password, email, address, membershipStatus));
                  break;
               //User Distributor
               case 2:
                  persons.add(new Distributor(uid, username, password, email, address, membershipStatus));
                  break;
               default:
                  break;
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return persons;
   }
  
   public static Person getPerson(String username){
      Person person = null;
      String query = "select * from person where username='" + username + "'";
      conn.connect();
       
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while(rs.next()){
            int uid = rs.getInt("uid");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String address = rs.getString("address");
            int userType = rs.getInt("user_type");
            boolean membershipStatus = rs.getBoolean("membership_status");

            switch (userType) {
               //User Admin
               case 0:
                  person = new Admin(uid, username, password, email, address, membershipStatus);
                  break;
               //User Supplier
               case 1:   
                  person = new Supplier(uid, username, password, email, address, membershipStatus);
                  break;
               //User Distributor
               case 2:
                  person = new Distributor(uid, username, password, email, address, membershipStatus);
                  break;
               default:
                  break;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
      return person;
   }
   
   public static boolean insertPerson(String username, String password, String email, String address, int user_type){
      String query = "insert into user (username, password, email, address, user_type) "
         + "values (?, ?, ?, ?, ?)";
      conn.connect();
      
      try {
         PreparedStatement stmt = conn.con.prepareStatement(query);
         stmt.setString(1, username);
         stmt.setString(2, password);
         stmt.setString(3, email);
         stmt.setString(4, address);
         stmt.setInt(5, user_type);
         stmt.executeUpdate();
         
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         
         return false;
      }
   }
   
   public static boolean updatePerson(Person person){
      String query = "update user set username='" + person.getUsername() + "', "
         + "password='" + person.getPassword() + "', "
         + "email='" + person.getEmail() + "',"
         + "address='" + person.getAddress() + "', "
         + "membership_status=" + person.isMembership_status() + " "
         + "where uid=" + person.getUid() + "";
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         stmt.executeUpdate(query);
         
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         
         return false;
      }
   }
   
   public static ArrayList<Item> getAllItems(Person person){
      ArrayList<Item> items = new ArrayList<Item>();
      String query = "select item_id, item_name, item_quantity, item_size, item_weight, item_price, is_deleted from item";
      //person = null untuk select semua
      if(person instanceof Supplier) query += " where  uid=" + person.getUid();
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while(rs.next()){
            int itemId = rs.getInt("item_id");
            String itemName = rs.getString("item_name");
            int itemQuantity = rs.getInt("item_quantity");
            int itemSize = rs.getInt("item_size");
            int itemWeight = rs.getInt("item_weight");
            int itemPrice = rs.getInt("item_price");
            boolean isDeleted = rs.getBoolean("is_deleted");

            items.add(new Item(itemId, itemName, itemQuantity, itemSize, itemWeight, itemPrice, isDeleted));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return items;
   }
   
   public static Item getItem(int itemId){
      Item item = null;
      String query = "select item_id, item_name, item_quantity, item_size, item_weight, item_price, is_deleted from item where item_id=" + itemId;
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            String itemName = rs.getString("item_name");
            int itemQuantity = rs.getInt("item_quantity");
            int itemSize = rs.getInt("item_size");
            int itemWeight = rs.getInt("item_weight");
            int itemPrice = rs.getInt("item_price");
            boolean isDeleted = rs.getBoolean("is_deleted");
            
            item = new Item(itemId, itemName, itemQuantity, itemSize, itemWeight, itemPrice, isDeleted);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return item;
   }
   
   public static boolean insertItem(int uid, String item_name, int item_quantity, int item_size, int item_weight, int item_price){
      String query = "insert into item (uid, item_name, item_quantity, item_size, item_weight, item_price) "
         + "values (?, ?, ?, ?, ?, ?)";
      conn.connect();
      
      try {
         PreparedStatement stmt = conn.con.prepareStatement(query);
         stmt.setInt(1, uid);
         stmt.setString(2, item_name);
         stmt.setInt(3, item_quantity);
         stmt.setInt(4, item_size);
         stmt.setInt(5, item_weight);
         stmt.setInt(6, item_price);
         stmt.executeUpdate();
         
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         
         return false;
      }
   }
   
   public static boolean updateItem(Item item){
      String query = "update item set item_name='" + item.getItem_name() + "', "
         + "item_quantity=" + item.getItem_quantity() + ", "
         + "item_size=" + item.getItem_size() + ", "
         + "item_weight=" + item.getItem_weight() + ", "
         + "item_price=" + item.getItem_price() + ", "
         + "is_deleted=" + item.isIs_deleted() + " "
         + "where item_id=" + item.getItem_id();
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         stmt.executeUpdate(query);
         
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         
         return false;
      }
   }
   
   public static ArrayList<Request> getAllRequests(){
      ArrayList<Request> requests = new ArrayList<Request>();
      String query = "select * from request";
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            int reqId = rs.getInt("req_id");
            int distId = rs.getInt("dist_id");
            int suppId = rs.getInt("supp_id");
            int itemId = rs.getInt("item_id");
            int itemQuantity = rs.getInt("item_quantity");
            boolean isAccepted = rs.getBoolean("is_accepted");
            
            requests.add(new Request(reqId, suppId, distId, itemId, itemQuantity, isAccepted));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return requests;
   }
   
   public static Request getRequest(int reqId){
      Request request = null;
      String query = "select * from request where req_id=" + reqId;
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            int suppId = rs.getInt("supp_id");
            int distId = rs.getInt("dist_id");
            int itemId = rs.getInt("item_id");
            int itemQuantity = rs.getInt("item_quantity");
            boolean isAccepted = rs.getBoolean("is_accepted");
            
            request = new Request(reqId, suppId, distId, itemId, itemQuantity, isAccepted);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return request;
   }
   
   public static boolean insertRequest(int dist_id, int supp_id, int item_id, int item_quantity){
      String query = "insert into item (dist_id, supp_id, item_id, item_quantity) "
         + "values (?, ?, ?, ?)";
      conn.connect();
      
      try {
         PreparedStatement stmt = conn.con.prepareStatement(query);
         stmt.setInt(1, dist_id);
         stmt.setInt(2, supp_id);
         stmt.setInt(3, item_id);
         stmt.setInt(4, item_quantity);
         stmt.executeUpdate();
         
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         
         return false;
      }
   }
   
   public static boolean updateRequest(Request request){
      String query = "update request set dist_id='" + request.getDistId() + "', "
         + "supp_id=" + request.getSuppId() + ", "
         + "item_id=" + request.getItemId() + ", "
         + "item_quantity=" + request.getItemQuantity() + ", "
         + "is_accepted=" + request.isIsAccepted() + " "
         + "where item_id=" + request.getReqId();
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         stmt.executeUpdate(query);
         
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         
         return false;
      }
   }
   
   //Incomplete Task
   public static ArrayList<TakenItem> getAllTakenItems(){
      ArrayList<TakenItem> takenItems = new ArrayList<>();
      String query = "select * from takenitem where uid=" + person.getUid();
      conn.connect();
      
      try {
         ArrayList<Item> listItem = new ArrayList<>();
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            int takenId = rs.getInt("taken_id");
            listItem.add(getItem(rs.getInt("item_id")));
            int itemQuantity = rs.getInt("item_quantity");
            Date date = rs.getDate("date");
            
            takenItems.add(new TakenItem(takenId, itemId, uid, itemQuantity, date.toString()));
         }
      } catch (Exception e) {
         e.printStackTrace();
         
      }
      
      return takenItems;
   }
   
   //Incomplete Task
   public static TakenItem getTakenItem(){
      
   }
   
   //Incomplete Task
   public static boolean insertTakenItem(){
      
   }
   
   //Incomplete Task
   public static boolean updateTakenItem(){
      
   }
   
}
