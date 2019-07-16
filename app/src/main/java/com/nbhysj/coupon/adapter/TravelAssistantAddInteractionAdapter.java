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
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hysj created at 2019/05/17.
 * description: 添加互动适配器
 */
public class TravelAssistantAddInteractionAdapter extends RecyclerView.Adapter<TravelAssistantAddInteractionAdapter.ViewHolder> {

    List<TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity> scenicSpotList;
    private Context mContext;
    private ScenicSpotAddListener scenicSpotAddListener;

    public TravelAssistantAddInteractionAdapter(Context mContext, ScenicSpotAddListener scenicSpotAddListener) {

        this.mContext = mContext;
        this.scenicSpotAddListener = scenicSpotAddListener;
    }

    public void setTravelAssistantInteractionList(List<TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity> scenicSpotList) {

        this.scenicSpotList = scenicSpotList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_add_interaction_item, parent, false);//解决宽度不能铺满
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
            double score = travelAssistantAddScenicSpotEntity.getScore();
            int commentNum = travelAssistantAddScenicSpotEntity.getCommentNum();

           // travelAssistantAddScenicSpotEntity.get
            GlideUtil.loadImage(mContext, photoUrl, holder.mImgInteraction);
            holder.mTvInteractionName.setText(mchName);
            holder.mTvScore.setText(String.valueOf(score));
            holder.mTvCommentNum.setText(commentNum + "条点评");

            holder.mTvScenicSpotsDistance.setText("距您5.8公里");

            holder.mTvLocation.setText("宁波.鄞州");

            if (!TextUtils.isEmpty(scenicSpotTag))
            {
                List<String> scenicSpotTagList = new ArrayList<>();
                holder.mTagFlowlayoutInteraction.setVisibility(View.VISIBLE);
                scenicSpotTagList.add(scenicSpotTag);
                holder.mTagFlowlayoutInteraction.setAdapter(new TagAdapter<String>(scenicSpotTagList) {

                    @Override
                    public View getView(FlowLayout parent, int position, String fineFoodTag) {
                        LayoutInflater mInflater = LayoutInflater.from(mContext);
                        TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_orange_frame,
                                holder.mTagFlowlayoutInteraction, false);
                        tagName.setText(fineFoodTag);
                        return tagName;
                    }
                });
            } else {

                holder.mTagFlowlayoutInteraction.setVisibility(View.GONE);
            }

            holder.mLlytAddInteraction.setOnClickListener(new View.OnClickListener() {
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
        RoundedImageView mImgInteraction;

        //景点标签
        TagFlowLayout mTagFlowlayoutInteraction;

        LinearLayout mLlytAddInteractionItem,mLlytAddInteraction;

        ImageView mImgAddMyTravel;

        //互动名称
        TextView mTvInteractionName;

        //评分
        TextView mTvScore;

        //距离多少公里
        TextView mTvScenicSpotsDistance;

        TextView mTvCommentNum,mTvLocation;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgInteraction = itemView.findViewById(R.id.image_interaction);
            mTvInteractionName = itemView.findViewById(R.id.tv_interaction_name);
            mTagFlowlayoutInteraction = itemView.findViewById(R.id.flowlayout_interaction_label);
            mLlytAddInteractionItem = itemView.findViewById(R.id.llyt_add_interaction_item);
            mLlytAddInteraction = itemView.findViewById(R.id.llyt_add_interaction);
            mImgAddMyTravel = itemView.findViewById(R.id.img_add_my_travel);
            mTvScore = itemView.findViewById(R.id.tv_score);
            mTvCommentNum = itemView.findViewById(R.id.tv_comment_num);
            mTvScenicSpotsDistance = itemView.findViewById(R.id.tv_scenic_spots_distance);
            mTvLocation = itemView.findViewById(R.id.tv_location);
        }
    }

    public interface ScenicSpotAddListener {

        void setScenicSpotAddListener(TripScenicSpotAddCountryBean.TravelAssistantAddScenicSpotEntity travelAssistantAddScenicSpotEntity);

    }

}
