package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;

import java.util.List;

/**
 * @author hysj created at 2019/05/09.
 * description:
 */
public class MyOrderTicketSubListAdapter extends RecyclerView.Adapter<MyOrderTicketSubListAdapter.ViewHolder> {


    List<UserOrderListResponse.GoodsEntity> userOrderList;
    private Context mContext;

    public MyOrderTicketSubListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setUserOrderList(List<UserOrderListResponse.GoodsEntity> userOrderList) {

        this.userOrderList = userOrderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_order_ticket_sub_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            UserOrderListResponse.GoodsEntity goodsEntity = userOrderList.get(itemPosition);
            int goodsNum = goodsEntity.getGoodsNum();
            String goodsTime = goodsEntity.getGoodsTime();
            double goodsPrice = goodsEntity.getGoodsPrice();
            holder.mTvTicketTitle.setText(goodsEntity.getGoodsTitle());

            holder.mTvTicketPrice.setText(Tools.getTwoDecimalPoint(goodsPrice));
            holder.mTvTicketPurchaseNum.setText("x" + goodsNum);
            holder.mTvTicketTime.setText(goodsTime);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return userOrderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //票标题
        TextView mTvTicketTitle;
        //票使用时间
        TextView mTvTicketTime;
        //票价
        TextView mTvTicketPrice;
        //购买数量
        TextView mTvTicketPurchaseNum;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvTicketTitle = itemView.findViewById(R.id.tv_ticket_title);
            mTvTicketTime = itemView.findViewById(R.id.tv_ticket_time);
            mTvTicketPrice = itemView.findViewById(R.id.tv_ticket_price);
            mTvTicketPurchaseNum = itemView.findViewById(R.id.tv_ticket_num);

        }
    }
}
