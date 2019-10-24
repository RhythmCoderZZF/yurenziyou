package com.nbhysj.coupon.ui;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.AlbumOprateAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.AlbumContract;
import com.nbhysj.coupon.dialog.CollectEnterAlbumsDialog;
import com.nbhysj.coupon.dialog.OprateDialog;
import com.nbhysj.coupon.model.AlbumModel;
import com.nbhysj.coupon.model.request.FavoritesBatchDeleteContentRequest;
import com.nbhysj.coupon.model.request.FavoritesBatchMoveContentRequest;
import com.nbhysj.coupon.model.request.PostsCollectionRequest;
import com.nbhysj.coupon.model.response.AlbumFavoritesDetail;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.CreateFavoritesResponse;
import com.nbhysj.coupon.model.response.FavoritesBean;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FavoritesResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageSubTopicTagBean;
import com.nbhysj.coupon.model.response.StrategyBean;
import com.nbhysj.coupon.presenter.AlbumPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.GlideUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.http.Query;
/**
 * @auther：hysj created on 2019/09/27
 * description：专辑详情
 */
public class AlbumDetailsActivity extends BaseActivity<AlbumPresenter, AlbumModel> implements AlbumContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    //攻略列表
    @BindView(R.id.rv_album)
    RecyclerView mRvAlbum;
    @BindView(R.id.image_avatar)
    CircleImageView mImgAvatar;
    //保存
    @BindView(R.id.tv_save)
    TextView mTvSave;
    //专辑编辑
    @BindView(R.id.img_album_edit)
    ImageView mImgAlbumEdit;
    //专辑分享
    @BindView(R.id.img_album_share)
    ImageView mImgAlbumShare;
    //编辑专辑标签
    @BindView(R.id.tv_album_edit_tag)
    TextView mTvAlbumEditTag;
    @BindView(R.id.edt_album_intro)
    TextView mEdtAlbumIntro;
    @BindView(R.id.tv_album_num)
    TextView mTvAlbumNum;
    @BindView(R.id.tv_album_title)
    TextView mTvAlbumTitle;
    //用户名
    @BindView(R.id.tv_name)
    TextView mTvUsername;
    //专辑操作
    @BindView(R.id.llyt_album_oprate)
    LinearLayout mLlytAlbumOprate;

    //已选笔记数量
    @BindView(R.id.tv_selected_notes_num)
    TextView mTvSelectedNotesNum;

    private AlbumOprateAdapter albumOprateAdapter;
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

    //收藏id
    List<Integer> collectionIdList;

    //帖子id
    List<Integer> postsIdList;

    //0:移动帖子专辑 1:删除专辑内容
    private int oprateFlag = 0;

    private OprateDialog oprateDialog;

    private int REQUEST_CODE_NEW_ALBUM = 0;

    //收藏弹框
    CollectEnterAlbumsDialog collectEnterAlbumsDialog;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_album_details;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        favoritesId = getIntent().getIntExtra("favoritesId", 0);
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

        if (postsIdList == null) {

            postsIdList = new ArrayList<>();
        } else {
            postsIdList.clear();
        }


    /*    int albumNum = recommendFriendsList.size();
        mTvAlbumNum.setText(String.valueOf(albumNum));*/

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
        mRvAlbum.setLayoutManager(staggeredGridLayoutManager);
     /*   mRvAlbum.setHasFixedSize(true);
        mRvAlbum.setItemViewCacheSize(10);
        mRvAlbum.setDrawingCacheEnabled(true);*/
        mRvAlbum.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRvAlbum.addItemDecoration(new RecyclerItemDecoration(6, 2));
        albumOprateAdapter = new AlbumOprateAdapter(AlbumDetailsActivity.this, new AlbumOprateAdapter.AlbumOprateListener() {
            @Override
            public void setAlbumOprateListener(int mSelectAlbumNum) {

                mTvSelectedNotesNum.setText("已选了" + mSelectAlbumNum + "个笔记");
            }
        });
      // albumOprateAdapter.setHasStableIds(true);
        albumOprateAdapter.setAlbumOprateList(favoritesAlbumList);
        mRvAlbum.setAdapter(albumOprateAdapter);
    }

    @Override
    public void initData() {
        showProgressDialog(AlbumDetailsActivity.this);
        getAlbumFavoritesDetail();

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        favoritesAlbumList.clear();
                        albumOprateAdapter.notifyDataSetChanged();
                        showProgressDialog(AlbumDetailsActivity.this);
                        getAlbumFavoritesDetail();

                    }
                }, 100);
            }
        });
    }

    @Override
    public void getAlbumFavoritesDetailResult(BackResult<AlbumFavoritesDetail> res) {
        dismissProgressDialog();
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    AlbumFavoritesDetail favoritesResponse = res.getData();
                    String title = favoritesResponse.getTitle();
                    String avatarUrl = favoritesResponse.getAvater();
                    String nickName = favoritesResponse.getNickname();
                    int postsNum = favoritesResponse.getPostsNum();
                    String intro = favoritesResponse.getIntro();
                    if (!TextUtils.isEmpty(title)) {
                        mTvAlbumTitle.setText(title);
                    }
                    mTvUsername.setText(nickName);
                    mTvAlbumNum.setText(String.valueOf(postsNum));
                    if (!TextUtils.isEmpty(intro)) {
                        mEdtAlbumIntro.setText(intro);
                    }
                    GlideUtil.loadImage(AlbumDetailsActivity.this, avatarUrl, mImgAvatar);
                    List<FavoritesBean> favoritesList = favoritesResponse.getPostsCollections();

                    if (favoritesList != null) {
                        favoritesAlbumList.addAll(favoritesList);
                    }

                    albumOprateAdapter.setAlbumOprateList(favoritesAlbumList);
                    albumOprateAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AlbumDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
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

    @OnClick({R.id.img_album_edit, R.id.tv_save, R.id.rlyt_back, R.id.tv_album_delete, R.id.tv_move_my_album})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.img_album_edit:

                mImgAlbumEdit.setVisibility(View.GONE);
                mImgAlbumShare.setVisibility(View.GONE);
                mTvSave.setVisibility(View.VISIBLE);
                mTvAlbumEditTag.setVisibility(View.VISIBLE);
                albumOprateAdapter.setAlbumOprate(0);
                albumOprateAdapter.notifyDataSetChanged();
                mLlytAlbumOprate.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_save:
                mLlytAlbumOprate.setVisibility(View.GONE);
                mTvSave.setVisibility(View.GONE);
                mImgAlbumEdit.setVisibility(View.VISIBLE);
                //  mImgAlbumShare.setVisibility(View.VISIBLE);
                mTvAlbumEditTag.setVisibility(View.GONE);
                albumOprateAdapter.setAlbumOprate(1);
                // albumOprateAdapter.setAlbumOprateList(favoritesAlbumList);
                albumOprateAdapter.notifyDataSetChanged();
                albumOprateAdapter.setAlbumEditListClear();
                //showToast(AlbumDetailsActivity.this, selectAlbumEditList.toString());
                    /*for (int i = 0; i < favoritesAlbumList.size(); i++) {
                        FavoritesBean albumBean = favoritesAlbumList.get(i);
                        int isAlbumSelect = albumBean.getIsAlbumSelect();
                        if (isAlbumSelect == 1) {
                            albumName = mEdtAlbumName.getText().toString().trim();
                            albumBean.setContent(albumName);
                        }
                        albumBean.setIsAlbumSelect(0);
                    }*/
                //  String selectAlbumEditListJson = JSON.toJSONString(selectAlbumEditList);

                // showToast(AlbumDetailsActivity.this, selectAlbumEditListJson);
               /* hiddenIME();
                albumOprateAdapter.setAlbumOprateList(selectAlbumEditList);
                albumOprateAdapter.notifyDataSetChanged();

                albumOprateAdapter.setAlbumEditListClear();*/


                break;
            case R.id.rlyt_back:
                AlbumDetailsActivity.this.finish();
                break;
            case R.id.tv_move_my_album:
                oprateFlag = 0;
                selectAlbumEditList = albumOprateAdapter.getSelectAlbumEditList();
                if (selectAlbumEditList != null && selectAlbumEditList.size() > 0) {
                    collectionIdList.clear();

                    for (int i = 0; i < selectAlbumEditList.size(); i++) {
                        int collectionId = selectAlbumEditList.get(i).getCollectionId();
                        collectionIdList.add(collectionId);
                    }

                    getFavoritesList();

                } else {

                    showToast(AlbumDetailsActivity.this, "请选择要转移的专辑内容");
                }
                break;
            case R.id.tv_album_delete:
                oprateFlag = 1;
                oprateDialog();
                break;
            default:
                break;

        }
    }

    @Override
    public void createFavoritesResult(BackResult<CreateFavoritesResponse> res) {

    }

    @Override
    public void getFavoritesCollectionListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void updateFavoritesResult(BackResult res) {

    }

    @Override
    public void queryUserFavoritesResult(BackResult<FavoritesResponse> res) {

    }

    @Override
    public void albumFavoritesbatchMoveContentResult(BackResult res) {
        dismissProgressDialog();

        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    for (int i = 0; i < favoritesAlbumList.size(); i++) {

                        FavoritesBean favoritesBean = favoritesAlbumList.get(i);
                        int mCollectionId = favoritesBean.getCollectionId();

                        for (int j = 0; j < collectionIdList.size(); j++) {
                            int collectionId = collectionIdList.get(j);

                            if (mCollectionId == collectionId) {

                                favoritesAlbumList.remove(favoritesBean);
                            }
                        }
                    }

                    albumOprateAdapter.setAlbumOprateList(favoritesAlbumList);
                    albumOprateAdapter.notifyDataSetChanged();

                    albumOprateAdapter.setAlbumEditListClear();

                    if (collectEnterAlbumsDialog != null) {
                        collectEnterAlbumsDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AlbumDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getFavoritesListResult(BackResult<FavoritesListResponse> res) {
        dismissProgressDialog();

        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    myfavoritesAlbumList.clear();
                    if (collectEnterAlbumsDialog != null) {
                        if (isOnLoadMore)
                        {
                            collectEnterAlbumsDialog.setSmartRefreshLayoutLoadMoreFinish();

                        } else {

                            collectEnterAlbumsDialog.setSmartRefreshLayoutRefreshFinish();
                        }
                    }
                    FavoritesListResponse favoritesListResponse = res.getData();

                    List<FavoritesBean> favoritesList = favoritesListResponse.getResult();

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    if (favoritesList == null) {
                        favoritesList = new ArrayList<>();
                    }

                    myfavoritesAlbumList.addAll(favoritesList);
                    if (collectEnterAlbumsDialog == null) {
                        collectEnterAlbumsDialog = new CollectEnterAlbumsDialog(myfavoritesAlbumList, new CollectEnterAlbumsDialog.ChooseAlbumsCollectionListener() {
                            @Override
                            public void setChooseAlbumsCollectionListener(FavoritesBean favoritesBean) {

                                int favoritesId = favoritesBean.getId();
                                albumFavoritesbatchMoveContent(favoritesId);
                            }

                            @Override
                            public void setNewAlbumCollectionListener() {

                                Intent intent = new Intent();
                                intent.setClass(AlbumDetailsActivity.this, NewAlbumActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_NEW_ALBUM);

                            }

                            @Override
                            public void setNewAlbumOnRefreshListener() {

                                isOnLoadMore = false;
                                mPageNo = 1;
                                myfavoritesAlbumList.clear();
                                collectEnterAlbumsDialog.setAlbumCollectList(myfavoritesAlbumList);
                                showProgressDialog(AlbumDetailsActivity.this);
                                getFavoritesList();
                            }

                            @Override
                            public void setNewAlbumOnLoadMoreListener(RefreshLayout refreshLayout) {

                                isOnLoadMore = true;
                                refreshLayout.getLayout().postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
                                        isOnLoadMore = true;
                                        try {
                                            if (mTotalPageCount == myfavoritesAlbumList.size()) {
                                                refreshLayout.finishLoadMoreWithNoMoreData();
                                            } else {
                                                mPageNo++;
                                                getFavoritesList();
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 200);

                            }
                        });

                        collectEnterAlbumsDialog.show(getFragmentManager(), "收藏专辑");

                    } else {
                        collectEnterAlbumsDialog.setAlbumCollectList(myfavoritesAlbumList);
                        collectEnterAlbumsDialog.show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AlbumDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void albumFavoritesbatchDeleteContentResult(BackResult res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    for (int i = 0; i < favoritesAlbumList.size(); i++) {

                        FavoritesBean favoritesBean = favoritesAlbumList.get(i);
                        int mSelectPostsId = favoritesBean.getId();

                        for (int j = 0; j < postsIdList.size(); j++) {
                            int postId = postsIdList.get(j);

                            if (mSelectPostsId == postId) {

                                favoritesAlbumList.remove(favoritesBean);
                            }
                        }
                    }

                    albumOprateAdapter.setAlbumOprateList(favoritesAlbumList);
                    albumOprateAdapter.notifyDataSetChanged();

                    albumOprateAdapter.setAlbumEditListClear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(AlbumDetailsActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(AlbumDetailsActivity.this, Constants.getResultMsg(msg));
    }

    public void getAlbumFavoritesDetail() {

        if (validateInternet()) {

            showProgressDialog(AlbumDetailsActivity.this);
            mPresenter.getAlbumFavoritesDetail(favoritesId);
        }
    }

    public void albumFavoritesbatchDeleteContent() {

        if (validateInternet()) {

            showProgressDialog(AlbumDetailsActivity.this);

            FavoritesBatchDeleteContentRequest favoritesBatchDeleteContentRequest = new FavoritesBatchDeleteContentRequest();
            favoritesBatchDeleteContentRequest.setDataIds(postsIdList);
            favoritesBatchDeleteContentRequest.setFavoritesId(favoritesId);
            mPresenter.albumFavoritesbatchDeleteContent(favoritesBatchDeleteContentRequest);

        }
    }

    public void oprateDialog() {

        if (oprateDialog == null) {

            oprateDialog = new OprateDialog(AlbumDetailsActivity.this).builder().setTitle("确认要删除吗?");
            oprateDialog.setNegativeButton(getResources().getString(R.string.str_cancel), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            oprateDialog.setPositiveButton(getResources().getString(R.string.str_confirm), getResources().getColor(R.color.color_text_black7), new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    selectAlbumEditList = albumOprateAdapter.getSelectAlbumEditList();
                    if (selectAlbumEditList != null && selectAlbumEditList.size() > 0) {
                        postsIdList.clear();

                        for (int i = 0; i < selectAlbumEditList.size(); i++) {
                            int postId = selectAlbumEditList.get(i).getId();
                            postsIdList.add(postId);
                        }
                        albumFavoritesbatchDeleteContent();

                    } else {

                        showToast(AlbumDetailsActivity.this, "请选择要删除的帖子");
                    }
                }
            });
        }

        oprateDialog.show();
    }

    //获取专辑列表
    public void getFavoritesList() {

        showProgressDialog(AlbumDetailsActivity.this);
        mPresenter.getFavoritesList(mPageNo, mPageSize);
    }


    //专辑帖子转移
    public void albumFavoritesbatchMoveContent(int favoritesId) {

        showProgressDialog(AlbumDetailsActivity.this);
        FavoritesBatchMoveContentRequest favoritesBatchMoveContentRequest = new FavoritesBatchMoveContentRequest();
        favoritesBatchMoveContentRequest.setCollectionId(collectionIdList);
        favoritesBatchMoveContentRequest.setFavoritesId(favoritesId);
        mPresenter.albumFavoritesbatchMoveContent(favoritesBatchMoveContentRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_NEW_ALBUM && resultCode == RESULT_OK) {
            getFavoritesList();
        }
    }
}
