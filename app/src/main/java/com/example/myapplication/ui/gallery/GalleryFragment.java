package com.example.myapplication.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private ImageButton img_button_left; /*Левая кнопка*/
    private ImageButton img_button_right; /*Правая кнопка*/
    private Button resetbtn; /*Кнопка сброса*/
    private TextView mycnt; /*Число*/
    private int counter; /*Счетчик*/

    /*Вызов небходимого метода в зависимости от нажатой кнопки*/
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.img_Button_left :
                    minusCounter();
                    break;
                case R.id.img_Button_right :
                    plusCounter();
                    break;
                case R.id.resetbtn :
                    initCounter();
                    break;
            }
        }
    };

    /*Метод сброса подсчета*/
    private void initCounter(){
        counter = 0;
        mycnt.setText(counter + "");
    }

    /*Метод суммирования числа*/
    private void plusCounter(){
        counter++;
        mycnt.setText(counter + "");
    }

    /*Метод вычитания числа*/
    private void minusCounter(){
        counter--;
        mycnt.setText(counter + "");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        /*Объявление числа*/
        mycnt = (TextView) root.findViewById(R.id.mycnt);
        /*Объявление левой кнопки*/
        img_button_left = (ImageButton) root.findViewById(R.id.img_Button_left);
        /*Метод для нажатия левой кнопки*/
        img_button_left.setOnClickListener(clickListener);
        /*Объявление правой кнопки*/
        img_button_right = (ImageButton) root.findViewById(R.id.img_Button_right);
        /*Метод для нажатия правой кнопки*/
        img_button_right.setOnClickListener(clickListener);
        /*Объявление кнопки сброса*/
        resetbtn = (Button) root.findViewById(R.id.resetbtn);
        /*Метод для нажатия кнопки сброса*/
        resetbtn.setOnClickListener(clickListener);

        initCounter();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}