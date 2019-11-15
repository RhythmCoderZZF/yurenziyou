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
import com.nbhysj.coupon.common.Enum.CouponRangeTypeEnum;
import com.nbhysj.coupon.common.Enum.CouponStatusTypeEnum;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.ui.MainActivity;
import com.nbhysj.coupon.ui.ShoppingMallHomestayActivity;
import com.nbhysj.coupon.ui.ShoppingMallHotelActivity;
import com.nbhysj.coupon.ui.ShoppingMallInteractionActivity;
import com.nbhysj.coupon.ui.ShoppingMallScenicSpotActivity;
import com.nbhysj.coupon.util.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/19.
 * description : 优惠券适配器
 */
public class CouponListAdapter extends RecyclerView.Adapter<CouponListAdapter.ViewHolder> {

    List<CouponsBean> couponList;
    private Context mContext;
    private CouponToUseListener couponToUseListener;
    Intent mIntent = new Intent();
    public CouponListAdapter(Context mContext, CouponToUseListener couponToUseListener) {

        this.mContext = mContext;
        this.couponToUseListener = couponToUseListener;
    }

    public void setCouponList(List<CouponsBean> couponList) {

        this.couponList = couponList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_coupon_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            CouponsBean couponResponse = couponList.get(itemPosition);
            String title = couponResponse.getTitle();
            String intro = couponResponse.getIntro();
            double fullFee = couponResponse.getFullFee();
            double discountFee = couponResponse.getDiscountFee();
            long startTime = couponResponse.getStartTime();
            long endTime = couponResponse.getEndTime();
            String couponRange = couponResponse.getCouponRange();
            //优惠卷状态 0未领取 10未使用 11占用中 12已使用 13已过期 14已作废
            int couponStatus = couponResponse.getCouponStatus();  //优惠券状态
          //  int couponStatus = 12;  //优惠券状态

            holder.mTvCouponTypeTitle.setText(title);
            holder.mTvCouponIntro.setText(intro);
            holder.mTvCouponFullFee.setText("满" + String.valueOf(fullFee) + "可用");
            holder.mTvCouponDiscountPrice.setText(String.valueOf(discountFee));

            String startTimeStr = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat, startTime);
            String endTimeStr = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat, endTime);

            holder.mTvCouponPeriodOfValidity.setText(startTimeStr + "至" + endTimeStr);

            holder.mTvCouponToUse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String mchScenicValue = CouponRangeTypeEnum.MCH_SCENIC.getValue();  //景点
                    String mchHotel1Value = CouponRangeTypeEnum.MCH_HOTEL1.getValue();  //酒店
                    String mchHotel2Value = CouponRangeTypeEnum.MCH_HOTEL2.getValue();  //民宿
                    String mchCarValue = CouponRangeTypeEnum.CAR.getValue();  //用车
                    String mchRecreationValue = CouponRangeTypeEnum.MCH_RECREATION.getValue();  //互动
                    String mchAllValue = CouponRangeTypeEnum.ALL.getValue();  //通用券

                    if (couponRange.equals(mchScenicValue)) {  //景点

                        mIntent.setClass(mContext, ShoppingMallScenicSpotActivity.class);
                        mContext.startActivity(mIntent);

                    } else if (couponRange.equals(mchCarValue)) {  //用车


                    } else if (couponRange.equals(mchRecreationValue)) {  //互动

                        mIntent.setClass(mContext, ShoppingMallInteractionActivity.class);
                        mContext.startActivity(mIntent);

                    } else if (couponRange.equals(mchHotel1Value)) {  //酒店

                        mIntent.setClass(mContext, ShoppingMallHotelActivity.class);
                        mContext.startActivity(mIntent);

                    } else if (couponRange.equals(mchHotel2Value)) {  //名宿

                        mIntent.setClass(mContext, ShoppingMallHomestayActivity.class);
                        mContext.startActivity(mIntent);

                    }else if (couponRange.equals(mchAllValue)) {  //通用券

                        couponToUseListener.setAllCouponUseCallback();
                    }

                }
            });

            holder.mLlytCouponItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    couponToUseListener.setCouponDesCallback(intro);
                }
            });

            //10未使用 11占用中 12已使用 13已过期 14已作废
          /*  int couponNotUsedKey = CouponStatusTypeEnum.COUPON_NOT_USED.getKey();
            int couponExpiredKey = CouponStatusTypeEnum.COUPON_EXPIRED.getKey();
            int couponUsedKey = CouponStatusTypeEnum.COUPON_USED.getKey();*/
            int mCouponNotUsedStatus = CouponStatusTypeEnum.COUPON_NOT_USED.getKey();
            int mCouponUsedStatus = CouponStatusTypeEnum.COUPON_USED.getKey();
            int mCouponExpiredStatus = CouponStatusTypeEnum.COUPON_EXPIRED.getKey();

            if(couponStatus == mCouponNotUsedStatus) {
                String couponStatusValue = CouponStatusTypeEnum.getEnumByKey(couponStatus).getValue();

                if (!TextUtils.isEmpty(couponStatusValue)) {
                    holder.mTvCouponToUse.setText(couponStatusValue);
                }

                String mchScenicValue = CouponRangeTypeEnum.MCH_SCENIC.getValue();  //景点
                String mchHotel1Value = CouponRangeTypeEnum.MCH_HOTEL1.getValue();  //酒店
                String mchCarValue = CouponRangeTypeEnum.CAR.getValue();  //用车
                String mchRecreationValue = CouponRangeTypeEnum.MCH_RECREATION.getValue();  //互动
                String mchAllValue = CouponRangeTypeEnum.ALL.getValue();  //通用券

                if (couponRange != null) {

                    holder.mTvCouponToUse.setText(mContext.getString(R.string.str_to_use_ticket));
                    holder.mTvCouponToUse.setEnabled(true);
                    if (couponRange.equals(mchScenicValue)) {  //景点

                        holder.mTvRenMinBiSymbol.setTextColor(mContext.getResources().getColor(R.color.color_green8));
                        holder.mTvCouponDiscountPrice.setTextColor(mContext.getResources().getColor(R.color.color_green8));

                        holder.mTvCouponToUse.setBackgroundResource(R.mipmap.bg_ibtn_green_scenic_spot_coupon_use);

                    } else if (couponRange.equals(mchCarValue)) {  //用车

                        holder.mTvRenMinBiSymbol.setTextColor(mContext.getResources().getColor(R.color.color_text_blue4));
                        holder.mTvCouponDiscountPrice.setTextColor(mContext.getResources().getColor(R.color.color_text_blue4));
                        holder.mTvCouponToUse.setBackgroundResource(R.mipmap.bg_ibtn_blue_car_coupon_use);

                    } else if (couponRange.equals(mchRecreationValue)) {  //互动

                        holder.mTvRenMinBiSymbol.setTextColor(mContext.getResources().getColor(R.color.color_light_red));
                        holder.mTvCouponDiscountPrice.setTextColor(mContext.getResources().getColor(R.color.color_light_red));
                        holder.mTvCouponToUse.setBackgroundResource(R.mipmap.bg_ibtn_light_red_interaction_coupon_use);

                    } else if (couponRange.equals(mchHotel1Value)) {  //酒店

                        holder.mTvRenMinBiSymbol.setTextColor(mContext.getResources().getColor(R.color.color_yellow));
                        holder.mTvCouponDiscountPrice.setTextColor(mContext.getResources().getColor(R.color.color_yellow));
                        holder.mTvCouponToUse.setBackgroundResource(R.mipmap.bg_ibtn_yellow_hotel_coupon_use);
                    } else if (couponRange.equals(mchAllValue)) {  //通用券

                        holder.mTvRenMinBiSymbol.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                        holder.mTvCouponDiscountPrice.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                        holder.mTvCouponToUse.setBackgroundResource(R.mipmap.bg_ibtn_orange_platform_universal_use);
                    }

                    holder.mTvCouponIntro.setTextColor(mContext.getResources().getColor(R.color.color_text_gray43));
                    holder.mTvCouponPeriodOfValidity.setTextColor(mContext.getResources().getColor(R.color.color_text_gray43));
                    holder.mTvCouponFullFee.setTextColor(mContext.getResources().getColor(R.color.color_text_gray43));

                }
            } else {

                if (couponStatus == mCouponUsedStatus)
                {
                    holder.mTvCouponToUse.setBackgroundResource(R.mipmap.icon_coupon_used);

                } else if (couponStatus == mCouponExpiredStatus) {

                    holder.mTvCouponToUse.setBackgroundResource(R.mipmap.icon_coupon_expired);
                }
                holder.mTvCouponToUse.setText("");
                holder.mTvCouponToUse.setEnabled(false);
                holder.mTvCouponDiscountPrice.setTextColor(mContext.getResources().getColor(R.color.color_text_gray44));
                holder.mTvRenMinBiSymbol.setTextColor(mContext.getResources().getColor(R.color.color_text_gray44));
                holder.mTvCouponIntro.setTextColor(mContext.getResources().getColor(R.color.color_text_gray44));
                holder.mTvCouponFullFee.setTextColor(mContext.getResources().getColor(R.color.color_text_gray44));
            }
          /*  if (couponRange.equals(goodsTypeTicket)) {  //景点

                mIntent.setClass(mContext, ScenicSpotDetailActivity.class);
                mIntent.putExtra("mchId", dataId);
                mContext.startActivity(mIntent);

            } else if (goodsType.equals(goodsTypeRecreation)) {  //互动

                mIntent.setClass(mContext, ScenicSpotDetailActivity.class);
                mIntent.putExtra("mchId", dataId);
                mContext.startActivity(mIntent);

            } else if (goodsType.equals(goodsTypeGroup)) { //组合

                mIntent.setClass(mContext, GroupMchDetailsActivity.class);
                mIntent.putExtra("packageId", dataId);
                mContext.startActivity(mIntent);

            } else if (goodsType.equals(goodsTypeHotel)) {  //酒店

                mIntent.setClass(mContext, HotelDetailsActivity.class);
                mIntent.putExtra("mchId", dataId);
                mContext.startActivity(mIntent);

            }else if (goodsType.equals(goodsTypeHotelHomestay)) {  //民宿

                mIntent.setClass(mContext, HomestayDetailActivity.class);
                mIntent.putExtra("mchId", dataId);
                mContext.startActivity(mIntent);

            }*/

            //color_green8
            //color_yellow
            //color_text_blue4
            //color_green8
            //color_light_red
            //color_text_orange2

            //  color_text_gray44

            //color_green8
            //color_yellow
            //color_text_blue4
            //color_green8
            //color_light_red
            //color_text_orange2

            //color_text_gray43
            //  color_text_gray44
           // color_text_gray45
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //优惠券类型标题
        @BindView(R.id.tv_coupon_type_title)
        TextView mTvCouponTypeTitle;

        //优惠券简介
        @BindView(R.id.tv_coupon_intro)
        TextView mTvCouponIntro;

        //优惠金额
        @BindView(R.id.tv_coupon_discount_price)
        TextView mTvCouponDiscountPrice;

        //满多少可用
        @BindView(R.id.tv_coupon_full_fee)
        TextView mTvCouponFullFee;

        //优惠券有效期
        @BindView(R.id.tv_coupon_period_of_validity)
        TextView mTvCouponPeriodOfValidity;

        //优惠券去使用
        @BindView(R.id.tv_coupon_to_use)
        TextView mTvCouponToUse;

        @BindView(R.id.llyt_coupon_item)
        LinearLayout mLlytCouponItem;

        @BindView(R.id.tv_renminbi_symbol)
        TextView mTvRenMinBiSymbol;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface CouponToUseListener {

        void setCouponToUseCallback(int position,CouponsBean couponResponse);

        void setCouponDesCallback(String intro);

        void setAllCouponUseCallback();
    }
}
