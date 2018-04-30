package com.example.adrian.dvsin;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrian.dvsin.Screens.ContainernScreen1;
import com.example.adrian.dvsin.Screens.BuchenScreen1;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.view.Gravity.BOTTOM;

public class MainActivity extends AppCompatActivity {

// -- INSTANZVARIABLEN festlegen -- //

    // int

    int saufCount;


    // String


    // -- Others -- //

    // TEXTVIEW 

    TextView hauptmenue, buchen_beschreibung, containern_buchen, doku_beschreibung, suchen_bescheibung, aendern_bescheibung;

    TextView benutzername_aktuell, benutzer_status;


    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;


    // BUTTONS

    Button zurueck;


    // IMAGEBUTTON

    ImageButton menuepunkt_buchen, menuepunkt_containern;
    ImageButton button_benuter_informationen;


    // LAYOUTINFLATOR

    LayoutInflater layoutInflater;


    // POPUPVIEW

    View popupView;


    // POPUPWINDWOW

    PopupWindow popupWindow;


    // ANIMATION

    Animation animPopUp;
  
  
    // DATABASE
    FirebaseDatabase database;
    DatabaseReference myRef;


    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        Intent intent = getIntent();
        String user = intent.getStringExtra("USER_NAME");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
        myRef.setValue(user);


        // IDs zuordnen

            setIDs();


        // FONTS einbeziehen

            setFonts();


        // -- FONTS ANWENDEN

            setFontsToIDs();


        // -- BUTTONS  -- //

        // BUTTON "menuepunkt_buchen" drücken

            startMethodeBuchen();


        // BUTTON "menuepunkt_containern" drücken

            startMethodeContainern();


        // BUTTON "button_benuter_informationen" drücken

            showUserDetails();


        // ########## //

        // -- ACTIVITY Ende -- //

        // *** ENDE *** //

    }

    // --- WEITERE Methoden --- //

    private void startMethodeBuchen() {

        //	FUNKTION "Buchen" wird gestartet

        menuepunkt_buchen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BuchenScreen1.class);
                startActivity(intent);
            }
        });

    }

    private void startMethodeContainern() {

        //	FUNKTION "Containern" wird gestartet

        menuepunkt_containern.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContainernScreen1.class);
                startActivity(intent);
            }
        });

    }

    private void setFontsToIDs() {

        // FONTS SETZEN

        hauptmenue.setTypeface(font_roboto_thin);

        buchen_beschreibung.setTypeface(font_roboto_thin);
        containern_buchen.setTypeface(font_roboto_thin);
        doku_beschreibung.setTypeface(font_roboto_thin);
        suchen_bescheibung.setTypeface(font_roboto_thin);
        aendern_bescheibung.setTypeface(font_roboto_thin);

        benutzername_aktuell.setTypeface(font_roboto_medium);
        benutzer_status.setTypeface(font_roboto_thin);

    }

    private void setFonts() {

        // Fonts einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(), "fonts/roboto-medium.ttf");

    }

    private void setIDs() {

        // IDs ZUORDNEN

        // TEXTVIEW

        hauptmenue = (TextView) findViewById(R.id.hauptmenue_text);

        buchen_beschreibung = (TextView) findViewById(R.id.buchen_beschreibung);
        containern_buchen = (TextView) findViewById(R.id.containern_beschreibung);
        doku_beschreibung = (TextView) findViewById(R.id.doku_beschreibung);
        suchen_bescheibung = (TextView) findViewById(R.id.suchen_beschreibung);
        aendern_bescheibung = (TextView) findViewById(R.id.aendern_beschreibung);

        // IMAGEBUTTON

        menuepunkt_buchen = (ImageButton) findViewById(R.id.buchen_button);
        menuepunkt_containern = (ImageButton) findViewById(R.id.containern_button);
        button_benuter_informationen = (ImageButton) findViewById(R.id.user_id);

        // LAYOUTINFLATER initialisieren

        layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        // VIEW initialisieren

        popupView = layoutInflater.inflate(R.layout.activity_popup_user_login_02, null);

        // POPUPWINDOW initialisieren

        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        // TEXTVIEW PopUpWindow

        benutzername_aktuell = (TextView) popupView.findViewById(R.id.benutzername_aktuell);
        benutzer_status = (TextView) popupView.findViewById(R.id.benutzer_status);

        // ANIMATION

        animPopUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup_v2);

    }

    private void showUserDetails() {

        //	FUNKTION "Benutzerinformationen" wird gestartet

        button_benuter_informationen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                setPopUpDetailsForAll();

            }
        });

    }

    private void setPopUpDetailsForAll(){

        // KOMMENTIEREN, wenn verstanden ;)

        // Eigenschaften POPUPWINDOW "popupWindow" festlegen

        popupView.startAnimation(animPopUp);

        // Design anpassen

        benutzername_aktuell.setTextColor(getResources().getColor(R.color.weiss_hintergrund_screen));
        benutzer_status.setTextColor(getResources().getColor(R.color.weiss_hintergrund_screen));

        //

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);

        // Hintergrund festlegen

        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        // Position ausgehend con BUTTON "vorwaerts"

        popupWindow.showAtLocation(button_benuter_informationen,BOTTOM,0,84);

        //Int

        saufCount = 0;

    }


    // kleines gimmick mit musik

    public void textSaufenPlay(View view) {
        saufCount++;

        //a little sugarle

        if (saufCount == 2) {
            Toast.makeText(this, "oh oh was passiert hier?", Toast.LENGTH_SHORT).show();
        }

        if (saufCount == 4) {
            Toast.makeText(this, "einmal noch dann...", Toast.LENGTH_SHORT).show();
        }

        if (saufCount == 5) {
            MediaPlayer mp = MediaPlayer.create(this, R.raw.saufen_morgens_immer);
            mp.start();
        }
    }

}