package com.nbhysj.coupon.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MchCommentAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.CommentContract;
import com.nbhysj.coupon.model.CommentModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.LabelEntity;
import com.nbhysj.coupon.model.response.MchCommentEntity;
import com.nbhysj.coupon.model.response.MchCommentResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.PostsCommentResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.presenter.CommentPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.view.MyRecycleView;
import com.nbhysj.coupon.view.StarBarView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/10/14
 * description：商户评论
 */
public class MchCommentActivity extends BaseActivity<CommentPresenter, CommentModel> implements CommentContract.View {

    @BindView(R.id.flowlayout_comment)
    TagFlowLayout mTagFlowComment;
    @BindView(R.id.rv_user_comment)
    MyRecycleView mRvUserComment;
    //商户评分
    @BindView(R.id.tv_mch_score)
    TextView mTvMchScore;

    //星级
    @BindView(R.id.starbar)
    StarBarView mStarBarView;

    //景色评分
    @BindView(R.id.tv_scenic_spot_score)
    TextView mTvScenicSpotScore;

    @BindView(R.id.pb_scenic_spot_progressbar)
    ProgressBar mProgressScenicSpot;

    //趣味评分
    @BindView(R.id.tv_interest_score)
    TextView mTvInterestScore;

    @BindView(R.id.pb_interest_progressbar)
    ProgressBar mProgressInterest;

    //性价比评分
    @BindView(R.id.tv_cost_performancet_score)
    TextView mTvCostPerformancetScore;

    @BindView(R.id.pb_cost_performancet_progressbar)
    ProgressBar mProgressCostPerformancet;

    //评论数
    @BindView(R.id.tv_comment_num)
    TextView mTvCommentNum;

    private List<MchCommentEntity> mchCommentList;

    MchCommentAdapter mchCommentAdapter;

    //商户Id
    private int mchId;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_mch_comment;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(MchCommentActivity.this, "评论", R.mipmap.icon_left_arrow_black, "");
        mchId = getIntent().getIntExtra("mchId",0);

        if (mchCommentList == null){
            mchCommentList = new ArrayList<>();
        } else {
            mchCommentList.clear();
        }
    }

    @Override
    public void initData() {
/*

        mTagFlowComment.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String content = "";
                Set<Integer> selectPosSet = mTagFlowComment.getSelectedList();
                Iterator it = selectPosSet.iterator();
                while (it.hasNext()) {
                    int index = (int) it.next();
                    //content = options[index];
                    // MoveToPosition(layoutManager,index);


                }
                return true;
            }
        });
        tagAdapter.setSelectedList(0);
*/

        getMchCommentList();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MchCommentActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvUserComment.setLayoutManager(linearLayoutManager);
        mchCommentAdapter = new MchCommentAdapter(MchCommentActivity.this);
        mchCommentAdapter.setScenicSpotsUserCommentList(mchCommentList);
        mRvUserComment.setAdapter(mchCommentAdapter);
    }

    @Override
    public void postsCommentResult(BackResult res) {

    }

    @Override
    public void getMchCommentListResult(BackResult<MchCommentResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    MchCommentResponse mchCollectionResponse = res.getData();
                    MchCommentResponse.ScoreEntity scoreEntity = mchCollectionResponse.getScore();
                    int commentNum = scoreEntity.getCommentNum();
                    float commentScore = scoreEntity.getCommentScore();
                    float commentScore1 = scoreEntity.getCommentScore1();
                    float commentScore2 = scoreEntity.getCommentScore2();
                    float commentScore3 = scoreEntity.getCommentScore3();

                    mTvMchScore.setText(String.valueOf(commentScore));
                    mStarBarView.setIntegerMark(false);
                            mStarBarView.setStarMark(commentScore);

                    mTvScenicSpotScore.setText(commentScore1 + "分");
                    mProgressScenicSpot.setProgress((int)commentScore1);

                    mTvInterestScore.setText(commentScore2 + "分");
                    mProgressInterest.setProgress((int)commentScore2);

                    mTvCostPerformancetScore.setText(commentScore3 + "分");
                    mProgressCostPerformancet.setProgress((int)commentScore3);

                    mTvCommentNum.setText("共" + commentNum + "条评论");

                    List<LabelEntity> labelList = mchCollectionResponse.getLabel();

                    if(labelList != null)
                    {
                        TagAdapter tagAdapter = new TagAdapter<LabelEntity>(labelList) {
                            @Override
                            public View getView(FlowLayout parent, int position, LabelEntity labelEntity) {
                                View view = LayoutInflater.from(mContext).inflate(R.layout.layout_flowlayout_tag_comment,
                                        mTagFlowComment, false);
                                TextView tv = view.findViewById(R.id.tv_flowlayout);
                                String title = labelEntity.getTitle();
                                tv.setText(title);
                                return view;
                            }
                        };
                        mTagFlowComment.setMaxSelectCount(1);
                        mTagFlowComment.setAdapter(tagAdapter);
                    }

                    //评论列表
                    MchCommentResponse.CommentEntity commentEntity = mchCollectionResponse.getComment();
                    mchCommentList = commentEntity.getResult();

                     if(mchCommentList != null)
                     {
                         mchCommentAdapter.setScenicSpotsUserCommentList(mchCommentList);
                         mchCommentAdapter.notifyDataSetChanged();
                     }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(MchCommentActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getAllPostsCommentListByArticleIdResult(BackResult<PostsCommentResponse> res) {

    }

    @Override
    public void postOprateResult(BackResult<PraiseOrCollectResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        Constants.getResultMsg(Constants.getResultMsg(msg));
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    public void getMchCommentList(){

        if(validateInternet()){

            showProgressDialog(MchCommentActivity.this);
            mPresenter.getMchCommentList(mchId);
        }
    }
}
