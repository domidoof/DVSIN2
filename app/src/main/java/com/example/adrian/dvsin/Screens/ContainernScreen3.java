package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adrian.dvsin.MainActivity;
import com.example.adrian.dvsin.R;

public class ContainernScreen3 extends AppCompatActivity {

    // TextView Eingabeauffroderung und Menüstruktur

    TextView zurueck, text_buchungsbestaetigung_teil_1, text_buchungsbestaetigung_teil_2, text_buchungsbestaetigung_teil_3, text_buchungsbestaetigung_teil_4;


    // TextView Buchungsbereich

    TextView buchungsnummer_abkuerzung, buchungsnummer_aktuell;


    // verwendete Fonts

    Typeface font_roboto_thin, font_roboto_medium;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_containern_screen_3);


        // TextView Eingabeauffroderung und Menüstruktur

        zurueck =(TextView) findViewById(R.id.zurueck);
        text_buchungsbestaetigung_teil_1 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_1);
        text_buchungsbestaetigung_teil_2 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_2);
        text_buchungsbestaetigung_teil_3 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_3);
        text_buchungsbestaetigung_teil_4 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_4);

        // TextView Buchung zuweisen

        buchungsnummer_abkuerzung =(TextView) findViewById(R.id.buchungsnummer_abkuerzung_01);
        buchungsnummer_aktuell = (TextView) findViewById(R.id.buchungsnummer_aktuell_01);


        // Fonts einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");


        //-- Fonts ANWENDEN --

        // Fonts für Eingabeauffroderung und Menüstruktur setzen

        zurueck.setTypeface(font_roboto_thin);
        text_buchungsbestaetigung_teil_1.setTypeface(font_roboto_thin);
        text_buchungsbestaetigung_teil_2.setTypeface(font_roboto_medium);
        text_buchungsbestaetigung_teil_3.setTypeface(font_roboto_thin);
        text_buchungsbestaetigung_teil_4.setTypeface(font_roboto_thin);

        // Fonts für Eingabeauffroderung und Menüstruktur setzen

        buchungsnummer_abkuerzung.setTypeface(font_roboto_thin);
        buchungsnummer_aktuell.setTypeface(font_roboto_medium);


    //BUTTONS

        // BUTTON zurück (links oben) aktivieren

        Button zurueck = (Button) findViewById(R.id.zurueck);
        zurueck.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContainernScreen3.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // BUTTON angeklickte Buchung verladen; erste Buchungsnummer

        ImageButton buchung_containern_done = (ImageButton) findViewById(R.id.buchung_auswaehlen_01_01);
        buchung_containern_done.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContainernScreen3.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}