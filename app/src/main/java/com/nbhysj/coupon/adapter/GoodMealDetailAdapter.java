package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CardBean;
import com.nbhysj.coupon.model.response.OrderDetailResponse;
import com.nbhysj.coupon.ui.NearbyCardDetailActivity;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.GlideRoundCornersTransUtils;

import java.util.List;

public class GoodMealDetailAdapter extends RecyclerView.Adapter<GoodMealDetailAdapter.CardHolder> {

    private Context mContent;
    //订单列表
    List<OrderDetailResponse.OrderGoodsEntity> orderGoodsList;
    public GoodMealDetailAdapter(Context mContent) {
        this.mContent = mContent;
    }

    public void setOrderMealList(List<OrderDetailResponse.OrderGoodsEntity> orderGoodsList){

        this.orderGoodsList = orderGoodsList;

    }
    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_package_details_item, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {

        OrderDetailResponse.OrderGoodsEntity orderGoodsEntity = orderGoodsList.get(position);
        //商户名
        String mchName = orderGoodsEntity.getMchName();
        //商品标题
        String goodsTitle = orderGoodsEntity.getGoodsTitle();
        //商品可用到期时间
        String goodTime = orderGoodsEntity.getGoodTime();

        holder.mTvMchName.setText(mchName);

        holder.mTvTicketTitle.setText(goodsTitle);

        holder.mTvTicketCanUseTime.setText(goodTime);
    }

    @Override
    public int getItemCount() {
        return orderGoodsList.size();
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        //商户名字
        TextView mTvMchName;
        //票类型
        TextView mTvTicketTitle;
        //可使用时间
        TextView mTvTicketCanUseTime;

        public CardHolder(View itemView) {
            super(itemView);

            mTvMchName = itemView.findViewById(R.id.tv_mch_name);
            mTvTicketTitle = itemView.findViewById(R.id.tv_ticket_title);
            mTvTicketCanUseTime = itemView.findViewById(R.id.tv_ticket_can_use_time);
        }
    }
}
