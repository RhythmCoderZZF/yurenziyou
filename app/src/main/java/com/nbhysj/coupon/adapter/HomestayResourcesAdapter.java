package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.HotelBean;
import com.nbhysj.coupon.model.response.ShopMallHomePageResponse;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/09/19.
 * description:民宿房源适配器
 */
public class HomestayResourcesAdapter extends RecyclerView.Adapter<HomestayResourcesAdapter.ViewHolder> {

    List<HotelBean> homestayResourcesList;
    private Context mContext;

    public HomestayResourcesAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setHomestayResourcesList(List<HotelBean> homestayResourcesList) {

        this.homestayResourcesList = homestayResourcesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hot_selling_homestay_nearby_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            HotelBean homestayBean = homestayResourcesList.get(itemPosition);
            int mchId = homestayBean.getMchId();
            String photo = homestayBean.getPhoto();
            String title = homestayBean.getTitle();
            int defaultPrice = homestayBean.getDefaultPrice();
            int marketPrice = homestayBean.getMarketPrice();
            int bedNum = homestayBean.getBedNum();
            String acreage = homestayBean.getAcreage();
            int intoNum = homestayBean.getIntoNum();
            GlideUtil.loadImage(mContext, photo, holder.mImgHomestay);
            holder.mTvHomestayTitle.setText(title);
            holder.mTvDefaultPrice.setText("¥" + String.valueOf(defaultPrice));
            holder.mTvDefaultPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            holder.mTvMarketPrice.setText(String.valueOf(marketPrice));

            holder.mTvHomestayTag.setText(bedNum + "居室." + acreage + "㎡" + ".可住" + intoNum + "人");

            holder.mLlytHomestayResourcesItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, HomestayDetailActivity.class);
                    intent.putExtra("mchId",mchId);
                    mContext.startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return homestayResourcesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //民宿照片
        @BindView(R.id.image_homestay)
        ImageView mImgHomestay;
        //市场价格
        @BindView(R.id.tv_market_price)
        TextView mTvMarketPrice;
        //默认价格
        @BindView(R.id.tv_default_price)
        TextView mTvDefaultPrice;
        //民宿名
        @BindView(R.id.tv_homestay_title)
        TextView mTvHomestayTitle;
        //民宿标签
        @BindView(R.id.tv_homestay_tag)
        TextView mTvHomestayTag;
        @BindView(R.id.llyt_homestay_resources_item)
        LinearLayout mLlytHomestayResourcesItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
