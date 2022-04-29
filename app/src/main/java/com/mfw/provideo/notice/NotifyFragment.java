package com.mfw.provideo.notice;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.mfw.provideo.Find.HomeViewModel;
import com.mfw.provideo.Fragment.BaseFragment;
import com.mfw.provideo.notice.Adapter.notifyFragmentAdapter;
import com.mfw.provideo.notice.message.messageFragment;
import com.mfw.provideo.notice.send.sendFragment;
import com.mfw.provideo.notice.theme.ThemeFragment;
import com.mfw.provideo.R;
import com.mfw.provideo.databinding.FragmentNoticeBinding;

import java.util.ArrayList;
import java.util.List;

public class NotifyFragment extends BaseFragment<FragmentNoticeBinding, HomeViewModel> {

    private notifyFragmentAdapter adapter;
    private List<Fragment> data;



    @Override
    public int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    public void initFragment() {
        data = new ArrayList<>();
        data.add(ThemeFragment.newInstance());
        data.add(messageFragment.newInstance());
        data.add(sendFragment.newInstance());
    }

    @Override
    public void initView() {
        adapter = new notifyFragmentAdapter(getChildFragmentManager(),data);
        binding.vpHomeContent.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.vpHomeContent);
        binding.vpHomeContent.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.vpHomeContent.setCurrentItem(tab.getPosition());
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
