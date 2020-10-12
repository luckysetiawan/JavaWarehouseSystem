/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author julia
 */
public class Request {
   private int reqId;
   private int suppId;
   private int itemId;
   private int itemQuantity;
   private boolean isAccepted;

   public Request(int reqId, int suppId, int itemId, int itemQuantity, boolean isAccepted) {
      this.reqId = reqId;
      this.suppId = suppId;
      this.itemId = itemId;
      this.itemQuantity = itemQuantity;
      this.isAccepted = isAccepted;
   }

   public int getReqId() {
      return reqId;
   }

   public void setReqId(int reqId) {
      this.reqId = reqId;
   }

   public int getSuppId() {
      return suppId;
   }

   public void setSuppId(int suppId) {
      this.suppId = suppId;
   }

   public int getItemId() {
      return itemId;
   }

   public void setItemId(int itemId) {
      this.itemId = itemId;
   }

   public int getItemQuantity() {
      return itemQuantity;
   }

   public void setItemQuantity(int itemQuantity) {
      this.itemQuantity = itemQuantity;
   }

   public boolean isIsAccepted() {
      return isAccepted;
   }

   public void setIsAccepted(boolean isAccepted) {
      this.isAccepted = isAccepted;
   }
   
   
}
