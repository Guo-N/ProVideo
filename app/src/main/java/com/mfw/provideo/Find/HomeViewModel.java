package com.mfw.provideo.Find;

import androidx.databinding.ViewDataBinding;

import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.Service.BaseViewModel;
import com.mfw.provideo.Utils.GsonUtils;
import com.mfw.provideo.viewmodel.BannerViewModel;
import com.mfw.provideo.viewmodel.Bean.BannerBean;
import com.mfw.provideo.viewmodel.Bean.BriefCard;
import com.mfw.provideo.viewmodel.Bean.CategoryCardBean;
import com.mfw.provideo.viewmodel.Bean.SubjectCardBean;
import com.mfw.provideo.viewmodel.Bean.TextCardbean;
import com.mfw.provideo.viewmodel.Bean.TopBannerBean;
import com.mfw.provideo.viewmodel.Bean.VideoSmallCardBean;
import com.mfw.provideo.viewmodel.BriefCardViewModel;
import com.mfw.provideo.viewmodel.ContentBannerViewModel;
import com.mfw.provideo.viewmodel.TitleViewModel;
import com.mfw.provideo.viewmodel.VideoCardViewModel;

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

public class HomeViewModel<T extends ViewDataBinding> extends BaseViewModel {

    public static final String DEFAULT_URL =
            "https://baobab.kaiyanapp.com/api/v7/index/tab/discovery?udid=fa53872206ed42e3857755c2756ab683fc22d64a&vc=591&vn=6.2.1&size=720X1280&deviceModel=Che1-CL20&first_channel=eyepetizer_zhihuiyun_market&last_channel=eyepetizer_zhihuiyun_market&system_version_code=19";
    private Class aClass ;

    public void load(Class clz){
        aClass=clz;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(DEFAULT_URL).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                list.get(0).LoadFinish(null,false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                loadSuccessful(body.string());
            }
        });
    }

    private void parseJson(String s)
    {
        List<BaseFindViewModel> viewModels = new ArrayList<>();
        try
        {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemList = jsonObject.optJSONArray("itemList");
            if (itemList != null)
            {
                for (int i = 0; i < itemList.length(); i++)
                {
                    JSONObject ccurrentObject = itemList.getJSONObject(i);
                    switch (ccurrentObject.optString("type"))
                    {
                        case "horizontalScrollCard":
                            TopBannerBean topBannerBean =(TopBannerBean) GsonUtils.fromLocalJson(ccurrentObject.toString(),TopBannerBean.class);
                            BannerViewModel topBannerViewModel = new BannerViewModel();
                            topBannerViewModel.setBannerUrl(topBannerBean.getData().getItemList().get(0).getData().getImage());
                            viewModels.add(topBannerViewModel);
                            break;
                        case "specialSquareCardCollection":
                            CategoryCardBean categoryCardBean =(CategoryCardBean) GsonUtils.fromLocalJson(ccurrentObject.toString(),CategoryCardBean.class);
                            viewModels.add(categoryCardBean);
                            break;
                        case "columnCardList":
                            SubjectCardBean subjectCardBean =(SubjectCardBean)GsonUtils.fromLocalJson(ccurrentObject.toString(),SubjectCardBean.class);
                            viewModels.add(subjectCardBean);
                            break;
                        case "textCard":
                            TextCardbean textCardbean = GsonUtils.fromLocalJson(ccurrentObject.toString(),TextCardbean.class);
                            TitleViewModel titleViewModel = new TitleViewModel();
                            titleViewModel.title = textCardbean.getData().getText();
                            titleViewModel.actionTitle = textCardbean.getData().getRightText();
                            viewModels.add(titleViewModel);
                            break;
                        case "banner":
                            BannerBean bannerBean = GsonUtils.fromLocalJson(ccurrentObject.toString(),BannerBean.class);
                            ContentBannerViewModel bannerViewModel = new ContentBannerViewModel();
                            bannerViewModel.bannerUrl = bannerBean.getData().getImage();
                            viewModels.add(bannerViewModel);
                            break;
                        case "videoSmallCard":
                            VideoSmallCardBean videoSmallCardBean = GsonUtils
                                    .fromLocalJson(ccurrentObject.toString(),
                                            VideoSmallCardBean.class);
                            paresVideoCard(viewModels, videoSmallCardBean);
                            break;
                        case "briefCard":
                            BriefCard briefCard = GsonUtils.fromLocalJson(ccurrentObject.toString(),BriefCard.class);
                            BriefCardViewModel briefCardViewModel = new BriefCardViewModel();
                            briefCardViewModel.coverUrl = briefCard.getData().getIcon();
                            briefCardViewModel.title = briefCard.getData().getTitle();
                            briefCardViewModel.description = briefCard.getData().getDescription();
                            viewModels.add(briefCardViewModel);
                            break;
                        default:
                            break;
                    }
                }
            }
//            list.get(0).LoadFinish(viewModels,true);
            LoadSuccessful(aClass,viewModels);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

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
