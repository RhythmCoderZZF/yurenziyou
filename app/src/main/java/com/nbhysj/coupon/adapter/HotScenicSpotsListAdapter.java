package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/21.
 * description :热门景点列表适配器
 */
public class HotScenicSpotsListAdapter extends RecyclerView.Adapter<HotScenicSpotsListAdapter.ViewHolder> {

    List<MchTypeBean> popularScenicSpotsList;
    private Context mContext;

    public HotScenicSpotsListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setPopularScenicSpotsList(List<MchTypeBean> popularScenicSpotsList) {

        this.popularScenicSpotsList = popularScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_scenic_spots_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            // PopularScenicSpotsResponse popularScenicSpots = popularScenicSpotsList.get(itemPosition);
            MchTypeBean hotMchTypeBean = popularScenicSpotsList.get(itemPosition);
            double mConsumePrice = hotMchTypeBean.getConsumePrice();
            double mCommentScore = hotMchTypeBean.getCommentScore();
            int commentNum = hotMchTypeBean.getCommentNum();
            String photoUrl = hotMchTypeBean.getPhoto();
            String intro = hotMchTypeBean.getIntro();
            holder.mTvPopularScenicSpotPrice.setText(Tools.getTwoDecimalPoint(mConsumePrice));
            holder.mTvPopularScenicSpotScore.setText(String.valueOf(mCommentScore) + "分");
            holder.mTvScenicSpotCommentNum.setText(commentNum + "条点评数");
            holder.mTvPopularScenicSpotName.setText(hotMchTypeBean.getMchName());
            if (intro != null) {
                holder.mTvScenicSpotsDes.setText(intro);
            } else {
                holder.mTvScenicSpotsDes.setText("");
            }
            holder.mTvScenicSpotsStar.setText("3星");
            holder.mTvScenicSpotsType.setText("经济型");

            RequestOptions myOptions = new RequestOptions()
                    .transform(new GlideRoundTransform(mContext, 5));

            Glide.with(mContext)
                    .load(photoUrl)
                    .apply(myOptions)
                    .into(holder.mImgScenicSpots);


            holder.mLlytScenicSpotItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, ScenicSpotDetailActivity.class);
                    mContext.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return popularScenicSpotsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //景区评分
        @BindView(R.id.tv_scenic_spots_score)
        TextView mTvPopularScenicSpotScore;
        //景区门票
        @BindView(R.id.tv_scenic_spots_price)
        TextView mTvPopularScenicSpotPrice;
        //景区名字
        @BindView(R.id.tv_scenic_spots_name)
        TextView mTvPopularScenicSpotName;
        //景区照片
        @BindView(R.id.image_scenic_spots)
        ImageView mImgScenicSpots;
        //点评数
        @BindView(R.id.tv_comment_num)
        TextView mTvScenicSpotCommentNum;
        //景点描述
        @BindView(R.id.tv_scenic_spots_des)
        TextView mTvScenicSpotsDes;
        //星级
        @BindView(R.id.tv_scenic_spots_level)
        TextView mTvScenicSpotsStar;
        //景点类型
        @BindView(R.id.tv_scenic_spot_type)
        TextView mTvScenicSpotsType;
        @BindView(R.id.llyt_scenic_spot_item)
        LinearLayout mLlytScenicSpotItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
