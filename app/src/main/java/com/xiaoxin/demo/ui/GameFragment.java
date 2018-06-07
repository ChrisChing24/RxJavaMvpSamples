package com.xiaoxin.demo.ui;

import android.os.Bundle;

import com.xiaoxin.demo.R;
import com.xiaoxin.demo.base.BaseFragment;

/**
 * Created by WangChang on 2016/5/15.
 */
public class GameFragment extends BaseFragment{


    @Override
    public int getLayout() {
        return R.layout.fragment_game;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    public static GameFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        GameFragment fragment = new GameFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
