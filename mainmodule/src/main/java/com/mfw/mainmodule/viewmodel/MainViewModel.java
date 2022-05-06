package com.mfw.mainmodule.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    MutableLiveData<String> name = new MutableLiveData<>();
    MutableLiveData<String> age = new MutableLiveData<>();



    public MutableLiveData<String> getName() {
       return name;
    }

    public MutableLiveData<String> getAge() {
        return age;
    }
}
