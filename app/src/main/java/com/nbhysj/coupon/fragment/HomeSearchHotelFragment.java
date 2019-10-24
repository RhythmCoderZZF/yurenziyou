package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.HomePageSearchHotelAdapter;
import com.nbhysj.coupon.adapter.HomeSearchScenicSpotsListAdapter;
import com.nbhysj.coupon.adapter.HotelAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.common.Enum.HomeSearchMchTypeEnum;
import com.nbhysj.coupon.contract.HomePageContract;
import com.nbhysj.coupon.model.HomePageModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.FavoritesCollectionResponse;
import com.nbhysj.coupon.model.response.FavoritesListResponse;
import com.nbhysj.coupon.model.response.FollowUserStatusResponse;
import com.nbhysj.coupon.model.response.HomePageAllSearchResponse;
import com.nbhysj.coupon.model.response.HomePageResponse;
import com.nbhysj.coupon.model.response.HomePageTypeSearchResponse;
import com.nbhysj.coupon.model.response.HomeSearchMchTypeBean;
import com.nbhysj.coupon.model.response.MchTypeBean;
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.model.response.StrategyBean;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.ui.StrategyActivity;
import com.nbhysj.coupon.util.GlideUtil;
import com.nbhysj.coupon.view.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/10/21
 * description：景点首页搜索酒店
 */
public class HomeSearchHotelFragment extends BaseFragment<HomePagePresenter, HomePageModel> implements HomePageContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private boolean isOnLoadMore = false;

    private int mPageNo = 1;
    private int mPageSize = 10;
    //总条数
    private int mTotalPageCount;
    //酒店列表
    private List<HomeSearchMchTypeBean> homeSearchMchTypeList;

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //当地热门景点
    //酒店
    @BindView(R.id.rv_hotel_list)
    RecyclerView mRvHotel;

    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;

    //商品类型
    private String mchType = HomeSearchMchTypeEnum.MCH_HOTEL.getValue();
    //关键字
    private String keyWord = "宁波";

    private HomePageSearchHotelAdapter homePageSearchHotelAdapter;

    public HomeSearchHotelFragment() {
        // Required empty public constructor
    }

    public static HomeSearchHotelFragment newInstance(String param1, String param2) {
        HomeSearchHotelFragment fragment = new HomeSearchHotelFragment();
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
        return R.layout.fragment_home_page_hotel_search;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);

    }

    @Override
    public void initView(View v) {

        if(homeSearchMchTypeList == null){

            homeSearchMchTypeList = new ArrayList<>();
        } else {

            homeSearchMchTypeList.clear();
        }

        LinearLayoutManager hotelReputationLinearLayout = new LinearLayoutManager(getActivity());
        hotelReputationLinearLayout.setOrientation(hotelReputationLinearLayout.VERTICAL);
        mRvHotel.setLayoutManager(hotelReputationLinearLayout);
        homePageSearchHotelAdapter = new HomePageSearchHotelAdapter(getActivity());
        homePageSearchHotelAdapter.setHotelList(homeSearchMchTypeList);
        mRvHotel.setAdapter(homePageSearchHotelAdapter);
    }

    @Override
    public void initData() {
        showProgressDialog(getActivity());
        getHomePageSearchByType();
        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        homeSearchMchTypeList.clear();
                        homePageSearchHotelAdapter.notifyDataSetChanged();
                        showProgressDialog(getActivity());
                        getHomePageSearchByType();

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
                            if (mTotalPageCount == homeSearchMchTypeList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getHomePageSearchByType();
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
    public void getHomePageSearchAllResult(BackResult<HomePageAllSearchResponse> res) {

    }

    @Override
    public void userFollowResult(BackResult<FollowUserStatusResponse> res) {

    }

    @Override
    public void getHomePageSearchByType(BackResult<HomePageTypeSearchResponse> res) {
        dismissProgressDialog();
        if (mSmartRefreshLayout != null)
        {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    HomePageTypeSearchResponse homePageTypeSearchResponse = res.getData();

                    BasePaginationResult paginationResult = homePageTypeSearchResponse.getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<HomeSearchMchTypeBean> hotelList = homePageTypeSearchResponse.getResult();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (hotelList != null)
                    {
                        homeSearchMchTypeList.addAll(hotelList);
                    }

                    homePageSearchHotelAdapter.setHotelList(homeSearchMchTypeList);
                    homePageSearchHotelAdapter.notifyDataSetChanged();


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
    public void showMsg(String msg) {

        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));

    }

    //首页搜索类型查询
    public void getHomePageSearchByType() {

        if (validateInternet()) {

            mPresenter.getHomePageSearchByType(mchType,keyWord,mPageNo,mPageSize);
        }
    }

}
