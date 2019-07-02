package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.DeliciousFoodRecommendResponse;
import com.nbhysj.coupon.model.response.HotelBean;
import com.nbhysj.coupon.model.response.HotelReputationResponse;
import com.nbhysj.coupon.model.response.ScenicSpotBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/19.
 * description : 酒店名宿适配器
 */
public class HotelHomestayAdapter extends RecyclerView.Adapter<HotelHomestayAdapter.ViewHolder> {

    /**
     * 酒店问答内容
     */
    List<ScenicSpotBean> hotelReputationList;
    private Context mContext;

    public HotelHomestayAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setHotelReputationList(List<ScenicSpotBean> hotelReputationList) {

        this.hotelReputationList = hotelReputationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_reputation_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            ScenicSpotBean hotelResponse = hotelReputationList.get(itemPosition);
            int commentScore = hotelResponse.getCommentScore();
            String intro = hotelResponse.getIntro();
            int level = hotelResponse.getLevel();
            int commentNum = hotelResponse.getCommentNum();
            int consumePrice = hotelResponse.getConsumePrice();
            String photo = hotelResponse.getPhoto();
            holder.mTvHotelName.setText(hotelResponse.getMchName());
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

            List<ScenicSpotBean.TagsEntity> scenicSpotTagsList = hotelResponse.getTags();
            if (scenicSpotTagsList != null) {

                if (scenicSpotTagsList.size() > 0) {

                    holder.mTvHotelType.setVisibility(View.VISIBLE);
                    ScenicSpotBean.TagsEntity tagsEntity = hotelResponse.getTags().get(0);
                    holder.mTvHotelType.setText(tagsEntity.getTitle());
                } else {

                    holder.mTvHotelType.setVisibility(View.GONE);
                }
            } else {

                holder.mTvHotelType.setVisibility(View.GONE);
            }
            GlideUtil.loadImage(mContext, photo, holder.mImgHotel);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return hotelReputationList.size();
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
        //酒店名宿类型

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
