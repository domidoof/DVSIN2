package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adrian.dvsin.Schiffsklassen.Ebene;
import com.example.adrian.dvsin.MainActivity;
import com.example.adrian.dvsin.R;

public class ContainernScreen2 extends AppCompatActivity {


    // TextView Menüstruktur

    TextView zurueck, text_ebene, ebenennummer, container_id_text, container_id_nummer, order_id_text, order_id_nummer;

    // String

    String orderID;

    // TextView Tabelle

    TextView zelle_01, zelle_02, zelle_03, zelle_04, zelle_05;
    TextView zelle_A0, zelle_B0, zelle_C0, zelle_D0, zelle_E0;

    // TextView Containeranzeige

    TextView naechster_container_groesse, naechster_container_wort, aktueller_container_groesse, aktueller_container_wort;

    // verwendete Fonts

    Typeface font_roboto_thin, font_roboto_medium;


    // Ebenentest BEGINN


    TextView aktuelleEbeneAnzeige;

    Ebene schiffsebene = new Ebene(8);


    // Ebenentest ENDE


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_containern_screen_2);

        Intent intent = getIntent();
        orderID = intent.getStringExtra("ORDER_ID");
        Log.d("WHERE_IS_THE_ORDER", orderID);


        // TextView Menüstruktur zuweisen

        zurueck = (TextView) findViewById(R.id.zurueck);
        text_ebene = (TextView) findViewById(R.id.text_ebene);
        ebenennummer = (TextView) findViewById(R.id.ebenen_nummer);
        container_id_text = (TextView) findViewById(R.id.container_id_text);
        container_id_nummer = (TextView) findViewById(R.id.container_id_nummer);
        order_id_text = findViewById(R.id.order_id_text);
        order_id_nummer = findViewById(R.id.order_id_nummer);


        //TextView Tabelle zuweisen

        zelle_01 = (TextView) findViewById(R.id.zelle_01);
        zelle_02 = (TextView) findViewById(R.id.zelle_02);
        zelle_03 = (TextView) findViewById(R.id.zelle_03);
        zelle_04 = (TextView) findViewById(R.id.zelle_04);
        zelle_05 = (TextView) findViewById(R.id.zelle_05);

        zelle_A0 = (TextView) findViewById(R.id.zelle_A0);
        zelle_B0 = (TextView) findViewById(R.id.zelle_B0);
        zelle_C0 = (TextView) findViewById(R.id.zelle_C0);
        zelle_D0 = (TextView) findViewById(R.id.zelle_D0);
        zelle_E0 = (TextView) findViewById(R.id.zelle_E0);


        // TextView Containeranzeige zuweisen

        naechster_container_groesse = (TextView) findViewById(R.id.naechster_container_groesse);
        naechster_container_wort = (TextView) findViewById(R.id.naechster_container_wort);
        aktueller_container_groesse = (TextView) findViewById(R.id.aktueller_container_groesse);
        aktueller_container_wort = (TextView) findViewById(R.id.aktueller_container_wort);

        // Fonts einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(), "fonts/roboto-medium.ttf");


        //-- Fonts ANWENDEN --

        // Fonts für Menüpunkte setzen

        zurueck.setTypeface(font_roboto_thin);
        text_ebene.setTypeface(font_roboto_thin);
        ebenennummer.setTypeface(font_roboto_thin);
        container_id_text.setTypeface(font_roboto_thin);
        container_id_nummer.setTypeface(font_roboto_medium);
        order_id_text.setTypeface(font_roboto_thin);
        order_id_nummer.setTypeface(font_roboto_medium);

        //set text für orderid
        order_id_nummer.setText(orderID);


        // Fonts für Tabelle setzen

        zelle_01.setTypeface(font_roboto_thin);
        zelle_02.setTypeface(font_roboto_thin);
        zelle_03.setTypeface(font_roboto_thin);
        zelle_04.setTypeface(font_roboto_thin);
        zelle_05.setTypeface(font_roboto_thin);

        zelle_A0.setTypeface(font_roboto_thin);
        zelle_B0.setTypeface(font_roboto_thin);
        zelle_C0.setTypeface(font_roboto_thin);
        zelle_D0.setTypeface(font_roboto_thin);
        zelle_E0.setTypeface(font_roboto_thin);


        // Fonts für Containeranzeige setzen

        naechster_container_groesse.setTypeface(font_roboto_medium);
        naechster_container_wort.setTypeface(font_roboto_thin);
        aktueller_container_groesse.setTypeface(font_roboto_medium);
        aktueller_container_wort.setTypeface(font_roboto_thin);


        //-- BUTTONS

        // BUTTON zurück (links oben) aktivieren

        Button zurueck = (Button) findViewById(R.id.zurueck);
        zurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContainernScreen2.this, MainActivity.class);
                startActivity(intent);
            }
        });


        // BUTTON vorwärts zur Activity activity_containern_screen_3

        ImageButton vorwaerts = (ImageButton) findViewById(R.id.vorwaerts);
        vorwaerts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContainernScreen2.this, ContainernScreen3.class);
                startActivity(intent);
            }

        });


        // EBENE Allgemein

        aktuelleEbeneAnzeige = (TextView) findViewById(R.id.ebenen_nummer);

        final ImageButton ebene_oben = (ImageButton) findViewById(R.id.ebene_oben);
        final ImageButton ebene_unten = (ImageButton) findViewById(R.id.ebene_unten);


        // schiffsebene nach oben wechseln

        ebene_oben.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                schiffsebene.getAktuelleEbene();

                    // Neue Ebenenennummer berechnen, schiffsebene nach oben wandern

                {
                    if (schiffsebene.aktuelleEbene >= 1 && schiffsebene.aktuelleEbene < schiffsebene.ebenenanzahl) {
                        schiffsebene.setAktuelleEbene(schiffsebene.aktuelleEbene +1);

                        aktuelleEbeneAnzeige.setText(Integer.toString(schiffsebene.getAktuelleEbene()));


                        // Buttondarstellung von weiss nach ornage ändern, je nach schiffsebene

                    }
                    if (schiffsebene.aktuelleEbene == schiffsebene.ebenenanzahl) {
                        ebene_oben.setImageResource(R.drawable.button_ebene_nach_oben_orange);
                        ebene_unten.setImageResource(R.drawable.button_ebene_nach_unten_weiss);
                    }
                    else {
                        ebene_oben.setImageResource(R.drawable.button_ebene_nach_oben_weiss);
                        ebene_unten.setImageResource(R.drawable.button_ebene_nach_unten_weiss);

                    }
                }


            }


        });


        // schiffsebene nach unten wechseln

        ebene_unten.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                schiffsebene.getAktuelleEbene();

                    // Neue Ebenenennummer berechnen, schiffsebene nach oben wandern

                {
                    if (schiffsebene.aktuelleEbene > 1 && schiffsebene.aktuelleEbene <= schiffsebene.ebenenanzahl) {
                        schiffsebene.setAktuelleEbene(schiffsebene.aktuelleEbene -1);

                        aktuelleEbeneAnzeige.setText(Integer.toString(schiffsebene.getAktuelleEbene()));

                    // Buttondarstellung von weiss nach ornage ändern, je nach schiffsebene

                    }
                    if (schiffsebene.aktuelleEbene == 1) {
                        ebene_unten.setImageResource(R.drawable.button_ebene_nach_unten_orange);
                        ebene_oben.setImageResource(R.drawable.button_ebene_nach_oben_weiss);
                    }
                    else {
                        ebene_unten.setImageResource(R.drawable.button_ebene_nach_unten_weiss);
                        ebene_oben.setImageResource(R.drawable.button_ebene_nach_oben_weiss);
                    }

                }


            }


        });
    }
}
