package com.mfw.provideo.community.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mfw.provideo.community.Adapter.RecommendAdapter;
import com.mfw.provideo.Find.HomeViewModel;
import com.mfw.provideo.Fragment.BaseFragment;
import com.mfw.provideo.R;
import com.mfw.provideo.Service.LoadService;
import com.mfw.provideo.community.CommunityViewModel;
import com.mfw.provideo.databinding.FragmentRecommendBinding;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;

public class RecommendFragment extends BaseFragment<FragmentRecommendBinding, CommunityViewModel> implements LoadService<FragmentRecommendBinding>{

    private RecommendAdapter adapter = new RecommendAdapter();
    private Class clz;

    public static RecommendFragment newInstance() {
        RecommendFragment fragment = new RecommendFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initFragment() {
        if(viewModel == null)
            viewModel = new CommunityViewModel();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(clz==null)
            clz = getClass();
        if (viewModel == null)
            viewModel = new CommunityViewModel();
        viewModel.Regist(this);
        viewModel.load(clz);
    }


    @Override
    public void initView() {
    binding.refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
    binding.refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
    binding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            viewModel.load(clz);
        }
    });
    binding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//            viewModel.load(clz);
        }
    });
        binding.rvRecommendView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rvRecommendView.setAdapter(adapter);
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
        else{
            binding.refreshLayout.finishRefresh(false);
        }

    }
}