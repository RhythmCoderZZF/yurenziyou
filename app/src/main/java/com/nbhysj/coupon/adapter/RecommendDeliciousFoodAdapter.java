package com.nbhysj.coupon.adapter;

import android.content.Context;
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
import com.nbhysj.coupon.model.response.NearbyScenicSpotsResponse;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hysj created at 2019/05/10.
 * description:推荐菜适配器
 */
public class RecommendDeliciousFoodAdapter extends RecyclerView.Adapter<RecommendDeliciousFoodAdapter.ViewHolder> {

    List<NearbyScenicSpotsResponse> nearbyScenicSpotsList;
    private Context mContext;

    public RecommendDeliciousFoodAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setNearbyScenicSpotsList(List<NearbyScenicSpotsResponse> nearbyScenicSpotsList) {

        this.nearbyScenicSpotsList = nearbyScenicSpotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recommend_food_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            if (itemPosition < 3) {

                holder.mTvFineFoodSerialNumber.setText("TOP." + itemPosition);
                holder.mTvFineFoodSerialNumber.setVisibility(View.VISIBLE);
            } else {
                holder.mTvFineFoodSerialNumber.setVisibility(View.GONE);
            }

            RequestOptions myOptions = new RequestOptions()
                    .transform(new GlideRoundTransform(mContext, 5));

            Glide.with(mContext)
                    .load("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1557554460&di=587cccfcf79487fa86575a004a4785fd&src=http://seopic.699pic.com/photo/50014/4961.jpg_wh1200.jpg")
                    .apply(myOptions)
                    .into(holder.mImgRecommendFood);

          /*  holder.mLlytHotelRoomItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hotelRoomItemListener.setHotelRoomItemListener(itemPosition);

                }
            });*/

            List<String> fineFoodTagList = new ArrayList<>();
            fineFoodTagList.add("人气推荐");
            holder.mTagFlowLayoutFineFoodLabel.setAdapter(new TagAdapter<String>(fineFoodTagList) {

                @Override
                public View getView(FlowLayout parent, int position, String fineFoodTag) {
                    LayoutInflater mInflater = LayoutInflater.from(mContext);
                    TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_orange_frame,
                            holder.mTagFlowLayoutFineFoodLabel, false);
                    tagName.setText(fineFoodTag);
                    return tagName;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //酒店房间类型
        TextView mTvHotelRoomType;
        //酒店房间图片
        ImageView mImgRecommendFood;
        //美食标签
        TagFlowLayout mTagFlowLayoutFineFoodLabel;
        //美食序列
        TextView mTvFineFoodSerialNumber;
        //酒店可取消时间
        TextView mTvHotelBookCancelTimeLimit;
        //人均价格
        TextView mTvHotelRoomPrice;
        LinearLayout mLlytHotelRoomItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mImgRecommendFood = itemView.findViewById(R.id.image_recommend_food);
            mTagFlowLayoutFineFoodLabel = itemView.findViewById(R.id.flowlayout_fine_food_label);
            mTvFineFoodSerialNumber = itemView.findViewById(R.id.tv_fine_food_serial_number);
        }
    }

}
