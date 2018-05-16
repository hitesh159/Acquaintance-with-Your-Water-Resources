package com.example.sarthak.ascs;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements signIn.OnFragmentInteractionListener,signUp.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tab=(TabLayout)findViewById(R.id.tabLayout);
        ViewPager page=(ViewPager)findViewById(R.id.viewPager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        page.setAdapter(adapter);
        tab.setupWithViewPager(page);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void nav(View view) {
        Intent i1=new Intent(MainActivity.this,navigation_drawer.class);
        startActivity(i1);
    }
}
