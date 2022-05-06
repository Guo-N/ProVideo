package com.mfw.homemodel.nominate;

import androidx.databinding.ViewDataBinding;

import com.mfw.homemodel.Service.BaseFindViewModel;
import com.mfw.homemodel.Service.BaseViewModel;
import com.mfw.homemodel.Utils.GsonUtils;
import com.mfw.homemodel.nominate.viewModel.FollowCardViewModel;
import com.mfw.homemodel.viewmodel.Bean.FollowCardBean;
import com.mfw.homemodel.viewmodel.Bean.TextCardbean;
import com.mfw.homemodel.viewmodel.Bean.VideoSmallCardBean;
import com.mfw.homemodel.viewmodel.SingleTitleViewModel;
import com.mfw.homemodel.viewmodel.SquareCardCollectionBean;
import com.mfw.homemodel.viewmodel.TitleViewModel;
import com.mfw.homemodel.viewmodel.VideoCardViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NominateViewModel<T extends ViewDataBinding> extends BaseViewModel {

    public static final String DEFAULT_URL = "https://baobab.kaiyanapp.com/api/v5/index/tab/allRec";
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
                loadSuccessful(body.string());
            }
        });
    }


    public void loadmore(Class clz){

    }

    private void parseJson(String s)
    {
        List<BaseFindViewModel> viewModels = new ArrayList<>();
        try
        {
            JSONObject jsonObject = new JSONObject(s);
            nextPageUrl = jsonObject.optString("nextPageUrl", "");
            JSONArray itemList = jsonObject.optJSONArray("itemList");
            if (itemList != null)
            {
                for (int i = 0; i < itemList.length(); i++)
                {
                    JSONObject ccurrentObject = itemList.getJSONObject(i);
                    switch (ccurrentObject.optString("type"))
                    {
                        case "squareCardCollection":
                            SquareCardCollectionBean squareCardCollectionBean =
                                    GsonUtils.fromLocalJson(
                                            ccurrentObject.toString(),
                                            SquareCardCollectionBean.class);
                            paresCollectionCard(viewModels,
                                    squareCardCollectionBean);
                            break;
                        case "textCard":
                            TextCardbean textCardBean = GsonUtils.fromLocalJson(
                                    ccurrentObject.toString(),
                                    TextCardbean.class);
                            SingleTitleViewModel viewModel =
                                    new SingleTitleViewModel();
                            viewModel.title = textCardBean.getData().getText();
                            viewModels.add(viewModel);
                            break;
                        case "videoSmallCard":
                            VideoSmallCardBean videoSmallCardBean = GsonUtils
                                    .fromLocalJson(ccurrentObject.toString(),
                                            VideoSmallCardBean.class);
                            paresVideoCard(viewModels, videoSmallCardBean);
                            break;
                        case "followCard":
                            FollowCardBean followCardBean = GsonUtils
                                    .fromLocalJson(ccurrentObject.toString(),
                                            FollowCardBean.class);
                            paresFollowCard(viewModels, followCardBean);
                            break;
                        default:
                            break;

                    }
                }
            }

            LoadSuccessful(aClass,viewModels);
//            list.get(0).LoadFinish(viewModels,true);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }


    private void paresCollectionCard(List<BaseFindViewModel> viewModels,
                                     SquareCardCollectionBean squareCardCollectionBean)
    {
        TitleViewModel titleLeftAndRightViewModel = new TitleViewModel();
        titleLeftAndRightViewModel.title =
                squareCardCollectionBean.getData().getHeader().getTitle();
        titleLeftAndRightViewModel.actionTitle =
                squareCardCollectionBean.getData().getHeader().getRightText();
        viewModels.add(titleLeftAndRightViewModel);
        // 解析精选视频
        for (int i1 = 0; i1 < squareCardCollectionBean.getData()
                .getItemList()
                .size(); i1++)
        {
            paresFollowCard(viewModels,
                    squareCardCollectionBean.getData().getItemList().get(i1));
        }
    }

    private void paresFollowCard(List<BaseFindViewModel> viewModelList,
                                 FollowCardBean cardBean)
    {
        FollowCardViewModel followCardViewModel = new FollowCardViewModel();
        followCardViewModel.coverUrl =
                cardBean.getData().getContent().getData().getCover().getDetail();
        followCardViewModel.videoTime =
                cardBean.getData().getContent().getData().getDuration();
        followCardViewModel.authorUrl =
                cardBean.getData().getContent().getData().getAuthor().getIcon();
        followCardViewModel.description =
                cardBean.getData().getContent().getData().getAuthor().getName()
                        + " / #"
                        + cardBean.getData().getContent().getData().getCategory();
        followCardViewModel.title =
                cardBean.getData().getContent().getData().getTitle();
        followCardViewModel.nickName = cardBean.getData().getContent().getData().getAuthor().getName();
        followCardViewModel.video_description = cardBean.getData().getContent().getData().getDescription();
        followCardViewModel.userDescription = cardBean.getData().getContent().getData().getAuthor().getDescription();
        followCardViewModel.playerUrl = cardBean.getData().getContent().getData().getPlayUrl();
        followCardViewModel.blurredUrl = cardBean.getData().getContent().getData().getCover().getBlurred();
        followCardViewModel.videoId = cardBean.getData().getContent().getData().getId();
        viewModelList.add(followCardViewModel);
    }

    private void paresVideoCard(List<BaseFindViewModel> viewModels,
                                VideoSmallCardBean videoSmallCardBean)
    {
        VideoCardViewModel videoCardViewModel = new VideoCardViewModel();
        videoCardViewModel.coverUrl =
                videoSmallCardBean.getData().getCover().getDetail();
        videoCardViewModel.videoTime =
                videoSmallCardBean.getData().getDuration();
        videoCardViewModel.title = videoSmallCardBean.getData().getTitle();
        videoCardViewModel.description =
                videoSmallCardBean.getData().getAuthor().getName() + " / # "
                        + videoSmallCardBean.getData().getCategory();
        videoCardViewModel.authorUrl = videoSmallCardBean.getData().getAuthor().getIcon();
        videoCardViewModel.userDescription = videoSmallCardBean.getData().getAuthor().getDescription();
        videoCardViewModel.nickName = videoSmallCardBean.getData().getAuthor().getName();
        videoCardViewModel.video_description = videoSmallCardBean.getData().getDescription();
        videoCardViewModel.playerUrl = videoSmallCardBean.getData().getPlayUrl();
        videoCardViewModel.blurredUrl = videoSmallCardBean.getData().getCover().getBlurred();
        videoCardViewModel.videoId = videoSmallCardBean.getData().getId();
        videoCardViewModel.collectionCount = videoSmallCardBean.getData().getConsumption().getCollectionCount();
        videoCardViewModel.shareCount = videoSmallCardBean.getData().getConsumption().getShareCount();
        viewModels.add(videoCardViewModel);
    }


    public void loadSuccessful(String s){
        parseJson(s);
    }

}
