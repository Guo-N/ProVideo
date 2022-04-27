package com.mfw.provideo.nominate.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.R;
import com.mfw.provideo.databinding.SingleSmallvideoItemBinding;
import com.mfw.provideo.viewmodel.VideoCardViewModel;

import java.util.List;

public class smallVideoAdapter extends RecyclerView.Adapter<smallVideoAdapter.MyViewHolder> {

    private List<VideoCardViewModel> data;
    private SingleSmallvideoItemBinding binding;
    public smallVideoAdapter(List<VideoCardViewModel> data){
        this.data=data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(),parent,false);
        MyViewHolder holder =new MyViewHolder(view);
        binding = DataBindingUtil.bind(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.refreshSmallVideo(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void refreshSmallVideo(VideoCardViewModel data){
            binding.setViewModel(data);
        }

    }

    public int  getLayout(){
        return R.layout.single_smallvideo_item;
    }
}
