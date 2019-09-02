package com.nbhysj.coupon.adapter;

import android.content.Context;
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
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.model.response.PopularScenicSpotsResponse;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/22.
 * description : 订单明细购票信息适配器
 */
public class OrderDetailTicketInfoAdapter extends RecyclerView.Adapter<OrderDetailTicketInfoAdapter.ViewHolder> {

    List<OrderSubmitInitResponse.GoodsPriceEntity> goodsPriceList;
    private Context mContext;

    public OrderDetailTicketInfoAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setOrderDetailTicketList(List<OrderSubmitInitResponse.GoodsPriceEntity> goodsPriceList) {

        this.goodsPriceList = goodsPriceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_detail_ticket_info_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsPriceList.get(itemPosition);
            int ticketPurchaseNum = goodsPriceEntity.getTicketPurchaseNum();
            String title = goodsPriceEntity.getTitle();
            int defaultPrice = goodsPriceEntity.getDefaultPrice();
            holder.mTvTicketTitle.setText(title + "X" + ticketPurchaseNum);
            int mTotalTicketPrice = defaultPrice * ticketPurchaseNum;
            holder.mTvTicketPrice.setText("¥" + String.valueOf(mTotalTicketPrice));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return goodsPriceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //票标题
        @BindView(R.id.tv_ticket_title)
        TextView mTvTicketTitle;

        //票价格
        @BindView(R.id.tv_ticket_price)
        TextView mTvTicketPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
