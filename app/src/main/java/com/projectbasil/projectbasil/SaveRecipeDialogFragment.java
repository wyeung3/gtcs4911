package com.projectbasil.projectbasil;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SaveRecipeDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //inflate custom view
        View view = inflater.inflate(R.layout.fragment_save_recipe_dialog, null);
        //find, build, set spinner for days of the week
        String[] weekdays = getResources().getStringArray(R.array.weekdays);
        ArrayAdapter<String> weekdaysAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, weekdays);
        weekdaysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner daySpinner = (Spinner) view.findViewById(R.id.dayPicker);
        daySpinner.setAdapter(weekdaysAdapter);
        //find, build, set spinner for meals in a day
        String[] meals = getResources().getStringArray(R.array.meals);
        ArrayAdapter<String> mealsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, meals);
        mealsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner mealSpinner = (Spinner) view.findViewById(R.id.mealPicker);
        mealSpinner.setAdapter(mealsAdapter);

        builder.setView(view);
        builder.setCancelable(true);
        builder.setTitle("Pick a day and meal");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                GlobalVars instance = GlobalVars.getInstance();
                Recipe recipe = instance.getMostRecentRecipe();
                //build string from picks
                String meal = daySpinner.getSelectedItem().toString() + "_" + mealSpinner.getSelectedItem().toString();
                int result = recipe.saveRecipe(meal, getContext());
                if (result == 0) {
                    Toast.makeText(getContext(), "Recipe saved!", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(getContext(), "Recipe not saved...", Toast.LENGTH_SHORT);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //dialog.dismiss();
                dialog.cancel();
            }
        });

        //Return the AlertDialog from create()
        return builder.create();
    }
}
