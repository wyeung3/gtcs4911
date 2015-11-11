package com.projectbasil.projectbasil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Walton on 11/4/2015.
 * Custome ListAdapter for listing searhc results
 */
public class SearchAdapter extends BaseAdapter implements ListAdapter {
    private List<Recipe> recipeList;
    private Context context;

    public SearchAdapter(List<Recipe> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
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
        return Long.parseLong(recipeList.get(index).getRecipeId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.search_item, null);
        }

        //Handle TextView, display item name
        TextView listRecipeName = (TextView)view.findViewById(R.id.recipe_name);
        listRecipeName.setText(recipeList.get(position).getName());

        //Handle TextView, display item social rank
        TextView listRecipeRank = (TextView)view.findViewById(R.id.recipe_name);
        listRecipeRank.setText(recipeList.get(position).getRating());

        return view;
    }
}
