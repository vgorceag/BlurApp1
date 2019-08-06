package com.example.blurapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.view.Menu;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button  buton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buton =  findViewById(R.id.buttonstart);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this,  Main2Activity.class);
                startActivity(myintent);


            }
        });





    }
}
