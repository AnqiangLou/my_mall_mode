package com.android.xwtech.mallmode.base;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.xwtech.mallmode.R;

import butterknife.ButterKnife;

/**
 * @author XW-Laq
 * @date 2017/12/15
 */

public abstract class BaseTitleActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvTopTitle;

    @Override
    protected View getContentView() {
        View rootView = View.inflate(this, R.layout.activity_title, null);
        FrameLayout mFlContent = rootView.findViewById(R.id.fl_content);
        mTvTopTitle = rootView.findViewById(R.id.tv_top_title);
        ImageButton mIbtnLeft = rootView.findViewById(R.id.ibtn_left);
        ImageButton mIbtnRight = rootView.findViewById(R.id.ibtn_right);
        mTvTopTitle.setText(getContentTitle() == null ? "" : getContentTitle());
        mIbtnLeft.setOnClickListener(this);
        mIbtnRight.setOnClickListener(this);

        View content = View.inflate(this, getChildContentId(), null);
        if (content != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            mFlContent.addView(content, params);
            ButterKnife.bind(this, rootView);
            // register after ButterKnife.bind()

        }
        return rootView;
    }

    /**
     * 标题
     * @return
     */
    protected abstract String getContentTitle();

    /**
     * 获取子布局ID
     * @return
     */
    protected abstract int getChildContentId();

//    protected void initTitleBar(String title, int leftResId, int rightResId) {
//
//        if (title != null) {
//            mTvTopTitle.setText(title);
//        }
//
//        if (leftResId == Constant.NO_RES_ID) {
//            ibtnLeft.setVisibility(View.INVISIBLE);
//        } else {
//            ibtnLeft.setVisibility(View.VISIBLE);
//            ibtnLeft.setImageResource(leftResId);
//        }
//
//        if (rightResId == Constant.NO_RES_ID) {
//            ibtnRight.setVisibility(View.INVISIBLE);
//        } else {
//            ibtnRight.setVisibility(View.VISIBLE);
//            ibtnRight.setImageResource(rightResId);
//        }
//    }

    /**
     * 左侧点击事件
     */
    protected abstract void leftClickEvent();

    /**
     * 右侧点击事件
     */
    protected abstract void rightClickEvent();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibtn_left:
                leftClickEvent();
                break;
            case R.id.ibtn_right:
                rightClickEvent();
                break;
            default:
                break;
        }
    }
}
