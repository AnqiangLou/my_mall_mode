package com.android.xwtech.mallmode.ui.news;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.xwtech.mallmode.R;
import com.android.xwtech.mallmode.base.BaseTitleActivity;
import com.android.xwtech.mallmode.callback.EmptyCallback;
import com.android.xwtech.mallmode.callback.ErrorCallback;
import com.android.xwtech.mallmode.callback.LoadingCallback;
import com.android.xwtech.mallmode.model.News;
import com.android.xwtech.mallmode.util.RecycleViewDivider;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author XW-Laq
 */
public class MainActivity extends BaseTitleActivity implements Callback.OnReloadListener, MainContract.View {
    @BindView(R.id.rv_news)
    RecyclerView mRvNews;
    private MainAdapter mMainAdapter;
    private LoadService mLoadService;
    private MainPresenter mMainPresenter;
//    private List<News> mNewsList;


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initData() {
        mMainPresenter = new MainPresenter();
        mMainPresenter.subscribe(this);
        mMainPresenter.getNewsList();
    }

    @Override
    protected void initViewAndListener() {
        mLoadService = LoadSir.getDefault().register(this);
        mMainAdapter = new MainAdapter(R.layout.item_news_list, null);
        View view = View.inflate(this, R.layout.layout_empty, null);
//        mMainAdapter.setEmptyView(view);
        mRvNews.setLayoutManager(new LinearLayoutManager(this));
        mRvNews.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL));
        mRvNews.setAdapter(mMainAdapter);
    }

    @Override
    protected void rightClickEvent() {

    }

    @Override
    protected void leftClickEvent() {

    }

    @Override
    protected String getContentTitle() {
        return "标题";
    }

    @Override
    protected int getChildContentId() {
        return R.layout.activity_main;
    }

    @Override
    public void showLoading() {
        mLoadService.showCallback(LoadingCallback.class);
    }

    @Override
    public void showError() {
        mLoadService.showCallback(ErrorCallback.class);
    }

    @Override
    public void showEmpty() {
        mLoadService.showCallback(EmptyCallback.class);
    }

    @Override
    public void onReload(View v) {
    }

    @Override
    public void showNewsList(List<News> list) {
        mLoadService.showSuccess();
        mMainAdapter.setNewData(list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.unsubscribe();
    }
}
