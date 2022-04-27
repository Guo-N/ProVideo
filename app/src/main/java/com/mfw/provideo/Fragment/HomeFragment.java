package com.mfw.provideo.Fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.mfw.provideo.Adapter.homeContentAdapter;
import com.mfw.provideo.Daily.DailyFragment;
import com.mfw.provideo.Find.FindFragment;
import com.mfw.provideo.nominate.NominateFragment;
import com.mfw.provideo.R;
import com.mfw.provideo.Find.HomeViewModel;
import com.mfw.provideo.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

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
            Fragment findFragment = new FindFragment();
            Fragment NominateFragment = new NominateFragment();
            Fragment DailyFragment = new DailyFragment();
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
