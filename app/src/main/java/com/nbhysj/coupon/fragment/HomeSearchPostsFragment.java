package com.nbhysj.coupon.fragment;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.HomePageSearchPostsAdapter;
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
import com.nbhysj.coupon.ui.PostRecommendDetailActivity;
import com.nbhysj.coupon.util.SharedPreferencesUtils;
import com.nbhysj.coupon.util.Tools;
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
 * @auther：hysj created on 2019/10/21
 * description：景点首页搜索帖子
 */
public class HomeSearchPostsFragment extends BaseFragment<HomePagePresenter, HomePageModel> implements HomePageContract.View {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int mPageNo = 1;
    private int mPageSize = 10;
    private HomePageSearchPostsAdapter homePageSearchPostsAdapter;
    //帖子列表
    private List<HomeSearchMchTypeBean> homePagePostsSearchList;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    //商户类型
    @BindView(R.id.rv_mch_type_list)
    RecyclerView mRvMchTypeList;

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;

    //商品类型
    private String mchType = HomeSearchMchTypeEnum.POST.getValue();
    //关键字
    private String keyWord = HomeSearchMchTypeEnum.ALL.getValue();
    int mTotalPageCount;
    private boolean visibleToUser;
    private boolean isOnLoadMore = false;


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
        EventBus.getDefault().register(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_page_posts_search;
    }

    @Override
    public void initPresenter() {

        mPresenter.setVM(this, mModel);

    }

    @Override
    public void initView(View v) {

        if(homePagePostsSearchList == null){

            homePagePostsSearchList = new ArrayList<>();
        } else {

            homePagePostsSearchList.clear();
        }

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
        mRvMchTypeList.setLayoutManager(staggeredGridLayoutManager);
        mRvMchTypeList.setHasFixedSize(true);
        mRvMchTypeList.setItemViewCacheSize(10);
        mRvMchTypeList.addItemDecoration(new RecyclerViewItemDecoration(Tools.dip2px(getActivity(), 10)));
        mRvMchTypeList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        // linearLayoutManager.setAutoMeasureEnabled(true);
        homePageSearchPostsAdapter = new HomePageSearchPostsAdapter(getActivity(), new HomePageSearchPostsAdapter.RecommendPostsDetailListener() {
            @Override
            public void lookRecommendPostDetailListener(int mPosition) {

                HomeSearchMchTypeBean homePageSubTopicTagBean = homePagePostsSearchList.get(mPosition);
                String postId = homePageSubTopicTagBean.getId();
                Intent intent = new Intent();
                intent.putExtra("postId", Integer.parseInt(postId));
                intent.setClass(getActivity(), PostRecommendDetailActivity.class);
                startActivity(intent);

            }

            @Override
            public void setPostIsCollectionListener(int mPosition) {


            }
        });
        homePageSearchPostsAdapter.setRecommendFriendsPictureList(homePagePostsSearchList);
        mRvMchTypeList.setAdapter(homePageSearchPostsAdapter);
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
                        homePagePostsSearchList.clear();
                        homePageSearchPostsAdapter.notifyDataSetChanged();
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
                            if (mTotalPageCount == homePagePostsSearchList.size()) {
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
    public void getUnReadMessageListResult(BackResult<Integer> res) {

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

                        homePagePostsSearchList.clear();
                        homePageSearchPostsAdapter.notifyDataSetChanged();
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
                        homePagePostsSearchList.addAll(hotelList);
                    }

                    homePageSearchPostsAdapter.setRecommendFriendsPictureList(homePagePostsSearchList);
                    homePageSearchPostsAdapter.notifyDataSetChanged();


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

        if(visibleToUser)
        {
            keyWord = searchkeyWordStr;

            getHomePageSearchByType();
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 为RecyclerView增加间距
     * 预设2列，如果是3列，则左右值不同
     */
    public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
        private int space = 0;
        private int pos;

        public RecyclerViewItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            outRect.top = space;

            //该View在整个RecyclerView中位置。
            pos = parent.getChildAdapterPosition(view);

            //取模

            //两列的左边一列
            if (pos % 2 == 0) {
                outRect.left = space;
                outRect.right = space / 2;
            }

            //两列的右边一列
            if (pos % 2 == 1) {
                outRect.left = space / 2;
                outRect.right = space;
            }
        }
    }

}
