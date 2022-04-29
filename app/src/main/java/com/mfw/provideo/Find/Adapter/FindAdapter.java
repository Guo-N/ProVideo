package com.mfw.provideo.Find.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mfw.provideo.R;
import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.databinding.HomeItemBannerViewBinding;
import com.mfw.provideo.databinding.HomeItemBriefCardViewBinding;
import com.mfw.provideo.databinding.HomeItemCatatoryCardViewBinding;
import com.mfw.provideo.databinding.HomeItemSubjectCardViewBinding;
import com.mfw.provideo.databinding.HomeItemVideoCardViewBinding;
import com.mfw.provideo.viewmodel.BannerViewModel;
import com.mfw.provideo.viewmodel.Bean.CategoryCardBean;
import com.mfw.provideo.viewmodel.Bean.SubjectCardBean;
import com.mfw.provideo.viewmodel.BriefCardViewModel;
import com.mfw.provideo.viewmodel.TitleViewModel;
import com.mfw.provideo.viewmodel.VideoCardViewModel;

import java.util.ArrayList;
import java.util.List;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.MyViewHolder> {

    private List<BaseFindViewModel> data;
    //load数据时，video直接存入list中没统一封装，所以第一次传值时在这里进行封装
    private boolean isFirst = true;
    private int[] find = {R.layout.home_item_banner_view,R.layout.home_item_catatory_card_view,R.layout.home_item_subject_card_view,R.layout.home_item_video_card_view,R.layout.home_item_brief_card_view};
    private ViewDataBinding[] bindings=new ViewDataBinding[find.length];
    private List<VideoCardViewModel> videoData;
    private List<BriefCardViewModel> briefCardData;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        MyViewHolder holder = null;
        view = LayoutInflater.from(parent.getContext()).inflate(find[viewType],parent,false);
        bindings[viewType] = DataBindingUtil.bind(view);
        view.setTag(viewType+"");
        holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setNewLayout(holder.getItemView());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return find.length;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            setView(itemView);
        }

        public void setNewLayout(View itemView){
            String viewType = (String) itemView.getTag();
            switch (viewType){
                case "0":
                    initBanner(itemView);
                case "1":
                    initcatatoryCard(itemView);
                case "2":
                    initobjectCard(itemView);
                case "3":
                    initvideoCard(itemView);
                case "4":
                    initbriefCard(itemView);
            }
        }
        private void setView(View itemView){
            this.itemView=itemView;
        }
        public View getItemView(){
            return itemView;
        }

    }

    public void initBanner(View view) {
    if(data !=null && data.size()!=0){
        BannerViewModel bannerViewModel = (BannerViewModel) data.get(0);
        HomeItemBannerViewBinding binding =(HomeItemBannerViewBinding)bindings[0];
        binding.setViewModel(bannerViewModel);
   }
}

    public void initcatatoryCard(View view){
        if(data !=null && data.size()!=0) {
            CategoryCardBean categoryCardBean = (CategoryCardBean) data.get(1);
            HomeItemCatatoryCardViewBinding binding = (HomeItemCatatoryCardViewBinding) bindings[1];
            binding.tvTitle.setText(categoryCardBean.getData().getHeader().getTitle());
            binding.tvActionTitle.setText(categoryCardBean.getData().getHeader().getRightText());
            GridLayoutManager layoutManager =  new GridLayoutManager(view.getContext(), 2);
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            binding.rvCategoryView.setLayoutManager(layoutManager);
            binding.rvCategoryView.setAdapter(new CatagoryCardAdapter(categoryCardBean.getData().getItemList()));
        }
    }

    public void initobjectCard(View view){
        if(data !=null && data.size()!=0) {
            SubjectCardBean subjectCardBean = (SubjectCardBean)data.get(2);
            HomeItemSubjectCardViewBinding binding = (HomeItemSubjectCardViewBinding) bindings[2];
            binding.tvTitle.setText(subjectCardBean.getData().getHeader().getTitle());
            binding.tvActionTitle.setText(subjectCardBean.getData().getHeader().getRightText());
            GridLayoutManager layoutManager =  new GridLayoutManager(view.getContext(), 2);
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            binding.rvCategoryView.setLayoutManager(layoutManager);
            binding.rvCategoryView.setAdapter(new SubjectCardAdapter(subjectCardBean.getData().getItemList()));
        }
    }

    public void initvideoCard(View view){
        if(data !=null && data.size()!=0) {
            TitleViewModel titleViewModel = (TitleViewModel)data.get(3);
            HomeItemVideoCardViewBinding binding=(HomeItemVideoCardViewBinding)bindings[3];
            if(binding != null){
                binding.tvTitle.setText(titleViewModel.title);
            binding.tvActionTitle.setText(titleViewModel.actionTitle);
            binding.rvVideoView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
            binding.rvVideoView.setAdapter(new VideoAdapter(videoData));
            }
        }
    }

    public void initbriefCard(View view) {
        if (data != null && data.size() != 0) {
            TitleViewModel titleViewModel = (TitleViewModel) data.get(9);
            HomeItemBriefCardViewBinding binding = (HomeItemBriefCardViewBinding) bindings[4];
            if (binding != null) {
                binding.tvTitle.setText(titleViewModel.title);
                binding.tvActionTitle.setText(titleViewModel.actionTitle);
                binding.rvBriefCardView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
                binding.rvBriefCardView.setAdapter(new BrifCardAdapter(briefCardData));
            }
        }

    }
public void setData(List data){
        this.data=data;
        initData();

}


  public void initData(){
        if(isFirst){
            if(videoData!=null && videoData.size()!=0)
                videoData.clear();
            if(briefCardData!=null && briefCardData.size()!=0)
                briefCardData.clear();
            videoData = new ArrayList<>();
            briefCardData = new ArrayList<>();
            for(BaseFindViewModel data:data){
                if(data.getClass().equals(VideoCardViewModel.class))
                    videoData.add((VideoCardViewModel)data);
                else if(data.getClass().equals(BriefCardViewModel.class))
                    briefCardData.add((BriefCardViewModel)data);

            }
            isFirst=false;
        }
  }



}
