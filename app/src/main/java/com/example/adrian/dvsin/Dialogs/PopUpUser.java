package com.example.adrian.dvsin.Dialogs;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adrian.dvsin.R;

public class PopUpUser extends AppCompatActivity {

    TextView benutzername_aktuell, text_benutzername;
    Typeface font_roboto_thin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popup_user_login);

        // Schiriftart setzen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");

        benutzername_aktuell = (TextView) findViewById(R.id.benutzername_aktuell);
        text_benutzername = (TextView) findViewById(R.id.text_benutzername);
        benutzername_aktuell.setTypeface(font_roboto_thin);
        text_benutzername.setTypeface(font_roboto_thin);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigt = dm.heightPixels;

        getWindow().setLayout((int) (width*.6),(int)(heigt*.2));
        getWindow().setGravity(9);


    }
}
