package com.example.adrian.dvsin;

//domi

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class BuchenScreen2 extends AppCompatActivity {

	// TEST -- FUNKTION Buchen -- //

		int button_vorwaerts_voll;
		int button_vorwaerts_transparent;
		
	
		int kapazitaet_uebergeben;
		int aktuelle_kapazitaet;

	// ENDE TEST 	


    // TextView Menüstruktur und Schiffangaben

    TextView zurueck, schrittanzeige, schifftyp_k_o_g, schiff_wort, schiff_beschreibung, max_wort, ladekapazitaet_zahl;

    // TextView Untermenü

    TextView untermenue;

    // TextView Containerbereich

    TextView container_nummer_20, container_name_klein, anzahl_feld_klein, icon_eingabe_kleiner_container;
    TextView container_nummer_40, container_name_gross, anzahl_feld_gross, icon_eingabe_grosser_container;

    // TextView Formel

    TextView formel_text_1, formel_restwert, formel_text_2;


    // verwendete Fonts

    Typeface font_roboto_thin, font_roboto_medium;

    // verwendete BUTTONS

    // ACTIVITY starten

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buchen_screen_2);


        // Textfelder Menüstruktur und Schiffangaben ZUORDNEN

        zurueck =(TextView) findViewById(R.id.zurueck);
        schrittanzeige = (TextView) findViewById(R.id.schrittanzeige);

        schifftyp_k_o_g = (TextView) findViewById(R.id.schifftyp_k_o_g);
        schiff_wort = (TextView) findViewById(R.id.schiff_wort);
        schiff_beschreibung =(TextView) findViewById(R.id.schiff_beschreibung);
        max_wort  =(TextView) findViewById(R.id.max_wort);
        ladekapazitaet_zahl  =(TextView) findViewById(R.id.ladekapazitaet_zahl);

        // Textfelder Untermenü ZUORDNEN

        untermenue = (TextView) findViewById(R.id.untermenue);

        // Textfelder Containerbereich ZUORDNEN

        container_nummer_20 = (TextView) findViewById(R.id.container_nummer_20);
        container_name_klein = (TextView) findViewById(R.id.container_name_klein);
        anzahl_feld_klein = (TextView) findViewById(R.id.anzahl_feld_klein);
        icon_eingabe_kleiner_container = (TextView) findViewById(R.id.icon_eingabe_kleiner_container);

        container_nummer_40 = (TextView) findViewById(R.id.container_nummer_40);
        container_name_gross = (TextView) findViewById(R.id.container_name_gross);
        anzahl_feld_gross = (TextView) findViewById(R.id.anzahl_feld_gross);
        icon_eingabe_grosser_container = (TextView) findViewById(R.id.icon_eingabe_grosser_container);

        // Textfelder Formel ZUORDNEN

        formel_text_1 = (TextView) findViewById(R.id.formel_text_1);
        formel_restwert = (TextView) findViewById(R.id.formel_restwert);
        formel_text_2 = (TextView) findViewById(R.id.formel_text_2);


        // Fonts einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(), "fonts/roboto-medium.ttf");


        //-- Fonts ANWENDEN --

        // Fonts für Menüstruktur und Schiffangaben SETZEN

        zurueck.setTypeface(font_roboto_thin);
        schrittanzeige.setTypeface(font_roboto_thin);

        schifftyp_k_o_g.setTypeface(font_roboto_medium);
        schiff_wort.setTypeface(font_roboto_thin);
        schiff_beschreibung.setTypeface(font_roboto_thin);
        max_wort.setTypeface(font_roboto_thin);
        ladekapazitaet_zahl.setTypeface(font_roboto_medium);


        // Font für Untermenü SETZEN

        untermenue.setTypeface(font_roboto_thin);

        // Font für Containerbereich SETZEN

        container_nummer_20.setTypeface(font_roboto_medium);
        container_name_klein.setTypeface(font_roboto_thin);
        anzahl_feld_klein.setTypeface(font_roboto_thin);
        icon_eingabe_kleiner_container.setTypeface(font_roboto_thin);

        container_nummer_40.setTypeface(font_roboto_medium);
        container_name_gross.setTypeface(font_roboto_thin);
        anzahl_feld_gross.setTypeface(font_roboto_thin);
        icon_eingabe_grosser_container.setTypeface(font_roboto_thin);

        // Font für Containerbereich SETZEN

        formel_text_1.setTypeface(font_roboto_thin);
        formel_restwert.setTypeface(font_roboto_medium);
        formel_text_2.setTypeface(font_roboto_thin);

        // INHALT Activity wählen --- //

        // Inhalt: Aktuelle Buchungseigenschaften von letzter Aktivity holen

        final Buchung aktuelleBuchung = (Buchung) getIntent().getParcelableExtra("aktuelleBuchungKEY");

        Button eingabeAnzahl20Container = (Button) findViewById(R.id.icon_eingabe_kleiner_container);
        Button eingabeAnzahl40Container = (Button) findViewById(R.id.icon_eingabe_grosser_container);

        // Inhalt: Prüfen ob kleines oder großes Schiff übergeben wurde

                String text_kleines_schiff = new String("Kleines Schiff");
                String text_grosses_schiff = new String("Großes Schiff");

                String uebergebener_schiffstyps = new String(aktuelleBuchung.getSchifftyp());

                // TEST --Container übernehmen -- TEST

                Integer übergebene_zahl_container_klein = new Integer(aktuelleBuchung.getContainerZahlKlein());
                Integer übergebene_zahl_container_gross = new Integer(aktuelleBuchung.getContainerZahlGross());

        //  Kleines Schiff vorhanden?

        if (text_kleines_schiff.equals(uebergebener_schiffstyps)){

            schifftyp_k_o_g.setText(R.string.schiff_klein_1);
            schiff_beschreibung.setText(R.string.text_schiff_klein);
            ladekapazitaet_zahl.setText("8");
            
			// Aktuelle Kapazität berechnen
			
			// Statt 2 später mit Attribut "Platzbedarf" Klasse "Schiffstyp" rechnen

			kapazitaet_uebergeben = 8;
			aktuelle_kapazitaet = kapazitaet_uebergeben - (aktuelleBuchung.getContainerZahlKlein() + 2*(aktuelleBuchung.getContainerZahlGross())); 
		
			formel_restwert.setText(Integer.toString(aktuelle_kapazitaet));

        }
            // Großes Schiff vorhanden?

        else {

            schifftyp_k_o_g.setText(R.string.schiff_groß_1);
            schiff_beschreibung.setText(R.string.text_schiff_groß);
            ladekapazitaet_zahl.setText("20");
            
			kapazitaet_uebergeben = 20;
			aktuelle_kapazitaet = kapazitaet_uebergeben - (aktuelleBuchung.getContainerZahlKlein() + 2*(aktuelleBuchung.getContainerZahlGross())); 
		
			formel_restwert.setText(Integer.toString(aktuelle_kapazitaet));
        }

        ///// Aktuelle Containerzahl setzen

            // Kleiner Container

            // Zahl und Design setzen

            eingabeAnzahl20Container.setText(Integer.toString(übergebene_zahl_container_klein));

            eingabeAnzahl20Container.getResources().getDrawable(R.drawable.zellenrahmen_3);
            eingabeAnzahl20Container.getResources().getColor(R.color.standard_orange);


            // Großer Container

            // Zahl und Design setzen

            eingabeAnzahl40Container.setText(Integer.toString(übergebene_zahl_container_gross));

            eingabeAnzahl40Container.getResources().getDrawable(R.drawable.zellenrahmen_3);
            eingabeAnzahl40Container.getResources().getColor(R.color.standard_orange);


    //BUTTONS

        // BUTTON zurück (links oben) aktivieren

        Button zurueck = (Button) findViewById(R.id.zurueck);
        zurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuchenScreen2.this, BuchenScreen1.class);
                startActivity(intent);
            }
        });

        // BUTTON Eingabe der kleinen Containeranzahl aktivieren


        eingabeAnzahl20Container.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BuchenScreen2.this, BuchenScreen2_1.class);

                // Kleiner Container wird eingeplant

                aktuelleBuchung.setContainerart(1);

                Bundle wertesammlung = new Bundle();

                wertesammlung.putParcelable("aktuelleBuchungKEY", aktuelleBuchung);
                wertesammlung.putInt("aktuelle_kapazitaetKEY",aktuelle_kapazitaet);

                intent.putExtras(wertesammlung);

                startActivity(intent);
            }
        });

        // BUTTON Eingabe der großen Containeranzahl aktivieren


        eingabeAnzahl40Container.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BuchenScreen2.this, BuchenScreen2_1.class);

                // Großer Container wird eingeplant

                aktuelleBuchung.setContainerart(2);

                Bundle wertesammlung = new Bundle();

                wertesammlung.putParcelable("aktuelleBuchungKEY", aktuelleBuchung);
                wertesammlung.putInt("aktuelle_kapazitaetKEY",aktuelle_kapazitaet);

                intent.putExtras(wertesammlung);

                startActivity(intent);
            }
        });


        // BUTTON vorwärts zur Activity activity_containern_screen_3

        ImageButton vorwaerts = (ImageButton) findViewById(R.id.vorwaerts);
        vorwaerts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
			
			if(button_vorwaerts_transparent == 1){
			
			return;
			
			}
			
			if(button_vorwaerts_voll == 1){
			

                Intent intent = new Intent(BuchenScreen2.this, BuchenScreen4.class);

                intent.putExtra("aktuelleBuchungKEY", aktuelleBuchung);

                startActivity(intent);
            }
			}

        });

        // --- //

        // Nuller (0) nicht anzeigen

        if(aktuelleBuchung.containerZahlKlein == 0){
            icon_eingabe_kleiner_container.setText("-");
        }

        if(aktuelleBuchung.containerZahlGross == 0){
            icon_eingabe_grosser_container.setText("-");
        }

        // --- //

		// Keine Container gewählt > transparenter Pfeil

		if(aktuelleBuchung.containerZahlKlein == 0 && aktuelleBuchung.containerZahlGross == 0){

		vorwaerts.setImageResource(R.drawable.icon_button_yes_transparent);
		
		button_vorwaerts_transparent = 1;
		button_vorwaerts_voll = 0;
				
		}

        // Container gewählt > normaler Pfeil
		
		if(aktuelleBuchung.containerZahlKlein != 0 || aktuelleBuchung.containerZahlGross != 0){
		
				
		vorwaerts.setImageResource(R.drawable.icon_button_yes);
		
		button_vorwaerts_transparent = 0;
		button_vorwaerts_voll = 1;
				
		}
		
    }
}