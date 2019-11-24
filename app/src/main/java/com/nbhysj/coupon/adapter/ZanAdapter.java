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
import com.nbhysj.coupon.model.response.ZanAndCollectionBean;
import com.nbhysj.coupon.ui.PostRecommendDetailActivity;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * @author hysj created on 2019/10/13.
 * description: 获赞适配器
 */
public class ZanAdapter extends RecyclerView.Adapter<ZanAdapter.ViewHolder> {

    private List<ZanAndCollectionBean> zanAndCollectionList;

    private Context mContext;

    public ZanAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setZanList(List<ZanAndCollectionBean> zanAndCollectionList) {

        this.zanAndCollectionList = zanAndCollectionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_zan_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            ZanAndCollectionBean zanAndCollectionBean = zanAndCollectionList.get(position);
            int postId = zanAndCollectionBean.getDataId();
            String dataPhotoUrl = zanAndCollectionBean.getDataPhoto();
            String avatarUrl = zanAndCollectionBean.getAvater();
            String message = zanAndCollectionBean.getMessage();
            long zanAndCollectionTimeLong = zanAndCollectionBean.getCtime() * 1000;
            String dataType = zanAndCollectionBean.getDataType();
            Date date = new Date(zanAndCollectionTimeLong);
            String zanAndCollectionTime = DateUtil.dateFormat(date);
            holder.mTvZanAndCollectionTime.setText(zanAndCollectionTime);
            holder.mTvTitle.setText(message);
            GlideUtil.loadImage(mContext, avatarUrl, holder.mImgUserAvatar);
            GlideUtil.loadImage(mContext, dataPhotoUrl, holder.mImgPostPictrueUrl);

            holder.mLlytZanAndCollectionItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.putExtra("postId", postId);
                    intent.setClass(mContext, PostRecommendDetailActivity.class);
                    mContext.startActivity(intent);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return zanAndCollectionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //头像
        @BindView(R.id.image_user_avatar)
        CircleImageView mImgUserAvatar;
        //帖子图片
        @BindView(R.id.img_post_pictrue)
        ImageView mImgPostPictrueUrl;
        //标题
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        //赞与收藏时间
        @BindView(R.id.tv_zan_and_collection_time)
        TextView mTvZanAndCollectionTime;

        @BindView(R.id.llyt_zan_and_collection_item)
        LinearLayout mLlytZanAndCollectionItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
