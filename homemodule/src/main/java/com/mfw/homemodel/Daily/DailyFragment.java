package com.mfw.homemodel.Daily;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mfw.homemodel.Fragment.BaseFragment;
import com.mfw.homemodel.Daily.Adapter.DailyAdapter;
import com.mfw.homemodel.Daily.ViewModel.DailyViewModel;
import com.mfw.homemodel.R;
import com.mfw.homemodel.Service.LoadService;
import com.mfw.homemodel.databinding.FragmentDailyBinding;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;


public class DailyFragment extends BaseFragment<FragmentDailyBinding, DailyViewModel> implements LoadService<FragmentDailyBinding> {


    private DailyAdapter adapter;
    private Class clz =getClass();

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DailyAdapter();
        if (viewModel == null)
            viewModel = new DailyViewModel();
        viewModel.Regist(this);
        viewModel.load(clz);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_daily;
    }

    @Override
    public void initFragment() {

    }

    @Override
    public void initView() {
        binding.refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        binding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if(viewModel==null)
                    viewModel = new DailyViewModel();
                viewModel.load(clz);
            }
        });
        binding.rvDailyView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rvDailyView.setAdapter(adapter);
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
