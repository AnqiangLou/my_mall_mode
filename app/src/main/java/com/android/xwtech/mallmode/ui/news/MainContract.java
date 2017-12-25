package com.android.xwtech.mallmode.ui.news;

import com.android.xwtech.mallmode.base.BasePresenter;
import com.android.xwtech.mallmode.base.BaseView;
import com.android.xwtech.mallmode.model.News;

import java.util.List;

/**
 *
 * @author XW-Laq
 * @date 2017/12/19
 */

public interface MainContract {
    interface View extends BaseView {
        /**
         * 显示新闻列表
         * @param list
         */
        void showNewsList(List<News> list);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 获取新闻列表
         */
        void getNewsList();
    }
}
