package com.mfw.mainmodule;

import com.alibaba.android.arouter.launcher.ARouter;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(Application.this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
