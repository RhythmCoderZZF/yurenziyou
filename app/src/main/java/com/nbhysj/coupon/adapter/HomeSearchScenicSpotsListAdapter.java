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
 * @author hysj created at 2019/4/21.
 * description : 首页搜索景点列表适配器
 */
public class HomeSearchScenicSpotsListAdapter extends RecyclerView.Adapter<HomeSearchScenicSpotsListAdapter.ViewHolder> {

    List<HomeSearchMchTypeBean> popularScenicSpotsList;
    private Context mContext;

    public HomeSearchScenicSpotsListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setPopularScenicSpotsList(List<HomeSearchMchTypeBean> popularScenicSpotsList) {

        this.popularScenicSpotsList = popularScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home_search_scenic_spots_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {


        try {
            HomeSearchMchTypeBean popularScenicSpots = popularScenicSpotsList.get(itemPosition);
            int scenicSpotSequence = itemPosition + 1;
            holder.mTvPopularScenicSpotSequence.setText("No." + scenicSpotSequence);
            String mchId = popularScenicSpots.getId();
            double consumePrice = popularScenicSpots.getConsumePrice();
            double commentScore = popularScenicSpots.getCommentScore();
            String county = popularScenicSpots.getCounty();
            String mchName = popularScenicSpots.getMchName();
            String scenicSpotsPhoto = popularScenicSpots.getPhoto();
            String mchType = popularScenicSpots.getSearchType();
            holder.mTvPopularScenicSpotPrice.setText(String.valueOf(consumePrice));
            holder.mTvPopularScenicSpotScore.setText(String.valueOf(commentScore) + "分");
            if(!TextUtils.isEmpty(county)) {
                holder.mTvPopularScenicSpotLocation.setText(county);
            }
            if(!TextUtils.isEmpty(mchName))
            {
                holder.mTvPopularScenicSpotName.setText(mchName);
            }

            GlideUtil.loadImage(mContext, scenicSpotsPhoto,  holder.mImgScenicSpots);

            if (itemPosition == 0) {

                holder.mHeader.setVisibility(View.VISIBLE);
            } else {

                holder.mHeader.setVisibility(View.GONE);
            }

            if (itemPosition == popularScenicSpotsList.size() - 1) {

                holder.mFooter.setVisibility(View.VISIBLE);
            } else {
                holder.mFooter.setVisibility(View.GONE);
            }

            holder.mLlytHotScenicSpotsItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String mchScenic = MchTypeEnum.MCH_SCENIC.getValue();
                    if(mchType.equals(mchScenic))
                    {
                        Intent intent = new Intent();
                        intent.putExtra("mchId", Integer.parseInt(mchId));
                        intent.putExtra("mchType", mchType);
                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        mContext.startActivity(intent);
                    }
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

        //景区序列
        @BindView(R.id.tv_popular_scenic_spot_sequence)
        TextView mTvPopularScenicSpotSequence;
        //景区位置
        @BindView(R.id.tv_scenic_spots_location)
        TextView mTvPopularScenicSpotLocation;
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
        @BindView(R.id.view_header)
        View mHeader;
        @BindView(R.id.view_footer)
        View mFooter;
        //热门景点
        @BindView(R.id.llyt_hot_scenic_spots_item)
        LinearLayout mLlytHotScenicSpotsItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
