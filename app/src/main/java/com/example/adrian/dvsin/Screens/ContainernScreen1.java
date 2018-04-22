package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adrian.dvsin.MainActivity;
import com.example.adrian.dvsin.R;

public class ContainernScreen1 extends AppCompatActivity {

    // -- INSTANZVARIABLEN festlegen -- //

    // int


    // String


    // -- Others -- //

    // TEXTVIEW Eingabeaufforderung und Menüstruktur

    TextView eingabeaufforderung_1, eingabeaufforderung_2, eingabeaufforderung_3, eingabeaufforderung_4, eingabeaufforderung_5;
		
	// TEXTVIEW Buchungsbereich
	
    TextView buchungsnummer_abkuerzung_01, buchungsnummer_abkuerzung_02, buchungsnummer_aktuell_01, buchungsnummer_aktuell_02;

    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;


    // BUTTONS

    Button zurueck;
	Button buchung_auswaehlen_01_01;
	Button buchung_auswaehlen_01_02;
	

    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_containern_screen_1);

        // IDs zuordnen

            setIDs();


        // FONTS einbeziehen

			setFonts();


        // -- FONTS ANWENDEN

			setFontsToIDs();


        // -- BUTTONS  -- //

        // BUTTON "zurueck" drücken

            buttonGetBack();


        // BUTTON "buchung_auswaehlen_01_01" drücken

            buttonBuchungVerladenStarten();


        // BUTTON "buchung_auswaehlen_01_02" drücken 

            buttonBuchungVerladenStarten();


        // ########## //
    }

    // -- ACTIVITY Ende -- //

    // *** ENDE *** //




    // --- WEITERE Methoden --- //
	
	private void buttonBuchungVerladenStarten(){
		
		// BUTTON "buchung_containern_01" wird gedrückt

        buchung_auswaehlen_01_01.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContainernScreen1.this, ContainernScreen2.class);
				
				// ACTIVITY ContainernScreen2 starten
				
                startActivity(intent);
            }
        });
		
		
		// BUTTON "buchung_containern_02" wird gedrückt

        buchung_auswaehlen_01_02.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContainernScreen1.this, ContainernScreen2.class);
				
				// ACTIVITY ContainernScreen2 starten
				
                startActivity(intent);
            }
        });
				
	}

    private void buttonGetBack() {

        zurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContainernScreen1.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void setFontsToIDs() {

        // FONTS für Eingabeaufforderung und Menüstruktur SETZEN

        eingabeaufforderung_1.setTypeface(font_roboto_thin);
        eingabeaufforderung_2.setTypeface(font_roboto_medium);
        eingabeaufforderung_3.setTypeface(font_roboto_thin);
        eingabeaufforderung_4.setTypeface(font_roboto_thin);
        eingabeaufforderung_5.setTypeface(font_roboto_thin);
		
		// FONTS für Buchungsbereich SETZEN
		
		buchungsnummer_abkuerzung_01.setTypeface(font_roboto_thin);
        buchungsnummer_abkuerzung_02.setTypeface(font_roboto_thin);
        buchungsnummer_aktuell_01.setTypeface(font_roboto_medium);
        buchungsnummer_aktuell_02.setTypeface(font_roboto_medium);
		
		// FONTS Buttons setzen
		
		buchung_auswaehlen_01_01.setTypeface(font_roboto_thin);
        buchung_auswaehlen_01_02.setTypeface(font_roboto_thin);

    }

    private void setFonts() {

        // Fonts einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(), "fonts/roboto-medium.ttf");

    }

    private void setIDs() {

        // IDs Menüstruktur und Schiffe ZUORDNEN

        // TEXTVIEW Eingabeaufforderung und Menüstruktur

		eingabeaufforderung_1 = (TextView) findViewById(R.id.eingabeaufforderung_1);
        eingabeaufforderung_2 = (TextView) findViewById(R.id.eingabeaufforderung_2);
        eingabeaufforderung_3 = (TextView) findViewById(R.id.eingabeaufforderung_3);
        eingabeaufforderung_4 = (TextView) findViewById(R.id.eingabeaufforderung_4);
        eingabeaufforderung_5 = (TextView) findViewById(R.id.eingabeaufforderung_5);
		
		// TEXTVIEW Buchungsbereich
		
		buchungsnummer_abkuerzung_01 =(TextView) findViewById(R.id.buchungsnummer_abkuerzung_01);
        buchungsnummer_abkuerzung_02 = (TextView) findViewById(R.id.buchungsnummer_abkuerzung_02);
        buchungsnummer_aktuell_01 = (TextView) findViewById(R.id.buchungsnummer_aktuell_01);
        buchungsnummer_aktuell_02 = (TextView) findViewById(R.id.buchungsnummer_aktuell_02);
        
        // BUTTONS

        zurueck = (Button) findViewById(R.id.zurueck);
		buchung_auswaehlen_01_01 = (Button) findViewById(R.id.buchung_auswaehlen_01_01);
        buchung_auswaehlen_01_02 = (Button) findViewById(R.id.buchung_auswaehlen_01_02);

    }
}



