package com.xiaoxin.demo.classroom.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.wang.avi.AVLoadingIndicatorView;
import com.xiaoxin.demo.R;
import com.xiaoxin.demo.base.BaseActivity;
import com.xiaoxin.demo.global.Constant;
import com.xiaoxin.demo.utils.SnackbarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassroomActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.activity_collapsing_toolbar_layout)
    CoordinatorLayout mActivityCollapsingToolbarLayout;
    @BindView(R.id.loading_view)
    AVLoadingIndicatorView mLoadingView;
    @BindView(R.id.iv_girl)
    PhotoView mIvGirl;
    private Context mContext;
    private List<String> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);
        mContext = ClassroomActivity.this;
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void widgetClick(View v) {

    }

    private void initView() {


        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示返回按钮
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//点击返回键退出当前页面
            }
        });

        mCollapsingToolbarLayout.setTitle("电影海报观赏");
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);     //设置展开时Title的颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);    //设置收缩时Title的颜色

        mList.add(Constant.ImageUrl.URL1);
        mList.add(Constant.ImageUrl.URL2);
        mList.add(Constant.ImageUrl.URL3);
        mList.add(Constant.ImageUrl.URL4);
        mList.add(Constant.ImageUrl.URL5);
        mList.add(Constant.ImageUrl.URL6);
        mList.add(Constant.ImageUrl.URL7);
        mList.add(Constant.ImageUrl.URL8);
        mList.add(Constant.ImageUrl.URL9);
        mList.add(Constant.ImageUrl.URL10);
        loadRandomImage();
    }

    private void loadRandomImage() {
        //生成0-9的随机数
        Random random = new Random();
        int i = random.nextInt(10);

        Glide.with(mContext).load(mList.get(i)).into(mIvGirl);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        Snackbar snackbar = SnackbarUtil.LongSnackbar(mActivityCollapsingToolbarLayout, "你对这个结果满意吗？", Color.WHITE, getResources().getColor(R.color.title_bg))
                .setAction("换一张", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadRandomImage();
//                        SnackbarUtil.LongSnackbar(mActivityCollapsingToolbarLayout, "觉得不错给个赞吧", Color.WHITE, getResources().getColor(R.color.title_bg));
                    }
                });

        snackbar.show();
    }

}
