package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.model.response.BannerUrlBO;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @auther：hysj created on 2019/4/17
 * description：
 */
public class ScenicSpotDetailBannerAdapter extends PagerAdapter {
    private List<ImageView> viewList;
    private int size;
    private final int cacheCount = 3;
    private Context mContext;
    private List<String> bannerUrlList;
    private ScenicSpotDetailBannerListener scenicSpotDetailBannerListener;

    public ScenicSpotDetailBannerAdapter(Context mContext, List<ImageView> viewList, List<String> bannerUrlList, ScenicSpotDetailBannerListener scenicSpotDetailBannerListener) {
        this.viewList = viewList;
        this.size = viewList.size();
        this.mContext = mContext;
        this.bannerUrlList = bannerUrlList;
        this.scenicSpotDetailBannerListener = scenicSpotDetailBannerListener;
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

        Glide.with(mContext)
                .load(bannerUrlList.get(position % this.size))
                .into(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scenicSpotDetailBannerListener.scenicSpotDetailBannerListener();
            }
        });
        return view;
    }

    public int getCount() {
        return 2147483647;
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public interface ScenicSpotDetailBannerListener {

        void scenicSpotDetailBannerListener();
    }
}
