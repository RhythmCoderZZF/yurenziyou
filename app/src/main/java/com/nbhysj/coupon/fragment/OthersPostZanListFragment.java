package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.RecommendFriendsPictureAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.contract.OthersHomePageContract;
import com.nbhysj.coupon.model.MineModel;
import com.nbhysj.coupon.model.OthersHomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.MineCollectionDetailResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.UserPersonalHomePageResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionResponse;
import com.nbhysj.coupon.presenter.MinePresenter;
import com.nbhysj.coupon.presenter.OthersHomePagePresenter;
import com.nbhysj.coupon.ui.PostRecommendDetailActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.ResponseBody;

public class OthersPostZanListFragment extends BaseFragment<OthersHomePagePresenter, OthersHomePageModel> implements OthersHomePageContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    @BindView(R.id.rv_mine_post_zan_list)
    RecyclerView mRvMinePostZanList;

    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private List<HomePageSubTopicTagBean> recommendFriendsList;
    private int mPageNo = 1;
    private int mPageSize = 10;
    private RecommendFriendsPictureAdapter recommendFriendsAdapter;
    public int mCollectPostPosition;
    private boolean isOnLoadMore = false;
    //总条数
    private int mTotalPageCount;

    //用户id
    private int userId;

    public static OthersPostZanListFragment newInstance(int userId) {
        OthersPostZanListFragment fragment = new OthersPostZanListFragment();
        Bundle args = new Bundle();
        args.putInt("userId", userId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_post_zan_list;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getOthersHomePageInfoResult(BackResult<UserPersonalHomePageResponse> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {

    }

    @Override
    public void getOtherCollectionAllResult(ResponseBody res) {

    }

    @Override
    public void getOthersPostShareListResult(ResponseBody res) {

    }

    @Override
    public void getOtherBeforeZanResult(BackResult<MinePostZanListResponse> res) {

        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    MinePostZanListResponse minePostZanListResponse = res.getData();
                    List<HomePageSubTopicTagBean> homePageSubTopicTagBeanList = minePostZanListResponse.getResult();
                    BasePaginationResult paginationResult = minePostZanListResponse.getPage();

                    mTotalPageCount = paginationResult.getPageCount();

                    if (homePageSubTopicTagBeanList != null)
                    {
                        recommendFriendsList.addAll(homePageSubTopicTagBeanList);
                    }

                    if (recommendFriendsList.size() == 0) {

                        mRlytNoData.setVisibility(View.VISIBLE);
                    } else {
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
    public void deletePostResult(BackResult res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
    }

    @Override
    public void initView(View v) {

        userId = getArguments().getInt("userId",userId);

        if (recommendFriendsList == null) {

            recommendFriendsList = new ArrayList<>();
        } else {

            recommendFriendsList.clear();
        }

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
        mRvMinePostZanList.setLayoutManager(staggeredGridLayoutManager);
        mRvMinePostZanList.setHasFixedSize(true);
        mRvMinePostZanList.setItemViewCacheSize(10);
        //  mRvRecommendFriends.setDrawingCacheEnabled(true);
        mRvMinePostZanList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
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


            }
        });
        recommendFriendsAdapter.setRecommendFriendsPictureList(recommendFriendsList);
        mRvMinePostZanList.addItemDecoration(new RecyclerItemDecoration(6, 2));
        mRvMinePostZanList.setAdapter(recommendFriendsAdapter);

        getOtherBeforeZanList();
    }

    @Override
    public void initData() {

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        recommendFriendsList.clear();
                        recommendFriendsAdapter.notifyDataSetChanged();
                        showProgressDialog(getActivity());
                        getOtherBeforeZanList();

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
                            if (mTotalPageCount == recommendFriendsList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getOtherBeforeZanList();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


    }

    @Override
    public void getOtherFindFavoritesListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void lazyInitView(View view) {
        // queryByTopic();
    }

    public void getOtherBeforeZanList() {
        if (validateInternet()) {
            mPresenter.getOtherBeforeZan(userId);
        }
    }
}
