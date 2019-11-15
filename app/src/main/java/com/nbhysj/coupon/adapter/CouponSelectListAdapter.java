package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.CouponRangeTypeEnum;
import com.nbhysj.coupon.common.Enum.CouponStatusTypeEnum;
import com.nbhysj.coupon.model.response.CouponsBean;
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
public class CouponSelectListAdapter extends RecyclerView.Adapter<CouponSelectListAdapter.ViewHolder> {

    List<CouponsBean> couponList;
    private Context mContext;
    private CouponSelectListener couponSelectListener;
    StringBuffer stringBuffer = new StringBuffer();
    public CouponSelectListAdapter(Context mContext, CouponSelectListener couponSelectListener) {

        this.mContext = mContext;
        this.couponSelectListener = couponSelectListener;
    }

    public void setCouponList(List<CouponsBean> couponList) {

        this.couponList = couponList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_coupon_select_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            CouponsBean couponResponse = couponList.get(itemPosition);
            int couponId = couponResponse.getId();
            String title = couponResponse.getTitle();

            holder.mTvCouponTypeTitle.setText(title);
            holder.mLlytCouponSelectItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isCouponSelect = couponResponse.isCouponSelect();
                    stringBuffer.setLength(0);
                    if(isCouponSelect){

                        couponResponse.setCouponSelect(false);
                    } else {

                        for(int i = 0; i < couponList.size();i++){

                            couponList.get(i).setCouponSelect(false);
                        }
                        couponResponse.setCouponSelect(true);
                    }
                    notifyDataSetChanged();
                    boolean couponSelect = couponResponse.isCouponSelect();
                    couponSelectListener.setCouponSelectCallback(couponId,couponSelect,title);
                }
            });

            if(couponResponse.isCouponSelect()){

                holder.mImgCouponSelect.setImageResource(R.mipmap.icon_conpon_select);
            } else {

                holder.mImgCouponSelect.setImageResource(R.mipmap.icon_conpon_unselect);
            }

            //10未使用 11占用中 12已使用 13已过期 14已作废
          /*  int couponNotUsedKey = CouponStatusTypeEnum.COUPON_NOT_USED.getKey();
            int couponExpiredKey = CouponStatusTypeEnum.COUPON_EXPIRED.getKey();
            int couponUsedKey = CouponStatusTypeEnum.COUPON_USED.getKey();*/
            int mCouponNotUsedStatus = CouponStatusTypeEnum.COUPON_NOT_USED.getKey();
            int mCouponUsedStatus = CouponStatusTypeEnum.COUPON_USED.getKey();
            int mCouponExpiredStatus = CouponStatusTypeEnum.COUPON_EXPIRED.getKey();


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

        //优惠券选择
        @BindView(R.id.llyt_coupon_select_item)
        LinearLayout mLlytCouponSelectItem;

        //优惠券类型标题
        @BindView(R.id.tv_coupon_type_title)
        TextView mTvCouponTypeTitle;

        @BindView(R.id.img_coupon_select)
        ImageView mImgCouponSelect;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface CouponSelectListener {

        void setCouponSelectCallback(int couponId,boolean isCouponSelect,String couponTitle);
    }
}
