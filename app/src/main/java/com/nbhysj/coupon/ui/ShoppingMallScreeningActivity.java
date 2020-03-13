package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.DropDownMenuClassifyThreeLevelAdapter;
import com.nbhysj.coupon.adapter.DropDownMenuDistanceClassifyAdapter;
import com.nbhysj.coupon.adapter.DropDownMenuDistanceSubTwoLevelAdapter;
import com.nbhysj.coupon.adapter.DropDownMenuDistanceTwoLevelAdapter;
import com.nbhysj.coupon.adapter.DropDownMenuListAdapter;
import com.nbhysj.coupon.adapter.HomestayAdapter;
import com.nbhysj.coupon.adapter.HotelAdapter;
import com.nbhysj.coupon.adapter.ShopMallScreenStarClassAdapter;
import com.nbhysj.coupon.adapter.SortDropDownMenuListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.HotelAndHomestaySearchContract;
import com.nbhysj.coupon.model.HotelAndHomestaySearchModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.HomestayBean;
import com.nbhysj.coupon.model.response.HotelSearchResponse;
import com.nbhysj.coupon.model.response.LevelTagEntity;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.PositionDistanceBean;
import com.nbhysj.coupon.model.response.PositionDistanceSearchBean;
import com.nbhysj.coupon.model.response.SortTypeBean;
import com.nbhysj.coupon.model.response.TagBaseEntity;
import com.nbhysj.coupon.presenter.HotelAndHomestaySearchPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.view.DropDownMenu;
import com.nbhysj.coupon.widget.DoubleSlideSeekBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/03/08
 * description：酒店民宿推荐排序
 */
