package com.projectbasil.projectbasil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class InventoryActivity extends AppCompatActivity {
    private ArrayList<String> inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        inventory = new ArrayList<>();
        populateInventory();

        // Set OnClickListener on button, transition when clicked?
        Button addItemBtn = (Button)findViewById(R.id.buttonShowRecipes);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //transition to addItem screen?
                Toast.makeText(getApplicationContext(), "Add Item screen here", Toast.LENGTH_LONG).show();
            }
        });

        // Set OnClickListener on button
        // When clicked, pop up overlay of checkboxes and remove all selected?
        Button removeItemBtn = (Button)findViewById(R.id.buttonShowRecipes);
        removeItemBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //transition to addItem screen?
                Toast.makeText(getApplicationContext(), "Add Item screen here", Toast.LENGTH_LONG).show();
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

    private void populateInventory() {
        ListView listViewInventory=(ListView)findViewById(R.id.listViewInventory);
        inventory = fetchInventory();
        InventoryAdapter inventoryAdapter = new InventoryAdapter(inventory, this);
        listViewInventory.setAdapter(inventoryAdapter);
        //custom adapter handles onClick events per item
    }

    private ArrayList<String> fetchInventory() {
        ArrayList list = new ArrayList<String>();
        for (int i = 0; i < 10; i++)
        {
            list.add("Inventory Item " + i);
        }
        return list;
    }

}