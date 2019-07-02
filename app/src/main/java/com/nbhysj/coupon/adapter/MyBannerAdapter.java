package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nbhysj.coupon.model.response.BannerUrlBO;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

/**
 * @auther：hysj created on 2019/4/17
 * description：banner适配器
 */
public class MyBannerAdapter extends PagerAdapter {
    private List<ImageView> viewList;
    private int size;
    private Context mContext;
    private List<String> bannerUrlList;

    public MyBannerAdapter(Context mContext, List<ImageView> viewList, List<String> bannerUrlList) {
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
        ImageView view = (ImageView) this.viewList.get(position % this.size);
        ViewGroup parent = (ViewGroup) (view).getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        container.addView(view);

        String imageUrl = bannerUrlList.get(position % this.size);
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
