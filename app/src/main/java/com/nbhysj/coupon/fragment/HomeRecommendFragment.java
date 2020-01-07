package com.nbhysj.coupon.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.RecommendFriendsPictureAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.request.PostOprateRequest;
import com.nbhysj.coupon.model.request.QueryByTopicRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FavoritesCollectionResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.ui.HomestayDetailActivity;
import com.nbhysj.coupon.ui.PostRecommendDetailActivity;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.Tools;
import com.nbhysj.coupon.view.JudgeNestedScrollView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeRecommendFragment extends BaseFragment<HomePagePresenter, HomePageModel> implements HomePageContract.View {
    /*  @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;*/
    @BindView(R.id.rv_recommend_friends)
    RecyclerView mRvRecommendFriends;
    @BindView(R.id.scroll_view)
    JudgeNestedScrollView setNeedScroll;

    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    //加载
    @BindView(R.id.llyt_progress_bar_loading)
    LinearLayout mLlytProgressBarLoading;
    @BindView(R.id.progressbar_load_more)
    ProgressBar mProgressBarLoadMore;
    @BindView(R.id.tv_load_more)
    TextView mTvLoadMore;
    private List<HomePageSubTopicTagBean> recommendFriendsList;
    private static int mTagId, mPosition;
    private int mPage = 1;
    private int mPageSize = 10;
    private RecommendFriendsPictureAdapter recommendFriendsAdapter;
    private boolean isInitView = false;
    private boolean isVisible = false;
    public static int currentFragment = 0;
    private int hasNext;
    public int mCollectPostPosition;

    private boolean visibleToUser;
    public void newInstance(int position, int tagId) {
        mTagId = tagId;
        mPosition = position;

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_viewpage_recommend_item;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getHomePageIndexResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void queryByTopicResult(BackResult<HomePageResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if (mPage == 1) {

                        mLlytProgressBarLoading.setVisibility(View.GONE);
                    }

                    HomePageResponse.ResultBean result = res.getData().getResult();
                    List<HomePageSubTopicTagBean> postsTagsBeanList = result.getList();
                    HomePageResponse.PageBean pageBean = res.getData().getPage();
                    hasNext = pageBean.getHasNext();
                    recommendFriendsList.addAll(postsTagsBeanList);
                    if(recommendFriendsList.size() == 0){

                        mRlytNoData.setVisibility(View.VISIBLE);
                    } else{
                        mRlytNoData.setVisibility(View.GONE);
                    }
                    recommendFriendsAdapter.setRecommendFriendsPictureList(recommendFriendsList);
                    recommendFriendsAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getPostInfoResult(BackResult<PostInfoDetailResponse> res) {

    }

    @Override
    public void getHomePageSearchAllResult(BackResult<HomePageAllSearchResponse> res) {

    }

    @Override
    public void getHomePageSearchByTypeResult(BackResult<HomePageTypeSearchResponse> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    @Override
    public void initView(View v) {
        EventBus.getDefault().register(this);
        if (recommendFriendsList == null) {

            recommendFriendsList = new ArrayList<>();
        } else {

            recommendFriendsList.clear();
        }

        isInitView = true;

        isCanLoadData();

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
        mRvRecommendFriends.setLayoutManager(staggeredGridLayoutManager);
        mRvRecommendFriends.setHasFixedSize(true);
        mRvRecommendFriends.setItemViewCacheSize(10);
        mRvRecommendFriends.addItemDecoration(new RecyclerItemDecoration(6, 2));
   //     mRvRecommendFriends.addItemDecoration(new RecyclerViewItemDecoration(Tools.dip2px(getActivity(), 10)));
        //  mRvRecommendFriends.setDrawingCacheEnabled(true);

        mRvRecommendFriends.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        // linearLayoutManager.setAutoMeasureEnabled(true);
        recommendFriendsAdapter = new RecommendFriendsPictureAdapter(getActivity(), new RecommendFriendsPictureAdapter.RecommendPostsDetailListener() {
            @Override
            public void lookRecommendPostDetailListener(int mPosition) {

                HomePageSubTopicTagBean homePageSubTopicTagBean = recommendFriendsList.get(mPosition);
                int postId = homePageSubTopicTagBean.getId();
                Intent intent = new Intent();
                intent.putExtra("postId", postId);
                intent.setClass(getActivity(), PostRecommendDetailActivity.class);
                startActivity(intent);

            }

            @Override
            public void setPostIsCollectionListener(int mPosition) {
                mCollectPostPosition = mPosition;
                HomePageSubTopicTagBean homePageSubTopicTagBean = recommendFriendsList.get(mPosition);

                //   showToast(getActivity(),mPosition + "");
                postIsCollection(homePageSubTopicTagBean);

            }
        });
        recommendFriendsAdapter.setRecommendFriendsPictureList(recommendFriendsList);
        mRvRecommendFriends.setAdapter(recommendFriendsAdapter);

        setNeedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
             /*   if (scrollY > oldScrollY) {
                    Log.i("TAG", "Scroll DOWN");
                }
                if (scrollY < oldScrollY) {
                    Log.i("TAG", "Scroll UP");
                }

                if (scrollY == 0) {
                    Log.i("TAG", "TOP SCROLL");
                }*/

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    Log.i("TAG", "BOTTOM SCROLL");

                    loadData();
                }
            }

        });
    }

    public void loadData() {
        mLlytProgressBarLoading.setVisibility(View.VISIBLE);
        if (hasNext == 1) {
            mProgressBarLoadMore.setVisibility(View.VISIBLE);
            mTvLoadMore.setText(getResources().getString(R.string.loading));
            showProgressDialog(getActivity());
            mPage++;
            queryByTopic();
        } else {
            mProgressBarLoadMore.setVisibility(View.GONE);
            mTvLoadMore.setText(getResources().getString(R.string.str_loading_no_more));
        }
    }

    @Override
    public void getHomeAttentionResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void postOprateResult(BackResult<PraiseOrCollectResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    PraiseOrCollectResponse praiseOrCollectResponse = res.getData();
                    int zanStatus = praiseOrCollectResponse.getZanStatus();

                    HomePageSubTopicTagBean homePageSubTopicTagBean = recommendFriendsList.get(mCollectPostPosition);
                    if (zanStatus == 0) {
                        homePageSubTopicTagBean.setLove(false);
                    } else if (zanStatus == 1) {
                        homePageSubTopicTagBean.setLove(true);
                    }
                    recommendFriendsAdapter.setRecommendFriendsPictureList(recommendFriendsList);
                    recommendFriendsAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void postCollectionResult(BackResult<FavoritesCollectionResponse> res) {

    }

    @Override
    public void getUnReadMessageListResult(BackResult res) {

    }

    @Override
    public void getFavoritesListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void postsCommentResult(BackResult res) {

    }

    @Override
    public void initData() {
       /* NestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                SwipeRefreshLayout.setEnabled(NestedScrollView.getScrollY() == 0);
            }
        });


*/
      /* setNeedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] location = new int[2];
                int xPosition = location[0];
                int yPosition = location[1];

                if (yPosition < 50) {
                    setNeedScroll.setNeedScroll(false);
                } else {
                    setNeedScroll.setNeedScroll(true);

                }
                //showToast(getActivity(),scrollY+""+setNeedScroll.getHeight());
            }
        });*/


       /* mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                     *//*  isOnLoadMore = true;
                        try {
                            if (mTotalCount == merchantEntityList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getMerchantList();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }*//*
                       // setNeedScroll.setNeedScroll(false);
                        refreshLayout.finishLoadMore();
                    }
                }, 200);
            }
        });*/
    }

    public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
        private int itemSpace;
        private int itemNum;

        /**
         * @param itemSpace item间隔
         * @param itemNum   每行item的个数
         */
        public RecyclerItemDecoration(int itemSpace, int itemNum) {
            this.itemSpace = itemSpace;
            this.itemNum = itemNum;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = itemSpace;
                /*if (parent.getChildLayoutPosition(view)%itemNum == 0){  //parent.getChildLayoutPosition(view) 获取view的下标
                    outRect.left = 0;
                } else {
                    outRect.left = itemSpace;
                }*/
            outRect.left = itemSpace;
            outRect.right = itemSpace;
            //outRect.right = itemSpace;
        }
    }

    public void queryByTopic() {

        if (validateInternet()) {

            QueryByTopicRequest queryByTopicRequest = new QueryByTopicRequest();
            queryByTopicRequest.setId(mTagId);
            queryByTopicRequest.setPage(mPage);
            queryByTopicRequest.setPageSize(mPageSize);
            mPresenter.queryByTopic(queryByTopicRequest);
        }
    }

    /*public void getHomePageData(){

        if(validateInternet()){

            mPresenter.getHomePageIndex();
        }
    }*/
