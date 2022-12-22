package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CardAdapter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCardsBinding;
import com.example.myapplication.read.Cases_ReadActivity;
import com.example.myapplication.write.WriteActivity;
import com.google.firebase.database.FirebaseDatabase;

public class ListCases extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState, View view) {

        Intent myIntent = new Intent(view.getContext(), Cases_ReadActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myIntent);
        return view;
    }
}
