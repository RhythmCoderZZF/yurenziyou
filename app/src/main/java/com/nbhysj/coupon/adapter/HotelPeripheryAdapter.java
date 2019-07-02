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
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/7.
 * description : 酒店附近(美食/娱乐/景区)推荐适配器
 */
public class HotelPeripheryAdapter extends RecyclerView.Adapter<HotelPeripheryAdapter.ViewHolder> {

    List<DeliciousFoodRecommendResponse> deliciousFoodRecommendList;
    private Context mContext;

    public HotelPeripheryAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setDeliciousFoodRecommendList(List<DeliciousFoodRecommendResponse> deliciousFoodRecommendList) {

        this.deliciousFoodRecommendList = deliciousFoodRecommendList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_periphery_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
          /*  DeliciousFoodRecommendResponse deliciousFoodRecommend = deliciousFoodRecommendList.get(itemPosition);
            holder.mTvFoodCuisine.setText(deliciousFoodRecommend.getFoodCuisine());
            holder.mTvPerCapitaPrice.setText(deliciousFoodRecommend.getPerCapitaPrice());
            holder.mTvDeliciousFoodStore.setText(deliciousFoodRecommend.getDeliciousFoodStore());*/

            RequestOptions myOptions = new RequestOptions()
                    .transform(new GlideRoundTransform(mContext, 5));

            Glide.with(mContext)
                    .load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1555639803&di=038b9646f3b207fcf7ed84a41c72a85b&src=http://b-ssl.duitang.com/uploads/item/20182/21/2018221142159_MZ33z.jpeg")
                    .apply(myOptions)
                    .into(holder.mImgDeliciousFood);

            if (itemPosition == 0) {

                holder.mHeader.setVisibility(View.VISIBLE);
            } else {

                holder.mHeader.setVisibility(View.GONE);
            }

            if (itemPosition == 5 - 1) {

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
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
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
