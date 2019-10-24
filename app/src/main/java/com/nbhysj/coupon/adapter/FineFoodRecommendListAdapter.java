package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchFoodBean;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.FoodDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.StarBarView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * @author hysj created at 2019/4/21.
 * description : 美食推荐列表适配器
 */
public class FineFoodRecommendListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MchFoodBean> mFineFoodList;
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

    public FineFoodRecommendListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setFineFoodList(List<MchFoodBean> mFineFoodList) {

        this.mFineFoodList = mFineFoodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fine_food_recommend_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        try {
            // PopularScenicSpotsResponse popularScenicSpots = popularScenicSpotsList.get(itemPosition);
            if (getItemViewType(position) == TYPE_HEADER) return;
            final int pos = getRealPosition(holder);
            if (holder instanceof ViewHolder) {
                ViewHolder holder1 = (ViewHolder) holder;
                MchFoodBean fineFoodBean = mFineFoodList.get(pos);
                int mchId = fineFoodBean.getMchId();
                String photoUrl = fineFoodBean.getPhoto();
                String mchName = fineFoodBean.getTitle();
                int zanNum = fineFoodBean.getZanNum();
                String marketPrice = fineFoodBean.getMarketPrice();
                if(!TextUtils.isEmpty(mchName)) {
                    holder1.mTvMchName.setText(mchName);
                }
                holder1.mTvPraiseNum.setText(String.valueOf(zanNum));

                holder1.mTvFineFoodPrice.setText("¥" + marketPrice);

                if (position < 4) {
                    holder1.mTvFineFoodSerialNum.setText("TOP." + position);
                    holder1.mTvFineFoodSerialNum.setVisibility(View.VISIBLE);
                } else {
                    holder1.mTvFineFoodSerialNum.setVisibility(View.GONE);
                }

                GlideUtil.loadImage(mContext, photoUrl, holder1.mImgFineFood);

                holder1.mRlytFineFoodItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent();
                        intent.setClass(mContext, FoodDetailActivity.class);
                        intent.putExtra("mchId",mchId);
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
        return mHeaderView != null ? mFineFoodList.size() + 1: mFineFoodList.size();
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

        //商户名字
        TextView mTvMchName;
        //美食图片
        ImageView mImgFineFood;
        //美食标签
        TagFlowLayout mTagFlowlayoutFineFood;
        //美食序列号
        TextView mTvFineFoodSerialNum;
        //点赞数量
        TextView mTvPraiseNum;
        //美食价格
        TextView mTvFineFoodPrice;
        //评分星级
        StarBarView mStarBarView;

        RelativeLayout mRlytFineFoodItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mTvMchName = itemView.findViewById(R.id.tv_mch_name);
            mImgFineFood = itemView.findViewById(R.id.image_fine_food);
            mTvFineFoodSerialNum = itemView.findViewById(R.id.tv_fine_food_serial_number);
            mTvPraiseNum = itemView.findViewById(R.id.tv_praise_num);
            mTvFineFoodPrice = itemView.findViewById(R.id.tv_price);
            mRlytFineFoodItem = itemView.findViewById(R.id.rlyt_fine_food_item);
        }
    }
}
