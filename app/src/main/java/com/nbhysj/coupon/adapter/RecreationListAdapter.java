package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/21.
 * description : 互动列表适配器
 */
public class RecreationListAdapter extends RecyclerView.Adapter<RecreationListAdapter.ViewHolder> {

    /**
     * 酒店问答内容
     */
    List<MchTypeBean> popularScenicSpotsList;
    private Context mContext;

    public RecreationListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setRecreationListList(List<MchTypeBean> popularScenicSpotsList) {

        this.popularScenicSpotsList = popularScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_interaction_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            MchTypeBean popularScenicSpots = popularScenicSpotsList.get(itemPosition);
            String photoUrl = popularScenicSpots.getPhoto();
            holder.mTvPopularScenicSpotPrice.setText(String.valueOf(popularScenicSpots.getConsumePrice()));
            holder.mTvPopularScenicSpotScore.setText(String.valueOf(popularScenicSpots.getCommentScore()) + "分");
            holder.mTvScenicSpotCommentNum.setText(popularScenicSpots.getCommentNum() + "条点评数");
            holder.mTvMchName.setText(popularScenicSpots.getMchName());
            holder.mTvScenicSpotsDistance.setText("距您" + popularScenicSpots.getDistance() + "公里");
            String city = popularScenicSpots.getCity();
            String country = popularScenicSpots.getCounty();
            holder.mTvLocation.setText(city + "." + country);
            List<MchTypeBean.TagsEntity> tagsEntityList = popularScenicSpots.getTags();

            GlideUtil.loadImage(mContext, photoUrl, holder.mImgScenicSpots);

            if (tagsEntityList != null) {
                if (tagsEntityList.size() > 0) {
                    holder.mTagFlowlayoutFineFood.setAdapter(new TagAdapter<MchTypeBean.TagsEntity>(tagsEntityList) {

                        @Override
                        public View getView(FlowLayout parent, int position, MchTypeBean.TagsEntity tagsEntity) {
                            LayoutInflater mInflater = LayoutInflater.from(mContext);
                            TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_interaction,
                                    holder.mTagFlowlayoutFineFood, false);
                            tagName.setText(tagsEntity.getTitle());
                            return tagName;
                        }
                    });
                }
            }

            holder.mRlytScenicSpotItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, ScenicSpotDetailActivity.class);
                    mContext.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return popularScenicSpotsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //景区评分
        @BindView(R.id.tv_scenic_spots_score)
        TextView mTvPopularScenicSpotScore;
        //景区门票
        @BindView(R.id.tv_per_capita_price)
        TextView mTvPopularScenicSpotPrice;
        //商户名字
        @BindView(R.id.tv_mch_name)
        TextView mTvMchName;
        //景区照片
        @BindView(R.id.image_scenic_spots)
        RoundedImageView mImgScenicSpots;
        //点评数
        @BindView(R.id.tv_comment_num)
        TextView mTvScenicSpotCommentNum;
        //位置距离
        @BindView(R.id.tv_scenic_spots_distance)
        TextView mTvScenicSpotsDistance;
        //标签
        @BindView(R.id.flowlayout_recreation_label)
        TagFlowLayout mTagFlowlayoutFineFood;
        @BindView(R.id.rlyt_scenic_spot_item)
        RelativeLayout mRlytScenicSpotItem;
        //位置
        @BindView(R.id.tv_location)
        TextView mTvLocation;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
