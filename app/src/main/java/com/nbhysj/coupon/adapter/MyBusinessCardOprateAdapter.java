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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author hysj created at 2019/05/22.
 * description: 我的名片适配器
 */
public class MyBusinessCardOprateAdapter extends RecyclerView.Adapter<MyBusinessCardOprateAdapter.ViewHolder> {

    List<String> businessCardOprateList;
    private Context mContext;

    public MyBusinessCardOprateAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setMyBusinessCardOprateList(List<String> businessCardOprateList) {

        this.businessCardOprateList = businessCardOprateList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_business_card_oprate_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {

            String businessCardOprate = businessCardOprateList.get(itemPosition);
            holder.mImgMyBusinessCardOprate.setImageResource(R.mipmap.icon_wechat_payment_method);
            holder.mTvMyMyBusinessCardOprateName.setText(businessCardOprate);
            if (itemPosition == 0) {

                holder.mViewMyBusinessCardOprate.setVisibility(View.VISIBLE);
            } else {
                holder.mViewMyBusinessCardOprate.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return businessCardOprateList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView mImgMyBusinessCardOprate;
        View mViewMyBusinessCardOprate;
        TextView mTvMyMyBusinessCardOprateName;

        public ViewHolder(View itemView) {
            super(itemView);

            mViewMyBusinessCardOprate = itemView.findViewById(R.id.view_my_business_card_oprate);
            mImgMyBusinessCardOprate = itemView.findViewById(R.id.img_my_business_card_oprate_item);
            mTvMyMyBusinessCardOprateName = itemView.findViewById(R.id.tv_my_business_card_oprate_name);

        }
    }


}
