package com.mfw.communitymodule.community.Adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
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
