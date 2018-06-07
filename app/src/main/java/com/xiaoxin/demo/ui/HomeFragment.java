package com.xiaoxin.demo.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.xiaoxin.demo.R;
import com.xiaoxin.demo.adapter.ClassroomAdapter;
import com.xiaoxin.demo.adapter.ContactsAdapter;
import com.xiaoxin.demo.adapter.MovieAdapter;
import com.xiaoxin.demo.base.MVPBaseFragment;
import com.xiaoxin.demo.base.OnRecyclerItemClickListener;
import com.xiaoxin.demo.bean.ClassroomBean;
import com.xiaoxin.demo.bean.ContactsBean;
import com.xiaoxin.demo.bean.MovieBean;
import com.xiaoxin.demo.classroom.presenter.ClassroomPresenter;
import com.xiaoxin.demo.classroom.ui.ClassroomActivity;
import com.xiaoxin.demo.classroom.view.IClassroomView;
import com.xiaoxin.demo.global.Constant;
import com.xiaoxin.demo.utils.AddMovieUtils;
import com.xiaoxin.demo.widget.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Ching on 2017/8/18.
 * 主页fragment
 */

public class HomeFragment extends MVPBaseFragment<IClassroomView, ClassroomPresenter> implements IClassroomView {



    private List<ClassroomBean.ResponseBean.ClassroomListBean> mClassroomList = new ArrayList<>();
    private List<ContactsBean.ResponseBean.ListBean> mContactsList = new ArrayList<>();
    private List<MovieBean> mMovieList = new ArrayList<>();
    private int nextPage = 0;
    private ClassroomAdapter mAdapter;
    private ImageView mIvNoData;
    private AVLoadingIndicatorView mLoadingView;
    private int mType = 2;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras() != null) {
                mType = intent.getExtras().getInt(Constant.TYPE);

                switch (mType) {
                    case 0://电影
                        mRecyclerView.setAdapter(mMovieAdapter);
                        nextPage = 0;
                        if (mMovieList != null) {
                            AddMovieUtils.addMovie(mMovieList);
                            mMovieAdapter.update(mMovieList);
                        }
                        break;
                    case 1://老师
                        mRecyclerView.setAdapter(mContactsAdapter);
                        refreshContacts();
                        break;
                    case 2://教室
                        mRecyclerView.setAdapter(mAdapter);
                        refreshClassroom();
                        break;
                }
            }
        }
    };
    private ContactsAdapter mContactsAdapter;
    private MovieAdapter mMovieAdapter;
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;


    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

        IntentFilter filter = new IntentFilter(Constant.ACTION.UPDATE_LIST);
        getActivity().registerReceiver(mReceiver, filter);

        mRefreshLayout = (SmartRefreshLayout) getActivity().findViewById(R.id.srl_home);
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.rv_home);
        mIvNoData = (ImageView) getActivity().findViewById(R.id.iv_nodata);
        mLoadingView = (AVLoadingIndicatorView) getActivity().findViewById(R.id.loading_view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new RecyclerViewDivider(getActivity(), LinearLayoutManager.VERTICAL,
                20, R.color.brown));
        mAdapter = new ClassroomAdapter(getActivity(), mClassroomList);
        mContactsAdapter = new ContactsAdapter(getActivity(), mContactsList);
        mMovieAdapter = new MovieAdapter(getActivity(), mMovieList);

        mRecyclerView.setAdapter(mAdapter);

        //下拉刷新
        mRefreshLayout.setOnRefreshListener(new com.scwang.smartrefresh.layout.listener.OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                switch (mType) {
                    case 0:
                        nextPage = 0;
                        if (mMovieList != null) {
                            mMovieList.clear();
                        }
                        AddMovieUtils.addMovie(mMovieList);
                        mMovieAdapter.update(mMovieList);
                        break;
                    case 1:
                        refreshContacts();
                        break;
                    case 2:
                        refreshClassroom();
                        break;
                }
                mRefreshLayout.finishRefresh();
            }
        });

        //上拉加载
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                switch (mType) {
                    case 0:
                        if (nextPage == -1) {
                            showToast(getActivity(), "已经加载全部数据");
                        } else {
                            AddMovieUtils.addMore(mMovieList);
                        }
                        nextPage = -1;
                        break;
                    case 1:
                        getMore();
                        break;
                    case 2:
                        if (nextPage == -1) {
                            showToast(getActivity(), "已经加载全部数据");
                        } else {
                            getPresenter().getList(getActivity(), nextPage);
                        }
                        break;
                }

                mRefreshLayout.finishLoadmore();
            }
        });

//        mAdapter.setOnItemsClickListener(new BaseItemAdapter.onItemsClickListener() {
//            @Override
//            public void onItemsClick(int position) {
//                Constant.url = mClassroomList.get(position).getUrl();
//                startActivity(ClassroomActivity.class);
//            }
//        });

        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Constant.url = mClassroomList.get(vh.getAdapterPosition()).getUrl();
                startActivity(ClassroomActivity.class);
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {

            }
        });

    }

    private void getMore() {
        if (nextPage == -1) {
            showToast(getActivity(), "已经加载全部数据");
        } else {
            getPresenter().getContacts(getActivity(), mType, nextPage);
        }
    }

    private void refreshClassroom() {
        if (mClassroomList != null) {
            mClassroomList.clear();
        }
        nextPage = 0;
        getPresenter().getList(getActivity(), nextPage);
    }

    private void refreshContacts() {
        nextPage = 0;
        if (mContactsList != null) {
            mContactsList.clear();
        }
        getPresenter().getContacts(getActivity(), mType, nextPage);
    }


    @Override
    public void initData() {

        switch (mType) {
            case 0:
                AddMovieUtils.addMovie(mMovieList);
                if (mMovieAdapter != null) {
                    mMovieAdapter.update(mMovieList);
                }
                break;
            case 1:
                getPresenter().getContacts(getActivity(), mType, nextPage);
                break;
            case 2:
                getPresenter().getList(getActivity(), nextPage);
                break;
        }

    }


    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public ClassroomPresenter createPresenter() {
        return new ClassroomPresenter(this);
    }

    @Override
    public void showData(Object object) {

        switch (mType) {
            case 0:
                break;
            case 1:
                getData(object);
                mContactsAdapter.update(mContactsList);
                break;
            case 2:
                ClassroomBean classroomBean = (ClassroomBean) object;
                mIvNoData.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                Log.d("tag", "classroom = " + classroomBean.getResponse().getClassroom_list().toString());
                nextPage = classroomBean.getResponse().getNext_id();
                Log.d("tag", "next_id = " + nextPage);

                if (mClassroomList != null) {
                    mClassroomList.addAll(classroomBean.getResponse().getClassroom_list());
                }
                mAdapter.update(mClassroomList);

                break;
        }

    }

    private void getData(Object object) {
        mIvNoData.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);

        ContactsBean contactsBean = (ContactsBean) object;
        nextPage = contactsBean.getResponse().getNext_id();
        if (mContactsList != null) {
            mContactsList.addAll(contactsBean.getResponse().getList());
        }

    }

    @Override
    public void showEmpty() {
        mIvNoData.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String msg) {
        showToast(getActivity(), msg);
    }

    @Override
    public void showLoading() {
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingView.show();
    }

    @Override
    public void dismiss() {
        mLoadingView.hide();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(mReceiver);
    }
}
