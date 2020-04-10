package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.PopularScenicSpotsResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/21.
 * description : 景点(榜单）列表适配器
 */
public class ScenicSpotBangDanListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MchTypeBean> popularScenicSpotsList;
    private Context mContext;
    private View mHeaderView;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;
    LayoutInflater mInflater;
    private List<MchTypeBean.TagsEntity> tagsList = new ArrayList<>();

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
//        notifyItemChanged(1,1);
    }

    public ScenicSpotBangDanListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setPopularScenicSpotsList(List<MchTypeBean> popularScenicSpotsList) {

        this.popularScenicSpotsList = popularScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_scenic_spots_item, parent, false);//解决宽度不能铺满
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
                MchTypeBean popularScenicSpots = popularScenicSpotsList.get(position - 1);
                int mchId = popularScenicSpots.getDataId();
                String photoUrl = popularScenicSpots.getPhoto();
                holder1.mTvPopularScenicSpotPrice.setText(Tools.getTwoDecimalPoint(popularScenicSpots.getConsumePrice()));
                holder1.mTvPopularScenicSpotScore.setText(String.valueOf(popularScenicSpots.getCommentScore()));
                holder1.mTvScenicSpotCommentNum.setText(popularScenicSpots.getCommentNum() + "条点评数");
                holder1.mTvPopularScenicSpotName.setText(popularScenicSpots.getMchName());
                holder1.mTvScenicSpotsDes.setText(popularScenicSpots.getIntro());
                int level = popularScenicSpots.getLevel();
                tagsList.clear();
                MchTypeBean.TagsEntity tagsEntity = new MchTypeBean().new TagsEntity();
                if (level > 0) {
                    tagsEntity.setTitle(level + "A级景区");
                    tagsList.add(tagsEntity);
                }
               /* if (level == 0) {
                    holder1.mTvScenicSpotsLevel.setVisibility(View.GONE);
                } else {
                    holder1.mTvScenicSpotsLevel.setText(level + "A级景区");
                    holder1.mTvScenicSpotsLevel.setVisibility(View.VISIBLE);
                }

                List<MchTypeBean.TagsEntity> scenicSpotTagsList = popularScenicSpots.getTags();
                if (scenicSpotTagsList != null) {

                    if (scenicSpotTagsList.size() > 0) {

                        holder1.mTvScenicSpotsType.setVisibility(View.VISIBLE);
                        MchTypeBean.TagsEntity tagsEntity = popularScenicSpots.getTags().get(0);
                        holder1.mTvScenicSpotsType.setText(tagsEntity.getTitle());
                    } else {

                        holder1.mTvScenicSpotsType.setVisibility(View.GONE);
                    }
                } else {

                    holder1.mTvScenicSpotsType.setVisibility(View.GONE);
                }
*/

                List<MchTypeBean.TagsEntity> scenicSpotTagsList = popularScenicSpots.getTags();

                if (scenicSpotTagsList != null)
                {
                    if(tagsList.size() > 0 && scenicSpotTagsList.size() > 0)
                    {
                        MchTypeBean.TagsEntity tagBean = scenicSpotTagsList.get(0);
                        tagsList.add(tagBean);
                    } if(tagsList.size() == 0 && scenicSpotTagsList.size() < 2)
                    {
                    for (int i = 0; i < scenicSpotTagsList.size(); i++) {
                        MchTypeBean.TagsEntity tagsBean = scenicSpotTagsList.get(i);
                        tagsList.add(tagsBean);
                    }
                  }else {

                        if (scenicSpotTagsList.size() >= 2) {
                            for (int i = 0; i < 2; i++) {
                                MchTypeBean.TagsEntity tagsBean = scenicSpotTagsList.get(i);
                                tagsList.add(tagsBean);
                            }
                        }
                    }
                }

                if (tagsList != null) {
                    TagAdapter tagAdapter = new TagAdapter<MchTypeBean.TagsEntity>(tagsList) {
                        @Override
                        public View getView(FlowLayout parent, int position, MchTypeBean.TagsEntity tagsEntity) {
                            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag,
                                    holder1.mTagFlowlayoutHotLabel, false);
                            TextView mTvFlowlayout = view.findViewById(R.id.tv_flowlayout);
                            String title = tagsEntity.getTitle();
                            mTvFlowlayout.setText(title);
                            if (position == 0) {
                                view.setBackgroundResource(R.drawable.bg_tag_radius_purplish_red_yellow_gradient);

                                mTvFlowlayout.setTextColor(mContext.getResources().getColor(R.color.white));
                            } else {
                                view.setBackgroundResource(R.drawable.bg_stroke_radius_hotel_reputation);
                                mTvFlowlayout.setTextColor(mContext.getResources().getColor(R.color.color_text_gray20));
                            }
                            return view;
                        }
                    };

                    holder1.mTagFlowlayoutHotLabel.setAdapter(tagAdapter);
                }
                GlideUtil.loadImage(mContext, photoUrl, holder1.mImgScenicSpots);

                holder1.mRlytScenicSpotItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent();
                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchId",mchId);
                        String mchScenicType = MchTypeEnum.MCH_SCENIC.getValue();
                        intent.putExtra("mchType", mchScenicType);
                        mContext.startActivity(intent);
                    }
                });
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
        return mHeaderView != null ? popularScenicSpotsList.size() + 1:popularScenicSpotsList.size();
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

    class ViewHolder extends RecyclerView.ViewHolder {

        //景区评分
        TextView mTvPopularScenicSpotScore;
        //景区门票
        TextView mTvPopularScenicSpotPrice;
        //景区名字
        TextView mTvPopularScenicSpotName;
        //景区照片
        RoundedImageView mImgScenicSpots;
        //点评数
        TextView mTvScenicSpotCommentNum;
        //景点描述
        TextView mTvScenicSpotsDes;
        //星级
        TextView mTvScenicSpotsLevel;
        //景点类型
        TextView mTvScenicSpotsType;
        RelativeLayout mRlytScenicSpotItem;

        TagFlowLayout mTagFlowlayoutHotLabel;
        public ViewHolder(View itemView) {
            super(itemView);

            mTvPopularScenicSpotScore = itemView.findViewById(R.id.tv_scenic_spots_score);
            mTvPopularScenicSpotPrice = itemView.findViewById(R.id.tv_scenic_spots_price);
            mTvPopularScenicSpotName = itemView.findViewById(R.id.tv_scenic_spots_name);
            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mTvScenicSpotCommentNum = itemView.findViewById(R.id.tv_comment_num);
            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mTvScenicSpotsDes = itemView.findViewById(R.id.tv_scenic_spots_des);
       /*     mTvScenicSpotsLevel = itemView.findViewById(R.id.tv_scenic_spots_level);
            mTvScenicSpotsType = itemView.findViewById(R.id.tv_scenic_spot_type);*/
            mRlytScenicSpotItem = itemView.findViewById(R.id.rlyt_scenic_spot_item);
            mTagFlowlayoutHotLabel = itemView.findViewById(R.id.flowlayout_hot_label);

        }
    }
}
