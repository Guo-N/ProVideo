package com.mfw.provideo.community;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.mfw.provideo.community.Adapter.communityFragmentAdapter;
import com.mfw.provideo.Find.HomeViewModel;
import com.mfw.provideo.Fragment.BaseFragment;
import com.mfw.provideo.R;
import com.mfw.provideo.community.Fragments.AttendFragment;
import com.mfw.provideo.community.Fragments.RecommendFragment;
import com.mfw.provideo.databinding.FragmentCommunityBinding;
import com.mfw.provideo.databinding.FragmentRecommendBinding;

import java.util.ArrayList;
import java.util.List;

public class CommuntiyFragment extends BaseFragment<FragmentCommunityBinding, HomeViewModel>{


    private communityFragmentAdapter adapter;
    private List<Fragment> data;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    public void initFragment() {
        data = new ArrayList<>();
        data.add(RecommendFragment.newInstance());
        data.add(AttendFragment.newInstance());
    }

    @Override
    public void initView() {
      binding.tabLayout.setupWithViewPager(binding.vpCommunityContent);
      binding.vpCommunityContent.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
      binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
          @Override
          public void onTabSelected(TabLayout.Tab tab) {
              binding.vpCommunityContent.setCurrentItem(tab.getPosition());
          }

          @Override
          public void onTabUnselected(TabLayout.Tab tab) {

          }

          @Override
          public void onTabReselected(TabLayout.Tab tab) {

          }
      });
        adapter = new communityFragmentAdapter(getChildFragmentManager());
        adapter.setData(data);
        binding.vpCommunityContent.setAdapter(adapter);
    }
}
