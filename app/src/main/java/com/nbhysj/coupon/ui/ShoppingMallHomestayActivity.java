package com.nbhysj.coupon.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.HomestayAdapter;
import com.nbhysj.coupon.adapter.HomestaySectionAdapter;
import com.nbhysj.coupon.adapter.MchRankingClassificationAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.MchSearchConditionEnum;
import com.nbhysj.coupon.common.Enum.MchTypeEnum;
import com.nbhysj.coupon.contract.HomestayContract;
import com.nbhysj.coupon.model.HomestayModel;
import com.nbhysj.coupon.model.request.MchCollectionRequest;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.CouponsGetBean;
import com.nbhysj.coupon.model.response.HouseResouceResponse;
import com.nbhysj.coupon.model.response.LandlordDetailResonse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchCouponResponse;
import com.nbhysj.coupon.model.response.MchHomestayDetailsResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.presenter.HomestayPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.view.StickyScrollView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/09/14
 * description：名宿页面
 */
public class ShoppingMallHomestayActivity extends BaseActivity<HomestayPresenter, HomestayModel> implements HomestayContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //热门景点
    @BindView(R.id.rv_popular_scenic_spots)
    RecyclerView mRvPopularScenicSpots;
    //景点分类
    @BindView(R.id.rv_scenic_spot_classification)
    RecyclerView mRvScenicSpotClassification;
    //附近热门
    @BindView(R.id.tv_popular_nearby)
    TextView mTvPopularNearby;
    //综合排序
    @BindView(R.id.tv_comprehensive_sorting)
    TextView mTvComprehensiveSorting;
    //超值预定
    @BindView(R.id.tv_super_value_reservation)
    TextView mTvSuperValueReservation;
    //距离优先
    @BindView(R.id.tv_distance_first)
    TextView mTvDistanceFirst;
    @BindView(R.id.rlyt_back)
    RelativeLayout mRlytBack;
    @BindView(R.id.sticky_scrollview)
    StickyScrollView mStickyScrollView;
    @BindView(R.id.rv_hot_scenic_spot)
    RecyclerView mRvHotelHomestay;
    LinearLayoutManager scenicSpotsLinearLayoutManager;
    //民宿栏目
    private HomestaySectionAdapter hotelHomestaySectionAdapter;
    //民宿分类
    private MchRankingClassificationAdapter mchRankingClassificationAdapter;
    //民宿适配器
    private HomestayAdapter homestayAdapter;
    //民宿中间分类
    List<ScenicSpotHomePageResponse.CateEntity> mCateEntityList;
    //民宿栏目
    List<MchTypeBean> mHomestayHotList;
    //民宿列表
    List<MchTypeBean> mHomestayList;
    //加载
    @BindView(R.id.llyt_progress_bar_loading)
    LinearLayout mLlytProgressBarLoading;
    @BindView(R.id.progressbar_load_more)
    ProgressBar mProgressBarLoadMore;
    @BindView(R.id.tv_load_more)
    TextView mTvLoadMore;

    private int mPage = 1;
    private int mPageSize = 10;
    int mTotalPageCount;
    //排序规则类型 FJ=附近热门 ZH=综合排序 默认FJ
    private String mSorting = "FJ";
    private String mLatitude = "";  //经度
    private String mLongitude = ""; //纬度
    //民宿点赞item
    private int mPosition = 0;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_shopping_mall_scenic_spot;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (mCateEntityList == null) {

            mCateEntityList = new ArrayList<>();
        } else {
            mCateEntityList.clear();
        }
        if (mHomestayHotList == null) {

            mHomestayHotList = new ArrayList<>();
        } else {
            mHomestayHotList.clear();
        }
        if (mHomestayList == null) {

            mHomestayList = new ArrayList<>();
        } else {
            mHomestayList.clear();
        }
        mLatitude = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.LATITUDE,"");
        mLongitude = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.LONGITUDE,"");
        showProgressDialog(ShoppingMallHomestayActivity.this);
        getHomestayHomePage();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShoppingMallHomestayActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvPopularScenicSpots.setLayoutManager(linearLayoutManager);
        hotelHomestaySectionAdapter = new HomestaySectionAdapter(ShoppingMallHomestayActivity.this);
        hotelHomestaySectionAdapter.setHotelHomestaySectionList(mHomestayHotList);
        mRvPopularScenicSpots.setAdapter(hotelHomestaySectionAdapter);

        //酒店民宿分类
        LinearLayoutManager scenicSpotClassificationLinearLayout = new LinearLayoutManager(ShoppingMallHomestayActivity.this);
        scenicSpotClassificationLinearLayout.setOrientation(linearLayoutManager.HORIZONTAL);
        mRvScenicSpotClassification.setLayoutManager(scenicSpotClassificationLinearLayout);
        mchRankingClassificationAdapter = new MchRankingClassificationAdapter(ShoppingMallHomestayActivity.this, MchTypeEnum.MCH_HOMESTAY.getValue(), new MchRankingClassificationAdapter.MchRankingClassificationListener() {
            @Override
            public void setMchRankingClassificationListener(int cateId, String photoUrl) {
                Intent intent = new Intent();
                intent.setClass(mContext, HomestayCateListActivity.class);
                intent.putExtra("cateId",cateId);
                intent.putExtra("photoUrl",photoUrl);
                intent.putExtra("sortStr",mSorting);
                startActivity(intent);
            }
        });
        mchRankingClassificationAdapter.setMchRankingClassificationList(mCateEntityList);
        mRvScenicSpotClassification.setAdapter(mchRankingClassificationAdapter);

        scenicSpotsLinearLayoutManager = new LinearLayoutManager(ShoppingMallHomestayActivity.this);
        scenicSpotsLinearLayoutManager.setOrientation(scenicSpotsLinearLayoutManager.VERTICAL);
        mRvHotelHomestay.setLayoutManager(scenicSpotsLinearLayoutManager);
        homestayAdapter = new HomestayAdapter(ShoppingMallHomestayActivity.this, new HomestayAdapter.HomestayCollectionListener() {
            @Override
            public void setHomestayCollection(int position,int dataId) {
                mPosition = position;
                mchCollection(dataId);

            }
        });
        homestayAdapter.setHomestayList(mHomestayList);
        mRvHotelHomestay.setAdapter(homestayAdapter);

    }

    @Override
    public void initData() {
        mRlytBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShoppingMallHomestayActivity.this.finish();
            }
        });

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mPage = 1;
                        mHomestayList.clear();
                        findHomestayByCate();

                    }
                }, 100);
            }
        });

        mStickyScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()))
                {
                    loadData();
                }
            }
        });

        mStickyScrollView.setScrolListener(new StickyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {

                if (scrollY == (mStickyScrollView.getChildAt(0).getMeasuredHeight() - mStickyScrollView.getMeasuredHeight())) {
                    loadData();
                }
            }
        });
    }

    public void loadData() {
        mLlytProgressBarLoading.setVisibility(View.VISIBLE);
        if (mHomestayList.size() != mTotalPageCount) {
            mProgressBarLoadMore.setVisibility(View.VISIBLE);
            mTvLoadMore.setText(getResources().getString(R.string.loading));
            showProgressDialog(ShoppingMallHomestayActivity.this);
            mPage++;
            findHomestayByCate();
        } else {
            mProgressBarLoadMore.setVisibility(View.GONE);
            mTvLoadMore.setText(getResources().getString(R.string.str_loading_no_more));
        }
    }

    @Override
    public void getLandlordHomePageResult(BackResult<LandlordDetailResonse> res) {

    }

    @Override
    public void getLandlordHouseResourceListResult(BackResult<HouseResouceResponse> res) {

    }

    @Override
    public void getHomestayHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    //景点栏
                    mHomestayHotList = res.getData().getHot();
                    hotelHomestaySectionAdapter.setHotelHomestaySectionList(mHomestayHotList);
                    hotelHomestaySectionAdapter.notifyDataSetChanged();

                    //景点分类
                    mCateEntityList = res.getData().getCate();
                    mchRankingClassificationAdapter.setMchRankingClassificationList(mCateEntityList);
                    mchRankingClassificationAdapter.notifyDataSetChanged();

                    List<MchTypeBean> scenicSpotList = res.getData().getMch().getNearby().getResult();
                    mHomestayList.addAll(scenicSpotList);
                    homestayAdapter.setHomestayList(mHomestayList);
                    homestayAdapter.notifyDataSetChanged();
                    BasePaginationResult pageBean = res.getData().getMch().getNearby().getPage();
                    mTotalPageCount = pageBean.getPageCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallHomestayActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getHomestayListByCateIdResult(BackResult<MchCateListResponse> res) {

    }

    @Override
    public void findHomestayByCateResult(BackResult<ScenicSpotResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    List<MchTypeBean> scenicSpotList = res.getData().getResult();
                    BasePaginationResult pageBean = res.getData().getPage();
                    mTotalPageCount = pageBean.getPageCount();
                    mHomestayList.addAll(scenicSpotList);

                        if (mPage == 1) {

                        if (mSmartRefreshLayout != null) {
                            mSmartRefreshLayout.finishRefresh();
                            mProgressBarLoadMore.setVisibility(View.GONE);
                            mTvLoadMore.setText(getResources().getString(R.string.str_pull_up_loading));
                        }
                    }

                    homestayAdapter.setHomestayList(mHomestayList);
                    homestayAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallHomestayActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getHomestayBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void getMchHomestayDetailResult(BackResult<MchHomestayDetailsResponse> res) {

    }

    @Override
    public void queryMchCouponListResult(BackResult<List<MchCouponResponse>> res) {

    }

    @Override
    public void getCouponResult(BackResult<CouponsGetBean> res) {

    }

    @Override
    public void mchCollectionResult(BackResult res) {

        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    mHomestayList.get(mPosition).setLoveStatus(1);
                    homestayAdapter.setHomestayList(mHomestayList);
                    homestayAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallHomestayActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {
        dismissProgressDialog();
        showToast(ShoppingMallHomestayActivity.this, Constants.getResultMsg(msg));

    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @OnClick({R.id.tv_popular_nearby, R.id.tv_comprehensive_sorting, R.id.tv_super_value_reservation, R.id.tv_distance_first,R.id.toolbar})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.tv_popular_nearby:
                mTvPopularNearby.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvPopularNearby.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                mTvComprehensiveSorting.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvSuperValueReservation.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvDistanceFirst.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvComprehensiveSorting.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvSuperValueReservation.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvDistanceFirst.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mPage = 1;
                mHomestayList.clear();
                mSorting = MchSearchConditionEnum.FJ.getValue();
                showProgressDialog(ShoppingMallHomestayActivity.this);
                findHomestayByCate();
                break;
            case R.id.tv_comprehensive_sorting:
                mTvComprehensiveSorting.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvComprehensiveSorting.setTypeface(Typeface.DEFAULT, Typeface.BOLD);

                mTvPopularNearby.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvSuperValueReservation.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvDistanceFirst.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvPopularNearby.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvSuperValueReservation.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvDistanceFirst.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mPage = 1;
                mHomestayList.clear();
                mSorting = MchSearchConditionEnum.CZ.getValue();
                showProgressDialog(ShoppingMallHomestayActivity.this);
                findHomestayByCate();

                break;
            case R.id.tv_super_value_reservation:
                mTvSuperValueReservation.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvComprehensiveSorting.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvPopularNearby.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvDistanceFirst.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvSuperValueReservation.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                mTvComprehensiveSorting.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvPopularNearby.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvDistanceFirst.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mPage = 1;
                mHomestayList.clear();
                mSorting = MchSearchConditionEnum.ZH.getValue();
                showProgressDialog(ShoppingMallHomestayActivity.this);
                findHomestayByCate();
                break;
            case R.id.tv_distance_first:
                mTvDistanceFirst.setTextColor(getResources().getColor(R.color.txt_font_black2));
                mTvSuperValueReservation.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvComprehensiveSorting.setTextColor(getResources().getColor(R.color.color_text_gray20));
                mTvPopularNearby.setTextColor(getResources().getColor(R.color.color_text_gray20));

                mTvDistanceFirst.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                mTvSuperValueReservation.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvComprehensiveSorting.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mTvPopularNearby.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
                mPage = 1;
                mHomestayList.clear();
                mSorting = "";
                showProgressDialog(ShoppingMallHomestayActivity.this);
                findHomestayByCate();
                break;
            case R.id.toolbar:

                toActivity(HomePageSearchActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
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
   /* public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }*/

    //获取民宿首页
    public void getHomestayHomePage() {

        if (validateInternet()) {

            mPresenter.getHomestayHomePage(mLongitude, mLatitude);
        }
    }

    //获取民宿首页栏目筛选
    public void findHomestayByCate() {
        if (validateInternet()) {

            HashMap<String, String> scenicSpotByCateRequest = new HashMap<>();

            if(!TextUtils.isEmpty(mSorting))
            {
                scenicSpotByCateRequest.put("sorting", mSorting);
            }
            scenicSpotByCateRequest.put("page", String.valueOf(mPage));
            scenicSpotByCateRequest.put("pageSize", String.valueOf(mPageSize));
            scenicSpotByCateRequest.put("longitude", mLongitude);
            scenicSpotByCateRequest.put("latitude", mLatitude);
            mPresenter.findHomestayByCate(scenicSpotByCateRequest);
        }
    }
    //民宿点赞
    public void mchCollection(int dataId){

        if (validateInternet())
        {
            MchCollectionRequest mchCollectionRequest = new MchCollectionRequest();
            mchCollectionRequest.setDataId(dataId);
            mPresenter.mchCollection(mchCollectionRequest);
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
}
