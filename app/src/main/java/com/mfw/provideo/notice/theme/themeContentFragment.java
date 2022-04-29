package com.mfw.provideo.notice.theme;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mfw.provideo.Fragment.BaseFragment;
import com.mfw.provideo.notice.Adapter.themeContentAdapter;
import com.mfw.provideo.R;
import com.mfw.provideo.Service.LoadService;
import com.mfw.provideo.databinding.FragmentThemeContentBinding;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;

import java.util.List;

public class themeContentFragment extends BaseFragment<FragmentThemeContentBinding, themeContentViewModel> implements LoadService {

    private String name="";
    private String url="";
    private Class clz;
    private themeContentAdapter adapter;


    public static themeContentFragment newInstance(String name,String url) {
        themeContentFragment fragment = new themeContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(clz == null)
            clz = getClass();
        if(viewModel == null)
            viewModel = new themeContentViewModel();
        viewModel.Regist(this);
        initFragment();
        viewModel.initModel(name, url);
        viewModel.load(clz);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_theme_content;
    }

    @Override
    public void initFragment() {
        if (getArguments() != null)
        {
            name = getArguments().getString("name");
            url = getArguments().getString("url");
        }
        viewModel.load(clz);
    }

    @Override
    public void initView() {
        binding.rvThemeView.setHasFixedSize(true);
        binding.rvThemeView
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
        viewModel.initModel(name, url);
        adapter = new themeContentAdapter();
        binding.rvThemeView.setAdapter(adapter);
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
