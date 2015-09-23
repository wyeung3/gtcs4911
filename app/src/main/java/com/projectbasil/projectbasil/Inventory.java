package com.projectbasil.projectbasil;
import java.util.List;

/**
 * Created by tehub on 9/21/2015.
 */
public class Inventory {
    private List<Item> inventory;

    public Inventory(List<Item> inv){
        this.inventory = inv;
    }

    public void addItem(Item item){
        inventory.add(item);
    }
    public List<Item> getInventory(){
        return inventory;
    }
    public Item getItem(String name){
        Item temp = new Item(null,null,null,null);
        //TODO: parse inventory for item with same name
        return temp;
    }
    public Item getItem(int code){
        //TODO: parse inv for item with same code
        Item temp = new Item(null,null,null,null);
        return temp;
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
