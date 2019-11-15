package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
 * description : 商户详情优惠券领取适配器
 */
public class MchDetailCouponListAdapter extends RecyclerView.Adapter<MchDetailCouponListAdapter.ViewHolder> {

    List<CouponsBean> couponList;
    private Context mContext;
    private CouponReceiveListener couponReceiveListener;

    public MchDetailCouponListAdapter(Context mContext, CouponReceiveListener couponReceiveListener) {

        this.couponReceiveListener = couponReceiveListener;
        this.mContext = mContext;
    }

   public void setCouponList(List<CouponsBean> couponList) {

        this.couponList = couponList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_flowlayout_tag_mch_detail_coupon_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            CouponsBean mchCouponResponse = couponList.get(itemPosition);
            String title = mchCouponResponse.getTitle();
            if(!TextUtils.isEmpty(title))
            {
                holder.mTvCouponInfoItem.setText(title);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_coupon_info_item)
        TextView mTvCouponInfoItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface CouponReceiveListener{

        void setCouponReceiveCallback(int position);
    }
}
