package com.mfw.provideo.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mfw.provideo.Service.BaseFindViewModel;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.PageStyle;

import java.util.ArrayList;

public class BannerViewModel extends BaseFindViewModel {
    String bannerUrl;

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

}
