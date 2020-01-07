package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.GroupMchDetailsResponse;
import com.nbhysj.coupon.ui.GroupMchDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/09/23.
 * description : 更多组合适配器
 */
public class GroupMchListMoreAdapter extends RecyclerView.Adapter<GroupMchListMoreAdapter.ViewHolder> {

    List<GroupMchDetailsResponse.NearbyFoodEntity> groupMchMoreList;
    private Context mContext;

    public GroupMchListMoreAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setGroupMchListMore(List<GroupMchDetailsResponse.NearbyFoodEntity> groupMchMoreList) {

        this.groupMchMoreList = groupMchMoreList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_mch_more_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            GroupMchDetailsResponse.NearbyFoodEntity groupMchMoreEntity = groupMchMoreList.get(itemPosition);
            int groupMchPackageId = groupMchMoreEntity.getId();
            String title = groupMchMoreEntity.getTitle();
            String photoUrl = groupMchMoreEntity.getPhoto();
            double score = groupMchMoreEntity.getScore();
            double price = groupMchMoreEntity.getPrice();

            GlideUtil.loadImage(mContext,photoUrl,holder.mImgGroupMch);
            holder.mTvGroupMchScore.setText(score + "分");
            holder.mTvGroupMchPrice.setText(String.valueOf(price));
            holder.mTvGroupMchName.setText(String.valueOf(title));

            holder.mRlytGroupMchMoreItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.putExtra("packageId",groupMchPackageId);
                    intent.setClass(mContext, GroupMchDetailsActivity.class);
                    mContext.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return groupMchMoreList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //组合更多
        @BindView(R.id.rlyt_group_mch_more_item)
        RelativeLayout mRlytGroupMchMoreItem;
        //组合图片
        @BindView(R.id.image_group_mch)
        ImageView mImgGroupMch;
        //组合名字
        @BindView(R.id.tv_group_mch_name)
        TextView mTvGroupMchName;
        //组合评分
        @BindView(R.id.tv_group_mch_score)
        TextView mTvGroupMchScore;
        //组合价格
        @BindView(R.id.tv_group_mch_price)
        TextView mTvGroupMchPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
