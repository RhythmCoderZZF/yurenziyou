package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CouponsBean;
import com.nbhysj.coupon.model.response.MchCouponResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/10/31.
 * description : 优惠券领取适配器
 */
public class MchCouponReceiveListAdapter extends RecyclerView.Adapter<MchCouponReceiveListAdapter.ViewHolder> {

    List<MchCouponResponse> couponList;
    private Context mContext;
    private CouponReceiveListener couponReceiveListener;

    public MchCouponReceiveListAdapter(Context mContext,CouponReceiveListener couponReceiveListener) {

        this.couponReceiveListener = couponReceiveListener;
        this.mContext = mContext;
    }

   public void setCouponList(List<MchCouponResponse> couponList) {

        this.couponList = couponList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mch_receive_coupon_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int groupPosition) {

        try {

            MchCouponResponse mchCouponResponse = couponList.get(groupPosition);
            String title = mchCouponResponse.getType();
            List<CouponsBean> couponsBeanList = mchCouponResponse.getCoupons();
            holder.mTvCouponTypeInfo.setText(title + "优惠券");

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
            holder.mRvCouponTypeList.setLayoutManager(linearLayoutManager);

            MchCouponReceiveSubListAdapter mchCouponReceiveSubListAdapter = new MchCouponReceiveSubListAdapter(mContext, new MchCouponReceiveSubListAdapter.CouponReceiveListener() {
                @Override
                public void setCouponReceiveCallback(int childPosition,CouponsBean couponResponse)
                {
                    couponReceiveListener.setCouponReceiveCallback(groupPosition,childPosition,couponResponse);
                }
            });
            mchCouponReceiveSubListAdapter.setCouponList(couponsBeanList);
            holder.mRvCouponTypeList.setAdapter(mchCouponReceiveSubListAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_coupon_type_info)
        TextView mTvCouponTypeInfo;

        @BindView(R.id.llyt_coupon_item)
        LinearLayout mLlytCouponItem;

        @BindView(R.id.rv_coupon_type_list)
        RecyclerView mRvCouponTypeList;
        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface CouponReceiveListener{

        void setCouponReceiveCallback(int groupPosition,int childPosition,CouponsBean couponsBean);
    }
}
