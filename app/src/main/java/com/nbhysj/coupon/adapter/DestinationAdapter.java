package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.DestinationCityResponse;
import com.nbhysj.coupon.model.response.HotTagsTopicBean;
import com.nbhysj.coupon.ui.ScenicSpotDestinationActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/21.
 * description : 目的地搜索目的地适配器
 */
public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.ViewHolder> {


    List<DestinationCityResponse> destinationList;
    private Context mContext;

    public DestinationAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setDestinationList(List<DestinationCityResponse> destinationList) {

        this.destinationList = destinationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_destination_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            DestinationCityResponse destinationTags = destinationList.get(itemPosition);
            String name = destinationTags.getName();
            int cityId = destinationTags.getId();
            holder.mTvDestinationCity.setText(name);

            holder.mTvDestinationCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, ScenicSpotDestinationActivity.class);
                    intent.putExtra("cityId",cityId);
                    mContext.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_destination_city)
        TextView mTvDestinationCity;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
