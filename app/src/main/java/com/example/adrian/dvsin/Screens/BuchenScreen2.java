package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.adrian.dvsin.Buchungsklasse.Buchung;
import com.example.adrian.dvsin.R;
import com.example.adrian.dvsin.Schiffsklassen.Schifftyp;

public class BuchenScreen2 extends AppCompatActivity {

    // -- INSTANZVARIABLEN festlegen -- //

    // INT

   		int button_vorwaerts_voll;
		int button_vorwaerts_transparent;
		
		int kapazitaet_uebergeben;
		int aktuelle_kapazitaet;

    // STRING

		String text_kleines_schiff;
		String text_grosses_schiff;
		String uebergebener_schiffstyps;
		String user;

		
    // -- Others -- //
	
	// INTEGER
	
		Integer uebergebene_zahl_container_klein;
		Integer uebergebene_zahl_container_gross;
	
	
	// TEXTVIEW

    // TEXTVIEW Menüstruktur und Schiffe
	
		TextView schrittanzeige, schifftyp_k_o_g, schiff_wort, schiff_beschreibung, max_wort, ladekapazitaet_zahl;
	
	// TEXTVIEW Untermenü

		TextView untermenue;

    // TEXTVIEW Containerbereich

		TextView container_nummer_20, container_name_klein, anzahl_feld_klein;
		TextView container_nummer_40, container_name_gross, anzahl_feld_gross;

    // TEXTVIEW Formelbereich

		TextView formel_text_1, formel_restwert, formel_text_2;
	

    // FONTS

   		 Typeface font_roboto_thin, font_roboto_medium;


    // BUTTONS

   		 Button zurueck;
   		 Button icon_eingabe_kleiner_container;
   		 Button icon_eingabe_grosser_container;
	

    // ImageBUTTONS

		ImageButton vorwaerts;


    // BUCHUNG

		Buchung aktuelleBuchung;
	
	
	// BUNDLES

		Bundle wertesammlungBS1_BS2_1;
		Bundle wertesammlungBS2;


	// ImageVIEW

		ImageView grosser_container_icon, kleiner_container_icon;


	// SCHIFFTYP

