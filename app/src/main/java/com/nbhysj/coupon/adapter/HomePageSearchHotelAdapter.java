package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.HomeSearchMchTypeBean;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/19.
 * description : 酒店名宿适配器
 */
public class HomePageSearchHotelAdapter extends RecyclerView.Adapter<HomePageSearchHotelAdapter.ViewHolder> {

    List<HomeSearchMchTypeBean> hotelList;
    private Context mContext;

    public HomePageSearchHotelAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setHotelList(List<HomeSearchMchTypeBean> hotelList) {

        this.hotelList = hotelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            HomeSearchMchTypeBean hotelResponse = hotelList.get(itemPosition);
            float commentScore = hotelResponse.getCommentScore();
            String intro = hotelResponse.getIntro();
            String level = hotelResponse.getLevel();
            int commentNum = hotelResponse.getCommentNum();
            int consumePrice = hotelResponse.getConsumePrice();
            String photo = hotelResponse.getPhoto();
            holder.mTvHotelName.setText(hotelResponse.getMchName());
            String mchId = hotelResponse.getId();
            String mchType = hotelResponse.getHotelType();
            String mchType2 = hotelResponse.getHotelType2();
            String hotelTypeName = hotelResponse.getHotelTypeName();
            holder.mTvHotelReputationScore.setText(String.valueOf(commentScore));
            if (intro != null) {

                holder.mTvHotelDescription.setText(intro);
            } else {

                holder.mTvHotelDescription.setText("");
            }
            if (TextUtils.isEmpty(level)) {
                holder.mTvHotelStar.setVisibility(View.GONE);
            } else {
                holder.mTvHotelStar.setText(level);
                holder.mTvHotelStar.setVisibility(View.VISIBLE);
            }
            // holder.mTvHotelType.setText(hotelReputationResponse.getHotelType());
            holder.mTvHotelLocation.setText(hotelResponse.getCounty());
            holder.mTvCommentNumber.setText(commentNum + "条点评");
            holder.mTvPerCapitaPrice.setText(String.valueOf(consumePrice));

            if (!TextUtils.isEmpty(hotelTypeName))
            {
                holder.mTvHotelType.setVisibility(View.VISIBLE);
                holder.mTvHotelType.setText(hotelTypeName);
            } else {
                holder.mTvHotelType.setVisibility(View.GONE);
            }

            GlideUtil.loadImage(mContext, photo, holder.mImgHotel);

            holder.mRlytHotelItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String type = MchTypeEnum.MCH_HOTEL.getValue();
                    String hotelType = MchTypeEnum.MCH_HOTEL1.getValue();
                    String homestayType = MchTypeEnum.MCH_HOTEL2.getValue();
                    if (mchType.equals(type)) {
                        if (!TextUtils.isEmpty(mchType2)) {
                            Intent intent = new Intent();
                            intent.putExtra("mchId", Integer.parseInt(mchId));
                            if (mchType2.equals(hotelType)) {

                                intent.setClass(mContext, HotelDetailsActivity.class);
                                mContext.startActivity(intent);
                            } else if (mchType2.equals(homestayType)) {
                                intent.setClass(mContext, HomestayDetailActivity.class);
                                mContext.startActivity(intent);
                            }

                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //酒店名宿名字
        @BindView(R.id.tv_hotel_name)
        TextView mTvHotelName;
        //酒店名宿评分
        @BindView(R.id.tv_hotel_reputation_score)
        TextView mTvHotelReputationScore;
        //酒店描述
        @BindView(R.id.tv_hotel_description)
        TextView mTvHotelDescription;
        //酒店星级
        @BindView(R.id.tv_hotel_star)
        TextView mTvHotelStar;
        //酒店类型
        @BindView(R.id.tv_hotel_type)
        TextView mTvHotelType;
        //酒店位置
        @BindView(R.id.tv_hotel_location)
        TextView mTvHotelLocation;
        //点评数
        @BindView(R.id.tv_comment_number)
        TextView mTvCommentNumber;
        //人均价格
        @BindView(R.id.tv_per_capita_price)
        TextView mTvPerCapitaPrice;
        //酒店民宿照片
        @BindView(R.id.image_hotel_reputation)
        RoundedImageView mImgHotel;
        @BindView(R.id.rlyt_hotel_item)
        RelativeLayout mRlytHotelItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
