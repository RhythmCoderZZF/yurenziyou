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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.model.response.StrategyBean;
import com.nbhysj.coupon.model.response.TripHomePageResponse;
import com.nbhysj.coupon.ui.StrategyCommentListActivity;
import com.nbhysj.coupon.ui.StrategyWebActivity;
import com.nbhysj.coupon.ui.WebActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/15.
 * description:行程助手推荐适配器
 */
public class TravelAssisantRecommendAdapter extends RecyclerView.Adapter<TravelAssisantRecommendAdapter.ViewHolder> {

    List<StrategyBean> strategyList;
    private Context mContext;

    public TravelAssisantRecommendAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setTravelAssisantStrategyList(List<StrategyBean> strategyList) {

        this.strategyList = strategyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travel_assistant_recommend_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            StrategyBean strategyEntity = strategyList.get(itemPosition);
            int articleId = strategyEntity.getId();
            String photoUrl = strategyEntity.getPhoto();
            String title = strategyEntity.getTitle();
            String intro = strategyEntity.getIntro();
            String strategyH5Url = strategyEntity.getStrategyH5Url();

            holder.mTvStrategyName.setText(title);
            if(!TextUtils.isEmpty(intro)) {
                holder.mTvStrategyDes.setText(intro);
            } else {
                holder.mTvStrategyDes.setText("");
            }
            GlideUtil.loadImage(mContext, photoUrl, holder.mImgStrategy);

            holder.mRlytStrategyItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(!TextUtils.isEmpty(strategyH5Url))
                    {
                        Intent intent = new Intent();
                        intent.setClass(mContext, StrategyWebActivity.class);
                        intent.putExtra("title", Constants.STRATEGY_H5_TITEL);
                        intent.putExtra("url",strategyH5Url);
                        intent.putExtra("articleId",articleId);
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

        //景点照片
        ImageView mImgStrategy;

        TextView mTvStrategyName;

        TextView mTvStrategyDes;

        RelativeLayout mRlytStrategyItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgStrategy = itemView.findViewById(R.id.image_strategy);
            mTvStrategyName = itemView.findViewById(R.id.tv_strategy_name);
            mTvStrategyDes = itemView.findViewById(R.id.tv_strategy_des);
            mRlytStrategyItem = itemView.findViewById(R.id.rlyt_strategy_item);
        }
    }

}
