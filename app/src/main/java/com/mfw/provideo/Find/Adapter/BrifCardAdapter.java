package com.mfw.provideo.Find.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.R;
import com.mfw.provideo.databinding.BriefCardViewItemBinding;
import com.mfw.provideo.databinding.VideoCardViewItemBinding;
import com.mfw.provideo.viewmodel.BriefCardViewModel;
import com.mfw.provideo.viewmodel.VideoCardViewModel;

import java.util.List;

public  class BrifCardAdapter extends RecyclerView.Adapter<BrifCardAdapter.MyHolder> {

    private List<BriefCardViewModel> data;
    private int Flag;

public BrifCardAdapter(List<BriefCardViewModel> data){
    this.data = data;
}


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(),parent,false);
       MyHolder holder =new MyHolder(view);
       return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.RefreshData(data.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{

        BriefCardViewItemBinding binding;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void RefreshData(BriefCardViewModel data){
            binding.setViewModel(data);
        }

    }

    public int getLayout(){
        return R.layout.brief_card_view_item;
    }
}
