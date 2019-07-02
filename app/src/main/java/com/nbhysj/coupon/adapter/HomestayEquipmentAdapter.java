package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.EquipmentResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.RadiusGradientSpanUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/05/30.
 * description : 民宿设备适配器
 */
public class HomestayEquipmentAdapter extends RecyclerView.Adapter<HomestayEquipmentAdapter.ViewHolder> {


    List<EquipmentResponse> mEquipmentList;
    private Context mContext;

    public HomestayEquipmentAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setEquipmentList(List<EquipmentResponse> mEquipmentList) {

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
            if (itemPosition == mEquipmentList.size()) {
                holder.mTvEquipmentNum.setVisibility(View.VISIBLE);
                holder.mImageEquipment.setVisibility(View.GONE);
                holder.mTvEquipmentNum.setText(RadiusGradientSpanUtil.getRadiusGradientSpan("34+", 0xFF1DEB96, 0xFF0DDDF6));
                holder.mTvEquipmentName.setText(RadiusGradientSpanUtil.getRadiusGradientSpan("更多设备", 0xFF1DEB96, 0xFF0DDDF6));
            } else {
                holder.mTvEquipmentNum.setVisibility(View.GONE);
                holder.mImageEquipment.setVisibility(View.VISIBLE);
                EquipmentResponse equipmentResponse = mEquipmentList.get(itemPosition);
                String equipmentName = equipmentResponse.getName();
                int equipmentImage = equipmentResponse.getImage();
                holder.mTvEquipmentName.setText(equipmentName);
                holder.mImageEquipment.setImageResource(equipmentImage);
                holder.mTvEquipmentName.setTextColor(mContext.getResources().getColor(R.color.txt_font_black2));

            }
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
        return mEquipmentList.size() + 1;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        //设备图片
        public ImageView mImageEquipment;

        //设备名字
        public TextView mTvEquipmentName, mTvEquipmentNum;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageEquipment = itemView.findViewById(R.id.img_equipment);
            mTvEquipmentName = itemView.findViewById(R.id.tv_equipment_name);
            mTvEquipmentNum = itemView.findViewById(R.id.tv_equipment_num);

        }
    }
}
