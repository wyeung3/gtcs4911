package com.projectbasil.projectbasil;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by bae on 12/9/15.
 */
public class MyActivity extends Activity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setUpActionBar();


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

}
