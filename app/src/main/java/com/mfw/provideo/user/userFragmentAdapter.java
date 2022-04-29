package com.mfw.provideo.user;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.Daily.Adapter.DailyAdapter;
import com.mfw.provideo.R;
import com.mfw.provideo.databinding.UserFragmentItemBinding;
import com.mfw.provideo.notice.Adapter.emptyAdapter;

import java.util.List;

public class userFragmentAdapter extends RecyclerView.Adapter<userFragmentAdapter.MyViewHolder> {

    private List<String> data;
    private  UserFragmentItemBinding binding;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(),parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        binding= DataBindingUtil.bind(view);
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

    public void setData(List<String> data){
        this.data=data;
    }

    public int getLayout(){
        return R.layout.user_fragment_item;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void setContent(String data){
            binding.tvItem.setText(data);
        }
    }


}
