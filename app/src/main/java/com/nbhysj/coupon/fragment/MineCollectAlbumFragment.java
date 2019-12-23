package com.nbhysj.coupon.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.MineCollectionAlbumAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.AlbumContract;
import com.nbhysj.coupon.model.AlbumModel;
import com.nbhysj.coupon.model.request.FavoritesDeleteRequest;
import com.nbhysj.coupon.model.response.AlbumFavoritesDetail;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.CreateFavoritesResponse;
import com.nbhysj.coupon.model.response.FavoritesBean;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FavoritesResponse;
import com.nbhysj.coupon.model.response.MineCollectionAlbumResponse;
import com.nbhysj.coupon.model.response.StrategyBean;
import com.nbhysj.coupon.presenter.AlbumPresenter;
import com.nbhysj.coupon.ui.EditAlbumActivity;
import com.nbhysj.coupon.ui.NewAlbumActivity;
import com.nbhysj.coupon.ui.StrategyActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * @auther：hysj created on 2019/05/24
 * description：我的收藏专辑
 */
public class MineCollectAlbumFragment extends BaseFragment<AlbumPresenter, AlbumModel> implements AlbumContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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
    private MineCollectionAlbumAdapter mineCollectionAlbumAdapter;

    private boolean visibleToUser;

    //专辑编辑
    private int REQUEST_CODE_EDIT_COLLECTIION_ALBUM = 0;

    //删除编辑
    private int REQUEST_CODE_DELETE_COLLECTIION_ALBUM = 1;

    private int mPosition;

    public MineCollectAlbumFragment() {
        // Required empty public constructor
    }

    public static MineCollectAlbumFragment newInstance(String param1, String param2) {
        MineCollectAlbumFragment fragment = new MineCollectAlbumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
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
         mineCollectionAlbumAdapter = new MineCollectionAlbumAdapter(getActivity(), new MineCollectionAlbumAdapter.NewCollectionAlbumListener() {
            @Override
            public void setNewCollectionAlbumListener() {

                toActivity(NewAlbumActivity.class);
            }

            @Override
            public void setEditCollectionAlbumListener(int position,FavoritesBean favoritesBean) {
                mPosition = position;
                int collectionId = favoritesBean.getId();

                Bundle bundle = new Bundle();
                bundle.putSerializable("favoritesBean",favoritesBean);
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getActivity(),EditAlbumActivity.class);
               // intent.putExtra("collectionId",collectionId);
                startActivityForResult(intent,REQUEST_CODE_EDIT_COLLECTIION_ALBUM);
            }
        });
        mineCollectionAlbumAdapter.setCollectionAlbumList(mineCollectionAlbumList);
        mRvMineCollectionAlbum.setAdapter(mineCollectionAlbumAdapter);
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
                        mineCollectionAlbumAdapter.notifyDataSetChanged();
                        showProgressDialog(getActivity());
                        getFavoritesCollectionList();

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
                                getFavoritesCollectionList();
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
            outRect.left = itemSpace;
            outRect.right = itemSpace;

        }
    }

    @Override
    public void lazyInitView(View view) {

        getFavoritesCollectionList();
    }

    @Override
    public void createFavoritesResult(BackResult<CreateFavoritesResponse> res) {

    }

    @Override
    public void updateFavoritesResult(BackResult res) {

    }

    @Override
    public void queryUserFavoritesResult(BackResult<FavoritesResponse> res) {

    }

    @Override
    public void getAlbumFavoritesDetailResult(BackResult<AlbumFavoritesDetail> res) {

    }

    @Override
    public void albumFavoritesbatchMoveContentResult(BackResult res) {

    }

    @Override
    public void albumFavoritesbatchDeleteContentResult(BackResult res) {

    }

    @Override
    public void getFavoritesListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void delFavoritesRequest(BackResult res) {

    }

    @Override
    public void getFavoritesCollectionListResult(BackResult<FavoritesListResponse> res) {
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
                        mineCollectionAlbumAdapter.notifyDataSetChanged();
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

                    mineCollectionAlbumAdapter.setCollectionAlbumList(mineCollectionAlbumList);
                    mineCollectionAlbumAdapter.notifyDataSetChanged();

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
    public void getFavoritesCollectionList(){

        if(validateInternet()){

            mPresenter.getFavoritesCollectionList(mPageNo,mPageSize);
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
                mPageNo = 1;
                mSmartRefreshLayout.setNoMoreData(false);
                mineCollectionAlbumList.clear();
                mineCollectionAlbumAdapter.notifyDataSetChanged();
                getFavoritesCollectionList();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_EDIT_COLLECTIION_ALBUM && resultCode == RESULT_OK)
        {
            int albumOprate = data.getIntExtra("albumOprate",0);
            if(albumOprate == 0) {
                String albumTitle = data.getStringExtra("albumTitle");
                String albumIntro = data.getStringExtra("albumIntro");
                mineCollectionAlbumList.get(mPosition).setTitle(albumTitle);
                mineCollectionAlbumList.get(mPosition).setIntro(albumIntro);
                mineCollectionAlbumAdapter.notifyDataSetChanged();
            } else if(albumOprate == 1){

                FavoritesBean favoritesBean = mineCollectionAlbumList.get(mPosition);
                mineCollectionAlbumList.remove(favoritesBean);
                mineCollectionAlbumAdapter.setCollectionAlbumList(mineCollectionAlbumList);
                mineCollectionAlbumAdapter.notifyDataSetChanged();
            }
        }
    }
}
