package com.example.leila.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
/**
 * Created by lafer on 15-11-16.
 */


public class Authentification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        Button b = (Button) findViewById(R.id.btnSinscrire);
        b.setOnClickListener(v -> {
            Intent t = new Intent(this,CreaMembre.class);
            startActivity(t);
        });

    }
}
