package com.example.adrian.dvsin.Screens;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.adrian.dvsin.MainActivity;
import com.example.adrian.dvsin.R;

public class LoginScreen extends AppCompatActivity {

    // -- INSTANZVARIABLEN festlegen -- //

    // int


    // String


    // -- Others -- //

    // TEXTVIEW

    TextView slogan_teil_1, slogan_teil_2, slogan_teil_3;


    // EDITTEXT

    EditText eingabe_login;


    // FONTS

    Typeface font_roboto_thin, font_roboto_medium;


    // IMAGEBUTTON

    ImageButton vorwaerts;


    // ########## //


    // *** HAUPTMETHODE *** //

    // -- ACTIVITY starten -- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // IDs zuordnen

        setIDs();


        // FONTS einbeziehen

        setFonts();


        // FONTS setzen

        setFontsToIDs();


        // -- BUTTONS  -- //

        // BUTTON "vorwaerts" drücken

        loginButton();


        // -- ACTIVITY Ende -- //

        // ########## //

        // *** ENDE *** //
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

                if(eingabe_login.length()!= 0){

                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
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

    public void setFontsToIDs() {

        // FONTS TextView SETZEN

        slogan_teil_1.setTypeface(font_roboto_thin);
        slogan_teil_2.setTypeface(font_roboto_medium);
        slogan_teil_3.setTypeface(font_roboto_thin);

        // FONTS EditText SETZEN

        eingabe_login.setTypeface(font_roboto_thin);

    }

}