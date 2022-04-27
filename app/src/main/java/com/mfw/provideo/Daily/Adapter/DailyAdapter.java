package com.mfw.provideo.Daily.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.Find.Adapter.BrifCardAdapter;
import com.mfw.provideo.R;
import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.databinding.HomeItemDailyItemBinding;
import com.mfw.provideo.databinding.NominateSingalItemBinding;
import com.mfw.provideo.nominate.Adapter.FollowCardAdapter;
import com.mfw.provideo.nominate.Adapter.SingleItemAdapter;
import com.mfw.provideo.nominate.viewModel.FollowCardViewModel;
import com.mfw.provideo.viewmodel.SingleTitleViewModel;
import com.mfw.provideo.viewmodel.VideoCardViewModel;

import java.util.ArrayList;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {

    private List<BaseFindViewModel> data;
    private List<SingleTitleViewModel> singleTitle;
    //临时去装follow的集合
    private List<FollowCardViewModel> follow;
    //每个singleTitle后的follow集合
    private List<List<FollowCardViewModel>> follows;
    private HomeItemDailyItemBinding binding;
    private boolean flag=true;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(),parent,false);
        binding = DataBindingUtil.bind(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           holder.setTitle((SingleTitleViewModel) singleTitle.get(position));
           holder.setFollow(holder.itemView,(List<FollowCardViewModel>)follows.get(position));
    }

    @Override
    public int getItemCount() {
        if(singleTitle!=null && singleTitle.size()!=0)
        return (singleTitle.size());
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setTitle(SingleTitleViewModel data){
            binding.setViewModel(data);

        }

        public void setFollow(View view,List<FollowCardViewModel> data){
            binding.rvDailyFollow.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
            binding.rvDailyFollow.setAdapter(new FollowAdapter(data));
        }
    }

    public void setData(List<BaseFindViewModel> data) {
        this.data = data;
        singleTitle =new ArrayList<>();
        follows = new ArrayList<>();

        for(int i=0;i<data.size()-1;i++){
            if(data.get(i).getClass().equals(SingleTitleViewModel.class)){
                follow = new ArrayList<>();
                singleTitle.add((SingleTitleViewModel)data.get(i));
                while(i<data.size()-1 && ! data.get(i+1).getClass().equals(SingleTitleViewModel.class)){
                    follow.add((FollowCardViewModel) data.get(i+1));
                    i++;
                }
                follows.add(follow);
            }
        }
    }

    public int getLayout(){
        return R.layout.home_item_daily_item;
    }
}
