package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;

/**
 * @author hysj created at 2019/4/21.
 * description : 互动类目列表适配器
 */
public class RecreationCateListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MchTypeBean> popularScenicSpotsList;
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

    public RecreationCateListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setPopularScenicSpotsList(List<MchTypeBean> popularScenicSpotsList) {

        this.popularScenicSpotsList = popularScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_interaction_item, parent, false);//解决宽度不能铺满
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

                MchTypeBean popularScenicSpots = popularScenicSpotsList.get(pos);
                String photoUrl = popularScenicSpots.getPhoto();
                int mchId = popularScenicSpots.getId();
                holder1.mTvPopularScenicSpotPrice.setText(String.valueOf(popularScenicSpots.getConsumePrice()));
                holder1.mTvPopularScenicSpotScore.setText(String.valueOf(popularScenicSpots.getCommentScore()));
                holder1.mTvScenicSpotCommentNum.setText(popularScenicSpots.getCommentNum() + "条点评数");
                holder1.mTvMchName.setText(popularScenicSpots.getMchName());
                holder1.mTvScenicSpotsDistance.setText("距您" + popularScenicSpots.getDistance() + "公里");
                String city = popularScenicSpots.getCity();
                String country = popularScenicSpots.getCounty();
                holder1.mTvLocation.setText(city + "." + country);
                List<MchTypeBean.TagsEntity> tagsEntityList = popularScenicSpots.getTags();

                GlideUtil.loadImage(mContext, photoUrl, holder1.mImgScenicSpots);

                if (tagsEntityList != null) {
                    if (tagsEntityList.size() > 0) {
                        holder1.mTagFlowlayoutFineFood.setAdapter(new TagAdapter<MchTypeBean.TagsEntity>(tagsEntityList) {

                            @Override
                            public View getView(FlowLayout parent, int position, MchTypeBean.TagsEntity tagsEntity) {
                                LayoutInflater mInflater = LayoutInflater.from(mContext);
                                TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_interaction,
                                        holder1.mTagFlowlayoutFineFood, false);
                                tagName.setText(tagsEntity.getTitle());
                                return tagName;
                            }
                        });
                    }
                }

                holder1.mRlytScenicSpotItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent();
                        intent.setClass(mContext, ScenicSpotDetailActivity.class);
                        intent.putExtra("mchId",mchId);
                        String mchType = MchTypeEnum.MCH_RECREATION.getValue();
                        intent.putExtra("mchType", mchType);
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
        return mHeaderView != null ? popularScenicSpotsList.size() + 1 : popularScenicSpotsList.size();
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
        //商户名字
        TextView mTvMchName;
        //景区照片
        RoundedImageView mImgScenicSpots;
        //点评数
        TextView mTvScenicSpotCommentNum;
        //位置距离
        TextView mTvScenicSpotsDistance;
        //标签
        TagFlowLayout mTagFlowlayoutFineFood;
        RelativeLayout mRlytScenicSpotItem;
        //位置
        TextView mTvLocation;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvPopularScenicSpotScore = itemView.findViewById(R.id.tv_scenic_spots_score);
            mTvPopularScenicSpotPrice = itemView.findViewById(R.id.tv_per_capita_price);
            mTvMchName = itemView.findViewById(R.id.tv_mch_name);
            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mTvScenicSpotCommentNum = itemView.findViewById(R.id.tv_comment_num);
            mTvScenicSpotsDistance = itemView.findViewById(R.id.tv_scenic_spots_distance);
            mTagFlowlayoutFineFood = itemView.findViewById(R.id.flowlayout_recreation_label);
            mTvLocation = itemView.findViewById(R.id.tv_location);
            mRlytScenicSpotItem = itemView.findViewById(R.id.rlyt_scenic_spot_item);

        }
    }
}
