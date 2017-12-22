package com.android.xwtech.mallmode.util;

import android.util.Log;

/**
 * Created by XW-Laq on 2017/8/15.
 */

public class NoFastClickUtil {
    private static int spaceTime = 500;//时间间隔

    private static long lastClickTime = 0;//上次点击的时间

    public static boolean allowClick() {

        long currentTime = System.currentTimeMillis();//当前系统时间

        boolean isAllowClick;//是否允许点击

        if (currentTime - lastClickTime > spaceTime) {
            isAllowClick = true;
            Log.d("laq", "isFastClick: 正常点击！！");
        } else {
            isAllowClick = false;
            Log.d("laq", "isFastClick: 点击太快！！");
        }

        lastClickTime = currentTime;

        return isAllowClick;
    }

}
