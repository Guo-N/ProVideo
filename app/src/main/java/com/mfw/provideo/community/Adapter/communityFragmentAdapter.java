package com.mfw.provideo.community.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class communityFragmentAdapter extends FragmentPagerAdapter {

    private String[] tables = {"推荐","关注"};
    private List<Fragment> fragments;

    public communityFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        if (fragments!=null)
        return fragments.size();
        return 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tables[position];
    }

    public void setData(List<Fragment> fragments){
        this.fragments=fragments;
    }
}
