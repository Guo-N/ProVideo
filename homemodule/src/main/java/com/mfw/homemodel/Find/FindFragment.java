package com.mfw.homemodel.Find;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mfw.homemodel.Fragment.BaseFragment;
import com.mfw.homemodel.Find.Adapter.FindAdapter;
import com.mfw.homemodel.R;
import com.mfw.homemodel.Service.LoadService;
import com.mfw.homemodel.databinding.FragmentFindBinding;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;


public class FindFragment extends BaseFragment<FragmentFindBinding,HomeViewModel> implements LoadService<FragmentFindBinding> {

    private FindAdapter adapter = new FindAdapter();
    private Class clz;
    public static FindFragment newInstance(){
        return new FindFragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initFragment() {
        if(viewModel == null)
            viewModel = new HomeViewModel();
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(clz==null)
            clz = getClass();
        if (viewModel == null)
            viewModel = new HomeViewModel();
         viewModel.Regist(this);
         viewModel.load(clz);
    }

    @Override
    public void initView() {
        System.out.println(binding.getClass());
        binding.refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        binding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if(viewModel==null)
                    viewModel = new HomeViewModel();
                    viewModel.load(clz);
            }
        });
        binding.findRecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.findRecycler.setAdapter(adapter);

    }

    @Override
    public void LoadFinish(List data, Boolean refreshed) {
        if(refreshed && (data!=null && data.size()!=0)){
         adapter.setData(data);
         getActivity().runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 adapter.notifyDataSetChanged();
             }
         });
         binding.refreshLayout.finishRefresh(true);
        }
        else
            binding.refreshLayout.finishRefresh(false);
    }



}


