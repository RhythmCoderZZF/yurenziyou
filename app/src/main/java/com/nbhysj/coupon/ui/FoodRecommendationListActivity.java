package com.nbhysj.coupon.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbhysj.coupon.R;
import com.nbhysj.coupon.adapter.FineFoodRecommendListAdapter;
import com.nbhysj.coupon.common.Constants;
import com.nbhysj.coupon.contract.FineFoodContract;
import com.nbhysj.coupon.model.FineFoodModel;
import com.nbhysj.coupon.model.response.BackResult;
import com.nbhysj.coupon.model.response.BasePaginationResult;
import com.nbhysj.coupon.model.response.FineFoodCommentInitResponse;
import com.nbhysj.coupon.model.response.FoodRecommendListResponse;
import com.nbhysj.coupon.model.response.MchBangDanRankingResponse;
import com.nbhysj.coupon.model.response.MchCateListResponse;
import com.nbhysj.coupon.model.response.MchCollectionResponse;
import com.nbhysj.coupon.model.response.MchFoodBean;
import com.nbhysj.coupon.model.response.MchFoodDetailResponse;
import com.nbhysj.coupon.model.response.ScenicSpotHomePageResponse;
import com.nbhysj.coupon.model.response.ScenicSpotResponse;
import com.nbhysj.coupon.presenter.FineFoodPresenter;
import com.nbhysj.coupon.statusbar.StatusBarCompat;
import com.nbhysj.coupon.util.ToolbarHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @auther：hysj created on 2019/03/08
 * description：推荐菜查看更多
 */
public class FoodRecommendationListActivity extends BaseActivity<FineFoodPresenter, FineFoodModel> implements FineFoodContract.View {
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    //赞无数据
    @BindView(R.id.rlyt_no_data)
    RelativeLayout mRlytNoData;
    //美食推荐列表
    @BindView(R.id.rv_delicious_food_recommendation_list)
    RecyclerView mRvFoodRecommendList;

    private int mPageNo = 1;
    private int mPageSize = 10;

    private FineFoodRecommendListAdapter fineFoodRecommendListAdapter;

    private List<MchFoodBean> mchFoodBeanList;
    private boolean isOnLoadMore = false;

    //总条数
    private int mTotalPageCount;

    private int mchId;

    View header;

    //网页推荐菜品数量
    private TextView mTvFineFoodRecommendNum;

    @Override
    public int getLayoutId() {
        StatusBarCompat.setStatusBarColor(this, -131077);
        return R.layout.activity_food_recommend_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        ToolbarHelper.setBar(FoodRecommendationListActivity.this, getResources().getString(R.string.str_food_recommend), R.mipmap.icon_left_arrow_black);

        mchId = getIntent().getIntExtra("mchId",0);
        if(mchFoodBeanList == null){

            mchFoodBeanList = new ArrayList<>();

        } else {

            mchFoodBeanList.clear();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FoodRecommendationListActivity.this) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };

        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        mRvFoodRecommendList.setLayoutManager(linearLayoutManager);
        fineFoodRecommendListAdapter = new FineFoodRecommendListAdapter(FoodRecommendationListActivity.this);
        fineFoodRecommendListAdapter.setFineFoodList(mchFoodBeanList);
        mRvFoodRecommendList.setAdapter(fineFoodRecommendListAdapter);
        setHeader(mRvFoodRecommendList);
    }

    private void setHeader(RecyclerView view)
    {
        header = LayoutInflater.from(this).inflate(R.layout.layout_fine_food_recommend_header_item, view, false);
        mTvFineFoodRecommendNum = header.findViewById(R.id.tv_food_recommend_num);
    }

    @Override
    public void initData()
    {
        showProgressDialog(FoodRecommendationListActivity.this);
        getGoodsFoodRecommendList();

        mSmartRefreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isOnLoadMore = false;
                        mPageNo = 1;
                        mchFoodBeanList.clear();
                        fineFoodRecommendListAdapter.notifyDataSetChanged();
                        showProgressDialog(FoodRecommendationListActivity.this);
                        getGoodsFoodRecommendList();

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
                            if (mTotalPageCount == mchFoodBeanList.size()) {
                                refreshLayout.finishLoadMoreWithNoMoreData();
                            } else {
                                mPageNo++;
                                getGoodsFoodRecommendList();
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
    public void initPresenter() {

        mPresenter.setVM(this, mModel);
    }

    @Override
    public void getGoodsFoodRecommendList(BackResult<FoodRecommendListResponse> res) {
        dismissProgressDialog();
        if (mSmartRefreshLayout != null) {
            mSmartRefreshLayout.finishRefresh();
        }
        switch (res.getCode()) {
            case Constants.SUCCESS_CODE:
                try {

                    if (isOnLoadMore) {

                        mSmartRefreshLayout.finishLoadMore();

                    } else {

                        mchFoodBeanList.clear();
                        fineFoodRecommendListAdapter.notifyDataSetChanged();
                        mSmartRefreshLayout.finishRefresh();
                        mSmartRefreshLayout.setNoMoreData(false);
                    }

                    BasePaginationResult paginationResult = res.getData().getPage();
                    mTotalPageCount = paginationResult.getPageCount();
                    List<MchFoodBean> strategyList = res.getData().getResult();

                    if (mTotalPageCount == 0)
                    {
                        mRlytNoData.setVisibility(View.VISIBLE);

                    } else {
                        mRlytNoData.setVisibility(View.GONE);
                    }

                    if (strategyList != null)
                    {
                        mchFoodBeanList.addAll(strategyList);
                    }

                    fineFoodRecommendListAdapter.setFineFoodList(mchFoodBeanList);
                    fineFoodRecommendListAdapter.notifyDataSetChanged();

                    int mchFoodRecommendNum = mchFoodBeanList.size();
                    fineFoodRecommendListAdapter.setHeaderView(header);
                    mTvFineFoodRecommendNum.setText("(" + mchFoodRecommendNum + ")");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                showToast(FoodRecommendationListActivity.this, Constants.getResultMsg(res.getMsg()));
                break;
        }
    }

    @Override
    public void getFineFoodHomePageResult(BackResult<ScenicSpotHomePageResponse> res) {

    }

    @Override
    public void findFoodByCateResult(BackResult<ScenicSpotResponse> res) {

    }

    @Override
    public void findFoodsListByCateIdResult(BackResult<MchCateListResponse> res) {

    }

    @Override
    public void getFoodBangDanRankingResult(BackResult<MchBangDanRankingResponse> res) {

    }

    @Override
    public void getFoodCommentIndexResult(BackResult<FineFoodCommentInitResponse> res) {

    }

    @Override
    public void fineFoodCommentResult(BackResult res) {

    }

    @Override
    public void getFoodDetailResult(BackResult<MchFoodDetailResponse> res) {

    }

    @Override
    public void mchCollectionResult(BackResult<MchCollectionResponse> res) {

    }

    @Override
    public void showMsg(String msg)
    {
        if (mSmartRefreshLayout != null)
        {
            mSmartRefreshLayout.finishRefresh();
        }

        dismissProgressDialog();
        showToast(FoodRecommendationListActivity.this, Constants.getResultMsg(msg));
    }

    //美食推荐列表
    public void getGoodsFoodRecommendList() {

        if (validateInternet())
        {
            mPresenter.getGoodsFoodRecommendList(mchId);
        }
    }
}
