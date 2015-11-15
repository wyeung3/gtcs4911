package com.projectbasil.projectbasil;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Created by tehub on 9/21/2015.
 * Container class for holding a recipe
 */
public class Recipe {

    private Map<String, Float> nutrAttr;
    private String name;
    private int rating; //-1 to 5 (-1 meaning no rating)
    private String recipeId;
    private List<String> instructions;
    private List<String> ingredients;

    public Recipe(String name, int rating, String recipeId, List<String> instructions, Map<String, Float> nutrAttr, List<String> ingredients){
        this.nutrAttr = nutrAttr;
        this.ingredients = ingredients;
        this.name = name;
        this.rating = rating;
        this.instructions = instructions;
        this.recipeId = recipeId;
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

    public String getRecipeId() {
        return recipeId;
    }

    public List<String> getInstructions(){
        return instructions;
    }
    public Map<String, Float> getNutrAttr(){
        return nutrAttr;
    }
    public List<String> getIngredients(){
        return ingredients;
    }

    @Override
    public String toString() {
        return name;
    }

    public int saveRecipe(String toMeal, Context context)
    {
        String toWrite = name + "\n" + rating + "\n" + recipeId + "\n";
        for(String instruction : instructions) toWrite += instruction + "\n";
        toWrite += "ingredients\n";
        for(String ingredient : ingredients) toWrite += ingredient + "\n";
        toWrite += "endRecipe\n";
        FileOutputStream fos = null;
        try{
            fos = context.openFileOutput(toMeal, Context.MODE_APPEND);
            fos.write(toWrite.getBytes());
        }
        catch(Exception e)
        {
            return -1;
        }

        return 0;
    }

    public static List<Recipe> loadMeal(String fromMeal, Context context)
    {
        FileInputStream fos = null;
        String t = null;
        List<Recipe> toReturn = new ArrayList<Recipe>();
        try{
            FileInputStream in = context.openFileInput(fromMeal);
            StringBuilder text = new StringBuilder();
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader b = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = b.readLine()) != null) {
                String name = line; //Name of the String
                int rating = Integer.parseInt(b.readLine()); //Rating of the Recipe
                String recipeId = b.readLine(); //Id of the Recipe

                List<String> is = new ArrayList<String>();
                String instruction = "";
                while(!(instruction = b.readLine()).equals("ingredients")) { is.add(instruction); } //List of instructions

                List<String> ing = new ArrayList<String>();
                String ingredient = "";
                while(!(ingredient = b.readLine()).equals("endRecipe")) { ing.add(ingredient); }

                Recipe toAdd = new Recipe(name, rating, recipeId, is, null, ing);
                toReturn.add(toAdd);
            }

            t = sb.toString();
        }
        catch(Exception e)
        {
            return null;
        }

        return toReturn;

    }

    //TODO: Format instructions here?
    //TODO: For creating your own recipes: Need to parse a List<Item> to calculate nutrAttr.
}
