package com.mfw.homemodel.nominate.viewHolder;

import android.view.View;
import android.widget.ImageView;

import com.mfw.homemodel.Find.Adapter.ViewBindingAdapters;
import com.mfw.homemodel.R;
import com.zhpan.bannerview.holder.ViewHolder;

public class BannerViewHolder implements ViewHolder {
    @Override
    public int getLayoutId() {
        return R.layout.nominate_banner_view_item;
    }


    @Override
    public void onBind(View itemView, Object  data, int position, int size) {
        ImageView imageView = itemView.findViewById(R.id.banner_bg);
        ViewBindingAdapters.LoadImage(imageView,(String) data);
    }
}
