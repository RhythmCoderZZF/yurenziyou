package com.nbhysj.coupon.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.RecreationCateListAdapter;
import com.nbhysj.coupon.adapter.ScenicSpotCateListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.RecreationContract;
import com.nbhysj.coupon.contract.ScenicSpotContract;
import com.nbhysj.coupon.model.RecreationModel;
import com.nbhysj.coupon.model.ScenicSpotModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.MchAlbumResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchDetailsResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.NetFriendAlbumResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.TourGuideBean;
import com.nbhysj.coupon.presenter.RecreationPresenter;
import com.nbhysj.coupon.presenter.ScenicSpotPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/11/24
 * description：互动类目列表
 */
public class RecreationCateListActivity extends BaseActivity<RecreationPresenter, RecreationModel> implements RecreationContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //景点热门榜
    @BindView(R.id.rv_scenic_spot_bangdan)
    RecyclerView mRvScenicSpotBangdan;
    @BindView(R.id.rlyt_fine_food_header)
    RelativeLayout mRlytFineFoodHeader;
    @BindView(R.id.toolbar_space)
    View mToolbarSpace;
    @BindView(R.id.iv_back)
    ImageView mImgBack;
    //美食收藏
    @BindView(R.id.img_fine_food_collection)
    ImageView mImgFineFoodCollection;
    //美食转发
    @BindView(R.id.img_fine_food_forward)
    ImageView mImgFineFoodForward;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private RecreationCateListAdapter recreationCateListAdapter;
    private LinearLayoutManager scenicSpotsLinearLayoutManager;
    //互动列表
    List<MchTypeBean> mRecreationList;
    View header;
    private ImageView mImgFineFoodHeader;

    private int mPageNo = 1;
    private int mPageSize = 10;
    //总条数
    int mTotalPageCount;
    //排序规则类型 FJ=附近热门 ZH=综合排序 默认FJ
    private String mSortStr = "FJ";

    private String mLatitude = "";
    private String mLongitude = "";
    private int mCateId;
    private boolean isOnLoadMore = false;
    //图片路径
    private String photoUrl;
    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarCompat.translucentStatusBar(this, false);
        //修改状态栏字体颜色
        StatusBarUtil.setImmersiveStatusBar(this, true);
        return R.layout.activity_scenic_spot_bangdan_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mLatitude = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.LONGITUDE,"");
        mLongitude = (String)SharedPreferencesUtils.getData(SharedPreferencesUtils.LATITUDE,"");
        mCateId = getIntent().getIntExtra("cateId",0);
        mSortStr = getIntent().getStringExtra("sortStr");
        photoUrl = getIntent().getStringExtra("photoUrl");
        if (mRecreationList == null) {

            mRecreationList = new ArrayList<>();
        } else {
            mRecreationList.clear();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        ViewGroup.LayoutParams layoutParams = mToolbarSpace.getLayoutParams();//取控件当前的布局参数
        layoutParams.height = getStatusBarHeight();// 控件的高强制设成状态栏高度
        mToolbarSpace.setLayoutParams(layoutParams); //使设置好的布局参数应用到控件</pre>
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbarSpace.setVisibility(View.VISIBLE);
        } else {
            mToolbarSpace.setVisibility(View.GONE);
        }

        scenicSpotsLinearLayoutManager = new LinearLayoutManager(RecreationCateListActivity.this);
        scenicSpotsLinearLayoutManager.setOrientation(scenicSpotsLinearLayoutManager.VERTICAL);
        mRvScenicSpotBangdan.setLayoutManager(scenicSpotsLinearLayoutManager);
        recreationCateListAdapter = new RecreationCateListAdapter(RecreationCateListActivity.this);
        recreationCateListAdapter.setPopularScenicSpotsList(mRecreationList);
        mRvScenicSpotBangdan.setAdapter(recreationCateListAdapter);
        setHeader(mRvScenicSpotBangdan);
    }


    private void setHeader(RecyclerView view) {
        header = LayoutInflater.from(this).inflate(R.layout.layout_fine_food_header_item, view, false);
        mImgFineFoodHeader = header.findViewById(R.id.img_fine_food_header);
        if (!TextUtils.isEmpty(photoUrl)) {

            GlideUtil.loadImage(mContext, photoUrl, mImgFineFoodHeader);
        }
        recreationCateListAdapter.setHeaderView(header);
    }

    @Override
    public void initData() {
        findScenicListByCateId();

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        mRecreationList.clear();
                        recreationCateListAdapter.notifyDataSetChanged();
                        findScenicListByCateId();

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
                            if (mTotalPageCount == mRecreationList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                findScenicListByCateId();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        refreshLayout.finishLoadMore();
                    }
                }, 200);
            }
        });

        mRvScenicSpotBangdan.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //找到即将移出屏幕Item的position,position是移出屏幕item的数量
                int position = scenicSpotsLinearLayoutManager.findFirstVisibleItemPosition();
                //根据position找到这个Item
                View firstVisiableChildView = scenicSpotsLinearLayoutManager.findViewByPosition(position);
                //获取Item的高
                int itemHeight = firstVisiableChildView.getHeight();
                //算出该Item还未移出屏幕的高度
                int itemTop = firstVisiableChildView.getTop();
                //position移出屏幕的数量*高度得出移动的距离
                int iposition = position * itemHeight;
                //减去该Item还未移出屏幕的部分可得出滑动的距离
                int iResult = iposition - itemTop;
                if (iResult < 300) {
                    float scale = (float) (iResult) / 300;
                    float alpha = (255 * scale);
                    mRlytFineFoodHeader.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    mToolbarSpace.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    mImgFineFoodCollection.setImageResource(R.mipmap.icon_white_fine_food_collection);
                    mImgFineFoodForward.setImageResource(R.mipmap.icon_white_forward);
                    mImgBack.setImageResource(R.mipmap.icon_left_arrow_white);

                } else {
                    mRlytFineFoodHeader.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    mToolbarSpace.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    mImgFineFoodCollection.setImageResource(R.mipmap.icon_black_fine_food_collection);
                    mImgFineFoodForward.setImageResource(R.mipmap.icon_black_forward);
                    mImgBack.setImageResource(R.mipmap.icon_left_arrow_black);
                }
            }


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisiableItemPosition = scenicSpotsLinearLayoutManager.findFirstVisibleItemPosition();
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getRecreationHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findRecreationByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void getRecreationDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void getRecreationDetailResult(BackResult<MchDetailsResponse> res) {

    }

    @Override
    public void getRecreationListByCateIdResult(BackResult<MchCateListResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();
                        String bannerUrl = res.getData().getBanner();
                        if (!TextUtils.isEmpty(bannerUrl)) {
                            GlideUtil.loadImage(mContext, bannerUrl, mImgFineFoodHeader);
                        }
                        recreationCateListAdapter.setHeaderView(header);

                    } else {

                        mRecreationList.clear();
                        recreationCateListAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    MchCateListResponse mchCateListResponse = res.getData();

                    List<MchTypeBean> scenicSpotList = mchCateListResponse.getMchs();
                    BasePaginationResult paginationResult = mchCateListResponse.getPage();
                    mTotalPageCount = paginationResult.getPageCount();

                    if (mTotalPageCount == 0) {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (scenicSpotList != null)
                    {
                        mRecreationList.addAll(scenicSpotList);
                    }
                    recreationCateListAdapter.setPopularScenicSpotsList(mRecreationList);
                    recreationCateListAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(RecreationCateListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(RecreationCateListActivity.this,Constants.getResultMsg(msg));
    }

    @OnClick({R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    public void findScenicListByCateId() {
        if (validateInternet()) {

            showProgressDialog(RecreationCateListActivity.this);
            HashMap<String, String> scenicSpotByCateRequest = new HashMap<>();

            scenicSpotByCateRequest.put("Sorting", mSortStr);
            scenicSpotByCateRequest.put("page", String.valueOf(mPageNo));
            scenicSpotByCateRequest.put("pageSize", String.valueOf(mPageSize));
            scenicSpotByCateRequest.put("longitude", mLongitude);
            scenicSpotByCateRequest.put("latitude",mLatitude);
            scenicSpotByCateRequest.put("cateId",String.valueOf(mCateId));
            mPresenter.getRecreationListByCateId(scenicSpotByCateRequest);
        }
    }
}
