package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.GroupMchResponse;
import com.nbhysj.coupon.ui.GroupMchDetailsActivity;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hysj created at 2019/09/22.
 * description : 组合列表适配器
 */
public class GroupMchListAdapter extends RecyclerView.Adapter<GroupMchListAdapter.ViewHolder> {

    List<GroupMchResponse.PackageVOSEntity> groupMchList;

    private Context mContext;
    LayoutInflater mInflater;

    public GroupMchListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setGroupMchList(List<GroupMchResponse.PackageVOSEntity> groupMchList) {

        this.groupMchList = groupMchList;
    }

    @Override
    public GroupMchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group_mch_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMchListAdapter.ViewHolder holder, int position) {

        try {
            GroupMchResponse.PackageVOSEntity packageVOSEntity = groupMchList.get(position);
            String photoUrl = packageVOSEntity.getPhoto();

            String title = packageVOSEntity.getTitle();
            String intro = packageVOSEntity.getIntro();
            int marketPrice = packageVOSEntity.getMarketPrice();
            int packageId = packageVOSEntity.getId();
            GlideUtil.loadImage(mContext, photoUrl, holder.mImgGroupMch);
            holder.mTvGroupMchName.setText(title);
            holder.mTvGroupMchDes.setText(intro);
            holder.mTvGroupPrice.setText(String.valueOf(marketPrice));

            List<String> fineFoodTagList = new ArrayList<>();
            fineFoodTagList.add("主题乐园");
            fineFoodTagList.add("酒店+景区");
            holder.mTagFlowlayoutCombinationLabel.setAdapter(new TagAdapter<String>(fineFoodTagList) {

                @Override
                public View getView(FlowLayout parent, int position, String fineFoodTag) {

                    View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_independent_travel,
                            holder.mTagFlowlayoutCombinationLabel, false);
                    TextView mTvGroupMchTitle = view.findViewById(R.id.tv_flowlayout);
                    if (position == 0) {
                        view.setBackgroundResource(R.drawable.bg_tag_radius_two_green_and_blue_gradient);
                        mTvGroupMchTitle.setTextColor(mContext.getResources().getColor(R.color.color_green6));
                    } else {
                        view.setBackgroundResource(R.drawable.bg_stroke_radius_two_black_shape);
                        mTvGroupMchTitle.setTextColor(mContext.getResources().getColor(R.color.color_green7));
                    }
                    view.getBackground().setAlpha(40);
                    TextView tv = view.findViewById(R.id.tv_flowlayout);
                    tv.setText(fineFoodTag);
                    return view;
                }
            });

            holder.mRlytGroupMchItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent();
                    intent.putExtra("packageId", packageId);
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
        return groupMchList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //组合商品名字
        TextView mTvGroupMchName;
        //组合商户图片
        ImageView mImgGroupMch;
        //组合标签
        TagFlowLayout mTagFlowlayoutCombinationLabel;
        RelativeLayout mRlytGroupMchItem;
        //组合商品描述
        TextView mTvGroupMchDes;
        //组合商品价格
        TextView mTvGroupPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            mRlytGroupMchItem = itemView.findViewById(R.id.rlyt_group_mch_item);
            mImgGroupMch = itemView.findViewById(R.id.image_group_mch);
            mTagFlowlayoutCombinationLabel = itemView.findViewById(R.id.flowlayout_group_mch_label);
            mTvGroupMchName = itemView.findViewById(R.id.tv_group_mch_name);
            mTvGroupMchDes = itemView.findViewById(R.id.tv_group_mch_des);
            mTvGroupPrice = itemView.findViewById(R.id.tv_group_price);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}
