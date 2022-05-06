package com.mfw.homemodel.Daily.ViewModel;

import androidx.databinding.ViewDataBinding;

import com.mfw.homemodel.Daily.Bean.TextCardBean;
import com.mfw.homemodel.Service.BaseFindViewModel;
import com.mfw.homemodel.Service.BaseViewModel;
import com.mfw.homemodel.Utils.GsonUtils;
import com.mfw.homemodel.nominate.viewModel.FollowCardViewModel;
import com.mfw.homemodel.viewmodel.Bean.FollowCardBean;
import com.mfw.homemodel.viewmodel.SingleTitleViewModel;

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

public class DailyViewModel<T extends ViewDataBinding> extends BaseViewModel {
        public static final String DEFAULT_URL = "https://baobab.kaiyanapp.com/api/v5/index/tab/feed";
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
            JSONObject jsonObject = null;
            try
            {
                jsonObject = new JSONObject(s);
                nextPageUrl = jsonObject.optString("nextPageUrl", "");
                JSONArray itemList = jsonObject.optJSONArray("itemList");
                if (itemList != null)
                {
                    for (int i = 0; i < itemList.length(); i++)
                    {
                        JSONObject ccurrentObject = itemList.getJSONObject(i);
                        switch (ccurrentObject.optString("type"))
                        {
                            case "textCard":
                                TextCardBean textCardBean = GsonUtils.fromLocalJson(
                                        ccurrentObject.toString(),
                                        TextCardBean.class);
                                if (textCardBean.getData().getText().equals("今日社区精选")){
                                    break;
                                }
                                SingleTitleViewModel viewModel =
                                        new SingleTitleViewModel();
                                viewModel.title = textCardBean.getData().getText();
                                viewModels.add(viewModel);
                                break;
                            case "followCard":
                                FollowCardBean followCardBean = GsonUtils
                                        .fromLocalJson(ccurrentObject.toString(),
                                                FollowCardBean.class);
                                paresFollowCard(viewModels, followCardBean);
                            default:
                                break;
                        }
                    }
                    LoadSuccessful(aClass,viewModels);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }

        }


        private void paresFollowCard(List<BaseFindViewModel> viewModels,
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
            followCardViewModel.video_description = cardBean.getData().getContent().getData().getDescription();
            followCardViewModel.userDescription = cardBean.getData().getContent().getData().getAuthor().getDescription();
            followCardViewModel.playerUrl = cardBean.getData().getContent().getData().getPlayUrl();
            followCardViewModel.blurredUrl = cardBean.getData().getContent().getData().getCover().getBlurred();
            followCardViewModel.videoId = cardBean.getData().getContent().getData().getId();
            viewModels.add(followCardViewModel);
        }





        public void loadSuccessful(String s){
            parseJson(s);
        }

    }

