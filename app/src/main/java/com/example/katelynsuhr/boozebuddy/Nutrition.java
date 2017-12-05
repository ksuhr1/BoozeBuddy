package com.example.katelynsuhr.boozebuddy;

/**
 * Created by katelynsuhr on 11/13/17.
 */

import java.io.Serializable;

@SuppressWarnings("serial")
public class Nutrition implements Serializable {

    String itemName;
    String itemId;
    String brandName;
    String Calories;
    String servingSize;
    String Quantity;
    //String totalCal;

    public Nutrition(){

    }
    public String getItemName(){
        return itemName;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public String getBrandName(){
        return brandName;
    }
    public String getItemId(){
        return itemId;
    }
   // public String getTotalCal(){return totalCal;}
    public void setBrandName(String brandName){
        this.brandName = brandName;
    }
    public void setNewBrandName(String brandName){this.brandName = brandName+"&";}
    public String getCalories(){
        return Calories;
    }
    public void setCalories(String Calories){
        this.Calories = Calories;
    }
    public void setItemId(String itemId){ this.itemId = itemId;}
   // public void setTotalCal(String totalCal){this.totalCal = totalCal;}
    public String getServingSize(){
        return servingSize;
    }
    public void setServingSize(String servingSize){
        this.servingSize = servingSize;
    }
    public String getQuantity(){
        return Quantity;
    }
    public void setQuantity(String Quantity){
        this.Quantity = Quantity;
    }
}
