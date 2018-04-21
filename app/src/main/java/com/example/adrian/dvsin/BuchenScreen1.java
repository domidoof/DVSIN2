package com.example.adrian.dvsin;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.annotation.Target;

public class BuchenScreen1 extends AppCompatActivity {


    //######## Github Text #########

// TEST -- FUNKTION Buchen -- //

    int button_grosses_Schiff_gedrueckt;
    int button_kleines_Schiff_gedrueckt;

// ENDE TEST 	

    // TextView Menüstruktur und Schiffe

    TextView zurueck, schrittanzeige, hauptmenue_buchen_text_1, hauptmenue_buchen_text_2, hauptmenue_buchen_text_3,  untermenue_1, schiff_klein_1, schiff_klein_2, schiff_gross_1, schiff_gross_2, beschreibung_schiff_groß, beschreibung_schiff_klein;


    // verwendete Fonts

    Typeface font_roboto_thin, font_roboto_medium;


    // ACTIVITY starten

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buchen_screen_1);


        // Textfelder Menüstruktur und Schiffe ZUORDNEN

        zurueck = (TextView) findViewById(R.id.zurueck);
        schrittanzeige = (TextView) findViewById(R.id.schrittanzeige);
        untermenue_1 = (TextView) findViewById(R.id.untermenue_1);
        hauptmenue_buchen_text_1 = (TextView) findViewById(R.id.hauptmenue_buchen_text_1);
        hauptmenue_buchen_text_2 = (TextView) findViewById(R.id.hauptmenue_buchen_text_2);
        hauptmenue_buchen_text_3 = (TextView) findViewById(R.id.hauptmenue_buchen_text_3);
        schiff_gross_1 = (TextView) findViewById(R.id.schiff_gross_1);
        schiff_gross_2 = (TextView) findViewById(R.id.schiff_gross_2);
        schiff_klein_1 = (TextView) findViewById(R.id.schiff_klein_1);
        schiff_klein_2 = (TextView) findViewById(R.id.schiff_klein_2);
        beschreibung_schiff_groß = (TextView) findViewById(R.id.beschreibung_schiff_groß);
        beschreibung_schiff_klein = (TextView) findViewById(R.id.beschreibung_schiff_klein);


        // Fonts einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(), "fonts/roboto-medium.ttf");


        //-- Fonts ANWENDEN --

        // Fonts für Eingabeauffroderung und Menüstruktur SETZEN

        zurueck.setTypeface(font_roboto_thin);
        schrittanzeige.setTypeface(font_roboto_thin);
        hauptmenue_buchen_text_1.setTypeface(font_roboto_thin);
        hauptmenue_buchen_text_2.setTypeface(font_roboto_medium);
        hauptmenue_buchen_text_3.setTypeface(font_roboto_thin);
        untermenue_1.setTypeface(font_roboto_thin);
        schiff_klein_1.setTypeface(font_roboto_medium);
        schiff_klein_2.setTypeface(font_roboto_thin);
        schiff_gross_1.setTypeface(font_roboto_medium);
        schiff_gross_2.setTypeface(font_roboto_thin);
        beschreibung_schiff_groß.setTypeface(font_roboto_thin);
        beschreibung_schiff_klein.setTypeface(font_roboto_thin);


        //BUTTONS

        // BUTTON zurück (links oben) aktivieren

        Button zurueck = (Button) findViewById(R.id.zurueck);
        zurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuchenScreen1.this, MainActivity.class);
                startActivity(intent);


            }
        });


        // BUTTON Definition

        // -- BUTTON zur nächsten Activity/Buchung starten SETZEN--

        final ImageButton vorwaerts = (ImageButton) findViewById(R.id.vorwaerts);

        // Button "vorwärts" zunächst unsichtbar

        vorwaerts.setVisibility(View.GONE);


        // -- BUTTON kleines Schiff SETZEN --

        final ImageButton schiff_klein_onklick = (ImageButton) findViewById(R.id.kleines_schiff_auswahl);


        // -- BUTTON großes Schiff SETZEN --

        final ImageButton schiff_gross_onklick = (ImageButton) findViewById(R.id.grosses_schiff_auswahl);



        // KLICK BUTTON "schiff_klein_onklick"

        schiff_klein_onklick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Iconfarbe richtig setzen und Buton "vorwaerts" sichtbar machen

                schiff_klein_onklick.setImageResource(R.drawable.icon_kleines_schiff_gross_v2_orange);

                button_kleines_Schiff_gedrueckt = 1;

                schiff_gross_onklick.setImageResource(R.drawable.icon_grosses_schiff_gross_v1);

                button_grosses_Schiff_gedrueckt = 0;

                vorwaerts.setVisibility(View.VISIBLE);

            }
        });


        // KLICK BUTTON "schiff_grosses_onklick"

        schiff_gross_onklick.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                schiff_gross_onklick.setImageResource(R.drawable.icon_grosses_schiff_gross_v2_orange);

                button_grosses_Schiff_gedrueckt = 1;

                schiff_klein_onklick.setImageResource(R.drawable.icon_kleines_schiff_gross_v1);

                button_kleines_Schiff_gedrueckt = 0;

                vorwaerts.setVisibility(View.VISIBLE);

            }
        });



        // KLICK BUTTON "vorwaerts"

        vorwaerts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                // TEST -- FUNKTION Buchen durchfürhen -- //

                {
                    if (button_grosses_Schiff_gedrueckt == 1) {

                        Intent intent = new Intent(BuchenScreen1.this, BuchenScreen2.class);

                        Buchung aktuelleBuchung = new Buchung(987654321,"Großes Schiff", 0, 0, 0);

                        intent.putExtra("aktuelleBuchungKEY", aktuelleBuchung);

                        startActivity(intent);


                    }


                    {
                        if (button_kleines_Schiff_gedrueckt == 1) {

                            Intent intent = new Intent(BuchenScreen1.this, BuchenScreen2.class);

                            Buchung aktuelleBuchung = new Buchung(777777777,"Kleines Schiff", 0, 0, 0);

                            intent.putExtra("aktuelleBuchungKEY", aktuelleBuchung);

                            startActivity(intent);

                        }

                    }
                }
            }
        });



    }
}



