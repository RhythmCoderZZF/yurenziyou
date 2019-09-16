package com.nbhysj.coupon.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.NetFriendAlbumAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.ScenicSpotContract;
import com.nbhysj.coupon.model.ScenicSpotModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.NetFriendAlbumResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.TourGuideBean;
import com.nbhysj.coupon.presenter.ScenicSpotPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/05/06
 * description：网友相册
 */
public class FriendsNetAlbumFragment extends BaseFragment<ScenicSpotPresenter, ScenicSpotModel> implements ScenicSpotContract.View {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.rv_net_friends_album)
    RecyclerView mRvNetFriendsAlbum;
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private int mPage = 1;
    private int mPageSize = 10;
    private int mTotalPageCount;
    NetFriendAlbumAdapter netFriendAlbumAdapter;
    private List<NetFriendAlbumResponse.NetFriendAlbumEntity> netFriendAlbumEntityList;
    private int mchId;
    private boolean isOnLoadMore = false;

    public FriendsNetAlbumFragment() {
        // Required empty public constructor
    }

    public static FriendsNetAlbumFragment newInstance(int mchId) {
        FriendsNetAlbumFragment fragment = new FriendsNetAlbumFragment();
        Bundle args = new Bundle();
        args.putInt("mchId", mchId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_net_friends_album;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(View v) {
        mchId = getActivity().getIntent().getIntExtra("mchId", 0);
        if (netFriendAlbumEntityList == null) {

            netFriendAlbumEntityList = new ArrayList<>();
        } else {
            netFriendAlbumEntityList.clear();
        }

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(mContext, 2
                );
        mRvNetFriendsAlbum.setLayoutManager(gridLayoutManager);
        // linearLayoutManager.setAutoMeasureEnabled(true);
        netFriendAlbumAdapter = new NetFriendAlbumAdapter(mContext);
        netFriendAlbumAdapter.setPhotoUrlList(netFriendAlbumEntityList);
        mRvNetFriendsAlbum.setAdapter(netFriendAlbumAdapter);
        mRvNetFriendsAlbum.addItemDecoration(new RecyclerItemDecoration(12, 2));

        // MoveToPosition(gridLayoutManager,6);
    }

    @Override
    public void initData() {
        getNetFriendsAlbum();
        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPage = 1;
                        getNetFriendsAlbum();

                        isOnLoadMore = false;
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
                        try {
                            isOnLoadMore = true;
                            if (mTotalPageCount == netFriendAlbumEntityList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getNetFriendsAlbum();
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
    public void getScenicSpotHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findScenicByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void getScenicBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void getMchDetailsResult(BackResult<MchDetailsResponse> res) {

    }

    @Override
    public void getTourGuideListResult(BackResult<List<TourGuideBean>> res) {

    }

    @Override
    public void getMchAlbumListResult(BackResult<MchAlbumResponse> res) {

    }

    @Override
    public void getNetFriendAlbumListResult(BackResult<NetFriendAlbumResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();

                    } else {
                        netFriendAlbumEntityList.clear();
                        netFriendAlbumAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }
                    mRlytNoData.setVisibility(View.GONE);
                    NetFriendAlbumResponse netFriendAlbumResponse = res.getData();
                    BasePaginationResult basePageBean = netFriendAlbumResponse.getPage();
                    mTotalPageCount = basePageBean.getPageCount();
                    List<NetFriendAlbumResponse.NetFriendAlbumEntity> netFriendAlbumList = netFriendAlbumResponse.getUserPhotos();

                    if (mTotalPageCount == 0) {
                        netFriendAlbumEntityList.clear();
                        mRlytNoData.setVisibility(View.VISIBLE);
                    }
                    if (netFriendAlbumEntityList != null) {
                        netFriendAlbumEntityList.addAll(netFriendAlbumList);
                    }
                    netFriendAlbumAdapter.setPhotoUrlList(netFriendAlbumEntityList);
                    netFriendAlbumAdapter.notifyDataSetChanged();

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
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));
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
            // outRect.bottom = itemSpace;
                /*if (parent.getChildLayoutPosition(view)%itemNum == 0){  //parent.getChildLayoutPosition(view) 获取view的下标
                    outRect.left = 0;
                } else {
                    outRect.left = itemSpace;
                }*/
            outRect.left = itemSpace;
            outRect.right = itemSpace;
            // outRect.bottom = itemSpace;
            //outRect.right = itemSpace;
        }
    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }

    /* RecyclerView 移动到当前位置，
     *
     * @param manager  设置RecyclerView对应的manager
     * @param n  要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }

    @Override
    public void lazyInitView(View view) {


    }

    public void getNetFriendsAlbum() {

        if (validateInternet()) {

            mPresenter.getNetFriendAlbumList(mchId, mPage, mPageSize);
        }
    }
}
