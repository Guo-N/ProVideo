package com.mfw.commonmodule;

public class ActivityRouterInit {
    /**
     * main组件
     */
    public static class Main
    {
        private static final String MAIN = "/main";

        /** 主页面 */
        public static final String PAGER_MAIN = MAIN + "/Main";
    }

    /**
     * 视频播放(video)组件
     */
    public static class Video
    {
        private static final String VIDEO = "/video";

        /* 视频播放界面 */
        public static final String PAGER_VIDEO = VIDEO + "/Video";

    }

    public static class User
    {
        private static final String USER = "/user";

        /** 登录界面 */
        public static final String PAGER_LOGIN = USER + "/Login";

        /** 关注页面 */
        public static final String PAGER_ATTENTION = USER + "/attention";
    }
}
