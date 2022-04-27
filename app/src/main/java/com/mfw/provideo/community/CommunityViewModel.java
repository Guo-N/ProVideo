package com.mfw.provideo.community;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.mfw.provideo.Service.BaseFindViewModel;
import com.mfw.provideo.Service.BaseViewModel;
import com.mfw.provideo.Utils.GsonUtils;
import com.mfw.provideo.community.viewmodel.CloumnsCardViewModel;
import com.mfw.provideo.community.viewmodel.CommunityColumnsCard;
import com.mfw.provideo.community.viewmodel.HorizontalScrollCard;
import com.mfw.provideo.nominate.viewModel.FollowCardViewModel;
import com.mfw.provideo.viewmodel.Bean.FollowCardBean;
import com.mfw.provideo.viewmodel.Bean.TextCardbean;
import com.mfw.provideo.viewmodel.Bean.VideoSmallCardBean;
import com.mfw.provideo.viewmodel.SingleTitleViewModel;
import com.mfw.provideo.viewmodel.SquareCardCollectionBean;
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

public class CommunityViewModel<T> extends BaseViewModel {

    public static final String DEFAULT_URL = "https://baobab.kaiyanapp.com/api/v7/community/tab/rec";
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
                        case "horizontalScrollCard":
                            HorizontalScrollCard scrollCard = GsonUtils
                                    .fromLocalJson(ccurrentObject.toString(),
                                            HorizontalScrollCard.class);
                            viewModels.add(scrollCard);
                            break;
                        case "communityColumnsCard":
                            CommunityColumnsCard communityColumnsCard =
                                    GsonUtils.fromLocalJson(
                                            ccurrentObject.toString(),
                                            CommunityColumnsCard.class);
                            parseCard(viewModels, communityColumnsCard);
                            break;
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

    private void parseCard(List<BaseFindViewModel> viewModels,
                           CommunityColumnsCard columnsCard)
    {
        CloumnsCardViewModel cardViewModel = new CloumnsCardViewModel();
        if (columnsCard != null)
        {
            cardViewModel.coverUrl = columnsCard.getData()
                    .getContent()
                    .getData()
                    .getCover()
                    .getFeed();
            cardViewModel.description =
                    columnsCard.getData().getContent().getData().getDescription();
            cardViewModel.nickName = columnsCard.getData()
                    .getContent()
                    .getData()
                    .getOwner()
                    .getNickname();
            cardViewModel.avatarUrl = columnsCard.getData()
                    .getContent()
                    .getData()
                    .getOwner()
                    .getAvatar();
            cardViewModel.collectionCount = columnsCard.getData()
                    .getContent()
                    .getData()
                    .getConsumption()
                    .getCollectionCount();
            cardViewModel.imgWidth =
                    columnsCard.getData().getContent().getData().getWidth();
            cardViewModel.imgHeight =
                    columnsCard.getData().getContent().getData().getHeight();
            viewModels.add(cardViewModel);
        }

    }

    public void loadSuccessful(String s){
        parseJson(s);
    }

}
