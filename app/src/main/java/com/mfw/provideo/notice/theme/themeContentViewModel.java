package com.mfw.provideo.notice.theme;

import android.util.Log;

import com.mfw.provideo.notice.viewmodel.ThemesContent;
import com.mfw.provideo.notice.viewmodel.ThemesItemViewModel;
import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.Service.BaseViewModel;
import com.mfw.provideo.Utils.GsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class themeContentViewModel<T> extends BaseViewModel{
    private List<BaseFindViewModel> data;
    private Class aClass;
    private String name="";
    private String url = "";

    public void load(Class clz){
        if(url != ""){
            if(!url.contains("https"))
            url = url.replace("http","https");
            aClass = clz;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .get()
                    .url(url).build();
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
        }else{
            parseData("ERROR 404");
        }

    }

    public void loadmore(Class clz){

    }

    private void parseData(String s)
    {
        if(s=="ERROR 404"){
            LoadFailer(aClass);
        }
        ArrayList<BaseFindViewModel> viewModels = new ArrayList<>();
        ThemesContent themesContent =
                GsonUtils.fromLocalJson(s, ThemesContent.class);
        if (themesContent != null)
        {
            nextPageUrl = themesContent.getNextPageUrl();
            for (ThemesContent.ItemListBean itemListBean : themesContent
                    .getItemList())
            {
                ThemesItemViewModel itemViewModel = new ThemesItemViewModel();
                itemViewModel.coverUrl = itemListBean.getData().getIcon();
                itemViewModel.title = itemListBean.getData().getTitle();
                itemViewModel.description =
                        itemListBean.getData().getDescription();
                viewModels.add(itemViewModel);
            }
        }
        LoadSuccessful(aClass,viewModels);
    }

public void initModel(String name,String url){
        this.name=name;
        this.url=url;
}


}
