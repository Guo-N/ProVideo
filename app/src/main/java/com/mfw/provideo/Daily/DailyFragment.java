package com.mfw.provideo.Daily;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.Daily.Adapter.DailyAdapter;
import com.mfw.provideo.Daily.ViewModel.DailyViewModel;
import com.mfw.provideo.Fragment.BaseFragment;
import com.mfw.provideo.R;
import com.mfw.provideo.Find.HomeViewModel;
import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.Service.LoadService;
import com.mfw.provideo.databinding.FragmentDailyBinding;
import com.mfw.provideo.databinding.FragmentFindBinding;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;


public class DailyFragment extends BaseFragment<FragmentDailyBinding, DailyViewModel> implements LoadService<FragmentFindBinding> {


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
