package com.curso.android.app.practica.livedatavmdatabinding;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> counter = new MutableLiveData<>();

    public void incrementCounter() {
        int currentValue = counter.getValue() != null ? counter.getValue() : 0;
        counter.setValue(currentValue + 1);
    }
    public LiveData<Integer> getCounter() {
        return counter;
    }
}
