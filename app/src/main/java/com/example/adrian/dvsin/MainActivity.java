package com.example.adrian.dvsin;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.adrian.dvsin.Screens.Doku;
import com.example.adrian.dvsin.Screens.LoginScreen;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.view.Gravity.BOTTOM;

public class MainActivity extends AppCompatActivity {

// -- INSTANZVARIABLEN festlegen -- //

    // int

    int saufCount, textViewID, textViewID2;
    int touchedbuchen, touchedcontainern, toucheddoku, touchchedsuchen, touchedaendern;

    // String

    String user, name, userNamePath, userAgePath, age;

    // -- Others -- //

    // TEXTVIEW 

    TextView hauptmenue, buchen_beschreibung, containern_buchen, doku_beschreibung, suchen_bescheibung, aendern_bescheibung;

    TextView benutzername_aktuell, benutzer_status;

    TextView zurueck;


    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;


    // IMAGEBUTTON

    ImageButton menuepunkt_buchen, menuepunkt_containern, menuepunkt_doku;
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
    FirebaseDatabase database2;
    DatabaseReference myRef2;
    FirebaseDatabase database3;
    DatabaseReference myRef3;


    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        Intent intent = getIntent();
        user = intent.getStringExtra("USER_NAME");

        if (user != null) {
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("users");
            myRef.setValue(user);
        }

        databaseQuery();


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


        // BUTTON "menuepunkt_containern" drücken

            startMethodeDoku();


        // BUTTON "button_benuter_informationen" drücken

            showUserDetails();


        // ########## //

        // -- ACTIVITY Ende -- //

