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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;

/**
 * @author hysj created at 2019/09/16.
 * description : 酒店(榜单）列表适配器
 */
public class HotelBangDanListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MchTypeBean> hotelList;
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

    public HotelBangDanListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setHotelBangDanList(List<MchTypeBean> hotelList) {

        this.hotelList = hotelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_reputation_item, parent, false);//解决宽度不能铺满
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
                    MchTypeBean hotelResponse = hotelList.get(position - 1);
                    int commentScore = hotelResponse.getCommentScore();
                    String intro = hotelResponse.getIntro();
                    int level = hotelResponse.getLevel();
                    int commentNum = hotelResponse.getCommentNum();
                    int consumePrice = hotelResponse.getConsumePrice();
                    String photo = hotelResponse.getPhoto();
                    holder1.mTvHotelName.setText(hotelResponse.getDataName());
                    holder1.mTvHotelReputationScore.setText(String.valueOf(commentScore));
                    if (intro != null) {

                        holder1.mTvHotelDescription.setText(intro);
                    } else {

                        holder1.mTvHotelDescription.setText("");
                    }
                    if (level == 0) {
                        holder1.mTvHotelStar.setVisibility(View.GONE);
                    } else {
                        holder1.mTvHotelStar.setText(level + "星级");
                        holder1.mTvHotelStar.setVisibility(View.VISIBLE);
                    }
                    // holder.mTvHotelType.setText(hotelReputationResponse.getHotelType());
                    holder1.mTvHotelLocation.setText(hotelResponse.getCounty());
                    holder1.mTvCommentNumber.setText(commentNum + "条点评");
                    holder1.mTvPerCapitaPrice.setText(String.valueOf(consumePrice));

                    List<MchTypeBean.TagsEntity> scenicSpotTagsList = hotelResponse.getTags();
                    if (scenicSpotTagsList != null) {

                        if (scenicSpotTagsList.size() > 0) {

                            holder1.mTvHotelType.setVisibility(View.VISIBLE);
                            MchTypeBean.TagsEntity tagsEntity = hotelResponse.getTags().get(0);
                            holder1.mTvHotelType.setText(tagsEntity.getTitle());
                        } else {

                            holder1.mTvHotelType.setVisibility(View.GONE);
                        }
                    } else {

                        holder1.mTvHotelType.setVisibility(View.GONE);
                    }
                    GlideUtil.loadImage(mContext, photo, holder1.mImgHotel);

                    holder1.mRlytHotelItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new Intent();
                            intent.setClass(mContext, HotelDetailsActivity.class);
                            mContext.startActivity(intent);
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
        return mHeaderView != null ? hotelList.size() : 0;
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
        //酒店名宿名字
        TextView mTvHotelName;
        //酒店评分
        TextView mTvHotelReputationScore;
        //酒店描述
        TextView mTvHotelDescription;
        //酒店星级
        TextView mTvHotelStar;
        //酒店类型
        TextView mTvHotelType;
        //酒店位置
        TextView mTvHotelLocation;
        //点评数
        TextView mTvCommentNumber;
        //人均价格
        TextView mTvPerCapitaPrice;
        //酒店民宿照片
        RoundedImageView mImgHotel;

        RelativeLayout mRlytHotelItem;
        public ViewHolder(View itemView) {
            super(itemView);

            mTvHotelName = itemView.findViewById(R.id.tv_hotel_name);
            mTvHotelReputationScore = itemView.findViewById(R.id.tv_hotel_reputation_score);
            mTvHotelDescription = itemView.findViewById(R.id.tv_hotel_description);
            mTvHotelStar = itemView.findViewById(R.id.tv_hotel_star);
            mTvHotelType = itemView.findViewById(R.id.tv_hotel_type);
            mTvHotelLocation = itemView.findViewById(R.id.tv_hotel_location);
            mTvCommentNumber = itemView.findViewById(R.id.tv_comment_number);
            mTvPerCapitaPrice = itemView.findViewById(R.id.tv_per_capita_price);
            mImgHotel = itemView.findViewById(R.id.image_hotel_reputation);
            mRlytHotelItem = itemView.findViewById(R.id.rlyt_hotel_item);
        }
    }
}
