package com.android.xwtech.mallmode.ui;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.xwtech.mallmode.http.CustomObserver;
import com.android.xwtech.mallmode.http.RxHelper;
import com.android.xwtech.mallmode.http.RxManager;
import com.android.xwtech.mallmode.model.HttpResult;
import com.android.xwtech.mallmode.model.News;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author XW-Laq
 * @date 2017/12/19
 */

public class MainPresenter implements MainContract.Presenter {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private MainContract.View mView;


    @Override
    public void subscribe(@NonNull MainContract.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getNewsList() {
        Observable<HttpResult<List<News>>> observable = RxHelper.getInstance().getApiService().getNews(1, 10, 1);
        observable
                .compose(RxManager.<List<News>>rxSchedulerHelper())
                .subscribe(new CustomObserver<List<News>>(mView, mCompositeDisposable) {
                    @Override
                    protected void onSuccess(List<News> data) {
                        Log.d("laq", "onSuccess: ");
                        mView.showNewsList(data);
                    }
                });

    }
}
