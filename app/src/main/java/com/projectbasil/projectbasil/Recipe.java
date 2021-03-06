package com.projectbasil.projectbasil;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * Created by John Brand on 9/21/2015.
 * Container class for holding a recipe
 */
public class Recipe {

    /**
     * Nutritional Attributes of recipe
     */
    private Map<String, Float> nutrAttr;
    /**
     * name of recipe
     */
    private String name;
    /**
     * rating of recipe
     */
    private int rating; //-1 to 5 (-1 meaning no rating)
    /**
     * id return from API for recipe
     */
    private String recipeId;
    /**
     * instructions of recipe from API
     */
    private List<String> instructions;
    /**
     * ingredients of recipe
     */
    private List<String> ingredients;
    /**
     * url for image of recipe
     */
    private String imgURL;

    /**
     * constructor
     * @param name
     * @param rating
     * @param recipeId
     * @param instructions
     * @param nutrAttr
     * @param ingredients
     * @param imgURL
     */
    public Recipe(String name, int rating, String recipeId, List<String> instructions, Map<String, Float> nutrAttr, List<String> ingredients, String imgURL){
        this.nutrAttr = nutrAttr;
        this.ingredients = ingredients;
        this.name = name;
        this.rating = rating;
        this.instructions = instructions;
        this.recipeId = recipeId;
        this.imgURL = imgURL;
    }

    /**
     * returns name of recipe
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * set name of recipe
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * return rating
     * @return
     */
    public int getRating() {
        return rating;
    }

    /**
     * set rating of recipe
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * return recipeID of recipe
     * @return
     */
    public String getRecipeId() {
        return recipeId;
    }

    /**
     * return instructions
     * @return
     */
    public List<String> getInstructions(){
        return instructions;
    }

    /**
     * return nutrAttr
     * @return
     */
    public Map<String, Float> getNutrAttr(){
        return nutrAttr;
    }

    /**
     * returns ingredients
     * @return
     */
    public List<String> getIngredients(){
        return ingredients;
    }

    /**
     * return URL to image
     * @return
     */
    public String getImgURL() {
        return imgURL;
    }

    /**
     * set url to image
     * @param imgURL
     */
    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public String toString() {
        return name;
    }

    public int saveRecipe(String toMeal, Context context)
    {
        String toWrite = buildRecipeString();
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
    /**
     * Helper for building string being written to file
     * @return string of recipe
     */
    private String buildRecipeString()
    {
        String toWrite = name + "\n" + rating + "\n" + recipeId + "\n" + imgURL + "\n";
        for(String instruction : instructions) toWrite += instruction + "\n";
        toWrite += "ingredients\n";
        for(String ingredient : ingredients) toWrite += ingredient + "\n";
        toWrite += "endRecipe\n";
        return toWrite;
    }

    /**
     * Delete selected recipe from schedule
     * @param id recipe id
     * @param fromMeal meal & day to delete from
     * @param context
     * @return indicator of success/failure
     */
    public int deleteRecipe(String id, String fromMeal, Context context)
    {
        List<Recipe> recipes = loadMeal(fromMeal, context);
        String toWrite = "";
        boolean deleted = false;
        for(Recipe recipe : recipes)
        {
            if(!recipe.getRecipeId().equals(id) || deleted)
            {
                toWrite += recipe.buildRecipeString();
                deleted = true;
            }
        }

        FileOutputStream fos = null;
        try{
            fos = context.openFileOutput(fromMeal, Context.MODE_PRIVATE);
            fos.write(toWrite.getBytes());
        }
        catch(Exception e)
        {
            return -1;
        }

        return 0;
    }

    /**
     * Load meal plan from saved file
     * @param fromMeal
     * @param context
     * @return list of recipes loaded from file
     */
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
                String imgURL = b.readLine();

                List<String> is = new ArrayList<String>();
                String instruction = "";
                while(!(instruction = b.readLine()).equals("ingredients")) { is.add(instruction); } //List of instructions

                List<String> ing = new ArrayList<String>();
                String ingredient = "";
                while(!(ingredient = b.readLine()).equals("endRecipe")) { ing.add(ingredient); }

                Recipe toAdd = new Recipe(name, rating, recipeId, is, null, ing, imgURL);
                toReturn.add(toAdd);
            }

            t = sb.toString();
        }
        catch(Exception e)
        {
            return new ArrayList<Recipe>();
        }

        return toReturn;

    }

    /**
     * Instantiate and executes AsyncTask for loading img from recipe's url
     * @param imgView layout view to display image
     */
    public void LoadImageFromURL(ImageView imgView) {
        LoadImgTask task = new LoadImgTask(imgView);
        task.execute(getImgURL());
    }

    /**
     * Private class, AsyncTask for loading an image
     */
    private class LoadImgTask extends AsyncTask<String, Void, Drawable> {
        private ImageView img;
        public LoadImgTask(ImageView img) {
            this.img = img;
        }
        @Override
        protected Drawable doInBackground(String... params) {
            try {
                InputStream is = (InputStream) new URL(params[0]).getContent();
                Drawable d = Drawable.createFromStream(is, "pic src"); //pic source doesnt matter
                return d;
            } catch (Exception e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(Drawable results) {
            img.setImageDrawable(results);
        }
    }

}
