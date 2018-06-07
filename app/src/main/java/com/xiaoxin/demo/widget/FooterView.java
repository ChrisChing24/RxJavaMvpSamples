package com.xiaoxin.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.xiaoxin.demo.R;

/**
 * Created by Chris Ching on 2017/8/23.
 */

public class FooterView extends LinearLayout implements SwipeLoadMoreTrigger,SwipeTrigger {

    private TextView mTvRefresh;

    public FooterView(Context context) {
        this(context, null, 0);
    }

    public FooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //这里的原理就是简单的动态布局添加
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = View.inflate(getContext(), R.layout.layout_header, null);
        addView(view, lp);

        mTvRefresh = (TextView) view.findViewById(R.id.tv_refresh);
    }


    @Override
    public void onPrepare() {
//        mTvRefresh.setText("准备加载");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        mTvRefresh.setText("松手即可加载");
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
        mTvRefresh.setText("加载完成");
    }

    @Override
    public void onReset() {

    }

    @Override
    public void onLoadMore() {
        mTvRefresh.setText("正在加载中...");
    }
}