        // *** ENDE *** /

    }

    private void databaseQuery() {
        userNamePath = "user/" + user + "/Name";
        database2 = FirebaseDatabase.getInstance();
        myRef2 = database.getReference(userNamePath);

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue(String.class);
                Log.d("TAG", "Value is: " + name);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());

            }
        });

        userAgePath = "user/" + user + "/age";
        database3 = FirebaseDatabase.getInstance();
        myRef3 = database.getReference(userAgePath);

        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                age = dataSnapshot.getValue(String.class);
                Log.d("TAG", "Value is: " + age);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());

            }
        });
    }

    // --- WEITERE Methoden --- //

    private void startMethodeBuchen() {

        //	FUNKTION "Buchen" wird gestartet

        menuepunkt_buchen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BuchenScreen1.class);
                intent.putExtra("USER_NAME", user);
                startActivity(intent);
            }
        });

        // FUNKTION "Buchen" wird erklärt (lange gedrückt halten)

        menuepunkt_buchen.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {

                touchedbuchen = 1;
                touchedcontainern = 0;
                toucheddoku = 0;

                startDescribtionMethodes();
                return true;
            }
        });
    }

    private void startMethodeContainern() {

        //	FUNKTION "Containern" wird gestartet

        menuepunkt_containern.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContainernScreen1.class);
                intent.putExtra("USER_NAME", user);
                startActivity(intent);
            }
        });

        // FUNKTION "Containern" wird erklärt (lange gedrückt halten)

        menuepunkt_containern.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {

                touchedbuchen = 0;
                touchedcontainern = 1;
                toucheddoku = 0;

                startDescribtionMethodes();
                return true;
            }
        });

    }

    private void startMethodeDoku() {

        //	FUNKTION "Containern" wird gestartet

        menuepunkt_doku.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Doku.class);
                intent.putExtra("USER_NAME", user);
                startActivity(intent);
            }
        });

        // FUNKTION "Doku" wird erklärt (lange gedrückt halten)

        menuepunkt_doku.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {

                touchedbuchen = 0;
                touchedcontainern = 0;
                toucheddoku = 1;

                startDescribtionMethodes();
                return true;
            }
        });

    }

    private void setFontsToIDs() {

        // FONTS SETZEN

        hauptmenue.setTypeface(font_roboto_thin);

        buchen_beschreibung.setTypeface(font_roboto_thin);
        containern_buchen.setTypeface(font_roboto_thin);
        doku_beschreibung.setTypeface(font_roboto_thin);
        // suchen_bescheibung.setTypeface(font_roboto_thin);
        // aendern_bescheibung.setTypeface(font_roboto_thin);

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
        // suchen_bescheibung = (TextView) findViewById(R.id.suchen_beschreibung);
        // aendern_bescheibung = (TextView) findViewById(R.id.aendern_beschreibung);

        zurueck =(TextView) findViewById(R.id.zurueck);

        // IMAGEBUTTON

        menuepunkt_buchen = (ImageButton) findViewById(R.id.buchen_button);
        menuepunkt_containern = (ImageButton) findViewById(R.id.containern_button);
        menuepunkt_doku = (ImageButton) findViewById(R.id.doku_button);
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

        animPopUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup);

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

        // Text anpassen

        benutzername_aktuell.setText(name);

        benutzer_status.setText("User " + user + " is " + age + " years old");

        //

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);

        // Hintergrund festlegen

        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        // Position ausgehend con BUTTON "vorwaerts"

        // old

        // popupWindow.showAtLocation(button_benuter_informationen,BOTTOM,0,84);

        // new

        popupWindow.showAtLocation(zurueck,0,0, 50);

        //Int

        saufCount = 0;

    }

    public void textSaufenPlay(View view) {

        // kleines gimmick mit Musik "saufen"

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

    public void onBackPressed() {

        // App wird in den Hintergrund verlegt, keine Rückkehr in den Loginbereich mehr möglich.

        moveTaskToBack(true);
    }

    private void startDescribtionMethodes() {

        // FUNKTION "Buchen" wird länger gedrückt

        if (touchedbuchen == 1) {

            TapTargetView.showFor(this,
                    TapTarget.forView(findViewById(R.id.buchen_touchbereich), "Funktion 1:", "Wähle hier Schiff, Containerart und Menge, die Grundlagen zum verladen\n")

                            // Darstellungseigenschaften werden festgelegt

                            .outerCircleColor(R.color.standard_orange)      // Specify a color for the outer circle
                            .outerCircleAlpha(.95f)                         // Specify the alpha amount for the outer circle

                            .targetCircleColor(R.color.weiß)                // Specify a color for the target circle
                            .titleTextSize(37)
                            .descriptionTextSize(25)

                            .textTypeface(font_roboto_thin)
                            .descriptionTypeface(font_roboto_thin)          // Specify the size (in sp) of the description text
                            .titleTextColor(R.color.weiß)                   // Specify the color of the title text
                            .descriptionTextColor(R.color.weiß)         // Specify the color of the description text

                            .dimColor(R.color.weiss_hintergrund_screen)            // If set, will dim behind the view with 30% opacity of the given color
                            .drawShadow(true)                                      // Whether to draw a drop shadow or not
                            .cancelable(true)                                      // Whether tapping outside the outer circle dismisses the view
                            .tintTarget(true)                                      // Whether to tint the target view's color
                            .transparentTarget(true)                               // Specify whether the target is transparent (displays the content underneath)

                            .targetRadius(65),

                    new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);      // This call is optional
                        }
                    });
        }

        // FUNKTION "Containern" wird länger gedrückt

        if (touchedcontainern == 1) {

            TapTargetView.showFor(this,
                    TapTarget.forView(findViewById(R.id.containern_touchbereich), "Funktion 2:", "Hier werden bestehende Buchungsnummern auf die jeweils passenden Schiffe verladen")

                            // Darstellungseigenschaften werden festgelegt

                            .outerCircleColor(R.color.standard_orange)      // Specify a color for the outer circle
                            .outerCircleAlpha(.95f)                         // Specify the alpha amount for the outer circle

                            .targetCircleColor(R.color.weiß)                // Specify a color for the target circle
                            .titleTextSize(37)
                            .descriptionTextSize(25)

                            .textTypeface(font_roboto_thin)
                            .descriptionTypeface(font_roboto_thin)          // Specify the size (in sp) of the description text
                            .titleTextColor(R.color.weiß)                   // Specify the color of the title text
                            .descriptionTextColor(R.color.weiß)         // Specify the color of the description text

                            .dimColor(R.color.weiss_hintergrund_screen)            // If set, will dim behind the view with 30% opacity of the given color
                            .drawShadow(true)                                      // Whether to draw a drop shadow or not
                            .cancelable(true)                                      // Whether tapping outside the outer circle dismisses the view
                            .tintTarget(true)                                      // Whether to tint the target view's color
                            .transparentTarget(true)                               // Specify whether the target is transparent (displays the content underneath)

                            .targetRadius(70),

                    new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);      // This call is optional
                        }
                    });
        }

        // FUNKTION "Doku" wird länger gedrückt

        if (toucheddoku == 1) {

            TapTargetView.showFor(this,
                    TapTarget.forView(findViewById(R.id.doku_touchbereich), "Funktion 3:", "Zeigt alle in der Datenbank hinterlegten Buchungsnummern an")

                            // Darstellungseigenschaften werden festgelegt

                            .outerCircleColor(R.color.standard_orange)      // Specify a color for the outer circle
                            .outerCircleAlpha(.95f)                         // Specify the alpha amount for the outer circle

                            .targetCircleColor(R.color.weiß)                // Specify a color for the target circle
                            .titleTextSize(37)
                            .descriptionTextSize(25)

                            .textTypeface(font_roboto_thin)
                            .descriptionTypeface(font_roboto_thin)          // Specify the size (in sp) of the description text
                            .titleTextColor(R.color.weiß)                   // Specify the color of the title text
                            .descriptionTextColor(R.color.weiß)         // Specify the color of the description text

                            .dimColor(R.color.weiss_hintergrund_screen)            // If set, will dim behind the view with 30% opacity of the given color
                            .drawShadow(true)                                      // Whether to draw a drop shadow or not
                            .cancelable(true)                                      // Whether tapping outside the outer circle dismisses the view
                            .tintTarget(true)                                      // Whether to tint the target view's color
                            .transparentTarget(true)                               // Specify whether the target is transparent (displays the content underneath)

                            .targetRadius(60),

                    new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);      // This call is optional
                        }
                    });
        }
    }
}
