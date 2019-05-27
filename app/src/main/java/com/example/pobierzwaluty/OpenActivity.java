package com.example.pobierzwaluty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//widok wejściowy
//znajduje się w nim krótka informacja o aplikacji
public class OpenActivity extends AppCompatActivity {
    //przycisk wejścia do aplikacji
    Button btn1;
    //tworzenie widoku
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        btn1= (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        } );
    }
}
