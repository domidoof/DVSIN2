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

public class ContainernScreen3 extends AppCompatActivity {

    // -- INSTANZVARIABLEN festlegen -- //

    // int


    // String

    String orderID, statusPath, user;


    // -- Others -- //

    // TEXTVIEW Eingabeauffroderung und Menüstruktur

     TextView text_containern_done_teil_1, text_containern_done_teil_2, text_containern_done_teil_3, text_containern_done_teil_4;
	
	// TEXTVIEW Buchungsnummer (dynamisch)
	
    TextView buchungsnummer_abkuerzung, buchungsnummer_aktuell;

	
    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;


    // BUTTONS

    Button zurueck;
	
	
	// IMAGEBUTTON 
	
	ImageButton buchung_containern_done;


	// Database initialisation

    DatabaseReference ref;
    FirebaseDatabase database;
	
	
    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_containern_screen_3);

        Intent intent = getIntent();
        orderID = intent.getStringExtra("ORDER_ID");


        // change the order status in the database to "closed"

        orderClosed();


        // IDs zuordnen

            setIDs();

			
        // FONTS einbeziehen

			setFonts();


        // -- FONTS ANWENDEN

			setFontsToIDs();


        // -- BUTTONS  -- //

        // BUTTON "zurueck" drücken

            buttonGetBack();


        // BUTTON "buchung_containern_done" drücken
		
			getBacktoMainActivity();

		//buchungsnummer auf textview setzen
        buchungsnummer_aktuell.setText(orderID);


        // ########## //

    // -- ACTIVITY Ende -- //

    // *** ENDE *** //

    }

    // --- WEITERE Methoden --- //

    private void orderClosed() {
        statusPath = "orders/" + orderID + "/status";
        database = FirebaseDatabase.getInstance();
        ref = database.getReference(statusPath);
        ref.setValue("closed");
    }
	
	private void getBacktoMainActivity(){
	
		// Führt zurück zur CLASS "MainActivity" (zurück ins Hauptmenü)

        buchung_containern_done.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContainernScreen3.this, MainActivity.class);
                intent.putExtra("USER_NAME", user);
				
				// ACTIVITY MainActivity starten
				
                startActivity(intent);
            }
        });
		
	}

    private void buttonGetBack() {
		
		// Führt zurück zur CLASS "MainActivity" (zurück ins Hauptmenü)

		zurueck.setOnClickListener(new View.OnClickListener() {
		
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContainernScreen3.this, MainActivity.class);
                intent.putExtra("USER_NAME", user);

                // ACTIVITY MainActivity starten

                startActivity(intent);

            }
        });

    }

    private void setFontsToIDs() {

        // FONTS Buchungsbestätigungsbereich SETZEN

        text_containern_done_teil_1.setTypeface(font_roboto_thin);
        text_containern_done_teil_2.setTypeface(font_roboto_thin);
        text_containern_done_teil_3.setTypeface(font_roboto_medium);
        text_containern_done_teil_4.setTypeface(font_roboto_thin);
		buchungsnummer_abkuerzung.setTypeface(font_roboto_thin);
		
		// FONTS Buchungsnummer (dynamisch) SETZEN

        buchungsnummer_aktuell.setTypeface(font_roboto_medium);

        // BUTTONS FONTS

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

        text_containern_done_teil_1 =(TextView) findViewById(R.id.text_containern_done_teil_1);
        text_containern_done_teil_2 = (TextView) findViewById(R.id.text_containern_done_teil_2);
        text_containern_done_teil_3 = (TextView) findViewById(R.id.text_containern_done_teil_3);
        text_containern_done_teil_4 = (TextView) findViewById(R.id.text_containern_done_teil_4);
        buchungsnummer_abkuerzung = (TextView) findViewById(R.id.buchungsnummer_abkuerzung_01);
		
		// TEXTVIEW Buchungsnummer (dynamisch)

        buchungsnummer_aktuell = (TextView) findViewById(R.id.buchungsnummer_aktuell_01);
		
		// BUTTON
		
		zurueck = (Button) findViewById(R.id.zurueck);
		
		// IMAGEBUTTON 
		
        buchung_containern_done = (ImageButton) findViewById(R.id.containern_fertig);

    }

    public void onBackPressed() {

        // Übergang von einer zur nächsten, passenden Activity

        Intent intent = new Intent(ContainernScreen3.this, MainActivity.class);
        intent.putExtra("USER_NAME", user);
        startActivity(intent);

    }
}



