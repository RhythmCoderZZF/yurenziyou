package com.nbhysj.coupon.ui;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.AlbumOprateAdapter;
import com.nbhysj.coupon.adapter.CollectionOprateAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.MineCollectionTypeEnum;
import com.nbhysj.coupon.contract.AlbumContract;
import com.nbhysj.coupon.contract.MineContract;
import com.nbhysj.coupon.dialog.CollectEnterAlbumsDialog;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.model.AlbumModel;
import com.nbhysj.coupon.model.MineModel;
import com.nbhysj.coupon.model.request.CollectionBatchMchDeleteRequest;
import com.nbhysj.coupon.model.request.FavoritesBatchDeleteContentRequest;
import com.nbhysj.coupon.model.request.FavoritesBatchMoveContentRequest;
import com.nbhysj.coupon.model.response.AlbumFavoritesDetail;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.CreateFavoritesResponse;
import com.nbhysj.coupon.model.response.FavoritesBean;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FavoritesResponse;
import com.nbhysj.coupon.model.response.MineCollectionAllResponse;
import com.nbhysj.coupon.model.response.MineCollectionDetailResponse;
import com.nbhysj.coupon.model.response.MinePostZanListResponse;
import com.nbhysj.coupon.model.response.StrategyBean;
import com.nbhysj.coupon.presenter.AlbumPresenter;
import com.nbhysj.coupon.presenter.MinePresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.GlideUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;

/**
 * @auther：hysj created on 2019/09/27
 * description：收藏详情
 */
