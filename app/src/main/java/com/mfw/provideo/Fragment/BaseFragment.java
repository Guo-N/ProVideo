package com.mfw.provideo.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.mfw.provideo.Service.BaseViewModel;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class BaseFragment<T extends ViewDataBinding,V extends BaseViewModel> extends Fragment {

    protected T binding;
    protected V viewModel;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        initFragment();
        initView();
        return binding.getRoot();
    }


    public abstract int getLayoutId();
    public abstract void initFragment();
    public abstract void initView();

}
