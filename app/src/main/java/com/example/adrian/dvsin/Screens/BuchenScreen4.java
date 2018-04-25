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

import com.example.adrian.dvsin.Buchungsklasse.Buchung;
import com.example.adrian.dvsin.MainActivity;
import com.example.adrian.dvsin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class BuchenScreen4 extends AppCompatActivity {

    // int
    int lastElement;
    int newOrderID;
    int randomNum;

    // TextView Menüstruktur

    TextView zurueck;


    // TextView Buchungsbestätigungsbereich

    TextView text_buchungsbestaetigung_teil_1, text_buchungsbestaetigung_teil_2, text_buchungsbestaetigung_teil_3, text_buchungsbestaetigung_teil_4, buchungsnummer_abkuerzung, buchungsnummer_abktuell, loadingBackground;


    // verwendete Fonts

    Typeface font_roboto_thin, font_roboto_medium;


    //Database Resources

    FirebaseDatabase database2;


    //Reference to the database

    DatabaseReference ref2;


    //Array List to save the data from the database

    ArrayList<Integer> orderList = new ArrayList<>();
    ArrayList<Integer> containerGrossList = new ArrayList<>();
    ArrayList<Integer> containerKleinList = new ArrayList<>();


    //aktuelle Buchungsklasse aus vorheriger activity

    Buchung aktuelleBuchung;


    // ACTIVITY starten

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buchen_screen_4);

        setViews();

        getLatestOrderID();

        writeToDatabase();


        // INHALT Activity wählen --- //

        // Inhalt: Aktuelle Buchungseigenschaften von letzter Aktivity holen

        aktuelleBuchung = (Buchung) getIntent().getParcelableExtra("aktuelleBuchungKEY");

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

    private void writeToDatabase() {
        lastElement = orderList.size() - 1;
        newOrderID = orderList.get(lastElement) + 1;

        //set new orderID
        aktuelleBuchung.setBuchungsID(newOrderID);

        for (int i = 0; i < aktuelleBuchung.getContainerZahlGross(); i++) {
            randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
            containerGrossList.add(randomNum);
        }

        for (int i = 0; i < aktuelleBuchung.getContainerZahlKlein(); i++) {
            randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
            containerKleinList.add(randomNum);
        }

    }

    private void setViews() {
        // TextView Menüstruktur

        zurueck =(TextView) findViewById(R.id.zurueck);

        // TextView Buchungsbereich zuweisen

        text_buchungsbestaetigung_teil_1 =(TextView) findViewById(R.id.text_buchungsbestaetigung_teil_1);
        text_buchungsbestaetigung_teil_2 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_2);
        text_buchungsbestaetigung_teil_3 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_3);
        text_buchungsbestaetigung_teil_4 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_4);

        buchungsnummer_abkuerzung = (TextView) findViewById(R.id.buchungsnummer_abkuerzung);
        buchungsnummer_abktuell = (TextView) findViewById(R.id.buchungsnummer_aktuell);

        loadingBackground = findViewById(R.id.loadingBackground);

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
    }


    private void getLatestOrderID() {
        database2 = FirebaseDatabase.getInstance();
        ref2 = database2.getReference("orders");

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snp : dataSnapshot.getChildren()) {
                    orderList.add(Integer.valueOf(snp.getKey()));
                    Log.d("TAG", "Value is: " + snp);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());

            }
        });

    }
}