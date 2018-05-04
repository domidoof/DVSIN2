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

public class ContainernScreen1 extends AppCompatActivity {

    // -- INSTANZVARIABLEN festlegen -- //

    // int
    int i;
    int rowCount;


    // String


    // -- Others -- //

    // TEXTVIEW Eingabeaufforderung und Menüstruktur

    TextView eingabeaufforderung_1, eingabeaufforderung_2, eingabeaufforderung_3, eingabeaufforderung_4, eingabeaufforderung_5;
		
	// TEXTVIEW Buchungsbereich
	
    TextView buchungsnummer_abkuerzung_01, buchungsnummer_abkuerzung_02, buchungsnummer_aktuell_01, buchungsnummer_aktuell_02;

    // TEXTVIEW Buchungsbereich (dynamsich)

    TextView buchungsNrAbkürzung, buchungsNrAktuell, zwischenBereich1, zwischenBereich2, zwischenBereich3;


    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;


    // BUTTONS

    Button zurueck;
	Button buchung_auswaehlen_01_01;
	Button buchung_auswaehlen_01_02;
	Button weiter;
	Button weiterClick;


	// LINEARLAYOUTS

    LinearLayout buchungsteil;


	// TABLE

    TableLayout buchngsDatentabelle;


    // TABLEROW

    TableRow buchungsDatenzeileVorgabe;
    //TableRow buchungsDatenzeile;


    // TABLEROW.LAYOUTPARAMS

    TableRow.LayoutParams buchungsDatenzeile_Layout;
    TableRow.LayoutParams buchungsteil_Layout;
    TableRow.LayoutParams buchungsNrAbkürzung_Layout;
    TableRow.LayoutParams buchungsNrAktuell_Layout;
    TableRow.LayoutParams weiter_Layout;
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
        setContentView(R.layout.activity_containern_screen_1);


        // IDs zuordnen

            setIDs();


        // FONTS einbeziehen

            setFonts();


        // -- FONTS ANWENDEN

            setFontsToIDs();


        // BuchungsdatenTabelle erstellen

            getDatabaseOrders();

            waitForData();

        // ------ //

        // ########## //

    // -- ACTIVITY Ende -- //

    // *** ENDE *** //

    }

    private void waitForData() {

        loadingScreen = findViewById(R.id.animation_view);
        loadingScreen.setAnimation("off_time_leap_frog_loader.json");
        loadingScreen.playAnimation();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 2000ms

                lookForStatus();

                newHandler();


                // --- MUSS in andere Funktion eingebaut werden --- //

                // BUTTON "buchung_auswaehlen_01_01" drücken

                //buttonBuchungVerladenStarten();


                // BUTTON "buchung_auswaehlen_01_02" drücken

                //buttonBuchungVerladenStarten();

            }
        }, 2000);
    }

    // --- WEITERE Methoden --- //

    //Handler for wait until database query is finished

    private void newHandler() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms

                loadingScreen.setVisibility(View.GONE);
                loadingScreen.cancelAnimation();

                setBuchungsDatentabelle();


                // -- BUTTONS  -- //

                // BUTTON "zurueck" drücken

                buttonGetBack();


                // --- MUSS in andere Funktion eingebaut werden --- //

                // BUTTON "buchung_auswaehlen_01_01" drücken

                //buttonBuchungVerladenStarten();


                // BUTTON "buchung_auswaehlen_01_02" drücken

                //buttonBuchungVerladenStarten();

            }
        }, 3000);
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
            if (orderStatus.get(i).equals("open")) {

                // --- ERSTELLEN der neuen TABELLENZELLE --- //

                buchungsDatenzeileVorgabe = new TableRow(this);

                // LAYOUT setzen

                buchungsDatenzeileVorgabe.setLayoutParams(buchungsDatenzeile_Layout);

                // INHALT und weitere Eigenschaften

                buchungsDatenzeileVorgabe.setPadding(0,0,0,17);

                // ##### //


                // --- ERSTELLEN des INHALTS für neue TABELLENZELLE --- //

                buchungsteil = new LinearLayout(new ContextThemeWrapper(this, R.style.buchungsteil), null, 0);

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

                buchungsNrAbkürzung.setTextAppearance(this, R.style.buchungsNrAbkürzung);

                buchungsNrAbkürzung.setTypeface(font_roboto_thin);

                buchungsNrAbkürzung.setText("BuNr.:");


                buchungsNrAbkürzung.setGravity(17);

                buchungsNrAbkürzung.setPadding(0,0,2,0);

                // ##### //


                buchungsNrAktuell = new TextView(this, null, R.style.buchungsNrAktuell);

                // LAYOUT setzen

                buchungsNrAktuell.setLayoutParams(buchungsNrAktuell_Layout);

                // INHALT und weitere Eigenschaften

                buchungsNrAktuell.setTextAppearance(this, R.style.buchungsNrAktuell);

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


                weiter = new Button(this, null, R.style.weiter);

                // LAYOUT setzen

                weiter.setLayoutParams(weiter_Layout);

                // INHALT und weitere Eigenschaften

                weiter.setTextAppearance(this, R.style.weiter);

                weiter.setTypeface(font_roboto_thin);

                weiter.setText(getText(R.string.weiterButton));

                weiter.setId(i+1);

                //id and orderid for inside method usage
                final String orderID = str;
                final int shipCount = i;
                //Log.d("TAG", orderID);


                // ------------------ muss noch geändert werden, sobald der screen für das kleine schiff ferztig ist! -----------------------------------

                //weiterClick = findViewById(id_);
                weiter.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (orderShip.get(shipCount).equals("Großes Schiff")) {
                            Intent intent = new Intent(ContainernScreen1.this, ContainernScreen2.class);
                            intent.putExtra("ORDER_ID", orderID);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(ContainernScreen1.this, ContainernScreen2.class);
                            intent.putExtra("ORDER_ID", orderID);
                            startActivity(intent);
                        }

                    }
                });


                weiter.setBackgroundColor(Color.parseColor("#ff883f"));


                weiter.setGravity(17);

                // ------ //



                zwischenBereich2 = new TextView (this, null, R.style.zwischenBereich1);

                // LAYOUT setzen

                zwischenBereich2.setLayoutParams(zwischenBereich1_Layout);

                // INHALT und weitere Eigenschaften

                zwischenBereich2.setTextAppearance(this, R.style.zwischenBereich1);


                zwischenBereich2.setGravity(1);

                // ------ //



                zwischenBereich3 = new TextView(this, null, R.style.zwischenBereich3);

                // LAYOUT setzen

                zwischenBereich3.setLayoutParams(zwischenBereich3_Layout);

                // INHALT und weitere Eigenschaften

                zwischenBereich3.setTextAppearance(this, R.style.zwischenBereich3);


                zwischenBereich3.setBackgroundColor(Color.parseColor("#ff731d"));


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

