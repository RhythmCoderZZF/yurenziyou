package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/06/01.
 * description : 商城首页小banner
 */
public class ShopMallIndexSmallBannerAdapter extends RecyclerView.Adapter<ShopMallIndexSmallBannerAdapter.ViewHolder> {

    private Context mContext;
    private List<String> shopMallIndexSmallBannerList;

    public ShopMallIndexSmallBannerAdapter(Context context) {
        this.mContext = context;
    }

    public void setShopMallIndexSmallBannerList(List<String> shopMallIndexSmallBannerList) {
        this.shopMallIndexSmallBannerList = shopMallIndexSmallBannerList;
    }

    @Override
    public ShopMallIndexSmallBannerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_shopping_mall_banner_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ShopMallIndexSmallBannerAdapter.ViewHolder holder, final int position) {

        try {
            String shopMallIndexSmallBannerUrl = shopMallIndexSmallBannerList.get(position);
          /*  LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(getScreenWidth() - 60,
                    ViewGroup.LayoutParams.WRAP_CONTENT);//两个400分别为添加图片的大小*/
            // holder.mImageShoppingMallBannerLayout.setLayoutParams(params);

            GlideUtil.loadImage(mContext, shopMallIndexSmallBannerUrl, holder.mImageShoppingMallBannerLayout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (shopMallIndexSmallBannerList != null) {
            return shopMallIndexSmallBannerList.size();
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView mImageShoppingMallBannerLayout;

        ViewHolder(View itemView) {
            super(itemView);
            mImageShoppingMallBannerLayout = itemView.findViewById(R.id.image_shopping_mall_layout_item);
        }
    }

    public int getScreenWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)
        return screenWidth;
    }
}
