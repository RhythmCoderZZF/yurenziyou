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
import com.nbhysj.coupon.model.response.DeliciousFoodRecommendResponse;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/27.
 * description : 游玩指南适配器
 */
public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder> {

    /**
     * 酒店问答内容
     */
    List<DeliciousFoodRecommendResponse> deliciousFoodRecommendList;
    private Context mContext;

    public PaymentMethodAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setDeliciousFoodRecommendList(List<DeliciousFoodRecommendResponse> deliciousFoodRecommendList) {

        this.deliciousFoodRecommendList = deliciousFoodRecommendList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_play_guide_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            //DeliciousFoodRecommendResponse deliciousFoodRecommend = deliciousFoodRecommendList.get(itemPosition);
            holder.mTvScenicSpotsType.setText("项目");
            holder.mTvScenicSpotName.setText("270°海底隧道");

            RequestOptions myOptions = new RequestOptions()
                    .transform(new GlideRoundTransform(mContext, 5));

            Glide.with(mContext)
                    .load("http://k.zol-img.com.cn/sjbbs/7692/a7691515_s.jpg")
                    .apply(myOptions)
                    .into(holder.mImgScenicSpots);

            if (itemPosition == 4 - 1) {

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
        return 4;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //景点类型
        @BindView(R.id.tv_scenic_spots_type)
        TextView mTvScenicSpotsType;
        //景点名字
        @BindView(R.id.tv_scenic_spot_name)
        TextView mTvScenicSpotName;
        //景点照片
        @BindView(R.id.image_scenic_spots)
        ImageView mImgScenicSpots;
        @BindView(R.id.view_footer)
        View mFooter;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
