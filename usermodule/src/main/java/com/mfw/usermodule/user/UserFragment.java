package com.mfw.usermodule.user;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.mfw.commonmodule.FragmentRouterInit;
import com.mfw.usermodule.R;
import com.mfw.usermodule.databinding.FragmentUserBinding;
import com.mfw.usermodule.user.Service.BaseFindViewModel;

import java.util.ArrayList;
import java.util.List;



@Route( path= FragmentRouterInit.User.PAGER_USER)
public class UserFragment extends BaseFragment<FragmentUserBinding, UserViewModel> {

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
            viewModel = new UserViewModel();
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
            viewModel = new UserViewModel();
//        viewModel.load(clz);
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
