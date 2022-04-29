package com.mfw.provideo.notice.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.R;
import com.mfw.provideo.Service.BaseFindViewModel;

import java.util.List;

public class emptyAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_layout,parent,false);
        emptyAdapter.MyViwHoldeer holder =new MyViwHoldeer(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViwHoldeer extends RecyclerView.ViewHolder{

        public MyViwHoldeer(@NonNull View itemView) {
            super(itemView);
        }

    }
}
