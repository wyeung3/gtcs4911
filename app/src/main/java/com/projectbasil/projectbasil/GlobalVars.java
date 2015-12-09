package com.projectbasil.projectbasil;

/**
 * Created by Walton on 10/28/2015.
 * Singleton class for holding instance of Inventory across activities
 */
public class GlobalVars {
    private static GlobalVars instance;

    // Global variable
    private Inventory inventory;
    private Recipe mostRecentRecipe;

    // Restrict the constructor from being instantiated
    private GlobalVars(){}

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void setMostRecentRecipe(Recipe recipe) {
        mostRecentRecipe = recipe;
    }

    public Recipe getMostRecentRecipe() {
        return mostRecentRecipe;
    }

    //returns an instance of the singleton, only way to instantiate GlobalVars
    public static synchronized GlobalVars getInstance(){
        if(instance==null){
            instance=new GlobalVars();
        }
        return instance;
    }

}
