package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.ConscienceRecommendationBean;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.FoodDetailActivity;
import com.nbhysj.coupon.ui.GroupMchDetailsActivity;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/09/29.
 * description : 良心推荐列表适配器
 */
public class ConscienceRecommendationListAdapter extends RecyclerView.Adapter<ConscienceRecommendationListAdapter.ViewHolder> {

    List<ConscienceRecommendationBean> conscienceRecommendationList;
    private Context mContext;

    public ConscienceRecommendationListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setConscienceRecommendationList(List<ConscienceRecommendationBean> conscienceRecommendationList) {

        this.conscienceRecommendationList = conscienceRecommendationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_conscience_recommendation_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            ConscienceRecommendationBean recommendationBean = conscienceRecommendationList.get(itemPosition);
            String photoUrl = recommendationBean.getPhoto();
            int mchId = recommendationBean.getId();
            double price = recommendationBean.getPrice();
            String mchType = recommendationBean.getType();
            holder.mTvPopularScenicSpotName.setText(recommendationBean.getTitle());
            GlideUtil.loadImage(mContext, photoUrl, holder.mImgScenicSpots);

            holder.mTvPopularScenicSpotPrice.setText(Tools.getTwoDecimalPoint(price));

            holder.mLlytScenicSpotItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    String mchFood = MchTypeEnum.MCH_FOOD.getValue();
                    String mchScenicSpot = MchTypeEnum.MCH_SCENIC.getValue();
                    String mchHotel = MchTypeEnum.MCH_HOTEL.getValue();
                    String mchRecreation = MchTypeEnum.MCH_RECREATION.getValue();
                    String mchGroup = MchTypeEnum.MCH_GROUP2.getValue();
                    if (mchType.equals(mchFood)) {
                        intent.setClass(mContext, FoodDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        mContext.startActivity(intent);

                    } else if (mchType.equals(mchScenicSpot)) {

                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchType", mchType);
                        intent.putExtra("mchId", mchId);

                        mContext.startActivity(intent);
                    } else if (mchType.equals(mchRecreation)) {

                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchType", mchType);
                        intent.putExtra("mchId", mchId);
                        mContext.startActivity(intent);
                    } else if (mchType.equals(mchHotel)) {

                        intent.setClass(mContext, HotelDetailsActivity.class);
                        intent.putExtra("mchId", mchId);
                        mContext.startActivity(intent);

                    } else if (mchType.equals(mchGroup)) {

                        intent.setClass(mContext, GroupMchDetailsActivity.class);
                        intent.putExtra("packageId", mchId);
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
        return conscienceRecommendationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        //景区门票
        @BindView(R.id.tv_price)
        TextView mTvPopularScenicSpotPrice;
        //景区名字
        @BindView(R.id.tv_scenic_spots_name)
        TextView mTvPopularScenicSpotName;
        //景区照片
        @BindView(R.id.image_scenic_spots)
        RoundedImageView mImgScenicSpots;
        @BindView(R.id.llyt_scenic_spot_item)
        LinearLayout mLlytScenicSpotItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
