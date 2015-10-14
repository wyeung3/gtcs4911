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

/**
 * Created by Walton on 10/14/2015.
 */
public class InventoryAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> inventory = new ArrayList<String>();
    private Context context;

    public InventoryAdapter(ArrayList<String> list, Context context) {
        this.inventory = inventory;
        this.context = context;
    }

    @Override
    public int getCount() {
        return inventory.size();
    }

    @Override
    public Object getItem(int index) {
        return inventory.get(index);
    }

    @Override
    public long getItemId(int index) {
        //return list.get(index).getId();
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.inventory_item, null);
        }

        //Handle TextView, display item name
        TextView listItemText = (TextView)view.findViewById(R.id.list_item);
        listItemText.setText(inventory.get(position));

        //Handle edit button, add onClickListener
        Button editBtn = (Button)view.findViewById(R.id.edit_btn);

        editBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //transition to edit UI?
                String selectedItem = inventory.get(position);
                Toast.makeText(context.getApplicationContext(), "Inventory Item Selected : " + selectedItem, Toast.LENGTH_LONG).show();
                notifyDataSetChanged(); //hey look, data changed!
            }
        });

        return view;
    }
}
