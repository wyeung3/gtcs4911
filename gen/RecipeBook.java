import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tehub on 9/21/2015.
 */
public class RecipeBook {

    private List<Recipe> recipeBook; //permanent book
    private List<Recipe> recipes;   //temporary list of all returned recipes from an API call

    public RecipeBook(List<Recipe> recipeBook){
        this.recipeBook = recipeBook;
    }

    public void addRecipe(Recipe recipe) {
        recipeBook.add(recipe);
    }

    public void removeRecipe(Recipe recipe){
        recipeBook.remove(recipe);
    }

    public void clearRecipeBook(){
        //recipeBook = Collections.<Recipe>emptyList();
        recipeBook.clear(); //possible resizing issues
    }

    private void clearRecipes(){
        //recipes = Collections.<Recipe>emptyList();
        recipes.clear();
       //doesnt resize recipes? Double check, should resize if it is arrayList
    }

    //public List<Recipe> getRecipe(List<String> names){ }//can't overload these do to type erasure
    //public List<Recipe> getRecipe(List<Integer> ratings) { }//need to check if passing in an arrayList will work with array

    public List<Recipe> getRecipe(String[] names){ //may need to use .toArray() on arrayList to pass in to method
        List<Recipe> results = new ArrayList<Recipe>();
        //TODO:parse recipes for names given
        return results;
    }
    public List<Recipe> getRecipe(int[] ratings) {
        List<Recipe> results = new ArrayList<Recipe>();
        //TODO: parse recipes for ratings given
        return results;
    }

    public void populateRecipes() {
        //TODO call database API with Query from User
    }

    //TODO: setFavorite(), removeFavorite()

    //TODO: sortBy rating/inventory

}
