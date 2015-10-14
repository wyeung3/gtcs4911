package com.projectbasil.projectbasil;
import java.util.List;

/**
 * Created by tehub on 9/21/2015.
 */
public class User {

    public String query;

    private List<Item> preferences;
    private List<Item> allergies;

    public Inventory inventory;
    public RecipeBook recipeBook;
    public List<Plan> mealPlan;

    public User(List<Item> preferences, List<Item> allergies, Inventory inventory, RecipeBook recipeBook) {
        // constructor
        this.preferences = preferences;
        this.allergies = allergies;
        this.inventory = inventory;
        this.recipeBook = recipeBook;
        //this.mealPlan = mealPlan;
    }
    //TODO: getters/Setters for instance variables?
    public void setPreferences(Item item){preferences.add(item);}
    public void setAllergies(Item item){allergies.add(item);}
    public void removeAllergies(Item item){allergies.remove(item);}
    public void removePreferences(Item item){preferences.remove(item);}
    public List<Item> getPreferences() {return preferences;}
    public List<Item> getAllergies() {return allergies;}
    public Inventory getInventory() {return inventory;}
    public RecipeBook getRecipeBook() {return recipeBook;}
    public List<Plan> getMealPlan() {return mealPlan;}
    public void setInventory(Inventory inventory) {this.inventory = inventory;}
    public void setRecipeBook(RecipeBook recipeBook) {this.recipeBook = recipeBook;}
    public void setMealPlan(List<Plan> mealPlan) {this.mealPlan = mealPlan;}

    //TODO: search(), generateMealPlan(), createPlan()


}
