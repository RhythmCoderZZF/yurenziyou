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

import java.util.List;

public class GoodsMealDetailAdapter extends RecyclerView.Adapter<GoodsMealDetailAdapter.CardHolder> {

    private Context mContext;
    //订单列表
    List<OrderDetailResponse.OrderGoodsEntity> orderGoodsList;

    private PartialApplyForRefundListener applyForRefundListener;
    public GoodsMealDetailAdapter(Context mContext,PartialApplyForRefundListener partialApplyForRefundListener) {
        this.mContext = mContext;
        this.applyForRefundListener = partialApplyForRefundListener;
    }

    public void setOrderMealList(List<OrderDetailResponse.OrderGoodsEntity> orderGoodsList){

        this.orderGoodsList = orderGoodsList;


    }
    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_package_details_item, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {

        OrderDetailResponse.OrderGoodsEntity orderGoodsEntity = orderGoodsList.get(position);
        int orderGoodsId = orderGoodsEntity.getOrderGoodsId();
        //商户名
        String mchName = orderGoodsEntity.getMchName();
        //商品标题
        String goodsTitle = orderGoodsEntity.getGoodsTitle();
        //商品可用到期时间
        String goodTime = orderGoodsEntity.getGoodTime();

        String usedNum = orderGoodsEntity.getUsedNum();
        String surplusNum = orderGoodsEntity.getSurplusNum();
        String goodsTotalFee = orderGoodsEntity.getGoodsTotalFee();
        String goodNum = orderGoodsEntity.getGoodsNum();
        String goodsId = orderGoodsEntity.getGoodsId();
        int canRefundStatus = orderGoodsEntity.getCanRefundStatus();
        String goodsType = orderGoodsEntity.getGoodType();

        //商户名
        if(!TextUtils.isEmpty(mchName))
        {
            holder.mTvMchName.setText(mchName);
        }

        //商品名称
        if(!TextUtils.isEmpty(goodsTitle))
        {
            holder.mTvTicketTitle.setText(goodsTitle);
        }

        //商品可用时间
        if(!TextUtils.isEmpty(goodTime))
        {
            holder.mTvTicketCanUseTime.setText(goodTime);
        }

        holder.mTvTicketAlreadyUsed.setText(usedNum + "张");

        //剩余票数量
        if(!TextUtils.isEmpty(surplusNum))
        {
             holder.mTvTicketRemainder.setText(surplusNum + "张");
        }

        if(!TextUtils.isEmpty(goodsTotalFee))
        {
            holder.mTvAmountOfMoney.setText(goodsTotalFee);
        }

        //是否可退款
        if(canRefundStatus == 0)
        {
            holder.mTvApplyForRefund.setVisibility(View.GONE);

        } else if(canRefundStatus == 1)
        {
            holder.mTvApplyForRefund.setVisibility(View.VISIBLE);
        }

        //商品数量
        holder.mTvGoodNum.setText("x" + goodNum);

        holder.mTvApplyForRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                applyForRefundListener.setPartialApplyForRefundListener(orderGoodsId,goodsType);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderGoodsList.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        //商户名字
        TextView mTvMchName;
        //票类型
        TextView mTvTicketTitle;
        //可使用时间
        TextView mTvTicketCanUseTime;
        //已用票数
        TextView mTvTicketAlreadyUsed;
        //剩余票数
        TextView mTvTicketRemainder;
        //金额
        TextView mTvAmountOfMoney;
        //商品数量
        TextView mTvGoodNum;
        //申请退款
        TextView mTvApplyForRefund;
        public CardHolder(View itemView) {
            super(itemView);

            mTvMchName = itemView.findViewById(R.id.tv_mch_name);
            mTvTicketTitle = itemView.findViewById(R.id.tv_ticket_title);
            mTvTicketCanUseTime = itemView.findViewById(R.id.tv_ticket_can_use_time);
            mTvTicketAlreadyUsed = itemView.findViewById(R.id.tv_ticket_already_used);
            mTvTicketRemainder = itemView.findViewById(R.id.tv_ticket_remainder);
            mTvAmountOfMoney = itemView.findViewById(R.id.tv_amount_of_money);
            mTvGoodNum = itemView.findViewById(R.id.tv_good_num);
            mTvApplyForRefund = itemView.findViewById(R.id.tv_apply_for_refund);

        }
    }

    //部分商品申请退款
    public interface PartialApplyForRefundListener{

        void setPartialApplyForRefundListener(int orderGoodsId,String goodsType);
    }
}
