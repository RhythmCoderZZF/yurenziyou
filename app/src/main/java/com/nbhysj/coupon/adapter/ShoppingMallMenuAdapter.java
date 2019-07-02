package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.ShoppingMallMenuBean;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * created by hysj on 2019/4/17.
 * description:
 */

public class ShoppingMallMenuAdapter extends RecyclerView.Adapter<ShoppingMallMenuAdapter.ViewHolder> {

    private String[] titles = {"风景名胜", "美食", "酒店名宿", "攻略", "亲子游", "互动", "自由行", "用车"};
    private Context mContext;
    private List<ShoppingMallMenuBean> shoppingMallMenuList;
    private ShoppingMallMenuListener shoppingMallMenuListener;

    public ShoppingMallMenuAdapter(Context mContext, ShoppingMallMenuListener shoppingMallMenuListener) {

        this.mContext = mContext;
        this.shoppingMallMenuListener = shoppingMallMenuListener;

    }

    public void setShoppingMallMenuList(List<ShoppingMallMenuBean> shoppingMallMenuList) {
        this.shoppingMallMenuList = shoppingMallMenuList;
    }

    @Override
    public ShoppingMallMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shopping_mall_option, null);
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ShoppingMallMenuAdapter.ViewHolder holder, int position) {

        try {

            holder.mTvShoppingMallItem.setText(titles[position]);
            holder.mLlytShoppingMallItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    shoppingMallMenuListener.setShoppingMallMenuListener(position);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.llyt_shopping_mall_item)
        LinearLayout mLlytShoppingMallItem;
        @BindView(R.id.img_shopping_mall_item)
        GlideImageView mImgShoppingMallItem;
        @BindView(R.id.tv_shopping_mall_item)
        TextView mTvShoppingMallItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ShoppingMallMenuListener {

        void setShoppingMallMenuListener(int position);
    }


}
