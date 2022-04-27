package com.mfw.provideo.community.bean;


import com.mfw.provideo.Service.BaseFindViewModel;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-19
 */
public class AttentionCardViewModel extends BaseFindViewModel
{
    public String avatarUrl;
    
    public String issuerName;
    
    public long releaseTime;
    
    public String title;
    
    public String description;
    
    public String coverUrl;

    public String blurredUrl;
    
    public String playUrl;
    
    public String category;
    
    public String authorDescription;

    public int videoId ;
    
    // 点赞
    public int collectionCount;
    
    // 分享
    public int shareCount;
    
    // 评论
    public int replyCount;
    
    // 收藏
    public int realCollectionCount;

    @Override
    public String toString() {
        return "AttentionCardViewModel{" +
                "avatarUrl='" + avatarUrl + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", releaseTime=" + releaseTime +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", blurredUrl='" + blurredUrl + '\'' +
                ", playUrl='" + playUrl + '\'' +
                ", category='" + category + '\'' +
                ", authorDescription='" + authorDescription + '\'' +
                ", videoId=" + videoId +
                ", collectionCount=" + collectionCount +
                ", shareCount=" + shareCount +
                ", replyCount=" + replyCount +
                ", realCollectionCount=" + realCollectionCount +
                '}';
    }
}
