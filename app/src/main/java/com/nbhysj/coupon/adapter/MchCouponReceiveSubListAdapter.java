package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.CouponRangeTypeEnum;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.util.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/10/31.
 * description : 优惠券领取适配器
 */
public class MchCouponReceiveSubListAdapter extends RecyclerView.Adapter<MchCouponReceiveSubListAdapter.ViewHolder> {

    List<CouponsBean> couponList;
    private Context mContext;
    private CouponReceiveListener couponReceiveListener;

    public MchCouponReceiveSubListAdapter(Context mContext, CouponReceiveListener couponReceiveListener) {

        this.couponReceiveListener = couponReceiveListener;
        this.mContext = mContext;
    }

    public void setCouponList(List<CouponsBean> couponList) {

        this.couponList = couponList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mch_receive_coupon_sub_item, parent, false);//解决宽度不能铺满
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
          //  String couponRange = CouponRangeTypeEnum.MCH_SCENIC.getValue();
            String couponRange = couponResponse.getCouponRange();
            int status = couponResponse.getGetStatus();
            holder.mTvCouponTypeTitle.setText(title);
            holder.mTvCouponIntro.setText(intro);
            holder.mTvCouponFullFee.setText("满" + String.valueOf(fullFee) + "可用");
            holder.mTvCouponDiscountPrice.setText(String.valueOf(discountFee));

            String startTimeStr = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat, startTime);
            String endTimeStr = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat, endTime);

            holder.mTvCouponPeriodOfValidity.setText(startTimeStr + "至" + endTimeStr);

            if(status == 0)
            {
                holder.mImgCouponReceiveTag.setVisibility(View.VISIBLE);
                holder.mTvReceiveImmediately.setText(mContext.getResources().getString(R.string.str_use_immediately));

            } else if(status == 1)
            {
                holder.mImgCouponReceiveTag.setVisibility(View.GONE);
                holder.mTvReceiveImmediately.setText(mContext.getResources().getString(R.string.str_collect_immediately));
            }

            String mchGoodsValue = CouponRangeTypeEnum.MCH_GOODS.getValue();  //商品
            String mchScenicValue = CouponRangeTypeEnum.MCH_SCENIC.getValue();  //景点
            String mchHotel1Value = CouponRangeTypeEnum.MCH_HOTEL1.getValue();  //酒店
            String mchCarValue = CouponRangeTypeEnum.CAR.getValue();  //用车
            String mchRecreationValue = CouponRangeTypeEnum.MCH_RECREATION.getValue();  //互动
            String mchAllValue = CouponRangeTypeEnum.ALL.getValue();  //通用券

            if (couponRange != null) {

                if (couponRange.equals(mchAllValue) || couponRange.equals(mchGoodsValue)) {  //通用券

                    holder.mTvRenMinBiSymbol.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                    holder.mTvCouponDiscountPrice.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                    holder.mTvCouponTypeTitle.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                    holder.mTvCouponIntro.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                    holder.mTvCouponFullFee.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                    holder.mTvCouponDiscountPrice.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                    holder.mTvCouponPeriodOfValidity.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                    holder.mTvCouponPeriodOfValidityTag.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                    holder.mTvReceiveImmediately.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                } else {

                    holder.mTvRenMinBiSymbol.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.mTvCouponDiscountPrice.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.mTvCouponTypeTitle.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.mTvCouponIntro.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.mTvCouponFullFee.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.mTvCouponDiscountPrice.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.mTvCouponPeriodOfValidity.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.mTvCouponPeriodOfValidityTag.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.mTvReceiveImmediately.setTextColor(mContext.getResources().getColor(R.color.white));

                    if (couponRange.equals(mchScenicValue)) {  //景点

                        holder.mLlytMchReceiveTopleftAndBottomleft.setBackgroundResource(R.drawable.shape_green_radius_topleft_and_bottomleft);
                        holder.mRlytMchReceiveToptightAndBottomright.setBackgroundResource(R.drawable.shape_green_radius_toptight_and_bottomright);
                        holder.mRlytBgCouponReceiveDottedLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_green8));

                    } else if (couponRange.equals(mchCarValue)) {  //用车

                        holder.mLlytMchReceiveTopleftAndBottomleft.setBackgroundResource(R.drawable.shape_blue_radius_topleft_and_bottomleft);
                        holder.mRlytMchReceiveToptightAndBottomright.setBackgroundResource(R.drawable.shape_blue_radius_toptight_and_bottomright);
                        holder.mRlytBgCouponReceiveDottedLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_text_blue4));


                    } else if (couponRange.equals(mchRecreationValue)) {  //互动

                        holder.mLlytMchReceiveTopleftAndBottomleft.setBackgroundResource(R.drawable.shape_light_red_radius_topleft_and_bottomleft);
                        holder.mRlytMchReceiveToptightAndBottomright.setBackgroundResource(R.drawable.shape_light_red_radius_toptight_and_bottomright);
                        holder.mRlytBgCouponReceiveDottedLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_light_red));

                    } else if (couponRange.equals(mchHotel1Value)) {  //酒店

                        holder.mLlytMchReceiveTopleftAndBottomleft.setBackgroundResource(R.drawable.shape_yellow_radius_topleft_and_bottomleft);
                        holder.mRlytMchReceiveToptightAndBottomright.setBackgroundResource(R.drawable.shape_yellow_radius_toptight_and_bottomright);
                        holder.mRlytBgCouponReceiveDottedLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_yellow));


                    } /*else if (couponRange.equals(mchAllValue)) {  //通用券

                        holder.mTvRenMinBiSymbol.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                        holder.mTvCouponDiscountPrice.setTextColor(mContext.getResources().getColor(R.color.color_text_orange2));
                    }*/
                }
            }

            holder.mLlytCouponItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    couponReceiveListener.setCouponReceiveCallback(itemPosition,couponResponse);

                }
            });

            holder.mTvReceiveImmediately.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    couponReceiveListener.setCouponReceiveCallback(itemPosition,couponResponse);
                }
            });


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

        //优惠券有效期标签
        @BindView(R.id.tv_coupon_period_of_validity_tag)
        TextView mTvCouponPeriodOfValidityTag;

        @BindView(R.id.llyt_coupon_item)
        LinearLayout mLlytCouponItem;

        @BindView(R.id.tv_renminbi_symbol)
        TextView mTvRenMinBiSymbol;

        @BindView(R.id.rlyt_bg_coupon_receive_dotted_line)
        RelativeLayout mRlytBgCouponReceiveDottedLine;

        @BindView(R.id.llyt_mch_receive_topleft_and_bottomleft)
        LinearLayout mLlytMchReceiveTopleftAndBottomleft;

        @BindView(R.id.rlyt_mch_receive_toptight_and_bottomright)
        RelativeLayout mRlytMchReceiveToptightAndBottomright;

        //立即领取
        @BindView(R.id.tv_receive_immediately)
        TextView mTvReceiveImmediately;

        //优惠券领取
        @BindView(R.id.img_coupon_receive_tag)
        ImageView mImgCouponReceiveTag;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface CouponReceiveListener {

        void setCouponReceiveCallback(int position,CouponsBean couponResponse);
    }
}
