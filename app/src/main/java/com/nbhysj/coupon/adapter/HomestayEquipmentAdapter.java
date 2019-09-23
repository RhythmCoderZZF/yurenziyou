package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.model.response.MchHomestayDetailsResponse;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.WebActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.RadiusGradientSpanUtil;

import java.util.List;

/**
 * @author hysj created at 2019/05/30.
 * description : 民宿设备适配器
 */
public class HomestayEquipmentAdapter extends RecyclerView.Adapter<HomestayEquipmentAdapter.ViewHolder> {

    List<MchHomestayDetailsResponse.ServiceEntity> mEquipmentList;
    private Context mContext;

    private AllFacilityItemListener allFacilityItemListener;
    public HomestayEquipmentAdapter(Context mContext, AllFacilityItemListener allFacilityItemListener) {

        this.mContext = mContext;
        this.allFacilityItemListener = allFacilityItemListener;
    }

    public void setEquipmentList(List<MchHomestayDetailsResponse.ServiceEntity> mEquipmentList) {

        this.mEquipmentList = mEquipmentList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_equipment_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
                if (itemPosition == 7) {
                    holder.mTvEquipmentNum.setVisibility(View.VISIBLE);
                    holder.mImageEquipment.setVisibility(View.GONE);
                    holder.mTvEquipmentNum.setText(RadiusGradientSpanUtil.getRadiusGradientSpan(mEquipmentList.size() + "+", 0xFF1DEB96, 0xFF0DDDF6));
                    holder.mTvEquipmentName.setText(RadiusGradientSpanUtil.getRadiusGradientSpan("更多设备", 0xFF1DEB96, 0xFF0DDDF6));

                } else {

                    MchHomestayDetailsResponse.ServiceEntity equipmentResponse = mEquipmentList.get(itemPosition);
                    int equipmentNum = mEquipmentList.size();
                    holder.mTvEquipmentNum.setVisibility(View.GONE);
                    holder.mImageEquipment.setVisibility(View.VISIBLE);

                    String equipmentName = equipmentResponse.getTitle();
                    String equipmentphoto = equipmentResponse.getPhoto();
                    holder.mTvEquipmentName.setText(equipmentName);
                    GlideUtil.loadImage(mContext, equipmentphoto, holder.mImageEquipment);
                    holder.mTvEquipmentName.setTextColor(mContext.getResources().getColor(R.color.txt_font_black2));

                }

                holder.mLlytAllFacilityItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        allFacilityItemListener.setAllFacilityItemListenerCallback();

                    }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if(mEquipmentList.size() == 0)
        {
            return 0;
        } else if(mEquipmentList.size() > 7){
            return 8;
        }
        return mEquipmentList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        //设备图片
        public ImageView mImageEquipment;

        //设备名字
        public TextView mTvEquipmentName, mTvEquipmentNum;

        LinearLayout mLlytAllFacilityItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageEquipment = itemView.findViewById(R.id.img_equipment);
            mTvEquipmentName = itemView.findViewById(R.id.tv_equipment_name);
            mTvEquipmentNum = itemView.findViewById(R.id.tv_equipment_num);
            mLlytAllFacilityItem = itemView.findViewById(R.id.llyt_all_facility_item);

        }
    }

    public interface AllFacilityItemListener
    {
        void setAllFacilityItemListenerCallback();
    }
}
