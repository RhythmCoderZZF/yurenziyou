package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.DeliciousFoodRecommendResponse;
import com.nbhysj.coupon.model.response.DeliciousFoodResponse;
import com.nbhysj.coupon.model.response.PopularScenicSpotsResponse;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/7.
 * description : 美食推荐适配器
 */
public class DeliciousFoodRecommendAdapter extends RecyclerView.Adapter<DeliciousFoodRecommendAdapter.ViewHolder> {

    List<DeliciousFoodResponse> deliciousFoodRecommendList;
    private Context mContext;

    public DeliciousFoodRecommendAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setDeliciousFoodRecommendList(List<DeliciousFoodResponse> deliciousFoodRecommendList) {

        this.deliciousFoodRecommendList = deliciousFoodRecommendList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_delicious_food_recommend_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            DeliciousFoodResponse deliciousFoodRecommend = deliciousFoodRecommendList.get(itemPosition);
            int consumePrice = deliciousFoodRecommend.getConsumePrice();
            String photo = deliciousFoodRecommend.getPhoto();
            holder.mTvFoodCuisine.setText(deliciousFoodRecommend.getIntro());
            holder.mTvPerCapitaPrice.setText(String.valueOf(consumePrice));
            holder.mTvDeliciousFoodStore.setText(deliciousFoodRecommend.getMchName());

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
        //美食人均价格
        @BindView(R.id.tv_per_capita_price)
        TextView mTvPerCapitaPrice;
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

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
