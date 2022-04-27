package com.mfw.provideo.Find.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.R;
import com.mfw.provideo.databinding.CategoryCardViewItemBinding;
import com.mfw.provideo.viewmodel.Bean.CategoryCardBean;
import com.mfw.provideo.viewmodel.Bean.SubjectCardBean;

import java.util.List;
import java.util.zip.Inflater;

public  class CatagoryCardAdapter extends RecyclerView.Adapter<CatagoryCardAdapter.MyHolder> {

    private List<CategoryCardBean.DataBeanX.ItemListBean> data;
    private int Flag;

public CatagoryCardAdapter(List<CategoryCardBean.DataBeanX.ItemListBean> data){
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


    public class MyHolder<T> extends RecyclerView.ViewHolder{

        CategoryCardViewItemBinding binding;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void RefreshData(CategoryCardBean.DataBeanX.ItemListBean data){
            binding.setViewModel(data.getData());
        }

    }

    public int getLayout(){
        return R.layout.category_card_view_item;
    }
}
