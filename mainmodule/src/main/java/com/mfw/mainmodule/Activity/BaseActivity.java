package com.mfw.mainmodule.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<V extends ViewBinding,T extends ViewModel> extends AppCompatActivity {

    protected V binding;
    protected T viewModel;



//    protected V viewBindingInflate(LayoutInflater inflater) {
//        return null;
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Type superclass = getClass().getGenericSuperclass();
        Class<V> vbClz = (Class<V>)((ParameterizedType)superclass).getActualTypeArguments()[0];
        Class<T> vmClz = (Class<T>)((ParameterizedType)superclass).getActualTypeArguments()[1];

//        binding = viewBindingInflate(getLayoutInflater());
//        setContentView(binding.getRoot());

        try{
            Method method = vbClz.getDeclaredMethod("inflate", LayoutInflater.class);
            binding = (V)method.invoke(null,getLayoutInflater());
            viewModel = new ViewModelProvider(this).get(vmClz);
            setContentView(binding.getRoot());
            dataObserve();
        }catch (Exception e){
            e.printStackTrace();
        }
//        initFragment();
        initView();
        dataObserve();
    }

    protected abstract void initView();
    protected  void dataObserve(){

    }



    public V getBinding(){
        return binding;
    }

    public T getViewModel(){
        return viewModel;
    }
}
