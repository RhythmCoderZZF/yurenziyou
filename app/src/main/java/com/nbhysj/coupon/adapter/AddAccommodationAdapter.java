package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author hysj created at 2019/05/17.
 * description: 添加住宿适配器
 */
public class AddAccommodationAdapter extends RecyclerView.Adapter<AddAccommodationAdapter.ViewHolder> {

    List<NearbyScenicSpotsResponse> nearbyScenicSpotsList;
    private Context mContext;

    public AddAccommodationAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setNearbyScenicSpotsList(List<NearbyScenicSpotsResponse> nearbyScenicSpotsList) {

        this.nearbyScenicSpotsList = nearbyScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_add_accommodation_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            RequestOptions myOptions = new RequestOptions()
                    .transform(new GlideRoundTransform(mContext, 13));

            Glide.with(mContext)
                    .load("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1557554460&di=587cccfcf79487fa86575a004a4785fd&src=http://seopic.699pic.com/photo/50014/4961.jpg_wh1200.jpg")
                    .apply(myOptions)
                    .into(holder.mImgScenicSpots);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //景点照片
        ImageView mImgScenicSpots;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgScenicSpots = itemView.findViewById(R.id.image_scenic_spots);
        }
    }


}
