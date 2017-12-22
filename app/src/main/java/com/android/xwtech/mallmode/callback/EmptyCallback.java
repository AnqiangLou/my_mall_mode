package com.android.xwtech.mallmode.callback;

import android.content.Context;
import android.view.View;

import com.android.xwtech.mallmode.R;
import com.kingja.loadsir.callback.Callback;



/**
 * Description:TODO
 * Create Time:2017/9/4 10:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }

    @Override
    protected boolean onRetry(Context context, View view) {
        return true;
    }
}
