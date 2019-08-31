package com.nbhysj.coupon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CarTypeBean;

import java.util.HashMap;
import java.util.List;

/**
 * @author hysj created at 2019/08/29.
 * description : 选择车辆型号适配器
 */
public class VehicleSelectionModelWheelAdapter extends RecyclerView.Adapter<VehicleSelectionModelWheelAdapter.ViewHolder> implements WheelAdapter {

    List<CarTypeBean> carTypeModeList;

    public VehicleSelectionModelWheelAdapter(List<CarTypeBean> carTypeModeList)
    {
        this.carTypeModeList = carTypeModeList;
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemsCount() {
        return carTypeModeList.size();
    }

    @Override
    public Object getItem(int index) {
        return carTypeModeList.get(index).getCarTypeName();
    }

    @Override
    public int indexOf(Object o) {
        try {
            String carTypeName = o.toString();
            for (int i = 0;i < carTypeModeList.size();i++){

                CarTypeBean carTypeBean = carTypeModeList.get(i);

                String mCarTypeName = carTypeBean.getCarTypeName();
                if(carTypeName.equals(mCarTypeName)){

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
        return carTypeModeList.size();
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
