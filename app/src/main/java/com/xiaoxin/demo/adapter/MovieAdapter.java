package com.xiaoxin.demo.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiaoxin.demo.R;
import com.xiaoxin.demo.bean.MovieBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 2017/8/30.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private List<MovieBean> mMovieList = new ArrayList<>();
    public MovieAdapter(Context context, List<MovieBean> movieList) {
        this.mContext = context;
        this.mMovieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MovieBean movieBean = mMovieList.get(position);
        holder.mTvName.setText(movieBean.getMovieName());
        holder.mTvClassicLines.setText(movieBean.getClassicLines());
        Glide.with(mContext).load(movieBean.getMoviePoster()).into(holder.mIvMovie);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void update(List<MovieBean> movieList) {
        this.mMovieList = movieList;
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvClassicLines;
        private TextView mTvName;
        private ImageView mIvMovie;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mTvClassicLines = (TextView) itemView.findViewById(R.id.tv_classic_lines);
            mTvName = (TextView) itemView.findViewById(R.id.tv_movie_name);
            mIvMovie = (ImageView) itemView.findViewById(R.id.iv_movie);
        }
    }
}
