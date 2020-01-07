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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchFoodBean;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.ui.ImagePagerActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hysj created at 2019/05/14.
 * description:本店推荐美食适配器
 */
public class ShopRecommendDeliciousFoodAdapter extends RecyclerView.Adapter<ShopRecommendDeliciousFoodAdapter.ViewHolder> {

    List<MchFoodBean> mchFoodList;
    private Context mContext;
    private List<String> photoUrlList;

    public ShopRecommendDeliciousFoodAdapter(Context mContext) {

        this.mContext = mContext;
        if(photoUrlList == null)
        {
            photoUrlList = new ArrayList<>();
        } else {

            photoUrlList.clear();
        }
    }

    public void setMchFoodsList(List<MchFoodBean> mchFoodList) {

        this.mchFoodList = mchFoodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_shop_recommended_delicious_food_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            MchFoodBean mchFoodBean = mchFoodList.get(itemPosition);
            String title = mchFoodBean.getTitle();
            String photoUrl = mchFoodBean.getPhoto();
            float score =  mchFoodBean.getScore();
            String marketPrice = mchFoodBean.getMarketPrice();

            GlideUtil.loadImage(mContext,photoUrl,holder.mImgDeliciousFood);
            //美食名字
            holder.mTvDeliciousFoodName.setText(title);
            //评分
            holder.mTvDeliciousFoodScore.setText("评分" + String.valueOf(score));
            //美食价格
            holder.mTvDeliciousFoodPrice.setText("¥" + marketPrice);

            holder.mCardViewFineFoodRecommend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    photoUrlList.clear();
                    photoUrlList.add(photoUrl);
                    ImagePagerActivity.ImageSize imageSize = new ImagePagerActivity.ImageSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    ImagePagerActivity.startImagePagerActivity(mContext, photoUrlList, 0, imageSize);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mchFoodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //美食照片
        RoundedImageView mImgDeliciousFood;
        //美食名字
        TextView mTvDeliciousFoodName;
        //美食价格
        TextView mTvDeliciousFoodPrice;
        //美食评分
        TextView mTvDeliciousFoodScore;
        CardView mCardViewFineFoodRecommend;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgDeliciousFood = itemView.findViewById(R.id.image_delicious_food);
            mTvDeliciousFoodName = itemView.findViewById(R.id.tv_delicious_food_name);
            mTvDeliciousFoodPrice = itemView.findViewById(R.id.tv_food_price);
            mTvDeliciousFoodScore = itemView.findViewById(R.id.tv_food_score);
            mCardViewFineFoodRecommend = itemView.findViewById(R.id.cardview_fine_food_recommend_item);
        }
    }
}
