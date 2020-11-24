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
   private int distId;
   private Item item;
   private RequestStatus isAccepted;
   private int reqType;
   private int takenId;

   public Request(int reqId, int suppId, int distId, Item item, RequestStatus isAccepted, int reqType, int takenId) {
      this.reqId = reqId;
      this.suppId = suppId;
      this.distId = distId;
      this.item = item;
      this.isAccepted = isAccepted;
      this.reqType = reqType;
      this.takenId = takenId;
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

   public int getDistId() {
      return distId;
   }

   public void setDistId(int distId) {
      this.distId = distId;
   }

   public Item getItem() {
      return item;
   }

   public void setItem(Item item) {
      this.item = item;
   }

   public RequestStatus isIsAccepted() {
      return isAccepted;
   }

   public void setIsAccepted(RequestStatus isAccepted) {
      this.isAccepted = isAccepted;
   }

   public int getReqType() {
      return reqType;
   }

   public void setReqType(int reqType) {
      this.reqType = reqType;
   }

   public int getTakenId() {
      return takenId;
   }

   public void setTakenId(int takenId) {
      this.takenId = takenId;
   }
   
}
