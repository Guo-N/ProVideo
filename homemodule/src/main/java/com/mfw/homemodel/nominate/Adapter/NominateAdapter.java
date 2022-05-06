package com.mfw.homemodel.nominate.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mfw.homemodel.R;
import com.mfw.homemodel.Service.BaseFindViewModel;
import com.mfw.homemodel.databinding.HomeItemNotimateFollowBinding;
import com.mfw.homemodel.databinding.HomeNominateSingleItemViewBinding;
import com.mfw.homemodel.nominate.viewHolder.SingalItemViewModel;
import com.mfw.homemodel.nominate.viewModel.FollowCardViewModel;
import com.mfw.homemodel.viewmodel.SingleTitleViewModel;
import com.mfw.homemodel.viewmodel.TitleViewModel;
import com.mfw.homemodel.viewmodel.VideoCardViewModel;

import java.util.ArrayList;
import java.util.List;

public class NominateAdapter extends RecyclerView.Adapter<NominateAdapter.MyViewHoler> {

    private int[] nominate = {R.layout.home_nominate_banner_view,R.layout.home_item_notimate_follow,R.layout.home_nominate_single_item_view};
    private ViewDataBinding[] bindings=new ViewDataBinding[nominate.length];
    private List<FollowCardViewModel> followCard;
    private List<TitleViewModel> title;
    private List<SingalItemViewModel> singalItem;
    private List<SingalItemViewModel> singalItems;
    private SingleItemAdapter singleItemAdapter  ;
    @NonNull
    @Override
    public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        MyViewHoler holder = null;
        view = LayoutInflater.from(parent.getContext()).inflate(nominate[viewType],parent,false);
        bindings[viewType] = DataBindingUtil.bind(view);
        view.setTag(viewType+"");
        holder = new MyViewHoler(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoler holder, int position) {
        holder.setNewLayout(holder.getItemView());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return nominate.length;
    }


    public class  MyViewHoler extends RecyclerView.ViewHolder{
        private View itemView;
        public MyViewHoler(@NonNull View itemView) {
            super(itemView);
            setView(itemView);
        }

        public void setNewLayout(View itemView){
            String viewType = (String) itemView.getTag();
            switch (viewType){
//                case "0":
//                    initNominateBanner();
                case "1":
                    initFollowCard(itemView);
                    break;
                case "2":
                    initSingleItem(itemView);
                    break;
//                case "3":
//                    initvideoCard(itemView);
//                case "4":
//                    initbriefCard(itemView);
            }
        }
        public void initFollowCard(View view){
           HomeItemNotimateFollowBinding binding = (HomeItemNotimateFollowBinding) bindings[1];
        if(title != null && title.size()!=0){
            binding.tvTitle.setText(title.get(0).title);
            binding.tvActionTitle.setText(title.get(0).actionTitle);
        }
            if(binding != null){
                binding.rvFollowView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
                binding.rvFollowView.setAdapter(new FollowCardAdapter(followCard));
            }
        }

        public void initSingleItem(View view){
            HomeNominateSingleItemViewBinding binding = (HomeNominateSingleItemViewBinding) bindings[2];
            if(binding!=null){
                binding.rvNominateSingalView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
                binding.rvNominateSingalView.setAdapter(new SingleItemAdapter(singalItem));

            }
        }

        private void setView(View itemView){
            this.itemView=itemView;
        }
        public View getItemView(){
            return itemView;
        }

    }

    public void setData(List<BaseFindViewModel> data){
        title = new ArrayList<>();
        followCard = new ArrayList<>();
        singalItem = new ArrayList<>();
        int count=0;
        boolean flag=true;
       for(BaseFindViewModel baseData : data){
           if(baseData.getClass() .equals(TitleViewModel.class))
               title.add((TitleViewModel) baseData);
           if(baseData.getClass().equals(FollowCardViewModel.class))
               followCard.add((FollowCardViewModel) baseData);
           if(baseData.getClass().equals(SingleTitleViewModel.class))
               break;
               count++;
       }

       SingalItemViewModel singalItemViewModel = new SingalItemViewModel();
       for(int i=count;i<data.size();i++){
           if(singalItemViewModel.getViewCards() != null && singalItemViewModel.getViewCards().size()==2){
               singalItem.add(singalItemViewModel);
               singalItemViewModel =new SingalItemViewModel();
           }

           if(data.get(i).getClass().equals(SingleTitleViewModel.class))
               singalItemViewModel.singalItemViewModel =(SingleTitleViewModel) data.get(i);
           else if(data.get(i).getClass().equals(FollowCardViewModel.class))
               singalItemViewModel.setFollowCardViewModel((FollowCardViewModel)data.get(i));
           else if(data.get(i).getClass().equals(VideoCardViewModel.class))
               singalItemViewModel.viewCards.add((VideoCardViewModel) data.get(i));
//           else
//               singalItem.add(singalItemViewModel);
       }

    }
}
