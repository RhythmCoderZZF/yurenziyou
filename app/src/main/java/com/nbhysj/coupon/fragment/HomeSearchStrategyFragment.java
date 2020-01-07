package com.nbhysj.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.HomePageSearchRecreationAdapter;
import com.nbhysj.coupon.adapter.HomePageSearchStrategyAdapter;
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
import com.nbhysj.coupon.model.response.PostInfoDetailResponse;
import com.nbhysj.coupon.model.response.PraiseOrCollectResponse;
import com.nbhysj.coupon.presenter.HomePagePresenter;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @auther：hysj created on 2019/10/27
 * description：景点首页搜索攻略
 */
public class HomeSearchStrategyFragment extends BaseFragment<HomePagePresenter, HomePageModel> implements HomePageContract.View {
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

    //商户类型列表
    @BindView(R.id.rv_mch_type_list)
    RecyclerView mRvMchTypeList;

    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;

    //商品(景点)类型
    private String mchType = HomeSearchMchTypeEnum.STRATEGY.getValue();
    //关键字
    private String keyWord = HomeSearchMchTypeEnum.ALL.getValue();

    private HomePageSearchStrategyAdapter homePageSearchStrategyAdapter;

    private boolean visibleToUser;
    public HomeSearchStrategyFragment() {
        // Required empty public constructor
    }

    public static HomeSearchStrategyFragment newInstance(String param1, String param2) {
        HomeSearchStrategyFragment fragment = new HomeSearchStrategyFragment();
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
        return R.layout.fragment_home_page_mch_search;
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
        mRvMchTypeList.setLayoutManager(hotelReputationLinearLayout);
        homePageSearchStrategyAdapter = new HomePageSearchStrategyAdapter(getActivity());
        homePageSearchStrategyAdapter.setStrategyList(homeSearchMchTypeList);
        mRvMchTypeList.setAdapter(homePageSearchStrategyAdapter);
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
                        homeSearchMchTypeList.clear();
                        homePageSearchStrategyAdapter.notifyDataSetChanged();
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
        keyWord = (String) SharedPreferencesUtils.getData(SharedPreferencesUtils.SEARCH_KEYWORD,"");
        showProgressDialog(getActivity());
        getHomePageSearchByType();
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
    public void getHomePageSearchByTypeResult(BackResult<HomePageTypeSearchResponse> res) {
        dismissProgressDialog();
        if (mSmartRefreshLayout != null)
        {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {
                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();

                    } else {

                        homeSearchMchTypeList.clear();
                        homePageSearchStrategyAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

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

                    homePageSearchStrategyAdapter.setStrategyList(homeSearchMchTypeList);
                    homePageSearchStrategyAdapter.notifyDataSetChanged();

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
        if (mSmartRefreshLayout != null)
        {
            mSmartRefreshLayout.finishRefresh();
        }
        dismissProgressDialog();
        showToast(getActivity(), Constants.getResultMsg(msg));

    }

    //首页搜索类型查询
    public void getHomePageSearchByType() {

        if (validateInternet()) {
            if(TextUtils.isEmpty(keyWord)) {

                keyWord = HomeSearchMchTypeEnum.ALL.getValue();
            }
            mPresenter.getHomePageSearchByType(mchType,keyWord,mPageNo,mPageSize);
        }
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
            if(TextUtils.isEmpty(keyWord))
            {
                keyWord = HomeSearchMchTypeEnum.ALL.getValue();
            }
            getHomePageSearchByType();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getUnReadMessageListResult(BackResult<Integer> res) {

    }
}
