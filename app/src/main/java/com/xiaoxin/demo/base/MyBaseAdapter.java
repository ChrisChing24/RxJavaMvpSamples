package com.xiaoxin.demo.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by chris on 2017/11/28.
 */

public abstract class MyBaseAdapter<T extends RecyclerView.ViewHolder,E> extends RecyclerView.Adapter<T> {
    private  static OnItemClickListener onItemClickListener;

    public  interface OnItemClickListener<E> {
        void onItemClick(View view, E bean);
        void onItemLongClick(View view, E bean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    protected Context context;
    protected ArrayList<E> datas;
    protected int layoutID;

    public MyBaseAdapter(Context context, ArrayList<E> datas, int layoutID) {
        this.context = context;
        this.datas = datas;
        this.layoutID=layoutID;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView=View.inflate(context,layoutID,null);
        return getViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(final T holder, final int position){
        final E bean = datas.get(position);
        setValues(holder,bean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.itemView, bean);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemLongClick(holder.itemView, bean);
                }
                return true;
            }
        });
    }

    /**
     * 返回viewholder
     * @param itemView
     * @return
     */
    protected abstract T getViewHolder(View itemView);
    /**
     * 设置控件数据
     * @param holder
     * @param bean
     */
    protected abstract void setValues(T holder, E bean);

}
