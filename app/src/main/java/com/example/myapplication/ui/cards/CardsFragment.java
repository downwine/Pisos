package com.example.myapplication.ui.cards;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CardAdapter;
import com.example.myapplication.CardDataModel;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentCardsBinding;

import java.util.ArrayList;

public class CardsFragment extends Fragment {

    private FragmentCardsBinding binding;
    private CardView prisoner1;
    private Button prisoner2;
    private Button prisoner3;
    private Button prisoner4;
    private Button prisoner5;
    private PopupWindow myPopUp;
    private CardView butt;
    private LinearLayout positionOfPopUp;
    ArrayList<CardDataModel> cards = new ArrayList<CardDataModel>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCardsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        butt = (CardView) root.findViewById(R.id.prisoner);
//        butt.setOnClickListener(clickListener);
        setInitialData();
        RecyclerView recyclerView = root.findViewById(R.id.list);
        // создаем адаптер
        CardAdapter adapter = new CardAdapter(getContext(), cards);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        return root;
    }

    private void setInitialData(){

        cards.add(new CardDataModel ("Ильинский Егор Максимович", "26 лет",
                "163 см", "65 кг", R.drawable.andrew));
        cards.add(new CardDataModel ("Сотников Демьян Тимофеевич", "19 лет",
                "175 см", "57 кг",R.drawable.anton));
        cards.add(new CardDataModel ("Самойлов Сергей Ильич", "30 лет",
                "188 см", "92 кг", R.drawable.sasha));
        cards.add(new CardDataModel ("Серов Давид Матвеевич", "45 лет",
                "192 см", "88 кг", R.drawable.ivan));
        cards.add(new CardDataModel ("Серов Марк Михайлович", "37 лет",
                "181 см", "90 кг", R.drawable.kolya));
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
