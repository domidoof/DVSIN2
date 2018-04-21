package com.example.adrian.dvsin;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContainernScreen1 extends AppCompatActivity {

    // TextView Eingabeauffroderung und Menüstruktur

    TextView zurueck, eingabeaufforderung_1, eingabeaufforderung_2, eingabeaufforderung_3, eingabeaufforderung_4, eingabeaufforderung_5;


    // TextView Buchungsbereich

    TextView buchungsnummer_abkuerzung_01, buchungsnummer_abkuerzung_02, buchungsnummer_aktuell_01, buchungsnummer_aktuell_02, buchung_auswaehlen_01_01, buchung_auswaehlen_01_02;


    // verwendete Fonts

    Typeface font_roboto_thin, font_roboto_medium;


    // ACTIVITY starten

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_containern_screen_1);


        // TextView Eingabeauffroderung und Menüstruktur

        zurueck =(TextView) findViewById(R.id.zurueck);
        eingabeaufforderung_1 = (TextView) findViewById(R.id.eingabeaufforderung_1);
        eingabeaufforderung_2 = (TextView) findViewById(R.id.eingabeaufforderung_2);
        eingabeaufforderung_3 = (TextView) findViewById(R.id.eingabeaufforderung_3);
        eingabeaufforderung_4 = (TextView) findViewById(R.id.eingabeaufforderung_4);
        eingabeaufforderung_5 = (TextView) findViewById(R.id.eingabeaufforderung_5);


        // TextView Buchungsbereich zuweisen

        buchungsnummer_abkuerzung_01 =(TextView) findViewById(R.id.buchungsnummer_abkuerzung_01);
        buchungsnummer_abkuerzung_02 = (TextView) findViewById(R.id.buchungsnummer_abkuerzung_02);
        buchungsnummer_aktuell_01 = (TextView) findViewById(R.id.buchungsnummer_aktuell_01);
        buchungsnummer_aktuell_02 = (TextView) findViewById(R.id.buchungsnummer_aktuell_02);
        buchung_auswaehlen_01_01 = (TextView) findViewById(R.id.buchung_auswaehlen_01_01);
        buchung_auswaehlen_01_02 = (TextView) findViewById(R.id.buchung_auswaehlen_01_02);


        // Fonts einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");


        //-- Fonts ANWENDEN --

        // Fonts für Eingabeauffroderung und Menüstruktur setzen

        zurueck.setTypeface(font_roboto_thin);
        eingabeaufforderung_1.setTypeface(font_roboto_thin);
        eingabeaufforderung_2.setTypeface(font_roboto_medium);
        eingabeaufforderung_3.setTypeface(font_roboto_thin);
        eingabeaufforderung_4.setTypeface(font_roboto_thin);
        eingabeaufforderung_5.setTypeface(font_roboto_thin);


        // Fonts für Eingabeauffroderung und Menüstruktur setzen

        buchungsnummer_abkuerzung_01.setTypeface(font_roboto_thin);
        buchungsnummer_abkuerzung_02.setTypeface(font_roboto_thin);
        buchungsnummer_aktuell_01.setTypeface(font_roboto_medium);
        buchungsnummer_aktuell_02.setTypeface(font_roboto_medium);
        buchung_auswaehlen_01_01.setTypeface(font_roboto_thin);
        buchung_auswaehlen_01_02.setTypeface(font_roboto_thin);


    //BUTTONS

        // BUTTON zurück (links oben) aktivieren

        Button zurueck = (Button) findViewById(R.id.zurueck);
        zurueck.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContainernScreen1.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // BUTTON angeklickte Buchung verladen; erste Buchungsnummer

        Button buchung_containern_01 = (Button) findViewById(R.id.buchung_auswaehlen_01_01);
        buchung_containern_01.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContainernScreen1.this, ContainernScreen2.class);
                startActivity(intent);
            }
        });


        // BUTTON angeklickte Buchung verladen; zweite Buchungsnummer

        Button buchung_containern_02 = (Button) findViewById(R.id.buchung_auswaehlen_01_02);
        buchung_containern_02.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContainernScreen1.this, ContainernScreen2.class);
                startActivity(intent);
            }
        });

    }
}