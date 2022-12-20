package com.example.myapplication.read;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Prisoner_ShowActivity extends AppCompatActivity {
    // private TextView Name ???
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.????)
        init();
        getIntentMain();
    }

    private void init(){
        // Name = findViewById(R.id.???);
    }
    private void getIntentMain()
    {
        Intent i = getIntent();
        //Name.setText(i.get......)
    }
}
