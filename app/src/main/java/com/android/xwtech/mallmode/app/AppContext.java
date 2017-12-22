package com.android.xwtech.mallmode.app;

import android.app.Application;

import com.android.xwtech.mallmode.callback.EmptyCallback;
import com.android.xwtech.mallmode.callback.ErrorCallback;
import com.android.xwtech.mallmode.callback.GithubCallback;
import com.android.xwtech.mallmode.callback.LoadingCallback;
import com.android.xwtech.mallmode.http.RxHelper;
import com.kingja.loadsir.core.LoadSir;
import com.orhanobut.hawk.Hawk;

/**
 *
 * @author XW-Laq
 * @date 2017/12/15
 */

public class AppContext extends Application {

    private static AppContext sInstance;

    public static AppContext getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //初始化hawk
        Hawk.init(this).build();

        RxHelper.getInstance().getApiService();

        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new GithubCallback())
//                .addCallback(new TimeoutCallback())
//                .addCallback(new CustomCallback())
                //设置默认状态页
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
