package com.example.myapplication.ui.poker;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentPokerBinding;
import com.example.myapplication.databinding.FragmentSlideshowBinding;
import com.example.myapplication.ui.slideshow.SlideshowViewModel;

public class PokerFragment extends Fragment {

    private FragmentPokerBinding binding;
    private ImageButton img_button_one;
    private ImageButton img_button_two;
    private TextView total;
    private ImageView step;

    private int counter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PokerViewModel pokerViewModel =
                new ViewModelProvider(this).get(PokerViewModel.class);

        binding = FragmentPokerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPoker;
        pokerViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        img_button_one = (ImageButton) root.findViewById(R.id.img_Button_left);
        
        //initTotal();

        return root;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            play();
            switch (v.getId()){
                case R.id.img_Button_One :
                    //minusCounter();
                    break;
                case R.id.img_Button_Two :
                    //plusCounter();
                    break;
            }
        }
    };

    private void initTotal(){
        counter = 0;
        total.setText(counter + "");
    }

    private void play()
    {

        if(true)
        {
            counter++;
        }
        else {
            counter--;
        }

        total.setText(counter + "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}