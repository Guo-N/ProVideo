package com.mfw.provideo.bean.viewmodel;


import com.mfw.homemodel.Service.BaseFindViewModel;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-12
 */
public class VideoCardViewModel extends BaseFindViewModel
{
    public String coverUrl;
    
    public int videoTime;
    
    public String title;
    
    public String description;

    public String authorUrl;

    public String video_description;

    public String userDescription;

    public String nickName;

    public String playerUrl;

    public String blurredUrl;

    public int videoId;

    // 点赞
    public int collectionCount;

    // 分享
    public int shareCount;


}