//	private void buttonBuchungVerladenStarten(){
//
//		// BUTTON "buchung_containern_01" wird gedrückt
//
//        buchung_auswaehlen_01_01.setOnClickListener( new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ContainernScreen1.this, ContainernScreen2.class);
//
//				// ACTIVITY ContainernScreen2 starten
//
//                startActivity(intent);
//            }
//        });
//
//
//		// BUTTON "buchung_containern_02" wird gedrückt
//
//        buchung_auswaehlen_01_02.setOnClickListener( new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ContainernScreen1.this, ContainernScreen2.class);
//
//				// ACTIVITY ContainernScreen2 starten
//
//                startActivity(intent);
//            }
//        });
//
//	}

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
		
//		buchungsnummer_abkuerzung_01.setTypeface(font_roboto_thin);
//		buchungsnummer_abkuerzung_02.setTypeface(font_roboto_thin);
//        buchungsnummer_aktuell_01.setTypeface(font_roboto_medium);
//        buchungsnummer_aktuell_02.setTypeface(font_roboto_medium);

        // FONTS für Buchungsbereich SETZEN (dynamisch)

        // funktioniert nicht ;(
		
		// FONTS Buttons setzen

        zurueck.setTypeface(font_roboto_thin);
//		buchung_auswaehlen_01_01.setTypeface(font_roboto_thin);
//        buchung_auswaehlen_01_02.setTypeface(font_roboto_thin);

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
		
//		buchungsnummer_abkuerzung_01 =(TextView) findViewById(R.id.buchungsnummer_abkuerzung_01);
//        buchungsnummer_abkuerzung_02 = (TextView) findViewById(R.id.buchungsnummer_abkuerzung_02);
//        buchungsnummer_aktuell_01 = (TextView) findViewById(R.id.buchungsnummer_aktuell_01);
//        buchungsnummer_aktuell_02 = (TextView) findViewById(R.id.buchungsnummer_aktuell_02);
        
        // BUTTONS

        zurueck = (Button) findViewById(R.id.zurueck);
//		buchung_auswaehlen_01_01 = (Button) findViewById(R.id.buchung_auswaehlen_01_01);
//        buchung_auswaehlen_01_02 = (Button) findViewById(R.id.buchung_auswaehlen_01_02);

        // TABLELAYOUT

        buchngsDatentabelle = (TableLayout) findViewById(R.id.buchungsDatentabelle);

        // TABELLENZEILE

//        buchungsDatenzeile = findViewById(R.id.buchungsDatenzeile);

        // LINEARLAYOUTS

    }

    public void useLayoutParamsForNewRow(){

        buchungsDatenzeile_Layout = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.MATCH_PARENT);

        buchungsteil_Layout = new TableRow.LayoutParams(buchungsDatenzeile_Layout.MATCH_PARENT,  buchungsDatenzeile_Layout.MATCH_PARENT, 15);

        buchungsNrAbkürzung_Layout = new TableRow.LayoutParams(buchungsteil_Layout.WRAP_CONTENT,  buchungsteil_Layout.MATCH_PARENT);

        buchungsNrAktuell_Layout = new TableRow.LayoutParams(buchungsteil_Layout.WRAP_CONTENT,  buchungsteil_Layout.MATCH_PARENT);

        weiter_Layout = new TableRow.LayoutParams(140,140);

        zwischenBereich1_Layout = new TableRow.LayoutParams(buchungsDatenzeile_Layout.WRAP_CONTENT,  buchungsDatenzeile_Layout.MATCH_PARENT, 2);

        zwischenBereich3_Layout = new TableRow.LayoutParams(buchungsDatenzeile_Layout.WRAP_CONTENT,  buchungsDatenzeile_Layout.MATCH_PARENT, 1);

    }

    public void onBackPressed() {

        // Übergang von einer zur nächsten, passenden Activity

        Intent intent = new Intent(ContainernScreen1.this, MainActivity.class);
        startActivity(intent);
    }

}



