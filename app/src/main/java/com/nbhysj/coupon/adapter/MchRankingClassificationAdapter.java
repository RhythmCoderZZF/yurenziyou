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
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.model.response.HotelReputationResponse;
import com.nbhysj.coupon.model.response.MchCitiesBean;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.ui.FineFoodBangDanListActivity;
import com.nbhysj.coupon.ui.HomestayBangDanListActivity;
import com.nbhysj.coupon.ui.HotelBangDanListActivity;
import com.nbhysj.coupon.ui.RecreationBangDanListActivity;
import com.nbhysj.coupon.ui.ScenicSpotBangDanListActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.ui.ScenicSpotListActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/19.
 * description : 商户类型榜单分类适配器
 */
public class MchRankingClassificationAdapter extends RecyclerView.Adapter<MchRankingClassificationAdapter.ViewHolder> {

    List<ScenicSpotHomePageResponse.CateEntity> scenicSpotClassifiyList;
    private Context mContext;
    private String mchType;

    public MchRankingClassificationAdapter(Context mContext,String mchType) {

        this.mContext = mContext;
        this.mchType = mchType;
    }

    public void setMchRankingClassificationList(List<ScenicSpotHomePageResponse.CateEntity> scenicSpotClassifiyList) {

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

            GlideUtil.loadImage(mContext, photo, holder.mImgScenicSpotPhoto);

            if (itemPosition == scenicSpotClassifiyList.size() - 1) {

                holder.mFooter.setVisibility(View.VISIBLE);
            } else {
                holder.mFooter.setVisibility(View.GONE);
            }

            holder.mLlytScenicSpotsClassificationItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    if(mchType.equals(MchTypeEnum.MCH_SCENIC.getValue()))
                    {
                        intent.setClass(mContext, ScenicSpotBangDanListActivity.class);

                    }  else if(mchType.equals(MchTypeEnum.MCH_FOOD.getValue())){

                        intent.setClass(mContext, FineFoodBangDanListActivity.class);
                    }else if(mchType.equals(MchTypeEnum.MCH_HOTEL.getValue())){

                        intent.setClass(mContext, HotelBangDanListActivity.class);
                    } else if(mchType.equals(MchTypeEnum.MCH_HOMESTAY.getValue())){

                        intent.setClass(mContext, HomestayBangDanListActivity.class);
                    }else if(mchType.equals(MchTypeEnum.MCH_RECREATION.getValue())){

                        intent.setClass(mContext, RecreationBangDanListActivity.class);
                    }
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
        RoundedImageView mImgScenicSpotPhoto;

        @BindView(R.id.llyt_scenic_spots_classification_item)
        LinearLayout mLlytScenicSpotsClassificationItem;

        @BindView(R.id.view_footer)
        View mFooter;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
