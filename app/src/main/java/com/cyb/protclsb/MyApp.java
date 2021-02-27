package com.cyb.protclsb;

import android.app.Application;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

/**
 * @Author 陈迎博
 * @Description
 * @Date 2021/2/27
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //覆盖原生样式，使用QMUI样式
        QMUISwipeBackActivityManager.init(this);
    }
}
