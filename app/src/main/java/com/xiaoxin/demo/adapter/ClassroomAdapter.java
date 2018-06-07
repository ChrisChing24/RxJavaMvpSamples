package com.xiaoxin.demo.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoxin.demo.R;
import com.xiaoxin.demo.bean.ClassroomBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanlili on 2017/3/1.
 */

public class ClassroomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ClassroomBean.ResponseBean.ClassroomListBean> mClassroomList = new ArrayList<>();

    public ClassroomAdapter(Context mContext, List<ClassroomBean.ResponseBean.ClassroomListBean> classroomList) {
        this.mContext = mContext;
        this.mClassroomList = classroomList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_classroom, parent, false);
        return new ClassroomViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ClassroomViewHolder viewHolder = (ClassroomViewHolder) holder;
        ClassroomBean.ResponseBean.ClassroomListBean classroomListBean = mClassroomList.get(position);
        if (classroomListBean.getClassroom_name().length() > 10) {
            viewHolder.mTvClassroom.setText(String.format("%s...", classroomListBean.getClassroom_name().substring(0, 11)));
        } else {
            viewHolder.mTvClassroom.setText(classroomListBean.getClassroom_name());
        }
        viewHolder.mTvSeatNum.setText(String.format("坐席：%s", classroomListBean.getSeat_num()));

        if (classroomListBean.getDevice_name().equals("")) {
            viewHolder.mTvDevice.setText("| 设备：无");
        } else {
            viewHolder.mTvDevice.setText(String.format("| 设备：%s", classroomListBean.getDevice_name()));
        }


//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mOnItemsClickListener != null) {
//                    mOnItemsClickListener.onItemsClick(position);
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mClassroomList == null ? 0 : mClassroomList.size();
    }

    public void update(List<ClassroomBean.ResponseBean.ClassroomListBean> classroomList) {
        this.mClassroomList = classroomList;
        notifyDataSetChanged();
    }

    private class ClassroomViewHolder extends RecyclerView.ViewHolder {

        TextView mTvDevice;
        ImageView mIvClassroom;
        TextView mTvClassroom;
        TextView mTvState;
        TextView mTvSeatNum;

        ClassroomViewHolder(View itemView) {
            super(itemView);
            mIvClassroom = (ImageView) itemView.findViewById(R.id.iv_classroom);
            mTvClassroom = (TextView) itemView.findViewById(R.id.tv_classroom);
            mTvState = (TextView) itemView.findViewById(R.id.tv_state);
            mTvSeatNum = (TextView) itemView.findViewById(R.id.tv_seatnum);
            mTvDevice = (TextView) itemView.findViewById(R.id.tv_device);

        }
    }

//    public interface onItemsClickListener {
//        void onItemsClick(int position);
//    }
//
//    private onItemsClickListener mOnItemsClickListener;
//
//    public void setOnItemsClickListener(onItemsClickListener listener) {
//        mOnItemsClickListener = listener;
//    }
}
