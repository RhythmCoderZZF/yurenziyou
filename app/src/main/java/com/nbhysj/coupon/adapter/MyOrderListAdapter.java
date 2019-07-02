package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.ImageData;
import com.nbhysj.coupon.model.response.RecommendFriendsBean;
import com.nbhysj.coupon.ui.MyOrderDetailActivity;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.GlideRoundCornersTransUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/03/29.
 * description: 订单适配器
 */
public class MyOrderListAdapter extends RecyclerView.Adapter<MyOrderListAdapter.ViewHolder> {

    /**
     * 图片列表数据
     */
    private List<RecommendFriendsBean> imageDataList;

    private Context mContext;

    public MyOrderListAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setMyOrderList(List<RecommendFriendsBean> imageDataList) {

        this.imageDataList = imageDataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = View.inflate(parent.getContext(), R.layout.list_survey_type_answer_item, null);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_my_order_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {

            holder.mImgPhoto.load("https://img5.duitang.com/uploads/item/201410/05/20141005190442_nuceP.thumb.700_0.jpeg", R.mipmap.icon_placeholder_image, 30);
            holder.mLlytMyOrderItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, MyOrderDetailActivity.class);
                    mContext.startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return imageDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.llyt_my_order_item)
        LinearLayout mLlytMyOrderItem;
        @BindView(R.id.img_photograph)
        GlideImageView mImgPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
