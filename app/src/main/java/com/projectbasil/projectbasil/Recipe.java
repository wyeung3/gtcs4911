package com.projectbasil.projectbasil;
import java.util.List;
import java.util.Map;

/**
 * Created by tehub on 9/21/2015.
 */
public class Recipe {

    private Map<String, Float> nutrAttr;
    private String name;
    private int rating; //-1 to 5 (-1 meaning no rating)
    private String instructions;
    private List<Item> ingredients;

    public Recipe(String name, int rating, String instructions, Map<String, Float> nutrAttr, List<Item> ingredients){
        this.nutrAttr = nutrAttr;
        this.ingredients = ingredients;
        this.name = name;
        this.rating = rating;
        this.instructions = instructions;

    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getInstructions(){
        return instructions;
    }
    public Map<String, Float> getNutrAttr(){
        return nutrAttr;
    }
    public List<Item> getIngredients(){
        return ingredients;
    }

    //TODO: Format instructions here?
    //TODO: For creating your own recipes: Need to parse a List<Item> to calculate nutrAttr.
}
