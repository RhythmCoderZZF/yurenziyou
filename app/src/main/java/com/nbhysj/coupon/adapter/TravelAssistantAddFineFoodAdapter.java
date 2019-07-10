package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.ScenicSpotBean;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.view.StarBarView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hysj created at 2019/05/17.
 * description: 添加美食适配器
 */
public class TravelAssistantAddFineFoodAdapter extends RecyclerView.Adapter<TravelAssistantAddFineFoodAdapter.ViewHolder> {

    List<TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity> scenicSpotList;
    private Context mContext;
    private FineFoodAddListener fineFoodAddListener;

    public TravelAssistantAddFineFoodAdapter(Context mContext, FineFoodAddListener fineFoodAddListener) {

        this.mContext = mContext;
        this.fineFoodAddListener = fineFoodAddListener;
    }

    public void setScenicSpotList(List<TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity> scenicSpotList) {

        this.scenicSpotList = scenicSpotList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_add_fine_food_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity travelAssistantAddScenicSpotEntity = scenicSpotList.get(itemPosition);

            String photoUrl = travelAssistantAddScenicSpotEntity.getPhoto();
            int level = travelAssistantAddScenicSpotEntity.getLevel();
            String fineFoodTag = travelAssistantAddScenicSpotEntity.getTags();
            int mSelectedStatus = travelAssistantAddScenicSpotEntity.getSelectedStatus();
            String mchName = travelAssistantAddScenicSpotEntity.getMchName();
            String intro = travelAssistantAddScenicSpotEntity.getIntro();
            double price = travelAssistantAddScenicSpotEntity.getPrice();
            String consumePrice = String.valueOf(price);

            GlideUtil.loadImage(mContext, photoUrl, holder.mImgFineFood);
            holder.mTvFineFoodName.setText(mchName);
            holder.mTvFineFoodPrice.setText("¥ " + consumePrice + "/人");
            holder.mTvFineFoodDes.setText(String.valueOf(intro));

            holder.mStarBarView.setStarMark(level);
            holder.mStarBarView.setIntegerMark(true);
            List<String> findFoodTagList = new ArrayList<>();
            findFoodTagList.add(fineFoodTag);

            if (!TextUtils.isEmpty(fineFoodTag))
            {
                    holder.mTagFlowlayoutFineFood.setAdapter(new TagAdapter<String>(findFoodTagList){

                        @Override
                        public View getView(FlowLayout parent,int position,String tagsEntity){
                            LayoutInflater mInflater = LayoutInflater.from(mContext);
                            TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_orange_frame,
                                    holder.mTagFlowlayoutFineFood, false);
                            tagName.setText(tagsEntity);
                            return tagName;
                        }
                    });
            }

            holder.mLlytAddFineFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    fineFoodAddListener.setFineFoodAddListener(travelAssistantAddScenicSpotEntity);
                    // holder.mImgAddMyTravel.setBackgroundResource(R.mipmap.icon_travel_assistant_selected_tag);
                }
            });

            if (mSelectedStatus == 0) {

                holder.mImgAddMyTravel.setBackgroundResource(R.mipmap.icon_travel_assistant_selected_tag);
                holder.mImgAddMyTravel.setEnabled(false);
            } else {
                holder.mImgAddMyTravel.setBackgroundResource(R.mipmap.icon_travel_assistant_add_tag);
                holder.mImgAddMyTravel.setEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return scenicSpotList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //美食照片
        RoundedImageView mImgFineFood;

        //美食标签
        TagFlowLayout mTagFlowlayoutFineFood;

        LinearLayout mLlytAddFineFood;

        ImageView mImgAddMyTravel;

        //美食名称
        TextView mTvFineFoodName;

        TextView mTvFineFoodDes;

        TextView mTvFineFoodPrice;

        //评分星级
        StarBarView mStarBarView;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgFineFood = itemView.findViewById(R.id.image_fine_food);
            mLlytAddFineFood = itemView.findViewById(R.id.llyt_add_fine_food);
            mImgAddMyTravel = itemView.findViewById(R.id.img_add_my_travel);
            mTvFineFoodName = itemView.findViewById(R.id.tv_fine_food_name);
            mTvFineFoodDes = itemView.findViewById(R.id.tv_fine_food_des);
            mStarBarView = itemView.findViewById(R.id.starbar_store_fine_food);
            mTvFineFoodPrice = itemView.findViewById(R.id.tv_price);
            mTagFlowlayoutFineFood = itemView.findViewById(R.id.flowlayout_fine_food_label);
        }
    }

    public interface FineFoodAddListener {

        void setFineFoodAddListener(TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity travelAssistantAddScenicSpotEntity);

    }

}
