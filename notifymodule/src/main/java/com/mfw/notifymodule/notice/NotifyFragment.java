package com.mfw.notifymodule.notice;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.mfw.commonmodule.FragmentRouterInit;
import com.mfw.notifymodule.R;
import com.mfw.notifymodule.databinding.FragmentNoticeBinding;
import com.mfw.notifymodule.notice.Adapter.notifyFragmentAdapter;
import com.mfw.notifymodule.notice.message.messageFragment;
import com.mfw.notifymodule.notice.send.sendFragment;
import com.mfw.notifymodule.notice.theme.ThemeFragment;
import com.mfw.notifymodule.notice.viewmodel.AttentionCardBean;
import com.mfw.notifymodule.notice.viewmodel.AttentionCardViewModel;
import com.mfw.notifymodule.notice.viewmodel.NotifyViewModel;
import com.mfw.notifymodule.notice.viewmodel.ThemesItemViewModel;

import java.util.ArrayList;
import java.util.List;


@Route( path= FragmentRouterInit.Notify.PAGER_NOTIFY)
public class NotifyFragment extends BaseFragment<FragmentNoticeBinding, NotifyViewModel> {

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
