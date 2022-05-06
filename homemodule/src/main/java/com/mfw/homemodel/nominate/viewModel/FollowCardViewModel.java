package com.mfw.homemodel.nominate.viewModel;


/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-12
 */
public class FollowCardViewModel extends BaseNomiateViewModel
{

    public String coverUrl;
    
    public int videoTime;
    
    public String authorUrl;
    
    public String description;
    
    public String title;

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

    @Override
    public String toString() {
        return "FollowCardViewModel{" +
                "coverUrl='" + coverUrl + '\'' +
                ", videoTime=" + videoTime +
                ", authorUrl='" + authorUrl + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", video_description='" + video_description + '\'' +
                ", userDescription='" + userDescription + '\'' +
                ", nickName='" + nickName + '\'' +
                ", playerUrl='" + playerUrl + '\'' +
                ", blurredUrl='" + blurredUrl + '\'' +
                ", videoId=" + videoId +
                ", collectionCount=" + collectionCount +
                ", shareCount=" + shareCount +
                '}';
    }
}
