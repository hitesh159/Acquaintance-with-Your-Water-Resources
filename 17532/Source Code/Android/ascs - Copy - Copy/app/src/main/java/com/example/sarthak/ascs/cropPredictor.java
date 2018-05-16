package com.example.sarthak.ascs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class cropPredictor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_predictor);
        Spinner dropdown = findViewById(R.id.crops);
        String[] items = new String[]{"Alfalfa","Banana","Barley","Bean","Cabbage","Citrus","Cotton","Maize","Melon","Onion","Peanut","Pea","Pepper","Potato","Rice","Sorghum","Soyabean","Sugarbeet","Sugarcane","Sunflower","Tomato"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        Spinner state =findViewById(R.id.states);
        String[] states = new String[]{"GUJARAT",
                "KERALA",
                "SAURASHTRA AND KUTCH",
                "JHARKHAND",
                "ORISSA",
                "GANGETIC WEST BENGAL",
                "SUB HIMALAYAN WEST BENGAL AND SIKKIM",
                "BIHAR",
                "EAST UTTAR PRADESH",
                "LAKSHADWEEP",
                "NAGA MANI MIZO TRIPURA",
                "COASTAL ANDHRA PRADESH",
                "EAST MADHYA PRADESH",
                "HARYANA DELHI AND CHANDIGARH",
                "VIDARBHA",
                "COASTAL KARNATAKA",
                "WEST RAJASTHAN",
                "MADHYA MAHARASHTRA",
                "WEST MADHYA PRADESH",
                "NORTH INTERIOR KARNATAKA",
                "SOUTH INTERIOR KARNATAKA",
                "HIMACHAL PRADESH",
                "KONKAN AND GOA",
                "UTTARAKHAND",
                "ANDAMAN AND NICOBAR ISLANDS",
                "EAST RAJASTHAN",
                "TAMIL NADU",
                "TELANGANA",
                "CHHATTISGARH",
                "MATATHWADA",
                "PUNJAB",
                "ARUNACHAL PRADESH",
                "JAMMU AND KASHMIR",
                "WEST UTTAR PRADESH"};
        ArrayAdapter<String> sadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, states);
        state.setAdapter(sadapter);
    }
}
