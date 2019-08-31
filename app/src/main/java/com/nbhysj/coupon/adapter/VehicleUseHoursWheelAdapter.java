package com.nbhysj.coupon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.VehicleUseTimeResponse;

import java.util.List;

/**
 * @author hysj created at 2019/05/23.
 * description : 用车时间小时选择适配器
 */
public class VehicleUseHoursWheelAdapter extends RecyclerView.Adapter<VehicleUseHoursWheelAdapter.ViewHolder> implements WheelAdapter {

    List<String> vehicleUseTimeList;

    public VehicleUseHoursWheelAdapter(List<String> vehicleUseTimeList)
    {
        this.vehicleUseTimeList = vehicleUseTimeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_vehicle_use_time_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {
        try {

            String vehicleUseTime = vehicleUseTimeList.get(itemPosition);
            holder.mTvVehicleUseTime.setText(vehicleUseTime);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemsCount() {
        return vehicleUseTimeList.size();
    }

    @Override
    public Object getItem(int index) {
            return vehicleUseTimeList.get(index) + "时";
    }

    @Override
    public int indexOf(Object o) {
        try {
            String hours = o.toString().replace("时", "");
            return vehicleUseTimeList.indexOf(hours);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return vehicleUseTimeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //用车时间
        public TextView mTvVehicleUseTime;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvVehicleUseTime = itemView.findViewById(R.id.tv_vehicle_use_time_item);
        }
    }
}
