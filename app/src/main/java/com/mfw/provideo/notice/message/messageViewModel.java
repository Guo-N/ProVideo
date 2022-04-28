package com.mfw.provideo.notice.message;

import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.Service.BaseViewModel;
import com.mfw.provideo.Utils.GsonUtils;
import com.mfw.provideo.community.bean.AttentionCardBean;
import com.mfw.provideo.community.bean.AttentionCardViewModel;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class messageViewModel<T> extends BaseViewModel{
    public static final String DEFAULT_URL = "https://baobab.kaiyanapp.com/api/v3/messages";
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

    private void parseData(String data) {

        AttentionCardBean attentionCardBean = GsonUtils.fromLocalJson(data,AttentionCardBean.class);
        ArrayList<BaseFindViewModel> viewModels = new ArrayList<>();
        nextPageUrl = attentionCardBean.getNextPageUrl();
        for (int i=0;i<attentionCardBean.getItemList().size();i++){
            AttentionCardBean.ItemListBean itemListBean = attentionCardBean.getItemList().get(i);
            AttentionCardViewModel cardViewModel = new AttentionCardViewModel();
            cardViewModel.avatarUrl = itemListBean.getData().getHeader().getIcon();
            cardViewModel.issuerName = itemListBean.getData().getHeader().getIssuerName();
            cardViewModel.releaseTime = itemListBean.getData().getHeader().getTime();
            cardViewModel.title = itemListBean.getData().getContent().getData().getTitle();
            cardViewModel.description = itemListBean.getData().getContent().getData().getDescription();
            cardViewModel.coverUrl = itemListBean.getData().getContent().getData().getCover().getFeed();
            cardViewModel.playUrl = itemListBean.getData().getContent().getData().getPlayUrl();
            cardViewModel.collectionCount = itemListBean.getData().getContent().getData().getConsumption().getCollectionCount();
            cardViewModel.replyCount = itemListBean.getData().getContent().getData().getConsumption().getReplyCount();
            cardViewModel.realCollectionCount = itemListBean.getData().getContent().getData().getConsumption().getRealCollectionCount();
            cardViewModel.shareCount = itemListBean.getData().getContent().getData().getConsumption().getShareCount();
            cardViewModel.category = itemListBean.getData().getContent().getData().getCategory();
            cardViewModel.authorDescription = itemListBean.getData().getContent().getData().getAuthor().getDescription();
            cardViewModel.blurredUrl = itemListBean.getData().getContent().getData().getCover().getBlurred();
            cardViewModel.videoId = itemListBean.getData().getContent().getData().getId();
            viewModels.add(cardViewModel);
        }
        LoadSuccessful(aClass,viewModels);
    }


}
