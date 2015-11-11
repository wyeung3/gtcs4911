package com.projectbasil.projectbasil;
import java.util.Map;

/**
 * Created by tehub on 9/21/2015.
 * Container class for holding ingredient information
 */
public class Item {
    //Integer code; //USDA food code
    String name; //name of the item
    Float quantity; //quantity IE mL, gallons, teaspoons etc
    //private Map<String, Float> nutrAttr;

    public Item(String name, Float quantity){
        //this.code = code;
        this.name = name;
        this.quantity = quantity;
        //this.nutrAttr = nutrAttr;

    }

    public String getName() {return name;}

    public float getQuantity() {return quantity;}

    //public int getCode() {return code;}

    //public Map<String, Float> getNutrAttr(){return nutrAttr;}

    public void setName(String name) {this.name = name;}

    //public void setCode(int code) {this.code = code;}

    public void setQuantity(float quantity) {this.quantity = quantity;}

    //public void setNutrAttr(Map<String, Float> nutrAttr) {this.nutrAttr = nutrAttr;}

    @Override
    public String toString() {
        return name;
    }

}
