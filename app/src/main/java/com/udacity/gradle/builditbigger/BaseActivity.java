package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.shane.joketeller.JokeActivity;


public abstract class BaseActivity extends AppCompatActivity implements JokeCallback {
    public static final String TAG = BaseActivity.class.getSimpleName();
    JokeAsyncTask jokeAsyncTask;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void fetchJoke() {
        if (jokeAsyncTask == null || jokeAsyncTask.getStatus() == AsyncTask.Status.FINISHED) {
            jokeAsyncTask = new JokeAsyncTask(this);
            jokeAsyncTask.execute();
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void tellJoke(View view) {
        fetchJoke();
    }

    @Override
    public void onJokeLoaded(@NonNull String joke) {
        progressBar.setVisibility(View.GONE);

        Intent intent = new Intent(BaseActivity.this, JokeActivity.class);
        intent.putExtra(JokeActivity.EXTRA_JOKE, joke);
        startActivity(intent);
    }
}
