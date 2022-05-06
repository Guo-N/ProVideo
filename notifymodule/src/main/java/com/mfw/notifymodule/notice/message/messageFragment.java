package com.mfw.notifymodule.notice.message;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mfw.notifymodule.R;
import com.mfw.notifymodule.databinding.FragmentSendBinding;
import com.mfw.notifymodule.notice.Adapter.messageFragmentAdapter;
import com.mfw.notifymodule.notice.BaseFragment;
import com.mfw.notifymodule.notice.Service.LoadService;
import com.mfw.notifymodule.notice.send.sendViewModel;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;

public class messageFragment extends BaseFragment<FragmentSendBinding, sendViewModel> implements LoadService {


    private messageFragmentAdapter adapter=new messageFragmentAdapter();
    private Class clz;

    public static messageFragment newInstance() {

        messageFragment fragment = new messageFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(clz == null)
            clz = getClass();
        if(viewModel == null)
            viewModel = new sendViewModel();
        viewModel.Regist(this);
        viewModel.load(clz);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_send;
    }

    @Override
    public void initFragment() {
        if(viewModel == null)
            viewModel = new sendViewModel();
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
                binding.refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
        binding.rvTopicView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rvTopicView.setAdapter(adapter);
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
