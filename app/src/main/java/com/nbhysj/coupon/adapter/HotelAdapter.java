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
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/19.
 * description : 酒店名宿适配器
 */
public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    List<MchTypeBean> hotelList;
    private Context mContext;

    public HotelAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setHotelList(List<MchTypeBean> hotelList) {

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
            MchTypeBean hotelResponse = hotelList.get(itemPosition);
            float commentScore = hotelResponse.getCommentScore();
            String intro = hotelResponse.getIntro();
            int level = hotelResponse.getLevel();
            int commentNum = hotelResponse.getCommentNum();
            int consumePrice = hotelResponse.getConsumePrice();
            String photo = hotelResponse.getPhoto();
            holder.mTvHotelName.setText(hotelResponse.getMchName());
            int mchId = hotelResponse.getId();
            String mchType = hotelResponse.getMchType();
            String mchType2 = hotelResponse.getMchType2();
            holder.mTvHotelReputationScore.setText(String.valueOf(commentScore));
            if (intro != null) {

                holder.mTvHotelDescription.setText(intro);
            } else {

                holder.mTvHotelDescription.setText("");
            }
            if (level == 0) {
                holder.mTvHotelStar.setVisibility(View.GONE);
            } else {
                holder.mTvHotelStar.setText(level + "星级");
                holder.mTvHotelStar.setVisibility(View.VISIBLE);
            }
            // holder.mTvHotelType.setText(hotelReputationResponse.getHotelType());
            holder.mTvHotelLocation.setText(hotelResponse.getCounty());
            holder.mTvCommentNumber.setText(commentNum + "条点评");
            holder.mTvPerCapitaPrice.setText(String.valueOf(consumePrice));

            List<MchTypeBean.TagsEntity> scenicSpotTagsList = hotelResponse.getTags();
            if (scenicSpotTagsList != null) {

                if (scenicSpotTagsList.size() > 0) {

                    holder.mTvHotelType.setVisibility(View.VISIBLE);
                    MchTypeBean.TagsEntity tagsEntity = hotelResponse.getTags().get(0);
                    holder.mTvHotelType.setText(tagsEntity.getTitle());
                } else {

                    holder.mTvHotelType.setVisibility(View.GONE);
                }
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
                            intent.putExtra("mchId", mchId);
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
