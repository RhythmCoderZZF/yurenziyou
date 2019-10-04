package com.nbhysj.coupon.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.OrderDetailResponse;

import java.util.List;

/**
 * @author hysj created at 2019/08/19.
 * description:订单详情价格明细用车信息适配器
 */
public class OrderPriceDetailDialogVehicleUseAdapter extends RecyclerView.Adapter<OrderPriceDetailDialogVehicleUseAdapter.ViewHolder> {

    List<OrderDetailResponse.OrderCarEntity> carList;

    public OrderPriceDetailDialogVehicleUseAdapter() {

    }

    public void setVehicleUseList(List<OrderDetailResponse.OrderCarEntity> carList) {

        this.carList = carList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_price_detail_dialog_vehicle_use_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            OrderDetailResponse.OrderCarEntity carsBean = carList.get(itemPosition);
            String departureTime = carsBean.getDepartureTime();
            String estimatePrice = carsBean.getEstimatePrice();
            String startAddressName = carsBean.getStartAddress();
            String endAddressName = carsBean.getEndAddress();
            String cancelNote = carsBean.getCancelNote();
            String isRefund = carsBean.getIsRefund();

            holder.mTvAmountOfMoney.setText(estimatePrice);
            holder.mTvStartingPoint.setText(startAddressName);
            holder.mTvDestination.setText(endAddressName);
            holder.mTvVehicleUseTime.setText(departureTime);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //用车时间
        TextView mTvVehicleUseTime;
        //起始地点
        TextView mTvStartingPoint;
        //目的地
        TextView mTvDestination;
        //用车金额
        TextView mTvAmountOfMoney;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvVehicleUseTime = itemView.findViewById(R.id.tv_vehicle_use_time);
            mTvStartingPoint = itemView.findViewById(R.id.tv_starting_point);
            mTvDestination = itemView.findViewById(R.id.tv_destination);
            mTvAmountOfMoney = itemView.findViewById(R.id.tv_amount_of_money);
        }
    }
}
