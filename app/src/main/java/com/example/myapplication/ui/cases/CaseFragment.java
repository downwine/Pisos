package com.example.myapplication.ui.cases;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCasesBinding;
import com.example.myapplication.entities.CrimCase;
import com.example.myapplication.ui.cases.CaseAdapter;
import com.example.myapplication.ui.cases.CaseDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CaseFragment extends Fragment {
    private com.example.myapplication.databinding.FragmentCasesBinding binding;
    private DatabaseReference dbPrisoner;
    private CaseAdapter adapter;
    List<CaseDataModel> cases = new ArrayList<CaseDataModel>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = com.example.myapplication.databinding.FragmentCasesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FirebaseDatabase pisosData = FirebaseDatabase.getInstance();
        dbPrisoner  = pisosData.getReference("Prisoner");

//        Intent myIntent = new Intent(getContext(), Cases_ReadActivity.class);
//        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(myIntent);

        RecyclerView recyclerView = root.findViewById(R.id.caselist);
        // создаем адаптер
        adapter = new CaseAdapter(getContext(), cases);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        return root;
    }

    private void getDataFromDB(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                cases.clear();
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    CrimCase cc = ds.getValue(CrimCase.class);
                    assert cc != null;
                    cases.add(new CaseDataModel(cc.name, cc.description));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbPrisoner.addValueEventListener(vListener);
    }
}
