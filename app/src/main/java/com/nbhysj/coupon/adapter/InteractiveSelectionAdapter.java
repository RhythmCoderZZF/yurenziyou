package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.PopularScenicSpotsResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/22.
 * description : 互动精选适配器
 */
public class InteractiveSelectionAdapter extends RecyclerView.Adapter<InteractiveSelectionAdapter.ViewHolder> {

    List<MchTypeBean> interactiveSelectionList;
    private Context mContext;

    public InteractiveSelectionAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setInteractiveSelectionList(List<MchTypeBean> interactiveSelectionList) {

        this.interactiveSelectionList = interactiveSelectionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_interactive_selection_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            MchTypeBean mchTypeBean = interactiveSelectionList.get(itemPosition);
            int consumePrice = mchTypeBean.getConsumePrice();
            holder.mTvPopularScenicSpotPrice.setText(String.valueOf(consumePrice));
            String mchName = mchTypeBean.getMchName();
            int mchId = mchTypeBean.getDataId();
            if(!TextUtils.isEmpty(mchName))
            {
                holder.mTvRecreationName.setText(mchName);
            }
            String photoUrl = mchTypeBean.getPhoto();

            GlideUtil.loadImage(mContext, photoUrl,  holder.mImgScenicSpots);
            if (itemPosition == 0) {

                holder.mHeader.setVisibility(View.VISIBLE);

            } else {

                holder.mHeader.setVisibility(View.GONE);
            }

            if (itemPosition == interactiveSelectionList.size() - 1) {

                holder.mFooter.setVisibility(View.VISIBLE);
            } else {
                holder.mFooter.setVisibility(View.GONE);
            }

            holder.mLlytInteractiveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent mIntent = new Intent();
                    String mchRecreationType = MchTypeEnum.MCH_RECREATION.getValue();
                    mIntent.putExtra("mchId", mchId);
                    mIntent.putExtra("mchType", mchRecreationType);
                    mIntent.setClass(mContext, ScenicSpotDetailActivity.class);
                    mContext.startActivity(mIntent);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return interactiveSelectionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //景区门票
        @BindView(R.id.tv_scenic_spots_price)
        TextView mTvPopularScenicSpotPrice;
        //互动名字
        @BindView(R.id.tv_recreation_name)
        TextView mTvRecreationName;
        //互动照片
        @BindView(R.id.image_scenic_spots)
        RoundedImageView mImgScenicSpots;
        @BindView(R.id.view_header)
        View mHeader;
        @BindView(R.id.view_footer)
        View mFooter;
        @BindView(R.id.tv_popular_scenic_spot_sequence)
        TextView mTvPopularScenicSpotSequence;
        //互动
        @BindView(R.id.llyt_interactive_item)
        LinearLayout mLlytInteractiveItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
