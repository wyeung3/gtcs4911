package com.projectbasil.projectbasil;

/**
 * Created by Walton on 10/28/2015.
 * Singleton class for holding instance of Inventory across activities
 */
public class GlobalVars {
    private static GlobalVars instance;

    // Global variable
    private Inventory inventory;

    // Restrict the constructor from being instantiated
    private GlobalVars(){}

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public static synchronized GlobalVars getInstance(){
        if(instance==null){
            instance=new GlobalVars();
        }
        return instance;
    }

}
