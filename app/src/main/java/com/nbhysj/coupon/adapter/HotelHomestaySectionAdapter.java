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
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/7.
 * description : 美食页面栏目适配器
 */
public class HotelHomestaySectionAdapter extends RecyclerView.Adapter<HotelHomestaySectionAdapter.ViewHolder> {

    List<MchTypeBean> hotelHomestayList;
    private Context mContext;

    public HotelHomestaySectionAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setHotelHomestaySectionList(List<MchTypeBean> hotelHomestayList) {

        this.hotelHomestayList = hotelHomestayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_homestay_section_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            MchTypeBean scenicSpotBean = hotelHomestayList.get(itemPosition);
            int mchId = scenicSpotBean.getId();
            String photo = scenicSpotBean.getPhoto();
            //holder.mTvFoodCuisine.setText(scenicSpotBean.getIntro());
            holder.mTvHotelHomestayName.setText(scenicSpotBean.getMchName());

            GlideUtil.loadCornersTransformImage(mContext, photo, 5, holder.mImgHotelhomestay);

            if (itemPosition == 0) {

                holder.mHeader.setVisibility(View.VISIBLE);
            } else {

                holder.mHeader.setVisibility(View.GONE);
            }

            if (itemPosition == hotelHomestayList.size() - 1) {

                holder.mFooter.setVisibility(View.VISIBLE);
            } else {
                holder.mFooter.setVisibility(View.GONE);
            }

            holder.mLlytHotelHomestayItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, HomestayDetailActivity.class);
                    intent.putExtra("mchId",mchId);
                    mContext.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return hotelHomestayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //美食位置
        @BindView(R.id.tv_hotel_homestay_name)
        TextView mTvHotelHomestayName;
        //美食照片
        @BindView(R.id.image_hotel_homestay)
        ImageView mImgHotelhomestay;
        @BindView(R.id.view_header)
        View mHeader;
        @BindView(R.id.view_footer)
        View mFooter;
        @BindView(R.id.llyt_hotel_homestay_item)
        LinearLayout mLlytHotelHomestayItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
