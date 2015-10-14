package com.projectbasil.projectbasil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<String> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button btnShowRecipes=(Button)findViewById(R.id.buttonShowRecipes);
        // Set OnClick Listener on button and search when clicked on Button
        btnShowRecipes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recipeList = new ArrayList<>();
                populateRecipeList();
            }
        });
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

        return super.onOptionsItemSelected(item);
    }

    private void populateRecipeList() {
        ListView listViewRecipes=(ListView)findViewById(R.id.listViewRecipes);
        recipeList = searchRecipes();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipeList);
        listViewRecipes.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        listViewRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                String selectedRecipe = recipeList.get(position);
                Toast.makeText(getApplicationContext(), "Recipe Selected : " + selectedRecipe, Toast.LENGTH_LONG).show();
            }
        });
    }

    private ArrayList<String> searchRecipes() {
        ArrayList list = new ArrayList<String>();
        for (int i = 0; i < 10; i++)
        {
            list.add("Recipe " + i);
        }
        return list;
    }

}