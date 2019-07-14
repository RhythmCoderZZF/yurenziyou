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
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.TripScenicSpotAddCountryBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hysj created at 2019/05/17.
 * description: 添加景点适配器
 */
public class TravelAssistantAddScenicSpotAdapter extends RecyclerView.Adapter<TravelAssistantAddScenicSpotAdapter.ViewHolder> {

    List<TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity> scenicSpotList;
    private Context mContext;
    private ScenicSpotAddListener scenicSpotAddListener;

    public TravelAssistantAddScenicSpotAdapter(Context mContext, ScenicSpotAddListener scenicSpotAddListener) {

        this.mContext = mContext;
        this.scenicSpotAddListener = scenicSpotAddListener;
    }

    public void setScenicSpotList(List<TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity> scenicSpotList) {

        this.scenicSpotList = scenicSpotList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_add_scenic_spot_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity travelAssistantAddScenicSpotEntity = scenicSpotList.get(itemPosition);

            String photoUrl = travelAssistantAddScenicSpotEntity.getPhoto();
            int level = travelAssistantAddScenicSpotEntity.getLevel();
            String scenicSpotTag = travelAssistantAddScenicSpotEntity.getTags();
            int mSelectedStatus = travelAssistantAddScenicSpotEntity.getSelectedStatus();
            String mchName = travelAssistantAddScenicSpotEntity.getMchName();
            String intro = travelAssistantAddScenicSpotEntity.getIntro();
            double score = travelAssistantAddScenicSpotEntity.getScore();
            int commentNum = travelAssistantAddScenicSpotEntity.getCommentNum();
            GlideUtil.loadImage(mContext, photoUrl, holder.mImgScenicSpots);
            holder.mTvScenicSpotName.setText(mchName);
            holder.mTvScore.setText(String.valueOf(score));
            holder.mTvScenicSpotDes.setText(String.valueOf(intro));
            holder.mTvCommentNum.setText(commentNum + "条点评");

            List<String> scenicSpotTagList = new ArrayList<>();
            scenicSpotTagList.add(level + "A级景区");
            if (!TextUtils.isEmpty(scenicSpotTag))
            {
                scenicSpotTagList.add(scenicSpotTag);
            }

            holder.mTagFlowlayoutScenicSpot.setAdapter(new TagAdapter<String>(scenicSpotTagList) {

                @Override
                public View getView(FlowLayout parent, int position, String fineFoodTag) {
                    LayoutInflater mInflater = LayoutInflater.from(mContext);
                    TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_orange_frame,
                            holder.mTagFlowlayoutScenicSpot, false);
                    tagName.setText(fineFoodTag);
                    return tagName;
                }
            });

            holder.mLlytAddMyTravel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    scenicSpotAddListener.setScenicSpotAddListener(travelAssistantAddScenicSpotEntity);
                    // holder.mImgAddMyTravel.setBackgroundResource(R.mipmap.icon_travel_assistant_selected_tag);
                }
            });

            if (mSelectedStatus == 0) {

                holder.mImgAddMyTravel.setBackgroundResource(R.mipmap.icon_travel_assistant_selected_tag);
                holder.mImgAddMyTravel.setEnabled(false);
            } else {
                holder.mImgAddMyTravel.setBackgroundResource(R.mipmap.icon_travel_assistant_add_tag);
                holder.mImgAddMyTravel.setEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return scenicSpotList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //景点照片
        RoundedImageView mImgScenicSpots;
        //景点标签
        TagFlowLayout mTagFlowlayoutScenicSpot;

        LinearLayout mLlytAddMyTravel;

        ImageView mImgAddMyTravel;

        //景点名称
        TextView mTvScenicSpotName;

        TextView mTvScore;

        TextView mTvScenicSpotDes;

        TextView mTvCommentNum;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mTagFlowlayoutScenicSpot = itemView.findViewById(R.id.flowlayout_scenic_spot_label);
            mLlytAddMyTravel = itemView.findViewById(R.id.llyt_add_my_travel);
            mImgAddMyTravel = itemView.findViewById(R.id.img_add_my_travel);
            mTvScenicSpotName = itemView.findViewById(R.id.tv_scenic_spot_name);
            mTvScore = itemView.findViewById(R.id.tv_score);
            mTvScenicSpotDes = itemView.findViewById(R.id.tv_scenic_spot_des);
            mTvCommentNum = itemView.findViewById(R.id.tv_comment_num);
        }
    }

    public interface ScenicSpotAddListener {

        void setScenicSpotAddListener(TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity travelAssistantAddScenicSpotEntity);

    }

}
