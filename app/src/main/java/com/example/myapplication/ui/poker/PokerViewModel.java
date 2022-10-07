package com.example.myapplication.ui.poker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PokerViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PokerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Тюремный покер");
    }

    public LiveData<String> getText() {
        return mText;
    }
}