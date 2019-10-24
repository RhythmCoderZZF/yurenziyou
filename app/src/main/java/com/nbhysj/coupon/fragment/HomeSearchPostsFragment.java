package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.HomePageSearchHotelAdapter;
import com.nbhysj.coupon.adapter.HomeSearchScenicSpotsListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.HomeSearchMchTypeEnum;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.FavoritesCollectionResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.HomeSearchMchTypeBean;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/10/21
 * description：景点首页搜索帖子
 */
public class HomeSearchPostsFragment extends BaseFragment<HomePagePresenter, HomePageModel> implements HomePageContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int mPage = 1;
    private int mPageSize = 10;
    private HomeSearchScenicSpotsListAdapter popularScenicSpotsAdapter;
    //景点列表
    List<HomeSearchMchTypeBean> mHotScenicSpotList;
    //酒店列表
    private List<HomeSearchMchTypeBean> hotelList;
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
    //酒店
    @BindView(R.id.rv_hotel)
    RecyclerView mRvHotel;
    //城市
    @BindView(R.id.img_city)
    RoundedImageView mImgCity;

    //商品类型
    private String mchType = HomeSearchMchTypeEnum.POST.getValue();
    //关键字
    private String keyWord = "宁波";
    int mTotalPageCount;

    private HomePageSearchHotelAdapter mHotelAdapter;

    public HomeSearchPostsFragment() {
        // Required empty public constructor
    }

    public static HomeSearchPostsFragment newInstance(String param1, String param2) {
        HomeSearchPostsFragment fragment = new HomeSearchPostsFragment();
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
        return R.layout.fragment_homepage_search_list;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);

    }

    @Override
    public void initView(View v) {

        if (mHotScenicSpotList == null) {
            mHotScenicSpotList = new ArrayList<>();
        } else {
            mHotScenicSpotList.clear();
        }

        if(hotelList == null){

            hotelList = new ArrayList<>();
        } else {

            hotelList.clear();
        }

      /*  isInitView = true;
        isCanLoadData();*/

        mTvCityTag.getBackground().setAlpha(50);

        LinearLayoutManager scenicSpotsLinearLayoutManager = new LinearLayoutManager(getActivity());
        scenicSpotsLinearLayoutManager.setOrientation(scenicSpotsLinearLayoutManager.HORIZONTAL);
        mRvPopularScenicSpots.setLayoutManager(scenicSpotsLinearLayoutManager);
        popularScenicSpotsAdapter = new HomeSearchScenicSpotsListAdapter(getActivity());
        popularScenicSpotsAdapter.setPopularScenicSpotsList(mHotScenicSpotList);
        mRvPopularScenicSpots.setAdapter(popularScenicSpotsAdapter);

        LinearLayoutManager hotelReputationLinearLayout = new LinearLayoutManager(getActivity());
        hotelReputationLinearLayout.setOrientation(hotelReputationLinearLayout.VERTICAL);
        mRvHotel.setLayoutManager(hotelReputationLinearLayout);
        mHotelAdapter = new HomePageSearchHotelAdapter(getActivity());
        mHotelAdapter.setHotelList(hotelList);
        mRvHotel.setAdapter(mHotelAdapter);
    }

    @Override
    public void initData() {
        showProgressDialog(getActivity());
        getHomePageSearchAll();
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
                    HomePageAllSearchResponse homePageAllSearchResponse = res.getData();
                    mHotScenicSpotList = homePageAllSearchResponse.getScenics();
                    List<HomePageAllSearchResponse.CityEntity> cityEntityList = homePageAllSearchResponse.getCitys();

                    if(cityEntityList != null)
                    {
                        HomePageAllSearchResponse.CityEntity cityEntity = cityEntityList.get(0);
                        String bannerUrl = cityEntity.getBanner();
                        String name = cityEntity.getName();
                        mTvCityName.setText(name);
                        GlideUtil.loadImage(getActivity(), bannerUrl, mImgCity);
                    }

                    if(mHotScenicSpotList != null)
                    {
                        mLlytPopularScenicSpots.setVisibility(View.VISIBLE);
                        popularScenicSpotsAdapter.setPopularScenicSpotsList(mHotScenicSpotList);
                        popularScenicSpotsAdapter.notifyDataSetChanged();
                    } else {
                        mLlytPopularScenicSpots.setVisibility(View.GONE);
                    }

                    hotelList = homePageAllSearchResponse.getHotels();
                    if(hotelList != null) {
                        mLlytHotel.setVisibility(View.VISIBLE);
                        mHotelAdapter.setHotelList(hotelList);
                        mHotelAdapter.notifyDataSetChanged();
                    } else {
                        mLlytHotel.setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
                default:
                    showToast(getActivity(),res.getMsg());
                    break;
        }
    }

    @Override
    public void getHomePageSearchByType(BackResult<HomePageTypeSearchResponse> res) {

    }

    @Override
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));

    }

    public void getHomePageSearchAll() {

        if (validateInternet()) {

            mPresenter.getHomePageSearchAll(mchType,keyWord);
        }
    }

}
