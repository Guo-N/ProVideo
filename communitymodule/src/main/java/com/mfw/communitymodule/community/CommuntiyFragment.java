package com.mfw.communitymodule.community;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.mfw.commonmodule.FragmentRouterInit;
import com.mfw.communitymodule.R;
import com.mfw.communitymodule.community.Adapter.communityFragmentAdapter;
import com.mfw.communitymodule.community.Fragments.AttendFragment;
import com.mfw.communitymodule.community.Fragments.BaseFragment;
import com.mfw.communitymodule.community.Fragments.RecommendFragment;
import com.mfw.communitymodule.databinding.FragmentCommunityBinding;

import java.util.ArrayList;
import java.util.List;

@Route( path= FragmentRouterInit.Community.PAGER_COMMUNITY)
public class CommuntiyFragment extends BaseFragment<FragmentCommunityBinding, CommunityViewModel> {


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
