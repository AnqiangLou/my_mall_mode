package com.android.xwtech.mallmode.http;

import android.content.Context;
import android.util.Log;

import com.android.xwtech.mallmode.app.AppContext;
import com.android.xwtech.mallmode.app.Constants;
import com.android.xwtech.mallmode.util.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author XW-Laq
 * @date 2017/12/18
 */

public class RetrofitHelp {

    //服务器地址
//    private static final String API_HOST = Constant.TEST_IP;
    private static final String API_HOST = Constants.RELEASE_IP;
    private Context mContext = AppContext.getInstance();
    /**
     * 缓存拦截器
     */
    private Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            HttpUrl.Builder builder = request.url()
                    .newBuilder();
//                    .addQueryParameter("region_id", String.valueOf(AppContext.defaultRegionId));

            if (!NetUtil.isNetConnected(mContext)) {
                request = request.newBuilder()
                        .addHeader("Accept", "application/vnd.WedealerAPI.f.v1+json")
                        .url(builder.build())
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            } else {
                request = request.newBuilder()
                        .addHeader("Accept", "application/vnd.WedealerAPI.f.v1+json")
                        .url(builder.build())
                        .build();
            }

            Response originalResponse = chain.proceed(request);

            if (NetUtil.isNetConnected(AppContext.getInstance())) {
                int maxAge = 0; // read from cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    /**
     * 获取Retrofit
     *
     * @return
     */
    protected Retrofit getRetrofit() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initOkHttp())
                .build();

        return mRetrofit;
    }

    /**
     * 初始化okhttp
     *
     * @return
     */
    private OkHttpClient initOkHttp() {
        //日志设置
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存设置
        File httpCacheDirectory = new File(mContext.getCacheDir(), "responses");
        //10MB
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                //cookies设置
                .cookieJar(new CookieJar() {
                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url.host(), cookies);
                        Log.d("laq", "saveFromResponse: " + cookies.toString());
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                //token自动刷新
//                .authenticator(new Authenticator() {
//                    private String newToken;
//
//                    @Override
//                    public Request authenticate(Route route, Response response) throws IOException {
//                        ErrorModule error;
//                        try {
//                            error = new Gson().fromJson(response.body().string(), ErrorModule.class);
//                            String errorInfo = Util.getErrorInfo(error);
//                            Log.d("laq", "authenticator_errorInfo: " + errorInfo);
//                            if (errorInfo.contains("实名认证")) {
//                                //未实名认证
//                                AppToast.makeNotUiToast(AppContext.getInstance(), R.string.unverify);
//                            } else if (errorInfo.contains("Unauthorized")) {
//                                //未登录
////                                Intent intent = new Intent(mContext, LoginActivity.class);
////                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//必须添加
////                                mContext.startActivity(intent);
//                                AppToast.makeNotUiToast(AppContext.getInstance(), R.string.login_pls);
//                            } else {
//                                //token过期
//                                String oldToken = AppContext.getToken();
//                                Log.d("laq", "authenticate_oldToken: " + oldToken);
//                                Call<BackInfo> call = RxHelper.getInstance().getAPIService().refreshToken(oldToken);
//                                retrofit2.Response<BackInfo> backInfoResponse = call.execute();
//                                if (backInfoResponse.isSuccess()) {
//                                    //code200
//                                    newToken = "Bearer " + backInfoResponse.body().getToken();
//                                    Hawk.put("token", newToken);
//                                    Log.d("laq", "authenticate_newToken: " + newToken);
//                                    return response.request().newBuilder().header("Authorization", newToken).build();
//                                } else {
//                                    //code500
//                                    clearLoginInfo();
//                                }
//                            }
//                        } catch (IOException e) {
//                            Log.d("laq", "authenticate_IOException: " + e);
//                            e.printStackTrace();
//                        }
//                        return null;
//                    }
//                })
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        return client;
    }
}
