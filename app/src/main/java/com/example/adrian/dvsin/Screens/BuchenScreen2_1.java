package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.adrian.dvsin.Buchungsklasse.Buchung;
import com.example.adrian.dvsin.R;

public class BuchenScreen2_1 extends AppCompatActivity {


    // Test Buchen

    int containerart_zahl;
	

    // TextView Elemente

    TextView zurueck, eingabeaufforderung_1, eingabeaufforderung_2, eingabeaufforderung_3, eingabeaufforderung_4, container_nummer, container_container, eingabefeld_container;
    Typeface font_roboto_thin, font_roboto_medium;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buchen_screen_2_1);


        zurueck = (TextView) findViewById(R.id.zurueck);
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

        final Buchung aktuelleBuchung = (Buchung) getIntent().getExtras().getParcelable("aktuelleBuchungKEY");

        ImageView aktueller_container = (ImageView) findViewById(R.id.aktueller_container);

        final EditText aktuelle_eingabe = (EditText) findViewById(R.id.eingabefeld_container);



        //--- TEST Kapazität Schiff festlegen ---//

        String text_kleines_schiff = new String("Kleines Schiff");
        String text_grosses_schiff = new String("Großes Schiff");

        String uebergebener_schiffstyps = new String(aktuelleBuchung.getSchifftyp());

        int kapazitaetMAX = 0;

        if (text_kleines_schiff.equals(uebergebener_schiffstyps)){

            kapazitaetMAX = 8;

        }

        if (text_grosses_schiff.equals(uebergebener_schiffstyps)){

            kapazitaetMAX = 20;

        }

        // ### //



        // Großer oder kleiner Container soll gesetzt werden

        // Kleiner Container


        if (aktuelleBuchung.getContainerart() == 1) {

            container_nummer.setText(R.string.container_nummer_20);

            aktueller_container.setImageResource(R.drawable.icon_container_20_weiss);

            containerart_zahl = 1;
        }

        // Großer Container

        if (aktuelleBuchung.getContainerart() == 2) {

            container_nummer.setText(R.string.container_nummer_40);

            aktueller_container.setImageResource(R.drawable.icon_container_40_weiss);

            containerart_zahl = 2;
        }


        //BUTTONS

        // BUTTON zurück (links oben) aktivieren

        final Button zurueck = (Button) findViewById(R.id.zurueck);
        final int finalKapazitaetMAX = kapazitaetMAX;
        zurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuchenScreen2_1.this, BuchenScreen2.class);

                int aktuelle_eingabe_speicher;
                int aktuelle_kapazitaet = getIntent().getExtras().getInt("aktuelle_kapazitaetKEY");
                int zwischenwert;

                eingabeaufforderung_1.setText(Integer.toString(aktuelle_kapazitaet));
                eingabeaufforderung_2.setText(Integer.toString(finalKapazitaetMAX));


                if (aktuelle_eingabe.getText().toString().isEmpty()) {

                }

                else {


                    aktuelle_eingabe_speicher = Integer.parseInt(aktuelle_eingabe.getText().toString());

                    if (containerart_zahl == 1) {

                        zwischenwert = aktuelle_kapazitaet - aktuelle_eingabe_speicher;

                        if(zwischenwert >= 0 || aktuelle_eingabe_speicher <= finalKapazitaetMAX && aktuelle_kapazitaet >= aktuelle_eingabe_speicher){

                        aktuelleBuchung.setContainerZahlKlein(aktuelle_eingabe_speicher);

                        }

                        else {

                            LayoutInflater layoutInflater
                                    = (LayoutInflater) getBaseContext()
                                    .getSystemService(LAYOUT_INFLATER_SERVICE);
                            View popupView = layoutInflater.inflate(R.layout.activity_popup_error_01, null);
                            final PopupWindow popupWindow = new PopupWindow(
                                    popupView,
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);

                            // TEST

                            popupWindow.setTouchable(true);
                            popupWindow.setFocusable(false);
                            popupWindow.setOutsideTouchable(true);

                            popupWindow.setBackgroundDrawable(new BitmapDrawable());

                            popupWindow.showAtLocation(zurueck,0,0, 50);

                            TextView fehlertext_ueberschift, fehlertext_erklaerung_v1_teil_1, fehlertext_erklaerung_v1_teil_2;

                            fehlertext_ueberschift = (TextView) popupView.findViewById(R.id.fehlertext_ueberschift);
                            fehlertext_erklaerung_v1_teil_1 = (TextView) popupView.findViewById(R.id.fehlertext_erklaerung_v1_teil_1);
                            fehlertext_erklaerung_v1_teil_2 = (TextView) popupView.findViewById(R.id.fehlertext_erklaerung_v1_teil_2);

                            fehlertext_ueberschift.setTypeface(font_roboto_medium);
                            fehlertext_erklaerung_v1_teil_1.setTypeface(font_roboto_thin);
                            fehlertext_erklaerung_v1_teil_2.setTypeface(font_roboto_thin);

                            return;

                        }
                    }

                    if (containerart_zahl == 2) {

                        zwischenwert = aktuelle_kapazitaet - 2*aktuelle_eingabe_speicher;

                        if(zwischenwert >= 0 || 2*aktuelle_eingabe_speicher <= finalKapazitaetMAX && aktuelle_kapazitaet >= 2*aktuelle_eingabe_speicher ){

                            aktuelleBuchung.setContainerZahlGross(aktuelle_eingabe_speicher);

                        }

                        else {

                            LayoutInflater layoutInflater
                                    = (LayoutInflater) getBaseContext()
                                    .getSystemService(LAYOUT_INFLATER_SERVICE);
                            View popupView = layoutInflater.inflate(R.layout.activity_popup_error_01, null);
                            final PopupWindow popupWindow = new PopupWindow(
                                    popupView,
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);

                            // TEST

                            popupWindow.setTouchable(true);
                            popupWindow.setFocusable(false);
                            popupWindow.setOutsideTouchable(true);

                            popupWindow.setBackgroundDrawable(new BitmapDrawable());

                            popupWindow.showAtLocation(zurueck,0,0, 50);

                            TextView fehlertext_ueberschift, fehlertext_erklaerung_v1_teil_1, fehlertext_erklaerung_v1_teil_2;

                            fehlertext_ueberschift = (TextView) popupView.findViewById(R.id.fehlertext_ueberschift);
                            fehlertext_erklaerung_v1_teil_1 = (TextView) popupView.findViewById(R.id.fehlertext_erklaerung_v1_teil_1);
                            fehlertext_erklaerung_v1_teil_2 = (TextView) popupView.findViewById(R.id.fehlertext_erklaerung_v1_teil_2);

                            fehlertext_ueberschift.setTypeface(font_roboto_medium);
                            fehlertext_erklaerung_v1_teil_1.setTypeface(font_roboto_thin);
                            fehlertext_erklaerung_v1_teil_2.setTypeface(font_roboto_thin);

                            return;

                        }
                    }
                }


                intent.putExtra("aktuelleBuchungKEY", aktuelleBuchung);

                startActivity(intent);

            }
        });

        final ImageButton vorwaerts = (ImageButton) findViewById(R.id.vorwaerts);
        vorwaerts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater
                        = (LayoutInflater) getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.activity_popup_error_01, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                // TEST

                popupWindow.setTouchable(true);
                popupWindow.setFocusable(false);
                popupWindow.setOutsideTouchable(true);

                popupWindow.setBackgroundDrawable(new BitmapDrawable());

                popupWindow.showAtLocation(vorwaerts,0,0, 50);

                TextView fehlertext_ueberschift, fehlertext_erklaerung_v1_teil_1, fehlertext_erklaerung_v1_teil_2;

                fehlertext_ueberschift = (TextView) popupView.findViewById(R.id.fehlertext_ueberschift);
                fehlertext_erklaerung_v1_teil_1 = (TextView) popupView.findViewById(R.id.fehlertext_erklaerung_v1_teil_1);
                fehlertext_erklaerung_v1_teil_2 = (TextView) popupView.findViewById(R.id.fehlertext_erklaerung_v1_teil_2);

                fehlertext_ueberschift.setTypeface(font_roboto_medium);
                fehlertext_erklaerung_v1_teil_1.setTypeface(font_roboto_thin);
                fehlertext_erklaerung_v1_teil_2.setTypeface(font_roboto_thin);



            }
        });


    }

}