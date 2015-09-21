import java.sql.Time;
import java.util.Date;


/**
 * Created by tehub on 9/21/2015.
 */
public class Plan {
    public Recipe recipe;
    public String name;
    public Time time;
    //TODO: set up triplet for easy formatting
    //private Triplet<Recipe, String, Time> plan;

    public Plan(Recipe recipe, String name, Time time){
        this.recipe = recipe;
        this.name = name;
        this.time = time;
    }

    //TODO: make single getPlan() to return triplet

    public Recipe getRecipe() {
        return recipe;
    }

    public String getName(){
        return name;
    }

    public Time getTime(){
        return time;
    }
}
