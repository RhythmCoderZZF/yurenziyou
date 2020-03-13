package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/09/16.
 * description : 民宿(榜单）列表适配器
 */
public class HomestayBangDanListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MchTypeBean> homestayList;
    private Context mContext;
    private View mHeaderView;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;
    LayoutInflater mInflater;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
//        notifyItemChanged(1,1);
    }

    public HomestayBangDanListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setPopularScenicSpotsList(List<MchTypeBean> homestayList) {

        this.homestayList = homestayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_homestay_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        try {
            if (getItemViewType(position) == TYPE_HEADER) return;
            final int pos = getRealPosition(holder);
            if (holder instanceof ViewHolder) {
                ViewHolder holder1 = (ViewHolder) holder;
                try {
                    MchTypeBean hotelResponse = homestayList.get(pos);
                    int consumePrice = hotelResponse.getConsumePrice();
                    String photo = hotelResponse.getPhoto();
                    String mchName = hotelResponse.getDataName();
                    String homestayAddress = hotelResponse.getAddress();
                    String intro = hotelResponse.getIntro();
                    int authenticationStatus = hotelResponse.getAuthenticationStatus();
                    String avatar = hotelResponse.getAvatar();
                    int loveStatus = hotelResponse.getLoveStatus();
                    String address = hotelResponse.getAddress();
                    int dataId = hotelResponse.getId();

                    GlideUtil.loadImage(mContext, photo, holder1.mImgHomestay);
                    GlideUtil.loadImage(mContext, avatar, holder1.mImgHouseOwnerAvatar);
                    holder1.mTvHomestayName.setText(mchName);
                    if(!TextUtils.isEmpty(address)) {

                        holder1.mTvHomestayAddress.setText(address);
                    } else {

                        holder1.mTvHomestayAddress.setText(intro);
                    }
                    holder1.mTvHomestayPrice.setText(String.valueOf(consumePrice));

                    if (authenticationStatus == 0) {
                        holder1.mImgHouseOwnerAuthentication.setVisibility(View.GONE);
                    } else if (authenticationStatus == 1) {
                        holder1.mImgHouseOwnerAuthentication.setVisibility(View.VISIBLE);
                    }

                    if (loveStatus == 0) {
                        holder1.mImgHomestayCollection.setImageResource(R.mipmap.icon_homestay_not_collection);
                    } else if (loveStatus == 1) {
                        holder1.mImgHomestayCollection.setImageResource(R.mipmap.icon_homestay_collection);
                    }

                    holder1.mLlytHomestayItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent mIntent = new Intent();
                            mIntent.setClass(mContext, HomestayDetailActivity.class);
                            mIntent.putExtra("mchId", dataId);
                            mContext.startActivity(mIntent);
                        }
                    });


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mHeaderView != null ? homestayList.size() + 1: homestayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView mImgHomestay;
        //民宿收藏
        ImageView mImgHomestayCollection;
        //名宿标签
        TextView mTvHomestayName;
        //名宿位置
        TextView mTvHomestayAddress;
        //民宿价格
        TextView mTvHomestayPrice;
        //房东头像
        ImageView mImgHouseOwnerAvatar;
        //酒店类型
        ImageView mImgHouseOwnerAuthentication;

        LinearLayout mLlytHomestayItem;
        public ViewHolder(View itemView) {
            super(itemView);

            mImgHomestay = itemView.findViewById(R.id.img_homestay);
            mImgHomestayCollection = itemView.findViewById(R.id.img_homestay_collection);
            mTvHomestayName = itemView.findViewById(R.id.tv_homestay_name);
            mTvHomestayAddress = itemView.findViewById(R.id.tv_homestay_address);
            mTvHomestayPrice = itemView.findViewById(R.id.tv_homestay_price);
            mImgHouseOwnerAvatar = itemView.findViewById(R.id.img_the_owner_of_the_house_avatar);
            mImgHouseOwnerAuthentication = itemView.findViewById(R.id.img_house_owner_name_authentication);
            mLlytHomestayItem = itemView.findViewById(R.id.llyt_homestay_item);
        }
    }
}
