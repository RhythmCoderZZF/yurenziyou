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
import com.nbhysj.coupon.model.response.CommentAndAnswerBean;
import com.nbhysj.coupon.model.response.ZanAndCollectionBean;
import com.nbhysj.coupon.ui.PostRecommendDetailActivity;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * @author hysj created on 2019/11/13.
 * description: 评论和回答适配器
 */
public class CommentAndAnswerAdapter extends RecyclerView.Adapter<CommentAndAnswerAdapter.ViewHolder> {

    private List<CommentAndAnswerBean> commentAndAnswerList;

    private Context mContext;

    public CommentAndAnswerAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setCommentAndAnswerList(List<CommentAndAnswerBean> commentAndAnswerList) {

        this.commentAndAnswerList = commentAndAnswerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comment_and_answer_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {
            CommentAndAnswerBean commentAndAnswerBean = commentAndAnswerList.get(position);
            int postId = commentAndAnswerBean.getArticleId();
            String dataPhotoUrl = commentAndAnswerBean.getPhoto();
            String avatarUrl = commentAndAnswerBean.getAvater();
            String content = commentAndAnswerBean.getContent();
            String articleIntro = commentAndAnswerBean.getArticleIntro();
            long zanAndCollectionTimeLong = commentAndAnswerBean.getCtime();
            String nickName = commentAndAnswerBean.getNickname();
            String zanAndCollectionTime = DateUtil.transferLongToDate(DateUtil.sDateYMDHHMMSSFormat, zanAndCollectionTimeLong);
            holder.mTvZanAndCollectionTime.setText(zanAndCollectionTime);
            holder.mTvUsername.setText(nickName);
            holder.mTvContent.setText(content);
            GlideUtil.loadImage(mContext, avatarUrl, holder.mImgUserAvatar);
            GlideUtil.loadImage(mContext, dataPhotoUrl, holder.mImgPostPictrueUrl);

            holder.mTvAticleIntro.setText(articleIntro);

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
        return commentAndAnswerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //头像
        @BindView(R.id.image_user_avatar)
        CircleImageView mImgUserAvatar;
        //帖子图片
        @BindView(R.id.img_post_pictrue)
        ImageView mImgPostPictrueUrl;
        //内容
        @BindView(R.id.tv_content)
        TextView mTvContent;
        //用户名
        @BindView(R.id.tv_username)
        TextView mTvUsername;
        //赞与收藏时间
        @BindView(R.id.tv_zan_and_collection_time)
        TextView mTvZanAndCollectionTime;

        @BindView(R.id.llyt_zan_and_collection_item)
        LinearLayout mLlytZanAndCollectionItem;
        //问题
        @BindView(R.id.tv_article_intro)
        TextView mTvAticleIntro;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
