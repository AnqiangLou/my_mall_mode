package com.android.xwtech.mallmode.http;

import android.util.Log;

import okhttp3.ResponseBody;

/**
 * Created by XW-Laq on 2017/7/23.
 */

public class ApiException extends Throwable {
    public ApiException(int code, ResponseBody errorBody) {
//        switch (code) {
//            case 401:
//                Converter<ResponseBody, ErrorModule> errorConverter =
//                        RxHelper.getInstance().getRetrofit().responseBodyConverter(ErrorModule.class, new Annotation[0]);
//                try {
//                    ErrorModule error = errorConverter.convert(errorBody);
//                    String errorMessage = getErrorInfo(error);
//                    if (errorMessage.contains("实名认证")) {
//                        AppToast.makeShortToast(AppContext.getInstance(), errorMessage);
//                    } else {
////                        Intent intent = new Intent(AppManager.getAppManager().currentActivity(), LoginActivity.class);
////                        AppManager.getAppManager().currentActivity().startActivity(intent);
//                        AppToast.makeShortToast(AppContext.getInstance(), "请先登录！");
////                        AppManager.getAppManager().currentActivity().finish();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
//            default:
//                break;
//        }
    }

    public ApiException(int code, String message) {
        Log.d("laq", "ApiException: "+code+"===="+message);
//        switch (code) {
//            case 401:
//                Converter<ResponseBody, ErrorModule> errorConverter =
//                        RxHelper.getInstance().getRetrofit().responseBodyConverter(ErrorModule.class, new Annotation[0]);
//                try {
//                    ErrorModule error = errorConverter.convert(errorBody);
//                    String errorMessage = getErrorInfo(error);
//                    if (errorMessage.contains("实名认证")) {
//                        AppToast.makeShortToast(AppContext.getInstance(), errorMessage);
//                    } else {
////                        Intent intent = new Intent(AppManager.getAppManager().currentActivity(), LoginActivity.class);
////                        AppManager.getAppManager().currentActivity().startActivity(intent);
//                        AppToast.makeShortToast(AppContext.getInstance(), "请先登录！");
////                        AppManager.getAppManager().currentActivity().finish();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
//            default:
//                break;
//        }
    }

}
