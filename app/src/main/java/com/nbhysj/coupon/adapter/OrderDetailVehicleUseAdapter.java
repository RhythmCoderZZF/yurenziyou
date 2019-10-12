package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.request.CarsBean;
import com.nbhysj.coupon.model.response.OrderDetailResponse;
import com.nbhysj.coupon.ui.PartialApplyForRefundActivity;
import com.nbhysj.coupon.util.Tools;

import java.util.List;

/**
 * @author hysj created at 2019/08/19.
 * description:订单用车信息适配器
 */
public class OrderDetailVehicleUseAdapter extends RecyclerView.Adapter<OrderDetailVehicleUseAdapter.ViewHolder> {

    List<OrderDetailResponse.OrderCarEntity> carList;

    private Context mContext;

    private PartialApplyForRefundListener applyForRefundListener;
    public OrderDetailVehicleUseAdapter(Context mContext,PartialApplyForRefundListener partialApplyForRefundListener) {

        this.mContext = mContext;
        this.applyForRefundListener = partialApplyForRefundListener;
    }

    public void setVehicleUseList(List<OrderDetailResponse.OrderCarEntity> carList) {

        this.carList = carList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_detail_vehicle_use_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            OrderDetailResponse.OrderCarEntity carsBean = carList.get(itemPosition);
            int orderGoodsId = carsBean.getOrderId();
            String goodsType = carsBean.getGoodsType();
            String departureTime = carsBean.getDepartureTime();
            String estimatePrice = carsBean.getEstimatePrice();
            String startAddressName = carsBean.getStartAddress();
            String endAddressName = carsBean.getEndAddress();
            String cancelNote = carsBean.getCancelNote();
            String isRefund = carsBean.getIsRefund();

            holder.mTvVehicleExpenses.setText(estimatePrice);
            holder.mTvStartingPoint.setText(startAddressName);
            holder.mTvDestination.setText(endAddressName);
            holder.mTvVehicleUseTime.setText(departureTime);


            if (TextUtils.isEmpty(isRefund) && TextUtils.isEmpty(cancelNote)) {   //可申请退款
                holder.mTvApplyForRefund.setVisibility(View.VISIBLE);
                holder.mTvTipsForTravel.setVisibility(View.GONE);

                holder.mTvOrderVehicleStatus.setVisibility(View.GONE);
                holder.mTvApplyForRefund.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        applyForRefundListener.setPartialApplyForRefundListener(orderGoodsId,goodsType);
                    }
                });
            } else if (TextUtils.isEmpty(isRefund) && !TextUtils.isEmpty(cancelNote)) {  //不可申请退款
                holder.mTvOrderVehicleStatus.setVisibility(View.VISIBLE);
                holder.mTvApplyForRefund.setVisibility(View.GONE);
                holder.mTvTipsForTravel.setVisibility(View.GONE);
                holder.mTvOrderVehicleStatus.setText(cancelNote);
            } else if (!TextUtils.isEmpty(isRefund) && !TextUtils.isEmpty(cancelNote)) {  //不可申请退款
                holder.mTvTipsForTravel.setVisibility(View.VISIBLE);
                holder.mTvApplyForRefund.setVisibility(View.GONE);
                holder.mTvApplyForRefund.setVisibility(View.GONE);
                holder.mTvTipsForTravel.setText(isRefund);
            }




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
        //用车金额
        TextView mTvVehicleExpenses;
        //起始地点
        TextView mTvStartingPoint;
        //目的地
        TextView mTvDestination;
        //总和
        TextView mTvAmountOfMoney;
        //待出行提示
        TextView mTvTipsForTravel;
        //待出行提示
        LinearLayout mLlytTipsForTravel;
        //申请退款
        TextView mTvApplyForRefund;
        //用车状态
        TextView mTvOrderVehicleStatus;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvVehicleUseTime = itemView.findViewById(R.id.tv_vehicle_use_time);
            mTvVehicleExpenses = itemView.findViewById(R.id.tv_vehicle_expenses);
            mTvStartingPoint = itemView.findViewById(R.id.tv_starting_point);
            mTvDestination = itemView.findViewById(R.id.tv_destination);
            mTvAmountOfMoney = itemView.findViewById(R.id.tv_amount_of_money);
            mTvTipsForTravel = itemView.findViewById(R.id.tv_tips_for_travel);
            mLlytTipsForTravel = itemView.findViewById(R.id.llyt_tips_for_travel);
            mTvApplyForRefund = itemView.findViewById(R.id.tv_apply_for_refund);
            mTvOrderVehicleStatus = itemView.findViewById(R.id.tv_order_vehicle_status);
        }
    }

    //部分商品申请退款
    public interface PartialApplyForRefundListener{

        void setPartialApplyForRefundListener(int orderGoodsId,String goodsType);
    }
}
