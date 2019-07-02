package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.PopularScenicSpotsResponse;
import com.nbhysj.coupon.model.response.ScenicSpotBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/22.
 * description : 目的地景点适配器
 */
public class DestinationScenicSpotsBannerAdapter extends RecyclerView.Adapter<DestinationScenicSpotsBannerAdapter.ViewHolder> {

    List<ScenicSpotBean> popularScenicSpotsList;
    private Context mContext;

    public DestinationScenicSpotsBannerAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setPopularScenicSpotsList(List<ScenicSpotBean> popularScenicSpotsList) {

        this.popularScenicSpotsList = popularScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_destination_scenic_spot_banner_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            ScenicSpotBean scenicSpotBean = popularScenicSpotsList.get(itemPosition);
            holder.mTvPopularScenicSpotName.setText(scenicSpotBean.getDataName());
            List<ScenicSpotBean.TagsEntity> tagsEntityList = scenicSpotBean.getTags();
            String photoUrl = scenicSpotBean.getPhoto();
            GlideUtil.loadCornersTransformImage(mContext, photoUrl, 5, holder.mImgScenicSpots);

            if (tagsEntityList != null) {
                TagAdapter tagAdapter = new TagAdapter<ScenicSpotBean.TagsEntity>(tagsEntityList) {
                    @Override
                    public View getView(FlowLayout parent, int position, ScenicSpotBean.TagsEntity tagsEntity) {
                        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_independent_travel,
                                holder.mTagFlowLayoutScenicSpotLabel, false);
                        if (position == 0) {
                            view.setBackgroundResource(R.drawable.bg_tag_radius_purplish_red_yellow_gradient);
                        } else {
                            view.setBackgroundResource(R.drawable.bg_tag_radius_two_black_shape_white_border);
                            view.getBackground().setAlpha(40);
                        }

                        TextView tv = view.findViewById(R.id.tv_flowlayout);
                        tv.setText(tagsEntity.getTitle());
                        return view;
                    }
                };

                holder.mTagFlowLayoutScenicSpotLabel.setAdapter(tagAdapter);
            }

            if (itemPosition == 0) {

                holder.mHeader.setVisibility(View.VISIBLE);
            } else {

                holder.mHeader.setVisibility(View.GONE);
            }

            if (itemPosition == 3 - 1) {

                holder.mFooter.setVisibility(View.VISIBLE);
            } else {
                holder.mFooter.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //景区名字
        @BindView(R.id.tv_scenic_spots_name)
        TextView mTvPopularScenicSpotName;
        //景区特色
        @BindView(R.id.flowlayout_scenic_spot_label)
        TagFlowLayout mTagFlowLayoutScenicSpotLabel;
        //景区照片
        @BindView(R.id.image_scenic_spots)
        ImageView mImgScenicSpots;
        @BindView(R.id.view_header)
        View mHeader;
        @BindView(R.id.view_footer)
        View mFooter;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
