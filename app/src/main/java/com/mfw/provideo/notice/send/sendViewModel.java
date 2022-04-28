package com.mfw.provideo.notice.send;

import android.util.Log;

import androidx.annotation.Nullable;

import com.mfw.provideo.R;
import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.Service.BaseViewModel;
import com.mfw.provideo.Utils.DateTimeUtils;
import com.mfw.provideo.Utils.GsonUtils;
import com.mfw.provideo.community.bean.AttentionCardBean;
import com.mfw.provideo.community.bean.AttentionCardViewModel;
import com.mfw.provideo.notice.viewmodel.Message;
import com.mfw.provideo.notice.viewmodel.MessageViewModel;
import com.mfw.provideo.notice.viewmodel.ThemesItemViewModel;
import com.mfw.provideo.notice.viewmodel.TopicBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;

public class sendViewModel<T> extends BaseViewModel{
    public static final String DEFAULT_URL = "https://baobab.kaiyanapp.com/api/v3/messages";
    private Class aClass;

    public void load(Class clz){
        aClass = clz;

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(DEFAULT_URL).newBuilder();
        builder.addQueryParameter("vc", "591");
        builder.addQueryParameter("deviceModel", "Che1-CL20");
        Request request = new Request.Builder()
                .get()
                .url(builder.build())
                .build();
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

    private void parseData(String s)
    {
        List<BaseFindViewModel> viewModels = new ArrayList<>();
        Message message = GsonUtils.fromLocalJson(s,Message.class);
        if (message != null){
            nextPageUrl = message.getNextPageUrl();
            for (Message.MessageListBean itemBean : message.getMessageList()){
                MessageViewModel viewModel = new MessageViewModel();
                viewModel.coverUrl = itemBean.getIcon();
                viewModel.title = itemBean.getTitle();
                viewModel.comtent = itemBean.getContent();
                viewModel.messageDate = formatDate(itemBean.getDate());
                viewModels.add(viewModel);
            }
        }
        LoadSuccessful(aClass,viewModels);
    }

    private String formatDate(long date){
        return DateTimeUtils.getDate(String.valueOf(date),"HH");

    }

}