public class CollectionDetailsActivity extends BaseActivity<MinePresenter, MineModel> implements MineContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    //我的收藏
    @BindView(R.id.rv_mine_collection)
    RecyclerView mRvMineCollection;
    //保存
    @BindView(R.id.tv_save)
    TextView mTvSave;
    //专辑编辑
    @BindView(R.id.img_album_edit)
    ImageView mImgAlbumEdit;
    //专辑操作
    @BindView(R.id.llyt_album_oprate)
    LinearLayout mLlytAlbumOprate;

    //已选笔记数量
    @BindView(R.id.tv_selected_notes_num)
    TextView mTvSelectedNotesNum;

    //收藏标题
    @BindView(R.id.tv_collection_title)
    TextView mTvCollectionTitle;

    //收藏数量
    @BindView(R.id.tv_collection_num)
    TextView mTvCollectionNum;

    private CollectionOprateAdapter collectionOprateAdapter;
    private List<FavoritesBean> favoritesAlbumList;

    //我的专辑列表
    private List<FavoritesBean> myfavoritesAlbumList;
    private String albumName;
    private int mPageNo = 1;
    private int mPageSize = 10;
    private boolean isOnLoadMore = false;

    //总条数
    private int mTotalPageCount;

    //专辑id
    private int favoritesId;

    //选择
    List<FavoritesBean> selectAlbumEditList;

    List<Integer> collectionIdList;

    //类型
    private String type;

    private OprateDialog oprateDialog;

    private int REQUEST_CODE_NEW_ALBUM = 0;

    //收藏弹框
    CollectEnterAlbumsDialog collectEnterAlbumsDialog;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_mine_collection_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        type = getIntent().getStringExtra("type");
        if (favoritesAlbumList == null) {

            favoritesAlbumList = new ArrayList<>();
        } else {
            favoritesAlbumList.clear();
        }

        if (myfavoritesAlbumList == null) {

            myfavoritesAlbumList = new ArrayList<>();
        } else {
            myfavoritesAlbumList.clear();
        }


        if (collectionIdList == null) {

            collectionIdList = new ArrayList<>();
        } else {
            collectionIdList.clear();
        }

    /*    int albumNum = recommendFriendsList.size();
        mTvAlbumNum.setText(String.valueOf(albumNum));*/

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
        mRvMineCollection.setLayoutManager(staggeredGridLayoutManager);
     /*   mRvAlbum.setHasFixedSize(true);
        mRvAlbum.setItemViewCacheSize(10);
        mRvAlbum.setDrawingCacheEnabled(true);*/
        mRvMineCollection.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRvMineCollection.addItemDecoration(new RecyclerItemDecoration(6, 2));
        collectionOprateAdapter = new CollectionOprateAdapter(CollectionDetailsActivity.this,type, new CollectionOprateAdapter.AlbumOprateListener() {
            @Override
            public void setAlbumOprateListener(int mSelectAlbumNum) {

                mTvSelectedNotesNum.setText("已选了" + mSelectAlbumNum + "个笔记");
            }
        });
        // albumOprateAdapter.setHasStableIds(true);
        collectionOprateAdapter.setAlbumOprateList(favoritesAlbumList);
        mRvMineCollection.setAdapter(collectionOprateAdapter);
    }

    @Override
    public void initData() {
        showProgressDialog(CollectionDetailsActivity.this);
        getMineCollectionDetail();

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        favoritesAlbumList.clear();
                        collectionOprateAdapter.notifyDataSetChanged();
                        showProgressDialog(CollectionDetailsActivity.this);
                        getMineCollectionDetail();

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
                            if (mTotalPageCount == favoritesAlbumList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getMineCollectionDetail();
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
    public void getMyPostShareListResult(ResponseBody res) {

    }

    @Override
    public void getMinePostZanListResult(BackResult<MinePostZanListResponse> res) {

    }

    @Override
    public void getMineCollectionAllListResult(ResponseBody res) {

    }

    @Override
    public void getMineCollectionDetailResult(BackResult<MineCollectionDetailResponse> res) {
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

                        favoritesAlbumList.clear();
                        collectionOprateAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }
                    MineCollectionDetailResponse mineCollectionDetailResponse = res.getData();

                    if(type != null)
                    {
                        String typeValue = MineCollectionTypeEnum.getEnumByKey(type).getValue();
                        mTvCollectionTitle.setText(typeValue);
                    }

                    BasePaginationResult paginationResult = mineCollectionDetailResponse.getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<FavoritesBean> favoritesList = mineCollectionDetailResponse.getResult();
                    mTvCollectionNum.setText(mTotalPageCount + "篇收藏");
                    if (mTotalPageCount == 0)
                    {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (favoritesList != null) {
                        favoritesAlbumList.addAll(favoritesList);
                    }

                    collectionOprateAdapter.setAlbumOprateList(favoritesAlbumList);
                    collectionOprateAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(CollectionDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void collectionPostsBatchDeleteResult(BackResult res) {

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
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

    @OnClick({R.id.img_album_edit, R.id.tv_save, R.id.rlyt_back, R.id.tv_album_delete})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.img_album_edit:

                mImgAlbumEdit.setVisibility(View.GONE);
                mTvSave.setVisibility(View.VISIBLE);
                collectionOprateAdapter.setAlbumOprate(0);
                collectionOprateAdapter.notifyDataSetChanged();
                mLlytAlbumOprate.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_save:
                mLlytAlbumOprate.setVisibility(View.GONE);
                mTvSave.setVisibility(View.GONE);
                mImgAlbumEdit.setVisibility(View.VISIBLE);
                collectionOprateAdapter.setAlbumOprate(1);
                collectionOprateAdapter.notifyDataSetChanged();
                collectionOprateAdapter.setAlbumEditListClear();

                break;
            case R.id.rlyt_back:
                CollectionDetailsActivity.this.finish();
                break;
            case R.id.tv_album_delete:
                oprateDialog();
                break;
            default:
                break;

        }
    }

    @Override
    public void collectionMchBatchDeleteContentResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                   /* for (int i = 0; i < favoritesAlbumList.size(); i++) {

                        FavoritesBean favoritesBean = favoritesAlbumList.get(i);
                        int mCollectionId = favoritesBean.getCollectionId();

                        for (int j = 0; j < collectionIdList.size(); j++) {
                            int collectionId = collectionIdList.get(j);

                            if (mCollectionId == collectionId) {

                                favoritesAlbumList.remove(favoritesBean);
                            }
                        }
                    }

                    collectionOprateAdapter.setAlbumOprateList(favoritesAlbumList);
                    collectionOprateAdapter.notifyDataSetChanged();*/
                    mPageNo = 1;
                    favoritesAlbumList.clear();
                    collectionOprateAdapter.notifyDataSetChanged();
                    getMineCollectionDetail();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(CollectionDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(CollectionDetailsActivity.this, Constants.getResultMsg(msg));
    }

    //商户批量删除
    public void collectionMchBatchDeleteContent() {

        if (validateInternet()) {

            showProgressDialog(CollectionDetailsActivity.this);

            CollectionBatchMchDeleteRequest collectionBatchMchDeleteRequest = new CollectionBatchMchDeleteRequest();
            collectionBatchMchDeleteRequest.setMchIds(collectionIdList);
            mPresenter.collectionMchBatchDeleteContentRequest(collectionBatchMchDeleteRequest);
        }
    }

    public void oprateDialog() {

        if (oprateDialog == null) {

            oprateDialog = new OprateDialog(CollectionDetailsActivity.this).builder().setTitle("确认要删除吗?");
            oprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            oprateDialog.setPositiveButton(getResources().getString(R.string.str_confirm), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    selectAlbumEditList = collectionOprateAdapter.getSelectAlbumEditList();
                    if (selectAlbumEditList != null && selectAlbumEditList.size() > 0) {
                        collectionIdList.clear();

                        for (int i = 0; i < selectAlbumEditList.size(); i++) {
                            int collectionId = selectAlbumEditList.get(i).getCollectionId();
                            collectionIdList.add(collectionId);
                        }
                        collectionMchBatchDeleteContent();

                    } else {

                        showToast(CollectionDetailsActivity.this, "请选择要删除的收藏内容");
                    }
                }
            });
        }

        oprateDialog.show();
    }

    //获取收藏详情
    public void getMineCollectionDetail() {

        mPresenter.getMineCollectionDetail(type, mPageNo, mPageSize);
    }
}
