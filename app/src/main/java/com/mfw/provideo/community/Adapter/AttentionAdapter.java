package com.mfw.provideo.community.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.R;
import com.mfw.provideo.community.bean.AttentionCardViewModel;
import com.mfw.provideo.community.viewmodel.CloumnsCardViewModel;
import com.mfw.provideo.databinding.CommunityItemAttentionCardViewBinding;
import com.mfw.provideo.databinding.CommunityItemCommunityViewBinding;

import java.util.List;

public class AttentionAdapter extends RecyclerView.Adapter<AttentionAdapter.MyViewHolder> {

    private List<AttentionCardViewModel> data;
    private CommunityItemAttentionCardViewBinding binding;



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_item_attention_card_view,parent,false);
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

        public void setCard(AttentionCardViewModel attentionData){
            binding.videoItemPlayer.loadCoverImage(attentionData.coverUrl,0);
            binding.setViewModel(attentionData);
        }
    }
public void setData(List data){
        this.data=data;
}

}
