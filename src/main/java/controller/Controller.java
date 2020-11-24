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
  
   public static Person getPerson(int uid, String username){
      Person person = null;
      String query = "select * from user ";
      if(uid == 0)
          query += "where username='" + username + "'";
      else if(username.equals(""))
          query += "where uid='" + uid + "'";
      conn.connect();
       
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while(rs.next()){
            uid = rs.getInt("uid");
            username = rs.getString("username");
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
      String query = "select * from item";
      if(person instanceof Supplier) query += " where  uid=" + person.getUid();
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while(rs.next()){
            int itemId = rs.getInt("item_id");
            int uid = rs.getInt("uid");
            String itemName = rs.getString("item_name");
            int itemQuantity = rs.getInt("item_quantity");
            int itemSize = rs.getInt("item_size");
            int itemWeight = rs.getInt("item_weight");
            int itemPrice = rs.getInt("item_price");
            boolean isDeleted = rs.getBoolean("is_deleted");

            items.add(new Item(itemId, uid, itemName, itemQuantity, itemSize, itemWeight, itemPrice, isDeleted));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return items;
   }
   
   public static Item getItem(int itemId){
      Item item = null;
      String query = "select * from item where item_id=" + itemId;
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            int uid = rs.getInt("uid");
            String itemName = rs.getString("item_name");
            int itemQuantity = rs.getInt("item_quantity");
            int itemSize = rs.getInt("item_size");
            int itemWeight = rs.getInt("item_weight");
            int itemPrice = rs.getInt("item_price");
            boolean isDeleted = rs.getBoolean("is_deleted");
            
            item = new Item(itemId, uid, itemName, itemQuantity, itemSize, itemWeight, itemPrice, isDeleted);
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
      String query = "select a.*, c.item_name, c.item_size, c.item_weight, c.item_price, c.is_deleted "
              + "from request a "
              + "join item c on a.item_take_id = c.item_id or a.item_return_id = c.item_id ";
      
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
            RequestStatus isAccepted = RequestStatus.values()[rs.getInt("a.is_accepted")];
            int reqType = rs.getInt("a.req_type");
            
            int itemId = rs.getInt("a.item_take_id");
            if(reqType == 1) itemId = rs.getInt("a.item_return_id");
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
      String query = "select a.*, c.item_name, c.item_size, c.item_weight, c.item_price, c.is_deleted "
              + "from request a "
              + "join item c on a.item_take_id = c.item_id or a.item_return_id = c.item_id "
              + "where a.req_id=" + reqId;
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            int suppId = rs.getInt("a.supp_id");
            int distId = rs.getInt("a.dist_id");
            RequestStatus isAccepted = RequestStatus.values()[rs.getInt("a.is_accepted")];
            int reqType = rs.getInt("a.req_type");
            
            int itemId = rs.getInt("a.item_take_id");
            if(reqType == 1) itemId = rs.getInt("a.item_return_id");
            
            String itemName = rs.getString("c.item_name");
            int itemQuantity = rs.getInt("a.item_quantity");
            int itemSize = rs.getInt("c.item_size");
            int itemWeight = rs.getInt("c.item_weight");
            int itemPrice = rs.getInt("c.item_price");
            boolean isDeleted = rs.getBoolean("c.is_deleted");
            Item item = new Item(itemId, itemName, itemQuantity, itemSize, itemWeight, itemPrice, isDeleted);
            
            request = new Request(reqId, suppId, distId, item, isAccepted, reqType);
         }
      } catch (Exception e) {
         e.printStackTrace();
         
      }
      return request;
   }
   
   public static boolean insertRequest(int distId, int suppId, int itemQuantity, int reqType, int itemId, int takenId){
      String query = "insert into request ";
      if(reqType == 0) query += "(dist_id, supp_id, item_quantity, req_type, item_take_id) values (" + distId + "," + suppId + "," + itemQuantity + "," + reqType + "," + itemId + ")";
      else if(reqType == 1) query += "(dist_id, supp_id, item_quantity, req_type, taken_id, item_return_id) values (" + distId + "," + suppId + "," + itemQuantity + "," + reqType + "," + takenId + "," + itemId + ")";
      
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
   
   public static boolean updateRequest(Request request){
      String query = "update request set dist_id='" + request.getDistId() + "', "
         + "supp_id=" + request.getSuppId() + ", "
         + "item_quantity=" + request.getItem().getItem_id() + ", "
         + "is_accepted=" + request.isIsAccepted() + ","
         + "req_type=" + request.getReqType() + " "
         + "where req_id=" + request.getReqId();
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
   
   public static ArrayList<TakenItem> getAllTakenItems(Distributor person){
      //get takenitem
      ArrayList<TakenItem> takenItems = new ArrayList<>();
      String query = "select * from takenitem where uid=" + person.getUid();
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            int takenId = rs.getInt("taken_id");
            Date date = rs.getDate("date");
            takenItems.add(new TakenItem(takenId, null, person.getUid(), date.toString()));
         }
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
      
      //get the items
      for(TakenItem takenItem : takenItems){
         ArrayList<Item> listItem = new ArrayList<>();
         query = "select c.item_id, c.item_name, a.item_quantity, c.item_size, c.item_weight, c.item_price, c.is_deleted "
               + "from takenitemdetail a "
               + "join item c on a.item_id=c.item_id "
               + "where a.taken_id=" + takenItem.getTaken_id();
         conn.connect();
         try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
               int itemId = rs.getInt("c.item_id");
               String itemName = rs.getString("c.item_name");
               int itemQuantity = rs.getInt("a.item_quantity");
               int itemSize = rs.getInt("c.item_size");
               int itemWeight = rs.getInt("c.item_weight");
               int itemPrice = rs.getInt("c.item_price");
               boolean isDeleted = rs.getBoolean("c.is_deleted");
               
               listItem.add(new Item(itemId, itemName, itemQuantity, itemSize, itemWeight, itemPrice, isDeleted));
            }
            
            takenItem.setListItem(listItem);
         } catch (Exception e) {
            e.printStackTrace();
            
            return null;
         }
      }
      
      return takenItems;
   }
   
   public static TakenItem getTakenItem(int takenId){
      //get takenitem
      TakenItem takenItem = null;
      String query = "select * from takenitem where taken_id=" + takenId;
      conn.connect();
      
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            int uid = rs.getInt("uid");
            Date date = rs.getDate("date");
            takenItem = new TakenItem(takenId, null, uid, date.toString());
         }
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
      
      //get the items
      ArrayList<Item> listItem = new ArrayList<>();
      query = "select c.item_id, c.item_name, a.item_quantity, c.item_size, c.item_weight, c.item_price, c.is_deleted "
            + "from takenitemdetail a "
            + "join item c on a.item_id=c.item_id "
            + "where a.taken_id=" + takenId;
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);

         while (rs.next()) {
            int itemId = rs.getInt("c.item_id");
            String itemName = rs.getString("c.item_name");
            int itemQuantity = rs.getInt("a.item_quantity");
            int itemSize = rs.getInt("c.item_size");
            int itemWeight = rs.getInt("c.item_weight");
            int itemPrice = rs.getInt("c.item_price");
            boolean isDeleted = rs.getBoolean("c.is_deleted");

            listItem.add(new Item(itemId, itemName, itemQuantity, itemSize, itemWeight, itemPrice, isDeleted));
         }

         takenItem.setListItem(listItem);
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }
      
      return takenItem;
   }
   
   public static boolean insertTakenItem(int uid, Date date, ArrayList<Item> listItems){
      //insert takenitem
      String query = "insert into takenitem (uid, date) values (?, ?)";
      conn.connect();
      
      try {
         PreparedStatement stmt = conn.con.prepareStatement(query);
         stmt.setInt(1, uid);
         stmt.setDate(2, date);
         stmt.executeUpdate();
         
      } catch (Exception e) {
         e.printStackTrace();
         
         return false;
      }
      
      //get pk
      int takenId = 0;
      query = "select max(taken_id) as 'taken_id' from takenitem";
      try {
         Statement stmt = conn.con.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         
         while (rs.next()) {
            takenId = rs.getInt("taken_id");
         }
      } catch (Exception e) {
         e.printStackTrace();
         
         return false;
      }
      
      //insert takenitemdetail
      query = "insert into takenitemdetail values ";
      for (int i = 0, j = listItems.size(); i < j; i++) {
         query += "(" + takenId + ", " + listItems.get(i).getItem_id() + ", " + listItems.get(i).getItem_quantity() + ")";
         if(i < j - 1) query += ", ";
      }
      
      try {
         Statement stmt = conn.con.createStatement();
         stmt.executeUpdate(query);
         
      } catch (Exception e) {
         e.printStackTrace();
         
         return false;
      }
      
      return true;
   }
   
}
