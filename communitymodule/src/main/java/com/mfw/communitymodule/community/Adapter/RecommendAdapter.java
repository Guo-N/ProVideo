package com.mfw.communitymodule.community.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mfw.communitymodule.R;
import com.mfw.communitymodule.Service.BaseFindViewModel;
import com.mfw.communitymodule.community.viewmodel.CloumnsCardViewModel;
import com.mfw.communitymodule.community.viewmodel.HorizontalScrollCard;
import com.mfw.communitymodule.databinding.CommunityItemCardViewBinding;
import com.mfw.communitymodule.databinding.CommunityItemSquareCardViewBinding;

import java.util.ArrayList;
import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.MyViewHolder> {

    private List<BaseFindViewModel> data;
    private int[] layout= {R.layout.community_item_square_card_view,R.layout.community_item_card_view};
    private ViewDataBinding[] bindings = new ViewDataBinding[layout.length];
    private List<HorizontalScrollCard> horizontalScrollCards;
    private List<CloumnsCardViewModel> leftCardView;
    private List<CloumnsCardViewModel> rightCardview;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layout[viewType],parent,false);
        bindings[viewType] = DataBindingUtil.bind(view);
        view.setTag(viewType+"");
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
         holder.setLayOut(holder.getView());
    }

    @Override
    public int getItemCount() {
        return layout.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private View ItemView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            setView(itemView);
        }

        public void setLayOut(View view){
           String tag = (String)view.getTag();
            switch (tag){
                case "0":
                    initSquareCard(horizontalScrollCards);
                    break;
                case "1":
                    initLeftCard(leftCardView);
                    initRightCard(rightCardview);
                    break;
            }
        }

        public void initSquareCard(List<HorizontalScrollCard> data) {
            CommunityItemSquareCardViewBinding binding =(CommunityItemSquareCardViewBinding)bindings[0];
            binding.rvSquareView.setLayoutManager(new GridLayoutManager(ItemView.getContext(), 2));
            binding.rvSquareView.setAdapter(new SquareCardItemAdapter(data));

        }

        public void initLeftCard(List<CloumnsCardViewModel> data) {
            CommunityItemCardViewBinding binding = (CommunityItemCardViewBinding) bindings[1];
            binding.rvSquareLeft.setLayoutManager(new GridLayoutManager(itemView.getContext(), 1));
            binding.rvSquareLeft.setAdapter(new CarditemAdapter(leftCardView));

        }
        public void initRightCard(List<CloumnsCardViewModel> data){
            CommunityItemCardViewBinding binding = (CommunityItemCardViewBinding) bindings[1];
            binding.rvSquareRight.setLayoutManager(new GridLayoutManager(itemView.getContext(), 1));
            binding.rvSquareRight.setAdapter(new CarditemAdapter(rightCardview));
        }

        public void setView(View view) {
            this.ItemView = view;
        }

        public View getView() {
            return ItemView;


        }
    }

    public void setData(List<BaseFindViewModel> data){
        this.data=data;
        int count=1;
        horizontalScrollCards = new ArrayList<>();
        leftCardView = new ArrayList<>();
        rightCardview = new ArrayList<>();
        for(BaseFindViewModel data1:data){
            if(data1.getClass().equals(HorizontalScrollCard.class))
                    horizontalScrollCards.add((HorizontalScrollCard) data1);
            else{
                if(count%2!=0)
                    leftCardView.add((CloumnsCardViewModel) data1);
                else
                    rightCardview.add((CloumnsCardViewModel)data1);
                count++;
            }

        }
    }
}
