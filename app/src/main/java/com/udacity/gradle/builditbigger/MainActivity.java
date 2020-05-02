package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.IdlingResource;

import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.milsat.myandroidlibrary2.DisplayJoke;
import com.udacity.gradle.builditbigger.backend.MyEndpoint;


public class MainActivity extends AppCompatActivity {

    public String results;
    TextView jjk;
    @Nullable
    private SimpleIdlingResource idlingResource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jjk = findViewById(R.id.jjk);
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

    public void tellJoke(View view) {

       runEndPoint();
    }

public void runEndPoint(){
    new EndpointsAsyncTask(MainActivity.this,idlingResource).execute();

}
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }
        return idlingResource;
    }
    public class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
        //private static MyApi myApiService = null;
        private  MyEndpoint myEndPoint = new MyEndpoint();
        private Context context;
        SimpleIdlingResource simpleIdlingResource;


        public EndpointsAsyncTask(Context context,SimpleIdlingResource simpleIdlingResource) {
            this.context = context;
            this.simpleIdlingResource = simpleIdlingResource;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (simpleIdlingResource != null) {
                simpleIdlingResource.setIdleState(false);
            }
        }

        @Override
        protected String doInBackground(String... params) {

            return myEndPoint.myJoke().getData();
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(context, result, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(context, DisplayJoke.class);
            intent.putExtra(DisplayJoke.JOKE,result);

            if (simpleIdlingResource != null) {

                simpleIdlingResource.setIdleState(true);

            }
            startActivity(intent);

        }
    }

}
