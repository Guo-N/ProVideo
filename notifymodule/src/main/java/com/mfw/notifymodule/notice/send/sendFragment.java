package com.mfw.notifymodule.notice.send;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.notifymodule.R;
import com.mfw.notifymodule.databinding.FragmentSendBinding;
import com.mfw.notifymodule.notice.Adapter.emptyAdapter;
import com.mfw.notifymodule.notice.Adapter.sendFragmentAdapter;
import com.mfw.notifymodule.notice.BaseFragment;
import com.mfw.notifymodule.notice.Service.LoadService;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;

import java.util.List;

public class sendFragment extends BaseFragment<FragmentSendBinding,sendViewModel> implements LoadService {


    private RecyclerView.Adapter adapter;
    private Class clz;

    public static sendFragment newInstance() {
        sendFragment fragment = new sendFragment();
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
        binding.rvTopicView.setHasFixedSize(true);
        binding.rvTopicView
                .setLayoutManager(new LinearLayoutManager(getContext()));
        binding.refreshLayout
                .setRefreshHeader(new ClassicsHeader(getContext()));
        binding.refreshLayout
                .setRefreshFooter(new ClassicsFooter(getContext()));
        binding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            viewModel.load(clz);
        });
        binding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            viewModel.load(clz);
        });
        binding.rvTopicView.setLayoutManager(new LinearLayoutManager(getContext()));
        if(adapter==null)
            adapter=new emptyAdapter();
        binding.rvTopicView.setAdapter(adapter);
    }

    @Override
    public void LoadFinish(List data, Boolean refreshed) {
        if(refreshed && (data!=null && data.size()!=0)){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(data==null || data.size()==0){
                         adapter =new emptyAdapter();
                    }
                    else
                       adapter=new sendFragmentAdapter(data);
                    adapter.notifyDataSetChanged();
                }
            });
            binding.refreshLayout.finishRefresh(true);
        }
        else
            binding.refreshLayout.finishRefresh(false);
    }
}
