package com.example.adrian.dvsin;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class BuchenScreen2_1 extends AppCompatActivity {


    // Test Buchen

    int containerart_zahl;

    // TextView Elemente

    TextView zurueck, eingabeaufforderung_1, eingabeaufforderung_2, eingabeaufforderung_3, eingabeaufforderung_4, container_nummer, container_container, eingabefeld_container;
    Typeface font_roboto_thin, font_roboto_medium;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buchen_screen_2_1_containeranzahl);


        zurueck =(TextView) findViewById(R.id.zurueck);
        eingabeaufforderung_1 = (TextView) findViewById(R.id.eingabeaufforderung_1);
        eingabeaufforderung_2 = (TextView) findViewById(R.id.eingabeaufforderung_2);
        eingabeaufforderung_3 = (TextView) findViewById(R.id.eingabeaufforderung_3);
        eingabeaufforderung_4 = (TextView) findViewById(R.id.eingabeaufforderung_4);
        container_nummer = (TextView) findViewById(R.id.container_nummer);
        container_container = (TextView) findViewById(R.id.container_container);
        eingabefeld_container = (TextView) findViewById(R.id.eingabefeld_container);

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");

        zurueck.setTypeface(font_roboto_thin);
        eingabeaufforderung_1.setTypeface(font_roboto_thin);
        eingabeaufforderung_2.setTypeface(font_roboto_medium);
        eingabeaufforderung_3.setTypeface(font_roboto_thin);
        eingabeaufforderung_4.setTypeface(font_roboto_thin);
        container_nummer.setTypeface(font_roboto_medium);
        container_container.setTypeface(font_roboto_thin);
        eingabefeld_container.setTypeface(font_roboto_thin);



        // INHALT Activity wählen --- //

        // Inhalt: Aktuelle Buchungseigenschaften von letzter Aktivity holen

        final Buchung aktuelleBuchung = (Buchung) getIntent().getParcelableExtra("aktuelleBuchungKEY");

        ImageView aktueller_container = (ImageView) findViewById(R.id.aktueller_container);

        final EditText aktuelle_eingabe = (EditText) findViewById(R.id.eingabefeld_container);


        // Großer oder Kleiner Container soll gestzt werden

        // Kleiner Container


        if(aktuelleBuchung.getContainerart()==1){

            container_nummer.setText(R.string.container_nummer_20);

            aktueller_container.setImageResource(R.drawable.icon_schiffscontainer20_gross_v1_weiss);

            containerart_zahl = 1;
        }

        // Großer Container

        if(aktuelleBuchung.getContainerart()==2){

            container_nummer.setText(R.string.container_nummer_40);
            aktueller_container.setImageResource(R.drawable.icon_schiffscontainer40_gross_v1_weiss);

            containerart_zahl = 2;
        }


    //BUTTONS

        // BUTTON zurück (links oben) aktivieren

        Button zurueck = (Button) findViewById(R.id.zurueck);
        zurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuchenScreen2_1.this, BuchenScreen2.class);

                int aktuelle_eingabe_speicher;

                if(containerart_zahl == 1){

                    aktuelle_eingabe_speicher = Integer.parseInt(aktuelle_eingabe.getText().toString());
                    aktuelleBuchung.setContainerZahlKlein(aktuelle_eingabe_speicher);

                }

                if(containerart_zahl == 2){

                        aktuelle_eingabe_speicher = Integer.parseInt(aktuelle_eingabe.getText().toString());
                        aktuelleBuchung.setContainerZahlGross(aktuelle_eingabe_speicher);
                    }

                intent.putExtra("aktuelleBuchungKEY", aktuelleBuchung);

                startActivity(intent);
            }
        });

    }
}