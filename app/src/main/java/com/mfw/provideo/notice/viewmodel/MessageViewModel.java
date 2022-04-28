package com.mfw.provideo.notice.viewmodel;

import com.mfw.provideo.Service.BaseFindViewModel;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-23
 */
public class MessageViewModel extends BaseFindViewModel {
    public String coverUrl;
    public String messageDate;
    public String title;
    public String comtent;


    @Override
    public String toString() {
        return "MessageViewModel{" +
                "coverUrl='" + coverUrl + '\'' +
                ", messageDate='" + messageDate + '\'' +
                ", title='" + title + '\'' +
                ", comtent='" + comtent + '\'' +
                '}';
    }
}
