package com.example.katelynsuhr.boozebuddy;

/**
 * Created by katelynsuhr on 11/13/17.
 */

import java.io.Serializable;

@SuppressWarnings("serial")
public class Nutrition implements Serializable{

    String itemName;
    String itemId;
    String brandName;
    String Calories;
    String servingSize;
    String Quantity;

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
    public void setBrandName(String brandName){
        this.brandName = brandName;
    }
    public String getCalories(){
        return Calories;
    }
    public void setCalories(String Calories){
        this.Calories = Calories;
    }
    public void setItemId(String itemId){ this.itemId = itemId;}
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
