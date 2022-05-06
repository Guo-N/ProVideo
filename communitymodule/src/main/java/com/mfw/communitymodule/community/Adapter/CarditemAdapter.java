package com.mfw.communitymodule.community.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.communitymodule.R;
import com.mfw.communitymodule.community.viewmodel.CloumnsCardViewModel;
import com.mfw.communitymodule.databinding.CommunityItemCommunityViewBinding;

import java.util.List;

public class CarditemAdapter extends RecyclerView.Adapter<CarditemAdapter.MyViewHolder> {

    private List<CloumnsCardViewModel> data;
    private CommunityItemCommunityViewBinding binding;

    public CarditemAdapter(List<CloumnsCardViewModel> data){
        this.data=data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_item_community_view,parent,false);
        binding = DataBindingUtil.bind(view);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setCard(data.get(position));
    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setCard(CloumnsCardViewModel card){
            binding.setViewModel(card);
        }
    }


}
