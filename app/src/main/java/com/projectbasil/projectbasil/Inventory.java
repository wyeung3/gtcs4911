package com.projectbasil.projectbasil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by John Brand on 9/21/2015.
 * Container class for a list of items, representing user's kitchen stock
 */
public class Inventory {
    /**
     * list of all items that define the user's inventory
     */
    private List<Item> inventory;

    /**
     * Inventory Constructor
     */
    public Inventory() {
        inventory = new ArrayList<>();
        inventory.add(new Item("Beef", 2.0f));
    }

    /**
     * Overloaded constructor
     * Creates inventory from a list of items
     * @param inv
     */
    public Inventory(List<Item> inv){
        this.inventory = inv;
    }

    /**
     * Add item (param) to current inventory list
     * @param item
     */
    public void addItem(Item item){
        inventory.add(item);
    }

    /**
     * retuns list of items that is the inventory
     * @return
     */
    public List<Item> getInventory(){
        return this.inventory;
    }

    /**
     * Searches inventory for item by String name
     * @param name
     * @return
     */
    public Item getItemByName(String name){
        Item temp = new Item(null,null);
        //TODO: parse inventory for item with same name
        return temp;
    }

    /**
     * Searches inventory for item by int code (product code)
     * @param code
     * @return
     */
    public Item getItemByCode(int code){
        //TODO: parse inv for item with same code
        Item temp = new Item(null,null);
        return temp;
    }

    /**
     * Returns item at index (param) in inventory
     * @param index
     * @return
     */
    public Item getItemByIndex(int index) {
        return inventory.get(index);
    }

    /**
     * returns the size of the current inventory list
     * @return
     */
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
