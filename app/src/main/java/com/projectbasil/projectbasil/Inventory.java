package com.projectbasil.projectbasil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by tehub on 9/21/2015.
 * Container class for a list of items, representing user's kitchen stock
 */
public class Inventory {
    private List<Item> inventory;

    public Inventory() {
        inventory = new ArrayList<>();
        inventory.add(new Item(1, "Ground Beef", 2.0f, new HashMap<String, Float>()));
    }
    
    public Inventory(List<Item> inv){
        this.inventory = inv;
    }

    public void addItem(Item item){
        inventory.add(item);
    }
    public List<Item> getInventory(){
        return this.inventory;
    }
    public Item getItemByName(String name){
        Item temp = new Item(null,null,null,null);
        //TODO: parse inventory for item with same name
        return temp;
    }
    public Item getItemByCode(int code){
        //TODO: parse inv for item with same code
        Item temp = new Item(null,null,null,null);
        return temp;
    }
    public Item getItemByIndex(int index) {
        return inventory.get(index);
    }
    public int size() {
        return inventory.size();
    }

    //TODO: public void addItemManually(){
        //  Maybe should be in user or some manager class
        //
        // get name from User
        // find it in database
        // Item temp = new Item(name, rating, asdf, asdf)
        //this.addItem(temp);
        //}
}
