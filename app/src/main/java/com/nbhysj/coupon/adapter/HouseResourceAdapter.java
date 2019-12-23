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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.HomestayBean;
import com.nbhysj.coupon.model.response.MchGoodsBean;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/12/23.
 * description:民宿详情房间筛选适配器
 */
public class HouseResourceAdapter extends RecyclerView.Adapter<HouseResourceAdapter.ViewHolder> {

    List<HomestayBean> homestayHouseResourceList;
    private Context mContext;

    public HouseResourceAdapter(Context mContext)
    {
        this.mContext = mContext;
    }

    public void setHomeResourceList(List<HomestayBean> homestayHouseResourceList) {

        this.homestayHouseResourceList = homestayHouseResourceList;
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
            HomestayBean homestayBean = homestayHouseResourceList.get(itemPosition);
            int mchId = homestayBean.getId();
            String photo = homestayBean.getPhoto();
            String title = homestayBean.getTitle();
            double defaultPrice = homestayBean.getDefaultPrice();
            double marketPrice = homestayBean.getMarketPrice();
            int bedNum = homestayBean.getBedNum();
            String acreage = homestayBean.getAcreage();
            int intoNum = homestayBean.getIntoNum();
            GlideUtil.loadImage(mContext, photo, holder.mImgHomestay);
            holder.mTvHomestayTitle.setText(title);
            holder.mTvDefaultPrice.setText("¥" + Tools.getTwoDecimalPoint(defaultPrice));
            holder.mTvDefaultPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            holder.mTvMarketPrice.setText(Tools.getTwoDecimalPoint(marketPrice));

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
        return homestayHouseResourceList.size();
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
