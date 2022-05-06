package com.mfw.notifymodule.notice.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.notifymodule.R;
import com.mfw.notifymodule.databinding.FragmentThemeContentItemBinding;
import com.mfw.notifymodule.notice.Service.BaseFindViewModel;
import com.mfw.notifymodule.notice.viewmodel.ThemesItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class sendFragmentAdapter extends RecyclerView.Adapter<sendFragmentAdapter.MyViewHolder> {

    private List<ThemesItemViewModel> data;
    private FragmentThemeContentItemBinding binding;


    public sendFragmentAdapter(List<BaseFindViewModel> data){
        for(BaseFindViewModel b:data){
            if(b.getClass().equals(ThemesItemViewModel.class)){
                this.data.add((ThemesItemViewModel)b);
            }
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLauout(),parent,false);
        binding = DataBindingUtil.bind(view);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(data==null || data.size()==0)
            return;
        holder.setContent(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data==null?1:data.size();
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
        this.data=new ArrayList<>();
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
