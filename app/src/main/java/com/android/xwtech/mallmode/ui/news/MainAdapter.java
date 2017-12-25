package com.android.xwtech.mallmode.ui.news;

import android.support.annotation.Nullable;

import com.android.xwtech.mallmode.R;
import com.android.xwtech.mallmode.model.News;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by XW-Laq on 2017/12/20.
 */

public class MainAdapter extends BaseQuickAdapter<News, BaseViewHolder> {

    public MainAdapter(int layoutResId, @Nullable List<News> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {
        helper.setText(R.id.tv_news_title, item.getNews_title());
    }
}
