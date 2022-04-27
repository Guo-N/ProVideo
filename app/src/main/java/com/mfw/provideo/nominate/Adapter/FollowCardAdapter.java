package com.mfw.provideo.nominate.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.R;
import com.mfw.provideo.databinding.HomeItemNotimateFollowBinding;
import com.mfw.provideo.databinding.HomeNominateFollowCardItemBinding;
import com.mfw.provideo.nominate.viewModel.FollowCardViewModel;

import java.util.List;
import java.util.zip.Inflater;

public class FollowCardAdapter extends RecyclerView.Adapter<FollowCardAdapter.MyViewHolder> {

    private List<FollowCardViewModel> data;
    public FollowCardAdapter(List<FollowCardViewModel> data){
        this.data=data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(),parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    holder.RefreshData(data.get(position));
    }

    @Override
    public int getItemCount() {
        if(data != null && data.size()!=0)
        return data.size();
        return 0;
    }

   public class MyViewHolder extends RecyclerView.ViewHolder{

        private HomeNominateFollowCardItemBinding binding;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);
           binding = DataBindingUtil.bind(itemView);
       }

       public void RefreshData(FollowCardViewModel data){
           binding.setViewModel(data);
       }

   }

    public int getLayout(){
        return R.layout.home_nominate_follow_card_item;
    }

}
