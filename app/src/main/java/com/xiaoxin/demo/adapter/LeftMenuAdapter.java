package com.xiaoxin.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoxin.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Ching on 2017/8/24.
 * 左侧菜单条目
 */

public class LeftMenuAdapter extends RecyclerView.Adapter<LeftMenuAdapter.MenuViewHolder> {


    private List<String> mList = new ArrayList<>();
    private Context mContext;
    private int index = 2;

    public LeftMenuAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_left, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MenuViewHolder holder, final int position) {
        holder.mTvMenu.setText(mList.get(position));
        if (index == position) {
            holder.mTvMenu.setBackgroundColor(mContext.getResources().getColor(R.color.title_bg));
        }else {
            holder.mTvMenu.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnMenuClickListener != null) {
                    mOnMenuClickListener.onClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateItem(int position) {
        this.index = position;
        notifyDataSetChanged();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvMenu;

        public MenuViewHolder(View itemView) {
            super(itemView);
            mTvMenu = (TextView) itemView.findViewById(R.id.tv_menu);
        }
    }

    public interface OnMenuClickListener {
        void onClick(int position);
    }

    private OnMenuClickListener mOnMenuClickListener;

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.mOnMenuClickListener = onMenuClickListener;
    }
}
