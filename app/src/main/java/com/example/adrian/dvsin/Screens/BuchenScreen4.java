package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
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

    // int

    int lastElement;
    int newOrderID;
    int randomNum;


    // String

    String childOrderID;


    // -- Others -- //

    // TEXTVIEW Menüstruktur

    TextView text_buchungsbestaetigung_teil_1, text_buchungsbestaetigung_teil_2, text_buchungsbestaetigung_teil_3, text_buchungsbestaetigung_teil_4, buchungsnummer_abkuerzung, loadingBackground;


    // TEXTVIEW Buchungsnummer (dynamisch)

    TextView buchungsnummer_aktuell;


    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;


    // Database Resources

    FirebaseDatabase database;
    FirebaseDatabase database2;


    // Reference to the database

    DatabaseReference newChildRef;
    DatabaseReference containerLargeRef;
    DatabaseReference containerSmallRef;
    DatabaseReference ref2;


    // Array List to save the data from the database

    // ANMERKUNG: Müssen die schon hier initialsiert werden???

    ArrayList<Integer> orderList = new ArrayList<>();
    ArrayList<Integer> containerGrossList = new ArrayList<>();
    ArrayList<Integer> containerKleinList = new ArrayList<>();


    // LOTTIE ANIMATION

    LottieAnimationView loadingScreen;


    // BUCHUNG, letze Activity

    Buchung aktuelleBuchung;


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

        // INHALT Activity wählen --- //

        // Inhalt: Aktuelle Buchungseigenschaften von letzter Aktivity holen

            getAllRequiredInformation();


        //IDs initialisieren

            setIDs();
      

        // Fonts einbeziehen

            setFonts();


        // -- FONTS ANWENDEN

            setFontsToIDs();


        // letzte order ID aus der Datenbank abfragen

            getLatestOrderID();

        // -- BUTTONS  -- //

        // BUTTON "zurueck" drücken

            buttonGetBack();
    }

    // --- WEITERE Methoden --- //

    private void getAllRequiredInformation() {

        aktuelleBuchung = (Buchung) getIntent().getParcelableExtra("aktuelleBuchungKEY");

        Log.d("HERENOW", aktuelleBuchung.getSchifftyp());

    }

    private void writeToDatabase() {

        // set the loading animation


        // ANMERKUNG: Könnte/Sollte unter setIDS
        loadingScreen = findViewById(R.id.animation_view);
        // --
        loadingScreen.setAnimation("off_time_leap_frog_loader.json");
        loadingScreen.playAnimation();



        // ANMERKUNG: Kommentare ins deutsche übersetzen
        // the handler is waiting 5 seconds then executes the code inside it, to make sure, that the database writing action is over

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // Do something after 100ms

                loadingScreen.setVisibility(View.GONE);
                loadingScreen.cancelAnimation();
                loadingBackground.setVisibility(View.GONE);


                // write the new data to the database

                lastElement = orderList.size() - 1;
                newOrderID = orderList.get(lastElement) + 1;

                database = FirebaseDatabase.getInstance();
                Log.d("ORDERIDIS", Integer.toString(newOrderID));

                // set new orderID

                aktuelleBuchung.setBuchungsID(newOrderID);

                // write to textView

                buchungsnummer_aktuell.setText(Integer.toString((aktuelleBuchung.getBuchungsID())));

                // make lists with random container IDs

                for (int i = 0; i < aktuelleBuchung.getContainerZahlGross(); i++) {
                    randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
                    containerGrossList.add(randomNum);
                    Log.d("GROSS_LIST", Integer.toString(containerGrossList.get(i)));
                }


                for (int i = 0; i < aktuelleBuchung.getContainerZahlKlein(); i++) {
                    randomNum = ThreadLocalRandom.current().nextInt(1000, 9999);
                    containerKleinList.add(randomNum);
                    Log.d("KLEIN_LIST", Integer.toString(containerKleinList.get(i)));
                }

                // convert the new orderID to a string

                childOrderID = Integer.toString(newOrderID);

                // add the new order ID as a child of orders to the database

                newChildRef = database.getReference("orders").child(childOrderID);

                // add the large containers to database order

                for (Integer a : containerGrossList) {
                    newChildRef.child("containerLarge").child(Integer.toString(a)).setValue("40");
                }

                // add the small containers to database order

                for (Integer a : containerKleinList) {
                    newChildRef.child("containerSmall").child(Integer.toString(a)).setValue("20");
                }

                // add the ship type to the database order

                newChildRef.child("shipType").setValue(aktuelleBuchung.getSchifftyp());


                // add the order status to the database order

                newChildRef.child("status").setValue("open");


                // BUTTONS

                // BUTTON zurück (links oben) aktivieren

                Button zurueck = (Button) findViewById(R.id.zurueck);
                zurueck.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(BuchenScreen4.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

                // BUTTON angeklickte Buchung verladen; erste Buchungsnummer

                ImageButton containern_gleich_starten = (ImageButton) findViewById(R.id.buchung_containern);
                containern_gleich_starten.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(BuchenScreen4.this, ContainernScreen1.class);
                        startActivity(intent);
                    }
                });

            }
        }, 5000);

    }

    private void buttonGetBack() {

        zurueck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuchenScreen4.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private void getLatestOrderID() {

        //get the order list from the database to set net orderID

        database2 = FirebaseDatabase.getInstance();
        ref2 = database2.getReference("orders");

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snp : dataSnapshot.getChildren()) {
                    orderList.add(Integer.valueOf(snp.getKey()));
                    Log.d("TAGTAGTAG", "Value is: " + snp);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());
            }
        });

        //neuen Auftrag in die Datenbank schreiben
        writeToDatabase();

    }

    private void setFontsToIDs () {

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

    private void setFonts () {

            // Fonts einbeziehen BL

            font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
            font_roboto_medium = Typeface.createFromAsset(getAssets(), "fonts/roboto-medium.ttf");

        }

    private void setIDs () {

            // IDs Buchungsbestätigungsbereich ZUORDNEN

            // TEXTVIEW Buchungsbestätigungsbereich

            loadingBackground = (TextView) findViewById(R.id.loadingBackground);

            text_buchungsbestaetigung_teil_1 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_1);
            text_buchungsbestaetigung_teil_2 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_2);
            text_buchungsbestaetigung_teil_3 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_3);
            text_buchungsbestaetigung_teil_4 = (TextView) findViewById(R.id.text_buchungsbestaetigung_teil_4);
            buchungsnummer_abkuerzung = (TextView) findViewById(R.id.buchungsnummer_abkuerzung);
            // loadingBackground = (TextView) findViewById(R.id.loadingBackground);

            // TEXTVIEW Buchungsnummer (dynamisch)

            buchungsnummer_aktuell = (TextView) findViewById(R.id.buchungsnummer_aktuell);

            // BUTTON

            zurueck = (Button) findViewById(R.id.zurueck);

            // IMAGEBUTTON

            containern_gleich_starten = (ImageButton) findViewById(R.id.buchung_containern);

        }

    public void onBackPressed() {

        // Übergang von einer zur nächsten, passenden Activity

        Intent intent = new Intent(BuchenScreen4.this, MainActivity.class);
        startActivity(intent);

    }

//    private void waitForDatabase() {
//
//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Do something after 100ms
//
//
//            }
//        }, 5000);
//    }

    }


