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
 * @author hysj created at 2019/08/29.
 * description : 用车时间选择适配器
 */
public class VehicleUseWheelAdapter extends RecyclerView.Adapter<VehicleUseWheelAdapter.ViewHolder> implements WheelAdapter {

    List<VehicleUseTimeResponse> vehicleUseTimeList;

    public VehicleUseWheelAdapter(List<VehicleUseTimeResponse> vehicleUseTimeList)
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

            VehicleUseTimeResponse vehicleUseTimeResponse = vehicleUseTimeList.get(itemPosition);
            String date = vehicleUseTimeResponse.getDate();
            String vehicleUseTime = vehicleUseTimeResponse.getVehicleUseTime();

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
        return vehicleUseTimeList.get(index).getVehicleUseTime();
    }

    @Override
    public int indexOf(Object o) {
        try {
            for(int i = 0; i < vehicleUseTimeList.size();i++)
            {
                VehicleUseTimeResponse vehicleUseTimeResponse = vehicleUseTimeList.get(i);
                String vehicleUseTime = vehicleUseTimeResponse.getVehicleUseTime();
                if(vehicleUseTime.equals(o)){

                    return i;
                }
            }
            return -1;
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
