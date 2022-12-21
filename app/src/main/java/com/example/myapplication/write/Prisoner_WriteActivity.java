package com.example.myapplication.write;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.entities.CrimCase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Prisoner_WriteActivity extends AppCompatActivity {

    private DatabaseReference dbPrisoner;
    private String id, name;
    private Integer age;
    private Float height, weight;
    private Date welcome, bye;
    private CrimCase id_criminal_Crim_case;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.???)

        FirebaseDatabase pisosData = FirebaseDatabase.getInstance();
        DatabaseReference dbPrisoner  = pisosData.getReference("Prisoner");

        // field = findViewById(R.id.???)
        // .....
    }

    public void onClickSave(View view)
    {

    }
}
