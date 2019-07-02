package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.example.library.banner.BannerLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.FollowDetailBean;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.GlideRoundCornersTransUtils;

import java.util.List;

/**
 * @author hysj created at 2019/04/17.
 * description : 商城
 */
public class ShoppingMallBannerLayoutAdapter extends RecyclerView.Adapter<ShoppingMallBannerLayoutAdapter.MzViewHolder> {

    private Context context;
    private List<String> shoppingMallBannerLayoutList;
    private BannerLayout.OnBannerItemClickListener onBannerItemClickListener;

    public ShoppingMallBannerLayoutAdapter(Context context, BannerLayout.OnBannerItemClickListener onBannerItemClickListener) {
        this.context = context;
        this.onBannerItemClickListener = onBannerItemClickListener;
    }

    public void setOnBannerItemClickListener(BannerLayout.OnBannerItemClickListener onBannerItemClickListener) {

    }

    public void setShoppingMallBannerLayoutList(List<String> shoppingMallBannerLayoutList) {
        this.shoppingMallBannerLayoutList = shoppingMallBannerLayoutList;
    }

    @Override
    public ShoppingMallBannerLayoutAdapter.MzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MzViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_shopping_mall_banner_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ShoppingMallBannerLayoutAdapter.MzViewHolder holder, final int position) {
        if (shoppingMallBannerLayoutList == null || shoppingMallBannerLayoutList.isEmpty())
            return;
        String shoppingMallBannerUrl = shoppingMallBannerLayoutList.get(position);
        // Glide.with(context).load(shoppingMallBannerUrl).into(holder.mImageShoppingMallBannerLayout);
        holder.mImageShoppingMallBannerLayout.fitCenter().load(shoppingMallBannerUrl, R.mipmap.icon_placeholder_image, 188);
       /* GlideApp.with(context)
                .load(shoppingMallBannerUrl)
                .placeholder(R.mipmap.icon_placeholder_image)
                .error(R.mipmap.icon_placeholder_image)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(Target.SIZE_ORIGINAL)
                .transform(new GlideRoundCornersTransUtils(context, 20, GlideRoundCornersTransUtils.CornerType.ALL))
                .into(holder.mImageShoppingMallBannerLayout);*/
    }

    @Override
    public int getItemCount() {
        if (shoppingMallBannerLayoutList != null) {
            return shoppingMallBannerLayoutList.size();
        }
        return 0;
    }


    class MzViewHolder extends RecyclerView.ViewHolder {
        GlideImageView mImageShoppingMallBannerLayout;

        MzViewHolder(View itemView) {
            super(itemView);
            mImageShoppingMallBannerLayout = itemView.findViewById(R.id.image_shopping_mall_layout_item);
        }
    }
}
