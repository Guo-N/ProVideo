package com.mfw.provideo.nominate.viewHolder;

import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.nominate.viewModel.BaseNomiateViewModel;
import com.mfw.provideo.nominate.viewModel.FollowCardViewModel;
import com.mfw.provideo.viewmodel.SingleTitleViewModel;
import com.mfw.provideo.viewmodel.VideoCardViewModel;

import java.util.ArrayList;
import java.util.List;

public class SingalItemViewModel extends BaseNomiateViewModel {

   public SingleTitleViewModel singalItemViewModel;
   public  FollowCardViewModel followCardViewModel;
   public  List<VideoCardViewModel> viewCards = new ArrayList<>();

    public void setFollowCardViewModel(FollowCardViewModel followCardViewModel) {
        this.followCardViewModel = followCardViewModel;
    }

    public void setSingalItemViewModel(SingleTitleViewModel singleTitleViewModel) {
        this.singalItemViewModel = singleTitleViewModel;
    }

    public void setViewModel(List<VideoCardViewModel> viewCards) {
        this.viewCards = viewCards;
    }

    public FollowCardViewModel getFollowCardViewModel() {
        return followCardViewModel;
    }

    public List<VideoCardViewModel> getViewCards() {
        return viewCards;
    }

    public SingleTitleViewModel getSingalItemViewModel() {
        return singalItemViewModel;
    }
}
