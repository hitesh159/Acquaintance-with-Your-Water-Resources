package com.example.sarthak.ascs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class farmer extends AppCompatActivity {
        Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        b1=(Button)findViewById(R.id.farmer);
        b2=(Button)findViewById(R.id.tech);
    }

    public void rain(View view)
    {
        Intent i2=new Intent(farmer.this,cropPredictor.class);
        startActivity(i2);
    }

    public void toTech(View view)
    {
        Intent i=new Intent(farmer.this,MainActivity.class);
        startActivity(i);
    }
}