package com.xiaoxin.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.xiaoxin.demo.R;

/**
 * Created by Chris Ching on 2017/8/23.
 * 自定义头布局
 */

public class HeaderView extends LinearLayout implements SwipeRefreshTrigger, SwipeTrigger {

    private TextView mTvRefresh;
    private ImageView mIvLoading;

    public HeaderView(Context context) {
        this(context, null, 0);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //这里的原理就是简单的动态布局添加
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = View.inflate(getContext(), R.layout.layout_header2, null);
        addView(view, lp);

        mIvLoading = (ImageView) view.findViewById(R.id.iv_loading);
//        mTvRefresh = (TextView) view.findViewById(R.id.tv_refresh);
    }

    @Override
    public void onRefresh() {
//        mTvRefresh.setText("正在刷新...");
        mIvLoading.setImageResource(R.mipmap.sj3);
    }

    @Override
    public void onPrepare() {
//        mTvRefresh.setText("准备刷新");
        mIvLoading.setImageResource(R.mipmap.sj1);
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled <= -getHeight()) {
                mIvLoading.setImageResource(R.mipmap.sj2);
//                mTvRefresh.setText("松手即可加载");
            } else {
                mIvLoading.setImageResource(R.mipmap.sj2);
//                mTvRefresh.setText("松手即可加载");
            }
        } else {
//            mTvRefresh.setText("LOAD MORE RETURNING");
        }
//        mTvRefresh.setText("正在加载中...");
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void onComplete() {
//        mTvRefresh.setText("刷新完成");
        mIvLoading.setImageResource(R.mipmap.sj4);
    }

    @Override
    public void onReset() {

    }
}
