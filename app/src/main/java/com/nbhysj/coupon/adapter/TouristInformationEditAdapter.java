package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;

import java.util.List;

/**
 * @author hysj created at 2019/5/05.
 * description:游客信息编辑适配器
 */
public class TouristInformationEditAdapter extends RecyclerView.Adapter<TouristInformationEditAdapter.ViewHolder> {


    List<String> touristInfoList;
    private Context mContext;

    public TouristInformationEditAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setTouristInfoList(List<String> touristInfoList) {

        this.touristInfoList = touristInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tourist_information_edit_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            if (itemPosition == touristInfoList.size() - 1) {

                holder.mLlytTouristInfo.setBackgroundResource(R.drawable.bg_order_date_select_bottom_shape);

            } else {

                holder.mLlytTouristInfo.setBackgroundResource(R.color.white);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return touristInfoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout mLlytTouristInfo;

        public ViewHolder(View itemView) {
            super(itemView);

            mLlytTouristInfo = itemView.findViewById(R.id.llyt_tourist_info_item);
        }
    }
}
