package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {
    private List<Prisoner> prisoners;
    private DatabaseReference pisosData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prisoners = new ArrayList<>();

        FirebaseDatabase pisosData = FirebaseDatabase.getInstance();
        DatabaseReference dbPrisoner  = pisosData.getReference("Prisoner");
    }

    private void getDataFromDB(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                prisoners.clear();
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    Prisoner prisoner = ds.getValue(Prisoner.class);
                    assert prisoner != null;
                    prisoners.add(prisoner);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        pisosData.addValueEventListener(vListener);
    }
}
