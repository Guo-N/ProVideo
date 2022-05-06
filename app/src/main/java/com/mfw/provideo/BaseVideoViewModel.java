package com.mfw.provideo;


import android.util.Log;

import com.mfw.homemodel.Service.BaseFindViewModel;
import com.mfw.homemodel.Service.BaseViewModel;
import com.mfw.provideo.Utils.GsonUtils;
import com.mfw.provideo.bean.LeftAlignTextHeader;
import com.mfw.provideo.bean.ReplyBean;
import com.mfw.provideo.bean.TextCard;
import com.mfw.provideo.bean.VideoSmallCard;
import com.mfw.provideo.bean.viewmodel.ReplyViewModel;
import com.mfw.provideo.bean.viewmodel.VideoCardViewModel;
import com.mfw.provideo.bean.viewmodel.VideoTextViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class BaseVideoViewModel extends BaseViewModel {

//    private Class aClass;

    /**
     * 相关推荐
     */
    public static final String NOMINATE_URL =
            "https://baobab.kaiyanapp.com/api/v4/video/related";

    /**
     * 热门评论
     */
    public static final String REPLY_URL =
            "https://baobab.kaiyanapp.com/api/v2/replies/video";

    public int videoId = 186856;

    private List<BaseFindViewModel> viewModels = new ArrayList<>();



    public void loadNominate(Class clz){
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder httpBuilder = HttpUrl.parse(NOMINATE_URL).newBuilder();
        httpBuilder.addQueryParameter("id", String.valueOf(videoId));

        Request nominate = new Request.Builder()
                .get()
                .url(httpBuilder.build()).build();

        Call call = client.newCall(nominate);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                LoadFailer(aClass);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                NominateloadSuccessful(body.string());
            }
        });
    }

    public void loadReplay(Class clz){
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder httpBuilder = HttpUrl.parse(REPLY_URL).newBuilder();
        httpBuilder.addQueryParameter("videoId", String.valueOf(videoId));

        Request reply = new Request.Builder()
                .get()
                .url(REPLY_URL).build();
        Call call = client.newCall(reply);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                LoadFailer(aClass);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                ReplyloadSuccessful(body.string());
            }
        });
    }

    public void NominateloadSuccessful(String s){
        Log.e("GY-nominate",s);
        parseJsonNominate(s);
    }
    public void ReplyloadSuccessful(String s){
        Log.e("GY-Reply",s);
        parseJsonNominate(s);

    }


    private List<BaseFindViewModel> parseJsonNominate(String nominateData)
    {
        parseNominateData(viewModels, nominateData);
        return viewModels;
    }
    private List<BaseFindViewModel> parseJsonReply(String replyData)
    {
        parseReplyData(viewModels, replyData);
        return viewModels;
    }

    private void parseNominateData(List<BaseFindViewModel> viewModels,
                                   String nominateData)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(nominateData);
            JSONArray itemList = jsonObject.optJSONArray("itemList");
            if (itemList != null)
            {
                for (int i = 0; i < itemList.length(); i++)
                {
                    JSONObject ccurrentObject = itemList.getJSONObject(i);
                    switch (ccurrentObject.optString("type"))
                    {
                        case "textCard":
                            TextCard textCard = GsonUtils.fromLocalJson(
                                    ccurrentObject.toString(),
                                    TextCard.class);
                            VideoTextViewModel textViewModel =
                                    new VideoTextViewModel();
                            textViewModel.setTextTitle(textCard.getData().getText());
                            viewModels.add(textViewModel);
                            break;
                        case "videoSmallCard":
                            VideoSmallCard videoSmallCard = GsonUtils
                                    .fromLocalJson(ccurrentObject.toString(),
                                            VideoSmallCard.class);
                            paresVideoCard(viewModels, videoSmallCard);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void parseReplyData(List<BaseFindViewModel> viewModels,
                                String replyData)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(replyData);
            JSONArray itemList = jsonObject.optJSONArray("itemList");
            if (itemList != null)
            {
                for (int i = 0; i < itemList.length(); i++)
                {
                    JSONObject ccurrentObject = itemList.getJSONObject(i);
                    switch (ccurrentObject.optString("type"))
                    {
                        case "leftAlignTextHeader":
                            LeftAlignTextHeader alignTextHeader = GsonUtils
                                    .fromLocalJson(ccurrentObject.toString(),
                                            LeftAlignTextHeader.class);
                            VideoTextViewModel textViewModel =
                                    new VideoTextViewModel();
                            textViewModel.setTextTitle(alignTextHeader.getData().getText());
                            viewModels.add(textViewModel);
                            break;
                        case "reply":
                            ReplyBean reply = GsonUtils.fromLocalJson(
                                    ccurrentObject.toString(),
                                    ReplyBean.class);
                            ReplyViewModel replyViewModel =
                                    new ReplyViewModel();
                            if (reply != null)
                            {
                                replyViewModel.avatar =
                                        reply.getData().getUser().getAvatar();
                                replyViewModel.nickName =
                                        reply.getData().getUser().getNickname();
                                replyViewModel.replyMessage =
                                        reply.getData().getMessage();
                                replyViewModel.releaseTime =
                                        reply.getData().getUser().getReleaseDate();
                                replyViewModel.likeCount =
                                        reply.getData().getLikeCount();
                                viewModels.add(replyViewModel);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void paresVideoCard(List<BaseFindViewModel> viewModels,
                                VideoSmallCard videoSmallCard)
    {
        if (videoSmallCard == null)
        {
            return;
        }
        VideoCardViewModel videoCardViewModel = new VideoCardViewModel();
        videoCardViewModel.coverUrl =
                videoSmallCard.getData().getCover().getDetail();
        videoCardViewModel.videoTime = videoSmallCard.getData().getDuration();
        videoCardViewModel.title = videoSmallCard.getData().getTitle();
        videoCardViewModel.description =
                videoSmallCard.getData().getAuthor().getName() + " / # "
                        + videoSmallCard.getData().getCategory();
        videoCardViewModel.authorUrl =
                videoSmallCard.getData().getAuthor().getIcon();
        videoCardViewModel.userDescription =
                videoSmallCard.getData().getAuthor().getDescription();
        videoCardViewModel.nickName =
                videoSmallCard.getData().getAuthor().getName();
        videoCardViewModel.video_description =
                videoSmallCard.getData().getDescription();
        videoCardViewModel.playerUrl = videoSmallCard.getData().getPlayUrl();
        videoCardViewModel.blurredUrl =
                videoSmallCard.getData().getCover().getBlurred();
        videoCardViewModel.videoId = videoSmallCard.getData().getId();
        videoCardViewModel.collectionCount =
                videoSmallCard.getData().getConsumption().getCollectionCount();
        videoCardViewModel.shareCount =
                videoSmallCard.getData().getConsumption().getShareCount();
        viewModels.add(videoCardViewModel);
    }


}
