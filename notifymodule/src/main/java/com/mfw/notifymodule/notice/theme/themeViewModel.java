package com.mfw.notifymodule.notice.theme;


import com.mfw.notifymodule.notice.Service.BaseFindViewModel;
import com.mfw.notifymodule.notice.Service.BaseViewModel;
import com.mfw.notifymodule.notice.Utils.GsonUtils;
import com.mfw.notifymodule.notice.viewmodel.TabInfo;
import com.mfw.notifymodule.notice.viewmodel.Tabs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class themeViewModel<T> extends BaseViewModel {
    private List<BaseFindViewModel> data;
    public static  String DEFAULT_URL = "https://baobab.kaiyanapp.com/api/v7/tag/tabList";
    private Class aClass;

    public void load(Class clz){
        aClass = clz;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(DEFAULT_URL).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LoadFailer(aClass);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                parseData(body.string());
            }
        });
    }

    public void loadmore(Class clz){

    }

    private void parseData(String s) {
        data=new ArrayList<>();
        TabInfo tabInfo = GsonUtils.fromLocalJson(s, TabInfo.class);
        if (tabInfo != null){
            for(Tabs tab:tabInfo.getTabInfo().getTabList()){
                data.add(tab);
            }
            LoadSuccessful(aClass,data);
        }
    }




}
