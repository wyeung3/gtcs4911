package com.projectbasil.projectbasil;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by bae on 11/1/15.
 */
public class MainActivity extends MyActivity {

    private ArrayList<String> days;
    private ArrayList<ArrayList<ArrayList<Object>>> recipesByDay; // Outer array list is to associate 1:1 with days,
    // Second array list is to hold a list of the recipes, inner most array list holds the views within the child

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close);

        drawerLayout.setDrawerListener(drawerToggle);


        days = new ArrayList<String>();
        recipesByDay = new ArrayList<ArrayList<ArrayList<Object>>>();


        ExpandableListView mainList = (ExpandableListView) findViewById(R.id.day_list);
        mainList.setDividerHeight(2);
        mainList.setGroupIndicator(null);
        mainList.setClickable(true);

        prepareList();

        CustomExpandableListAdapter adapter = new CustomExpandableListAdapter(days, recipesByDay);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        adapter.setInflater(inflater, this);

        mainList.setAdapter(adapter);
        for(int i = 0; i < days.size(); i++){
            mainList.expandGroup(i);
        }

        ImageButton searchButton = ((ImageButton) findViewById(R.id.search));
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSearch();
            }
        });



    }

    @Override
    public void onResume(){
        super.onResume();
        prepareList();
        CustomExpandableListAdapter adapter = new CustomExpandableListAdapter(days, recipesByDay);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        adapter.setInflater(inflater, this);

    }


//    private void prepareList() {
//        days.add("Monday");
//        days.add("Tuesday");
////        days.add("Wednesday");
////        days.add("Thursday");
////        days.add("Friday");
//
//        //Monday
//        ArrayList<ArrayList<Object>> day = new ArrayList<ArrayList<Object>>();
//
//        // list_image, recipe_name, nutrition, rating
//        ArrayList<Object> recipe = new ArrayList<Object>();
//        recipe.add(null);
//        recipe.add("Muffins of Doom");
//        recipe.add("NUTRITIONS");
//        recipe.add(getResources().getDrawable(R.drawable.fourstars));
//        day.add(recipe);
//
//        recipesByDay.add(day);
//
//
//        day = new ArrayList<ArrayList<Object>>();
//        recipe = new ArrayList<Object>();
//        recipe.add(null);
//        recipe.add("PASTRAmi");
//        recipe.add("It's pork, man");
//        recipe.add(new ColorDrawable(getResources().getColor(R.color.actionbar_background)));
//        day.add(recipe);
//
//        recipesByDay.add(day);
//
//
//
//    }

    /**
     * Creates the relevant lists of days and recipes by day from existing saved text files
     * based on older implementaiton of the schedule.
     */
    private void prepareList(){
        String[] weekdays = getResources().getStringArray(R.array.weekdays);
        String[] meals = getResources().getStringArray(R.array.meals);

        ArrayList<ArrayList<Object>> day = new ArrayList<ArrayList<Object>>();
        ArrayList<Object> recipe = new ArrayList<Object>();

        for (int i = 0; i < weekdays.length; i++){
            if(days.size() < 7) {
                days.add(weekdays[i]);
            }
            day = new ArrayList<ArrayList<Object>>();

            for (int j = 0; j < meals.length; j++){
                // recipeObject, recipe_name, nutrition, rating
                List<Recipe> mealList = Recipe.loadMeal(buildFileName(i, j), this);
                if (!mealList.isEmpty()){

                    for (int k = 0; k < mealList.size(); k++){
                        recipe = new ArrayList<Object>();

                        Recipe recipeObject = mealList.get(k);
                        recipe.add(recipeObject); // images too small to use
                        recipe.add(recipeObject.getName());
                        recipe.add("<Nutrition data here>");//nutrition
                        recipe.add(recipeObject.getRating());
                        day.add(recipe);
                    }
                }

            }
            recipesByDay.add(day);
        }

    }



    private String buildFileName(int weekday, int meal){
        String[] weekdays = getResources().getStringArray(R.array.weekdays);
        String[] meals = getResources().getStringArray(R.array.meals);
        String combine = weekdays[weekday] + "_" + meals[meal];
        return combine;
    }
    private void setUpActionBar(){
        ActionBar ab = getActionBar();
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(false);


        ab.setIcon(R.drawable.basil_true_white);
        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.actionbar, null);

        TextView title = (TextView) v.findViewById(R.id.title);
        Typeface font = Typeface.createFromAsset(getAssets(),
                "ArkitechMedium.ttf"); // note this is stored in two places, because I wasn't sure wich was correct
        title.setTypeface(font);
        title.setTextColor(Color.WHITE);

        ab.setCustomView(v);

        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
    }

    private void startSearch(){
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }


}
