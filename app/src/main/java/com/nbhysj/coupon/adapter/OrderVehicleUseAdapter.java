package com.nbhysj.coupon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.request.CarsBean;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import com.nbhysj.coupon.util.Tools;

import java.util.List;

/**
 * @author hysj created at 2019/08/19.
 * description:订单用车信息适配器
 */
public class OrderVehicleUseAdapter extends RecyclerView.Adapter<OrderVehicleUseAdapter.ViewHolder> {

    List<UserOrderListResponse.OrderListCarEntity> carList;

    private OrderOnclickListener orderOnclickListener;
    public OrderVehicleUseAdapter(OrderOnclickListener orderOnclickListener) {

        this.orderOnclickListener = orderOnclickListener;
    }

    public void setVehicleUseList(List<UserOrderListResponse.OrderListCarEntity> carList) {

        this.carList = carList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_vehicle_use_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            UserOrderListResponse.OrderListCarEntity carsBean = carList.get(itemPosition);
            String departureTime = carsBean.getUseCarTime();
            double price = carsBean.getCarPrice();
            String startAddressName = carsBean.getStartName();
            String endAddressName = carsBean.getEndName();
            holder.mTvVehicleExpenses.setText(Tools.getTwoDecimalPoint(price));
            holder.mTvStartingPoint.setText(startAddressName);
            holder.mTvDestination.setText(endAddressName);
            holder.mTvVehicleUseTime.setText(departureTime);

            holder.mLlytMyOrderItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    orderOnclickListener.setLookMyOrderDetailListener();

                }
            });
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
        LinearLayout mLlytMyOrderItem;
        public ViewHolder(View itemView) {
            super(itemView);

            mTvVehicleUseTime = itemView.findViewById(R.id.tv_vehicle_use_time);
            mTvVehicleExpenses = itemView.findViewById(R.id.tv_vehicle_expenses);
            mTvStartingPoint = itemView.findViewById(R.id.tv_starting_point);
            mTvDestination = itemView.findViewById(R.id.tv_destination);
            mLlytMyOrderItem = itemView.findViewById(R.id.llyt_my_order_item);
        }
    }

    public interface OrderOnclickListener{

        void setLookMyOrderDetailListener();
    }
}
