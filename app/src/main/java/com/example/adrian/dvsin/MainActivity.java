package com.example.adrian.dvsin;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView hauptmenue, buchen_beschreibung, containern_buchen, doku_beschreibung, suchen_bescheibung;
    Typeface font_roboto_thin;
    ImageButton button_benuter_informationen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        hauptmenue = (TextView) findViewById(R.id.hauptmenue);
        buchen_beschreibung = (TextView) findViewById(R.id.buchen_beschreibung);
        containern_buchen = (TextView) findViewById(R.id.containern_beschreibung);
        doku_beschreibung = (TextView) findViewById(R.id.doku_beschreibung);
        suchen_bescheibung = (TextView) findViewById(R.id.suchen_beschreibung);

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");

        hauptmenue.setTypeface(font_roboto_thin);
        buchen_beschreibung.setTypeface(font_roboto_thin);
        containern_buchen.setTypeface(font_roboto_thin);
        doku_beschreibung.setTypeface(font_roboto_thin);
        suchen_bescheibung.setTypeface(font_roboto_thin);


        //Hauptmenübuttons werden aktiviert

        //Buchen

        ImageButton menuepunkt_buchen = (ImageButton) findViewById(R.id.buchen_button);
        menuepunkt_buchen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BuchenScreen1.class);
                startActivity(intent);
            }
        });

        //Containern

        ImageButton menuepunkt_containern = (ImageButton) findViewById(R.id.containern_button);
        menuepunkt_containern.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContainernScreen1.class);
                startActivity(intent);
            }
        });




        /*
        Anzeigen der Benutzerinformationen über Button onCklick

        final ImageButton button_benuter_informationen = (ImageButton) findViewById(R.id.user_id);
        button_benuter_informationen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                PopupWindow popupWindow = new PopupWindow(R.layout.activity_popup_user_login, 5, 5, true);
                  popupMenu.getMenuInflater()
           */


    }

    // App in den Hintergrund auf Klick von Smartphonebutton "zurück"; Keine Rückkehr zum Loginbildschirm

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);

    }
}


    /*public void ShowPopup(View v){
        benutzer_informationen.setContentView(R.layout.activity_popup_user_login);



        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");

        benutzername_aktuell = (TextView) findViewById(R.id.benutzername_aktuell);
        text_benutzername = (TextView) findViewById(R.id.text_benutzername);
        benutzername_aktuell.setTypeface(font_roboto_thin);
        text_benutzername.setTypeface(font_roboto_thin);


        benutzer_informationen.show();
        */

