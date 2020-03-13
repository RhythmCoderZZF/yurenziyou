package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.CommentSubBean;
import com.nbhysj.coupon.model.response.CommentUserEntity;
import com.nbhysj.coupon.model.response.PostsCommentResponse;
import com.nbhysj.coupon.ui.UserPersonalHomePageActivity;
import com.nbhysj.coupon.util.DateUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * @author hysj created on 2019/10/14.
 * description: 帖子评论适配器
 */
public class PostCommentItemAdapter extends RecyclerView.Adapter<PostCommentItemAdapter.ViewHolder> {

    private List<PostsCommentResponse.PostsCommentEntity> postsCommentList;

    private Context mContext;

    private PostCommentPraiseListener postCommentPraiseListener;

    public PostCommentItemAdapter(Context mContext, PostCommentPraiseListener postCommentPraiseListener) {

        this.mContext = mContext;

        this.postCommentPraiseListener = postCommentPraiseListener;
    }

    public void setPostCommentList(List<PostsCommentResponse.PostsCommentEntity> postsCommentList) {

        this.postsCommentList = postsCommentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comments_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        try {

            PostsCommentResponse.PostsCommentEntity postsCommentEntity = postsCommentList.get(position);
            CommentUserEntity commentUserEntity = postsCommentEntity.getUser();
            int userId = commentUserEntity.getId();
            int zanCount = postsCommentEntity.getZanCount();
            int zanStatus = postsCommentEntity.getZanStatus();
            String avatarUrl = commentUserEntity.getAvater();
            String nickName = commentUserEntity.getNickname();
            String content = postsCommentEntity.getContent();
            GlideUtil.loadImage(mContext,avatarUrl,holder.mImgUserAvatar);
            if(!TextUtils.isEmpty(nickName))
            {
                holder.mTvAuthorUsername.setText(nickName);
            }

            //点赞状态
            if(zanStatus == 0){

              holder.mImgUserZanStatus.setImageResource(R.mipmap.icon_post_love_gray_heart);

            } else {

                holder.mImgUserZanStatus.setImageResource(R.mipmap.icon_post_love_red_heart);
            }

            long commentTime = postsCommentEntity.getCtime();
            String postCommentDate = DateUtil.transferLongToDate(DateUtil.sDateYMDFormat,commentTime);

            //点赞数量
            holder.mTvPraiseNum.setText(String.valueOf(zanCount));

            holder.mTvCommentTime.setText(postCommentDate);

            if(!TextUtils.isEmpty(content))
            {
                holder.mTvCommentContent.setText(content);
            }

            List<CommentSubBean> commentEntityList = postsCommentEntity.getComment();
            if(commentEntityList != null && commentEntityList.size() > 0)
            {
                holder.mRvCommentContent.setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                holder.mRvCommentContent.setLayoutManager(linearLayoutManager);
                CommentReceivedSubItemAdapter commentReceivedSubItemAdapter = new CommentReceivedSubItemAdapter(mContext);
                commentReceivedSubItemAdapter.setCommentReceivedSubItemList(commentEntityList);
                holder.mRvCommentContent.setAdapter(commentReceivedSubItemAdapter);
            } else {

                holder.mRvCommentContent.setVisibility(View.GONE);
            }
            holder.mLlytPraiseNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    postCommentPraiseListener.setPostCommentPraiseListener(position,postsCommentEntity);
                }
            });

            holder.mLlytPostComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    postCommentPraiseListener.setPostCommentListener(position,postsCommentEntity);
                }
            });


            holder.mImgUserAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, UserPersonalHomePageActivity.class);
                    intent.putExtra("publisherAvatarUrl", avatarUrl);
                    intent.putExtra("authorId", userId);
                    mContext.startActivity(intent);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return postsCommentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //评论内容
        @BindView(R.id.rv_comment_content)
        RecyclerView mRvCommentContent;
        //头像
        @BindView(R.id.image_user_avatar)
        CircleImageView mImgUserAvatar;
        //用户名
        @BindView(R.id.tv_author_username)
        TextView mTvAuthorUsername;
        //点赞状态
        @BindView(R.id.img_user_zan_status)
        ImageView mImgUserZanStatus;
        //点赞数
        @BindView(R.id.tv_praise_num)
        TextView mTvPraiseNum;

        //帖子评论时间
        @BindView(R.id.tv_comment_time)
        TextView mTvCommentTime;

        //点赞
        @BindView(R.id.llyt_praise_num)
        LinearLayout mLlytPraiseNum;

        //评论内容
        @BindView(R.id.tv_comment_content)
        TextView mTvCommentContent;

        @BindView(R.id.llyt_post_comment)
        LinearLayout mLlytPostComment;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface PostCommentPraiseListener{

        void setPostCommentPraiseListener(int position,PostsCommentResponse.PostsCommentEntity postsCommentEntity);

        void setPostCommentListener(int position,PostsCommentResponse.PostsCommentEntity postsCommentEntity);
    }
}
