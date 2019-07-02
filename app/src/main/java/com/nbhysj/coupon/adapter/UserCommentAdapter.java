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
import com.nbhysj.coupon.model.response.DeliciousFoodRecommendResponse;
import com.nbhysj.coupon.model.response.LabelResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.widget.glide.GlideRoundTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/27.
 * description : 用户评论适配器
 */
public class UserCommentAdapter extends RecyclerView.Adapter<UserCommentAdapter.ViewHolder> {

    List<MchDetailsResponse.LabelEntity> labelResponseList;
    private Context mContext;

    public UserCommentAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setLabelList(List<MchDetailsResponse.LabelEntity> labelResponseList) {

        this.labelResponseList = labelResponseList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_flowlayout_tag_user_comment, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            MchDetailsResponse.LabelEntity labelEntity = labelResponseList.get(itemPosition);
            String label = labelEntity.getTitle();
            int count = labelEntity.getCount();
            holder.mTvTagUserComment.setText(label + String.valueOf(count));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return labelResponseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //用户评论标签
        @BindView(R.id.tv_flowlayout)
        TextView mTvTagUserComment;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
