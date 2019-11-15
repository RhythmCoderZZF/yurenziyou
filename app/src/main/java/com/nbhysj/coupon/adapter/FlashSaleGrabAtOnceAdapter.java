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
import com.nbhysj.coupon.common.Enum.GoodsTypeEnum;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.GoodsBean;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.ui.FoodDetailActivity;
import com.nbhysj.coupon.ui.GroupMchDetailsActivity;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.nbhysj.coupon.widget.glide.GlideRoundedCornersTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/17.
 * description: 限时特卖适配器
 */
public class FlashSaleGrabAtOnceAdapter extends RecyclerView.Adapter<FlashSaleGrabAtOnceAdapter.ViewHolder> {

    List<GoodsBean> limitedTimeSaleGoodsList;
    private Context mContext;

    public FlashSaleGrabAtOnceAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setLimitedTimeSaleGoodsList(List<GoodsBean> limitedTimeSaleGoodsList) {

        this.limitedTimeSaleGoodsList = limitedTimeSaleGoodsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_flash_sale_immediate_robbery_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            GoodsBean goodsBean = limitedTimeSaleGoodsList.get(itemPosition);
            int mchId = goodsBean.getMchId();
            double goodsPrice = goodsBean.getGoodsPrice();
            String goodsName = goodsBean.getGoodsName();
            String goodsPhotoUrl = goodsBean.getGoodsPhoto();
            String mchType = goodsBean.getMchType();
            GlideUtil.loadImage(mContext, goodsPhotoUrl, holder.mImgFLashSale);
            holder.mTvLimitedTimeSalePrice.setText(Tools.getTwoDecimalPoint(goodsPrice));
            holder.mTvLimitedTimeSaleTitle.setText(goodsName);

            holder.mLlytLimitedTimeRobbery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String mchScenic = MchTypeEnum.MCH_SCENIC.getValue();
                    String mchHotelRoom = MchTypeEnum.MCH_HOTEL.getValue();
                    String mchRecreation = MchTypeEnum.MCH_RECREATION.getValue();
                    String mchFood = MchTypeEnum.MCH_FOOD.getValue();
                    String mchGroup = MchTypeEnum.MCH_GROUP.getValue();

                    Intent intent = new Intent();
                    if (mchType.equals(mchScenic))
                    {
                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        intent.putExtra("mchType", mchType);
                        mContext.startActivity(intent);

                    } else if (mchType.equals(mchRecreation))
                    {
                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        intent.putExtra("mchType", mchType);
                        mContext.startActivity(intent);

                    } else if (mchType.equals(mchFood)) {

                        intent.setClass(mContext, FoodDetailActivity.class);
                        intent.putExtra("mchId", mchId);
                        mContext.startActivity(intent);

                    } else if (mchType.equals(mchGroup)) {

                        intent.setClass(mContext, GroupMchDetailsActivity.class);
                        intent.putExtra("packageId", mchId);
                        mContext.startActivity(intent);

                    } else if (mchType.equals(mchHotelRoom)) {
                        intent.setClass(mContext, HotelDetailsActivity.class);
                        intent.putExtra("mchId", mchId);
                        mContext.startActivity(intent);
                       /* String mchHotel1 = MchTypeEnum.MCH_HOTEL1.getValue();
                        String mchHomestay = MchTypeEnum.MCH_HOTEL2.getValue();
                        if (mchHotel1.equals(type2)) {

                            intent.setClass(mContext, HotelDetailsActivity.class);
                            mContext.startActivity(intent);

                        } else if (mchHomestay.equals(type2)) {

                            intent.setClass(mContext, HomestayDetailActivity.class);
                            mContext.startActivity(intent);
                        }*/

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return limitedTimeSaleGoodsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //限时特卖
        RoundedImageView mImgFLashSale;
        //限时特卖价格
        TextView mTvLimitedTimeSalePrice;
        //限时特卖标题
        TextView mTvLimitedTimeSaleTitle;
        //限时抢购
        LinearLayout mLlytLimitedTimeRobbery;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgFLashSale = itemView.findViewById(R.id.image_flash_sale);
            mTvLimitedTimeSalePrice = itemView.findViewById(R.id.tv_limited_time_sale_price);
            mTvLimitedTimeSaleTitle = itemView.findViewById(R.id.tv_limited_sale_title);
            mLlytLimitedTimeRobbery = itemView.findViewById(R.id.llyt_limited_time_robbery);

        }
    }


}
