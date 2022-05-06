package com.mfw.homemodel.nominate.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.homemodel.R;
import com.mfw.homemodel.databinding.NominateSingalItemBinding;
import com.mfw.homemodel.databinding.SingleFollowItemBinding;
import com.mfw.homemodel.nominate.viewHolder.SingalItemViewModel;

import java.util.List;

public class SingleItemAdapter extends RecyclerView.Adapter<SingleItemAdapter.MyViewHolder> {

    private List<SingalItemViewModel> data;

    public SingleItemAdapter(List<SingalItemViewModel> data){
        this.data=data;
        if(data !=null && data.size()!=0){
            for(SingalItemViewModel data1 : data){
                System.out.println(data1);
            }
        }

    }

    private SingleFollowItemBinding followBinding;
    private NominateSingalItemBinding binding;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(),parent,false);
        MyViewHolder holder = new MyViewHolder(view);
//        followBinding= DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_follow_item,parent,false));
        binding= DataBindingUtil.bind(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(data!=null && data.size()!=0){
            holder.refreshTitle(data.get(position));
            holder.refreshFollow(data.get(position));
            holder.refreshSmallVideo(holder.itemView,data.get(position));
        }
    }



    @Override
    public int getItemCount() {
        if(data!=null && data.size()!=0){
            return data.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void refreshFollow(SingalItemViewModel singalItemViewModel){
            if(singalItemViewModel.followCardViewModel != null)
           binding.setViewModel1(singalItemViewModel.followCardViewModel);
        }

        public void refreshSmallVideo(View view,SingalItemViewModel singalItemViewModel){
            if(singalItemViewModel.viewCards != null){
                binding.rvSingleSmallVideo.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
                binding.rvSingleSmallVideo.setAdapter(new smallVideoAdapter(singalItemViewModel.viewCards));
            }
        }

        public void refreshTitle(SingalItemViewModel singalItemViewModel){
//            binding.tvTitle.setText(singalItemViewModel.singalItemViewModel.title);
            if(singalItemViewModel.singalItemViewModel !=null)
            binding.setViewModel(singalItemViewModel.singalItemViewModel);
        }
    }

    public int getLayout(){
        return R.layout.nominate_singal_item;
    }
}
