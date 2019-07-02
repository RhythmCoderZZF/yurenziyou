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
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CardBean;
import com.nbhysj.coupon.ui.NearbyCardDetailActivity;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.GlideRoundCornersTransUtils;

import java.util.List;

public class SetMealDetailAdapter extends RecyclerView.Adapter<SetMealDetailAdapter.CardHolder> {

    private List<CardBean> mCardBeanList;
    private RequestOptions mRequestOptions;
    private Context mContent;

    public SetMealDetailAdapter(Context mContent) {
        this.mContent = mContent;
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_package_details_item, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class CardHolder extends RecyclerView.ViewHolder {
        public CardHolder(View itemView) {
            super(itemView);

        }
    }
}
