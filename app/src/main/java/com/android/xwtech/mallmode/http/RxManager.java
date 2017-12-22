package com.android.xwtech.mallmode.http;

import com.android.xwtech.mallmode.model.HttpResult;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * @author XW-Laq
 * @date 2017/7/21
 */

public class RxManager {

    private RxManager() {

    }

    public static RxManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 统一线程处理 compose 操作符 简化线程
     *
     * @param <T>
     * @return
     */
//    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<T> upstream) {
//                return upstream.subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }

    public static <T> ObservableTransformer<HttpResult<T>, HttpResult<T>> rxSchedulerHelper() {
        return new ObservableTransformer<HttpResult<T>, HttpResult<T>>() {
            @Override
            public ObservableSource<HttpResult<T>> apply(Observable<HttpResult<T>> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 根据自己请求接口 判断接口是否调成功
     * compose操作符
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<HttpResult<T>, T> handleResult() {
        return new ObservableTransformer<HttpResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpResult<T>> upstream) {
                return upstream.flatMap(new Function<HttpResult<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(HttpResult<T> tHttpResult) throws Exception {
                        if (tHttpResult.getStatus_code() == 200) {
                            return createData(tHttpResult.getData());
                        } else {
                            return Observable.error(new ApiException(300, "111"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                emitter.onNext(t);
                emitter.onComplete();
            }
        });
    }

    /**
     * 倒计时操作
     *
     * @param time
     * @return
     */
    public static Observable<Integer> countDown(int time) {
        if (time < 0) {
            time = 0;
        }
        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) {
                        return countTime - aLong.intValue();
                    }
                })
//                .map(new Func1<Long, Integer>() {
//                    @Override
//                    public Integer call(Long aLong) {
//                        return countTime - aLong.intValue();
//                    }
//                })
                .take(time + 1);
    }

    /**
     * 延迟跳转界面 Rx的Timer操作符 可以设置周期次数
     *
     * @param time
     * @param consumer
     */
    public static void DelayJumpByTimer(int time, Consumer consumer) {
        Observable.timer(time, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    /**
     * 延迟跳转界面 Rx的Intercal 可以设置周期次数
     *
     * @param time
     * @param consumer
     */
    public static void DelayJumpByInterval(int time, Consumer consumer) {
        Observable.interval(time, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

//    public <T> void doSub(Observable<HttpResult<T>> observable, ProgressObserver<T> observer) {
//        observable
////                .compose(RxManager.<T>handleResult())
//                .compose(RxManager.<T>rxSchedulerHelper())
//                .subscribe(observer);
//    }



    /**
     * 单例
     */
    private static class SingletonHolder {
        private static final RxManager INSTANCE = new RxManager();
    }
}
