package com.mfw.homemodel.viewmodel;



/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-12
 */
public class TitleViewModel extends BaseNomiateViewModel
{
    public String title;
    
    public String actionTitle;

    public String getActionTitle() {
        return actionTitle;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "TitleViewModel{" +
                "title='" + title + '\'' +
                ", actionTitle='" + actionTitle + '\'' +
                '}';
    }
}
