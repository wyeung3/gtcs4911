package com.projectbasil.projectbasil;

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

    /**
     *
     * @param nutrition:
     * @return a list of recipes
     */
    public static List<Recipe> getRecipes(HashMap<String, Integer> nutrition)
    {
        return null;
    }

    /**
     * @param inventory A list of items in the user's inventory / their kitchen stock.
     * @return a list of recipes based on inventory stock
     */
    public static List<Recipe> getRecipe(Inventory inventory) //throws IOException, JSONException
    {
        try {
            String parameters = "&ingredients=";
            for (Item item : inventory.getInventory()) {
                parameters += item.getName() + ",";
            }
            parameters = parameters.substring(0, parameters.length() - 1);
            return buildRecipe(apiUrl + parameters);
        } catch (Exception E) {
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
            String parameters = "&ingredients=" + itemName;
            parameters = parameters.substring(0, parameters.length() - 1);
            return buildRecipe(apiUrl + parameters);
        } catch (Exception E) {
            return null;
        }
    }

    private static String readAll(BufferedReader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static List<Recipe> buildRecipe(String request) throws IOException, JSONException
    {
        ArrayList<Recipe> toReturn = new ArrayList<>();

        InputStream input = new URL(request).openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-*")));
        JSONObject json = new JSONObject(readAll(reader));

        JSONArray allRecipes = json.getJSONArray("");
        for(int x = 0; x < allRecipes.length(); x++)
        {
            JSONObject recipeHere = allRecipes.getJSONObject(x);
            toReturn.add(new Recipe(recipeHere.getString("title"), recipeHere.getInt("social_rank"), recipeHere.getString("ingredients"),null,null));
        }

        return toReturn;
    }
}