package com.mfw.provideo.Daily.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.R;
import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.databinding.HomeNominateFollowCardItemBinding;
import com.mfw.provideo.nominate.Adapter.FollowCardAdapter;
import com.mfw.provideo.nominate.viewModel.FollowCardViewModel;

import java.util.List;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.MyViewHolder> {

    private List<FollowCardViewModel> follows;
    private List<BaseFindViewModel> data;
    private HomeNominateFollowCardItemBinding binding;


    public FollowAdapter(List<FollowCardViewModel> follows){
        this.follows = follows;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoyt(),parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        binding = DataBindingUtil.bind(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.setFollow(follows.get(position));
    }

    @Override
    public int getItemCount() {
        return follows.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setFollow(FollowCardViewModel followCardViewModel){
            binding.setViewModel(followCardViewModel);
        }


    }

    public void setData(List<BaseFindViewModel> data){
        this.data = data;
    }

    public int getLayoyt(){
        return R.layout.home_nominate_follow_card_item;
    }
}
