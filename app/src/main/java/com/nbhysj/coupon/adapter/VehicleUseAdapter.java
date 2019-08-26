package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.TouristBean;

import java.util.List;
/**
 * @author hysj created at 2019/08/19.
 * description:用车信息适配器
 */
public class VehicleUseAdapter extends RecyclerView.Adapter<VehicleUseAdapter.ViewHolder> {


    List<TouristBean> touristInfoList;
    private Context mContext;
    private TouristInformationListener touristInformationListener;

    public VehicleUseAdapter(Context mContext, TouristInformationListener touristInformationListener) {

        this.mContext = mContext;
        this.touristInformationListener = touristInformationListener;
    }

    public void setTouristInfoList(List<TouristBean> touristInfoList) {

        this.touristInfoList = touristInfoList;
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

          /*  TouristBean touristBean = touristInfoList.get(itemPosition);
            holder.mTvTouristName.setText(touristBean.getName());
            holder.mTvPhone.setText(touristBean.getIdNumber());
            holder.mTvEditVisitors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    touristInformationListener.setEditTouristInfoListener(itemPosition);
                }
            });*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return touristInfoList.size();
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

        public ViewHolder(View itemView) {
            super(itemView);

            mTvVehicleUseTime = itemView.findViewById(R.id.tv_vehicle_use_time);
            mTvVehicleExpenses = itemView.findViewById(R.id.tv_vehicle_expenses);
            mTvStartingPoint = itemView.findViewById(R.id.tv_starting_point);
            mTvDestination = itemView.findViewById(R.id.tv_destination);

        }
    }

    public interface TouristInformationListener {

        void setEditTouristInfoListener(int position);
    }
}
