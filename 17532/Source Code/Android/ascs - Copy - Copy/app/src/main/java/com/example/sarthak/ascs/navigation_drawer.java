package com.example.sarthak.ascs;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class navigation_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,predictor.OnFragmentInteractionListener,billPayment.OnFragmentInteractionListener,account_details.OnFragmentInteractionListener,groundWater.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.logout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment f = null;
        TextView t=(TextView)findViewById(R.id.welcome);
        t.setVisibility(View.INVISIBLE);
        if (id == R.id.predictor) {
            f = new predictor();
        } else if (id == R.id.map) {
            startActivity(new Intent(navigation_drawer.this,watermaps.class));
        } else if (id == R.id.ground) {
            f=new groundWater();
        } else if (id == R.id.bill) {
            f = new billPayment();
        } else if (id == R.id.account) {
            f = new account_details();
        } else if (id == R.id.chat_bot) {
            startActivity(new Intent(this, chatBot.class));
        }

        if (f != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content, f);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public void getPrediction(View view) {
        Spinner s1 = (Spinner) findViewById(R.id.states);
        Spinner s2 = (Spinner) findViewById(R.id.months);
        TextView t1=(TextView)findViewById(R.id.result);
        String state = s1.getSelectedItem().toString();
        String month = s2.getSelectedItem().toString();
        URL url = null;
        try {
            url = new URL("http://hitesh159.pythonanywhere.com/rainfall_predict/?name=" + state + "&month=" + month);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new ranswer().execute(url);
    }

    public void groundlevel(View view)
    {
        Spinner spinner=(Spinner)findViewById(R.id.state);
        String s=spinner.getSelectedItem().toString();
        EditText e1=(EditText)findViewById(R.id.help);
        String s1=e1.getText().toString();
        URL url=null;
        try {
            url = new URL("http://hitesh159.pythonanywhere.com/groundwater/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new ganswer().execute(url);
    }
    public class ganswer extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String Results = null;
            try {
                Results = getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Results;
        }

       // @Override
        //protected void onPreExecute() {
          //  super.onPreExecute();
            //ProgressBar indicator=(ProgressBar)findViewById(R.id.progress);
            //indicator.setVisibility(View.VISIBLE);
            //Button b1=(Button)findViewById(R.id.predict);
//            b1.setVisibility(View.INVISIBLE);

       // }
        @Override
        protected void onPostExecute(String JSOENResults) {
            if (JSOENResults != null && !JSOENResults.equals("")) {
              //  TextView t1=(TextView)findViewById(R.id.levels);
                Log.i("sarthak",JSOENResults);
                Bundle b=new Bundle();
                b.putString("values",JSOENResults);
                Intent i =new Intent(getApplicationContext(),groundWaterLocations.class);
                i.putExtras(b);
                startActivity(i);
            }
        }
    }

    public class ranswer extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String Results = null;
            try {
                Results = getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Results;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressBar indicator=(ProgressBar)findViewById(R.id.progress);
            indicator.setVisibility(View.VISIBLE);
            Button b1=(Button)findViewById(R.id.predict);
            b1.setVisibility(View.INVISIBLE);

        }
        @Override
        protected void onPostExecute(String JSOENResults) {
            if (JSOENResults != null && !JSOENResults.equals("")) {
                TextView t1=(TextView)findViewById(R.id.result);
                JSONObject obj=null;
                try {
                     obj=new JSONObject(JSOENResults);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    t1.setText(obj.getString("ans")+" mm");
                    Button b1=(Button)findViewById(R.id.predict);
                    b1.setVisibility(View.VISIBLE);
                    ProgressBar indicator=(ProgressBar)findViewById(R.id.progress);
                    indicator.setVisibility(View.INVISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}