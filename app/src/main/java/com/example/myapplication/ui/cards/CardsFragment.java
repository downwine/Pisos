package com.example.myapplication.ui.cards;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCardsBinding;
import com.example.myapplication.entities.CrimCase;
import com.example.myapplication.entities.Prisoner;
import com.example.myapplication.write.WriteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardsFragment extends Fragment {

    private FragmentCardsBinding binding;
    private DatabaseReference dbPrisoner;
    private DatabaseReference dbCase;

    FloatingActionButton mFab;

    List<CardDataModel> prisoners = new ArrayList<CardDataModel>();
    Map<String,String> dictionary = new HashMap<String,String>();
    private CardAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCardsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FirebaseDatabase pisosData = FirebaseDatabase.getInstance();
        dbPrisoner  = pisosData.getReference("Prisoner");
        dbCase  = pisosData.getReference("Сrim_Case");
        //setInitialData();

        RecyclerView recyclerView = root.findViewById(R.id.list);
        // создаем адаптер
        adapter = new CardAdapter(getContext(), prisoners);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        getDataFromDBCase();
        getDataFromDBPrisoner();

        mFab = root.findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), WriteActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myIntent);
            }
        });

        return root;
    }

    private void setInitialData(){

//        String id = dbPrisoner.getKey();
//        String name =  "Васильева- Куприянова София Олеговна";
//        Integer age = 20;
//        Double height = 1.78;
//        Double weight = 100.78;
//        Date welcome = new Date(2022, 01, 01);
//        Date bye = new Date(2023, 01, 01);
//        CrimCase crim_case = new CrimCase("украл", "Вор");
//        Prisoner newPrisoner = new Prisoner(name, age, height, weight, id, welcome, bye, crim_case.name);
//        dbPrisoner.push().setValue(newPrisoner);

    }

    private void getDataFromDBCase(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                dictionary.clear();
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    CrimCase crim_case = ds.getValue(CrimCase.class);
                    dictionary.put(ds.getKey(), crim_case.name);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbCase.addValueEventListener(vListener);
    }


    private void getDataFromDBPrisoner(){
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                prisoners.clear();
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    Prisoner prisoner = ds.getValue(Prisoner.class);
                    prisoners.add(new CardDataModel (dictionary.get(prisoner.id_criminal_case), prisoner.name, prisoner.age,
                            prisoner.height, prisoner.weight, prisoner.welcome, prisoner.bye));
                }
                if (prisoners.size() == 0) {
                    Intent myIntent = new Intent(getContext(), WriteActivity.class);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(myIntent);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbPrisoner.addValueEventListener(vListener);
    }
//    private View.OnClickListener clickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.prisoner:
//                    LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    View customView = inflater.inflate(R.layout.popup_layout1, null);
//                    myPopUp = new PopupWindow(
//                            customView,
//                            LinearLayout.LayoutParams.WRAP_CONTENT,
//                            LinearLayout.LayoutParams.WRAP_CONTENT
//                    );
//                    Activity context = this;
//                    RelativeLayout linearLayout = (RelativeLayout) context.findViewById(R.id.popup_layout1);
//                     LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                     View customView = inflater.inflate(R.layout.popup_layout1, null);
//                     customView.requestFocus();
//                     customView.setVisibility(View.VISIBLE);
//                    ImageButton closePopUp = (ImageButton) customView.findViewById(R.id.imageButton);
//                    closePopUp.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            myPopUp.dismiss();
//                        }
//                    });
//                    break;
//                case R.id.img_Button_right :
//
//                    break;
//                case R.id.resetbtn :
//
//                    break;
//            }
//        }
//    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
