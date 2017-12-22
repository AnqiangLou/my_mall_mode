package com.android.xwtech.mallmode.http;


/**
 * @author XW-Laq
 */
public class RxHelper extends RetrofitHelp{

    private ApiService mApiService;

    /**
     * 私有构造
     */
    private RxHelper() {

    }

    public static RxHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 获取APIService
     *
     * @return
     */
    public ApiService getApiService() {
        if (mApiService == null) {
            mApiService = getRetrofit().create(ApiService.class);
        }
        return mApiService;
    }


    /**
     * 内部类单例
     */
    private static class SingletonHolder {
        private final static RxHelper INSTANCE = new RxHelper();
    }

//    /**
//     * 刷新token失败，清除用户信息
//     */
//    private void clearLoginInfo() {
//        Log.d("laq", "authenticate:刷新失败 ");
//        PreferencesHelper.getInstance().clearUserInfo(mContext);
//        Hawk.delete("userData");
//        Hawk.delete("isVerify");
//        Hawk.delete("token");
//        Hawk.delete("user_avatar");
//        Hawk.delete("user_id");
//        Hawk.delete("nickname");
//    }

}
