package com.shane.joketeller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "extra_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        TextView jokeTextView = findViewById(R.id.joke_text_view);
        Intent startingIntent = getIntent();

        String joke;

        if (startingIntent.hasExtra(EXTRA_JOKE)) {
            joke = startingIntent.getStringExtra(EXTRA_JOKE);
        } else {
            joke = getString(R.string.no_joke_found);
        }

        jokeTextView.setText(joke);
    }
}
