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
import com.nbhysj.coupon.model.response.DeliciousFoodResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.FoodRecommendationActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/7.
 * description : 美食页面栏目适配器
 */
public class DeliciousFoodSectionAdapter extends RecyclerView.Adapter<DeliciousFoodSectionAdapter.ViewHolder> {

    List<MchTypeBean> deliciousFoodRecommendList;
    private Context mContext;

    public DeliciousFoodSectionAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setDeliciousFoodRecommendList(List<MchTypeBean> deliciousFoodRecommendList) {

        this.deliciousFoodRecommendList = deliciousFoodRecommendList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_delicious_food_section_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            MchTypeBean scenicSpotBean = deliciousFoodRecommendList.get(itemPosition);
            String photo = scenicSpotBean.getPhoto();
            //holder.mTvFoodCuisine.setText(scenicSpotBean.getIntro());
            String mchName = scenicSpotBean.getMchName();
            String dataName = scenicSpotBean.getDataName();
            if (!TextUtils.isEmpty(mchName)) {
                holder.mTvDeliciousFoodStore.setText(mchName);
            } else if (!TextUtils.isEmpty(dataName)) {
                holder.mTvDeliciousFoodStore.setText(dataName);
            } else {
                holder.mTvDeliciousFoodStore.setText("");
            }
            GlideUtil.loadCornersTransformImage(mContext, photo, 5, holder.mImgDeliciousFood);

            if (itemPosition == 0) {

                holder.mHeader.setVisibility(View.VISIBLE);
            } else {

                holder.mHeader.setVisibility(View.GONE);
            }

            if (itemPosition == deliciousFoodRecommendList.size() - 1) {

                holder.mFooter.setVisibility(View.VISIBLE);
            } else {
                holder.mFooter.setVisibility(View.GONE);
            }

            holder.mLlytDeliciousFoodSectionItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, FoodRecommendationActivity.class);
                    mContext.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return deliciousFoodRecommendList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //美食菜系
        @BindView(R.id.tv_food_cuisine)
        TextView mTvFoodCuisine;
        //美食位置
        @BindView(R.id.tv_delicious_food_store)
        TextView mTvDeliciousFoodStore;
        //美食照片
        @BindView(R.id.image_delicious_food)
        ImageView mImgDeliciousFood;
        @BindView(R.id.view_header)
        View mHeader;
        @BindView(R.id.view_footer)
        View mFooter;
        @BindView(R.id.llyt_delicious_food_section_item)
        LinearLayout mLlytDeliciousFoodSectionItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
