package com.mfw.notifymodule.notice.viewmodel;


import com.mfw.notifymodule.notice.Service.BaseFindViewModel;

import java.util.List;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 * @author darryrzhoong
 * @since 2020-02-23
 */
public class TabInfo extends BaseFindViewModel {

    /**
     * tabInfo : {"tabList":[{"id":-1,"name":"推荐","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/0?isRecTab=true","tabType":0,"nameType":0,"adTrack":null},{"id":1,"name":"创作","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/1","tabType":0,"nameType":0,"adTrack":null},{"id":3,"name":"书影音","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/3","tabType":0,"nameType":0,"adTrack":null},{"id":6,"name":"旅行","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/6","tabType":0,"nameType":0,"adTrack":null},{"id":8,"name":"眼友你好","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/8","tabType":0,"nameType":0,"adTrack":null},{"id":4,"name":"兴趣","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/4","tabType":0,"nameType":0,"adTrack":null},{"id":5,"name":"吃喝指南","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/5","tabType":0,"nameType":0,"adTrack":null},{"id":9,"name":"毕业季","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/9","tabType":0,"nameType":0,"adTrack":null}],"defaultIdx":0}
     */

    private TabInfoBean tabInfo;

    public TabInfoBean getTabInfo() {
        return tabInfo;
    }

    public void setTabInfo(TabInfoBean tabInfo) {
        this.tabInfo = tabInfo;
    }

    public static class TabInfoBean {
        /**
         * tabList : [{"id":-1,"name":"推荐","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/0?isRecTab=true","tabType":0,"nameType":0,"adTrack":null},{"id":1,"name":"创作","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/1","tabType":0,"nameType":0,"adTrack":null},{"id":3,"name":"书影音","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/3","tabType":0,"nameType":0,"adTrack":null},{"id":6,"name":"旅行","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/6","tabType":0,"nameType":0,"adTrack":null},{"id":8,"name":"眼友你好","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/8","tabType":0,"nameType":0,"adTrack":null},{"id":4,"name":"兴趣","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/4","tabType":0,"nameType":0,"adTrack":null},{"id":5,"name":"吃喝指南","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/5","tabType":0,"nameType":0,"adTrack":null},{"id":9,"name":"毕业季","apiUrl":"http://baobab.kaiyanapp.com/api/v7/tag/childTab/9","tabType":0,"nameType":0,"adTrack":null}]
         * defaultIdx : 0
         */

        private int defaultIdx;
        private List<Tabs> tabList;

        public int getDefaultIdx() {
            return defaultIdx;
        }

        public void setDefaultIdx(int defaultIdx) {
            this.defaultIdx = defaultIdx;
        }

        public List<Tabs> getTabList() {
            return tabList;
        }

        public void setTabList(List<Tabs> tabList) {
            this.tabList = tabList;
        }

    }
}
