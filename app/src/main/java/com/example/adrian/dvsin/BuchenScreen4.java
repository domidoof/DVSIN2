package com.example.adrian.dvsin;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class BuchenScreen4 extends AppCompatActivity {

    // TextView Menüstruktur

    TextView zurueck;


    // TextView Buchungsbestätigungsbereich

    TextView text_buchungsbestaetigung_teil_1, text_buchungsbestaetigung_teil_2, text_buchungsbestaetigung_teil_3, text_buchungsbestaetigung_teil_4, buchungsnummer_abkuerzung, buchungsnummer_abktuell;


    // verwendete Fonts

    Typeface font_roboto_thin, font_roboto_medium;


    // ACTIVITY starten

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buchen_screen_4);


        // TextView Menüstruktur

        zurueck =(TextView) findViewById(R.id.zurueck);

        // TextView Buchungsbereich zuweisen

        text_buchungsbestaetigung_teil_1 =(TextView) findViewById(R.id.text_buchungsbestaetigung_teil_1);
        text_buchungsbestaetigung_teil_2 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_2);
        text_buchungsbestaetigung_teil_3 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_3);
        text_buchungsbestaetigung_teil_4 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_4);

        buchungsnummer_abkuerzung = (TextView) findViewById(R.id.buchungsnummer_abkuerzung);
        buchungsnummer_abktuell = (TextView) findViewById(R.id.buchungsnummer_aktuell);

        // Fonts einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");


        //-- Fonts ANWENDEN --

        // Fonts für Eingabeauffroderung und Menüstruktur setzen

        zurueck.setTypeface(font_roboto_thin);

        // Fonts für Eingabeauffroderung und Menüstruktur setzen

        text_buchungsbestaetigung_teil_1.setTypeface(font_roboto_thin);
        text_buchungsbestaetigung_teil_2.setTypeface(font_roboto_thin);
        text_buchungsbestaetigung_teil_3.setTypeface(font_roboto_medium);
        text_buchungsbestaetigung_teil_4.setTypeface(font_roboto_thin);

        buchungsnummer_abkuerzung.setTypeface(font_roboto_thin);
        buchungsnummer_abktuell.setTypeface(font_roboto_medium);


        // INHALT Activity wählen --- //

        // Inhalt: Aktuelle Buchungseigenschaften von letzter Aktivity holen

        final Buchung aktuelleBuchung = (Buchung) getIntent().getParcelableExtra("aktuelleBuchungKEY");

        buchungsnummer_abktuell.setText(Integer.toString((aktuelleBuchung.getBuchungsID())));

    //BUTTONS

        // BUTTON zurück (links oben) aktivieren

        Button zurueck = (Button) findViewById(R.id.zurueck);
        zurueck.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuchenScreen4.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // BUTTON angeklickte Buchung verladen; erste Buchungsnummer

        ImageButton containern_gleich_starten = (ImageButton) findViewById(R.id.buchung_containern);
        containern_gleich_starten.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuchenScreen4.this, ContainernScreen1.class);
                startActivity(intent);
            }
        });

    }
}