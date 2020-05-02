package com.milsat.myandroidlibrary2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayJoke extends AppCompatActivity {

    public static String JOKE = "JOKE";

    TextView jokes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
        jokes = findViewById(R.id.text_jokes);
        Intent intent = getIntent();

        if (intent.hasExtra(JOKE)){
            jokes.setText(intent.getStringExtra(JOKE));
        }
    }
}
