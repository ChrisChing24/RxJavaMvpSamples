package com.xiaoxin.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.xiaoxin.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Ching on 2017/8/19.
 * 主页列表adapter
 */

public class HomeAdapter extends UltimateViewAdapter<UltimateRecyclerviewViewHolder> {

    private List<String> mList = new ArrayList<>();
    private Context mContext;

    public HomeAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public UltimateRecyclerviewViewHolder getViewHolder(View view) {
        return new HomeViewHolder(view);
    }

    @Override
    public UltimateRecyclerviewViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public int getAdapterItemCount() {
        return mList.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(UltimateRecyclerviewViewHolder holder, int position) {
        HomeViewHolder viewHolder = (HomeViewHolder) holder;
        //一定要加这个判断  因为UltimateRecyclerView本身有加了头部和尾部  这个方法返回的是包括头部和尾部在内的
        if (position < getItemCount() && (customHeaderView != null ? position <= mList.size() : position < mList.size())
                && (customHeaderView == null || position > 0)) {
            position -= customHeaderView == null ? 0 : 1;
            Glide.with(mContext).load(mList.get(position)).into(viewHolder.mImageView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_header, parent, false);
//        return new HeaderViewHolder(view);
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class HomeViewHolder extends UltimateRecyclerviewViewHolder {

        private ImageView mImageView;

        public HomeViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_home);
        }
    }

    class HeaderViewHolder extends UltimateRecyclerviewViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
