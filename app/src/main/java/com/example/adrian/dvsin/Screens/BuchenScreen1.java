package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adrian.dvsin.Buchungsklasse.Buchung;
import com.example.adrian.dvsin.MainActivity;
import com.example.adrian.dvsin.R;
import com.example.adrian.dvsin.Schiffsklassen.Schifftyp;

public class BuchenScreen1 extends AppCompatActivity {

    // -- INSTANZVARIABLEN festlegen -- //

    // INT //

    int button_grosses_Schiff_gedrueckt;
    int button_kleines_Schiff_gedrueckt;


    // STRING



    // -- Others -- //

    // TEXTVIEW Menüstruktur und Schiffe

    TextView schrittanzeige, hauptmenue_buchen_text_1, hauptmenue_buchen_text_2, hauptmenue_buchen_text_3,  untermenue_1, schiff_klein_1, schiff_klein_2, schiff_gross_1, schiff_gross_2, beschreibung_schiff_groß, beschreibung_schiff_klein;


    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;

    //penis


    // BUTTONS

    Button zurueck;


    // ImageBUTTONS

    ImageButton vorwaerts;
    ImageButton schiff_klein_onklick;
    ImageButton schiff_gross_onklick;


    // BUCHUNG

    Buchung aktuelleBuchung;

    // SCHIFFTYP

    Schifftyp aktuellesSchiff;


    // BUNDLE

    Bundle wertesammlungBS1;


    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buchen_screen_1);

        // IDs zuordnen

            setIDs();


        // FONTS einbeziehen

            setFonts();


        // FONTS anwenden

            setFontsToIDs();


        // -- BUTTONS  -- //

        // BUTTON "zurueck" drücken

            buttonGetBack();


        // -- BUTTON "vorwaerts" drücken

            buttonNextActivity();


        // -- BUTTON kleines Schiff drücken -- //

            buttonKleinesSchiffWählen();


        // -- BUTTON großes Schiff drücken -- //

            buttonGrossesSchiffWählen();

        // ########## //


        // -- ACTIVITY Ende -- //

        // *** ENDE *** //

    }

    // --- WEITERE Methoden --- //

    private void buttonGrossesSchiffWählen() {

        // BUTTON "schiff_gross_onklick" drücken

        schiff_gross_onklick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Iconfarbe richtig setzen

                schiff_gross_onklick.setImageResource(R.drawable.icon_grosses_schiff_orange);
                schiff_klein_onklick.setImageResource(R.drawable.icon_kleines_schiff);

                // gewählten Schiffstyp erkennen

                button_grosses_Schiff_gedrueckt = 1;
                button_kleines_Schiff_gedrueckt = 0;

                // BUTTON "vorwaerts" sichtbar machen

                vorwaerts.setVisibility(View.VISIBLE);

            }
        });
    }

    private void buttonKleinesSchiffWählen() {

        // BUTTON "schiff_klein_onklick" drücken

        schiff_klein_onklick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Iconfarbe richtig setzen

                schiff_klein_onklick.setImageResource(R.drawable.icon_kleines_schiff_orange);
                schiff_gross_onklick.setImageResource(R.drawable.icon_grosses_schiff);

                // gewählten Schiffstyp erkennen

                button_kleines_Schiff_gedrueckt = 1;
                button_grosses_Schiff_gedrueckt = 0;

                // BUTTON "vorwaerts" sichtbar machen

                vorwaerts.setVisibility(View.VISIBLE);

            }
        });
    }

    private void buttonNextActivity() {

        // BUTTON "vorwaerts" zunächst unsichtbar

            vorwaerts.setVisibility(View.GONE);

        // BUTTON "vorwaerts" drücken

        vorwaerts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // -- ACTIVITY BuchenScreen2 einbeziehen -- //

                Intent intent = new Intent(BuchenScreen1.this, BuchenScreen2.class);

                {

                    // Prüfen ob BUTTON "schiff_klein_onklick" oder BUTTON "schiff_grosses_onklick" gedrückt

                    // großes Schiff gewählt

                    if (button_grosses_Schiff_gedrueckt == 1) {

                        // SCHIFFTYP "aktuellesSchiff" initalisieren

                        aktuellesSchiff = new Schifftyp(54321,"Großes Schiff", getString(R.string.text_schiff_groß), 3, 20);

                    }

                    // kleines Schiff gewählt

                    {
                        if (button_kleines_Schiff_gedrueckt == 1) {

                            // SCHIFFTYP "aktullesSchiff" initalisieren

                            aktuellesSchiff = new Schifftyp(12345,"Kleines Schiff", getString(R.string.text_schiff_klein), 2, 8);
                        }

                    }

                    // BUCHUNG erstellen

                    aktuelleBuchung = new Buchung(000000000, aktuellesSchiff.getTypbezeichnung(), 0, 0, 0);

                    // SCHIFFTYP "aktuellesSchiff" & BUCHUNG "aktuelleBuchung" übergeben

                    intent.putExtra("aktuelleBuchungKEY", aktuelleBuchung);
                    intent.putExtra("aktuellesSchiffKEY", aktuellesSchiff);

                    // ACTIVITY BuchenScreen2 starten

                    startActivity(intent);

                }
            }
        });
    }

    private void buttonGetBack() {

        zurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuchenScreen1.this, MainActivity.class);
                startActivity(intent);


            }
        });

    }

    private void setFontsToIDs() {

        // FONTS für Menüstruktur und Schiffe SETZEN

        zurueck.setTypeface(font_roboto_thin);
        schrittanzeige.setTypeface(font_roboto_thin);
        untermenue_1.setTypeface(font_roboto_thin);

        hauptmenue_buchen_text_1.setTypeface(font_roboto_thin);
        hauptmenue_buchen_text_2.setTypeface(font_roboto_medium);
        hauptmenue_buchen_text_3.setTypeface(font_roboto_thin);

        schiff_klein_1.setTypeface(font_roboto_medium);
        schiff_klein_2.setTypeface(font_roboto_thin);
        schiff_gross_1.setTypeface(font_roboto_medium);
        schiff_gross_2.setTypeface(font_roboto_thin);

        beschreibung_schiff_groß.setTypeface(font_roboto_thin);
        beschreibung_schiff_klein.setTypeface(font_roboto_thin);

    }

    private void setFonts() {

        // FONTS einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(), "fonts/roboto-medium.ttf");

    }

    private void setIDs() {

        // IDs Menüstruktur und Schiffe ZUORDNEN

        // TEXTVIEW

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


        // BUTTONS

        zurueck = (Button) findViewById(R.id.zurueck);


        // ImageBUTTONS

        vorwaerts = (ImageButton) findViewById(R.id.vorwaerts);
        schiff_klein_onklick = (ImageButton) findViewById(R.id.kleines_schiff_auswahl);
        schiff_gross_onklick = (ImageButton) findViewById(R.id.grosses_schiff_auswahl);

    }

    public void onBackPressed() {

            // Übergang von einer zur nächsten, passenden Activity

            Intent intent = new Intent(BuchenScreen1.this, MainActivity.class);
            startActivity(intent);
        }

}



