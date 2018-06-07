package com.xiaoxin.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marshalchen.ultimaterecyclerview.expanx.Util.parent;
import com.xiaoxin.demo.R;
import com.xiaoxin.demo.bean.ContactsBean;
import com.xiaoxin.demo.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 联系人（老师）适配器
 * Created by yuanlili on 2016/11/21.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {
    private Context mContext;
    private List<ContactsBean.ResponseBean.ListBean> mContactsList = new ArrayList<>();


    public ContactsAdapter(Context context, List<ContactsBean.ResponseBean.ListBean> contactsList) {
        this.mContext = context;
        this.mContactsList = contactsList;

    }


    @Override
    public int getItemCount() {

        return mContactsList.size();

    }


    @Override
    public ContactsAdapter.ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.contacts_item, parent, false);
        return new ContactsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ContactsAdapter.ContactsViewHolder holder, final int position) {
        ContactsBean.ResponseBean.ListBean listBean = mContactsList.get(position);
        holder.mTvTeacherName.setText(listBean.getTeacher_name());
        if (StringUtils.isNotEmpty(listBean.getPhoto())) {
            Glide.with(mContext).load(listBean.getPhoto()).into(holder.mIvAvatar);
        } else {
            holder.mIvAvatar.setImageResource(R.mipmap.teacher);
        }


    }

    public void update(List<ContactsBean.ResponseBean.ListBean> contactsList) {
        this.mContactsList = contactsList;
        notifyDataSetChanged();
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout mRelativeLayout;
        TextView mTvTeacherName;
        CircleImageView mIvAvatar;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            mRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_layout);
            mTvTeacherName = (TextView) itemView.findViewById(R.id.tv_teacher_name);
            mIvAvatar = (CircleImageView) itemView.findViewById(R.id.iv_avatar);
        }


    }
}




