package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.adrian.dvsin.Buchungsklasse.Buchung;
import com.example.adrian.dvsin.MainActivity;
import com.example.adrian.dvsin.R;

public class BuchenScreen4 extends AppCompatActivity {

    // -- INSTANZVARIABLEN festlegen -- //

    // int


    // String


    // -- Others -- //

    // TEXTVIEW Buchungsbestätigungsbereich

    TextView text_buchungsbestaetigung_teil_1, text_buchungsbestaetigung_teil_2, text_buchungsbestaetigung_teil_3, text_buchungsbestaetigung_teil_4, buchungsnummer_abkuerzung;
	
	// TEXTVIEW Buchungsnummer (dynamisch)
	
	TextView buchungsnummer_aktuell;

	
    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;


    // BUTTONS

    Button zurueck;
	
	
	// IMAGEBUTTON 
	
	ImageButton containern_gleich_starten;	
	
	
    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buchen_screen_4);


        // IDs zuordnen

            setIDs();

			
        // FONTS einbeziehen

			setFonts();


        // -- FONTS ANWENDEN

			setFontsToIDs();


        // -- BUTTONS  -- //

        // BUTTON "zurueck" drücken

            buttonGetBack();


        // BUTTON "containern_gleich_starten" drücken
		
			getToMethodeContainern();


        // ########## //

    // -- ACTIVITY Ende -- //

    // *** ENDE *** //

    }

    // --- WEITERE Methoden --- //
	
	private void getToMethodeContainern(){
	
		// Ermöglicht den sofortigen Übergang von der Funktion "Buchen" zur Funktion "Containern"

        containern_gleich_starten.setOnClickListener( new View.OnClickListener() {

            @Override
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



