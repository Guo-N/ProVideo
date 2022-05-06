package com.mfw.communitymodule.community.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mfw.communitymodule.R;
import com.mfw.communitymodule.Service.LoadService;
import com.mfw.communitymodule.community.Adapter.AttentionAdapter;
import com.mfw.communitymodule.community.AttentionViewModel;
import com.mfw.communitymodule.databinding.CommunityFragmentAttentionBinding;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;

public class AttendFragment extends BaseFragment<CommunityFragmentAttentionBinding, AttentionViewModel> implements LoadService<CommunityFragmentAttentionBinding> {

    private AttentionAdapter adapter = new AttentionAdapter();
    private Class clz;


    public static AttendFragment newInstance() {
        AttendFragment fragment = new AttendFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.community_fragment_attention;
    }

    @Override
    public void initFragment() {
        if(viewModel == null)
            viewModel = new AttentionViewModel();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(clz==null)
            clz = getClass();
        if (viewModel == null)
            viewModel = new AttentionViewModel();
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
        binding.rvAttentionView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rvAttentionView.setAdapter(adapter);
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
