package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.adrian.dvsin.MainActivity;
import com.example.adrian.dvsin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Doku extends AppCompatActivity {

    // -- INSTANZVARIABLEN festlegen -- //

    // int
    int i;
    int rowCount;
    int aufsteigend_pu;
    int absteigend_pu;


    // String

    String user;

    // -- Others -- //

    // TEXTVIEW Eingabeaufforderung und Menüstruktur

    TextView eingabeaufforderung_1, eingabeaufforderung_2, eingabeaufforderung_3;


    // TEXTVIEW Buchungsbereich (dynamsich)

    TextView buchungsNrAbkürzung, buchungsNrAktuell, zwischenBereich1, zwischenBereich3, suchkriterium;


    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;


    // BUTTONS

    Button zurueck;

    // IMAGEBUTTON

    ImageButton absteigend;
    ImageButton aufsteigend;

	// LINEARLAYOUTS

    LinearLayout buchungsteil;


	// TABLE

    TableLayout buchngsDatentabelle;


    // TABLEROW

    TableRow buchungsDatenzeileVorgabe;

    // TABLEROW.LAYOUTPARAMS

    TableRow.LayoutParams buchungsDatenzeile_Layout;
    TableRow.LayoutParams buchungsteil_Layout;
    TableRow.LayoutParams buchungsNrAbkürzung_Layout;
    TableRow.LayoutParams buchungsNrAktuell_Layout;
    TableRow.LayoutParams zwischenBereich1_Layout;
    TableRow.LayoutParams zwischenBereich3_Layout;


    // Database Resources

    FirebaseDatabase database2;
    FirebaseDatabase database;


    // Reference to the database

    DatabaseReference ref2;
    DatabaseReference ref;
    DatabaseReference refChildStatus;
    DatabaseReference refChildShip;

    //Array List to save the data from the database

    ArrayList<String> orderList = new ArrayList<>();
    ArrayList<String> orderStatus = new ArrayList<>();
    ArrayList<String> orderShip = new ArrayList<>();


    //setup lottie animation

    LottieAnimationView loadingScreen;
	

    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doku_screen_1);

        Intent intent = getIntent();
        user = intent.getStringExtra("USER_NAME");


        // IDs zuordnen

            setIDs();


        // FONTS einbeziehen

            setFonts();


        // -- FONTS ANWENDEN

            setFontsToIDs();


        // BuchungsdatenTabelle erstellen

            getDatabaseOrders();

            waitForData();


        // -- BUTTONS  -- //

        // BUTTON "zurueck" drücken

            buttonGetBack();


        // IMAGEBUTTONS drücken

            sortTheListUP();

            sortTheListDOWN();


        // ------ //

        // ########## //

    // -- ACTIVITY Ende -- //

    // *** ENDE *** //

    }

    private void sortTheListUP() {

            aufsteigend.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (aufsteigend_pu == 1) {

                    buchngsDatentabelle.removeAllViews();

                    // Zeigt alle verladenen Buchungsnummern

                        showAllOnShips();

                    // Zeigt alle noch nicht verladenen Buchungsnummern

                        showAllNotOnShips();

                        aufsteigend.setImageResource(R.drawable.button_ebene_nach_oben_orange);
                        absteigend.setImageResource(R.drawable.button_ebene_nach_unten_weiss);
                        aufsteigend_pu = 0;
                        absteigend_pu = 1;
                }

                    else {

                    }
            }

        });

    }


    private void sortTheListDOWN() {

        absteigend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (absteigend_pu == 1) {

                    buchngsDatentabelle.removeAllViews();

                    // Zeigt alle noch nicht verladenen Buchungsnummern

                    showAllNotOnShips();

                    // Zeigt alle verladenen Buchungsnummern

                    showAllOnShips();

                    aufsteigend.setImageResource(R.drawable.button_ebene_nach_oben_weiss);
                    absteigend.setImageResource(R.drawable.button_ebene_nach_unten_orange);
                    aufsteigend_pu = 1;
                    absteigend_pu = 0;

                }

                else {

                }
            }

        });

    }

    private void showAllNotOnShips() {

        // LAYOUT.PARAMS setzen

        useLayoutParamsForNewRow();

        i = 0;
        rowCount = 0;


        // NEW TABLEROW hizufügen zu TABLELAYOUT "buchungsDatentabelle"

        for (String str : orderList) {

            if (orderStatus.get(i).equals("open")) {

            // --- ERSTELLEN der neuen TABELLENZELLE --- //

            buchungsDatenzeileVorgabe = new TableRow(this);

            // LAYOUT setzen

            buchungsDatenzeileVorgabe.setLayoutParams(buchungsDatenzeile_Layout);

            // INHALT und weitere Eigenschaften

            buchungsDatenzeileVorgabe.setPadding(0,0,0,17);

            // ##### //


            // --- ERSTELLEN des INHALTS für neue TABELLENZELLE --- //

            buchungsteil = new LinearLayout(new ContextThemeWrapper(this, R.style.buchungsteil_v2), null, 0);

            // LAYOUT setzen

            buchungsteil.setLayoutParams(buchungsteil_Layout);

            // INHALT und weitere Eigenschaften

            buchungsteil.setOrientation(LinearLayout.HORIZONTAL);

            buchungsteil.setPadding(40,0,0,0);

            // ##### //


            buchungsNrAbkürzung = new TextView(this, null, R.style.buchungsNrAbkürzung);

            // LAYOUT setzen


            buchungsNrAbkürzung.setLayoutParams(buchungsNrAbkürzung_Layout);


            // INHALT und weitere Eigenschaften

            buchungsNrAbkürzung.setTextAppearance(this, R.style.buchungsNrAbkürzung_v2);

            buchungsNrAbkürzung.setTypeface(font_roboto_thin);

            buchungsNrAbkürzung.setText("BuNr.:");


            buchungsNrAbkürzung.setGravity(17);

            buchungsNrAbkürzung.setPadding(0,0,2,0);

            // ##### //


            buchungsNrAktuell = new TextView(this, null, R.style.buchungsNrAktuell_v2);

            // LAYOUT setzen

            buchungsNrAktuell.setLayoutParams(buchungsNrAktuell_Layout);

            // INHALT und weitere Eigenschaften

            buchungsNrAktuell.setTextAppearance(this, R.style.buchungsNrAktuell_v2);

            buchungsNrAktuell.setTypeface(font_roboto_medium);

            buchungsNrAktuell.setText(str);


            buchungsNrAktuell.setGravity(17);

            buchungsNrAktuell.setPadding(2,0,10,0);

            // ------ //


            zwischenBereich1 = new TextView (this, null, R.style.zwischenBereich1);

            // TextView zwischenBereich1 = new TextView(new ContextThemeWrapper(this, R.style.zwischenBereich1), null, 0);

            // LAYOUT setzen

            zwischenBereich1.setLayoutParams(zwischenBereich1_Layout);

            // INHALT und weitere Eigenschaften

            zwischenBereich1.setGravity(17);

            zwischenBereich1.setTextAppearance(this, R.style.zwischenBereich1);

            // ------ //


            zwischenBereich3 = new TextView(this, null, R.style.zwischenBereich3);

            // LAYOUT setzen

            zwischenBereich3.setLayoutParams(zwischenBereich3_Layout);

            // INHALT und weitere Eigenschaften

            zwischenBereich3.setTextAppearance(this, R.style.zwischenBereich3);

            // Wenn Buchungsnummer noch nicht verladen, ZWISCHENBEREICH "rot" einfärebn

            zwischenBereich3.setBackgroundColor(Color.parseColor("#f65050"));

            zwischenBereich3.setGravity(17);

            // ------ //


            // LINEARLAYOUT "buchungsteil" füllen

            buchungsteil.addView(buchungsNrAbkürzung);
            buchungsteil.addView(buchungsNrAktuell);

            // TABLEROW "buchungsDatenzeile" füllen

            buchungsDatenzeileVorgabe.addView(buchungsteil);
            buchungsDatenzeileVorgabe.addView(zwischenBereich1);
            buchungsDatenzeileVorgabe.addView(zwischenBereich3);

            // TABLELAYOUT "buchungsDatenzeileVorgabe" füllen

            buchngsDatentabelle.addView(buchungsDatenzeileVorgabe, rowCount);

            i++;
            rowCount++;

            }

            else {

                i++;
            }

        }
    }

    private void showAllOnShips() {

        // LAYOUT.PARAMS setzen

        useLayoutParamsForNewRow();

        i = 0;
        rowCount = 0;


        // NEW TABLEROW hizufügen zu TABLELAYOUT "buchungsDatentabelle"

        for (String str : orderList) {

            if (orderStatus.get(i).equals("closed")) {

                // --- ERSTELLEN der neuen TABELLENZELLE --- //

                buchungsDatenzeileVorgabe = new TableRow(this);

                // LAYOUT setzen

                buchungsDatenzeileVorgabe.setLayoutParams(buchungsDatenzeile_Layout);

                // INHALT und weitere Eigenschaften

                buchungsDatenzeileVorgabe.setPadding(0,0,0,17);

                // ##### //


                // --- ERSTELLEN des INHALTS für neue TABELLENZELLE --- //

                buchungsteil = new LinearLayout(new ContextThemeWrapper(this, R.style.buchungsteil_v2), null, 0);

                // LAYOUT setzen

                buchungsteil.setLayoutParams(buchungsteil_Layout);

                // INHALT und weitere Eigenschaften

                buchungsteil.setOrientation(LinearLayout.HORIZONTAL);

                buchungsteil.setPadding(40,0,0,0);

                // ##### //


                buchungsNrAbkürzung = new TextView(this, null, R.style.buchungsNrAbkürzung);

                // LAYOUT setzen


                buchungsNrAbkürzung.setLayoutParams(buchungsNrAbkürzung_Layout);


                // INHALT und weitere Eigenschaften

                buchungsNrAbkürzung.setTextAppearance(this, R.style.buchungsNrAbkürzung_v2);

                buchungsNrAbkürzung.setTypeface(font_roboto_thin);

                buchungsNrAbkürzung.setText("BuNr.:");


                buchungsNrAbkürzung.setGravity(17);

                buchungsNrAbkürzung.setPadding(0,0,2,0);

                // ##### //


                buchungsNrAktuell = new TextView(this, null, R.style.buchungsNrAktuell_v2);

                // LAYOUT setzen

                buchungsNrAktuell.setLayoutParams(buchungsNrAktuell_Layout);

                // INHALT und weitere Eigenschaften

                buchungsNrAktuell.setTextAppearance(this, R.style.buchungsNrAktuell_v2);

                buchungsNrAktuell.setTypeface(font_roboto_medium);

                buchungsNrAktuell.setText(str);


                buchungsNrAktuell.setGravity(17);

                buchungsNrAktuell.setPadding(2,0,10,0);

                // ------ //


                zwischenBereich1 = new TextView (this, null, R.style.zwischenBereich1);

                // TextView zwischenBereich1 = new TextView(new ContextThemeWrapper(this, R.style.zwischenBereich1), null, 0);

                // LAYOUT setzen

                zwischenBereich1.setLayoutParams(zwischenBereich1_Layout);

                // INHALT und weitere Eigenschaften

                zwischenBereich1.setGravity(17);

                zwischenBereich1.setTextAppearance(this, R.style.zwischenBereich1);

                // ------ //


                zwischenBereich3 = new TextView(this, null, R.style.zwischenBereich3);

                // LAYOUT setzen

                zwischenBereich3.setLayoutParams(zwischenBereich3_Layout);

                // INHALT und weitere Eigenschaften

                zwischenBereich3.setTextAppearance(this, R.style.zwischenBereich3);

                // Wenn Buchungsnummer noch nicht verladen, ZWISCHENBEREICH "grün" einfärebn

                zwischenBereich3.setBackgroundColor(Color.parseColor("#b4eba7"));

                zwischenBereich3.setGravity(17);

                // ------ //


                // LINEARLAYOUT "buchungsteil" füllen

                buchungsteil.addView(buchungsNrAbkürzung);
                buchungsteil.addView(buchungsNrAktuell);

                // TABLEROW "buchungsDatenzeile" füllen

                buchungsDatenzeileVorgabe.addView(buchungsteil);
                buchungsDatenzeileVorgabe.addView(zwischenBereich1);
                buchungsDatenzeileVorgabe.addView(zwischenBereich3);

                // TABLELAYOUT "buchungsDatenzeileVorgabe" füllen

                buchngsDatentabelle.addView(buchungsDatenzeileVorgabe, rowCount);

                i++;
                rowCount++;

            }

            else {

                i++;
            }

        }

    }

    private void waitForData() {

        loadingScreen = findViewById(R.id.animation_view);
        loadingScreen.setAnimation("helicopter.json");
        loadingScreen.playAnimation();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // Do something after 2000ms

                lookForStatus();

                newHandler();

            }
        }, 1500);

        // BUTTON "zurueck" sichtbar7verwendbar machen

    //    zurueck.setVisibility(View.VISIBLE);

    }

    // --- WEITERE Methoden --- //

    // Handler for wait until database query is finished

    private void newHandler() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Do something after 100ms

                loadingScreen.setVisibility(View.GONE);
                loadingScreen.cancelAnimation();

                showOnScreen();

                zurueck.setText(R.string.zurueckzeichen);

            }
        }, 1500);
    }

    private void showOnScreen() {

        // BUTTON "zurück" setzen

        zurueck.setText(R.string.zurueckzeichen);

        // IMAGEBUTTONS setzen

        aufsteigend.setImageResource(R.drawable.button_ebene_nach_oben_weiss);
        absteigend.setImageResource(R.drawable.button_ebene_nach_unten_weiss);

                // Werte für IMAGEBUTTONS setzen

                aufsteigend_pu = 1;
                absteigend_pu = 1;

        // TEXTVIEW setzen

        suchkriterium.setText("Status");

        // TABLE erstellen

        setBuchungsDatentabelle();

    }

    private void lookForStatus() {

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("orders");

        for (String str : orderList) {
            Log.d("ORDER_LIST", str);
            refChildStatus = ref.child(str).child("status");
            refChildStatus.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    orderStatus.add(dataSnapshot.getValue(String.class));
                    Log.d("TEST_VALUE", dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            refChildShip = ref.child(str).child("shipType");
            refChildShip.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    orderShip.add(dataSnapshot.getValue(String.class));
                    Log.d("TEST_SHIP", dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
    }

    private void getDatabaseOrders() {

        database2 = FirebaseDatabase.getInstance();

        ref2 = database2.getReference("orders");

        ref2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snp : dataSnapshot.getChildren()) {
                    orderList.add(String.valueOf(snp.getKey()));
                    //Log.d("TAG", "Value is: " + snp);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());

            }
        });

    }

    private void setBuchungsDatentabelle() {

        // LAYOUT.PARAMS setzen

        useLayoutParamsForNewRow();

        i = 0;
        rowCount = 0;


        // NEW TABLEROW hizufügen zu TABLELAYOUT "buchungsDatentabelle"

        //for (int i = 0; i <4; i++) {

        for (String str : orderList) {

            // if (orderStatus.get(i).equals("open")) {

                // --- ERSTELLEN der neuen TABELLENZELLE --- //

                buchungsDatenzeileVorgabe = new TableRow(this);

                // LAYOUT setzen

                buchungsDatenzeileVorgabe.setLayoutParams(buchungsDatenzeile_Layout);

                // INHALT und weitere Eigenschaften

                buchungsDatenzeileVorgabe.setPadding(0,0,0,17);

                // ##### //


                // --- ERSTELLEN des INHALTS für neue TABELLENZELLE --- //

                buchungsteil = new LinearLayout(new ContextThemeWrapper(this, R.style.buchungsteil_v2), null, 0);

                // LAYOUT setzen

                buchungsteil.setLayoutParams(buchungsteil_Layout);

                // INHALT und weitere Eigenschaften

                buchungsteil.setOrientation(LinearLayout.HORIZONTAL);

                buchungsteil.setPadding(40,0,0,0);

                // ##### //


                buchungsNrAbkürzung = new TextView(this, null, R.style.buchungsNrAbkürzung);

                // LAYOUT setzen


                buchungsNrAbkürzung.setLayoutParams(buchungsNrAbkürzung_Layout);


                // INHALT und weitere Eigenschaften

                buchungsNrAbkürzung.setTextAppearance(this, R.style.buchungsNrAbkürzung_v2);

                buchungsNrAbkürzung.setTypeface(font_roboto_thin);

                buchungsNrAbkürzung.setText("BuNr.:");


                buchungsNrAbkürzung.setGravity(17);

                buchungsNrAbkürzung.setPadding(0,0,2,0);

                // ##### //


                buchungsNrAktuell = new TextView(this, null, R.style.buchungsNrAktuell_v2);

                // LAYOUT setzen

                buchungsNrAktuell.setLayoutParams(buchungsNrAktuell_Layout);

                // INHALT und weitere Eigenschaften

                buchungsNrAktuell.setTextAppearance(this, R.style.buchungsNrAktuell_v2);

                buchungsNrAktuell.setTypeface(font_roboto_medium);

                buchungsNrAktuell.setText(str);


                buchungsNrAktuell.setGravity(17);

                buchungsNrAktuell.setPadding(2,0,10,0);

                // ------ //


                zwischenBereich1 = new TextView (this, null, R.style.zwischenBereich1);

                // TextView zwischenBereich1 = new TextView(new ContextThemeWrapper(this, R.style.zwischenBereich1), null, 0);

                // LAYOUT setzen

                zwischenBereich1.setLayoutParams(zwischenBereich1_Layout);

                // INHALT und weitere Eigenschaften

                zwischenBereich1.setGravity(17);

                zwischenBereich1.setTextAppearance(this, R.style.zwischenBereich1);

                // ------ //


                zwischenBereich3 = new TextView(this, null, R.style.zwischenBereich3);

                // LAYOUT setzen

                zwischenBereich3.setLayoutParams(zwischenBereich3_Layout);

                // INHALT und weitere Eigenschaften

                zwischenBereich3.setTextAppearance(this, R.style.zwischenBereich3);

                // Wenn Buchungsnummer noch nicht verladen, ZWISCHENBEREICH "rot" einfärebn


                if (orderStatus.get(i).equals("open")) {

                    zwischenBereich3.setBackgroundColor(Color.parseColor("#f65050"));

                }

                else{

                    zwischenBereich3.setBackgroundColor(Color.parseColor("#b4eba7"));

                }


                zwischenBereich3.setGravity(17);

                // ------ //



                // LINEARLAYOUT "buchungsteil" füllen

                buchungsteil.addView(buchungsNrAbkürzung);
                buchungsteil.addView(buchungsNrAktuell);

                // TABLEROW "buchungsDatenzeile" füllen

                buchungsDatenzeileVorgabe.addView(buchungsteil);
                buchungsDatenzeileVorgabe.addView(zwischenBereich1);
                buchungsDatenzeileVorgabe.addView(zwischenBereich3);

                // TABLELAYOUT "buchungsDatenzeileVorgabe" füllen

                buchngsDatentabelle.addView(buchungsDatenzeileVorgabe, rowCount);

                i++;
                rowCount++;

            // }

            /*else {

                i++;
            }
*/
        }
    }

    private void buttonGetBack() {

        zurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Doku.this, MainActivity.class);
                intent.putExtra("USER_NAME", user);
                startActivity(intent);

            }
        });

    }

    private void setFontsToIDs() {

        // FONTS für Eingabeaufforderung und Menüstruktur SETZEN

        eingabeaufforderung_1.setTypeface(font_roboto_thin);
        eingabeaufforderung_2.setTypeface(font_roboto_thin);
        eingabeaufforderung_3.setTypeface(font_roboto_medium);

        suchkriterium.setTypeface(font_roboto_thin);

		// FONTS Buttons setzen

        zurueck.setTypeface(font_roboto_thin);

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

        suchkriterium = (TextView) findViewById(R.id.suchkriterium);

        // BUTTONS

        zurueck = (Button) findViewById(R.id.zurueck);

            // BUTTON am Anfang nicht sichtbar

    //            zurueck.setVisibility(View.GONE);

        // IMAGEBUTTON

        aufsteigend = (ImageButton) findViewById(R.id.aufsteigend);
        absteigend = (ImageButton) findViewById(R.id.absteigend);

        // TABLELAYOUT

        buchngsDatentabelle = (TableLayout) findViewById(R.id.buchungsDatentabelle);

    }

    public void useLayoutParamsForNewRow(){

        buchungsDatenzeile_Layout = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.MATCH_PARENT);

        buchungsteil_Layout = new TableRow.LayoutParams(buchungsDatenzeile_Layout.MATCH_PARENT,  140, 15);

        buchungsNrAbkürzung_Layout = new TableRow.LayoutParams(buchungsteil_Layout.WRAP_CONTENT,  buchungsteil_Layout.MATCH_PARENT);

        buchungsNrAktuell_Layout = new TableRow.LayoutParams(buchungsteil_Layout.WRAP_CONTENT,  buchungsteil_Layout.MATCH_PARENT);

        zwischenBereich1_Layout = new TableRow.LayoutParams(buchungsDatenzeile_Layout.WRAP_CONTENT,  buchungsDatenzeile_Layout.MATCH_PARENT, 2);

        zwischenBereich3_Layout = new TableRow.LayoutParams(buchungsDatenzeile_Layout.WRAP_CONTENT,  buchungsDatenzeile_Layout.MATCH_PARENT, 1);

    }

    public void onBackPressed() {

        // Übergang von einer zur nächsten, passenden Activity

        Intent intent = new Intent(Doku.this, MainActivity.class);
        intent.putExtra("USER_NAME", user);
        startActivity(intent);
    }

}



