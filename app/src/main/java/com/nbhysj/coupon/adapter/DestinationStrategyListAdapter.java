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

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.ui.WebActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/10/21.
 * description : 目的地攻略列表适配器
 */
public class DestinationStrategyListAdapter extends RecyclerView.Adapter<DestinationStrategyListAdapter.ViewHolder> {

    List<MchTypeBean> popularScenicSpotsList;
    private Context mContext;

    public DestinationStrategyListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setDestinationStrategyList(List<MchTypeBean> popularScenicSpotsList) {

        this.popularScenicSpotsList = popularScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_destination_interaction_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            MchTypeBean mchTypeBean = popularScenicSpotsList.get(itemPosition);
            String mchName = mchTypeBean.getMchName();
            int mchId = mchTypeBean.getDataId();
            String intro = mchTypeBean.getIntro();
            String strategyH5Url = mchTypeBean.getH5();
            String photoUrl = mchTypeBean.getPhoto();
            holder.mTvStrategysTitle.setText(mchName);
            holder.mTvStrategysContent.setText(intro);
            GlideUtil.loadImage(mContext,photoUrl,holder.mImgStrategys);

            holder.mLlytDestinationInteractionItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!TextUtils.isEmpty(strategyH5Url)) {

                        Intent intent = new Intent();
                        intent.putExtra("url", strategyH5Url);
                        intent.putExtra("title", Constants.STRATEGY_H5_TITEL);
                        intent.setClass(mContext, WebActivity.class);
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
        return popularScenicSpotsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //攻略标题
        @BindView(R.id.tv_strategys_title)
        TextView mTvStrategysTitle;
        //攻略内容
        @BindView(R.id.tv_strategys_content)
        TextView mTvStrategysContent;
        //攻略图片
        @BindView(R.id.img_strategys)
        ImageView mImgStrategys;

        @BindView(R.id.llyt_destination_interaction_item)
        LinearLayout mLlytDestinationInteractionItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
