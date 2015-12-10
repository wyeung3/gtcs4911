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
 * Main focus of the app, the upcoming week schedule screen. Displays upcoming meals in an
 * ExpandableList so that each day is collapsible.
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

    /**
     * Ensures that the ExpandableList gets updated on resume in case of any changes
     */
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
     * based on older implementation of the schedule.
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


    /**
     *
     * @param weekday int day of the week
     * @param meal int meal on that day (0-2 breakfast lunch dinner)
     * @return String that is the file name that should contain the representation of that meal
     */
    private String buildFileName(int weekday, int meal){
        String[] weekdays = getResources().getStringArray(R.array.weekdays);
        String[] meals = getResources().getStringArray(R.array.meals);
        String combine = weekdays[weekday] + "_" + meals[meal];
        return combine;
    }

    /**
     * starts the search activity from the magnifying glass in the top right
     */
    private void startSearch(){
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    /**
     * used for drawer continuity, but drawer is currently empty
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    /**
     * used for drawer continuity, but drawer is currently empty
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * used for drawer continuity, but drawer is currently empty
     * @param item
     * @return
     */
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
