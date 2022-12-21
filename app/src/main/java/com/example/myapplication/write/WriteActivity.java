package com.example.myapplication.write;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
    private EditText edLogin, edPassword;
    private FirebaseAuth mAuth;
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
//        FirebaseUser cUser = mAuth.getCurrentUser();
//        if (cUser != null) {
//            Toast.makeText(this, "User not null", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "User null", Toast.LENGTH_SHORT).show();
//        }
    }

    private void init() {
        edLogin = findViewById(R.id.edLogin);
        edPassword = findViewById(R.id.edPassword);
        mAuth = FirebaseAuth.getInstance();
    }


    public void onClickWrite(View view) {

            String id = dbPrisoner.getKey();
            String name =  "Васильева- Куприянова София Олеговна";
            Integer age = 20;
            Double height = 1.78;
            Double weight = 100.78;
            Date welcome = new Date(2022, 01, 01);
            Date bye = new Date(2023, 01, 01);
            CrimCase crim_case = new CrimCase(id, "украл", "Вор");
            Prisoner newPrisoner = new Prisoner(name, age, height, weight, id, welcome, bye, crim_case.name);
            dbPrisoner.push().setValue(newPrisoner);
        }
}
