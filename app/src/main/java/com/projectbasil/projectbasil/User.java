package com.projectbasil.projectbasil;
import java.util.List;

/**
 * Created by John Brand on 9/21/2015.
 */
public class User {

    /**
     * String used to pass into RecipeManager for API calls
     */
    public String query;

    /**
     * Variables used to filter API calls (unused)
     */
    private List<Item> preferences;
    private List<Item> allergies;

    /**
     * Basic structures for data
     */
    public Inventory inventory;
    public RecipeBook recipeBook;
    public List<Plan> mealPlan;

    /**
     * User Constructor
     * @param preferences
     * @param allergies
     * @param inventory
     * @param recipeBook
     */
    public User(List<Item> preferences, List<Item> allergies, Inventory inventory, RecipeBook recipeBook) {
        // constructor
        this.preferences = preferences;
        this.allergies = allergies;
        this.inventory = inventory;
        this.recipeBook = recipeBook;
        //this.mealPlan = mealPlan;
    }

    /**
     * Adds preference (param) to list of current preferences
     * @param item
     */
    public void setPreferences(Item item){preferences.add(item);}

    /**
     * Adds allergy (param) to list of current allergies
     * @param item
     */
    public void setAllergies(Item item){allergies.add(item);}

    /**
     * Remove  allergy (param) from list of current allergies
     * @param item
     */
    public void removeAllergies(Item item){allergies.remove(item);}

    /**
     * Remove preference (param) from list of current preferences
     * @param item
     */
    public void removePreferences(Item item){preferences.remove(item);}

    /**
     * Return list of user preferences
     * @return List <Item> preferences
     */
    public List<Item> getPreferences() {return preferences;}

    /**
     * Return list of allergies
     * @return List <Item> allergies
     */
    public List<Item> getAllergies() {return allergies;}

    /**
     * Return user's inventory list
     * @return Inventory inventory
     */
    public Inventory getInventory() {return inventory;}

    /**
     * Returns user's recipebook list
     * @return RecipeBook recipeBook
     */
    public RecipeBook getRecipeBook() {return recipeBook;}

    /**
     * Returns current mealPlan
     * @return List <Plan> mealPlan
     */
    public List<Plan> getMealPlan() {return mealPlan;}

    /**
     * Used to set user's inventory
     * @param inventory
     */
    public void setInventory(Inventory inventory) {this.inventory = inventory;}

    /**
     * Used to set user's current recipeBook
     * @param recipeBook
     */
    public void setRecipeBook(RecipeBook recipeBook) {this.recipeBook = recipeBook;}

    /**
     * Used to set user's current mealPlan
     * @param mealPlan
     */
    public void setMealPlan(List<Plan> mealPlan) {this.mealPlan = mealPlan;}


}
