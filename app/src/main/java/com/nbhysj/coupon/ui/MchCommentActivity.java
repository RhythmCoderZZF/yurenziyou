package com.nbhysj.coupon.ui;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MchCommentAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.contract.CommentContract;
import com.nbhysj.coupon.model.CommentModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.LabelEntity;
import com.nbhysj.coupon.model.response.MchCommentEntity;
import com.nbhysj.coupon.model.response.MchCommentResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.PostsCommentResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.model.response.UserOrderListResponse;
import com.nbhysj.coupon.presenter.CommentPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.nbhysj.coupon.view.MyRecycleView;
import com.nbhysj.coupon.view.StarBarView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/10/14
 * description：商户评论
 */
public class MchCommentActivity extends BaseActivity<CommentPresenter, CommentModel> implements CommentContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    TagFlowLayout mTagFlowComment;
    @BindView(R.id.rv_user_comment)
    RecyclerView mRvUserComment;
    //商户评分
    TextView mTvMchScore;

    //星级
    StarBarView mStarBarView;

    //景色评分
    TextView mTvScenicSpotScore;

    //评分标签
    TextView mTvScenicSpotScoreTag;

    ProgressBar mProgressScenicSpot;

    //趣味评分
    TextView mTvInterestScore;

    TextView mTvInterestScoreTag;

    ProgressBar mProgressInterest;

    //性价比评分
    TextView mTvCostPerformancetScore;

    ProgressBar mProgressCostPerformancet;

    //评论数
    TextView mTvCommentNum;

    private List<MchCommentEntity> mchCommentList;

    MchCommentAdapter mchCommentAdapter;

    //商户Id
    private int mchId;

    //商户类型
    private String mchType;

    private View header;

    private int mPage = 1;

    private int mPageSize = 10;

    private boolean isOnLoadMore = false;

    int mTotalPageCount;

    private int tagIndex;

    //标签值
    private int tagValue;

    //标签点击
    private boolean onTagClick = true;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_mch_comment;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setHeadBar(MchCommentActivity.this, "评论", R.mipmap.icon_left_arrow_black, "");
        mchId = getIntent().getIntExtra("mchId", 0);
        mchType = getIntent().getStringExtra("mchType");
        tagIndex = getIntent().getIntExtra("tagIndex", 0);
        tagValue = getIntent().getIntExtra("tagValue", 0);

        if (mchCommentList == null) {
            mchCommentList = new ArrayList<>();
        } else {
            mchCommentList.clear();
        }
    }

    private void setHeader(RecyclerView view) {
        header = LayoutInflater.from(this).inflate(R.layout.layout_mch_comment_header_item, view, false);
        mTvMchScore = header.findViewById(R.id.tv_mch_score);
        mStarBarView = header.findViewById(R.id.starbar);

        mTvScenicSpotScoreTag = header.findViewById(R.id.tv_scenic_spot_score_tag);
        mTvScenicSpotScore = header.findViewById(R.id.tv_scenic_spot_score);
        mProgressScenicSpot = header.findViewById(R.id.pb_scenic_spot_progressbar);


        mTvInterestScoreTag = header.findViewById(R.id.tv_interest_score_tag);
        mTvInterestScore = header.findViewById(R.id.tv_interest_score);
        mProgressInterest = header.findViewById(R.id.pb_interest_progressbar);
        mTvCostPerformancetScore = header.findViewById(R.id.tv_cost_performancet_score);
        mProgressCostPerformancet = header.findViewById(R.id.pb_cost_performancet_progressbar);
        mTvCommentNum = header.findViewById(R.id.tv_comment_num);
        mTagFlowComment = header.findViewById(R.id.flowlayout_comment);
    }

    @Override
    public void initData() {

        showProgressDialog(MchCommentActivity.this);
        getMchCommentList();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MchCommentActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvUserComment.setLayoutManager(linearLayoutManager);
        mchCommentAdapter = new MchCommentAdapter(MchCommentActivity.this);
        mchCommentAdapter.setScenicSpotsUserCommentList(mchCommentList);
        mRvUserComment.setAdapter(mchCommentAdapter);
        setHeader(mRvUserComment);
        String mchTypeValue = MchTypeEnum.MCH_SCENIC.getValue();
        if (!TextUtils.isEmpty(mchType)){
            if (mchType.equals(mchTypeValue))
            {
                mTvScenicSpotScoreTag.setText("景色");
                mTvInterestScoreTag.setText("舒适");
            }
       }
        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPage = 1;
                        isOnLoadMore = false;
                        mchCommentList.clear();
                        mchCommentAdapter.notifyDataSetChanged();
                        // showProgressDialog(getActivity());
                        getMchCommentList();

                    }
                }, 100);
            }
        });

        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isOnLoadMore = true;
                        try {
                            if (mTotalPageCount == mchCommentList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getMchCommentList();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });
    }

    @Override
    public void postsCommentResult(BackResult res) {

    }

    @Override
    public void getMchCommentListResult(BackResult<MchCommentResponse> res) {
        dismissProgressDialog();
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    MchCommentResponse mchCollectionResponse = res.getData();
                    MchCommentResponse.ScoreEntity scoreEntity = mchCollectionResponse.getScore();
                    if (!isOnLoadMore && onTagClick) {
                        int commentNum = scoreEntity.getCommentNum();
                        float commentScore = scoreEntity.getCommentScore();
                        float commentScore1 = scoreEntity.getCommentScore1();
                        float commentScore2 = scoreEntity.getCommentScore2();
                        float commentScore3 = scoreEntity.getCommentScore3();

                        mTvMchScore.setText(String.valueOf(commentScore));
                        mStarBarView.setIntegerMark(false);
                        mStarBarView.setStarMark(commentScore);

                        mTvScenicSpotScore.setText(commentScore1 + "分");
                        mProgressScenicSpot.setProgress((int) commentScore1);

                        mTvInterestScore.setText(commentScore2 + "分");
                        mProgressInterest.setProgress((int) commentScore2);

                        mTvCostPerformancetScore.setText(commentScore3 + "分");
                        mProgressCostPerformancet.setProgress((int) commentScore3);

                        mTvCommentNum.setText("共" + commentNum + "条评论");

                        List<LabelEntity> labelList = mchCollectionResponse.getLabel();

                        if (labelList != null) {
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
                            mTagFlowComment.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                                @Override
                                public boolean onTagClick(View view, int position, FlowLayout parent) {

                                    Set<Integer> selectPosSet = mTagFlowComment.getSelectedList();
                                    Iterator it = selectPosSet.iterator();
                                    while (it.hasNext()) {
                                        onTagClick = false;
                                        int index = (int) it.next();
                                        LabelEntity labelEntity = labelList.get(index);
                                        tagValue = labelEntity.getValue();
                                    }
                                    mPage = 1;
                                    isOnLoadMore = false;
                                    mchCommentList.clear();
                                    mchCommentAdapter.notifyDataSetChanged();
                                    showProgressDialog(MchCommentActivity.this);
                                    getMchCommentList();
                                    return true;
                                }
                            });
                        }
                        if (tagValue != 0)
                        {
                            mTagFlowComment.getAdapter().setSelectedList(tagIndex);
                        }
                    }

                    //评论列表
                    MchCommentResponse.CommentEntity commentEntity = mchCollectionResponse.getComment();
                    List<MchCommentEntity> mchCommentEntityList = commentEntity.getResult();

                    mchCommentAdapter.setHeaderView(header);

                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();
                    } else {

                        mchCommentList.clear();
                        mchCommentAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }
                    //评论列表
                    BasePaginationResult paginationResult = commentEntity.getPage();
                    mTotalPageCount = paginationResult.getPageCount();

                /*    if (mTotalPageCount == 0)
                    {
                        mRlytNoOrderData.setVisibility(View.VISIBLE);
                    } else {
                        mRlytNoOrderData.setVisibility(View.GONE);
                    }
*/

                    if (mchCommentEntityList != null) {
                        mchCommentList.addAll(mchCommentEntityList);
                    }

                    mchCommentAdapter.setScenicSpotsUserCommentList(mchCommentList);
                    mchCommentAdapter.notifyDataSetChanged();
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
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        dismissProgressDialog();
        Constants.getResultMsg(Constants.getResultMsg(msg));
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    public void getMchCommentList() {

        if (validateInternet()) {
            HashMap<String, Object> mchCommentRequest = new HashMap<>();

            mchCommentRequest.put("mchId", mchId);
            mchCommentRequest.put("page", mPage);
            mchCommentRequest.put("pageSize", mPageSize);
            if (tagValue != 0) {
                mchCommentRequest.put("value", tagValue);
            }
            mPresenter.getMchCommentList(mchCommentRequest);
        }
    }
}
