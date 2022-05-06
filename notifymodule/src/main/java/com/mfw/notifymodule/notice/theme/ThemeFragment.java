package com.mfw.notifymodule.notice.theme;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;
import com.mfw.notifymodule.R;
import com.mfw.notifymodule.databinding.FragmentThemeBinding;
import com.mfw.notifymodule.notice.Adapter.themeAdapter;
import com.mfw.notifymodule.notice.BaseFragment;
import com.mfw.notifymodule.notice.Service.BaseFindViewModel;
import com.mfw.notifymodule.notice.Service.LoadService;
import com.mfw.notifymodule.notice.viewmodel.Tabs;

import java.util.ArrayList;
import java.util.List;

public class ThemeFragment extends BaseFragment<FragmentThemeBinding,themeViewModel> implements LoadService {

    private themeAdapter adapter;
    private Class clz;

    public static ThemeFragment newInstance() {
        ThemeFragment fragment = new ThemeFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (clz == null)
            clz = getClass();
        if (viewModel == null)
            viewModel = new themeViewModel();
        viewModel.Regist(this);
        viewModel.load(clz);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_theme;
    }

    @Override
    public void initFragment() {
        if(viewModel == null)
            viewModel = new themeViewModel();
        viewModel.load(clz);
    }

    @Override
    public void initView() {
        binding.vpContent.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.vpContent.setOffscreenPageLimit(0);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.vpContent.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        adapter = new themeAdapter(getChildFragmentManager());
        binding.vpContent.setAdapter(adapter);
    }

    @Override
    public void LoadFinish(List data, Boolean refreshed) {
        binding.tabLayout.removeAllTabs();
        List<Tabs> t=new ArrayList<>();
        for(BaseFindViewModel b: (List<BaseFindViewModel>) data){
            if(b.getClass().equals(Tabs.class)){
                t.add((Tabs)b);
            }
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setData(data);
//                adapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
                for (Tabs tabs1 :t){
                    binding.tabLayout.addTab(binding.tabLayout.newTab().setText(tabs1.getName()));
                }
                binding.tabLayout.scrollTo(0,0);
            }
        });

    }

}
