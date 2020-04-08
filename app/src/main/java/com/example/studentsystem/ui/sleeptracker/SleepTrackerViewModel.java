package com.example.studentsystem.ui.sleeptracker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SleepTrackerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SleepTrackerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}