		Schifftyp aktuellesSchiff;

	
    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buchen_screen_2);

		Intent intent = getIntent();
		user = intent.getStringExtra("USER_NAME");

        // IDs zuordnen

            setIDs();


        // FONTS einbeziehen

			setFonts();


        // FONTS anwenden

            setFontsToIDs();
			
		
		// INTENTINHALT holen

			getIntentInhalt();
			
			
		// STARTINITIALISIERUNG 
			
			startInitialization();
			
			
		// CONTAINERFORMEL erstellen

			setContainerformel();
			
			
		// SCREENINHALT festlegen

			showOnScreen();
			
			
		// SCREENINHALT modifizieren (Changes "Color & Images", Darstellung der Zahl "0")

			showDifferentOnScreen();
			
			
        // -- BUTTONS  -- //

        // BUTTON "zurueck" drücken

            buttonGetBack();


        // -- BUTTON "icon_eingabe_kleiner_container" drücken

            buttonZuEingabe20Container();
			

		// -- BUTTON "icon_eingabe_grosser_container" drücken

            buttonZuEingabe40Container();
			
			
		// -- BUTTON "vorwaerts" drücken
		
			buttonNextActivity();


		// -- ACTIVITY Ende -- //

		// *** ENDE *** //
    }

    // --- WEITERE Methoden --- //

	private void buttonNextActivity() {
		
		
		// ZUSATZMETHODE 
		
		setVisability();
		
		// ############ //
		
		
		vorwaerts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
				
			// BUTTON "vorwaerts" prüft ob Container ausgewählt wurden
			
			// KEINE Container gewählt, return_Befehl
			
				if(button_vorwaerts_transparent == 1){
			
					return;
			
				}
			
			// mindestens ein Container gewählt, Buchung möglich
			
				if(button_vorwaerts_voll == 1){
		
					Intent intent = new Intent(BuchenScreen2.this, BuchenScreen4.class);
				
					// Übergabewerte an neue Activity bestimmen und übergeben	
	
					intent.putExtra("aktuelleBuchungKEY", aktuelleBuchung);
					intent.putExtra("USER_NAME", user);
				
					// ACTIVITY BuchenScreen4 starten

					startActivity(intent);
				}
			
			}

        });
	
	}

    private void buttonZuEingabe40Container() {

        // BUTTON "icon_eingabe_grosser_container" drücken

        icon_eingabe_grosser_container.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BuchenScreen2.this, BuchenScreen2_1.class);

                // Kleiner Container wird eingeplant

					aktuelleBuchung.setContainerart(2);
					
				// Übergabewerte an neue Activity bestimmen und übergeben

				// wertesammlungBS1_BS2_1.putParcelable("aktuellesSchiffKEY", aktuellesSchiff);
                wertesammlungBS2.putParcelable("aktuelleBuchungKEY", aktuelleBuchung);
                wertesammlungBS2.putInt("aktuelle_kapazitaetKEY", aktuelle_kapazitaet);
                wertesammlungBS2.putParcelable("aktuellesSchiffKEY", aktuellesSchiff);

                intent.putExtras(wertesammlungBS2);
				intent.putExtra("USER_NAME", user);
				
				// ACTIVITY BuchenScreen2_1 starten

                startActivity(intent);
            }
        });
		
	}

    private void buttonZuEingabe20Container() {

        // BUTTON "eingabeAnzahl20Container" drücken

        icon_eingabe_kleiner_container.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BuchenScreen2.this, BuchenScreen2_1.class);

                // Kleiner Container wird eingeplant

					aktuelleBuchung.setContainerart(1);
					
				// Übergabewerte an neue Activity bestimmen und übergeben

                wertesammlungBS2.putParcelable("aktuelleBuchungKEY", aktuelleBuchung);
                wertesammlungBS2.putInt("aktuelle_kapazitaetKEY",aktuelle_kapazitaet);
				wertesammlungBS2.putParcelable("aktuellesSchiffKEY", aktuellesSchiff);
				intent.putExtra("USER_NAME", user);

                intent.putExtras(wertesammlungBS2);
				
				// ACTIVITY BuchenScreen2_1 starten

                startActivity(intent);
            }

        });
		
	}

    private void buttonGetBack() {

        zurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BuchenScreen2.this, BuchenScreen1.class);
				intent.putExtra("USER_NAME", user);

				// ACTIVITY BuchenScreen1 starten

                startActivity(intent);

            }
        });

    }

    private void setFontsToIDs() {

        // FONTS SETZEN
		
		// FONTS Menüstruktur und Schiffangaben SETZEN 

		zurueck.setTypeface(font_roboto_thin);
        schrittanzeige.setTypeface(font_roboto_thin);

        schifftyp_k_o_g.setTypeface(font_roboto_medium);
        schiff_wort.setTypeface(font_roboto_thin);
        schiff_beschreibung.setTypeface(font_roboto_thin);
        max_wort.setTypeface(font_roboto_thin);
        ladekapazitaet_zahl.setTypeface(font_roboto_medium);


        // FONTS Untermenü SETZEN

        untermenue.setTypeface(font_roboto_thin);

        // FONTS Containerbereich SETZEN

        container_nummer_20.setTypeface(font_roboto_medium);
        container_name_klein.setTypeface(font_roboto_thin);
        anzahl_feld_klein.setTypeface(font_roboto_thin);

        container_nummer_40.setTypeface(font_roboto_medium);
        container_name_gross.setTypeface(font_roboto_thin);
        anzahl_feld_gross.setTypeface(font_roboto_thin);

        // FONTS Formelbereich SETZEN

        formel_text_1.setTypeface(font_roboto_thin);
        formel_restwert.setTypeface(font_roboto_medium);
        formel_text_2.setTypeface(font_roboto_thin);
		
		
		// FONTS Buttons zuordnen 
		
		icon_eingabe_grosser_container.setTypeface(font_roboto_thin);
		icon_eingabe_kleiner_container.setTypeface(font_roboto_thin);
		
    }

    private void setFonts() {

        // FONTS einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(), "fonts/roboto-medium.ttf");

    }

    private void setIDs() {

        // IDs ZUORDNEN

        // TEXTVIEW

        // TEXTVIEW Menüstruktur und Schiffangaben ZUORDNEN

			schrittanzeige = (TextView) findViewById(R.id.schrittanzeige);
	
			schifftyp_k_o_g = (TextView) findViewById(R.id.schifftyp_k_o_g);
			schiff_wort = (TextView) findViewById(R.id.schiff_wort);
			schiff_beschreibung =(TextView) findViewById(R.id.schiff_beschreibung);
			max_wort  =(TextView) findViewById(R.id.max_wort);
			ladekapazitaet_zahl  =(TextView) findViewById(R.id.ladekapazitaet_zahl);

        // TEXTVIEW Untermenü ZUORDNEN

			untermenue = (TextView) findViewById(R.id.untermenue);

        // TEXTVIEW Containerbereich ZUORDNEN

			container_nummer_20 = (TextView) findViewById(R.id.container_nummer_20);
			container_name_klein = (TextView) findViewById(R.id.container_name_klein);
			anzahl_feld_klein = (TextView) findViewById(R.id.anzahl_feld_klein);

			container_nummer_40 = (TextView) findViewById(R.id.container_nummer_40);
			container_name_gross = (TextView) findViewById(R.id.container_name_gross);
			anzahl_feld_gross = (TextView) findViewById(R.id.anzahl_feld_gross);

        // TEXTVIEW Formelbereich ZUORDNEN

			formel_text_1 = (TextView) findViewById(R.id.formel_text_1);
			formel_restwert = (TextView) findViewById(R.id.formel_restwert);
			formel_text_2 = (TextView) findViewById(R.id.formel_text_2);


        // BUTTONS

			zurueck = (Button) findViewById(R.id.zurueck);
			icon_eingabe_grosser_container = (Button) findViewById(R.id.icon_eingabe_grosser_container);
			icon_eingabe_kleiner_container = (Button) findViewById(R.id.icon_eingabe_kleiner_container);


        // ImageBUTTONS

			vorwaerts = (ImageButton) findViewById(R.id.vorwaerts);


		// ImageVIEW

			grosser_container_icon = (ImageView) findViewById(R.id.grosser_container_icon);
			kleiner_container_icon = (ImageView) findViewById(R.id.kleiner_container_icon);


    }

    private void startInitialization() {

		// INTEGER initialisieren
		
			uebergebene_zahl_container_klein = (Integer) aktuelleBuchung.getContainerZahlKlein();
			uebergebene_zahl_container_gross = (Integer) aktuelleBuchung.getContainerZahlGross();
			
		// STRING initialisieren
		
			text_kleines_schiff = "Kleines Schiff";
            text_grosses_schiff = "Großes Schiff";
			uebergebener_schiffstyps = aktuelleBuchung.getSchifftyp();

		// BUNDLE initalisieren

			wertesammlungBS2 = new Bundle();

		}

	private void getIntentInhalt() {

    	// ALLGEMEIN

			wertesammlungBS1_BS2_1 = getIntent().getExtras();

		// SCHIFFTYP "aktuellesSchiff" holen

			// aktuellesSchiff = new Schifftyp(12345,"Kleines Schiff", getString(R.string.text_schiff_klein), 2, 24);

			aktuellesSchiff = (Schifftyp) wertesammlungBS1_BS2_1.getParcelable("aktuellesSchiffKEY");

		// SCHIFFTYP "aktuelleBuchung" holen

	    	aktuelleBuchung = (Buchung) wertesammlungBS1_BS2_1.getParcelable("aktuelleBuchungKEY");

			// aktuelleBuchung = new Buchung(123456789, "Kleines Schiff", 0, 0 , 0);
		
		}

	private void showOnScreen() {

		// --- SHOW entweder Inhalt in Bezug auf ein kleines oder ein großes Schiff --- //

// +++++++++++++++++++++++++++++++++++++++++++++

		schifftyp_k_o_g.setText(aktuellesSchiff.getTypbezeichnung());
		schiff_beschreibung.setText(aktuellesSchiff.getTypbeschreibung());
		ladekapazitaet_zahl.setText(Integer.toString(aktuellesSchiff.getStellplaetze()));

			
// #############################################

		/*
			// "Kleines Schiff" wurde ausgewählt

			if (text_kleines_schiff.equals(uebergebener_schiffstyps)){
				
				// TEXTVIEW ändern/setzen

				schifftyp_k_o_g.setText(R.string.schiff_klein_1);
				schiff_beschreibung.setText(R.string.text_schiff_klein);
				
				ladekapazitaet_zahl.setText("8");

			}
			
			// "Großes Schiff" wurde ausgewählt

			else {
				
				// TEXTVIEW ändern/setzen

				schifftyp_k_o_g.setText(R.string.schiff_groß_1);
				schiff_beschreibung.setText(R.string.text_schiff_groß);
				
				ladekapazitaet_zahl.setText("20");
				            
			}

		*/
// ----------------------------------------------------------------

			// Für BUTTONS "icon_eingabe_grosser_container" & "icon_eingabe_kleiner_container" wird Text gesetzt.

			icon_eingabe_grosser_container.setText(Integer.toString(aktuelleBuchung.getContainerZahlGross()));
			icon_eingabe_kleiner_container.setText(Integer.toString(aktuelleBuchung.getContainerZahlKlein()));


			// TEXTVIEW "aktueller_kapazität" in Text umwandeln //

//********* FALLUNTERSCHEIDUNG HIER NOCH WEITER AUSBAUEN! !!

			if (aktuelle_kapazitaet == 1) {
				formel_restwert.setText(R.string.wenn_eins);
				formel_text_2.setText(R.string.freier_platz_einzahl);
			}
			else {
				formel_restwert.setText(Integer.toString(aktuelle_kapazitaet));
			}

		}
		
	private void setContainerformel() {

// #####################################

/*
			
			// --- CONTAINERFORMEL entweder Inhalt in Bezug auf ein kleines oder ein großes Schiff --- //
			
			// "Kleines Schiff" wurde ausgewählt

			if (text_kleines_schiff.equals(uebergebener_schiffstyps)) {

				// Kapazität festlegen

				kapazitaet_uebergeben = 8;

			}
			// "Großes Schiff" wurde ausgewählt
			
			else {
			
			// Kapazität festlegen
			
			kapazitaet_uebergeben = 20;
		
			}

*/
			// Aktuelle Kapazität berechnen

			kapazitaet_uebergeben = aktuellesSchiff.getStellplaetze();
			
			aktuelle_kapazitaet = kapazitaet_uebergeben - (aktuelleBuchung.getContainerZahlKlein() + 2*(aktuelleBuchung.getContainerZahlGross()));
			
		}
		
	private void showDifferentOnScreen(){
			
			// --- Wenn BUCHUNG "containerZahlKlein" oder "containerZahlGross" wird ORANGE als Hauptfarbe gewählt --- //
			
			// mindestens ein kleiner Container ist gesetzt
			
			if(aktuelleBuchung.containerZahlKlein != 0){
				
				// BUTTON "eingabeAnzahl20Container" oranger Rahmen & FONTcolor & ImageVIEW "orange"
				
				// Rahmen

				icon_eingabe_kleiner_container.setBackground(getResources().getDrawable(R.drawable.zellenrahmen_v3));
				
				// FONTcolor
				
				// icon_eingabe_kleiner_container.setTextColor(getResources().getColor(R.color.standard_orange));

				// ICON CONTAINER "orange"

				kleiner_container_icon.setImageResource(R.drawable.icon_container_20_orange);
				
			}

			else{

				// Rahmen

				icon_eingabe_kleiner_container.setBackground(getResources().getDrawable(R.drawable.zellenrahmen_v2));

				// FONTcolor

				// icon_eingabe_kleiner_container.setTextColor(getResources().getColor(R.color.standard_grau));

				// ICON CONTAINER "grau"

				kleiner_container_icon.setImageResource(R.drawable.icon_container_20);

			}
			
			// mindestens ein großer Container ist gesetzt
			
			if(aktuelleBuchung.containerZahlGross != 0){
				
				// BUTTON "eingabeAnzahl40Container" oranger Rahmen & FONTcolor
				
				// Rahmen
				
				icon_eingabe_grosser_container.setBackground(getResources().getDrawable(R.drawable.zellenrahmen_v3));
				
				// FONTcolor
				
				// icon_eingabe_grosser_container.getResources().getColor(R.color.standard_orange);

				// ICON CONTAINER "orange"

				grosser_container_icon.setImageResource(R.drawable.icon_container_40_orange);
				
			}

			else{

				// Rahmen

				icon_eingabe_grosser_container.setBackground(getResources().getDrawable(R.drawable.zellenrahmen_v2));

				// FONTcolor

				// icon_eingabe_grosser_container.setTextColor(getResources().getColor(R.color.standard_grau));

				// ICON CONTAINER "grau"

				grosser_container_icon.setImageResource(R.drawable.icon_container_40);

			}
			
			// ########## //
			
			
			// SHOW der Zahl "0" 
			
			// Die Zahl "0" wird in "icon_eingabe_kleiner_container" & "icon_eingabe_grosser_container" als "-" dargestellt
			
			if(aktuelleBuchung.containerZahlKlein == 0){
				
				icon_eingabe_kleiner_container.setText("-");
			}

			if(aktuelleBuchung.containerZahlGross == 0){
				
				icon_eingabe_grosser_container.setText("-");
			}
				
		}
		
	private void setVisability(){
			
			// ZUSATZMETHODE für "buttonNextActivity()"
			
			// Keine Container gewählt: BUTTON "vorwearts" transparent

			if(aktuelleBuchung.containerZahlKlein == 0 && aktuelleBuchung.containerZahlGross == 0){

				vorwaerts.setImageResource(R.drawable.button_vorwaerts_transparent);
		
				button_vorwaerts_transparent = 1;
				
				button_vorwaerts_voll = 0;
				
			}

			// Container gewählt: BUTTON "vorwearts" normal

		
			if(aktuelleBuchung.containerZahlKlein != 0 || aktuelleBuchung.containerZahlGross != 0){
					
				vorwaerts.setImageResource(R.drawable.button_vorwaerts);
		
				button_vorwaerts_transparent = 0;
				
				button_vorwaerts_voll = 1;
				
			}
			
		}

    public void onBackPressed() {

        // Übergang von einer zur nächsten, passenden Activity

        Intent intent = new Intent(BuchenScreen2.this, BuchenScreen1.class);
		intent.putExtra("USER_NAME", user);
        startActivity(intent);
    }

}



