package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.model.response.BroadcastBean;
import com.nbhysj.coupon.model.response.ImageData;
import com.nbhysj.coupon.ui.HotelDetailsActivity;
import com.nbhysj.coupon.ui.WebActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.GlideImageView;
import com.nbhysj.coupon.view.RoundedImageView;
import com.nbhysj.coupon.widget.glide.GlideApp;
import com.nbhysj.coupon.widget.glide.GlideRoundCornersTransUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author hysj created on 2019/04/14.
 * description: 广播适配器
 */
public class BroadcastItemAdapter extends RecyclerView.Adapter<BroadcastItemAdapter.ViewHolder> {

    /**
     * 广播消息列表数据
     */
    private List<BroadcastBean> broadcastMessageList;

    private Context mContext;


    public BroadcastItemAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setBroadcastList(List<BroadcastBean> broadcastMessageList) {

        this.broadcastMessageList = broadcastMessageList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = View.inflate(parent.getContext(), R.layout.list_survey_type_answer_item, null);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_broadcast_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            BroadcastBean broadcastBean = broadcastMessageList.get(position);
            String photoUrl = broadcastBean.getPhoto();
            String title = broadcastBean.getTitle();
            String content = broadcastBean.getContent();
            String radioH5 = broadcastBean.getRadioH5();

            GlideUtil.loadImage(mContext,photoUrl,holder.mImgBroadcast);

            holder.mTvTitle.setText(title);
            holder.mTvDescription.setText(Html.fromHtml(content));

            holder.mLlytBroadCastItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!TextUtils.isEmpty(radioH5)) {

                        Intent intent = new Intent();
                        intent.putExtra("url", radioH5);
                        intent.putExtra("title", title);
                        intent.setClass(mContext, WebActivity.class);
                        mContext.startActivity(intent);

                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return broadcastMessageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //广播图片
        @BindView(R.id.image_broadcast)
        RoundedImageView mImgBroadcast;

        //标题
        @BindView(R.id.tv_title)
        TextView mTvTitle;

        //描述
        @BindView(R.id.tv_description)
        TextView mTvDescription;

        @BindView(R.id.llyt_broadcast_item)
        LinearLayout mLlytBroadCastItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
