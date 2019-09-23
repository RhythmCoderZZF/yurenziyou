package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.model.response.ContactsBean;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.StrategyResponse;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.ui.WebActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by hysj on 2019/09/22.
 * description: 攻略列表适配器
 */

public class StrategyListAdapter extends RecyclerView.Adapter<StrategyListAdapter.ViewHolder> {

    List<StrategyResponse.StrategyEntity> strategyList;
    private Context mContext;

    public StrategyListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setStrategyList(List<StrategyResponse.StrategyEntity> strategyList) {

        this.strategyList = strategyList;
    }

    @Override
    public StrategyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_strategy_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            StrategyResponse.StrategyEntity strategyEntity = strategyList.get(itemPosition);
            String photoUrl = strategyEntity.getPhoto();
            String title = strategyEntity.getTitle();
            int hits = strategyEntity.getHits();
            int collectionNum = strategyEntity.getCollectionNum();
            String intro = strategyEntity.getIntro();
            String strategyH5Url = strategyEntity.getStrategyH5Url();
            if(!TextUtils.isEmpty(intro))
            {
                holder.mTvStrategyName.setText(intro);
            }
            holder.mTvStrategyPraiseNum.setText(String.valueOf(hits));
            holder.mTvStrategyCollectionNum.setText(String.valueOf(collectionNum));

            GlideUtil.loadImage(mContext, photoUrl, holder.mImgStrategy);

            holder.mRlytStrategyItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(!TextUtils.isEmpty(strategyH5Url))
                    {
                        Intent intent = new Intent();
                        intent.setClass(mContext, WebActivity.class);
                        intent.putExtra("title", Constants.STRATEGY_H5_TITEL);
                        intent.putExtra("url",strategyH5Url);
                        mContext.startActivity(intent);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return strategyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //攻略照片
        @BindView(R.id.image_strategy)
        RoundedImageView mImgStrategy;
        //攻略名
        @BindView(R.id.tv_strategy_name)
        TextView mTvStrategyName;
        //攻略点赞数
        @BindView(R.id.tv_strategy_praise_num)
        TextView mTvStrategyPraiseNum;
        //攻略收藏数
        @BindView(R.id.tv_strategy_collection_num)
        TextView mTvStrategyCollectionNum;
        //攻略
        @BindView(R.id.rlyt_strategy_item)
        RelativeLayout mRlytStrategyItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
