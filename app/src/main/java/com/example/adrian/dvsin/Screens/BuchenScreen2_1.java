package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    // -- INSTANZVARIABLEN festlegen -- //

    // INT

		int containerart_zahl;
		int kapazitaetMAX;
		int aktuelle_eingabe_speicher;
        int aktuelle_kapazitaet;
        int zwischenwert;
        int returnuebergabe;


    // STRING

		String text_kleines_schiff;
		String text_grosses_schiff;
		String uebergebener_schiffstyps;

		
    // -- Others -- //
	
	// TEXTVIEW

    // TEXTVIEW Menüstruktur
	
	TextView eingabeaufforderung_1, eingabeaufforderung_2, eingabeaufforderung_3, eingabeaufforderung_4;
	
	// TEXTVIEW Containereingabe

	TextView container_nummer, container_container, eingabefeld_container;
	
	// TEXTVIEW Containereingabe
	
	TextView fehlertext_ueberschift, fehlertext_erklaerung_v1_teil_1, fehlertext_erklaerung_v1_teil_2;


    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;
	
	
	// EDITTEXT
	
	EditText aktuelle_eingabe;


    // BUTTONS

    Button zurueck;

	// POPUPVIEW

	View popupView;

	// ImageView
	
	ImageView aktueller_container;
	

    // ImageBUTTONS

	ImageButton vorwaerts;


    // BUCHUNG

	Buchung aktuelleBuchung;
	
	
	// BUNDLES

	Bundle wertesammlung;
	
	
	// POPUPWINDOW
	
	PopupWindow popupWindow;


    // ########## //


    // *** HAUPTMETHODE *** //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		
		// -- ACTIVITY starten -- //
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buchen_screen_2_1);

        // IDs zuordnen

            setIDs();


        // FONTS einbeziehen

			setFonts();

		// INTENTINHALT holen

		getIntentInhalt();


		// STARTINITIALISIERUNG

		startInitialization();


        // FONTS anwenden

            setFontsToIDs();


		// SCREENINHALT festlegen

			showOnScreen();


        // -- BUTTONS  -- //

        // BUTTON "zurueck" drücken

            buttonGetBack();

			
		// -- BUTTON "vorwaerts" drücken
		
			buttonNextActivity();
       
	   // -- ACTIVITY Ende -- //

    // *** ENDE *** //
	   
    }

    // --- WEITERE Methoden --- //
	
	private void buttonNextActivity() {
		
		
        // BUTTON "vorwaerts" drücken

        vorwaerts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuchenScreen2_1.this, BuchenScreen2.class);
				
				
				// prüfen ob EDITTEXT "aktuelle_eingabe" leer ist
				
				// Wenn JA, dann zeig Dialog "DialogEmptyV1"
				
                if (aktuelle_eingabe.getText().toString().isEmpty()) {

					showDialogEmptyEditText();

					return;

                }

                else {
					
				// Wenn NEIN, dann wird neue Containeranzahl an KLASSE "BuchenScreen2" übergeben

					setNewContainerAnzahl();

				}

				// Wenn keine Änderungen vorgenommen wurden, verlasse die Methode und zeige das PopUpWindow "Leider zu SCHWER"

				if (returnuebergabe ==  1) {

					showDialogErrorToMuch();

					return;

				}

				
                // Neue bzw. alte Werte für Atrribute von BUCHUNG "aktuelleBuchung" zurück übergeben
				
				intent.putExtra("aktuelleBuchungKEY", aktuelleBuchung);
				
                // ACTIVITY BuchenScreen2 starten

                startActivity(intent);
            }
        });
		
	}
	
    private void buttonGetBack() {
		
        zurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BuchenScreen2_1.this, BuchenScreen2.class);

				// Alte Werte für Atrribute von BUCHUNG "aktuelleBuchung" zurück übergeben

				intent.putExtra("aktuelleBuchungKEY", aktuelleBuchung);

				// ACTIVITY BuchenScreen2 starten

                startActivity(intent);

            }
        });

    }

    private void setFontsToIDs() {

        // FONTS SETZEN
		
		// FONTS Menüstruktur SETZEN 

		zurueck.setTypeface(font_roboto_thin);
		
        eingabeaufforderung_1.setTypeface(font_roboto_thin);
        eingabeaufforderung_2.setTypeface(font_roboto_medium);
        eingabeaufforderung_3.setTypeface(font_roboto_thin);
        eingabeaufforderung_4.setTypeface(font_roboto_thin);

        // FONTS Containereingabe SETZEN

		container_nummer.setTypeface(font_roboto_medium);
        container_container.setTypeface(font_roboto_thin);
        eingabefeld_container.setTypeface(font_roboto_thin);
		
		// FONTS PopUpWindow SETZEN
		
		fehlertext_ueberschift.setTypeface(font_roboto_medium);
        fehlertext_erklaerung_v1_teil_1.setTypeface(font_roboto_thin);
        fehlertext_erklaerung_v1_teil_2.setTypeface(font_roboto_thin);
	
    }

    private void setFonts() {

        // FONTS einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(), "fonts/roboto-medium.ttf");

    }

    private void setIDs() {

        // IDs ZUORDNEN

        // TEXTVIEW

        // TEXTVIEW Menüstruktur ZUORDNEN
		
        eingabeaufforderung_1 = (TextView) findViewById(R.id.eingabeaufforderung_1);
        eingabeaufforderung_2 = (TextView) findViewById(R.id.eingabeaufforderung_2);
        eingabeaufforderung_3 = (TextView) findViewById(R.id.eingabeaufforderung_3);
        eingabeaufforderung_4 = (TextView) findViewById(R.id.eingabeaufforderung_4);
		
        // TEXTVIEW Containereingabe ZUORDNEN
		
		container_nummer = (TextView) findViewById(R.id.container_nummer);
        container_container = (TextView) findViewById(R.id.container_container);
        eingabefeld_container = (TextView) findViewById(R.id.eingabefeld_container);

        // BUTTONS

		zurueck = (Button) findViewById(R.id.zurueck);


        // ImageBUTTONS
		
		vorwaerts = (ImageButton) findViewById(R.id.vorwaerts);
			
    }
	
	private void startInitialization() {
		
				
		// STRING initialisieren
		
			text_kleines_schiff = "Kleines Schiff";
            text_grosses_schiff = "Großes Schiff";
			uebergebener_schiffstyps = aktuelleBuchung.getSchifftyp();
		
		// IMAGEVIEW initialisieren
			
			aktueller_container = (ImageView) findViewById(R.id.aktueller_container);
			
		// INT kapazitaetMAX initalisieren
		
			getAktuelleMaxKapazitaet();

			
		// EDITTEXT initialisieren
			
			aktuelle_eingabe = (EditText) findViewById(R.id.eingabefeld_container);

		// LAYOUTINFLATER initialisieren

		LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);


		// VIEW initialisieren

		View popupView = layoutInflater.inflate(R.layout.activity_popup_error_01, null);


		// POPUPWINDOW initialisieren

		popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


		// TEXTVIEW PopUpWindow

		fehlertext_ueberschift = (TextView) popupView.findViewById(R.id.fehlertext_ueberschift_v1);
		fehlertext_erklaerung_v1_teil_1 = (TextView) popupView.findViewById(R.id.fehlertext_erklaerung_v1_teil_1);
		fehlertext_erklaerung_v1_teil_2 = (TextView) popupView.findViewById(R.id.fehlertext_erklaerung_v1_teil_2);

	}
			
	private void getIntentInhalt() {
			
		// BUCHUNG "aktulleBuchung" holen
			
			aktuelleBuchung = (Buchung) getIntent().getParcelableExtra("aktuelleBuchungKEY");

		// INT "aktuelle_kapazitaet" holen

		aktuelle_kapazitaet = getIntent().getExtras().getInt("aktuelle_kapazitaetKEY");
		
		}

	private void showOnScreen() {
			
			
			// --- SHOW entweder Inhalt in Bezug auf einen kleinen oder einen großen Container --- //
			
			// "Kleiner Container" wurde ausgewählt

			if (aktuelleBuchung.getContainerart() == 1) {
				
				// Containernummer und Containerimage setzen

				container_nummer.setText(R.string.container_nummer_20);

				aktueller_container.setImageResource(R.drawable.icon_container_20_weiss);
				
				
				// INT containerart_zahl initialisieren

				containerart_zahl = 1;
			
			}
			

			// "Großer Container" wurde ausgewählt
			
			if (aktuelleBuchung.getContainerart() == 2) {
				
				// Containernummer und Containerimage setzen

				container_nummer.setText(R.string.container_nummer_40);

				aktueller_container.setImageResource(R.drawable.icon_container_40_weiss);
				
				
				// INT containerart_zahl initialisieren				

				containerart_zahl = 2;
            
			}
					
		}
			
	private void getAktuelleMaxKapazitaet() {
			
			// Kleines Schiff gewählt, maximaler Platz = 8
			
			if (text_kleines_schiff.equals(uebergebener_schiffstyps)){

				kapazitaetMAX = 8;

			}
			
			
			// Großes Schiff gewählt, maximaler Platz = 20

			if (text_grosses_schiff.equals(uebergebener_schiffstyps)){

				kapazitaetMAX = 20;

			}
			
		}
		
		private void setNewContainerAnzahl() {
		
		// Eingabe aus EDITTEXT "aktuelle_eingabe" in INT umwandeln
		
		aktuelle_eingabe_speicher = Integer.parseInt(aktuelle_eingabe.getText().toString());
		
		// PRÜFEN ob kleiner oder großer Container ausgeählt wurde
		
		// Kleiner Container gewählt 

			if (containerart_zahl == 1) {
				
						// Prüfen ob Kapazität des gewählten Schiffs noch ausreichend ist

                        zwischenwert = aktuelle_kapazitaet - aktuelle_eingabe_speicher + aktuelleBuchung.getContainerZahlKlein();

				Log.d("BLABLABLA", Integer.toString(aktuelle_kapazitaet));
				Log.d("TUTUTUTUT", Integer.toString(aktuelle_eingabe_speicher));
				Log.d("ASASASASASA", Integer.toString(aktuelleBuchung.getContainerZahlKlein()));
				Log.d("OFSPOASAP", Integer.toString(zwischenwert));

				if (zwischenwert < 0) {
					returnuebergabe = 1;

					return;
				}
						
						// Wenn ausreichend JA, dann Anzahl kleiner Container in Buchung erhöhen

                        if(zwischenwert >= 0 || aktuelle_eingabe_speicher <= aktuelleBuchung.getContainerZahlKlein() || ((kapazitaetMAX - aktuelle_eingabe_speicher <= aktuelle_kapazitaet) && (aktuelle_eingabe_speicher <= kapazitaetMAX))){

							aktuelleBuchung.setContainerZahlKlein(aktuelle_eingabe_speicher);


							// Damit POPUPWINDOW "DialogErrorV1" nicht mehr angezeigt wird!

							returnuebergabe = 0;

                        }
						
						// Wenn ausreichend NEIN, dann zeig POPUPWINDOW "DialogErrorV1"

                        else {

							returnuebergabe = 1;

							return;
							
						}

                         
                     }
            
		// Großer Container gewählt 

			if (containerart_zahl == 2) {
				
						// Prüfen ob Kapazität des gewählten Schiffs noch ausreichend ist



				zwischenwert = aktuelle_kapazitaet - 2*aktuelle_eingabe_speicher + 2*aktuelleBuchung.getContainerZahlGross();

				Log.d("BLABLABLA", Integer.toString(aktuelle_kapazitaet));
				Log.d("TUTUTUTUT", Integer.toString(aktuelle_eingabe_speicher));
				Log.d("ASASASASASA", Integer.toString(aktuelleBuchung.getContainerZahlGross()));
				Log.d("OFSPOASAP", Integer.toString(zwischenwert));

				if (zwischenwert < 0) {
					returnuebergabe = 1;

					return;
				}
						
						// Wenn ausreichend JA, dann Anzahl kleiner Container in Buchung erhöhen 

                        if(zwischenwert >= 0 || aktuelle_eingabe_speicher <= aktuelleBuchung.getContainerZahlGross()|| ((kapazitaetMAX - 2*aktuelle_eingabe_speicher <= aktuelle_kapazitaet) && (2*aktuelle_eingabe_speicher <= kapazitaetMAX))){

							aktuelleBuchung.setContainerZahlGross(aktuelle_eingabe_speicher);

							// Damit POPUPWINDOW "DialogErrorV1" nicht mehr angezeigt wird!

							returnuebergabe = 0;

                        }
						
						// Wenn ausreichend NEIN, dann zeig Dialog "DialogErrorV1"

                        else {

							returnuebergabe = 1;

							return;
							
						}

                     }
	}

	private void showDialogErrorToMuch(){
		
		setPopUpDetailsForAll();
		
		// TEXTVIEW updaten
			
		setTextViewDialogErrorToMuch();
		
        return;
	}	
		
	private void showDialogEmptyEditText(){

		setPopUpDetailsForAll();
		
		// TEXTVIEW updaten

		setTextViewDialogEmptyEditText();
		
        return;
	}	
			
	private void setTextViewDialogErrorToMuch(){
		
			// TEXTVIEW PopUpWindow setzen

		    fehlertext_ueberschift.setText(R.string.fehlertext_ueberschift_v1);
            fehlertext_erklaerung_v1_teil_1.setText(R.string.fehlertext_erklaerung_v1_teil_1);
			fehlertext_erklaerung_v1_teil_2.setText(R.string.fehlertext_erklaerung_v1_teil_2);
	}	
	
	private void setTextViewDialogEmptyEditText(){
		
			// TEXTVIEW PopUpWindow setzen

		fehlertext_ueberschift.setText(R.string.fehlertext_ueberschift_v2);
		fehlertext_erklaerung_v1_teil_1.setText(R.string.fehlertext_erklaerung_v2_teil_1);
		fehlertext_erklaerung_v1_teil_2.setText(R.string.fehlertext_erklaerung_v2_teil_2);
		
		
	}
	
	private void setPopUpDetailsForAll(){
		
		// KOMMENTIEREN, wenn verstanden ;)

			// Eigenschaften POPUPWINDOW "popupWindow" festlegen				

            popupWindow.setTouchable(true);
            popupWindow.setFocusable(false);
            popupWindow.setOutsideTouchable(true);
			
			// Hintergrund festlegen

			popupWindow.setBackgroundDrawable(new BitmapDrawable());
			
			// Position ausgehend con BUTTON "vorwaerts"

            popupWindow.showAtLocation(zurueck,0,0,50);
		
	}
	
}



