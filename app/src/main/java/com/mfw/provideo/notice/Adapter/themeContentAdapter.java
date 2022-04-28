package com.mfw.provideo.notice.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.notice.viewmodel.ThemesItemViewModel;
import com.mfw.provideo.R;
import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.databinding.FragmentThemeContentItemBinding;

import java.util.ArrayList;
import java.util.List;

public class themeContentAdapter extends RecyclerView.Adapter<themeContentAdapter.MyViewHolder> {

    private List<ThemesItemViewModel> data;
    private FragmentThemeContentItemBinding binding;

    @NonNull
    @Override
    public themeContentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(getLayout(),parent,false);
        binding= DataBindingUtil.bind(view);
        themeContentAdapter.MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull themeContentAdapter.MyViewHolder holder, int position) {
     holder.setContent(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setContent(ThemesItemViewModel data){
           binding.setViewModel(data);
        }


    }

    public int getLayout(){
        return R.layout.fragment_theme_content_item;
    }

    public void setData(List<BaseFindViewModel> data){
        this.data=new ArrayList<>();
        for(BaseFindViewModel b:data){
            if(b.getClass().equals(ThemesItemViewModel.class)){
                this.data.add((ThemesItemViewModel)b);
            }
        }
    }
}
