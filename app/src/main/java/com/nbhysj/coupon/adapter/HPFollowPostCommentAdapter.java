package com.nbhysj.coupon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.LabelResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hysj created at 2019/4/27.
 * description : 首页关注用户帖子评论
 */
public class HPFollowPostCommentAdapter extends RecyclerView.Adapter<HPFollowPostCommentAdapter.ViewHolder> {

    List<HomePageSubTopicTagBean.PostsCommentsEntity> postsCommentsList;
    private Context mContext;

    public HPFollowPostCommentAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setLabelList(List<HomePageSubTopicTagBean.PostsCommentsEntity> labelResponseList) {

        this.postsCommentsList = labelResponseList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_follow_post_comment_item, parent, false);//解决宽度不能铺满
        ViewHolder hold = new ViewHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int itemPosition) {

        try {
            //DeliciousFoodRecommendResponse deliciousFoodRecommend = deliciousFoodRecommendList.get(itemPosition);
            HomePageSubTopicTagBean.PostsCommentsEntity postsCommentsEntity = postsCommentsList.get(itemPosition);
            String content = postsCommentsEntity.getContent();
            String nickname = postsCommentsEntity.getNickname();
            holder.mTvCommentContent.setText(content);
            holder.mTvCommentUserNickName.setText(nickname);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return postsCommentsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //评论用户
        @BindView(R.id.tv_comment_user_nickname)
        TextView mTvCommentUserNickName;
        //评论内容
        @BindView(R.id.tv_comment_content)
        TextView mTvCommentContent;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
