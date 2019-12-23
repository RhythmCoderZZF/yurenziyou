package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MineCollectionAlbumAdapter;
import com.nbhysj.coupon.adapter.OthersCollectionAlbumAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.common.Enum.MineCollectionTypeEnum;
import com.nbhysj.coupon.contract.AlbumContract;
import com.nbhysj.coupon.contract.OthersHomePageContract;
import com.nbhysj.coupon.model.AlbumModel;
import com.nbhysj.coupon.model.OthersHomePageModel;
import com.nbhysj.coupon.model.response.AlbumFavoritesDetail;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.CreateFavoritesResponse;
import com.nbhysj.coupon.model.response.FavoritesBean;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FavoritesResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.UserPersonalHomePageResponse;
import com.nbhysj.coupon.presenter.AlbumPresenter;
import com.nbhysj.coupon.presenter.OthersHomePagePresenter;
import com.nbhysj.coupon.ui.EditAlbumActivity;
import com.nbhysj.coupon.ui.GroupMchOrderSubmitActivity;
import com.nbhysj.coupon.ui.NewAlbumActivity;
import com.nbhysj.coupon.ui.OrderSubmitActivity;
import com.nbhysj.coupon.ui.OthersAlbumDetailsActivity;
import com.nbhysj.coupon.ui.ScenicSpotDetailActivity;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.ResponseBody;

/**
 * @auther：hysj created on 2019/12/11
 * description：其他用户收藏专辑
 */
public class OthersCollectionAlbumFragment extends BaseFragment<OthersHomePagePresenter, OthersHomePageModel> implements OthersHomePageContract.View {
    private static final String USER_ID = "userId";
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    //我的收藏专辑
    @BindView(R.id.rv_mine_collection_album)
    RecyclerView mRvMineCollectionAlbum;

    private int mPageNo = 1;
    private int mPageSize = 10;
    private List<FavoritesBean> mineCollectionAlbumList;
    private boolean isOnLoadMore = false;

    //总条数
    private int mTotalPageCount;
    //专辑收藏适配器
    private OthersCollectionAlbumAdapter othersCollectionAlbumAdapter;

    private boolean visibleToUser;
    public OthersCollectionAlbumFragment() {
        // Required empty public constructor
    }

    public static OthersCollectionAlbumFragment newInstance(int userId) {
        OthersCollectionAlbumFragment fragment = new OthersCollectionAlbumFragment();
        Bundle args = new Bundle();
        args.putInt(USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            userId = getArguments().getInt(USER_ID,0);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_collection_album;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView(View v) {
        EventBus.getDefault().register(this);
        if (mineCollectionAlbumList == null) {

            mineCollectionAlbumList = new ArrayList<>();
        } else {
            mineCollectionAlbumList.clear();
        }

        //我的收藏专辑
        GridLayoutManager travelListAdapterLayoutManager = new GridLayoutManager(getActivity(), 2);
        travelListAdapterLayoutManager.setOrientation(travelListAdapterLayoutManager.VERTICAL);
        mRvMineCollectionAlbum.setLayoutManager(travelListAdapterLayoutManager);
        othersCollectionAlbumAdapter = new OthersCollectionAlbumAdapter(getActivity(), new OthersCollectionAlbumAdapter.NewCollectionAlbumListener() {

            @Override
            public void setLookCollectionAlbumListener(int favoritesId) {

                String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
                if (!TextUtils.isEmpty(token)) {

                    Intent intent = new Intent();
                    intent.setClass(getActivity(), OthersAlbumDetailsActivity.class);
                    intent.putExtra("favoritesId", favoritesId);
                    startActivity(intent);

                } else {

                    onReLogin("");
                }
            }

            @Override
            public void setEditCollectionAlbumListener(FavoritesBean mineCollectionAlbumResponse) {

                String token = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.TOKEN, "");
                if (!TextUtils.isEmpty(token)) {

                    toActivity(EditAlbumActivity.class);

                } else {

                    onReLogin("");
                }
            }
        });
        othersCollectionAlbumAdapter.setCollectionAlbumList(mineCollectionAlbumList);
        mRvMineCollectionAlbum.setAdapter(othersCollectionAlbumAdapter);
        mRvMineCollectionAlbum.addItemDecoration(new RecyclerItemDecoration(6, 2));
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
                        mineCollectionAlbumList.clear();
                        othersCollectionAlbumAdapter.notifyDataSetChanged();
                        showProgressDialog(getActivity());
                        getOtherFindFavoritesList();

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
                            if (mTotalPageCount == mineCollectionAlbumList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getOtherFindFavoritesList();
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
    public void lazyInitView(View view) {

        getOtherFindFavoritesList();
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

    }

    @Override
    public void deletePostResult(BackResult res) {

    }

    @Override
    public void getOtherFindFavoritesListResult(BackResult<FavoritesListResponse> res) {
        dismissProgressDialog();
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();

                    } else {

                        mineCollectionAlbumList.clear();
                        othersCollectionAlbumAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<FavoritesBean> favoritesList = res.getData().getResult();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (favoritesList != null)
                    {
                        mineCollectionAlbumList.addAll(favoritesList);
                    }

                    othersCollectionAlbumAdapter.setCollectionAlbumList(mineCollectionAlbumList);
                    othersCollectionAlbumAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case Constants.USER_NOT_LOGIN_CODE:

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

    //获取专辑列表
    public void getOtherFindFavoritesList()
    {
        if(validateInternet())
        {
            String postsType = MineCollectionTypeEnum.POSTS.getKey();
            mPresenter.getOtherFindFavoritesList(postsType,mPageNo,mPageSize,userId);
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibleToUser = isVisibleToUser;
    }

    @Subscribe
    public void onEvent(String mineFragmentRefresh) {

        if(visibleToUser)
        {
            if(mineFragmentRefresh.equals("mineFragmentRefresh"))
            {
                mineCollectionAlbumList.clear();
                othersCollectionAlbumAdapter.notifyDataSetChanged();
                getOtherFindFavoritesList();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
