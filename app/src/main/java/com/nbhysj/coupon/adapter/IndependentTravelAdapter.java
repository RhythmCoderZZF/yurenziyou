package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.GroupGoodsBean;
import com.nbhysj.coupon.ui.GroupMchDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/19.
 * description : 商城自由行适配器
 */
public class IndependentTravelAdapter extends RecyclerView.Adapter<IndependentTravelAdapter.ViewHolder> {

    List<GroupGoodsBean> groupGoodsList;
    private Context mContext;
    RequestOptions myOptions;

    public IndependentTravelAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setIndependentTravelList(List<GroupGoodsBean> groupGoodsList) {

        this.groupGoodsList = groupGoodsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_independent_travel_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            GroupGoodsBean groupGoodsBean = groupGoodsList.get(itemPosition);
            int groupId = groupGoodsBean.getId();
            String title = groupGoodsBean.getTitle();
            String packageTitle = groupGoodsBean.getPackageTitle();
            String photo = groupGoodsBean.getPhoto();
            List<String> IndependentTravelTagList = groupGoodsBean.getTags();
            holder.mTvIndependentTravelTitle.setText(title);
            holder.mTvIndependentTravelDes.setText(packageTitle);
            GlideUtil.loadCornersTransformImage(mContext, photo, 5, holder.mImgIndependentTravel);

            if (IndependentTravelTagList != null) {
                TagAdapter tagAdapter = new TagAdapter<String>(IndependentTravelTagList) {
                    @Override
                    public View getView(FlowLayout parent, int position, String option) {
                        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_independent_travel,
                                holder.mTagFlowLayoutTravelLabel, false);
                        if (position == 0) {
                            view.setBackgroundResource(R.drawable.bg_tag_radius_purplish_red_yellow_gradient);
                        } else {
                            view.setBackgroundResource(R.drawable.bg_tag_radius_two_black_shape_white_border);
                            view.getBackground().setAlpha(40);
                        }

                        TextView tv = view.findViewById(R.id.tv_flowlayout);
                        tv.setText(option);
                        return view;
                    }
                };

                holder.mTagFlowLayoutTravelLabel.setAdapter(tagAdapter);
            }

            holder.mLlytGroupGoodsItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent mIntent = new Intent();
                    mIntent.setClass(mContext, GroupMchDetailsActivity.class);
                    mIntent.putExtra("packageId",groupId);
                    mContext.startActivity(mIntent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return groupGoodsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //自由行图片
        @BindView(R.id.image_independent_travel)
        ImageView mImgIndependentTravel;
        //自由行标签
        @BindView(R.id.flowlayout_independent_travel_label)
        TagFlowLayout mTagFlowLayoutTravelLabel;
        //自由行标题
        @BindView(R.id.tv_independent_travel_title)
        TextView mTvIndependentTravelTitle;
        //自由行描述
        @BindView(R.id.tv_independent_travel_des)
        TextView mTvIndependentTravelDes;
        //组合商品Item
        @BindView(R.id.llyt_group_goods_item)
        LinearLayout mLlytGroupGoodsItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
