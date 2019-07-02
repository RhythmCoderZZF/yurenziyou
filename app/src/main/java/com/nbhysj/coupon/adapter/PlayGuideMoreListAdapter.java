package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.DeliciousFoodRecommendResponse;
import com.nbhysj.coupon.model.response.TourGuideBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/27.
 * description : 游玩指南适配器
 */
public class PlayGuideMoreListAdapter extends RecyclerView.Adapter<PlayGuideMoreListAdapter.ViewHolder> {

    List<TourGuideBean> tourGuideList;
    private Context mContext;

    public PlayGuideMoreListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setPlayGuideMoreList(List<TourGuideBean> tourGuideList) {

        this.tourGuideList = tourGuideList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_more_guide_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            TourGuideBean tourGuideBean = tourGuideList.get(itemPosition);
            String title = tourGuideBean.getTitle();
            String intro = tourGuideBean.getIntro();
            String photoUrl = tourGuideBean.getPhoto();
            holder.mTvProjectName.setText(title);
            holder.mTvProjectDes.setText(intro);
            GlideUtil.loadImage(mContext, photoUrl, holder.mImgScenicSpots);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return tourGuideList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_scenic_spots)
        RoundedImageView mImgScenicSpots;
        //项目名字
        @BindView(R.id.tv_project_name)
        TextView mTvProjectName;
        //项目描述
        @BindView(R.id.tv_project_des)
        TextView mTvProjectDes;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
