package com.example.myapplication.read;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Constant;
import com.example.myapplication.R;
import com.example.myapplication.entities.CrimCase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Cases_ReadActivity extends AppCompatActivity {
        private ListView listView;
        private ArrayAdapter<String> adapter;
        private List<String> listData;
        private List<CrimCase> listTemp;

        private DatabaseReference dbCase;
        private String USER_KEY = "Сrim_Case";
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            init();
            getDataFromDB();
            setOnClickItem();
        }
        private void init()
        {
            listView = findViewById(R.id.caselist);
            listData = new ArrayList<>();
            listTemp = new ArrayList<>();
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, listData);
            listView.setAdapter(adapter);
            dbCase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        }
        private void getDataFromDB()
        {
            ValueEventListener vListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    for(DataSnapshot ds : dataSnapshot.getChildren())
                    {
                        CrimCase cc = ds.getValue(CrimCase.class);
                        listData.add(cc.name);
                        listTemp.add(cc);
                    }
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            dbCase.addValueEventListener(vListener);
        }
        private void setOnClickItem()
        {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CrimCase cc = listTemp.get(position);
                    Intent i = new Intent(Cases_ReadActivity.this, Cases_ShowActivity.class);
                    i.putExtra(Constant.CASE_NAME,cc.name);
                    i.putExtra(Constant.CASE_DESCRIPTION,cc.description);
                    startActivity(i);

                }
            });
        }
}
