package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.Date;
import java.util.List;

/**
 * @author hysj created at 2019/05/15.
 * description: 行程助手列表适配器
 */
public class MyTravelListAdapter extends RecyclerView.Adapter<MyTravelListAdapter.ViewHolder> {

    List<TripHomePageResponse.TripEntity> mTripEntityList;
    private Context mContext;
    private MyTravelListener myTravelListener;

    public MyTravelListAdapter(Context mContext, MyTravelListener myTravelListener) {

        this.mContext = mContext;
        this.myTravelListener = myTravelListener;
    }

    public void setMyTravelList(List<TripHomePageResponse.TripEntity> mTripEntityList) {

        this.mTripEntityList = mTripEntityList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_travel_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            TripHomePageResponse.TripEntity tripEntity = mTripEntityList.get(itemPosition);

            String title = tripEntity.getTitle();
            String photoUrl = tripEntity.getPhoto();
            String startDate = tripEntity.getStartDate();
            String endDate = tripEntity.getEndDate();
            String startPlace = tripEntity.getStartPlace();
            String endPlace = tripEntity.getEndPlace();

            GlideUtil.loadImage(mContext, photoUrl, holder.mImgScenicSpots);

            if(!TextUtils.isEmpty(startPlace))
            {
                holder.mTvTripPath.setText(startPlace + "-" + endPlace);
            }

            if(!TextUtils.isEmpty(endDate))
            {
                Date endTime = DateUtil.getDateStrToDate(endDate, DateUtil.sDateYMDFormat);

                Date date = new Date();

                if (endTime.before(date)) {

                    holder.mTvTravelAssistantTag.setText("即将出行");

                } else {

                    holder.mTvTravelAssistantTag.setText("行程结束");
                }
            }
            String mStartDate = DateUtil.toMMDD(startDate);
            String mEndDate = DateUtil.toMMDD(endDate);



            holder.mTvMyTravelDate.setText(mStartDate + "-" + mEndDate);

            holder.mTvMyTravelAssistant.setText(title);

            holder.mLlytMyTravelItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    myTravelListener.setMyTravelListener(itemPosition);
                }
            });

            holder.mLlytMyTravelItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    myTravelListener.setMyTravelEditListener(itemPosition);
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mTripEntityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //景点照片
        RoundedImageView mImgScenicSpots;
        LinearLayout mLlytMyTravelItem;
        //行程路径
        TextView mTvTripPath;

        //行程日期
        TextView mTvMyTravelDate,mTvMyTravelAssistant;

        TextView mTvTravelAssistantTag;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mLlytMyTravelItem = itemView.findViewById(R.id.llyt_my_travel_item);
            mTvTripPath = itemView.findViewById(R.id.tv_trip_path);
            mTvMyTravelDate = itemView.findViewById(R.id.tv_my_travel_date);
            mTvMyTravelAssistant = itemView.findViewById(R.id.tv_travel_assistant_title);
            mTvTravelAssistantTag = itemView.findViewById(R.id.tv_travel_assistant_tag);

        }
    }

    public interface MyTravelListener {

        void setMyTravelListener(int position);


        void setMyTravelEditListener(int position);
    }
}
