package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.MchCitiesBean;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.util.GlideUtil;

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
            String mchtName = cateEntity.getName();
            String photo = cateEntity.getPhoto();
            holder.mTvScenicSpotName.setText(mchtName);

            GlideUtil.loadCornersTransformImage(mContext, photo, 20, holder.mImgScenicSpotPhoto);

            if (itemPosition == mchCitiesDestinationList.size() - 1) {

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
        return mchCitiesDestinationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //景区名字
        @BindView(R.id.tv_scenic_spot_name)
        TextView mTvScenicSpotName;

        //景点图片
        @BindView(R.id.image_scenic_spots)
        ImageView mImgScenicSpotPhoto;

        @BindView(R.id.view_footer)
        View mFooter;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
