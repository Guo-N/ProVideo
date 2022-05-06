package com.mfw.homemodel;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.mfw.commonmodule.FragmentRouterInit;
import com.mfw.homemodel.Adapter.homeContentAdapter;
import com.mfw.homemodel.Daily.DailyFragment;
import com.mfw.homemodel.Find.FindFragment;
import com.mfw.homemodel.Find.HomeViewModel;
import com.mfw.homemodel.Fragment.BaseFragment;
import com.mfw.homemodel.databinding.FragmentHomeBinding;
import com.mfw.homemodel.nominate.NominateFragment;

import java.util.ArrayList;
import java.util.List;



@Route( path= FragmentRouterInit.Home.PAGER_HOME)
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    List<Fragment> fragmentList;
    private FragmentPagerAdapter pagerAdapter;

    private volatile static HomeFragment Instance;

    public static HomeFragment newInstance(){
        if(Instance == null){
            synchronized (HomeFragment.class){
                if(Instance == null){
                    Instance = new HomeFragment();
                }
            }
        }
        return Instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    public void initFragment(){
            fragmentList = new ArrayList<>();
            Fragment findFragment = FindFragment.newInstance();
            Fragment NominateFragment = new NominateFragment();
            Fragment DailyFragment =new DailyFragment();
            fragmentList.add(findFragment);
            fragmentList.add(NominateFragment);
            fragmentList.add(DailyFragment);
    }

    public void initView() {
        pagerAdapter=new homeContentAdapter(getChildFragmentManager(),fragmentList);
        binding.homeContent.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.homeContent);
        binding.homeContent.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.homeContent.setCurrentItem(0);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.homeContent.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}
