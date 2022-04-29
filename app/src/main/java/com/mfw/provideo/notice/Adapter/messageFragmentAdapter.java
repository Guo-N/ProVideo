package com.mfw.provideo.notice.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.R;
import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.databinding.FragmentMessageItemBinding;
import com.mfw.provideo.databinding.FragmentThemeContentItemBinding;
import com.mfw.provideo.notice.viewmodel.Message;
import com.mfw.provideo.notice.viewmodel.MessageViewModel;
import com.mfw.provideo.notice.viewmodel.ThemesItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class messageFragmentAdapter extends RecyclerView.Adapter<messageFragmentAdapter.MyViewHolder> {

    private List<MessageViewModel> data;
    private FragmentMessageItemBinding binding;

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
                holder.setContent(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setContent(MessageViewModel data){
            binding.setViewModel(data);
        }
    }

    public void setData(List<MessageViewModel> data){
        this.data=new ArrayList<>();
        for(BaseFindViewModel b:data){
            if(b.getClass().equals(MessageViewModel.class)){
                this.data.add((MessageViewModel)b);
            }
        }
    }

    public int getLauout(){
        return R.layout.fragment_message_item;
    }

}
