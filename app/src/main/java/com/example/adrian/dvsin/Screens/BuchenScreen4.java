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

<<<<<<< HEAD
    // int
    int lastElement;
    int newOrderID;
    int randomNum;

    // TextView Menüstruktur
=======
    // -- INSTANZVARIABLEN festlegen -- //

    // int
>>>>>>> 2ee47f621af6e7fa63da5898dd1ea2f445f94aff


    // String


<<<<<<< HEAD
    TextView text_buchungsbestaetigung_teil_1, text_buchungsbestaetigung_teil_2, text_buchungsbestaetigung_teil_3, text_buchungsbestaetigung_teil_4, buchungsnummer_abkuerzung, buchungsnummer_abktuell, loadingBackground;
=======
    // -- Others -- //
>>>>>>> 2ee47f621af6e7fa63da5898dd1ea2f445f94aff

    // TEXTVIEW Buchungsbestätigungsbereich

    TextView text_buchungsbestaetigung_teil_1, text_buchungsbestaetigung_teil_2, text_buchungsbestaetigung_teil_3, text_buchungsbestaetigung_teil_4, buchungsnummer_abkuerzung;
	
	// TEXTVIEW Buchungsnummer (dynamisch)
	
	TextView buchungsnummer_aktuell;

	
    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;


<<<<<<< HEAD
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
=======
    // BUTTONS

    Button zurueck;
	
	
	// IMAGEBUTTON 
	
	ImageButton containern_gleich_starten;	
	
	
    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //
>>>>>>> 2ee47f621af6e7fa63da5898dd1ea2f445f94aff

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

<<<<<<< HEAD
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
=======
        // IDs zuordnen
>>>>>>> 2ee47f621af6e7fa63da5898dd1ea2f445f94aff

            setIDs();

			
        // FONTS einbeziehen

<<<<<<< HEAD
        buchungsnummer_abkuerzung = (TextView) findViewById(R.id.buchungsnummer_abkuerzung);
        buchungsnummer_abktuell = (TextView) findViewById(R.id.buchungsnummer_aktuell);

        loadingBackground = findViewById(R.id.loadingBackground);

        // Fonts einbeziehen
=======
			setFonts();
>>>>>>> 2ee47f621af6e7fa63da5898dd1ea2f445f94aff


        // -- FONTS ANWENDEN

			setFontsToIDs();


        // -- BUTTONS  -- //

        // BUTTON "zurueck" drücken

            buttonGetBack();

<<<<<<< HEAD
        buchungsnummer_abkuerzung.setTypeface(font_roboto_thin);
        buchungsnummer_abktuell.setTypeface(font_roboto_medium);
    }
=======
>>>>>>> 2ee47f621af6e7fa63da5898dd1ea2f445f94aff

        // BUTTON "containern_gleich_starten" drücken
		
			getToMethodeContainern();

<<<<<<< HEAD
    private void getLatestOrderID() {
        database2 = FirebaseDatabase.getInstance();
        ref2 = database2.getReference("orders");
=======

        // ########## //

    // -- ACTIVITY Ende -- //

    // *** ENDE *** //

    }

    // --- WEITERE Methoden --- //
	
	private void getToMethodeContainern(){
	
		// Ermöglicht den sofortigen Übergang von der Funktion "Buchen" zur Funktion "Containern"

        containern_gleich_starten.setOnClickListener( new View.OnClickListener() {
>>>>>>> 2ee47f621af6e7fa63da5898dd1ea2f445f94aff

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
<<<<<<< HEAD
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snp : dataSnapshot.getChildren()) {
                    orderList.add(Integer.valueOf(snp.getKey()));
                    Log.d("TAG", "Value is: " + snp);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
=======
            public void onClick(View view) {
                Intent intent = new Intent(BuchenScreen4.this, ContainernScreen1.class);
				
				// ACTIVITY ContainernScreen1 starten
				
                startActivity(intent);
            }
        });
		
	}

    private void buttonGetBack() {

        zurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuchenScreen4.this, MainActivity.class);

                // ACTIVITY MainActivity starten

                startActivity(intent);
>>>>>>> 2ee47f621af6e7fa63da5898dd1ea2f445f94aff

            }
        });

    }

    private void setFontsToIDs() {

        // FONTS Buchungsbestätigungsbereich SETZEN

        text_buchungsbestaetigung_teil_1.setTypeface(font_roboto_thin);
        text_buchungsbestaetigung_teil_2.setTypeface(font_roboto_thin);
        text_buchungsbestaetigung_teil_3.setTypeface(font_roboto_medium);
        text_buchungsbestaetigung_teil_4.setTypeface(font_roboto_thin);
		buchungsnummer_abkuerzung.setTypeface(font_roboto_thin);
		
		// FONTS Buchungsnummer (dynamisch) SETZEN

        buchungsnummer_aktuell.setTypeface(font_roboto_medium);

        // FONTS BUTTONS

        zurueck.setTypeface(font_roboto_thin);

    }

    private void setFonts() {

        // Fonts einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(), "fonts/roboto-medium.ttf");

    }

    private void setIDs() {

        // IDs Buchungsbestätigungsbereich ZUORDNEN
		
		// TEXTVIEW Buchungsbestätigungsbereich

        text_buchungsbestaetigung_teil_1 =(TextView) findViewById(R.id.text_buchungsbestaetigung_teil_1);
        text_buchungsbestaetigung_teil_2 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_2);
        text_buchungsbestaetigung_teil_3 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_3);
        text_buchungsbestaetigung_teil_4 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_4);
        buchungsnummer_abkuerzung = (TextView) findViewById(R.id.buchungsnummer_abkuerzung);
		
		// TEXTVIEW Buchungsnummer (dynamisch)

        buchungsnummer_aktuell = (TextView) findViewById(R.id.buchungsnummer_aktuell);
		
		// BUTTON
		
		zurueck = (Button) findViewById(R.id.zurueck);
		
		// IMAGEBUTTON 
		
        containern_gleich_starten = (ImageButton) findViewById(R.id.buchung_containern);

    }
}



