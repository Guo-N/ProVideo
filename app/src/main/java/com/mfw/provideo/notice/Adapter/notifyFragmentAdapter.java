package com.mfw.provideo.notice.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class notifyFragmentAdapter extends FragmentPagerAdapter {

    private final String[] tab={"发现","推荐","日报"};
    private List<Fragment> fragments;

    public notifyFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }
    public void setData(List<Fragment> fragments){
        this.fragments=fragments;
    }
}
