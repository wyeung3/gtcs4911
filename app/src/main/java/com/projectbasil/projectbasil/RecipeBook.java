package com.projectbasil.projectbasil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by John Brand on 9/21/2015.
 * Container class for a list of recipes
 */
public class RecipeBook {

    /**
     * list of favorite recipes of the user
     */
    private List<Recipe> favorites; //permanent book of recipes

    /**
     * Loads favorites from local storage
     * @param savedFavorites a list of recipes saved locally by user
     */
    public RecipeBook(List<Recipe> savedFavorites){
        //TODO: change from taking in parameter to actually loading from local storage
        this.favorites = savedFavorites;
    }

    /**
     * add a Recipe to list of recipes (favorites)
     * @param recipe
     */
    public void addFavoriteRecipe(Recipe recipe) {
        favorites.add(recipe);
        //TODO: save change to locally stored favorites recipebook
    }

    /**
     * removes a recipe from favorites list
     * @param recipe
     */
    public void removeFavoriteRecipe(Recipe recipe){
        favorites.remove(recipe);
        //TODO: save change to locally stored favorites recipebook
    }

    /**
     * delete all entries in favorites
     */
    public void clearFavorites(){
        favorites.clear(); //possible resizing issues
        //TODO: save change to locally stored favorites recipebook
    }

    /**
     * Return a list of recipes based off current inventory for user
     * to pick a recipe from from.
     * @param inventory
     * @return
     */
    public static List<Recipe> getRecipe(Inventory inventory){
        return RecipeManager.getRecipe(inventory);
    }

    /*
    public List<Recipe> getRecipe(int[] ratings) {
        List<Recipe> results = new ArrayList<Recipe>();
        //TODO: sort results by rating?
        return results;
    }
    */

}
