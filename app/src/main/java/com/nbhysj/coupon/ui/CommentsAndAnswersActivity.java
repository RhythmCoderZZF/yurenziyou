package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.CommentReceivedItemAdapter;
import com.nbhysj.coupon.model.response.CommentReceiveResponse;
import com.nbhysj.coupon.util.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/13
 * description：收到的评论
 */
public class CommentsAndAnswersActivity extends BaseActivity {

    //收到的评论
    @BindView(R.id.rv_comment_received)
    RecyclerView mRvCommentReceivedList;

    private List<CommentReceiveResponse> commentReceiveList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_comments_and_answers;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(CommentsAndAnswersActivity.this, getResources().getString(R.string.str_comments_received), R.mipmap.nav_ico_back_black);
        if (commentReceiveList == null) {
            commentReceiveList = new ArrayList<>();

        } else {
            commentReceiveList.clear();
        }

        List<CommentReceiveResponse.CommentEntity> commentSubList = new ArrayList<>();
        CommentReceiveResponse commentReceiveResponse = new CommentReceiveResponse();
        commentReceiveResponse.setAvatar("http://img3.imgtn.bdimg.com/it/u=2584422743,1020351689&fm=214&gp=0.jpg");
        commentReceiveResponse.setCommentOprate("开始关注你了");
        commentReceiveResponse.setCommentTime("2019-08-09");
        commentReceiveResponse.setCommentUser("陈开开");
        commentReceiveResponse.setCommentPictrue("https://img3.duitang.com/uploads/blog/201407/10/20140710152857_Puy5M.jpeg");

        CommentReceiveResponse.CommentEntity commentEntity = commentReceiveResponse.new CommentEntity();
        commentEntity.setComment("很好吃啊");
        commentEntity.setMyComment(false);

        CommentReceiveResponse.CommentEntity commentEntity1 = commentReceiveResponse.new CommentEntity();
        commentEntity1.setComment("我的评论：看着很好吃啊");
        commentEntity1.setMyComment(true);

        CommentReceiveResponse.CommentEntity commentEntity2 = commentReceiveResponse.new CommentEntity();
        commentEntity2.setComment("看着很好吃啊");
        commentEntity2.setMyComment(false);
        commentSubList.add(commentEntity);
        commentSubList.add(commentEntity1);
        commentSubList.add(commentEntity2);

        commentReceiveResponse.setCommentList(commentSubList);

        commentReceiveList.add(commentReceiveResponse);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommentsAndAnswersActivity.this);
        mRvCommentReceivedList.setLayoutManager(linearLayoutManager);
       /* CommentReceivedItemAdapter commentReceivedItemAdapter = new CommentReceivedItemAdapter(CommentsAndAnswersActivity.this);
        commentReceivedItemAdapter.setCommentReceivedList(commentReceiveList);
        mRvCommentReceivedList.setAdapter(commentReceivedItemAdapter);*/
    }

    @Override
    public void initData() {


    }

    @Override
    public void initPresenter() {

    }

}
