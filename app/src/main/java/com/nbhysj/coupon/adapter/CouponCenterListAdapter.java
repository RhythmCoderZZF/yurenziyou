package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.GoodsTypeEnum;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.ui.GroupMchDetailsActivity;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/19.
 * description : 票券中心适配器
 */
public class CouponCenterListAdapter extends RecyclerView.Adapter<CouponCenterListAdapter.ViewHolder> {

    List<CouponsBean> couponList;
    private Context mContext;
    private CouponToUseListener couponToUseListener;

    public CouponCenterListAdapter(Context mContext, CouponToUseListener couponToUseListener) {

        this.mContext = mContext;
        this.couponToUseListener = couponToUseListener;
    }

   public void setCouponList(List<CouponsBean> couponList) {

        this.couponList = couponList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ticket_center_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            CouponsBean couponResponse = couponList.get(itemPosition);
            int couponId = couponResponse.getId();
            String title = couponResponse.getTitle();
            String intro = couponResponse.getIntro();
            double fullFee = couponResponse.getFullFee();
            double discountFee = couponResponse.getDiscountFee();
            long startTime = couponResponse.getStartTime();
            long endTime = couponResponse.getEndTime();
            String couponRange = couponResponse.getCouponRange();

            holder.mTvCouponTypeTitle.setText(title);
            holder.mTvCouponIntro.setText(intro);
            holder.mTvCouponFullFee.setText("满" + String.valueOf(fullFee) + "可用");
            holder.mTvCouponDiscountPrice.setText(String.valueOf(discountFee));

            String startTimeStr = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat,startTime);
            String endTimeStr = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat,endTime);

            holder.mTvCouponPeriodOfValidity.setText(startTimeStr + "至" + endTimeStr);

            holder.mTvCouponToUse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    couponToUseListener.setCouponToUseCallback(itemPosition,couponId);

                }
            });

            holder.mLlytCouponItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    couponToUseListener.setCouponDesCallback(intro);
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

    public interface CouponToUseListener{

        void setCouponToUseCallback(int position,int couponId);

        void setCouponDesCallback(String intro);
    }
}
