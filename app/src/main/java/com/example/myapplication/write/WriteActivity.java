package com.example.myapplication.write;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.entities.CrimCase;
import com.example.myapplication.entities.Prisoner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class WriteActivity extends AppCompatActivity {
    private EditText edFio, edID, edAge, edHeight, edWeight, edWelcome, edBye;
    private Spinner spinner;
    private DatabaseReference dbPrisoner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filling_card);
        init();
        FirebaseDatabase pisosData = FirebaseDatabase.getInstance();
        dbPrisoner  = pisosData.getReference("Prisoner");

        Intent i = getIntent();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void init() {
        //get the spinner from the xml.
        spinner = findViewById(R.id.spinner);
        //create a list of items for the spinner.
        //АНУС ANNUS ANNYS сделать заполнение списка из бд
        String[] items = new String[]{"1", "2", "three"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        spinner.setAdapter(adapter);

        edFio = findViewById(R.id.fio_ed);
        edID = findViewById(R.id.id_ed);
        edAge = findViewById(R.id.age_ed);
        edHeight = findViewById(R.id.height_ed);
        edWeight = findViewById(R.id.weight_ed);
        edWelcome = findViewById(R.id.welcome_ed);
        edBye = findViewById(R.id.bye_ed);
    }


    public void onClickWrite(View view) {

            String id = dbPrisoner.getKey();
            String name =  edFio.getText().toString();
            Integer age = Integer.parseInt(edAge.getText().toString());
            Double height = Double.parseDouble(edHeight.getText().toString());
            Double weight = Double.parseDouble(edWeight.getText().toString());

            String[] words = edWelcome.getText().toString().split("\\.");
            Date welcome = new Date(Integer.parseInt(words[2]), Integer.parseInt(words[1]), Integer.parseInt(words[0]));

            String[] words1 = edBye.getText().toString().split("\\.");
            Date bye = new Date(Integer.parseInt(words1[2]), Integer.parseInt(words1[1]), Integer.parseInt(words1[0]));
            //ANNUS ANNYS сделать так чтобы по имени подтягивалось описание
            CrimCase crim_case = new CrimCase(id, spinner.getSelectedItem().toString(), spinner.getSelectedItem().toString());
            Prisoner newPrisoner = new Prisoner(name, age, height, weight, id, welcome, bye, crim_case.name);
            dbPrisoner.push().setValue(newPrisoner);
        }
}
