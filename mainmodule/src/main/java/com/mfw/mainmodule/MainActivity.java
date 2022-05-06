package com.mfw.mainmodule;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.mfw.commonmodule.ActivityRouterInit;
import com.mfw.commonmodule.FragmentRouterInit;
import com.mfw.communitymodule.community.CommuntiyFragment;
import com.mfw.homemodel.HomeFragment;
import com.mfw.mainmodule.Activity.BaseActivity;
import com.mfw.mainmodule.Adapter.BottomBarAdapter;
import com.mfw.mainmodule.databinding.ActivityMainBinding;
import com.mfw.mainmodule.viewmodel.MainViewModel;
import com.mfw.notifymodule.notice.NotifyFragment;
import com.mfw.usermodule.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;



@Route( path= ActivityRouterInit.Main.PAGER_MAIN)
public class MainActivity extends BaseActivity<com.mfw.mainmodule.databinding.ActivityMainBinding, MainViewModel> {

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
        Fragment homeFragment = (Fragment)ARouter.getInstance().build(FragmentRouterInit.Home.PAGER_HOME).navigation();
        Fragment communityFragment = (Fragment)ARouter.getInstance().build(FragmentRouterInit.Community.PAGER_COMMUNITY).navigation();
        Fragment notifyFragment =(Fragment)ARouter.getInstance().build(FragmentRouterInit.Notify.PAGER_NOTIFY).navigation();
        Fragment userFragment =(Fragment)ARouter.getInstance().build(FragmentRouterInit.User.PAGER_USER).navigation();
        fragments.add(homeFragment);
        fragments.add(communityFragment);
        fragments.add(notifyFragment);
        fragments.add(userFragment);
    }


//    @Override
//    protected ActivityMainBinding viewBindingInflate(LayoutInflater inflater) {
//        return ActivityMainBinding.inflate(inflater);
//    }


    @Override
    protected void initView() {
            PageNavigationView pageNavigationView =  findViewById(R.id.bottom_view);
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