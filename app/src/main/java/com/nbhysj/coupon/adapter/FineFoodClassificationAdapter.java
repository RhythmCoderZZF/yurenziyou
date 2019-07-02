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

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.ui.ScenicSpotBangDanListActivity;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/19.
 * description : 美食类型分类适配器
 */
public class FineFoodClassificationAdapter extends RecyclerView.Adapter<FineFoodClassificationAdapter.ViewHolder> {

    List<ScenicSpotHomePageResponse.CateEntity> scenicSpotClassifiyList;
    private Context mContext;

    public FineFoodClassificationAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setScenicSpotClassificationList(List<ScenicSpotHomePageResponse.CateEntity> scenicSpotClassifiyList) {

        this.scenicSpotClassifiyList = scenicSpotClassifiyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_scenic_spot_classification_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            ScenicSpotHomePageResponse.CateEntity cateEntity = scenicSpotClassifiyList.get(itemPosition);
            String mchtName = cateEntity.getTitle();
            String photo = cateEntity.getPhoto();
            holder.mTvScenicSpotName.setText(mchtName);

            GlideUtil.loadCornersTransformImage(mContext, photo, 20, holder.mImgScenicSpotPhoto);

            if (itemPosition == scenicSpotClassifiyList.size() - 1) {

                holder.mFooter.setVisibility(View.VISIBLE);
            } else {
                holder.mFooter.setVisibility(View.GONE);
            }

            holder.mLlytFineFoodClassificationItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, ScenicSpotBangDanListActivity.class);
                    mContext.startActivity(intent);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return scenicSpotClassifiyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //景区名字
        @BindView(R.id.tv_scenic_spot_name)
        TextView mTvScenicSpotName;

        //景点图片
        @BindView(R.id.image_scenic_spots)
        ImageView mImgScenicSpotPhoto;

        @BindView(R.id.llyt_scenic_spots_classification_item)
        LinearLayout mLlytFineFoodClassificationItem;

        @BindView(R.id.view_footer)
        View mFooter;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
