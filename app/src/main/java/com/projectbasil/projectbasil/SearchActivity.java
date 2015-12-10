package com.projectbasil.projectbasil;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {

    private ListView listViewRecipes;
    private List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Search");


        //API23, need to check permission during runtime
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.INTERNET}, 987654321);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        }
        else{
            //permission was previously granted; or legacy device
            listViewRecipes = (ListView)findViewById(R.id.listViewRecipes);
            recipeList = new ArrayList<>();
            ListSearchTask task = new ListSearchTask();
            task.execute();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 987654321 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            finish();
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
    }

    private class ListSearchTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            GlobalVars instance = GlobalVars.getInstance();
            recipeList = RecipeManager.getRecipe(instance.getInventory());
            if(recipeList == null) {
                cancel(true);
                return null;
            }
            System.out.println("recipeList size: " +  recipeList.size());
            return null;
        }
        @Override
        protected void onPostExecute(Void results) {
            ArrayAdapter<Recipe> arrayAdapter = new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_list_item_1, recipeList);
            listViewRecipes.setAdapter(arrayAdapter);
            listViewRecipes.setEmptyView(findViewById(R.id.emptyView));

            // register onClickListener to handle click events on each item
            listViewRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                // argument position gives the index of item which is clicked
                public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                    if (recipeList.get(position).getIngredients() == null) {
                        RecipeSearchTask task = new RecipeSearchTask();
                        task.execute(position);
                    } else {
                        GlobalVars instance = GlobalVars.getInstance();
                        instance.setMostRecentRecipe(recipeList.get(position));
                        Intent intent = new Intent(getApplicationContext(), RecipeDetailsActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
        @Override
        protected void onCancelled(Void results) {
            listViewRecipes.getEmptyView();
        }
    }

    private class RecipeSearchTask extends AsyncTask<Integer, Void, Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {
            String id = recipeList.get(params[0]).getRecipeId();
            Recipe selectedRecipe = RecipeManager.getRecipeFromId(id);
            if(selectedRecipe == null) {
                cancel(true);
                return -1;
            }
            recipeList.set(params[0], selectedRecipe);
            return params[0];
        }
        @Override
        protected void onPostExecute(Integer results) {
            if(results == -1) {
                Toast.makeText(getApplicationContext(), "We're sorry, but we have no details for this recipe!!",
                        Toast.LENGTH_LONG).show();
            }
            else {
                GlobalVars instance = GlobalVars.getInstance();
                instance.setMostRecentRecipe(recipeList.get(results));
                Intent intent = new Intent(getApplicationContext(), RecipeDetailsActivity.class);
                startActivity(intent);
            }

        }
        @Override
        protected void onCancelled(Integer results) {
            Toast.makeText(getApplicationContext(), "We're sorry, but we have no details for "
                    + recipeList.get(results).getName(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
        else if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}