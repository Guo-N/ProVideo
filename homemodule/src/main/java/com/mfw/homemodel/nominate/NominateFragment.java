package com.mfw.homemodel.nominate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mfw.homemodel.Fragment.BaseFragment;
import com.mfw.homemodel.R;
import com.mfw.homemodel.Service.LoadService;
import com.mfw.homemodel.databinding.FragmentNominateBinding;
import com.mfw.homemodel.nominate.Adapter.NominateAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;

public class NominateFragment extends BaseFragment<FragmentNominateBinding, NominateViewModel> implements LoadService<FragmentNominateBinding> {

    private NominateAdapter adapter=new NominateAdapter();
    private Class clz;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(clz == null)
        clz = getClass();
        if(viewModel == null)
         viewModel = new NominateViewModel();
        viewModel.Regist(this);
        viewModel.load(clz);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_nominate;
    }

    @Override
    public void initFragment() {
        if(viewModel == null)
            viewModel = new NominateViewModel();
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
        binding.rvNominateView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.rvNominateView.setAdapter(adapter);
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
//        else
//            binding.refreshLayout.finishRefresh(false);
    }
}
