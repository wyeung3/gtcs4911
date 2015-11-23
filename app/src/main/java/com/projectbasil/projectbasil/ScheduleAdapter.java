package com.projectbasil.projectbasil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Walton on 11/22/2015.
 * Custom adapter for Schedule
 */
public class ScheduleAdapter extends BaseAdapter implements ListAdapter{
    private List<Recipe> recipeList;
    private Context context;
    private String day_meal;

    public ScheduleAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    public ScheduleAdapter(Context context, List<Recipe> recipeList, String day_meal) {
        this.context = context;
        this.recipeList = recipeList;
        this.day_meal = day_meal;
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int index) {
        return recipeList.get(index);
    }

    @Override
    public long getItemId(int index) {
        return Long.valueOf(recipeList.get(index).getRecipeId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.recipe_item, null);
        }

        //Handle TextView, display recipe name
        TextView listRecipeName = (TextView)view.findViewById(R.id.recipe_name);
        listRecipeName.setText(recipeList.get(position).getName());

        //Handles Button, for deleting recipe from saved file
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deleted = recipeList.get(position).deleteRecipe(recipeList.get(position).getRecipeId(), day_meal, context);
                if(deleted == 0) {
                    recipeList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

        return view;
    }
}
