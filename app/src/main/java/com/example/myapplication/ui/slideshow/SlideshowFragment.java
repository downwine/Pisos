package com.example.myapplication.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSlideshowBinding;
import com.example.myapplication.entities.Prisoner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private DatabaseReference dbPrisoner;

    List<SlideDataModel> prisoners = new ArrayList<SlideDataModel>();
    private SlideshowAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FirebaseDatabase pisosData = FirebaseDatabase.getInstance();
        dbPrisoner  = pisosData.getReference("Prisoner");

        RecyclerView recyclerView = root.findViewById(R.id.checklist);
        // создаем адаптер
        adapter = new SlideshowAdapter(getContext(), prisoners);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        getDataFromDB();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
                    prisoners.add(new SlideDataModel(prisoner.name));
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