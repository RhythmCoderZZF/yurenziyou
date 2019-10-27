package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.HomeSearchMchTypeBean;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/10/27.
 * description : 首页搜索景点列表适配器
 */
public class HomePageSearchScenicSpotsAdapter extends RecyclerView.Adapter<HomePageSearchScenicSpotsAdapter.ViewHolder> {

    List<HomeSearchMchTypeBean> popularScenicSpotsList;
    private Context mContext;

    public HomePageSearchScenicSpotsAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setPopularScenicSpotsList(List<HomeSearchMchTypeBean> popularScenicSpotsList) {

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

            HomeSearchMchTypeBean popularScenicSpots = popularScenicSpotsList.get(itemPosition);
            String photoUrl = popularScenicSpots.getPhoto();
            int mchtId = Integer.parseInt(popularScenicSpots.getId());
            holder.mTvPopularScenicSpotPrice.setText(String.valueOf(popularScenicSpots.getConsumePrice()));
            holder.mTvPopularScenicSpotScore.setText(String.valueOf(popularScenicSpots.getCommentScore()));
            holder.mTvScenicSpotCommentNum.setText(popularScenicSpots.getCommentNum() + "条点评数");
            holder.mTvPopularScenicSpotName.setText(popularScenicSpots.getMchName());
            String intro = popularScenicSpots.getIntro();
            if (intro != null) {

                holder.mTvScenicSpotsDes.setText(intro);
            } else {

                holder.mTvScenicSpotsDes.setText("");
            }
            String level = popularScenicSpots.getLevel();
            if(TextUtils.isEmpty(level)){
                holder.mTvScenicSpotsLevel.setVisibility(View.GONE);
            } else {
                holder.mTvScenicSpotsLevel.setText(level + "A级景区");
                holder.mTvScenicSpotsLevel.setVisibility(View.VISIBLE);
            }

            List<String> scenicSpotTagsList = popularScenicSpots.getTags();
            if (scenicSpotTagsList != null) {

                if (scenicSpotTagsList.size() > 0) {

                    holder.mTvScenicSpotsType.setVisibility(View.VISIBLE);
                    String tagsTitle = popularScenicSpots.getTags().get(0);
                    holder.mTvScenicSpotsType.setText(tagsTitle);
                } else {

                    holder.mTvScenicSpotsType.setVisibility(View.GONE);
                }
            } else {

                holder.mTvScenicSpotsType.setVisibility(View.GONE);
            }

            GlideUtil.loadImage(mContext, photoUrl, holder.mImgScenicSpots);

            holder.mLlytScenicSpotItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, ScenicSpotDetailActivity.class);
                    intent.putExtra("mchId", mchtId);
                    String mchType = MchTypeEnum.MCH_SCENIC.getValue();
                    intent.putExtra("mchType", mchType);
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
        RoundedImageView mImgScenicSpots;
        //点评数
        @BindView(R.id.tv_comment_num)
        TextView mTvScenicSpotCommentNum;
        //景点描述
        @BindView(R.id.tv_scenic_spots_des)
        TextView mTvScenicSpotsDes;
        //星级
        @BindView(R.id.tv_scenic_spots_level)
        TextView mTvScenicSpotsLevel;
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
