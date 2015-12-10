package com.projectbasil.projectbasil;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created to allow the ExpandableList to be used in MainActivity, keeps track of the lists of
 * days and recipes, and returns relevant views
 */
public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<String> days;
    private ArrayList<ArrayList<ArrayList<Object>>> recipesByDay;

    /**
     *
     * @param days - ArrayList of Strings that represent days of the week
     * @param recipesByDay - ArrayList (for days) of ArrayLists
     *                     (for recipes) of ArrayLists (for items in
     *                     the list_item layout) of Objects (actual items to be put in the layout)
     * instantiates the lists used to fill the ExpandableList
     */
    public CustomExpandableListAdapter(ArrayList<String> days, ArrayList<ArrayList<ArrayList<Object>>> recipesByDay){
        this.days = days;
        this.recipesByDay = recipesByDay;

    }

    /**
     *
     * @param inflater
     * @param activity
     * gives the adapter an inflater to pass out views
     */
    public void setInflater(LayoutInflater inflater, Activity activity){
        this.inflater = inflater;
        this.activity = activity;
    }

    /**
     *
     * @return number of days being considered (always 7)
     */
    @Override
    public int getGroupCount() {
        return days.size();
    }

    /**
     *
     * @param groupPosition ( day of the week int )
     * @return number of recipes for that day
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return recipesByDay.get(groupPosition).size();
    }

    /**
     *
     * @param groupPosition
     * @return null
     * unused
     */
    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    /**
     *
     * @param groupPosition
     * @param childPosition
     * @return null
     * unused
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    /**
     *
     * @param groupPosition
     * @return 0
     * unused
     */
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    /**
     *
     * @param groupPosition
     * @param childPosition
     * @return 0
     * unused
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    /**
     *
     * @return false
     * unused
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     *
     * @param groupPosition day by int
     * @param isExpanded
     * @param convertView previously used view, or null
     * @param parent ViewGroup
     * @return an inflated list_group layout with the day of the week
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_group, null).findViewById(R.id.list_group);
        }
        ((CheckedTextView) convertView).setText(days.get(groupPosition));
        ((CheckedTextView) convertView).setChecked(isExpanded);


        return convertView;

    }

    /**
     *
     * @param groupPosition day of the week
     * @param childPosition position of recipe in list
     * @param isLastChild
     * @param convertView a view to overwrite
     * @param parent ViewGroup
     * @return An inflated list_item layout with the image, recipe name, nutrition, and rating populated from
     *         the ArrayList of objects
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
        }
        // list_image, recipe_name, nutrition, rating
        if(recipesByDay.get(groupPosition).get(childPosition).get(0) != null){
            ((Recipe) recipesByDay.get(groupPosition).get(childPosition).get(0)).LoadImageFromURL((ImageView) convertView.findViewById(R.id.list_image));
            convertView.setOnClickListener(new RecipeOnClickListener((Recipe) recipesByDay.get(groupPosition).get(childPosition).get(0)));
        }
        if(recipesByDay.get(groupPosition).get(childPosition).get(1) != null){
            ((TextView) convertView.findViewById(R.id.recipe_name)).setText((String) recipesByDay.get(groupPosition).get(childPosition).get(1));
        }
        if(recipesByDay.get(groupPosition).get(childPosition).get(2) != null){
            ((TextView) convertView.findViewById(R.id.nutrition)).setText((String) recipesByDay.get(groupPosition).get(childPosition).get(2));
        }
        if(recipesByDay.get(groupPosition).get(childPosition).get(3) != null){
            ((RatingBar) convertView.findViewById(R.id.rating)).setNumStars(((int) recipesByDay.get(groupPosition).get(childPosition).get(3))/20);
        }

        return convertView;


    }

    /**
     *
     * @param groupPosition day of the week int
     * @param childPosition recipe position int
     * @return false
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    /**
     *
     * @param groupPosition int
     */
    @Override
    public void onGroupCollapsed(int groupPosition){
        super.onGroupCollapsed(groupPosition);
    }

    /**
     *
     * @param groupPosition int
     */
    @Override
    public void onGroupExpanded(int groupPosition){
        super.onGroupExpanded(groupPosition);
    }

    public class RecipeOnClickListener implements View.OnClickListener{

        Recipe recipe;

        public RecipeOnClickListener(Recipe recipe){
            this.recipe = recipe;
        }

        @Override
        public void onClick(View v){
            GlobalVars instance = GlobalVars.getInstance();
            instance.setMostRecentRecipe(recipe);
            Intent intent = new Intent(activity, RecipeDetailsActivity.class);
            activity.startActivity(intent);

        }
    }
}
