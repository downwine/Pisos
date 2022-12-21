package com.example.myapplication.ui.cards;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CardAdapter;
import com.example.myapplication.CardDataModel;
import com.example.myapplication.LoginActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCardsBinding;
import com.example.myapplication.entities.Prisoner;
import com.example.myapplication.read.Prisoner_ReadActivity;
import com.example.myapplication.write.WriteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardsFragment extends Fragment {

    private FragmentCardsBinding binding;
    private CardView prisoner1;
    private Button prisoner2;
    private Button prisoner3;
    private Button prisoner4;
    private Button prisoner5;
    private PopupWindow myPopUp;
    //private List<Prisoner> prisoners;
    private DatabaseReference dbPrisoner;
    private CardView butt;
    FloatingActionButton mFab;
    private LinearLayout positionOfPopUp;

    List<CardDataModel> prisoners = new ArrayList<CardDataModel>();
    private CardAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCardsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FirebaseDatabase pisosData = FirebaseDatabase.getInstance();
        dbPrisoner  = pisosData.getReference("Prisoner");
        setInitialData();

        RecyclerView recyclerView = root.findViewById(R.id.list);
        adapter = new CardAdapter(getContext(), prisoners);
        recyclerView.setAdapter(adapter);
        getDataFromDB();

        mFab = root.findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //Intent myIntent = new Intent(getContext(), WriteActivity.class);
                //getContext().startActivity(myIntent);
            }
        });
        // создаем адаптер
        //CardAdapter adapter = new CardAdapter(getContext(), cards);

        //root.findViewById(R.id.prisoner).setAdapter(adapter);
        // устанавливаем для списка адаптер

        return root;
    }

    private void setInitialData(){

//        cards.add(new CardDataModel ("Ильинский Егор Максимович", "26 лет",
//                "163 см", "65 кг", R.drawable.andrew));
//        cards.add(new CardDataModel ("Сотников Демьян Тимофеевич", "19 лет",
//                "175 см", "57 кг",R.drawable.anton));
//        cards.add(new CardDataModel ("Самойлов Сергей Ильич", "30 лет",
//                "188 см", "92 кг", R.drawable.sasha));
//        cards.add(new CardDataModel ("Серов Давид Матвеевич", "45 лет",
//                "192 см", "88 кг", R.drawable.ivan));
//        cards.add(new CardDataModel ("Серов Марк Михайлович", "37 лет",
//                "181 см", "90 кг", R.drawable.kolya));

        String id = dbPrisoner.getKey();
        String name =  "Васильева- Куприянова София Олеговна";
        Integer age = 20;
        Double height = 1.78;
        Double weight = 100.78;
        Date welcome = new Date(2022, 01, 01);
        Date bye = new Date(2023, 01, 01);
        Prisoner newPrisoner = new Prisoner(name, age, height, weight, id, welcome, bye);
        dbPrisoner.push().setValue(newPrisoner);
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
                    prisoners.add(new CardDataModel (prisoner.id, prisoner.name, prisoner.age,
                            prisoner.height, prisoner.weight, prisoner.welcome, prisoner.bye));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dbPrisoner.addValueEventListener(vListener);
        //System.out.println(Array.getLength(prisoners));
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
