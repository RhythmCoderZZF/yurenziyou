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
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.model.response.SelectDestionationsBean;
import com.nbhysj.coupon.model.response.TravelAssistantDetailCountryBean;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/15.
 * description: 行程助手感兴趣目的地列表适配器
 */
public class TravelAssistantDestionationsListAdapter extends RecyclerView.Adapter<TravelAssistantDestionationsListAdapter.ViewHolder> {

    List<TravelAssistantDetailCountryBean> nearbyScenicSpotsList;
    private Context mContext;
    private DestionationSelectListener destionationSelectListener;

    public TravelAssistantDestionationsListAdapter(Context mContext, DestionationSelectListener destionationSelectListener) {

        this.mContext = mContext;
        this.destionationSelectListener = destionationSelectListener;
    }

    public void setSelectDestionationList(List<TravelAssistantDetailCountryBean> nearbyScenicSpotsList) {

        this.nearbyScenicSpotsList = nearbyScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_travel_assistant_interest_destinations_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {



            TravelAssistantDetailCountryBean travelAssistantDetailCountryBean = nearbyScenicSpotsList.get(itemPosition);
            String destinationName = travelAssistantDetailCountryBean.getCountyName();
            holder.mTvDestinationName.setText(destinationName);
            String bannerUrl = travelAssistantDetailCountryBean.getBanner();
            GlideUtil.loadImage(mContext, bannerUrl, holder.mImgScenicSpots);

            holder.mTvAddMyTravel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    travelAssistantDetailCountryBean.setSelect(true);
                    notifyDataSetChanged();
                    destionationSelectListener.setDestionationSelectListener(travelAssistantDetailCountryBean);

                }
            });

            if (travelAssistantDetailCountryBean.isSelect()) {

                holder.mTvAddMyTravel.setBackgroundResource(R.mipmap.icon_selected_destionation_tag);
                holder.mTvAddMyTravel.setText("");
                holder.mTvAddMyTravel.setEnabled(false);
            } else {

                holder.mTvAddMyTravel.setBackgroundResource(R.drawable.bg_stroke_radius_gray_shape);
                holder.mTvAddMyTravel.setText("添加");
                holder.mTvAddMyTravel.setEnabled(true);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return nearbyScenicSpotsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //景点照片
        RoundedImageView mImgScenicSpots;
        //添加
        TextView mTvAddMyTravel;
        //目的地名字
        TextView mTvDestinationName;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
            mTvAddMyTravel = itemView.findViewById(R.id.tv_add_my_travel);
            mTvDestinationName = itemView.findViewById(R.id.tv_destination_name);

        }
    }

    public interface DestionationSelectListener {

        void setDestionationSelectListener(TravelAssistantDetailCountryBean selectDestionation);
    }
}
