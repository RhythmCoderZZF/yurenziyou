package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.BasicApplication;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FineFoodListAdapter;
import com.nbhysj.coupon.adapter.HomePageSearchFoodAdapter;
import com.nbhysj.coupon.adapter.HomePageSearchHotelAdapter;
import com.nbhysj.coupon.adapter.HomePageSearchStrategyAdapter;
import com.nbhysj.coupon.adapter.HomeSearchScenicSpotsListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.HomeSearchMchTypeEnum;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.greendao.DaoSession;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FavoritesCollectionResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.HomeSearchComprehensiveBean;
import com.nbhysj.coupon.model.response.HomeSearchMchTypeBean;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.model.response.SearchBean;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.ui.ScenicSpotDestinationActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.view.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @auther：hysj created on 2019/06/05
 * description：景点首页搜索
 */
public class HomeSearchComprehensiveFragment extends BaseFragment<HomePagePresenter, HomePageModel> implements HomePageContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int mPage = 1;
    private int mPageSize = 10;
    private HomeSearchScenicSpotsListAdapter popularScenicSpotsAdapter;
    //景点列表
    List<HomeSearchMchTypeBean> mHotScenicSpotList;

    //酒店列表
    private List<HomeSearchMchTypeBean> hotelList;

    //美食列表
    private List<HomeSearchMchTypeBean> fineFoodList;

    //攻略列表
    List<HomeSearchMchTypeBean> strategysList;
    //城市名
    @BindView(R.id.tv_city_name)
    TextView mTvCityName;
    @BindView(R.id.tv_city_tag)
    TextView mTvCityTag;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //当地热门景点
    @BindView(R.id.llyt_popular_scenic_spots)
    LinearLayout mLlytPopularScenicSpots;
    //当地热门景点列表
    @BindView(R.id.rv_popular_scenic_spots)
    RecyclerView mRvPopularScenicSpots;
    //当地热门景点
    @BindView(R.id.llyt_hotel)
    LinearLayout mLlytHotel;
    //美食
    @BindView(R.id.llyt_delicious_food)
    LinearLayout mLlytDeliciousFood;
    //攻略
    @BindView(R.id.llyt_strategy)
    LinearLayout mLlytStrategy;
    //城市
    @BindView(R.id.rlyt_city)
    RelativeLayout mRlytCity;
    //酒店
    @BindView(R.id.rv_hotel)
    RecyclerView mRvHotel;
    //城市
    @BindView(R.id.img_city)
    RoundedImageView mImgCity;

    //美食列表
    @BindView(R.id.rv_delicious_food)
    RecyclerView mRvDeliciousFood;

    //攻略列表
    @BindView(R.id.rv_strategy)
    RecyclerView mRvStrategy;

    //历史记录标签
    @BindView(R.id.llyt_historical_label)
    LinearLayout mLlytHistoricalLabel;

    //历史标签
    @BindView(R.id.flowlayout_historical_label)
    TagFlowLayout mTagHistoryLabel;

    //搜索结果
    @BindView(R.id.llyt_search_result)
    LinearLayout mLlytSearchResult;

    //暂无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;

    //商品类型
    private String mchType = HomeSearchMchTypeEnum.ALL.getValue();
    //关键字
    private String keyWord = HomeSearchMchTypeEnum.ALL.getValue();
    int mTotalPageCount;

    private boolean visibleToUser;

    //搜索关键字
    String searchKeyWord;
    private HomePageSearchHotelAdapter mHotelAdapter;

    private HomePageSearchFoodAdapter fineFoodListAdapter;

    private HomePageSearchStrategyAdapter homeSearchStrategyListAdapter;

    private boolean isExistSearchContent = false;

    private List<HomeSearchComprehensiveBean> homeSearchComprehensiveList;

    private TagAdapter mTagAdapter;

    public HomeSearchComprehensiveFragment() {
        // Required empty public constructor
    }

    public static HomeSearchComprehensiveFragment newInstance(String param1, String param2) {
        HomeSearchComprehensiveFragment fragment = new HomeSearchComprehensiveFragment();
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

        EventBus.getDefault().register(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_homepage_search_list;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);

    }

    @Override
    public void initView(View v) {
        keyWord = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.SEARCH_KEYWORD, "");

        if(homeSearchComprehensiveList == null)
        {
            homeSearchComprehensiveList = new ArrayList<>();

        } else {
            homeSearchComprehensiveList.clear();
        }

        if (mHotScenicSpotList == null) {
            mHotScenicSpotList = new ArrayList<>();
        } else {
            mHotScenicSpotList.clear();
        }

        if (hotelList == null) {

            hotelList = new ArrayList<>();
        } else {

            hotelList.clear();
        }

        if (fineFoodList == null) {
            fineFoodList = new ArrayList<>();
        } else {

            fineFoodList.clear();
        }

        if (strategysList == null) {
            strategysList = new ArrayList<>();
        } else {

            strategysList.clear();
        }

      /*  isInitView = true;
        isCanLoadData();*/

        mTvCityTag.getBackground().setAlpha(50);

        //风景
        LinearLayoutManager scenicSpotsLinearLayoutManager = new LinearLayoutManager(getActivity());
        scenicSpotsLinearLayoutManager.setOrientation(scenicSpotsLinearLayoutManager.HORIZONTAL);
        mRvPopularScenicSpots.setLayoutManager(scenicSpotsLinearLayoutManager);
        popularScenicSpotsAdapter = new HomeSearchScenicSpotsListAdapter(getActivity());
        popularScenicSpotsAdapter.setPopularScenicSpotsList(mHotScenicSpotList);
        mRvPopularScenicSpots.setAdapter(popularScenicSpotsAdapter);

        //美食
        LinearLayoutManager hotelReputationLinearLayout = new LinearLayoutManager(getActivity());
        hotelReputationLinearLayout.setOrientation(hotelReputationLinearLayout.VERTICAL);
        mRvHotel.setLayoutManager(hotelReputationLinearLayout);
        mHotelAdapter = new HomePageSearchHotelAdapter(getActivity());
        mHotelAdapter.setHotelList(hotelList);
        mRvHotel.setAdapter(mHotelAdapter);

        //美食
        LinearLayoutManager deliciousFoodLinearLayout = new LinearLayoutManager(getActivity());
        deliciousFoodLinearLayout.setOrientation(deliciousFoodLinearLayout.VERTICAL);
        mRvDeliciousFood.setLayoutManager(deliciousFoodLinearLayout);
        fineFoodListAdapter = new HomePageSearchFoodAdapter(getActivity());
        fineFoodListAdapter.setFineFoodList(fineFoodList);
        mRvDeliciousFood.setAdapter(fineFoodListAdapter);

        //攻略
        LinearLayoutManager strategyLinearLayout = new LinearLayoutManager(getActivity());
        strategyLinearLayout.setOrientation(strategyLinearLayout.VERTICAL);
        mRvStrategy.setLayoutManager(strategyLinearLayout);
        homeSearchStrategyListAdapter = new HomePageSearchStrategyAdapter(getActivity());
        homeSearchStrategyListAdapter.setStrategyList(strategysList);
        mRvStrategy.setAdapter(homeSearchStrategyListAdapter);


        mTagAdapter = new TagAdapter<HomeSearchComprehensiveBean>(homeSearchComprehensiveList) {

            @Override
            public View getView(FlowLayout parent, int position, HomeSearchComprehensiveBean homeSearchComprehensiveBean) {
                LayoutInflater mInflater = LayoutInflater.from(getActivity());
                TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_gray_frame,
                        mTagHistoryLabel, false);
                String homeSearchComprehensiveStr = homeSearchComprehensiveBean.getSearch();
                tagName.setText(homeSearchComprehensiveStr);
                return tagName;
            }
        };


        mTagHistoryLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                //    showToast(DestinationSearchActivity.this,historyLabelList.get(position));
                //toActivity(ScenicSpotDestinationActivity.class);
                HomeSearchComprehensiveBean homeSearchComprehensive = homeSearchComprehensiveList.get(position);
                keyWord = homeSearchComprehensive.getSearch();
                EventBus.getDefault().post(keyWord);
                getHomePageSearchAll();
                return false;
            }
        });

        mTagHistoryLabel.setAdapter(mTagAdapter);
    }

    @Override
    public void initData() {

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        try {

                            getHomePageSearchAll();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 200);
            }
        });
    }

    @Override
    public void lazyInitView(View view) {
        keyWord = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.SEARCH_KEYWORD, "");
        showProgressDialog(getActivity());
        getHomePageSearchAll();

    }

    @Override
    public void getHomePageIndexResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void queryByTopicResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void getHomeAttentionResult(BackResult<HomePageResponse> res) {

    }

    @Override
    public void getPostInfoResult(BackResult<PostInfoDetailResponse> res) {

    }

    @Override
    public void postOprateResult(BackResult<PraiseOrCollectResponse> res) {

    }

    @Override
    public void postsCommentResult(BackResult res) {

    }

    @Override
    public void postCollectionResult(BackResult<FavoritesCollectionResponse> res) {

    }

    @Override
    public void getFavoritesListResult(BackResult<FavoritesListResponse> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {

    }

    @Override
    public void getHomePageSearchAllResult(BackResult<HomePageAllSearchResponse> res) {
        dismissProgressDialog();
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    mSmartRefreshLayout.finishRefresh();
                    mSmartRefreshLayout.setNoMoreData(false);
                    HomePageAllSearchResponse homePageAllSearchResponse = res.getData();
                    mHotScenicSpotList = homePageAllSearchResponse.getScenics();
                    List<HomePageAllSearchResponse.CityEntity> cityEntityList = homePageAllSearchResponse.getCitys();

                    if (cityEntityList != null && cityEntityList.size() > 0) {
                        mRlytCity.setVisibility(View.VISIBLE);
                        HomePageAllSearchResponse.CityEntity cityEntity = cityEntityList.get(0);
                        String bannerUrl = cityEntity.getBanner();
                        String name = cityEntity.getName();
                        mTvCityName.setText(name);
                        GlideUtil.loadImage(getActivity(), bannerUrl, mImgCity);

                    } else {
                        mRlytCity.setVisibility(View.GONE);
                    }

                    if (mHotScenicSpotList != null && mHotScenicSpotList.size() > 0) {
                        mLlytPopularScenicSpots.setVisibility(View.VISIBLE);
                        popularScenicSpotsAdapter.setPopularScenicSpotsList(mHotScenicSpotList);
                        popularScenicSpotsAdapter.notifyDataSetChanged();
                    } else {
                        mLlytPopularScenicSpots.setVisibility(View.GONE);
                    }

                    hotelList = homePageAllSearchResponse.getHotels();
                    if (hotelList != null && hotelList.size() > 0) {
                        mLlytHotel.setVisibility(View.VISIBLE);
                        mHotelAdapter.setHotelList(hotelList);
                        mHotelAdapter.notifyDataSetChanged();
                    } else {
                        mLlytHotel.setVisibility(View.GONE);
                    }

                    fineFoodList = homePageAllSearchResponse.getFoods();
                    if (fineFoodList != null && fineFoodList.size() > 0) {
                        mLlytDeliciousFood.setVisibility(View.VISIBLE);
                        fineFoodListAdapter.setFineFoodList(fineFoodList);
                        fineFoodListAdapter.notifyDataSetChanged();
                    } else {
                        mLlytDeliciousFood.setVisibility(View.GONE);
                    }

                    strategysList = homePageAllSearchResponse.getStrategys();
                    if (strategysList != null && strategysList.size() > 0) {
                        mLlytStrategy.setVisibility(View.VISIBLE);
                        homeSearchStrategyListAdapter.setStrategyList(strategysList);
                        homeSearchStrategyListAdapter.notifyDataSetChanged();
                    } else {
                        mLlytStrategy.setVisibility(View.GONE);
                    }
                    String all = HomeSearchMchTypeEnum.ALL.getValue();
                    if (!TextUtils.isEmpty(keyWord)) {
                        if (keyWord.equals(all)) {
                            mRlytNoData.setVisibility(View.GONE);
                            mLlytHistoricalLabel.setVisibility(View.VISIBLE);
                            mLlytSearchResult.setVisibility(View.GONE);

                            homeSearchComprehensiveList = queryAll();

                            if (homeSearchComprehensiveList != null && homeSearchComprehensiveList.size() > 0) {

                                mTagAdapter = new TagAdapter<HomeSearchComprehensiveBean>(homeSearchComprehensiveList) {

                                    @Override
                                    public View getView(FlowLayout parent, int position, HomeSearchComprehensiveBean homeSearchComprehensiveBean) {
                                        LayoutInflater mInflater = LayoutInflater.from(getActivity());
                                        TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_gray_frame,
                                                mTagHistoryLabel, false);
                                        String homeSearchComprehensiveStr = homeSearchComprehensiveBean.getSearch();
                                        tagName.setText(homeSearchComprehensiveStr);
                                        return tagName;
                                    }
                                };
                            }
                            //mTagAdapter.notifyDataChanged();
                            mTagHistoryLabel.setAdapter(mTagAdapter);
                        } else {
                            addSearchRecordData(keyWord);
                        }
                    }
                    if (cityEntityList.size() == 0 && mHotScenicSpotList.size() == 0 && hotelList.size() == 0 && fineFoodList.size() == 0 && strategysList.size() == 0) {

                        mLlytSearchResult.setVisibility(View.GONE);

                        if(keyWord.equals(all)){
                            mLlytHistoricalLabel.setVisibility(View.VISIBLE);
                        } else {
                            mLlytHistoricalLabel.setVisibility(View.GONE);
                        }

                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else if (mHotScenicSpotList != null && mHotScenicSpotList.size() > 0 || hotelList != null && hotelList.size() > 0 || fineFoodList != null && fineFoodList.size() > 0 || strategysList != null && strategysList.size() > 0) {

                        mLlytSearchResult.setVisibility(View.VISIBLE);
                        mLlytHistoricalLabel.setVisibility(View.GONE);
                        mRlytNoData.setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(getActivity(), res.getMsg());
                break;
        }
    }

    @Override
    public void getHomePageSearchByTypeResult(BackResult<HomePageTypeSearchResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));

    }

    private void addSearchRecordData(String keyword) {
        homeSearchComprehensiveList = queryAll();
        for (HomeSearchComprehensiveBean homeSearchComprehensiveBean : homeSearchComprehensiveList) {

            String searchRecord = homeSearchComprehensiveBean.getSearch();
            if (searchRecord.equals(keyword)) {
                isExistSearchContent = true;
            }
        }
        DaoSession daoSession = ((BasicApplication) getActivity().getApplication()).getDaoSession();
        //是否存在搜索内容 不存在的情况
        if (!isExistSearchContent) {
            if (homeSearchComprehensiveList != null && homeSearchComprehensiveList.size() > 5) {
                HomeSearchComprehensiveBean homeSearchComprehensiveBean = homeSearchComprehensiveList.get(homeSearchComprehensiveList.size() - 1);
                daoSession.delete(homeSearchComprehensiveBean);
            }
            HomeSearchComprehensiveBean searchBean = new HomeSearchComprehensiveBean();
            searchBean.setSearch(keyword);
            daoSession.insert(searchBean);
        }
    }

    public void getHomePageSearchAll() {

        if (validateInternet()) {

            if (TextUtils.isEmpty(keyWord)) {
                keyWord = HomeSearchMchTypeEnum.ALL.getValue();
            }
            mPresenter.getHomePageSearchAll(mchType, keyWord);
        }
    }

 /*   private void addSearchRecordData(String keyword) {
        DaoSession daoSession = ((BasicApplication) getActivity().getApplication()).getDaoSession();
        HomeSearchComprehensiveBean searchBean = new HomeSearchComprehensiveBean();
        searchBean.setSearch(keyword);
        daoSession.insert(searchBean);
    }*/

    public void deleteAll() {
        DaoSession daoSession = ((BasicApplication) getActivity().getApplication()).getDaoSession();
        daoSession.deleteAll(HomeSearchComprehensiveBean.class);
    }

    //查询全部搜索记录
    public List<HomeSearchComprehensiveBean> queryAll() {
        List<HomeSearchComprehensiveBean> searchs = ((BasicApplication) getActivity().getApplication()).getDaoSession().loadAll(HomeSearchComprehensiveBean.class);
        return searchs;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibleToUser = isVisibleToUser;
    }

    @Subscribe
    public void onEvent(String searchkeyWordStr) {

        if (visibleToUser) {
            keyWord = searchkeyWordStr;
            if (!TextUtils.isEmpty(keyWord)) {
                getHomePageSearchAll();
            } else {
                mRlytNoData.setVisibility(View.GONE);
                mLlytHistoricalLabel.setVisibility(View.VISIBLE);
                mLlytSearchResult.setVisibility(View.GONE);
                homeSearchComprehensiveList = queryAll();
                if (homeSearchComprehensiveList != null && homeSearchComprehensiveList.size() > 0) {

                    mTagHistoryLabel.setVisibility(View.VISIBLE);

                    mTagAdapter = new TagAdapter<HomeSearchComprehensiveBean>(homeSearchComprehensiveList) {

                        @Override
                        public View getView(FlowLayout parent, int position, HomeSearchComprehensiveBean homeSearchComprehensiveBean) {
                            LayoutInflater mInflater = LayoutInflater.from(getActivity());
                            TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_gray_frame,
                                    mTagHistoryLabel, false);
                            String homeSearchComprehensiveStr = homeSearchComprehensiveBean.getSearch();
                            tagName.setText(homeSearchComprehensiveStr);
                            return tagName;
                        }
                    };
                    mTagHistoryLabel.setAdapter(mTagAdapter);
                    mTagHistoryLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                            //    showToast(DestinationSearchActivity.this,historyLabelList.get(position));
                            //toActivity(ScenicSpotDestinationActivity.class);

                            HomeSearchComprehensiveBean homeSearchComprehensive = homeSearchComprehensiveList.get(position);
                            keyWord = homeSearchComprehensive.getSearch();
                            EventBus.getDefault().post(keyWord);
                            getHomePageSearchAll();

                            return false;
                        }
                    });
                } else {
                    mTagHistoryLabel.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.image_history_record_clear})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.image_history_record_clear:
                deleteAll();
                homeSearchComprehensiveList = queryAll();
                if (homeSearchComprehensiveList != null && homeSearchComprehensiveList.size() > 0) {

                    mTagHistoryLabel.setVisibility(View.VISIBLE);
                    mTagHistoryLabel.setAdapter(new TagAdapter<HomeSearchComprehensiveBean>(homeSearchComprehensiveList) {

                        @Override
                        public View getView(FlowLayout parent, int position, HomeSearchComprehensiveBean homeSearchComprehensiveBean) {
                            LayoutInflater mInflater = LayoutInflater.from(getActivity());
                            TextView tagName = (TextView) mInflater.inflate(R.layout.layout_flowlayout_tag_gray_frame,
                                    mTagHistoryLabel, false);
                            String homeSearchComprehensiveStr = homeSearchComprehensiveBean.getSearch();
                            tagName.setText(homeSearchComprehensiveStr);
                            return tagName;
                        }
                    });

                    mTagHistoryLabel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                            //    showToast(DestinationSearchActivity.this,historyLabelList.get(position));
                            //toActivity(ScenicSpotDestinationActivity.class);
                            HomeSearchComprehensiveBean homeSearchComprehensive = homeSearchComprehensiveList.get(position);
                            keyWord = homeSearchComprehensive.getSearch();
                            getHomePageSearchAll();

                            return false;
                        }
                    });
                } else {
                    mTagHistoryLabel.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }
}