public class ShoppingMallScreeningActivity extends BaseActivity<HotelAndHomestaySearchPresenter, HotelAndHomestaySearchModel> implements HotelAndHomestaySearchContract.View {
    private String headers[] = {"推荐排序", "位置距离", "价格/星级"};
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.drop_down_menu_screening)
    DropDownMenu mDropDownMenu;
    RecyclerView mRvHotel;
    RecyclerView mRvHomestay;
    //赞无数据
    RelativeLayout mRlytNoData;
    //酒店适配器
    private HotelAdapter hotelAdapter;
    //民宿适配器
    private HomestayAdapter homestayAdapter;
    //星级
    RecyclerView mRvStarClass;
    DropDownMenuDistanceClassifyAdapter dropDownMenuDistanceTypeOneLevelAdapter;
    DropDownMenuDistanceTwoLevelAdapter dropDownMenuDistanceTypeTwoLevelAdapter;
    DropDownMenuDistanceSubTwoLevelAdapter dropDownMenuDistanceTypeSubTwoLevelAdapter;
    DropDownMenuClassifyThreeLevelAdapter dropDownMenuClassifyThreeLevelAdapter;
    List<PositionDistanceSearchBean> positionDistanceSearchOneLevelList;

    List<PositionDistanceSearchBean> positionDistanceSearchTwoLevelList;

    private List<PositionDistanceSearchBean> positionDistanceSearchThreeLevelList;

    private List<MchTypeBean> hotelList;

    //二级列表
    private RecyclerView mRvPositionDistanceClassifyTwoLevelLinkage;

    //二级列表(有三级列表时候显示)
    private RecyclerView mRvPositionDistanceClassifySubTwoLevelLinkage;

    //三级列表
    private RecyclerView mRvPositionDistanceClassifyThreeLevelLinkage;

    //经度
    private String latitude;

    //纬度
    private String longitude;

    //距离
    private int distance;

    //RECOMMEND(推荐好评)，LOW_PRICE(低价优先)，HIGH_PRICE(高价优先),NICE_COMMENT(好评优先)
    private String sortValue;

    //条数
    private int pageSize = 10;

    //页数
    private int mPageNo = 1;

    private boolean isOnLoadMore = false;

    //总条数
    private int mTotalPageCount;

    //商户类型
    private String[] mchTypeArray = {"MCH_HOTEL1","MCH_HOTEL2","MCH_HOTEL3"};

    private String mchType = mchTypeArray[0];

    //排序方式
    private String[] sortTypeArray = {"RECOMMEND","LOW_PRICE","HIGH_PRICE","NICE_COMMENT"};

    private LinearLayoutManager homestayLayoutManager;

    //是否是民宿搜索
    private boolean isHomestaySearch;
    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_shopping_mall_screening;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        latitude = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.LATITUDE, "");
        longitude = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.LONGITUDE, "");

        if (positionDistanceSearchOneLevelList == null) {

            positionDistanceSearchOneLevelList = new ArrayList<>();

        } else {
            positionDistanceSearchOneLevelList.clear();
        }

        if (positionDistanceSearchTwoLevelList == null) {
            positionDistanceSearchTwoLevelList = new ArrayList<>();

        } else {
            positionDistanceSearchTwoLevelList.clear();
        }

        if (positionDistanceSearchThreeLevelList == null) {
            positionDistanceSearchThreeLevelList = new ArrayList<>();

        } else {
            positionDistanceSearchThreeLevelList.clear();
        }

        if(hotelList == null){

            hotelList = new ArrayList<>();
        } else {
            hotelList.clear();
        }

        getHomestayScreeningCondition();


        List<View> popupViews = new ArrayList<>();

        List<SortTypeBean> sortTypeDropDownMenuList = new ArrayList<>();
        SortTypeBean recommendedSorting = new SortTypeBean();
        recommendedSorting.setSortType("推荐排序");
        recommendedSorting.setSortValue(sortTypeArray[0]);
        recommendedSorting.setSelect(true);
        sortValue = recommendedSorting.getSortValue();
        SortTypeBean lowPriority = new SortTypeBean();
        lowPriority.setSortType("低价优先");
        lowPriority.setSortValue(sortTypeArray[1]);
        SortTypeBean highPricePriority = new SortTypeBean();
        highPricePriority.setSortType("高价优先");
        highPricePriority.setSortValue(sortTypeArray[2]);
        SortTypeBean praiseFirst = new SortTypeBean();
        praiseFirst.setSortType("好评优先");
        praiseFirst.setSortValue(sortTypeArray[3]);

        sortTypeDropDownMenuList.add(recommendedSorting);
        sortTypeDropDownMenuList.add(lowPriority);
        sortTypeDropDownMenuList.add(highPricePriority);
        sortTypeDropDownMenuList.add(praiseFirst);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        RecyclerView mRvdropDownMenuList = new RecyclerView(ShoppingMallScreeningActivity.this);
        mRvdropDownMenuList.setLayoutManager(linearLayoutManager);
        SortDropDownMenuListAdapter dropDownMenuListAdapter = new SortDropDownMenuListAdapter(ShoppingMallScreeningActivity.this, new SortDropDownMenuListAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(SortTypeBean sortTypeBean)
            {
                String positionDistanceType = sortTypeBean.getSortType();
                sortValue = sortTypeBean.getSortValue();
                mDropDownMenu.setTabText(positionDistanceType);
                mDropDownMenu.closeMenu();
                mPageNo = 1;
                hotelList.clear();
                hotelAdapter.notifyDataSetChanged();
                showProgressDialog(ShoppingMallScreeningActivity.this);
                getMchHotelList();
            }
        });
        dropDownMenuListAdapter.setDropDownMenuList(sortTypeDropDownMenuList);
        mRvdropDownMenuList.setAdapter(dropDownMenuListAdapter);

        List<PositionDistanceBean> dropDownMenuList3 = new ArrayList<>();
        PositionDistanceBean positionDistance10 = new PositionDistanceBean();
        positionDistance10.setPositionDistanceType("推荐排序");

        PositionDistanceBean positionDistance11 = new PositionDistanceBean();
        positionDistance11.setPositionDistanceType("低价优先");
        PositionDistanceBean positionDistance12 = new PositionDistanceBean();
        PositionDistanceBean positionDistance13 = new PositionDistanceBean();
        positionDistance13.setPositionDistanceType("高价优先");
        PositionDistanceBean positionDistance14 = new PositionDistanceBean();
        positionDistance14.setPositionDistanceType("好评优先");
        dropDownMenuList3.add(positionDistance10);
        dropDownMenuList3.add(positionDistance11);
        dropDownMenuList3.add(positionDistance12);
        dropDownMenuList3.add(positionDistance13);
        dropDownMenuList3.add(positionDistance14);

       LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        linearLayoutManager3.setOrientation(linearLayoutManager3.VERTICAL);
        RecyclerView mRvdropDownMenuList3 = new RecyclerView(ShoppingMallScreeningActivity.this);
        mRvdropDownMenuList3.setLayoutManager(linearLayoutManager3);
        DropDownMenuListAdapter dropDownMenuListAdapter3 = new DropDownMenuListAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuListAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(int position,PositionDistanceBean positionDistanceBean) {


              //  dropDownMenuListAdapter3.notifyDataSetChanged();

            }
        });
        dropDownMenuListAdapter3.setDropDownMenuList(dropDownMenuList3);
        mRvdropDownMenuList3.setAdapter(dropDownMenuListAdapter3);


        View mViewDistanceCondition = getLayoutInflater().inflate(R.layout.layout_shop_mall_position_distance_condition, null);
        RecyclerView mRvPositionDistanceClassifyType = mViewDistanceCondition.findViewById(R.id.rv_position_distance_classify_type);
        //二级列表
        mRvPositionDistanceClassifyTwoLevelLinkage = mViewDistanceCondition.findViewById(R.id.rv_position_distance_classify_two_level_linkage);

        //三级列表时候（对应二级,三级列表）
        mRvPositionDistanceClassifySubTwoLevelLinkage = mViewDistanceCondition.findViewById(R.id.rv_position_distance_classify_sub_two_level_linkage);
        mRvPositionDistanceClassifyThreeLevelLinkage = mViewDistanceCondition.findViewById(R.id.rv_position_distance_classify_three_level_linkage);
        TextView mTvPositionDistanceConfirm = mViewDistanceCondition.findViewById(R.id.tv_position_distance_confirm);
        mTvPositionDistanceConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog(ShoppingMallScreeningActivity.this);
                getMchHotelList();
            }
        });

        LinearLayoutManager distanceConditionLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        distanceConditionLinearLayoutManager.setOrientation(distanceConditionLinearLayoutManager.VERTICAL);
        mRvPositionDistanceClassifyType.setLayoutManager(distanceConditionLinearLayoutManager);

        dropDownMenuDistanceTypeOneLevelAdapter = new DropDownMenuDistanceClassifyAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuDistanceClassifyAdapter.DropDownMenuDistanceClassifyListener() {
            @Override
            public void setDropDownMenuDistanceClassifyListener(int position, PositionDistanceSearchBean positionDistanceBean) {


                    if(positionDistanceSearchOneLevelList != null && positionDistanceSearchOneLevelList.size() > 0) {
                        for (int j = 0; j < positionDistanceSearchOneLevelList.size(); j++) {
                            PositionDistanceSearchBean positionDistanceTwoLevel = positionDistanceSearchOneLevelList.get(j);
                            positionDistanceTwoLevel.setSelect(false);

                            positionDistanceSearchTwoLevelList = positionDistanceTwoLevel.getPosition();

                            if (positionDistanceSearchTwoLevelList != null && positionDistanceSearchTwoLevelList.size() > 0)
                            {
                                for (int k = 0; k < positionDistanceSearchTwoLevelList.size(); k++)
                                {
                                    PositionDistanceSearchBean positionDistanceThreeLevel = positionDistanceSearchTwoLevelList.get(k);
                                    positionDistanceThreeLevel.setSelect(false);
                                }
                            }
                    }
                }

                positionDistanceBean.setSelect(true);
                dropDownMenuDistanceTypeOneLevelAdapter.notifyDataSetChanged();

                if (positionDistanceSearchOneLevelList != null && positionDistanceSearchOneLevelList.size() > 0) {

                    positionDistanceSearchTwoLevelList = positionDistanceSearchOneLevelList.get(position).getPosition();
                     positionDistanceSearchThreeLevelList = positionDistanceSearchTwoLevelList.get(0).getPosition();
                    // List<PositionDistanceBean.PositionDistanceTypeClassifyThreeLevel> positionDistanceTypeClassifyThreeLevelList = positionDistanceTypeClassify.get(0).getClassifyThreeLevelList();
                    if (positionDistanceSearchTwoLevelList != null && positionDistanceSearchTwoLevelList.size() > 0 && positionDistanceSearchThreeLevelList != null && positionDistanceSearchThreeLevelList.size() > 0) {
                        PositionDistanceSearchBean positionDistanceTwoBean = positionDistanceSearchTwoLevelList.get(0);
                        positionDistanceTwoBean.setSelect(true);
                        dropDownMenuDistanceTypeSubTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
                        dropDownMenuDistanceTypeSubTwoLevelAdapter.notifyDataSetChanged();

                        PositionDistanceSearchBean positionDistanceThreeBean = positionDistanceSearchThreeLevelList.get(0);
                        positionDistanceThreeBean.setSelect(true);
                        dropDownMenuClassifyThreeLevelAdapter.setPositionDistanceList(positionDistanceSearchThreeLevelList);
                        dropDownMenuClassifyThreeLevelAdapter.notifyDataSetChanged();
                        //  mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.GONE);
                        mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.GONE);
                        mRvPositionDistanceClassifySubTwoLevelLinkage.setVisibility(View.VISIBLE);
                        mRvPositionDistanceClassifyThreeLevelLinkage.setVisibility(View.VISIBLE);


                    }else if (positionDistanceSearchTwoLevelList != null && positionDistanceSearchTwoLevelList.size() > 0 && positionDistanceSearchThreeLevelList == null || positionDistanceSearchThreeLevelList.size() == 0) {
                        PositionDistanceSearchBean positionDistanceTwoBean = positionDistanceSearchTwoLevelList.get(0);
                        positionDistanceTwoBean.setSelect(true);
                        dropDownMenuDistanceTypeTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
                        dropDownMenuDistanceTypeTwoLevelAdapter.notifyDataSetChanged();
                        mRvPositionDistanceClassifyThreeLevelLinkage.setVisibility(View.GONE);
                        mRvPositionDistanceClassifySubTwoLevelLinkage.setVisibility(View.GONE);
                        mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        dropDownMenuDistanceTypeOneLevelAdapter.setPositionDistanceList(positionDistanceSearchOneLevelList);
        mRvPositionDistanceClassifyType.setAdapter(dropDownMenuDistanceTypeOneLevelAdapter);

        //位置距离二级列表
        LinearLayoutManager twoLevelLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        twoLevelLinearLayoutManager.setOrientation(twoLevelLinearLayoutManager.VERTICAL);
        mRvPositionDistanceClassifyTwoLevelLinkage.setLayoutManager(twoLevelLinearLayoutManager);
        dropDownMenuDistanceTypeTwoLevelAdapter = new DropDownMenuDistanceTwoLevelAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuDistanceTwoLevelAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(int position, PositionDistanceSearchBean positionDistanceBean) {

                getPositionDistance(positionDistanceBean);
            }
        });

        dropDownMenuDistanceTypeTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
        mRvPositionDistanceClassifyTwoLevelLinkage.setAdapter(dropDownMenuDistanceTypeTwoLevelAdapter);

        //位置距离二级子列表
        LinearLayoutManager subTwoLevelLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        subTwoLevelLinearLayoutManager.setOrientation(subTwoLevelLinearLayoutManager.VERTICAL);
        mRvPositionDistanceClassifySubTwoLevelLinkage.setLayoutManager(subTwoLevelLinearLayoutManager);
        dropDownMenuDistanceTypeSubTwoLevelAdapter = new DropDownMenuDistanceSubTwoLevelAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuDistanceSubTwoLevelAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(int position, PositionDistanceSearchBean positionDistanceBean) {

                if(positionDistanceSearchTwoLevelList != null && positionDistanceSearchTwoLevelList.size() > 0)
                {
                    positionDistanceSearchThreeLevelList = positionDistanceSearchTwoLevelList.get(position).getPosition();
                    dropDownMenuClassifyThreeLevelAdapter.setPositionDistanceList(positionDistanceSearchThreeLevelList);
                    dropDownMenuClassifyThreeLevelAdapter.notifyDataSetChanged();
                }
            }
        });

        dropDownMenuDistanceTypeSubTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
        mRvPositionDistanceClassifySubTwoLevelLinkage.setAdapter(dropDownMenuDistanceTypeSubTwoLevelAdapter);


        //位置距离三级列表
        LinearLayoutManager threeLevelLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        threeLevelLinearLayoutManager.setOrientation(threeLevelLinearLayoutManager.VERTICAL);
        mRvPositionDistanceClassifyThreeLevelLinkage.setLayoutManager(threeLevelLinearLayoutManager);
        dropDownMenuClassifyThreeLevelAdapter = new DropDownMenuClassifyThreeLevelAdapter(ShoppingMallScreeningActivity.this, new DropDownMenuClassifyThreeLevelAdapter.DropDownMenuListener() {
            @Override
            public void setDropDownMenuListener(int position, PositionDistanceSearchBean positionDistanceBean) {

                getPositionDistance(positionDistanceBean);
            }
        });

        dropDownMenuClassifyThreeLevelAdapter.setPositionDistanceList(positionDistanceSearchThreeLevelList);
        mRvPositionDistanceClassifyThreeLevelLinkage.setAdapter(dropDownMenuClassifyThreeLevelAdapter);


        View mViewStarPriceCondition = getLayoutInflater().inflate(R.layout.layout_shop_mall_star_price_condition, null);
        DoubleSlideSeekBar mDoubleslideWithrule = mViewStarPriceCondition.findViewById(R.id.double_slide_price);
        mRvStarClass = mViewStarPriceCondition.findViewById(R.id.rv_star_class);
        // 用法
        mDoubleslideWithrule.setOnRangeListener(new DoubleSlideSeekBar.onRangeListener() {
            @Override
            public void onRange(float low, float big) {
                String lowPrice = String.format("%.0f", low);
                String bigPrice = String.format("%.0f", big);
                String mBudegt = lowPrice + "," + bigPrice;
                showToast(ShoppingMallScreeningActivity.this, mBudegt);
            }
        });

        List<LevelTagEntity> levelTagEntityList = new ArrayList<>();
        LevelTagEntity tagBaseEntity = new LevelTagEntity();
        tagBaseEntity.setTitle("不限");
        LevelTagEntity tagBaseEntity1 = new LevelTagEntity();
        tagBaseEntity1.setTitle("二星/经济");
        LevelTagEntity tagBaseEntity2 = new LevelTagEntity();
        tagBaseEntity2.setTitle("三星/舒适");
        LevelTagEntity tagBaseEntity3 = new LevelTagEntity();
        tagBaseEntity3.setTitle("四星/豪华");
        LevelTagEntity tagBaseEntity4 = new LevelTagEntity();
        tagBaseEntity4.setTitle("五星/豪华");
        levelTagEntityList.add(tagBaseEntity);
        levelTagEntityList.add(tagBaseEntity1);
        levelTagEntityList.add(tagBaseEntity2);
        levelTagEntityList.add(tagBaseEntity3);
        levelTagEntityList.add(tagBaseEntity4);

        //星级筛选
        mRvStarClass.setLayoutManager(new GridLayoutManager(ShoppingMallScreeningActivity.this, 4));
        ShopMallScreenStarClassAdapter huXingAdapter = new ShopMallScreenStarClassAdapter(ShoppingMallScreeningActivity.this, new ShopMallScreenStarClassAdapter.ShopMallScreenStarClassListener() {
            @Override
            public void setShopMallScreenStarClassListener(LevelTagEntity tagBaseBean) {

                tagBaseBean.getTitle();
            }
        });
        huXingAdapter.setTagList(levelTagEntityList);
        mRvStarClass.setAdapter(huXingAdapter);

        popupViews.add(mRvdropDownMenuList);
        popupViews.add(mViewDistanceCondition);
        popupViews.add(mViewStarPriceCondition);
      //  popupViews.add(mRvdropDownMenuList3);

        final View contentView = getLayoutInflater().inflate(R.layout.layout_shop_mall_dropdownmenu_condition, null);
        mRvHotel = contentView.findViewById(R.id.rv_hotel);
        mRvHomestay = contentView.findViewById(R.id.rv_homestay);

        LinearLayoutManager hotelLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        hotelLinearLayoutManager.setOrientation(hotelLinearLayoutManager.VERTICAL);
        mRvHotel.setLayoutManager(hotelLinearLayoutManager);
        hotelAdapter = new HotelAdapter(ShoppingMallScreeningActivity.this);
        hotelAdapter.setHotelList(hotelList);
        mRvHotel.setAdapter(hotelAdapter);


        LinearLayoutManager homestayLinearLayoutManager = new LinearLayoutManager(ShoppingMallScreeningActivity.this);
        homestayLinearLayoutManager.setOrientation(homestayLinearLayoutManager.VERTICAL);
        mRvHomestay.setLayoutManager(homestayLinearLayoutManager);
        homestayAdapter = new HomestayAdapter(ShoppingMallScreeningActivity.this, new HomestayAdapter.HomestayCollectionListener() {
            @Override
            public void setHomestayCollection(int position,int dataId) {

            }
        });
        homestayAdapter.setHomestayList(hotelList);
        mRvHomestay.setAdapter(homestayAdapter);

        mSmartRefreshLayout = contentView.findViewById(R.id.refresh_layout);
        mRlytNoData = contentView.findViewById(R.id.rlyt_no_data);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView, new DropDownMenu.DropDownMenuListener() {
            @Override
            public void HomestaySearchCallback(boolean isOpenHomestaySearch) {
                if(isOpenHomestaySearch)
                {
                    mchType = mchTypeArray[1];
                    mRvHomestay.setVisibility(View.VISIBLE);
                    mRvHotel.setVisibility(View.GONE);
                } else {
                    mchType = mchTypeArray[0];
                    mRvHomestay.setVisibility(View.GONE);
                    mRvHotel.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void initData() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        hotelList.clear();
                        if(isHomestaySearch){
                            homestayAdapter.notifyDataSetChanged();
                        } else {
                            hotelAdapter.notifyDataSetChanged();
                        }
                        getMchHotelList();

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
                            if (mTotalPageCount == hotelList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getMchHotelList();
                            }
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
    public void getHomestayScreeningConditionResult(BackResult<List<PositionDistanceSearchBean>> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    positionDistanceSearchOneLevelList = res.getData();
                    if (positionDistanceSearchOneLevelList != null && positionDistanceSearchOneLevelList.size() > 0) {
                        PositionDistanceSearchBean positionDistanceOneBean = positionDistanceSearchOneLevelList.get(0);
                        positionDistanceOneBean.setSelect(true);
                        dropDownMenuDistanceTypeOneLevelAdapter.setPositionDistanceList(positionDistanceSearchOneLevelList);
                        dropDownMenuDistanceTypeOneLevelAdapter.notifyDataSetChanged();
                        positionDistanceSearchTwoLevelList = positionDistanceOneBean.getPosition();

                        if (positionDistanceSearchTwoLevelList != null && positionDistanceSearchTwoLevelList.size() > 0 && positionDistanceSearchThreeLevelList != null && positionDistanceSearchThreeLevelList.size() > 0) {
                            PositionDistanceSearchBean positionDistanceTwoBean = positionDistanceSearchTwoLevelList.get(0);
                            positionDistanceTwoBean.setSelect(true);
                            dropDownMenuDistanceTypeSubTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
                            dropDownMenuDistanceTypeSubTwoLevelAdapter.notifyDataSetChanged();
                            dropDownMenuClassifyThreeLevelAdapter.setPositionDistanceList(positionDistanceSearchThreeLevelList);
                            dropDownMenuClassifyThreeLevelAdapter.notifyDataSetChanged();
                            //  mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.GONE);
                            mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.GONE);
                            mRvPositionDistanceClassifySubTwoLevelLinkage.setVisibility(View.VISIBLE);
                            mRvPositionDistanceClassifyThreeLevelLinkage.setVisibility(View.VISIBLE);

                        }
                        if (positionDistanceSearchTwoLevelList != null && positionDistanceSearchTwoLevelList.size() > 0 && positionDistanceSearchThreeLevelList == null || positionDistanceSearchThreeLevelList.size() == 0) {
                            PositionDistanceSearchBean positionDistanceTwoBean = positionDistanceSearchTwoLevelList.get(0);
                            positionDistanceTwoBean.setSelect(true);
                            dropDownMenuDistanceTypeTwoLevelAdapter.setPositionDistanceList(positionDistanceSearchTwoLevelList);
                            dropDownMenuDistanceTypeTwoLevelAdapter.notifyDataSetChanged();
                            mRvPositionDistanceClassifyThreeLevelLinkage.setVisibility(View.GONE);
                            mRvPositionDistanceClassifySubTwoLevelLinkage.setVisibility(View.GONE);
                            mRvPositionDistanceClassifyTwoLevelLinkage.setVisibility(View.VISIBLE);

                        }
                    }

                    getMchHotelList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallScreeningActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getMchHotelListResult(BackResult<HotelSearchResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    HotelSearchResponse hotelSearchResponse = res.getData();

                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();

                    } else {

                        hotelList.clear();
                        hotelAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<MchTypeBean> mchHotelList = hotelSearchResponse.getResult();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if(mchHotelList != null)
                    {
                        hotelList.addAll(mchHotelList);
                    }

                    if(isHomestaySearch)
                    {
                        homestayAdapter.setHomestayList(hotelList);
                    } else {
                        hotelAdapter.setHotelList(hotelList);
                    }

                    if(isOnLoadMore)
                    {
                        if(isHomestaySearch)
                        {
                            homestayAdapter.notifyDataSetChanged();
                        } else {
                            hotelAdapter.notifyDataSetChanged();
                        }

                    } else {

                        if(isHomestaySearch)
                        {
                            mRvHomestay.setAdapter(homestayAdapter);

                        }else{
                            mRvHotel.setAdapter(hotelAdapter);
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(ShoppingMallScreeningActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(ShoppingMallScreeningActivity.this,msg);
    }

    @OnClick({R.id.rlyt_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlyt_back:

                ShoppingMallScreeningActivity.this.finish();

                break;

            default:
                break;
        }
    }

    //酒店民宿筛选条件
    public void getHomestayScreeningCondition() {

        if (validateInternet()) {
            mPresenter.getHomestayScreeningCondition();
        }
    }

    public void getMchHotelList(){

        if (validateInternet()) {
            HashMap<String,String> map = new HashMap<>();
            if(!TextUtils.isEmpty(longitude))
            {
                map.put("longitude", longitude);    //(自己/目标)经度
            } else {
                map.put("longitude", String.valueOf(0));    //(自己/目标)经度
            }
            if(!TextUtils.isEmpty(latitude))
            {
                map.put("latitude", latitude);      //(自己/目标)纬度
            } else {
                map.put("latitude", String.valueOf(0));      //(自己/目标)纬度
            }
            map.put("sortType",sortValue);            //RECOMMEND(推荐好评)，LOW_PRICE(低价优先)，HIGH_PRICE(高价优先),NICE_COMMENT(好评优先)
           // map.put("level","");               //星级 "level":[1,2,3,4]
            //map.put("floorPrice","");          //最低价格
           // map.put("topPrice","");            //最高价格
            map.put("mchType2",mchType);            //商户类型(MCH_HOTEL1 酒店,MCH_HOTEL2 民宿,MCH_HOTEL3 客栈）
         //   map.put("peopleNumber","");        //人数
            //map.put("startDate","");           //时间区间 开始时间 (yyyy-MM-dd)
           // map.put("endDate","");             //时间区间 结束时间 (yyyy-MM-dd)
            map.put("content","宁波");             //内容
            map.put("distance",String.valueOf(distance));            //距离
           // map.put("countyId","");            //区县Id
            map.put("page",String.valueOf(mPageNo));                //当前页数
            map.put("pageSize",String.valueOf(pageSize));            //每页数量
            mPresenter.getMchHotelList(map);
        }
    }

    public void getPositionDistance(PositionDistanceSearchBean positionDistanceBean){

        if(positionDistanceBean != null)
        {
            distance = positionDistanceBean.getDistance();    //距离
            latitude = positionDistanceBean.getLatitude();    //经度
            longitude = positionDistanceBean.getLongitude();  //纬度
        }
    }

}
