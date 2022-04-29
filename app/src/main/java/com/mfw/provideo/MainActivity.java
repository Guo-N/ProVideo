package com.mfw.provideo;


import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.fragment.app.Fragment;

import com.mfw.provideo.Activity.BaseActivity;
import com.mfw.provideo.Adapter.BottomBarAdapter;
import com.mfw.provideo.community.Adapter.ScreenAutoAdapter;
import com.mfw.provideo.community.CommuntiyFragment;
import com.mfw.provideo.Fragment.HomeFragment;
import com.mfw.provideo.notice.NotifyFragment;
import com.mfw.provideo.user.UserFragment;
import com.mfw.provideo.viewmodel.MainViewModel;
import com.mfw.provideo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private BottomBarAdapter adapter;
    private NavigationController mNavigationController;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initFragment();
        super.onCreate(savedInstanceState);

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        Fragment homeFragment = HomeFragment.newInstance();
        Fragment communityFragment = new CommuntiyFragment();
        Fragment notifyFragment = new NotifyFragment();
        Fragment userFragment = new UserFragment();
        fragments.add(homeFragment);
        fragments.add(communityFragment);
        fragments.add(notifyFragment);
        fragments.add(userFragment);
    }

    @Override
    protected void initView() {
         mNavigationController = binding.bottomView.material()
                 .addItem(R.drawable.main_home,"首页", getResources().getColor(R.color.black))
                 .addItem(R.drawable.main_community,"社区", getResources().getColor(R.color.black))
                 .addItem(R.drawable.main_notify,"通知", getResources().getColor(R.color.black))
                 .addItem(R.drawable.main_user,"我的", getResources().getColor(R.color.black))
                 .setDefaultColor( getResources().getColor(R.color.black))
                 .enableAnimateLayoutChanges()
                 .build();
        mNavigationController.setHasMessage(2, true);
        mNavigationController.setMessageNumber(3, 6);
        adapter = new BottomBarAdapter(getSupportFragmentManager(),fragments);
        binding.cvContentView.setOffscreenPageLimit(1);
        binding.cvContentView.setAdapter(adapter);
        mNavigationController.setupWithViewPager(binding.cvContentView);

    }

}