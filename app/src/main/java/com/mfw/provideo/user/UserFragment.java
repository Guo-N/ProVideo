package com.mfw.provideo.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.mfw.provideo.Find.HomeViewModel;
import com.mfw.provideo.Fragment.BaseFragment;
import com.mfw.provideo.R;
import com.mfw.provideo.databinding.FragmentUserBinding;
import com.mfw.provideo.notice.send.sendViewModel;

import java.util.ArrayList;
import java.util.List;


public class UserFragment extends BaseFragment<FragmentUserBinding, HomeViewModel> {

    private userFragmentAdapter adapter;
    private Class clz;

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    public void initFragment() {
        if(viewModel == null)
            viewModel = new HomeViewModel();
    }

    private void start(Context context){
//        startActivity(new Intent(context,LoginActivity.class));
        Toast.makeText(getContext(), "Jumping To Login", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initView() {
        Glide.with(getContext())
                .load(getContext().getDrawable(R.drawable.avatar))
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(binding.ivAvatar);
        binding.rvTables.setHasFixedSize(true);
        binding.rvTables
                .setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new userFragmentAdapter();
        initData();
//        adapter.setFooterView(getFooterView());
        binding.rvTables.setAdapter(adapter);
        binding.ivMore.setOnClickListener(v -> {start(getContext());});
        binding.tvLike.setOnClickListener(v -> {start(getContext());});
        binding.tvReply.setOnClickListener(v -> {start(getContext());});
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(clz == null)
            clz = getClass();
        if(viewModel == null)
            viewModel = new HomeViewModel();
        viewModel.load(clz);
    }

    private void initData()
    {
        List<String> items = new ArrayList<>();
        items.add("我的关注");
        items.add("我的收藏");
        items.add("视频功能声明");
        items.add("用户协议");
        items.add("版权声明");
        items.add("关于作者");
        adapter.setData(items);
    }
}
