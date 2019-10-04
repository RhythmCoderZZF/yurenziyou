package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.OrderDetailResponse;
import com.nbhysj.coupon.ui.PartialApplyForRefundActivity;
import com.nbhysj.coupon.util.Tools;

import java.util.List;

/**
 * @author hysj created at 2019/10/03.
 * description:订单详情价格明细
 */
public class GoodsMealPriceDetailAdapter extends RecyclerView.Adapter<GoodsMealPriceDetailAdapter.CardHolder> {

    private Context mContext;
    //订单列表
    List<OrderDetailResponse.OrderGoodsEntity> orderGoodsList;

    public GoodsMealPriceDetailAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setOrderDetailDialogList(List<OrderDetailResponse.OrderGoodsEntity> orderGoodsList) {

        this.orderGoodsList = orderGoodsList;

    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_price_details_dialog_item, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {

        OrderDetailResponse.OrderGoodsEntity orderGoodsEntity = orderGoodsList.get(position);
        //商户名
        String mchName = orderGoodsEntity.getMchName();
        //商品标题
        String goodsTitle = orderGoodsEntity.getGoodsTitle();
        //商品可用到期时间
        String goodTime = orderGoodsEntity.getGoodTime();

        String goodsTotalFee = orderGoodsEntity.getGoodsTotalFee();
        String goodsNum = orderGoodsEntity.getGoodsNum();
        String goodsId = orderGoodsEntity.getGoodsId();
        String price = orderGoodsEntity.getPrice();

        if (!TextUtils.isEmpty(mchName)) {
            holder.mTvMchName.setText(mchName);
        }

        if (!TextUtils.isEmpty(goodsTitle)) {
            holder.mTvGoodsTitle.setText(goodsTitle);
        }

        holder.mTvTicketCanUseTime.setText(goodTime);

        if (!TextUtils.isEmpty(goodsTotalFee)) {
            holder.mTvTotalPrice.setText(goodsTotalFee);
        }

        holder.mTvGoodsNum.setText(goodsNum);

        holder.mTvUnitPrice.setText(price);
    }

    @Override
    public int getItemCount() {
        return orderGoodsList.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        //商户名字
        TextView mTvMchName;
        //酒店民宿描述
        TextView mHotelHomestayDes;
        //商品名称
        TextView mTvGoodsTitle;
        //票可使用时间
        TextView mTvTicketCanUseTime;
        //总价
        TextView mTvTotalPrice;
        //商品数量
        TextView mTvGoodsNum;
        //申请退款
        TextView mTvUnitPrice;

        public CardHolder(View itemView) {
            super(itemView);

            mTvMchName = itemView.findViewById(R.id.tv_mch_name);
            mHotelHomestayDes = itemView.findViewById(R.id.tv_hotel_homestay_des);
            mTvGoodsTitle = itemView.findViewById(R.id.tv_goods_title);
            mTvTicketCanUseTime = itemView.findViewById(R.id.tv_ticket_can_use_time);
            mTvTotalPrice = itemView.findViewById(R.id.tv_total_price);
            mTvGoodsNum = itemView.findViewById(R.id.tv_goods_num);
            mTvUnitPrice = itemView.findViewById(R.id.tv_unit_price);

        }
    }
}
