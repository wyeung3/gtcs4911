package com.projectbasil.projectbasil;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Created by Marc on 10/5/2015.
 * Manager for API calls to recipe database
 */
public class RecipeManager {
    private final static String apiKey = "20b007f3c89a4eb35710c9312abf9e36";
    private final static String apiUrl = "http://food2fork.com/api/search?key=" + apiKey;
    private final static String apiRecipeUrl = "http://food2fork.com/api/get?key=";

    /**
     *
     * @param nutrition:
     * @return a list of recipes
     */
    public static List<Recipe> getRecipes(HashMap<String, Integer> nutrition)
    {
        return null;
    }

    public static Recipe getRecipeFromId(String id)
    {
        try {
            return buildIdRecipe(apiRecipeUrl + apiKey + "&rId=" + id);
        }
        catch(Exception e)
        {
            return null;
        }
    }


    /**
     * @param inventory A list of items in the user's inventory / their kitchen stock.
     * @return a list of recipes based on inventory stock
     */
    public static List<Recipe> getRecipe(Inventory inventory) //throws IOException, JSONException
    {
        try {
            String parameters = "&q=";
            for (Item item : inventory.getInventory()) {
                parameters += item.getName() + ",";
            }
            parameters = parameters.substring(0, parameters.length() - 1);
            return buildRecipe(apiUrl + parameters);
        } catch (Exception E) {
            E.printStackTrace();
            return null;
        }
    }

    /**
     * @param itemName a specific string to search by
     * @return list of recipes found by searching given string
     */
    public static List<Recipe> getRecipeByString(String itemName) //throws IOException, JSONException
    {
        try {
            String parameters = "&q=" + itemName;
            parameters = parameters.substring(0, parameters.length() - 1);
            return buildRecipe(apiUrl + parameters);
        } catch (Exception E) {
            return null;
        }
    }

    private static String readAll(BufferedReader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        System.out.println("Inside readAll");
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static List<Recipe> buildRecipe(String request) throws IOException, JSONException
    {
        List<Recipe> toReturn = new ArrayList<>();
        BufferedInputStream input = new BufferedInputStream(new URL(request).openStream(), 8000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
        JSONObject json = new JSONObject(readAll(reader));

        JSONArray allRecipes = json.getJSONArray("recipes");
        for(int x = 0; x < allRecipes.length(); x++)
        {
            JSONObject recipeHere = allRecipes.getJSONObject(x);
            toReturn.add(new Recipe(recipeHere.getString("title"), recipeHere.getInt("social_rank"),
                    recipeHere.getString("recipe_id"), null,null,null, recipeHere.getString("image_url")));
        }

        return toReturn;
    }

    private static Recipe buildIdRecipe(String request) throws IOException, JSONException
    {
        BufferedInputStream input = new BufferedInputStream(new URL(request).openStream(), 8000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
        JSONObject json = new JSONObject(readAll(reader));

        JSONObject recipeHere = json.getJSONObject("recipe");
        JSONArray t = recipeHere.getJSONArray("ingredients");
        ArrayList<String> ingredients = new ArrayList<String>();
        for(int x = 0; x < t.length(); x++)
        {
            ingredients.add(t.getString(x));
        }
        Recipe toReturn = new Recipe(recipeHere.getString("title"), recipeHere.getInt("social_rank"),
                recipeHere.getString("recipe_id"), new ArrayList<String>(), null,ingredients, recipeHere.getString("image_url"));

        return toReturn;
    }

}