package com.mfw.homemodel.viewmodel;


import com.mfw.homemodel.Service.BaseFindViewModel;

public class BannerViewModel extends BaseFindViewModel {
    String bannerUrl;

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

}
