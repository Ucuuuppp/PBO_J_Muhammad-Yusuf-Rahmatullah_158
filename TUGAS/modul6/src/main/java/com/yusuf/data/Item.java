package com.yusuf.data;

public class Item{
    public String description;
    public String itemName;
    public String location;
    public String status;

     public Item(String itemName, String description, String location, String status) {
         this.itemName = itemName;
         this.description = description;
         this.location = location;
         this.status = status;
     }

     public String getItemName() {
         return itemName;
     }

     public void setItemName(String itemName) {
         this.itemName = itemName;
     }

     public String getDescription() {
         return description;
     }

     public void setDescription(String description) {
         this.description = description;
     }

     public String getLocation() {
         return location;
     }

     public void setLocation(String location) {
         this.location = location;
     }

     public String getStatus() {
         return status;
     }

     public void setStatus(String status) {
         this.status = status;
     }

     
}