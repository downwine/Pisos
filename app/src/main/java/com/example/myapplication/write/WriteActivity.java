package com.example.myapplication.write;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.entities.CrimCase;
import com.example.myapplication.entities.Prisoner;
import com.example.myapplication.ui.cards.CardsFragment;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class WriteActivity extends AppCompatActivity {
    private EditText edFio, edID, edAge, edHeight, edWeight, edWelcome, edBye;
    private Spinner spinner;
    private DatabaseReference dbPrisoner, dbCases;
    private List<String> cases;
    private List<String> cases_id;
    private ArrayAdapter<String> adapter;
    String[] crim_cases;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filling_card);
        dbPrisoner  = FirebaseDatabase.getInstance().getReference("Prisoner");
        dbCases  = FirebaseDatabase.getInstance().getReference("Сrim_Case");
        init();
        getDataFromDB();
        System.out.println(cases);
        System.out.println(Arrays.toString(crim_cases));
        Intent i = getIntent();
    }

    private void init() {
        spinner = findViewById(R.id.spinner);
        cases = new ArrayList<>();
        cases_id = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cases);
        //set the spinners adapter to the previously created one.
        spinner.setAdapter(adapter);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.

        edFio = findViewById(R.id.fio_ed);
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
            Date welcome = new Date(Integer.parseInt(words[2]), Integer.parseInt(words[1])-1, Integer.parseInt(words[0]));

            String[] words1 = edBye.getText().toString().split("\\.");
            Date bye = new Date(Integer.parseInt(words1[2]), Integer.parseInt(words1[1])-1, Integer.parseInt(words1[0]));

            String сrim_case = spinner.getSelectedItem().toString();
            Integer n_id = cases.indexOf(сrim_case);
            String case_key = cases_id.get(n_id);
            Prisoner newPrisoner = new Prisoner(name, age, height, weight, id, welcome, bye, case_key);
            dbPrisoner.push().setValue(newPrisoner);

            edFio.getText().clear();
            edAge.getText().clear();
            edHeight.getText().clear();
            edWeight.getText().clear();
            edWelcome.getText().clear();
            edBye.getText().clear();
            adapter.clear(); // clear items
            adapter.notifyDataSetChanged(); // update spinner view

            Toast.makeText(getApplicationContext(), "Данные записаны", Toast.LENGTH_SHORT).show();
        }

    private void getDataFromDB() {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cases.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    CrimCase crim_case = ds.getValue(CrimCase.class);
                    assert crim_case != null;
                    cases.add(crim_case.name);
                    cases_id.add(ds.getKey());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbCases.addValueEventListener(vListener);
    }
}


