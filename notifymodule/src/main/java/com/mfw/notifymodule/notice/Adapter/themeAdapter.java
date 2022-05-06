package com.mfw.notifymodule.notice.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mfw.notifymodule.notice.Service.BaseFindViewModel;
import com.mfw.notifymodule.notice.theme.themeContentFragment;
import com.mfw.notifymodule.notice.viewmodel.Tabs;

import java.util.ArrayList;
import java.util.List;

public class themeAdapter extends FragmentPagerAdapter {

    private List<Tabs> tabs;

    public themeAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return themeContentFragment.newInstance(tabs.get(position).getName(),tabs.get(position).getApiUrl());
    }


    public void setData(List<BaseFindViewModel> tabs){
        List<Tabs> tab =new ArrayList<>();
        for(BaseFindViewModel t:tabs){
            if(t.getClass().equals(Tabs.class))
                tab.add((Tabs) t);
        }
        this.tabs=tab;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        if (tabs != null && tabs.size() >0){
            return tabs.size();
        }
        return 0;
    }
}
