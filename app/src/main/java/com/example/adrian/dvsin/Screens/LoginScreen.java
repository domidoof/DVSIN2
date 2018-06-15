package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.adrian.dvsin.MainActivity;
import com.example.adrian.dvsin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {

    // -- INSTANZVARIABLEN festlegen -- //

    // int

    // boolean

    boolean userState = false;

    // String


    // -- Others -- //

    // TEXTVIEW

    TextView slogan_teil_1, slogan_teil_2, slogan_teil_3;

    // TEXTVIEW Containereingabe

    TextView fehlertext_ueberschift, fehlertext_erklaerung_v1_teil_1, fehlertext_erklaerung_v1_teil_2;


    // EDITTEXT

    EditText eingabe_login;

    // BUTTONS

    Button zurueck;


    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;


    // IMAGEBUTTON

    ImageButton vorwaerts;

    // POPUPVIEW

    View popupView;

    // POPUPWINDOW

    PopupWindow popupWindow;

    // LAYOUTINFLATOR

    LayoutInflater layoutInflater;


    // ANIMATION

    Animation animPopUp;

    // Database Resources

    FirebaseDatabase database;


    // Reference to the database

    DatabaseReference ref;

    //Array List to save the data from the database

    ArrayList<String> userList = new ArrayList<>();


    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // Database abfrage

        getDatabaseUser();

        // IDs zuordnen

        setIDs();


        // FONTS einbeziehen

        setFonts();

        // Initialisieren

        init();


        // FONTS setzen

        setFontsToIDs();


        // -- BUTTONS  -- //

        // BUTTON "vorwaerts" drücken

        loginButton();


        // -- ACTIVITY Ende -- //

        // ########## //

        // *** ENDE *** //
    }

    private void setPopUpDetailsForAll(){

        // KOMMENTIEREN, wenn verstanden ;)

        // Eigenschaften POPUPWINDOW "popupWindow" festlegen

        // ??? GEHT DAS ??? //

        popupView.startAnimation(animPopUp);

        // ??? GEHT DAS ??? //

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);

        // Hintergrund festlegen

        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        // Position ausgehend con BUTTON "vorwaerts"

        popupWindow.showAtLocation(zurueck,0,0,50);



    }

    private void setTextViewDialogError(){

        // TEXTVIEW PopUpWindow setzen

        fehlertext_ueberschift.setText(R.string.fehlertext_ueberschift_v3);
        fehlertext_erklaerung_v1_teil_1.setText(R.string.fehlertext_erklaerung_v3_teil_1);
    }

    private void init() {
        // LAYOUTINFLATER initialisieren

        layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);


        // VIEW initialisieren

        popupView = layoutInflater.inflate(R.layout.activity_popup_error_03, null);

        // POPUPWINDOW initialisieren

        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        // TEXTVIEW PopUpWindow

        fehlertext_ueberschift = (TextView) popupView.findViewById(R.id.fehlertext_ueberschift_v1);
        fehlertext_erklaerung_v1_teil_1 = (TextView) popupView.findViewById(R.id.fehlertext_erklaerung_v1_1_teil_1);
    }

    // --- WEITERE Methoden --- //

    private void setFonts() {

        // FONTS einbeziehen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(),"fonts/roboto-medium.ttf");

    }

    private void setIDs() {

        // TEXTVIEW

        slogan_teil_1 =findViewById(R.id.slogan_teil_1);
        slogan_teil_2 = findViewById(R.id.slogan_teil_2);
        slogan_teil_3 = findViewById(R.id.slogan_teil_3);


        // EDITTEXT

        eingabe_login = findViewById(R.id.eingabefeld_login);


        // IMAGEBUTTON

        vorwaerts = findViewById(R.id.vorwaerts);

        // ANIMATION

        animPopUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slideup);

        // BUTTONS

        zurueck = (Button) findViewById(R.id.zurueck);

    }

    public void loginButton() {

        eingabe_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ((EditText)findViewById(R.id.eingabefeld_login)).setOnEditorActionListener(

                        new EditText.OnEditorActionListener() {

                            @Override
                            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                        actionId == EditorInfo.IME_ACTION_DONE ||
                                        event != null &&
                                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                                    if (event == null || !event.isShiftPressed()) {

                                        // Benutzer mit Eingabe fertig

                                        eingabe_login.length();
                                        if(eingabe_login.length()!= 0){

                                        }

                                        // EFFEKT wird gestartet, der auf leeres Eingabefeld hinweist!

                                        else {
                                            YoYo.with(Techniques.Tada)
                                                    .duration(1000)
                                                    .playOn(eingabe_login);
                                        }

                                    }
                                }

                                return false; // Weitergabe an andere listener
                            }
                        }
                );

            }
        });

        // !!! Kommenteiren wenn verstanden !!! //

        vorwaerts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                eingabe_login.length();

                for (String usr : userList) {
                    if (eingabe_login.getText().toString().equals(usr)) {
                        userState = true;
                        break;
                    }
                    else {
                        userState = false;
                    }
                }

                if (!userState) {
                    showDialogError();
                }

                if(eingabe_login.length()!= 0 & userState){

                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    intent.putExtra("USER_NAME", eingabe_login.getText().toString());
                    startActivity(intent);
                }

                else {

                    YoYo.with(Techniques.Tada)
                            .duration(1000)
                            .playOn(eingabe_login);
                }
            }
        });
    }

    private void showDialogError(){

        setPopUpDetailsForAll();

        // TEXTVIEW updaten

        setTextViewDialogError();
    }

    private void getDatabaseUser() {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("user");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snp : dataSnapshot.getChildren()) {
                    userList.add(String.valueOf(snp.getKey()));
                    //Log.d("TAG", "Value is: " + snp);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value.", databaseError.toException());

            }
        });

    }

    public void setFontsToIDs() {

        // FONTS TextView SETZEN

        slogan_teil_1.setTypeface(font_roboto_thin);
        slogan_teil_2.setTypeface(font_roboto_medium);
        slogan_teil_3.setTypeface(font_roboto_thin);
        zurueck.setTypeface(font_roboto_thin);

        // FONTS EditText SETZEN

        eingabe_login.setTypeface(font_roboto_thin);

        // FONTS PopUpWindow SETZEN

        fehlertext_ueberschift.setTypeface(font_roboto_medium);
        fehlertext_erklaerung_v1_teil_1.setTypeface(font_roboto_thin);
        // fehlertext_erklaerung_v1_teil_2.setTypeface(font_roboto_thin);

    }

}