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

    TextView slogan_teil_1, slogan_teil_2, slogan_teil_3;
    EditText eingabe_login;

    Typeface font_roboto_thin, font_roboto_medium;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        setVariables();

        loginButton();
    }

    public void loginButton() {
        //BUTTONS

        // BUTTON vorwärts zur Activity activity_homescreen

        final ImageButton vorwaerts = findViewById(R.id.vorwaerts);

        //  Test PASST

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
                                        // the user is done typing.

                                        eingabe_login.length();
                                        if(eingabe_login.length()!= 0){

                                        }

                                        else {
                                            YoYo.with(Techniques.Tada)
                                                    .duration(1000)
                                                    .playOn(eingabe_login);
                                        }

                                    }
                                }
                                return false; // pass on to other listeners.
                            }
                        }
                );

            }
        });

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

    private void setVariables() {
        slogan_teil_1 =findViewById(R.id.slogan_teil_1);
        slogan_teil_2 = findViewById(R.id.slogan_teil_2);
        slogan_teil_3 = findViewById(R.id.slogan_teil_3);

        eingabe_login = findViewById(R.id.eingabefeld_login);

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(),"fonts/roboto-medium.ttf");

        slogan_teil_1.setTypeface(font_roboto_thin);
        slogan_teil_2.setTypeface(font_roboto_medium);
        slogan_teil_3.setTypeface(font_roboto_thin);

        eingabe_login.setTypeface(font_roboto_thin);

    }
}