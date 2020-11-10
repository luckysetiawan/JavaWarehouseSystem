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
      ArrayList<Person> persons = new ArrayList<>();
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
      ArrayList<Item> items = new ArrayList<>();
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
   
   public static ArrayList<Request> getAllRequest(Person person){
      ArrayList<Request> requests = new ArrayList<>();
      
      //get request take
      String query = "select a.*, c.item_id, c.item_name, c.item_size, c.item_weight, c.item_price, c.is_deleted "
              + "from request a "
              + "join requesttake b on a.req_id = b.req_id "
              + "join item c on b.item_id = c.item_id ";
      
      if(person instanceof Distributor) query += "where a.dist_id=" + person.getUid();
      else if(person instanceof Supplier) query += "where a.supp_id=" + person.getUid();
      
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            int reqId = rs.getInt("a.req_id");
            int suppId = rs.getInt("a.supp_id");
            int distId = rs.getInt("a.dist_id");
            boolean isAccepted = rs.getBoolean("is_accepted");
            int reqType = rs.getInt("a.req_type");
            int itemId = rs.getInt("c.item_id");
            String itemName = rs.getString("c.item_name");
            int itemQuantity = rs.getInt("a.item_quantity");
            int itemSize = rs.getInt("c.item_size");
            int itemWeight = rs.getInt("c.item_weight");
            int itemPrice = rs.getInt("c.item_price");
            boolean isDeleted = rs.getBoolean("c.is_deleted");
            Item item = new Item(itemId, itemName, itemQuantity, itemSize, itemWeight, itemPrice, isDeleted);
            
            requests.add(new Request(reqId, suppId, distId, item, isAccepted, reqType));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      //get request return
      query = "select a.*, d.item_id, d.item_name, d.item_size, d.item_weight, d.item_price, d.is_deleted "
              + "from request a "
              + "join requestreturn b on a.req_id = b.req_id "
              + "join takenitemdetail c on b.taken_id = c.taken_id and b.item_id = c.item_id "
              + "join item d on c.item_id = d.item_id ";
      
      if(person instanceof Distributor) query += "where a.dist_id=" + person.getUid();
      else if(person instanceof Supplier) query += "where a.supp_id=" + person.getUid();
      
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            int reqId = rs.getInt("a.req_id");
            int suppId = rs.getInt("a.supp_id");
            int distId = rs.getInt("a.dist_id");
            boolean isAccepted = rs.getBoolean("is_accepted");
            int reqType = rs.getInt("a.req_type");
            int itemId = rs.getInt("c.item_id");
            String itemName = rs.getString("c.item_name");
            int itemQuantity = rs.getInt("a.item_quantity");
            int itemSize = rs.getInt("c.item_size");
            int itemWeight = rs.getInt("c.item_weight");
            int itemPrice = rs.getInt("c.item_price");
            boolean isDeleted = rs.getBoolean("c.is_deleted");
            Item item = new Item(itemId, itemName, itemQuantity, itemSize, itemWeight, itemPrice, isDeleted);
            
            requests.add(new Request(reqId, suppId, distId, item, isAccepted, reqType));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return requests;
   }
   
   public static Request getRequest(int reqId){
      Request request = null;
      String query = "select * from request where a.req_id=" + reqId;
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            int suppId = rs.getInt("supp_id");
            int distId = rs.getInt("dist_id");
            int itemQuantity = rs.getInt("item_quantity");
            boolean isAccepted = rs.getBoolean("is_accepted");
            int reqType = rs.getInt("req_type");
            int itemId = 0;
            
            //request take
            if(rs.getInt("taken_id") == 0) itemId = rs.getInt("item_take_id");
            //request return
            else itemId = rs.getInt("item_return_id");
            
            query = "select item_name, item_size, item_weight, item_price, is_deleted "
               + "from item "
               + "where item_id=" + itemId;
            
            try {
               Statement stmt1 = conn.con.createStatement();
               rs = stmt1.executeQuery(query);

               while (rs.next()) {
                  String itemName = rs.getString("item_name");
                  int itemSize = rs.getInt("item_size");
                  int itemWeight = rs.getInt("item_weight");
                  int itemPrice = rs.getInt("item_price");
                  boolean isDeleted = rs.getBoolean("is_deleted");
                  Item item = new Item(itemId, itemName, itemQuantity, itemSize, itemWeight, itemPrice, isDeleted);

                  request = new Request(reqId, suppId, distId, item, isAccepted, reqType);
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         
      }
      return request;
   }
   
   public static boolean insertRequest(int dist_id, int supp_id, int item_id, int item_quantity, int req_type, int taken_id){
      String query;
      conn.connect();
      if(req_type == 0){
         query = "insert into item (dist_id, supp_id, item_quantity, req_type, item_take_id) "
         + "values (?, ?, ?, ?, ?)";

         try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, dist_id);
            stmt.setInt(2, supp_id);
            stmt.setInt(3, item_quantity);
            stmt.setInt(4, req_type);
            stmt.setInt(5, item_id);
            stmt.executeUpdate();
            
            return true;
         } catch (Exception e) {
            e.printStackTrace();

         }
      }else if(req_type == 1){
         query = "insert into item (dist_id, supp_id, item_quantity, req_type, taken_id, item_return_id) "
         + "values (?, ?, ?, ?, ?, ?)";
         try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, dist_id);
            stmt.setInt(2, supp_id);
            stmt.setInt(3, item_quantity);
            stmt.setInt(4, req_type);
            stmt.setInt(5, taken_id);
            stmt.setInt(6, item_id);
            stmt.executeUpdate();
            
            return true;
         } catch (Exception e) {
            e.printStackTrace();

         }
      }
      
      return false;
   }
   
   public static boolean updateRequest(Request request){
      String query = "update request set dist_id='" + request.getDistId() + "', "
         + "supp_id=" + request.getSuppId() + ", "
         + "item_quantity=" + request.getItem().getItem_id() + ", "
         + "is_accepted=" + request.isIsAccepted() + ","
         + "req_type=" + request.getReqType() + " "
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
   
   public static ArrayList<TakenItem> getAllTakenItems(Person person){
      ArrayList<TakenItem> takenItems = new ArrayList<>();
      String query = "select * from takenitem where uid=" + person.getUid();
      conn.connect();
      
      try {
         ArrayList<Item> listItem = new ArrayList<>();
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            int takenId = rs.getInt("taken_id");
            String query2 = "select c.item_id, c.item_name, a.item_quantity, c.item_size, c.item_weight, c.item_price, c.is_deleted "
                    + "from takenitemdetail a "
                    + "join item c on a.item_id=c.item_quantity "
                    + "where a.taken_id=" + takenId;
            
            try {
               Statement stmt2 = conn.con.createStatement();
               ResultSet rs2 = stmt2.executeQuery(query2);
               
               while (rs2.next()) {
                  int itemId = rs2.getInt("c.item_id");
                  String itemName = rs2.getString("c.item_name");
                  int itemQuantity = rs2.getInt("a.item_quantity");
                  int itemSize = rs2.getInt("c.item_size");
                  int itemWeight = rs2.getInt("c.item_weight");
                  int itemPrice = rs2.getInt("c.item_price");
                  boolean isDeleted = rs2.getBoolean("c.is_deleted");
                  Item item = new Item(itemId, itemName, itemQuantity, itemSize, itemWeight, itemPrice, isDeleted);
                  
                  listItem.add(item);
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
            int itemQuantity = rs.getInt("item_quantity");
            Date date = rs.getDate("date");
            
            takenItems.add(new TakenItem(takenId, listItem, person.getUid(), itemQuantity, date.toString()));
         }
      } catch (Exception e) {
         e.printStackTrace();
         
      }
      
      return takenItems;
   }
   
   public static TakenItem getTakenItem(int takenId){
      TakenItem takenItem = null;
      String query = "select * from takenitem where taken_id=" + takenId;
      conn.connect();
      
      try {
         ArrayList<Item> listItem = new ArrayList<>();
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            String query2 = "select c.item_id, c.item_name, a.item_quantity, c.item_size, c.item_weight, c.item_price, c.is_deleted "
                    + "from takenitemdetail a "
                    + "join item c on a.item_id=c.item_quantity "
                    + "where a.taken_id=" + takenId;
            
            try {
               Statement stmt2 = conn.con.createStatement();
               ResultSet rs2 = stmt2.executeQuery(query2);
               
               while (rs2.next()) {
                  int itemId = rs2.getInt("c.item_id");
                  String itemName = rs2.getString("c.item_name");
                  int itemQuantity = rs2.getInt("a.item_quantity");
                  int itemSize = rs2.getInt("c.item_size");
                  int itemWeight = rs2.getInt("c.item_weight");
                  int itemPrice = rs2.getInt("c.item_price");
                  boolean isDeleted = rs2.getBoolean("c.is_deleted");
                  Item item = new Item(itemId, itemName, itemQuantity, itemSize, itemWeight, itemPrice, isDeleted);
                  
                  listItem.add(item);
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
            int uid = rs.getInt("uid");
            int itemQuantity = rs.getInt("item_quantity");
            Date date = rs.getDate("date");
            
            takenItem = new TakenItem(takenId, listItem, uid, itemQuantity, date.toString());
         }
      } catch (Exception e) {
         e.printStackTrace();
         
      }
      
      return takenItem;
   }
   
   //Unfinished
   public static boolean insertTakenItem(int uid, Date date, ArrayList<Item> listItems){
      String query = "insert into takenitem (uid, date) values (?, ?)";
      conn.connect();
      
      try {
         //insert takenitem
         PreparedStatement stmt = conn.con.prepareStatement(query);
         stmt.setInt(1, uid);
         stmt.setDate(2, date);
         stmt.executeUpdate();
         
         //insert takenitemdetail
         for (int i = 0; i < listItems.size(); i++) {
            
         }
         
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         
         return false;
      }
   }
   
}
