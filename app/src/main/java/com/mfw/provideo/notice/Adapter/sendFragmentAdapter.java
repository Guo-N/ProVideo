package com.mfw.provideo.notice.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.R;
import com.mfw.provideo.databinding.FragmentThemeContentItemBinding;
import com.mfw.provideo.notice.theme.themeContentFragment;
import com.mfw.provideo.notice.viewmodel.Tabs;
import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.notice.viewmodel.ThemesItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class sendFragmentAdapter extends RecyclerView.Adapter<sendFragmentAdapter.MyViewHolder> {

    private List<ThemesItemViewModel> data;
    private FragmentThemeContentItemBinding binding;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLauout(),parent,false);
        FragmentThemeContentItemBinding binding = DataBindingUtil.bind(view);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                holder.setContent(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setContent(ThemesItemViewModel data){
            binding.setViewModel(data);
        }
    }

    public void setData(List<BaseFindViewModel> data){
        Log.e("GY",data+"");
        for(BaseFindViewModel b:data){
            if(b.getClass().equals(ThemesItemViewModel.class)){
                this.data.add((ThemesItemViewModel)b);
            }
        }
    }

    public int getLauout(){
        return R.layout.fragment_theme_content_item;
    }

}
