package com.mfw.homemodel.Fragment;

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

import com.mfw.homemodel.Service.BaseViewModel;

public abstract class BaseFragment<T extends ViewDataBinding,V extends BaseViewModel> extends Fragment {

    protected T binding;
    protected V viewModel;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(),getLayoutId(),container,false);
        initFragment();
        initView();
        return binding.getRoot();
    }


    public abstract int getLayoutId();
    public abstract void initFragment();
    public abstract void initView();

}
