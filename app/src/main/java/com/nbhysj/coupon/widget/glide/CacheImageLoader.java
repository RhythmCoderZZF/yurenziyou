package com.nbhysj.coupon.widget.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.view.GlideImageView;
import com.youth.banner.loader.ImageLoader;

/**
 * 图片loader
 */

public class CacheImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        GlideApp.with(context)
                .load(path)
                .placeholder(context.getResources().getDrawable(R.mipmap.icon_placeholder_image))
                .into(imageView);
    }
}
