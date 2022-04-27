package com.mfw.provideo.Adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mfw.provideo.nominate.viewHolder.BannerViewHolder;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.constants.PageStyle;

import java.util.ArrayList;

public class ViewBindingAdapters {



    @BindingAdapter("imageUrl")
    public static void LoadImage(ImageView imageView, String bannerUrl){
        Glide.with(imageView.getContext())
                .load(bannerUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(40)))
                .into(imageView);
    }

    @BindingAdapter("initBannerView")
    public static void initBannerView(BannerViewPager bannerViewPager,
                                      ArrayList<String> list)
    {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add(
                "http://img.kaiyanapp.com/b5b00c67dfc759a02c8b457e104b3ec6.png?imageMogr2/quality/60/format/jpg");
        list1.add(
                "http://img.kaiyanapp.com/1eaf8827688ea3b910b7b6b6cb192a5f.png?imageMogr2/quality/60/format/jpg");
        list1.add(
                "http://img.kaiyanapp.com/b5b00c67dfc759a02c8b457e104b3ec6.png?imageMogr2/quality/60/format/jpg");
        list1.add(
                "http://img.kaiyanapp.com/1eaf8827688ea3b910b7b6b6cb192a5f.png?imageMogr2/quality/60/format/jpg");

        bannerViewPager.setHolderCreator(BannerViewHolder:: new)
                .setPageStyle(PageStyle.MULTI_PAGE_OVERLAP)
                .create(list1);
    }

    @BindingAdapter("imageCircleUrl")
    public static void loadCircleImage(ImageView imageView, String url)
    {
        if (!TextUtils.isEmpty(url))
        {
            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageView);
        }

    }
}
