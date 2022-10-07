package com.example.myapplication.ui.cards;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CardViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("Список заключённых");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
