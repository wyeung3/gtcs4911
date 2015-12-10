package com.projectbasil.projectbasil;

import android.app.Activity;
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
 * Created by bae on 12/2/15.
 */
public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<String> days;
    private ArrayList<ArrayList<ArrayList<Object>>> recipesByDay;

    public CustomExpandableListAdapter(ArrayList<String> days, ArrayList<ArrayList<ArrayList<Object>>> recipesByDay){
        this.days = days;
        this.recipesByDay = recipesByDay;

    }

    public void setInflater(LayoutInflater inflater, Activity activity){
        this.inflater = inflater;
        this.activity = activity;
    }
    @Override
    public int getGroupCount() {
        return days.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return recipesByDay.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_group, null).findViewById(R.id.list_group);
        }
        ((CheckedTextView) convertView).setText(days.get(groupPosition));
        ((CheckedTextView) convertView).setChecked(isExpanded);


        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
        }
        // list_image, recipe_name, nutrition, rating
        if(recipesByDay.get(groupPosition).get(childPosition).get(0) != null){
            ((Recipe) recipesByDay.get(groupPosition).get(childPosition).get(0)).LoadImageFromURL((ImageView) convertView.findViewById(R.id.list_image));
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

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    @Override
    public void onGroupCollapsed(int groupPosition){
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition){
        super.onGroupExpanded(groupPosition);
    }
}
