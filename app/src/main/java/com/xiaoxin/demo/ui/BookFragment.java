package com.xiaoxin.demo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.wang.avi.AVLoadingIndicatorView;
import com.xiaoxin.demo.R;
import com.xiaoxin.demo.base.BaseFragment;
import com.xiaoxin.demo.global.Constant;
import com.xiaoxin.demo.utils.WebViewUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Chris Ching on 2017/8/18.
 */
public class BookFragment extends BaseFragment {

    @BindView(R.id.swipe_target)
    WebView mWebView;
    @BindView(R.id.stl_book)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.loading_view)
    AVLoadingIndicatorView mLoadingView;
    Unbinder unbinder;
    private Map<String, String> headers = new HashMap<>();

    @Override
    public int getLayout() {
        return R.layout.fragment_book;
    }

    @Override
    public void initView() {

        WebViewUtil.setupWebView(mWebView, getActivity(), mLoadingView);

        headers.put(Constant.TOKEN, Constant.token);
        headers.put(Constant.VERSION, Constant.version);

        mWebView.loadUrl(Constant.url, headers);

        mSwipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebView.loadUrl(Constant.url, headers);
                mSwipeToLoadLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {

    }

    public static BookFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        BookFragment fragment = new BookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_book, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
