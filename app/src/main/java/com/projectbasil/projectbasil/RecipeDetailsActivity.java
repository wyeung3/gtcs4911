package com.projectbasil.projectbasil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class RecipeDetailsActivity extends AppCompatActivity {

    private String bulletSymbol = "•";
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        GlobalVars instance = GlobalVars.getInstance();
        recipe = instance.getMostRecentRecipe();
        if(recipe == null) {
            TextView rating = (TextView) findViewById(R.id.recipeRating);
            rating.setText("Sorry! Only us hungry hungry programmers here!");
        }
        else {
            getSupportActionBar().setTitle(recipe.getName());
            TextView rating = (TextView) findViewById(R.id.recipeRating);
            rating.setText("Rating: " + recipe.getRating());
            TextView ingredients = (TextView) findViewById(R.id.recipeIngredients);
            String list = "Ingredients:\n";
            for(int i = 0; i < recipe.getIngredients().size(); i++) {
                list = list.concat(bulletSymbol + recipe.getIngredients().get(i) + "\n");
            }
            ingredients.setText(list);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
