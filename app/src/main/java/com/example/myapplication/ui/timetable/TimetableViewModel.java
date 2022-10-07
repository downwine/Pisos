package com.example.myapplication.ui.timetable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimetableViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TimetableViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Распорядок дня");
    }

    public LiveData<String> getText() {
        return mText;
    }
}