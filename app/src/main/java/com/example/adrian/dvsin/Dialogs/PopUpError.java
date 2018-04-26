package com.example.adrian.dvsin.Dialogs;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.example.adrian.dvsin.R;

public class PopUpError extends AppCompatActivity {

    TextView fehlertext_ueberschift, fehlertext_erklaerung_v1_teil_1, fehlertext_erklaerung_v1_teil_2;
    Typeface font_roboto_thin, font_roboto_medium;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popup_error_02);

        // Schiriftart setzen

        font_roboto_thin = Typeface.createFromAsset(getAssets(), "fonts/roboto-thin.ttf");
        font_roboto_medium = Typeface.createFromAsset(getAssets(), "fonts/roboto-medium.ttf");


        fehlertext_ueberschift = (TextView) findViewById(R.id.fehlertext_ueberschift_v1);
        fehlertext_erklaerung_v1_teil_1 = (TextView) findViewById(R.id.fehlertext_erklaerung_v1_teil_1);
        // fehlertext_erklaerung_v1_teil_2 = (TextView) findViewById(R.id.fehlertext_erklaerung_v1_teil_2);

        fehlertext_erklaerung_v1_teil_1.setTypeface(font_roboto_medium);
        fehlertext_erklaerung_v1_teil_1.setTypeface(font_roboto_thin);
        // fehlertext_erklaerung_v1_teil_2.setTypeface(font_roboto_thin);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigt = dm.heightPixels;

        getWindow().setLayout((int) (width*.0),(int)(heigt*.4));
        // getWindow().setGravity(0);

    }
}
