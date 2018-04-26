package com.example.adrian.dvsin;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrian.dvsin.Screens.ContainernScreen1;
import com.example.adrian.dvsin.Screens.BuchenScreen1;

public class MainActivity extends AppCompatActivity {

// -- INSTANZVARIABLEN festlegen -- //

    // int

    int saufCount;


    // String


    // -- Others -- //

    // TEXTVIEW 

    TextView hauptmenue, buchen_beschreibung, containern_buchen, doku_beschreibung, suchen_bescheibung;


    // FONTS

    Typeface font_roboto_thin;


    // BUTTONS

    Button zurueck;


    // IMAGEBUTTON

    ImageButton menuepunkt_buchen, menuepunkt_containern;
    ImageButton button_benuter_informationen;


    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        // IDs zuordnen

        setIDs();


        // FONTS einbeziehen

        setFonts();


        // -- FONTS ANWENDEN

        setFontsToIDs();


        // -- BUTTONS  -- //

        // BUTTON "menuepunkt_buchen" drücken

        startMethodeBuchen();


        // BUTTON "menuepunkt_containern" drücken

        startMethodeContainern();


        // BUTTON "button_benuter_informationen" drücken

        // showUserInformation();


        // ########## //

        // -- ACTIVITY Ende -- //

        // *** ENDE *** //

    }

    // --- WEITERE Methoden --- //

    private void startMethodeBuchen() {

        //	FUNKTION "Buchen" wird gestartet

        menuepunkt_buchen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BuchenScreen1.class);
                startActivity(intent);
            }
        });

    }

    private void startMethodeContainern() {

        //	FUNKTION "Containern" wird gestartet

        menuepunkt_containern.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContainernScreen1.class);
                startActivity(intent);
            }
        });

    }

    /* privat void showUserInformation() {

    		// !!! NICHT AUF AKTUELLEM STAND !!! //
		
        Anzeigen der Benutzerinformationen über Button onCklick

        final ImageButton button_benuter_informationen = (ImageButton) findViewById(R.id.user_id);
        button_benuter_informationen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                PopupWindow popupWindow = new PopupWindow(R.layout.activity_popup_user_login, 5, 5, true);
                  popupMenu.getMenuInflater()
				  
		});
		
		}
	} */

    @Override
    public void onBackPressed(){

        // APPLICATION go to Background; Keine Rückkehr zum Loginbildschirm!

        moveTaskToBack(true);

        }

    /* public void ShowPopup(View v){
		
		// !!! NICHT AUF AKTUELLEM STAND !!! //
		
        benutzer_informationen.setContentView(R.layout.activity_popup_user_login);



        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");

        benutzername_aktuell = (TextView) findViewById(R.id.benutzername_aktuell);
        text_benutzername = (TextView) findViewById(R.id.text_benutzername);
        benutzername_aktuell.setTypeface(font_roboto_thin);
        text_benutzername.setTypeface(font_roboto_thin);


        benutzer_informationen.show();
		
        */

    private void setFontsToIDs() {

        // FONTS SETZEN

        hauptmenue.setTypeface(font_roboto_thin);

        buchen_beschreibung.setTypeface(font_roboto_thin);
        containern_buchen.setTypeface(font_roboto_thin);
        doku_beschreibung.setTypeface(font_roboto_thin);
        suchen_bescheibung.setTypeface(font_roboto_thin);

    }

    private void setFonts() {

        // Fonts einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");

    }

    private void setIDs() {

        // IDs ZUORDNEN

        // TEXTVIEW

        hauptmenue = (TextView) findViewById(R.id.hauptmenue);

        buchen_beschreibung = (TextView) findViewById(R.id.buchen_beschreibung);
        containern_buchen = (TextView) findViewById(R.id.containern_beschreibung);
        doku_beschreibung = (TextView) findViewById(R.id.doku_beschreibung);
        suchen_bescheibung = (TextView) findViewById(R.id.suchen_beschreibung);

        // IMAGEBUTTON

        menuepunkt_buchen = (ImageButton) findViewById(R.id.buchen_button);
        menuepunkt_containern = (ImageButton) findViewById(R.id.containern_button);

        //Int
        saufCount = 0;

    }

    public void textSaufenPlay(View view) {
        saufCount++;

        if (saufCount == 2) {
            Toast.makeText(this, "oh oh was passiert hier?", Toast.LENGTH_SHORT).show();
        }

        if (saufCount == 4) {
            Toast.makeText(this, "einmal noch dann...", Toast.LENGTH_SHORT).show();
        }

        if (saufCount == 5) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.saufen_morgens_immer);
            mp.start();
        }
    }

}