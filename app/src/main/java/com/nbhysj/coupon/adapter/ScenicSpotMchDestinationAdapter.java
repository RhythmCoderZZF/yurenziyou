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
import com.nbhysj.coupon.model.response.MchCitiesBean;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.ui.ScenicSpotDestinationActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/19.
 * description : 景点类型分类适配器
 */
public class ScenicSpotMchDestinationAdapter extends RecyclerView.Adapter<ScenicSpotMchDestinationAdapter.ViewHolder> {

    List<MchCitiesBean> mchCitiesDestinationList;
    private Context mContext;

    public ScenicSpotMchDestinationAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setScenicSpotMchCitiesDestinationList(List<MchCitiesBean> mchCitiesDestinationList) {

        this.mchCitiesDestinationList = mchCitiesDestinationList;
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
            MchCitiesBean cateEntity = mchCitiesDestinationList.get(itemPosition);
            String cityId = cateEntity.getCityId();
            String mchtName = cateEntity.getName();
            String photo = cateEntity.getPhoto();
            holder.mTvScenicSpotName.setText(mchtName);

            GlideUtil.loadImage(mContext, photo, holder.mImgScenicSpotPhoto);

            if (itemPosition == mchCitiesDestinationList.size() - 1) {

                holder.mFooter.setVisibility(View.VISIBLE);
            } else {
                holder.mFooter.setVisibility(View.GONE);
            }

            holder.mLlytMchCitiesItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.putExtra("cityId",cityId);
                    intent.setClass(mContext, ScenicSpotDestinationActivity.class);
                    mContext.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mchCitiesDestinationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //景区名字
        @BindView(R.id.tv_scenic_spot_name)
        TextView mTvScenicSpotName;

        //景点图片
        @BindView(R.id.image_scenic_spots)
        RoundedImageView mImgScenicSpotPhoto;

        @BindView(R.id.view_footer)
        View mFooter;
        @BindView(R.id.llyt_scenic_spots_classification_item)
        LinearLayout mLlytMchCitiesItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
