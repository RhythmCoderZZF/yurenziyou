package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hysj created at 2019/06/03.
 * description : 组合列表适配器
 */
public class CombinationListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<PopularScenicSpotsResponse> popularScenicSpotsList;
    private Context mContext;
    LayoutInflater mInflater;

    public CombinationListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setPopularScenicSpotsList(List<PopularScenicSpotsResponse> popularScenicSpotsList) {

        this.popularScenicSpotsList = popularScenicSpotsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_combination_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        try {
            // PopularScenicSpotsResponse popularScenicSpots = popularScenicSpotsList.get(itemPosition);
            if (holder instanceof ViewHolder) {
                ViewHolder holder1 = (ViewHolder) holder;
                //holder.mTvPopularScenicSpotName.setText(popularScenicSpots.getScenicSpotsName());
                // holder.mTvScenicSpotsDes.setText(popularScenicSpots.getScenicSpotsDes());


                RequestOptions myOptions = new RequestOptions()
                        .transform(new GlideRoundTransform(mContext, 5));

                Glide.with(mContext)
                        .load("http://k.zol-img.com.cn/sjbbs/7692/a7691515_s.jpg")
                        .apply(myOptions)
                        .into(holder1.mImgScenicSpots);

                List<String> fineFoodTagList = new ArrayList<>();
                fineFoodTagList.add("百年老字号");
                fineFoodTagList.add("菜色精致");
                fineFoodTagList.add("江浙菜");
                holder1.mTagFlowlayoutCombinationLabel.setAdapter(new TagAdapter<String>(fineFoodTagList) {

                    @Override
                    public View getView(FlowLayout parent, int position, String fineFoodTag) {
                        mInflater = LayoutInflater.from(mContext);
                        TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_orange_frame,
                                holder1.mTagFlowlayoutCombinationLabel, false);
                        tagName.setText(fineFoodTag);
                        return tagName;
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //景区名字
        TextView mTvPopularScenicSpotName;
        //美食图片
        ImageView mImgScenicSpots;
        //组合标签
        TagFlowLayout mTagFlowlayoutCombinationLabel;

        public ViewHolder(View itemView) {
            super(itemView);

            //mTvPopularScenicSpotName = itemView.findViewById(R.id.tv_scenic_spots_name);
            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mTagFlowlayoutCombinationLabel = itemView.findViewById(R.id.flowlayout_combination_label);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}
