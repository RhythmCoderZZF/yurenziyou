package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.GoodsPriceDatesResponse;
import com.nbhysj.coupon.model.response.OrderSubmitInitResponse;
import com.nbhysj.coupon.util.DateUtil;

import java.util.List;

/**
 * @author hysj created at 2019/4/27.
 * description:订单使用日期选择适配器
 */
public class GroupMchOrderUseDateSelectAdapter extends RecyclerView.Adapter<GroupMchOrderUseDateSelectAdapter.ViewHolder> {

    List<OrderSubmitInitResponse.GoodsPriceEntity> goodsList;
    List<GoodsPriceDatesResponse> orderSubmitDateList;
    private Context mContext;
    private GroupMchOrderUseDateSelectListener orderUseDateSelectListener;
    private GroupMchOrderUseDateSubSelectAdapter orderUseDateSelectAdapter;

    public GroupMchOrderUseDateSelectAdapter(Context mContext, GroupMchOrderUseDateSelectListener orderUseDateSelectListener) {

        this.mContext = mContext;
        this.orderUseDateSelectListener = orderUseDateSelectListener;
    }

    public void setOrderUseDateSelectList(List<OrderSubmitInitResponse.GoodsPriceEntity> goodsList) {

        this.goodsList = goodsList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_mch_date_select_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int groupItemPosition) {

        try {

            OrderSubmitInitResponse.GoodsPriceEntity goodsPriceEntity = goodsList.get(groupItemPosition);
            String title = goodsPriceEntity.getTitle();

            if(!TextUtils.isEmpty(title))
            {
                holder.mTvGroupMchName.setText(title + "使用日期");
            }

            //订单提交日期
            orderSubmitDateList = DateUtil.getOrderSubmitDate();

            //商品价格日期
            List<GoodsPriceDatesResponse> goodsPriceDatesList = goodsPriceEntity.getGoodsPriceDates();

            for (int i = 0; i < orderSubmitDateList.size(); i++)
            {
                GoodsPriceDatesResponse orderSubmitDateResponse = orderSubmitDateList.get(i);
                String orderSubmitDate = orderSubmitDateResponse.getDate();
                for (int j = 0; j < goodsPriceDatesList.size(); j++) {
                    GoodsPriceDatesResponse goodsPriceDatesResponse = goodsPriceDatesList.get(j);
                    String goodsPriceDate = goodsPriceDatesResponse.getDate();
                    int id = goodsPriceDatesResponse.getId();
                    double price = goodsPriceDatesResponse.getPrice();

                    if (orderSubmitDate.equals(goodsPriceDate)) {
                        orderSubmitDateResponse.setIsCanBooking(1);
                        orderSubmitDateResponse.setId(id);
                        orderSubmitDateResponse.setPrice(price);
                        orderSubmitDateResponse.setSelectDatePrice(true);
                    }
                }
            }
            goodsPriceEntity.setGoodsPriceSelectList(orderSubmitDateList);
            GridLayoutManager layoutManager = new GridLayoutManager(mContext, 4);
            holder.mRvUseTicketDate.setLayoutManager(layoutManager);
            orderUseDateSelectAdapter = new GroupMchOrderUseDateSubSelectAdapter(mContext, new GroupMchOrderUseDateSubSelectAdapter.OrderUseDateSelectListener() {
                @Override
                public void moreDatesCallBack() {

                    orderUseDateSelectListener.setMoreDatesCallBack(groupItemPosition);
                }

                @Override
                public void datePriceSelectCallBack(int dateSubSelectPosition) {

                    orderUseDateSelectListener.setDatePriceSelectCallBack(groupItemPosition,dateSubSelectPosition);
                }
            });

            orderUseDateSelectAdapter.setOrderUseDateSelectList(orderSubmitDateList);
            holder.mRvUseTicketDate.setAdapter(orderUseDateSelectAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {

            return goodsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //商户名
        TextView mTvGroupMchName;
        //价格
        RecyclerView mRvUseTicketDate;

        public ViewHolder(View itemView) {
            super(itemView);

            mRvUseTicketDate = itemView.findViewById(R.id.rv_use_ticket_date);
            mTvGroupMchName = itemView.findViewById(R.id.tv_group_mch_name);
        }
    }

    public List<GoodsPriceDatesResponse> getOrderSubmitDateList(){

        return orderSubmitDateList;
    }

    public interface GroupMchOrderUseDateSelectListener {

        void setMoreDatesCallBack(int position);

        void setDatePriceSelectCallBack(int groupPosition,int childPosition);
    }
}
