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

import java.util.Random;

public class PokerFragment extends Fragment {

    private FragmentPokerBinding binding;
    private ImageButton img_button_one;
    private ImageButton img_button_two;
    private TextView total;
    private ImageView step;
    private Integer totalScore = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PokerViewModel pokerViewModel =
                new ViewModelProvider(this).get(PokerViewModel.class);

        binding = FragmentPokerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPoker;
        pokerViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        img_button_one = (ImageButton) root.findViewById(R.id.img_Button_left);
        binding.imgButtonOne.setOnClickListener(v -> throughOneFinger());
        binding.imgButtonTwo.setOnClickListener(v -> throughTwoFinger());
        step = (ImageView) root.findViewById(R.id.Step);

        return root;
    }

    public void throughOneFinger() {
        Random r = new Random();
        step.setVisibility(View.INVISIBLE);
        View root = binding.getRoot();
        Integer random = r.nextInt();
        if (random % 2 == 0) {
            step.setImageDrawable(getResources().getDrawable(R.drawable.two_fingers));
            step.setVisibility(View.VISIBLE);
            totalScore += 10;
        } else {
            step.setImageDrawable(getResources().getDrawable(R.drawable.one_finger));
            step.setVisibility(View.VISIBLE);
            totalScore -= 10;
        }
        TextView view1 = (TextView) root.findViewById(R.id.total);
        view1.setText(String.valueOf(totalScore));
    }

    public void throughTwoFinger() {
        Random r = new Random();
        Integer random = r.nextInt();
        step.setVisibility(View.INVISIBLE);
        View root = binding.getRoot();
        if (random % 2 == 0) {
            step.setImageDrawable(getResources().getDrawable(R.drawable.two_fingers));
            step.setVisibility(View.VISIBLE);
            //root.findViewById(R.id.Step).setVisibility(View.VISIBLE);
            totalScore += 10;
        } else {
            step.setImageDrawable(getResources().getDrawable(R.drawable.one_finger));
            step.setVisibility(View.VISIBLE);
            totalScore -= 10;
        }
        TextView view1 = (TextView) root.findViewById(R.id.total);
        view1.setText(String.valueOf(totalScore));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}