/*
    @Override
    public boolean getUserVisibleHint() {
        queryByTopic();
        return super.getUserVisibleHint();

    }*/

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibleToUser = isVisibleToUser;
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见，获取该标志记录下来

        if (isVisibleToUser) {
            isVisible = true;
            isCanLoadData();
        } else {
            isVisible = false;
        }
    }

    private void isCanLoadData() {
        //所以条件是view初始化完成并且对用户可见
        if (isInitView && isVisible)
        {
            if (recommendFriendsList != null) {
                recommendFriendsList.clear();
            }

            if(recommendFriendsAdapter != null)
            {
                recommendFriendsAdapter.notifyDataSetChanged();
            }
            mPage = 1;
            if(mRlytNoData != null)
            {
                mRlytNoData.setVisibility(View.GONE);
            }
            queryByTopic();

            //防止重复加载数据
            isInitView = false;
            isVisible = false;
        }
    }

  /*  @OnClick({R.id.rlyt_load_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlyt_load_more:
                mPage++;
                showProgressDialog(getActivity());
                queryByTopic();

                break;
            default:
                break;
        }
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       /* if(receiver != null)
        {
            getActivity().unregisterReceiver(receiver);
        }*/
    }

    @Override
    public void lazyInitView(View view) {
        // queryByTopic();
    }

    public void postIsCollection(HomePageSubTopicTagBean homePageSubTopicTagBean) {
        if (validateInternet()) {
            showProgressDialog(getActivity());
            mDialog.setTitle("正在点赞...");
            int mPostId = homePageSubTopicTagBean.getId();
            PostOprateRequest postOprateRequest = new PostOprateRequest();
            postOprateRequest.setPostsId(mPostId);
            postOprateRequest.setPostsType(0);
            mPresenter.postOprate(postOprateRequest);
        }
    }


    @Subscribe
    public void onEvent(String mineFragmentRefresh) {
/*
        if(visibleToUser)
        {*/
            if(mineFragmentRefresh.equals("homeFragmentRefresh"))
            {
                if (recommendFriendsList != null) {
                    recommendFriendsList.clear();
                }

                /*if(recommendFriendsAdapter != null)
                {
                    recommendFriendsAdapter.notifyDataSetChanged();
                }*/
                mPage = 1;
                if(mRlytNoData != null)
                {
                    mRlytNoData.setVisibility(View.GONE);
                }
                showProgressDialog(getActivity());
                queryByTopic();
            }
       // }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
