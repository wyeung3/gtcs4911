/**
 * Created by tehub on 9/21/2015.
 */
public class Recipe {

    //TODO: nutritional Attributes data structure, get/set
    private String name;
    private int rating; //-1 to 5 (-1 meaning no rating)
    //Will be removed with attributes
    //private float protein;
    //private float etc;
    private String instructions;
    //private List<objects> ingredients;

    public Recipe(String name, int rating, String instructions){
        //TODO: adapt to the nutr attr struct
        this.name = name;
        this.rating = rating;
        this.instructions = instructions;

    }
}
