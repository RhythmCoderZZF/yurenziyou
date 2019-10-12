package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.DeliciousFoodRecommendResponse;
import com.nbhysj.coupon.model.response.HotelBean;
import com.nbhysj.coupon.model.response.HotelReputationResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/19.
 * description : 酒店名宿适配器
 */
public class HomestayAdapter extends RecyclerView.Adapter<HomestayAdapter.ViewHolder> {

    List<MchTypeBean> hotelReputationList;
    private Context mContext;
    private HomestayCollectionListener homestayCollectionListener;

    public HomestayAdapter(Context mContext,HomestayCollectionListener homestayCollectionListener) {

        this.mContext = mContext;
        this.homestayCollectionListener = homestayCollectionListener;
    }

    public void setHomestayList(List<MchTypeBean> hotelReputationList) {

        this.hotelReputationList = hotelReputationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_homestay_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            MchTypeBean homestayResponse = hotelReputationList.get(itemPosition);
            int consumePrice = homestayResponse.getConsumePrice();
            int mchId = homestayResponse.getId();
            String photo = homestayResponse.getPhoto();
            String mchName = homestayResponse.getMchName();
            String homestayAddress = homestayResponse.getAddress();
            int authenticationStatus = homestayResponse.getAuthenticationStatus();
            String avatarUrl = homestayResponse.getAvatar();
            int loveStatus = homestayResponse.getLoveStatus();
            int dataId = homestayResponse.getId();

            GlideUtil.loadImage(mContext, photo, holder.mImgHomestay);
            GlideUtil.loadImage(mContext, avatarUrl, holder.mImgHouseOwnerAvatar);
            holder.mTvHomestayName.setText(mchName);
            holder.mTvHomestayAddress.setText(homestayAddress);
            holder.mTvHomestayPrice.setText(String.valueOf(consumePrice));

            if (authenticationStatus == 0) {
                holder.mImgHouseOwnerAuthentication.setVisibility(View.GONE);
            } else if (authenticationStatus == 1) {
                holder.mImgHouseOwnerAuthentication.setVisibility(View.VISIBLE);
            }

            if (loveStatus == 0) {
                holder.mImgHomestayCollection.setImageResource(R.mipmap.icon_homestay_not_collection);
            } else if (loveStatus == 1) {
                holder.mImgHomestayCollection.setImageResource(R.mipmap.icon_homestay_collection);
            }

            holder.mImgHomestayCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    homestayCollectionListener.setHomestayCollection(itemPosition,dataId);

                }
            });

            holder.mLlytHomestayItem.setOnClickListener(new View.OnClickListener() {
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
        return hotelReputationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //民宿照片
        @BindView(R.id.img_homestay)
        RoundedImageView mImgHomestay;
        //民宿收藏
        @BindView(R.id.img_homestay_collection)
        ImageView mImgHomestayCollection;
        //名宿标签
        @BindView(R.id.tv_homestay_name)
        TextView mTvHomestayName;
        //名宿位置
        @BindView(R.id.tv_homestay_address)
        TextView mTvHomestayAddress;
        //民宿价格
        @BindView(R.id.tv_homestay_price)
        TextView mTvHomestayPrice;
        //房东头像
        @BindView(R.id.img_the_owner_of_the_house_avatar)
        ImageView mImgHouseOwnerAvatar;
        //房东身份验证
        @BindView(R.id.img_house_owner_name_authentication)
        ImageView mImgHouseOwnerAuthentication;
        @BindView(R.id.llyt_homestay_item)
        LinearLayout mLlytHomestayItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface HomestayCollectionListener{

        void setHomestayCollection(int position,int dataId);
    }
}
