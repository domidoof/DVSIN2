package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrian.dvsin.Schiffsklassen.Ebene;
import com.example.adrian.dvsin.MainActivity;
import com.example.adrian.dvsin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContainernScreen2 extends AppCompatActivity {


    // TextView Menüstruktur

    TextView zurueck, text_ebene, ebenennummer, container_id_text, container_id_nummer;

    ImageButton vorwaerts;

    ImageView aktueller_container_icon;


    // String

    String orderID, cellValue, contLargePath, contSmallPath;


    //integer

    //for the cell IDs
    int cellCount = 1;
    int arrayCount = 0;
    int onClickCounter = 0;
    int textViewID;


    // TextView Tabelle

    TextView zelle_01, zelle_02, zelle_03, zelle_04, zelle_05;
    TextView zelle_A0, zelle_B0, zelle_C0, zelle_D0, zelle_E0;

    // TextView Containeranzeige

    TextView aktueller_container_groesse, aktueller_container_wort, aktuelle_containerid_wort, aktuelle_containerid, tempCell;


    //Database

    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseDatabase database2;
    DatabaseReference ref2;


    //Arrays for containers

    ArrayList<String> contLarge = new ArrayList<>();
    ArrayList<String> contSmall = new ArrayList<>();


    // verwendete Fonts

    Typeface font_roboto_thin, font_roboto_medium;


    // Ebenentest BEGINN


    TextView aktuelleEbeneAnzeige;

    Ebene schiffsebene = new Ebene(8);


    // Ebenentest ENDE


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_containern_screen_2);


        //get the order ID from the screen before
        Intent intent = getIntent();
        orderID = intent.getStringExtra("ORDER_ID");
        Log.d("WHERE_IS_THE_ORDER", orderID);

        setActivityViews();

        setLevelButtons();

        //set orderID in Screen
        container_id_nummer.setText(orderID);

        getLargeContainer();

        getSmallContainer();


        //-- BUTTONS

        // BUTTON zurück (links oben) aktivieren

        //Button zurueck = (Button) findViewById(R.id.zurueck);


        // BUTTON vorwärts zur Activity activity_containern_screen_3

        //ImageButton vorwaerts = (ImageButton) findViewById(R.id.vorwaerts);

    }

    private void setLevelButtons() {
        // EBENE Allgemein

        aktuelleEbeneAnzeige = (TextView) findViewById(R.id.ebenen_nummer);

        final ImageButton ebene_oben = (ImageButton) findViewById(R.id.ebene_oben);
        final ImageButton ebene_unten = (ImageButton) findViewById(R.id.ebene_unten);


        // schiffsebene nach oben wechseln

        ebene_oben.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                schiffsebene.getAktuelleEbene();

                // Neue Ebenenennummer berechnen, schiffsebene nach oben wandern

                {
                    if (schiffsebene.aktuelleEbene >= 1 && schiffsebene.aktuelleEbene < schiffsebene.ebenenanzahl) {
                        schiffsebene.setAktuelleEbene(schiffsebene.aktuelleEbene +1);

                        aktuelleEbeneAnzeige.setText(Integer.toString(schiffsebene.getAktuelleEbene()));


                        // Buttondarstellung von weiss nach ornage ändern, je nach schiffsebene

                    }
                    if (schiffsebene.aktuelleEbene == schiffsebene.ebenenanzahl) {
                        ebene_oben.setImageResource(R.drawable.button_ebene_nach_oben_orange);
                        ebene_unten.setImageResource(R.drawable.button_ebene_nach_unten_weiss);
                    }
                    else {
                        ebene_oben.setImageResource(R.drawable.button_ebene_nach_oben_weiss);
                        ebene_unten.setImageResource(R.drawable.button_ebene_nach_unten_weiss);

                    }
                }


            }


        });


        // schiffsebene nach unten wechseln

        ebene_unten.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                schiffsebene.getAktuelleEbene();

                // Neue Ebenenennummer berechnen, schiffsebene nach oben wandern

                {
                    if (schiffsebene.aktuelleEbene > 1 && schiffsebene.aktuelleEbene <= schiffsebene.ebenenanzahl) {
                        schiffsebene.setAktuelleEbene(schiffsebene.aktuelleEbene -1);

                        aktuelleEbeneAnzeige.setText(Integer.toString(schiffsebene.getAktuelleEbene()));

                        // Buttondarstellung von weiss nach ornage ändern, je nach schiffsebene

                    }
                    if (schiffsebene.aktuelleEbene == 1) {
                        ebene_unten.setImageResource(R.drawable.button_ebene_nach_unten_orange);
                        ebene_oben.setImageResource(R.drawable.button_ebene_nach_oben_weiss);
                    }
                    else {
                        ebene_unten.setImageResource(R.drawable.button_ebene_nach_unten_weiss);
                        ebene_oben.setImageResource(R.drawable.button_ebene_nach_oben_weiss);
                    }

                }


            }


        });
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(ContainernScreen2.this, ContainernScreen1.class);
        startActivity(intent);
    }

    public void onClickNext(View view) {

        if (contLarge.size() != 0) {
            if (onClickCounter <= (contLarge.size()-1)) {
                vorwaerts.setImageResource(R.drawable.button_vorwaerts);
                aktueller_container_groesse.setText(R.string.container_nummer_40);
                aktueller_container_icon.setImageResource(R.drawable.icon_container_40_dunkelgrau);

                setContainerLarge();
            }
            else if (onClickCounter > (contLarge.size())-1) {

                //color the last Large containers green
                cellCount--;
                cellValue = "cell_" + cellCount;
                textViewID = getResources().getIdentifier(cellValue, "id", getPackageName());
                tempCell = findViewById(textViewID);
                tempCell.setBackgroundColor(Color.rgb(0, 255, 0));

                cellCount--;

                cellValue = "cell_" + cellCount;
                textViewID = getResources().getIdentifier(cellValue, "id", getPackageName());
                tempCell = findViewById(textViewID);
                tempCell.setBackgroundColor(Color.rgb(0, 255, 0));
                cellCount = cellCount + 2;

                vorwaerts.setImageResource(R.drawable.button_vorwaerts_weiss);
                aktueller_container_groesse.setText(R.string.container_nummer_20);
                aktueller_container_icon.setImageResource(R.drawable.icon_container_20_dunkelgrau);
                aktuelle_containerid.setText("-");

                arrayCount = 0;
                contLarge.clear();
                onClickCounter = 0;
            }
            else {

            }
        }
        else if (contSmall.size() != 0){
            if (onClickCounter <= (contSmall.size())-1) {
                vorwaerts.setImageResource(R.drawable.button_vorwaerts);
                aktueller_container_groesse.setText(R.string.container_nummer_20);
                aktueller_container_icon.setImageResource(R.drawable.icon_container_20_dunkelgrau);

                setContainerSmall();
            }
            else if (onClickCounter > (contSmall.size()-1)) {

                //color the containers from before green
                cellCount--;
                cellValue = "cell_" + cellCount;
                textViewID = getResources().getIdentifier(cellValue, "id", getPackageName());
                tempCell = findViewById(textViewID);
                tempCell.setBackgroundColor(Color.rgb(0, 255, 0));

                cellCount++;

                vorwaerts.setImageResource(R.drawable.button_done);
                aktuelle_containerid.setText("-");

                arrayCount = 0;
                contSmall.clear();
                onClickCounter = 0;
            }
        }
        else if (cellCount > 1) {
            Intent intent = new Intent(ContainernScreen2.this, ContainernScreen3.class);
            intent.putExtra("ORDER_ID", orderID);
            startActivity(intent);
        }
    }

    private void setActivityViews() {
        // TextView Menüstruktur zuweisen

        zurueck = (TextView) findViewById(R.id.zurueck);
        text_ebene = (TextView) findViewById(R.id.text_ebene);
        ebenennummer = (TextView) findViewById(R.id.ebenen_nummer);
        container_id_text = (TextView) findViewById(R.id.container_id_text);
        container_id_nummer = (TextView) findViewById(R.id.container_id_nummer);
        // order_id_text = findViewById(R.id.order_id_text);
        // order_id_nummer = findViewById(R.id.order_id_nummer);

        //ImageButton

        vorwaerts = (ImageButton) findViewById(R.id.vorwaerts);


        //ImageView

        aktueller_container_icon = (ImageView) findViewById(R.id.aktueller_container_icon);


        //TextView Tabelle zuweisen

        zelle_01 = (TextView) findViewById(R.id.zelle_01);
        zelle_02 = (TextView) findViewById(R.id.zelle_02);
        zelle_03 = (TextView) findViewById(R.id.zelle_03);
        zelle_04 = (TextView) findViewById(R.id.zelle_04);
        zelle_05 = (TextView) findViewById(R.id.zelle_05);

        zelle_A0 = (TextView) findViewById(R.id.zelle_A0);
        zelle_B0 = (TextView) findViewById(R.id.zelle_B0);
        zelle_C0 = (TextView) findViewById(R.id.zelle_C0);
        zelle_D0 = (TextView) findViewById(R.id.zelle_D0);
        zelle_E0 = (TextView) findViewById(R.id.zelle_E0);


        // TextView Containeranzeige zuweisen

        aktuelle_containerid = (TextView) findViewById(R.id.aktuelle_containerid);
        aktuelle_containerid_wort = (TextView) findViewById(R.id.aktuelle_containerid_wort);
        aktueller_container_groesse = (TextView) findViewById(R.id.aktueller_container_groesse);
        aktueller_container_wort = (TextView) findViewById(R.id.aktueller_container_wort);


        // Fonts einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(), "fonts/roboto-medium.ttf");


        //-- Fonts ANWENDEN --

        // Fonts für Menüpunkte setzen

        zurueck.setTypeface(font_roboto_thin);
        text_ebene.setTypeface(font_roboto_thin);
        ebenennummer.setTypeface(font_roboto_thin);
        container_id_text.setTypeface(font_roboto_thin);
        container_id_nummer.setTypeface(font_roboto_medium);
        // order_id_text.setTypeface(font_roboto_thin);
        // order_id_nummer.setTypeface(font_roboto_medium);

        // set text für orderid
        // order_id_nummer.setText(orderID);


        // Fonts für Tabelle setzen

        zelle_01.setTypeface(font_roboto_thin);
        zelle_02.setTypeface(font_roboto_thin);
        zelle_03.setTypeface(font_roboto_thin);
        zelle_04.setTypeface(font_roboto_thin);
        zelle_05.setTypeface(font_roboto_thin);

        zelle_A0.setTypeface(font_roboto_thin);
        zelle_B0.setTypeface(font_roboto_thin);
        zelle_C0.setTypeface(font_roboto_thin);
        zelle_D0.setTypeface(font_roboto_thin);
        zelle_E0.setTypeface(font_roboto_thin);


        // Fonts für Containeranzeige setzen

        aktuelle_containerid.setTypeface(font_roboto_medium);
        aktuelle_containerid_wort.setTypeface(font_roboto_thin);
        aktueller_container_groesse.setTypeface(font_roboto_medium);
        aktueller_container_wort.setTypeface(font_roboto_thin);
    }

    private void getSmallContainer() {
        contSmallPath = "orders/" + orderID + "/containerSmall";
        database2 = FirebaseDatabase.getInstance();
        ref2 = database2.getReference(contSmallPath);

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snp : dataSnapshot.getChildren()) {
                    contSmall.add(String.valueOf(snp.getKey()));
                    Log.d("TAG", "Value is: " + snp);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());

            }
        });
    }

    private void getLargeContainer() {
        contLargePath = "orders/" + orderID + "/containerLarge";
        database = FirebaseDatabase.getInstance();
        ref = database.getReference(contLargePath);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snp : dataSnapshot.getChildren()) {
                    contLarge.add(String.valueOf(snp.getKey()));
                    Log.d("TAG", "Value is: " + snp);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());

            }
        });
    }

    private void setContainerSmall() {
        if (arrayCount >= 1) {

            //color the containers from before green
            cellCount--;
            cellValue = "cell_" + cellCount;
            textViewID = getResources().getIdentifier(cellValue, "id", getPackageName());
            tempCell = findViewById(textViewID);
            tempCell.setBackgroundColor(Color.rgb(0, 255, 0));

            cellCount++;

            cellValue = "cell_" + cellCount;
            textViewID = getResources().getIdentifier(cellValue, "id", getPackageName());
            tempCell = findViewById(textViewID);
            tempCell.setText(contSmall.get(arrayCount));
            tempCell.setBackgroundColor(Color.rgb(255, 0, 0));

            aktuelle_containerid.setText(contSmall.get(arrayCount));

            arrayCount++;
            cellCount++;
            onClickCounter++;
        }
        else {
            cellValue = "cell_" + cellCount;
            textViewID = getResources().getIdentifier(cellValue, "id", getPackageName());
            tempCell = findViewById(textViewID);
            tempCell.setText(contSmall.get(arrayCount));
            tempCell.setBackgroundColor(Color.rgb(255, 0, 0));

            aktuelle_containerid.setText(contSmall.get(arrayCount));

            arrayCount++;
            cellCount++;
            onClickCounter++;
        }
    }

    private void setContainerLarge() {
        if (arrayCount >= 1) {

            //color the containers from before green
            cellCount--;
            cellValue = "cell_" + cellCount;
            textViewID = getResources().getIdentifier(cellValue, "id", getPackageName());
            tempCell = findViewById(textViewID);
            tempCell.setBackgroundColor(Color.rgb(0, 255, 0));

            cellCount--;

            cellValue = "cell_" + cellCount;
            textViewID = getResources().getIdentifier(cellValue, "id", getPackageName());
            tempCell = findViewById(textViewID);
            tempCell.setBackgroundColor(Color.rgb(0, 255, 0));
            cellCount = cellCount + 2;


            //show next containers in red
            cellValue = "cell_" + cellCount;
            textViewID = getResources().getIdentifier(cellValue, "id", getPackageName());
            tempCell = findViewById(textViewID);
            tempCell.setText(contLarge.get(arrayCount));
            tempCell.setBackgroundColor(Color.rgb(255, 0, 0));

            cellCount++;

            cellValue = "cell_" + cellCount;
            textViewID = getResources().getIdentifier(cellValue, "id", getPackageName());
            tempCell = findViewById(textViewID);
            tempCell.setText(contLarge.get(arrayCount));
            tempCell.setBackgroundColor(Color.rgb(255, 0, 0));

            aktuelle_containerid.setText(contLarge.get(arrayCount));

            arrayCount++;
            cellCount++;
            onClickCounter++;
        }
        else {
            cellValue = "cell_" + cellCount;
            textViewID = getResources().getIdentifier(cellValue, "id", getPackageName());
            tempCell = findViewById(textViewID);
            tempCell.setText(contLarge.get(arrayCount));
            tempCell.setBackgroundColor(Color.rgb(255, 0, 0));

            cellCount++;

            cellValue = "cell_" + cellCount;
            textViewID = getResources().getIdentifier(cellValue, "id", getPackageName());
            tempCell = findViewById(textViewID);
            tempCell.setText(contLarge.get(arrayCount));
            tempCell.setBackgroundColor(Color.rgb(255, 0, 0));

            aktuelle_containerid.setText(contLarge.get(arrayCount));

            arrayCount++;
            cellCount++;
            onClickCounter++;
        }
    }

}
