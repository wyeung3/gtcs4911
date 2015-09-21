import java.util.List;

/**
 * Created by tehub on 9/21/2015.
 */
public class User {

    public String query;

    private List<Item> preferences;
    private List<Item> allergies;
    private Inventory inventory;
    private RecipeBook recipeBook;
    private List<Plan> mealPlan;

    public User(List<Item> preferences, List<Item> allergies, Inventory inventory, RecipeBook recipeBook) {
        // constructor
        this.preferences = preferences;
        this.allergies = allergies;
        this.inventory = inventory;
        this.recipeBook = recipeBook;
        //this.mealPlan = mealPlan;
    }

    private void setPreferences(Item item){
        preferences.add(item);
    }
    private void setAllergies(Item item){
        allergies.add(item);
    }
    private void removeAllergies(Item item){
        allergies.remove(item);
    }
    private void removePreferences(Item item){
        preferences.remove(item);
    }

    private List<Plan> getMealPlan(){
        return mealPlan;
    }

    //TODO: search(), generateMealPlan(), createPlan()



}
