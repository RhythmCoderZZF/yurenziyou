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
import com.nbhysj.coupon.adapter.FineFoodBangDanListAdapter;
import com.nbhysj.coupon.adapter.FineFoodCateListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.SharePlatformEnum;
import com.nbhysj.coupon.contract.FineFoodContract;
import com.nbhysj.coupon.dialog.ShareOprateDialog;
import com.nbhysj.coupon.model.FineFoodModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.FineFoodCommentInitResponse;
import com.nbhysj.coupon.model.response.FoodRecommendListResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchFoodDetailResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.model.response.ZanAndCollectionBean;
import com.nbhysj.coupon.presenter.FineFoodPresenter;
import com.nbhysj.coupon.systembar.StatusBarCompat;
import com.nbhysj.coupon.systembar.StatusBarUtil;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/09/16
 * description：美食榜单列表
 */
public class FineFoodCateListActivity extends BaseActivity<FineFoodPresenter, FineFoodModel> implements FineFoodContract.View {
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
    //互动榜单收藏
    @BindView(R.id.img_fine_food_collection)
    ImageView mImgFineFoodCollection;
    //互动榜单转发
    @BindView(R.id.img_fine_food_forward)
    ImageView mImgFineFoodForward;
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    private FineFoodCateListAdapter fineFoodCateListAdapter;
    private LinearLayoutManager scenicSpotsLinearLayoutManager;
    //景点列表
    List<MchTypeBean> mRecreationList;
    View header;
    private ImageView mImgFineFoodHeader;
    //排序规则类型 FJ=附近热门 ZH=综合排序 默认FJ
    private String mSortStr = "FJ";

    private String mLatitude = "";
    private String mLongitude = "";
    private int mCateId;

    private int mPage = 1;
    private int mPageSize = 10;

    //总数量
    private int mTotalPageCount;
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

        scenicSpotsLinearLayoutManager = new LinearLayoutManager(FineFoodCateListActivity.this);
        scenicSpotsLinearLayoutManager.setOrientation(scenicSpotsLinearLayoutManager.VERTICAL);
        mRvScenicSpotBangdan.setLayoutManager(scenicSpotsLinearLayoutManager);
        fineFoodCateListAdapter = new FineFoodCateListAdapter(FineFoodCateListActivity.this);
        fineFoodCateListAdapter.setFineFoodList(mRecreationList);
        mRvScenicSpotBangdan.setAdapter(fineFoodCateListAdapter);
        setHeader(mRvScenicSpotBangdan);
    }


    private void setHeader(RecyclerView view) {
        header = LayoutInflater.from(this).inflate(R.layout.layout_fine_food_header_item, view, false);
        mImgFineFoodHeader = header.findViewById(R.id.img_fine_food_header);
    }

    @Override
    public void initData() {
        findFineFoodByCate();
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

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPage = 1;
                        /*isOnLoadMore = false;
                        pendingTravelOrderTypeList.clear();
                        myOrderListAdapter.notifyDataSetChanged();
                        // showProgressDialog(getActivity());
                        getPendingTravelOrderList();*/

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
                        // isOnLoadMore = true;
                        try {
                           /* if (mTotalCount == merchantEntityList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPage++;
                                getMerchantList();
                            }*/
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        refreshLayout.finishLoadMore();
                    }
                }, 200);
            }
        });
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getFoodCommentIndexResult(BackResult<FineFoodCommentInitResponse> res) {

    }

    @Override
    public void fineFoodCommentResult(BackResult res) {

    }

    @Override
    public void getFineFoodHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findFoodByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void mchCollectionResult(BackResult<MchCollectionResponse> res) {

    }

    @Override
    public void getFoodBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void findFoodsListByCateIdResult(BackResult<MchCateListResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    mRecreationList.clear();
                    List<MchTypeBean> scenicSpotList = res.getData().getMchs();
                    if(scenicSpotList != null && scenicSpotList.size() > 0) {
                        mRecreationList.addAll(scenicSpotList);
                        fineFoodCateListAdapter.setFineFoodList(mRecreationList);
                        fineFoodCateListAdapter.notifyDataSetChanged();
                    }
                    String bannerUrl = res.getData().getBanner();
                    if (!TextUtils.isEmpty(bannerUrl))
                    {
                        GlideUtil.loadImage(mContext, bannerUrl, mImgFineFoodHeader);
                    }

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }


                    fineFoodCateListAdapter.setHeaderView(header);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(FineFoodCateListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(FineFoodCateListActivity.this,Constants.getResultMsg(msg));
    }

    @Override
    public void getFoodDetailResult(BackResult<MchFoodDetailResponse> res) {

    }

    @Override
    public void getGoodsFoodRecommendList(BackResult<FoodRecommendListResponse> res) {

    }

    @OnClick({R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.img_fine_food_forward:

              /*  if(shareOprateDialog == null)
                {
                    shareOprateDialog = new ShareOprateDialog(FineFoodCateListActivity.this, new ShareOprateDialog.OnSharePlatformItemClickListener() {
                        @Override
                        public void onSharePlatformItemClick(SHARE_MEDIA sharePlatform) {

                            try {
                                if (bannerList != null && bannerList.size() > 0) {
                                    String sharePlatformStr = sharePlatform.toString();
                                    photoUrl = bannerList.get(0);
                                    String wechatFriend = SharePlatformEnum.WECHAT_FRIEND.getValue();
                                    if (sharePlatformStr.equals(wechatFriend)) {

                                        new Thread(saveFileRunnable).start();

                                    } else {

                                        thirdShare(sharePlatform, photoUrl);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).builder().setCancelable(true).setCanceledOnTouchOutside(true);
                }
                shareOprateDialog.show();*/
                break;
            default:
                break;
        }
    }

    public void findFineFoodByCate() {
        if (validateInternet()) {

            HashMap<String, String> scenicSpotByCateRequest = new HashMap<>();

            scenicSpotByCateRequest.put("Sorting", mSortStr);
            scenicSpotByCateRequest.put("page", String.valueOf(mPage));
            scenicSpotByCateRequest.put("pageSize", String.valueOf(mPageSize));
            scenicSpotByCateRequest.put("longitude",mLongitude);
            scenicSpotByCateRequest.put("latitude", mLatitude);
            scenicSpotByCateRequest.put("cateId",String.valueOf(mCateId));
            mPresenter.findFoodListByCateId(scenicSpotByCateRequest);
        }
    }
}
