package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.BannerUrlBO;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.GlideRoundCornersTransUtils;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @auther：hysj created on 2019/4/17
 * description：
 */
public class BannerAdapter extends PagerAdapter {
    private List<ImageView> viewList;
    private int size;
    private Context mContext;
    private List<String> bannerUrlList;

    public BannerAdapter(Context mContext, List<ImageView> viewList, List<String> bannerUrlList) {
        this.viewList = viewList;
        this.size = viewList.size();
        this.mContext = mContext;
        this.bannerUrlList = bannerUrlList;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        if (this.viewList.size() > 3) {
            container.removeView((View) this.viewList.get(position % this.size));
        }
    }

    public Object instantiateItem(ViewGroup container, int position) {

        ImageView view = viewList.get(position % size);
        // 如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        ((ViewGroup) container).addView(view);

        String imageUrl = bannerUrlList.get(position % size);
        GlideUtil.loadImage(mContext, imageUrl, view);
        return view;
    }

    public int getCount() {
        return 2147483647;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
