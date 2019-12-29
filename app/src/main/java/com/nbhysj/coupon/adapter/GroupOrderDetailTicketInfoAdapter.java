package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.GoodsPriceDatesResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.util.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/22.
 * description : 订单明细购票信息适配器
 */
public class GroupOrderDetailTicketInfoAdapter extends RecyclerView.Adapter<GroupOrderDetailTicketInfoAdapter.ViewHolder> {

    List<OrderSubmitInitResponse.GoodsPriceEntity> goodsPriceList;
    private Context mContext;

    public GroupOrderDetailTicketInfoAdapter(Context mContext) {

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
            double defaultPrice = goodsPriceEntity.getDefaultPrice();
            holder.mTvTicketTitle.setText(title + "X" + 1);
            double mTotalTicketPrice = defaultPrice;
           List<GoodsPriceDatesResponse> goodsPriceDateList = goodsPriceEntity.getGoodsPriceSelectList();
            for (int i = 0; i < goodsPriceDateList.size();i++) {
                GoodsPriceDatesResponse goodsPriceDatesResponse = goodsPriceDateList.get(i);
                boolean isSelectDatePrice = goodsPriceDatesResponse.isSelectDatePrice();
                if(isSelectDatePrice)
                {
                    holder.mTvTicketPrice.setText("¥" + Tools.getTwoDecimalPoint(mTotalTicketPrice));
                }
            }

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
