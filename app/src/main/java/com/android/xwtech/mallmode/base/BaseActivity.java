package com.android.xwtech.mallmode.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.xwtech.mallmode.app.AppManager;

import butterknife.ButterKnife;

/**
 *
 * @author XW-Laq
 * @date 2017/12/15
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());

        initViewAndListener();

        initData();

        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(view);
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化页面及监听事件
     */
    protected abstract void initViewAndListener();

    /**
     * 获取界面ID
     *
     * @return
     */
    protected abstract View getContentView();

    /**
     * 页面跳转
     *
     * @param clz
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    /**
     * 页面跳转
     *
     * @param clz
     * @param isCloseCurrentActivity
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    /**
     * 页面跳转
     *
     * @param clz
     * @param isCloseCurrentActivity
     * @param ex
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(this, clz);
        if (ex != null) {
            intent.putExtras(ex);
        }
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }

    /**
     * Toast
     * @param content
     */
    protected void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
