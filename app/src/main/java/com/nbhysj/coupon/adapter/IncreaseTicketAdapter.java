package com.nbhysj.coupon.adapter;

import android.content.Context;
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
import com.nbhysj.coupon.model.response.PopularScenicSpotsResponse;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/22.
 * description : 增加门票适配器
 */
public class IncreaseTicketAdapter extends RecyclerView.Adapter<IncreaseTicketAdapter.ViewHolder> {

    List<PopularScenicSpotsResponse> popularScenicSpotsList;
    private Context mContext;

    public IncreaseTicketAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setInteractiveSelectionList(List<PopularScenicSpotsResponse> popularScenicSpotsList) {

        this.popularScenicSpotsList = popularScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_increase_ticket_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            /*PopularScenicSpotsResponse popularScenicSpots = popularScenicSpotsList.get(itemPosition);
            holder.mTvPopularScenicSpotPrice.setText(popularScenicSpots.getScenicSpotsTicketPrice());
            holder.mTvPopularScenicSpotName.setText(popularScenicSpots.getScenicSpotsName());*/

            if (itemPosition == 1) {

                holder.mLlytIncreaseTicketItem.setBackgroundResource(R.drawable.bg_order_date_select_bottom_shape);

            } else {

                holder.mLlytIncreaseTicketItem.setBackgroundResource(R.color.white);
            }
            RequestOptions myOptions = new RequestOptions()
                    .transform(new GlideRoundTransform(mContext, 5));

            Glide.with(mContext)
                    .load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1843898868,2780995514&fm=27&gp=0.jpg")
                    .apply(myOptions)
                    .into(holder.mImgScenicSpots);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //景区门票
        @BindView(R.id.tv_scenic_spots_price)
        TextView mTvPopularScenicSpotPrice;
        //景区名字
        @BindView(R.id.tv_scenic_spots_name)
        TextView mTvPopularScenicSpotName;
        //景区照片
        @BindView(R.id.image_scenic_spots)
        ImageView mImgScenicSpots;
        //初始价格
        @BindView(R.id.tv_original_price)
        TextView mTvOriginalPrice;
        @BindView(R.id.tag_flowlayout_ticket)
        TagFlowLayout mTagFlowLayout;
        //增加门票
        @BindView(R.id.llyt_increase_ticket_item)
        LinearLayout mLlytIncreaseTicketItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
