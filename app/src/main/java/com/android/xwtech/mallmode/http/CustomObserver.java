package com.android.xwtech.mallmode.http;

import com.android.xwtech.mallmode.base.BaseView;
import com.android.xwtech.mallmode.model.HttpResult;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by XW-Laq on 2017/12/21.
 */

public abstract class CustomObserver<T> implements Observer<HttpResult<T>> {
    private BaseView mBaseView;
    private CompositeDisposable mCompositeDisposable;

    public CustomObserver(BaseView baseView, CompositeDisposable compositeDisposable) {
        mBaseView = baseView;
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mBaseView.showLoading();
        mCompositeDisposable.add(d);
    }

    @Override
    public void onNext(HttpResult<T> tHttpResult) {
        if (tHttpResult.getData() != null) {
//            if (tHttpResult.getData() instanceof List && !((List) tHttpResult.getData()).isEmpty()) {
//                onSuccess(tHttpResult.getData());
//            }else {
//                mBaseView.showEmpty();
//            }
            onSuccess(tHttpResult.getData());
        } else {
            mBaseView.showEmpty();
        }
    }

    protected abstract void onSuccess(T data);

    @Override
    public void onError(Throwable e) {
        //显示错误信息
        //show error
        mBaseView.showError();
    }

    @Override
    public void onComplete() {

    }
}
