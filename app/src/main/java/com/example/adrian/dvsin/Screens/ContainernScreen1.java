package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.database.sqlite.SQLiteTableLockedException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
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


	// TABLE

    TableLayout buchngsDatentabelle;


    // TABLEROW

    TableRow buchungsDatenzeile;
	

    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_containern_screen_1);


        // IDs zuordnen

            setIDs();


        // BuchungsdatenTabelle erstellen

            setBuchungsDatentabelle();


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

    // -- ACTIVITY Ende -- //

    // *** ENDE *** //

    }

    // --- WEITERE Methoden --- //

    private void setBuchungsDatentabelle() {


        // Setzen der individuellen LayoutParts

        TableRow.LayoutParams buchungsDatenzeile_Layout = new TableRow.LayoutParams(1000,  170, 1f);

        LinearLayout.LayoutParams buchungsteil_Layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT, 4);

        ViewGroup.LayoutParams weiter_Layout = new ViewGroup.LayoutParams(70,70);

        ViewGroup.LayoutParams buchungsNrAbkürzung_Layout = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT);

        ViewGroup.LayoutParams buchungsNrAktuell_Layout = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams zwischenBereich1_Layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.MATCH_PARENT, 2);

        LinearLayout.LayoutParams zwischenBereich3_Layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.MATCH_PARENT, 1);

        // Eine zusätzliche Tabellenzeile anfügen zu TABLELAYOUT "buchungsDatentabelle"


        for (int i = 0; i <10; i++) {


            // --- Erstellen der neuen Tabellenzeile --- //


            TableRow buchungsDatenzeileVorgabe = new TableRow(this);

            // LAYOUT setzen

            buchungsDatenzeileVorgabe.setLayoutParams(buchungsDatenzeile_Layout);

            // INHALT und weitere Eigenschaften

            buchungsDatenzeileVorgabe.setPadding(0,0,0,8);

            // ------ //




            LinearLayout buchungsteil = new LinearLayout(new ContextThemeWrapper(this, R.style.buchungsteil), null, 0);

            // LAYOUT setzen

            // buchungsteil.setLayoutParams(buchungsteil_Layout);

            // INHALT und weitere Eigenschaften

            buchungsteil.setOrientation(LinearLayout.HORIZONTAL);

            buchungsteil.setPadding(20,0,0,0);

            // ------ //




            TextView buchungsNrAbkürzung = new TextView(new ContextThemeWrapper(this, R.style.buchungsNrAbkürzung), null, 0);

            // LAYOUT setzen

            // buchungsNrAbkürzung.setLayoutParams(weiter_Layout);

            // INHALT und weitere Eigenschaften

            buchungsNrAbkürzung.setTextAppearance(this, R.style.buchungsNrAbkürzung);

            buchungsNrAbkürzung.setText("BuNr.:");

            buchungsNrAbkürzung.setGravity(17);

            buchungsNrAbkürzung.setPadding(0,0,2,0);

            // ------ //




            TextView buchungsNrAktuell = new TextView(new ContextThemeWrapper(this, R.style.buchungsNrAktuell), null, 0);

            // LAYOUT setzen

            // buchungsNrAbkürzung.setLayoutParams(weiter_Layout);

            // INHALT und weitere Eigenschaften

            buchungsNrAktuell.setTextAppearance(this, R.style.buchungsNrAktuell);

            buchungsNrAktuell.setText("123456789");

            buchungsNrAktuell.setPadding(2,0,10,0);

            buchungsDatenzeile.setGravity(17);

            // ------ //




            TextView zwischenBereich1 = new TextView(new ContextThemeWrapper(this, R.style.zwischenBereich1), null, 0);

            // LAYOUT setzen

            // zwischenBereich1.setLayoutParams(weiter_Layout);

            // INHALT und weitere Eigenschaften

            zwischenBereich1.setGravity(17);

            // ------ //




            Button weiter = new Button(new ContextThemeWrapper(this, R.style.weiter), null, 0);

            // LAYOUT setzen

            // weiter.setLayoutParams(weiter_Layout);

            // INHALT und weitere Eigenschaften

            weiter.setTextAppearance(this, R.style.weiter);

            weiter.setText(">");

            // ------ //




            TextView zwischenBereich2 = new TextView(new ContextThemeWrapper(this, R.style.zwischenBereich1), null, 0);

            // LAYOUT setzen

            // zwischenBereich2.setLayoutParams(weiter_Layout);

            // INHALT und weitere Eigenschaften

            zwischenBereich2.setGravity(1);

            // ------ //




            TextView zwischenBereich3 = new TextView(new ContextThemeWrapper(this, R.style.zwischenBereich3), null, 0);

            // LAYOUT setzen

            // zwischenBereich3.setLayoutParams(weiter_Layout);

            // INHALT und weitere Eigenschaften

            zwischenBereich3.setGravity(17);

            // ------ //




            // LINEARLAYOUT "buchungsteil" füllen

            buchungsteil.addView(buchungsNrAbkürzung);
            buchungsteil.addView(buchungsNrAktuell);


            // TABLEROW "buchungsDatenzeile" füllen

            buchungsDatenzeileVorgabe.addView(buchungsteil);
            buchungsDatenzeileVorgabe.addView(zwischenBereich1);
            buchungsDatenzeileVorgabe.addView(weiter);
            buchungsDatenzeileVorgabe.addView(zwischenBereich2);
            buchungsDatenzeileVorgabe.addView(zwischenBereich3);

            // TABLELAYOUT "buchngsDatentabelle" füllen

            buchngsDatentabelle.addView(buchungsDatenzeileVorgabe, i);

        }
    }

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

        zurueck.setTypeface(font_roboto_thin);
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

        // TABLELAYOUT

        buchngsDatentabelle = (TableLayout) findViewById(R.id.buchungsDatentabelle);

        // TABELLENZEILE

        buchungsDatenzeile = findViewById(R.id.buchungsDatenzeile);


    }

}



